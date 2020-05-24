using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Apache.NMS;
using MQTTnet;
using MQTTnet.Client;
using MQTTnet.Client.Connecting;
using MQTTnet.Client.Options;
using Spring.Messaging.Nms.Core;

namespace SenderJms
{
    class Listener : IMessageListener
    {
        public static IMqttClient mqttClient;

        public Listener()
        {
            var factory = new MqttFactory();
            mqttClient = factory.CreateMqttClient();
            mqttClient.ConnectedHandler = new MqttClientConnectedHandlerDelegate(OnConnected);
            var options = new MqttClientOptionsBuilder()
                .WithClientId("ClientA")
                .WithNoKeepAlive()
                .WithKeepAlivePeriod(TimeSpan.FromSeconds(4000))
                .WithCommunicationTimeout(TimeSpan.FromSeconds(4000))
                .WithTcpServer("localhost", 1883)
                .Build();

            mqttClient.ConnectAsync(options);
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


        }
        #region IMessageListener Members




        #endregion

        public void OnMessage(IMessage message)
        {
            ITextMessage textMessage = message as ITextMessage;
            Console.WriteLine(textMessage.Text);
            var message1 = new MqttApplicationMessageBuilder()
                .WithTopic("Answer")
                .WithPayload(textMessage.Text)
                .WithExactlyOnceQoS()
                .WithRetainFlag()
                .Build();

            mqttClient.PublishAsync(message1);
        }

        private static async void OnConnected(MqttClientConnectedEventArgs e)
        {
            Console.WriteLine("### CONNECTED WITH SERVER ###");
            Console.WriteLine("### SUBSCRIBED ###");

        }
    }


}
