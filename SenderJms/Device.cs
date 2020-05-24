using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace SenderJms
{
    class Device

    {
       public static Random random = new Random();

        private string[] a = {"Gps Sensor", "Temperature Sensor", "Humidity Sensor", "Light Sensor", "Adel Sensor", ""};
        public Device()
        {

            generateDevice();

        }


        public string macAddress { get; set; }
        public string deviceType { get; set; }
        public string name { get; set; }

        public string metricValue { get; set; }
        public string metricDate { get; set; }


        public void generateDevice()
        {
            this.deviceType = GetrandomType();
            this.macAddress = GetRandomMacAddress();
            this.name = "";
            this.metricValue = GetrandomMetric();
        }

        public void changeDate()
        {
            this.metricDate = DateTime.Now.ToString();
        }
        private string GetrandomMetric()
        {
            if (this.deviceType == "Temperature Sensor")
            return random.Next(3, 29).ToString();
            else if (this.deviceType == "Humidity Sensor")
                return random.Next(40, 80).ToString();
            else if (this.deviceType == "Light Sensor")
                return random.Next(100, 800).ToString();

            return random.Next(3, 29).ToString();


        }

        public string ChangeMetric()
        {

            if (this.deviceType == "Temperature Sensor")
               this.metricValue= random.Next(3, 29).ToString();
            else if (this.deviceType == "Humidity Sensor")
                this.metricValue = random.Next(40, 80).ToString();
            else if (this.deviceType == "Light Sensor")
                this.metricValue = random.Next(100, 800).ToString();

            else this.metricValue = random.Next(3, 29).ToString();

            return this.metricValue;
        }

        private string GetrandomType()
        {

            return a[random.Next(5)];
        }

        private string GetRandomMacAddress()
        {
            var buffer = new byte[6];
            random.NextBytes(buffer);
            var result = String.Concat(buffer.Select(x => string.Format("{0}:", x.ToString("X2"))).ToArray());
            return result.TrimEnd(':');
        }
    }

    
}
