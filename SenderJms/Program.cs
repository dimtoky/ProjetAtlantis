using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
using Apache.NMS.ActiveMQ;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Spring.Messaging.Nms.Core;

namespace SenderJms
{
    class Program
    {
        public static Device device = new Device();
        private const string URI = "tcp://192.168.9.129:61616";
        private const string DESTINATION = "reqInfo";
        static void Main(string[] args)
        {
            ConnectionFactory connectionFactory = new ConnectionFactory(URI);
            NmsTemplate template = new NmsTemplate(connectionFactory);

            string devicesnumber = Console.ReadLine();
            

                Console.WriteLine(device.deviceType + " " + device.macAddress + " " + device.name + " " + device.metricDate + " " + device.metricValue);
            

            Console.ReadLine();
            //SomeMethod();
          ;
            //SendToJMS.ListenToAnswer();
            SendToMQTT.testAsync();

        }

        


        


    }
}
