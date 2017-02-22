// DateUtils.java
// Various utilities to work with dates
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class DateUtils {
	private DateUtils(){}
	
	private static Calendar _getCalendar()
	{
        TimeZone tz	= TimeZone.getTimeZone("UTC");
        return Calendar.getInstance(tz);
	}
	
	private static Date _addPeriod(Date startDate, int periodType, int numberOfPeriods)
	{
		Calendar cldr = _getCalendar();
        cldr.setTime(startDate);
        cldr.add(periodType, numberOfPeriods);
        return cldr.getTime();
	}
	
	public static Date AddDays(Date startDate, int numDays)
	{
        return _addPeriod(startDate, Calendar.DATE, numDays);
	}
	
	public static Date AddMonths(Date startDate, int numMonths)
	{
        return _addPeriod(startDate, Calendar.MONTH, numMonths);
	}
	
	public static Date AddYears(Date startDate, int numYears)
	{
        return _addPeriod(startDate, Calendar.YEAR, numYears);
	}
	
	public static long CountDays(Date startDate, Date endDate)
	{
    	long milliSecs = endDate.getTime() - startDate.getTime();
    	long nbDays = TimeUnit.DAYS.convert(milliSecs, TimeUnit.MILLISECONDS);
    	return nbDays;
	}
	
    public static int CountYears(Date startDate, Date endDate)
    {
        int years = 0;
        startDate = AddYears(startDate, 1);
        while (!startDate.after(endDate))
        {
            years++;
            startDate = AddYears(startDate, 1);
        }
        return years;
    }
    
    public static int GetDay(Date d)
    {
    	return _getDateComponent(d, Calendar.DAY_OF_MONTH);
    }
    
    public static int GetMonth(Date d)
    {
    	return _getDateComponent(d, Calendar.MONTH);
    }
    
    private static int _getDateComponent(Date d, int comp)
    {
    	Calendar cldr = _getCalendar();
    	cldr.setTime(d);
    	return cldr.get(comp);
    }
    
	public static Date AddPeriod(Date startDate, String period)
    {
        // Parse period
        int num = Integer.parseInt(period.substring(0, period.length() - 1));
        String unit = period.substring(period.length() - 1);

        // Start date adjustment
        Calendar cldr = _getCalendar();
        cldr.setTime(startDate);
        int dayOfWeek = cldr.get(Calendar.DAY_OF_WEEK);
        
        if (dayOfWeek == 0 || dayOfWeek == 6)
        {
            /* Sunday = 0, Saturday = 6
             * If Sunday (0), add 1 day
             * If Saturday (6), add 2 days
             * day of week / 6 + 1 is 1 for Sunday, 2 for Saturday */
            startDate = AddDays(startDate, dayOfWeek / 6 + 1);
        }

        Date endDate = startDate;
        switch (unit)
        {
            case "D":
                endDate = AddDays(startDate, num);
                break;
            case "W":
                endDate = AddDays(startDate, num * 7);
                break;
            case "M":
                endDate = AddMonths(startDate, num);
                break;
            default:    // Assume year
                endDate = AddYears(startDate, num);
                break;
        }

        // End date adjustment
        cldr.setTime(endDate);
        dayOfWeek = cldr.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 0 || dayOfWeek == 6)
        {
        	endDate = AddDays(endDate, dayOfWeek / 6 + 1);
        }

        return endDate;
    }
	
	public static double YearFrac(Date startDate, Date endDate)
	{
		return YearFrac(startDate, endDate, DaycountConvention.ActualActual);
	}
	
    public static double YearFrac(Date startDate, Date endDate, DaycountConvention dayCount)
    {
        double yearBasis = dayCount == DaycountConvention.Actual365 ? 365.0 : 360.0;
        if (dayCount == DaycountConvention.ActualActual)
        {
            yearBasis = CountDays(startDate, AddYears(startDate, 1));
        }

        // Find number of whole years
        int wholeYears = CountYears(endDate, startDate);

        if(wholeYears > 0)
        {
            startDate = AddYears(startDate, wholeYears);
        }

        // Loop through months
        int totalNumberOfDays = 0;
        while (startDate.before(endDate))
        {
            Date monthStartDate = AddDays(startDate, -(GetDay(startDate)-1));
            Date monthEndDate = endDate;
            if (startDate.getTime() != monthStartDate.getTime())    // if start date is not first day of month
            {
                monthEndDate = AddMonths(monthStartDate, 1);
            }
            else if (GetMonth(startDate) != GetMonth(endDate))  // If end date is not in same month, then find last day
            {
            	monthEndDate = (AddMonths(monthStartDate, 1).after(endDate)) ? endDate : AddMonths(monthStartDate, 1);
            }

            // Increment total by number of days in (partial) month following convention
            if (dayCount == DaycountConvention.Thirty360)
            {
                int numberOfDays = 31 - GetDay(startDate);
                if (GetMonth(startDate) == 2 /*month is February*/ && numberOfDays >= 28)
                {
                    totalNumberOfDays += 30;
                }
                else
                {
                    totalNumberOfDays += Math.min(numberOfDays, 30);
                }
            }
            else
            {
                totalNumberOfDays += CountDays(startDate, monthEndDate);
            }

            // Increment start date and repeat
            if (startDate != monthStartDate)
            {
                startDate = monthEndDate;
            }
            else
            {
                startDate = AddMonths(startDate, 1);
            }

        }

        return (double)wholeYears + (totalNumberOfDays / yearBasis);
    }
    
    public static Period CalculateZcDates(Date valueDate, String period)
    {
        Date startDate = valueDate;
        Period zcDates = new Period();
        switch (period)
        {
            case "0D":
            	zcDates.setStartDate(valueDate);
            	zcDates.setEndDate(valueDate);
                break;
            case "ON":
            	zcDates.setStartDate(startDate);
            	zcDates.setEndDate(AddPeriod(startDate, "1D"));
                break;
            case "TN":
                startDate = AddPeriod(startDate, "1D");
            	zcDates.setStartDate(startDate);
            	zcDates.setEndDate(AddPeriod(startDate, "1D"));
                break;
            case "SW":
                startDate = AddPeriod(startDate, "2D");
            	zcDates.setStartDate(startDate);
            	zcDates.setEndDate(AddPeriod(startDate, "1W"));
                break;
            default:    // Spot. Assume 2 days spot lag. TODO: for LIBOR use 0 days when underlying is swap or LIBOR
                startDate = AddPeriod(startDate, "2D");
            	zcDates.setStartDate(startDate);
            	zcDates.setEndDate(AddPeriod(startDate, period));
                break;
        }
        return zcDates;
    }
    
    public static ArrayList<Coupon> GetCouponDates(Date settlementDate, Date maturityDate, Frequency frequency)
    {
    	ArrayList<Coupon> coupons = new ArrayList<Coupon>();
    	Period couponDates = new Period(AddMonths(maturityDate, -frequency.getValue()), maturityDate);
        while (!(couponDates.getStartDate().before(settlementDate)))
        {
        	coupons.add(new Coupon(couponDates.getStartDate(), couponDates.getEndDate(), DaycountConvention.Actual360));
            couponDates = new Period(AddMonths(couponDates.getStartDate(), -frequency.getValue()), couponDates.getStartDate());
        }
        return coupons;
    }}
