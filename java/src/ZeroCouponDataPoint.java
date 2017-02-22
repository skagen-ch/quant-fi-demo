// ZeroCouponDataPoint.java
// Represents a point on the zero coupon curve
import java.util.Date;


public class ZeroCouponDataPoint extends Period {
	private Date _valueDate;
	private String _period;
	private double _discountFactor;
	
	// Main constructor
    public ZeroCouponDataPoint(Date valueDate, String period, double discountFactor)
    {
        setValueDate(valueDate);
        setPeriod(period);
        setDiscountFactor(discountFactor);
        Period zcDates = DateUtils.CalculateZcDates(valueDate, period);
        setStartDate(zcDates.getStartDate());
        setEndDate(zcDates.getEndDate());
    }

    private void setValueDate(Date valueDate)
    {
    	_valueDate = valueDate;
	}
    
    public Date getValueDate()
    {
    	return _valueDate;
	}

    private void setPeriod(String period)
    {
    	_period = period;
	}
    
    public String getPeriod()
    {
    	return _period;
	}

    private void setDiscountFactor(double discountFactor)
    {
    	_discountFactor = discountFactor;
	}
    
    public double getDiscountFactor()
    {
    	return _discountFactor;
	}

}
