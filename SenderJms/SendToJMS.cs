﻿using System;
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
        private const string Answer = "Answer";
        private const string Metrics = "Metrics";
        private const string Command = "Command";


     

        public static void Send(string message, string queue)
        {
            ConnectionFactory connectionFactory = new ConnectionFactory(URI);
            NmsTemplate template = new NmsTemplate(connectionFactory);
            template.ConvertAndSend(queue, message);
        }
    }
}
