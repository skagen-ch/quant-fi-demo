using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace vanilla_swap
{
    public static class DateUtils
    {
        public double YearFrac(DateTime startDate, DateTime endDate, DayCountBasis dayCount = DayCountBasis.ActualActual)
        {
            int yearBasis = 365;
            bool thirtyDayMonth = false;

            switch (dayCount)
            {
                case DayCountBasis.Actual360:
                    yearBasis = 360;
                    break;
                case DayCountBasis.Actual365:
                    break;
                case DayCountBasis.Thirty360:
                    yearBasis = 360;
                    break;
                default:
                    var oneYearDate = startDate.AddYears(1);
                    yearBasis = (oneYearDate - startDate).Days;
                    break;
            }

            // Find number of whole years
            var wholeYears = (endDate - startDate);
        }
        /*

        # Find number of whole years
        whole_years = relativedelta(end_date, start_date).years
        if whole_years > 0:
            start_date = start_date + relativedelta(months=whole_years*12)

        # Loop through months
        total_number_of_days = 0
        while start_date <= end_date:
            # If end date is not in same month, then find last day
            if start_date.month != end_date.month:
                month_start_date = start_date - timedelta(days=start_date.day - 1)
                month_end_date = month_start_date + relativedelta(months=1)
            else:
                month_end_date = end_date #- timedelta(days=1)

            # Find actual number of days in month or partial month
            actual_number_of_days = (month_end_date - start_date).days

            # Increment total by number of days in (partial) month following convention
            if thirty_day_month:
                is_feb = start_date.month == 2
                if is_feb and actual_number_of_days >= 28:
                    total_number_of_days += 30
                else:
                    total_number_of_days += min(actual_number_of_days, 30)
            else:
                total_number_of_days += actual_number_of_days

            # Increment start date and repeat
            start_date = start_date + relativedelta(months=1)

        return whole_years + (total_number_of_days/year_basis)
         * */
    }
}
