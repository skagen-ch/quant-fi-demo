using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace vanilla_swap
{
    public class Coupon
    {
        public Coupon(DateTime startDate, DateTime endDate, DayCountBasis dayCount = DayCountBasis.ActualActual)
        {
            StartDate = startDate;
            EndDate = endDate;
            NbDays = (EndDate - StartDate).Days;
            DayCount = dayCount;
        }

        public DateTime StartDate { get; set; }

        public DateTime EndDate { get; set; }

        public int NbDays { get; set; }

        public DayCountBasis DayCount { get; set; }
    }
}
