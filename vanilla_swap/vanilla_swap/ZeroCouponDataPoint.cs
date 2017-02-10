using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace vanilla_swap
{
    public class ZeroCouponDataPoint
    {
        public ZeroCouponDataPoint(DateTime valueDate, string period, double discountFactor)
        {
            ValueDate = valueDate;
            Period = period;
            DiscountFactor = discountFactor;
            var zcDates = DateUtils.CalculateZcDates(valueDate, period);
            StartDate = zcDates.Item1;
            EndDate = zcDates.Item2;
        }

        public DateTime ValueDate { get; set; }

        public DateTime StartDate { get; set; }

        public DateTime EndDate { get; set; }

        public string Period { get; set; }

        public double DiscountFactor { get; set; }

    }
}
