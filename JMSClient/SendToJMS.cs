using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;
using Spring.Messaging.Nms.Core;
using Spring.Messaging.Nms.Listener;

namespace SenderJms
{
   static class SendToJMS
    {
        private const string URI = "tcp://192.168.9.129:61616";
        private const string RequestInfo = "RequestInfo";
        private const string Answer = "REPINFO";
        private const string Metrics = "DBMETRICS";
        private const string Command = "COMMANDS";


        public static void ListenToAnswer()
        {
            

            Console.WriteLine("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
            try
            {
                ConnectionFactory connectionFactory = new ConnectionFactory(URI);

                using (SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer())
                {
                    listenerContainer.ConnectionFactory = connectionFactory;
                    listenerContainer.DestinationName = Answer;
                    listenerContainer.MessageListener = new Listener();
                    listenerContainer.AfterPropertiesSet();
                    ListenToCommand();
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                Console.WriteLine("Press <ENTER> to exit.");
                Console.Read();
            }
        }

        public static void ListenToCommand()
        {
            Console.WriteLine("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
            try
            {
                ConnectionFactory connectionFactory = new ConnectionFactory(URI);

                using (SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer())
                {
                    listenerContainer.ConnectionFactory = connectionFactory;
                    listenerContainer.DestinationName = Command;
                    listenerContainer.MessageListener = new CommandListener();
                    listenerContainer.AfterPropertiesSet();
                    
                    while (true)
                    {

                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                Console.WriteLine("Press <ENTER> to exit.");
                Console.Read();
            }
        }


    }
}
