using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using mn = MathNet.Numerics.Interpolation;

namespace VanillaSwap
{
    public class Coupon
    {
        private double? _userForwardRate;

        public Coupon(DateTime startDate, DateTime endDate, DayCountBasis dayCount = DayCountBasis.ActualActual)
        {
            StartDate = startDate;
            EndDate = endDate;
            NbDays = (EndDate - StartDate).Days;
            DayCount = dayCount;
            NbYears = DateUtils.YearFrac(StartDate, EndDate);
        }

        public DateTime StartDate { get; set; }

        public DateTime EndDate { get; set; }

        public int NbDays { get; set; }

        public DayCountBasis DayCount { get; set; }

        public double NbYears { get; set; }

        public double StartDiscountFactor { get; set; }

        public double EndDiscountFactor { get; set; }

        public double ForwardRate {
            get
            {
                if (_userForwardRate.HasValue) return _userForwardRate.Value;
                return (StartDiscountFactor == 0 || NbYears == 0) ? 0 : ((1 / EndDiscountFactor / StartDiscountFactor) - 1) / NbYears;
            }
            set
            {
                _userForwardRate = value;
            } 
        }

        public double CashFlow { get; set; }

        public void CalculateDiscountFactor(IList<ZeroCouponDataPoint> discountCurve)
        {
            var cs = mn.CubicSpline.InterpolateNatural(discountCurve.Select(x => x.EndDate.ToOADate()), discountCurve.Select(y => y.DiscountFactor));
            StartDiscountFactor = cs.Interpolate(StartDate.ToOADate());
            EndDiscountFactor = cs.Interpolate(EndDate.ToOADate());
        }

        public void CalculateCashFlow(double notional, double spread = 0.0, bool maturity = false)
        {
            var couponRate = ForwardRate + spread;
            var redemption = maturity ? 1 : 0;
            CashFlow = (redemption + (couponRate * NbYears)) * notional;
        }
    }
}
