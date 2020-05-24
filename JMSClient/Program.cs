using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SenderJms;

namespace JMSClient
{
    class Program
    {
        static void Main(string[] args)
        {
            SendToJMS.ListenToAnswer();
            //SendToJMS.ListenToCommand();
        }
    }
}
