using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VanillaSwap
{
    public static class DateUtils
    {
        /// <summary>
        /// Calculates the number of years as a fraction, using period-based calculation and the given daycount convention.
        /// </summary>
        /// <param name="startDate">Start date of the period</param>
        /// <param name="endDate">End date of the period</param>
        /// <param name="dayCount">Daycount basis, e.g. Actual/Actual or 30/360</param>
        /// <returns>The number of years between start and end date</returns>
        public static double YearFrac(DateTime startDate, DateTime endDate, DayCountBasis dayCount = DayCountBasis.ActualActual)
        {
            var yearBasis = dayCount == DayCountBasis.Actual365 ? 365.0 : 360.0;
            if (dayCount == DayCountBasis.ActualActual)
            {
                yearBasis = (startDate.AddYears(1) - startDate).Days;
            }

            // Find number of whole years
            var wholeYears = CountYears(endDate, startDate);

            if(wholeYears > 0)
            {
                startDate = startDate.AddYears(wholeYears);
            }

            // Loop through months
            var totalNumberOfDays = 0;
            while (startDate <= endDate)
            {
                var monthStartDate = startDate.AddDays(-(startDate.Day - 1));
                var monthEndDate = endDate;
                if (startDate != monthStartDate)    // if start date is not first day of month
                {
                    monthEndDate = monthStartDate.AddMonths(1);
                }
                else if (startDate.Month != endDate.Month)  // If end date is not in same month, then find last day
                {
                    monthEndDate = new DateTime(Math.Min(endDate.Ticks, startDate.AddMonths(1).Ticks));
                }

                // Increment total by number of days in (partial) month following convention
                if (dayCount == DayCountBasis.Thirty360)
                {
                    var numberOfDays = 31 - startDate.Day;
                    if (startDate.Month == 2 /*month is February*/ && numberOfDays >= 28)
                    {
                        totalNumberOfDays += 30;
                    }
                    else
                    {
                        totalNumberOfDays += Math.Min(numberOfDays, 30);
                    }
                }
                else
                {
                    totalNumberOfDays += (monthEndDate - startDate).Days;
                }

                // Increment start date and repeat
                if (startDate != monthStartDate)
                {
                    startDate = monthEndDate;
                }
                else
                {
                    startDate = startDate.AddMonths(1);
                }

            }

            return (double)wholeYears + (totalNumberOfDays / yearBasis);
        }

        public static int CountYears(DateTime startDate, DateTime endDate)
        {
            int years = 0;
            startDate = startDate.AddYears(1);
            while (startDate <= endDate)
            {
                years++;
                startDate = startDate.AddYears(1);
            }
            return years;
        }

        public static IEnumerable<Coupon> GetCouponDates(DateTime settlementDate, DateTime maturityDate, Frequency frequency)
        {
            var couponDates = new Tuple<DateTime, DateTime>(maturityDate.AddMonths(-(int)frequency), maturityDate);
            while (couponDates.Item1 > settlementDate)
            {
                yield return new Coupon(couponDates.Item1, couponDates.Item2, DayCountBasis.Actual360);
                couponDates = new Tuple<DateTime, DateTime>(couponDates.Item1.AddMonths(-(int)frequency), couponDates.Item1);
            }
        }

        /// <summary>
        /// Basic add period which does not take into account date adjustment conventions such as modified following.
        /// </summary>
        /// <param name="startDate"></param>
        /// <param name="period"></param>
        /// <returns></returns>
        public static DateTime AddPeriod(DateTime startDate, string period)
        {
            // Parse period
            var num = int.Parse(period.Substring(0, period.Length - 1));
            var unit = period[period.Length - 1].ToString();

            // Start date adjustment
            if (startDate.DayOfWeek == DayOfWeek.Saturday || startDate.DayOfWeek == DayOfWeek.Sunday)
            {
                /* Sunday = 0, Saturday = 6
                 * If Sunday (0), add 1 day
                 * If Saturday (6), add 2 days
                 * day of week / 6 + 1 is 1 for Sunday, 2 for Saturday */
                startDate.AddDays((int)startDate.DayOfWeek / 6 + 1);
            }

            var endDate = startDate;
            switch (unit)
            {
                case "D":
                    endDate = startDate.AddDays(num);
                    break;
                case "W":
                    endDate = startDate.AddDays(num * 7);
                    break;
                case "M":
                    endDate = startDate.AddMonths(num);
                    break;
                default:    // Assume year
                    endDate = startDate.AddYears(num);
                    break;
            }

            // End date adjustment
            if (endDate.DayOfWeek == DayOfWeek.Saturday || endDate.DayOfWeek == DayOfWeek.Sunday)
            {
                endDate.AddDays((int)endDate.DayOfWeek / 6 + 1);
            }

            return endDate;
        }

        public static Tuple<DateTime, DateTime> CalculateZcDates(DateTime valueDate, string period)
        {
            var startDate = valueDate;
            var endDate = valueDate;

            switch (period)
            {
                case "0D":
                    return new Tuple<DateTime, DateTime>(startDate,endDate);
                case "ON":
                    return new Tuple<DateTime, DateTime>(startDate, AddPeriod(startDate, "1D"));
                case "TN":
                    startDate = AddPeriod(startDate, "1D");
                    return new Tuple<DateTime, DateTime>(startDate, AddPeriod(startDate, "1D"));
                case "SW":
                    startDate = AddPeriod(startDate, "2D");
                    return new Tuple<DateTime, DateTime>(startDate, AddPeriod(startDate, "1W"));
                default:    // Spot. Assume 2 days spot lag. TODO: for LIBOR use 0 days when underlying is swap or LIBOR
                    startDate = AddPeriod(startDate, "2D");
                    return new Tuple<DateTime, DateTime>(startDate, AddPeriod(startDate, period));
            }
        }
    }
}
