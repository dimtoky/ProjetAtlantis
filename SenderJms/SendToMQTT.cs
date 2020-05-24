


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Json;
using MQTTnet;
using MQTTnet.Client;
using MQTTnet.Client.Connecting;
using MQTTnet.Client.Disconnecting;
using MQTTnet.Client.Options;
using MQTTnet.Client.Receiving;

using Newtonsoft.Json;
using Newtonsoft.Json.Linq;


namespace SenderJms
{
    static class SendToMQTT
    {
        public static IMqttClient mqttClient , mqttClient2;
        private static Random random = new Random();
        public static string RandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
                .Select(s => s[random.Next(s.Length)]).ToArray());
        }
        public static async Task testAsync()
        {
            

            var factory = new MqttFactory();
            mqttClient = factory.CreateMqttClient();
            var factory1 = new MqttFactory();
           mqttClient2 = factory.CreateMqttClient();
              mqttClient2.ApplicationMessageReceivedHandler = new MqttApplicationMessageReceivedHandlerDelegate(OnAppMessage);
            mqttClient.ConnectedHandler = new MqttClientConnectedHandlerDelegate(OnConnected);
           mqttClient2.ConnectedHandler = new MqttClientConnectedHandlerDelegate(OnConnected1);



            var options = new MqttClientOptionsBuilder()
                .WithClientId(RandomString(6))
                .WithNoKeepAlive()
                .WithKeepAlivePeriod(TimeSpan.FromSeconds(4000))
                .WithCommunicationTimeout(TimeSpan.FromSeconds(4000))
                .WithTcpServer("localhost", 1883)
                .Build();

            var options1 = new MqttClientOptionsBuilder()
                .WithClientId(RandomString(8))
                .WithKeepAlivePeriod(TimeSpan.FromSeconds(4000))
                .WithCommunicationTimeout(TimeSpan.FromSeconds(4000))
                .WithCommunicationTimeout(TimeSpan.FromSeconds(150))
                .WithTcpServer("localhost", 1883)
                .Build();



            mqttClient.ConnectAsync(options);
            mqttClient2.ConnectAsync(options1);




            mqttClient.UseDisconnectedHandler(async e =>
            {
                Console.WriteLine("### DISCONNECTED FROM SERVER ###");
                await Task.Delay(TimeSpan.FromSeconds(5));

                try
                {
                    await mqttClient.ConnectAsync(options);
                }
                catch
                {
                    Console.WriteLine("### RECONNECTING FAILED ###");
                }
            });

            mqttClient2.UseDisconnectedHandler(async e =>
            {
                Console.WriteLine("### DISCONNECTED FROM SERVER ###");
                await Task.Delay(TimeSpan.FromSeconds(5));

                try
                {
                    await mqttClient2.ConnectAsync(options);
                }
                catch
                {
                    Console.WriteLine("### RECONNECTING FAILED ###");
                }
            });





            while (true)
            {
                if (mqttClient2.IsConnected)
                {
                     Send();
                 Thread.Sleep(3000);
                }
               
            }



        }

         public static  void Send()

        {

            
                Program.device.ChangeMetric();
                Program.device.changeDate();

                if (Program.device.name == "")
                {

                    string result = JsonConvert.SerializeObject(Program.device);
                    var message = new MqttApplicationMessageBuilder()
                        .WithTopic("RequestInfo")
                        .WithPayload(result)
                        .WithExactlyOnceQoS()
                        .WithRetainFlag()
                        .Build();
                    try
                    {
                        mqttClient.PublishAsync(message);
                        Thread.Sleep(5000);

                    }
                    catch (Exception e)
                    {
                    }
                }

                else
                {


                    string result = JsonConvert.SerializeObject(Program.device);
                    var message = new MqttApplicationMessageBuilder()
                        .WithTopic("Metrics")
                        .WithPayload(result)
                        .WithExactlyOnceQoS()
                        .WithRetainFlag()
                        .Build();


                    try
                    {
                        mqttClient.PublishAsync(message);

                    }
                    catch (Exception e)
                    {

                    }
                }
          


            



        }

        

        

        private static async void OnAppMessage(MqttApplicationMessageReceivedEventArgs e)
        {

            Task t = new Task(() =>
            {
                if (e.ApplicationMessage.Topic == "Answer")
                {
                    

                    try
                    {
                        Device b = Newtonsoft.Json.JsonConvert.DeserializeObject<Device>(Encoding.UTF8.GetString(e.ApplicationMessage.Payload));
                        Program.device.name = b.name;

                    }
                    catch (Exception exception)
                    {

                    }

                    if (e.ApplicationMessage.Topic == "Command")
                    {


                        try
                        {
                            Device b = Newtonsoft.Json.JsonConvert.DeserializeObject<Device>(Encoding.UTF8.GetString(e.ApplicationMessage.Payload));
                            Console.WriteLine("Le Device : " + Program.device.name + " a effectué la commande" + e.ApplicationMessage.Payload);

                        }
                        catch (Exception exception)
                        {

                        }


                    }


                }

                if (e.ApplicationMessage.Topic == "Metrics")
                {
                    SendToJMS.Send(Encoding.UTF8.GetString(e.ApplicationMessage.Payload), "DBMETRICS");
                    SendToJMS.Send(Encoding.UTF8.GetString(e.ApplicationMessage.Payload), "MOBILEMETRICS");


                }

                if (e.ApplicationMessage.Topic == "RequestInfo")
                {
                    SendToJMS.Send(Encoding.UTF8.GetString(e.ApplicationMessage.Payload), "REQINFO");


                }

                Console.Clear();
                Console.WriteLine("### RECEIVED APPLICATION MESSAGE ###");
                Console.WriteLine($"+ Topic = {e.ApplicationMessage.Topic}");
                Console.WriteLine($"+ Payload = {Encoding.UTF8.GetString(e.ApplicationMessage.Payload)}");
                Console.WriteLine($"+ QoS = {e.ApplicationMessage.QualityOfServiceLevel}");
                Console.WriteLine($"+ Retain = {e.ApplicationMessage.Retain}");
                Console.WriteLine();


            });
            t.Start();
            await t;
            
          

        }

        private static async void OnConnected(MqttClientConnectedEventArgs e)
        {
            Console.WriteLine("### CONNECTED WITH SERVER ###");

            // Subscribe to a topic
           // await mqttClient.SubscribeAsync(new TopicFilterBuilder().WithTopic("RequestInfo").Build());
           


            Console.WriteLine("### SUBSCRIBED ###");

        }

        private static async void OnConnected1(MqttClientConnectedEventArgs e)
        {
            Console.WriteLine("### CONNECTED WITH SERVER ###");

            
            await mqttClient2.SubscribeAsync(new TopicFilterBuilder().WithTopic("RequestInfo").Build());
            await mqttClient2.SubscribeAsync(new TopicFilterBuilder().WithTopic("Answer").Build());
            await mqttClient2.SubscribeAsync(new TopicFilterBuilder().WithTopic("Metrics").Build());
            await mqttClient2.SubscribeAsync(new TopicFilterBuilder().WithTopic("Command").Build());

            Console.WriteLine("### SUBSCRIBED ###");

        
            
        }



    }
}



           