// Coupon.java
// Represents a swap cash flow
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class Coupon extends Period {
	private int _nbDays;
	private DaycountConvention _dayCount;
	private double _nbYears;
    private Double _userForwardRate;
    private double _startDiscountFactor;
    private double _endDiscountFactor;
    private double _cashFlow;

    public Coupon(Date startDate, Date endDate)
    {
    	this(startDate, endDate, DaycountConvention.ActualActual); 
    }

    public Coupon(Date startDate, Date endDate, DaycountConvention dayCount)
    {
    	setStartDate(startDate);
    	setEndDate(endDate);
    	setDayCount(dayCount); 
    }

    public long getNbDays()
    {
    	return DateUtils.CountDays(this.getStartDate(), this.getEndDate());
	}

    private void setDayCount(DaycountConvention dayCount)
    {
    	_dayCount = dayCount;
	}

    public DaycountConvention getDayCount()
    {
    	return _dayCount != null ? _dayCount : DaycountConvention.ActualActual;
	}

    public double getNbYears()
    {
    	_nbYears = DateUtils.YearFrac(this.getStartDate(), this.getEndDate());
    	return _nbYears;
	}

    public void setStartDiscountFactor(double df)
    {
    	_startDiscountFactor = df;
    }
    
    public double getStartDiscountFactor()
    {
    	return _startDiscountFactor;
    }

    public void setEndDiscountFactor(double df)
    {
    	_endDiscountFactor = df;
    }
    
    public double getEndDiscountFactor()
    {
    	return _endDiscountFactor;
    }

    public void setForwardRate(double rate)
    {
    	_userForwardRate = rate;	
    }
    
    public double getForwardRate()
    {
    	if (!(_userForwardRate == null)) return _userForwardRate.doubleValue();
        return (getStartDiscountFactor() == 0 || getNbYears() == 0) ? 0 : ((1 / getEndDiscountFactor() / getStartDiscountFactor()) - 1) / getNbYears();
    }

    public void setCashFlow(double cf)
    {
    	_cashFlow = cf;
    }
    
    public double getCashFlow()
    {
    	return _cashFlow;
    }

    public void CalculateDiscountFactor(ArrayList<ZeroCouponDataPoint> discountCurve)
    {
    	SplineInterpolator interp = new SplineInterpolator();
    	double[] xArray = discountCurve.stream().mapToDouble(x -> x.getEndDate().getTime()).toArray();
		double[] yArray = discountCurve.stream().mapToDouble(y -> y.getDiscountFactor()).toArray();
		PolynomialSplineFunction cs = interp.interpolate(xArray, yArray);
        setStartDiscountFactor(cs.value(getStartDate().getTime()));
        setEndDiscountFactor(cs.value(getEndDate().getTime()));
    }

    public void CalculateCashFlow(double notional)
    {
    	CalculateCashFlow(notional, 0.0, false);
    }

    public void CalculateCashFlow(double notional, double spread)
    {
    	CalculateCashFlow(notional, spread, false);
    }

    public void CalculateCashFlow(double notional, double spread, boolean maturity)
    {
        double couponRate = getForwardRate() + spread;
        double redemption = maturity ? 1.0 : 0.0;
        double nbYears = getNbYears();
        setCashFlow((redemption + (couponRate * nbYears)) * notional);
    }

}
