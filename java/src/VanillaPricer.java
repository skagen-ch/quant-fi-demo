// VanillaPricer.java
// Program that prices a simple vanilla swap
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;

public class VanillaPricer {
	private ArrayList<ZeroCouponDataPoint> _zcCurve;
	private String _maturityDate;
	private Double _calculatedFixedRate;
	private Double _fixedLegNpv;
	private Double _floatLegNpv;
	private ArrayList<Coupon> _fixedCoupons;
	private ArrayList<Coupon> _floatCoupons;
	
	public void main(String[] args) throws java.text.ParseException, NumberFormatException, IOException {
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
        Date valueDate = null;
        Date settle = null;
        
        // Get inputs
        String dateAsString = args[0];
        try
        {
        	valueDate = dateFormat.parse(dateAsString);
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }
        
        dateAsString = args[1];
        try
        {
        	settle = dateFormat.parse(dateAsString);
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }

        String fixFrq = args[2];
        String floatFrq = args[3];
        double notional = Double.parseDouble(args[4]);
        String tenor = args[5];
        double currentFloat = Double.parseDouble(args[6]);
        double spread = Double.parseDouble(args[7])/10000.0;
        double npv = Double.parseDouble(args[8]);
        
        // Inputs for debugging
        /*
        try
        {
        	valueDate = dateFormat.parse("22/02/2017");
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }
        try
        {
        	settle = dateFormat.parse("24/02/2017");
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }
        String fixFrq = "A";
        String floatFrq = "S";
        double notional = 10000000.0;
        String tenor = "5";
        double currentFloat = 0.024;
        double spread = 0.0;
        double npv = 0.0;
        */
        
        // Calculate maturity date
        Date maturity = DateUtils.AddPeriod(settle, tenor + "Y");
        setMaturityDate(dateFormat.format(maturity));

        // Calculate coupon dates
        Hashtable<String, Frequency> frqDict = new Hashtable<String, Frequency>()
        {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
            put("A", Frequency.Annual);
            put("S", Frequency.SemiAnnual);
            put("Q", Frequency.Quarterly);
            put("M", Frequency.Monthly);
        }};
        ArrayList<Coupon> fixedCoupons = DateUtils.GetCouponDates(settle, maturity, frqDict.get(fixFrq));
        ArrayList<Coupon> floatCoupons = DateUtils.GetCouponDates(settle, maturity, frqDict.get(floatFrq));
        fixedCoupons.sort((x, y) -> x.getEndDate().compareTo(y.getEndDate()));
        floatCoupons.sort((x, y) -> x.getEndDate().compareTo(y.getEndDate()));
        /*
        System.out.println();
        for (Coupon cpn : floatCoupons)
        {
            System.out.println(String.format("Start: %tD, End: %tD", cpn.getStartDate(), cpn.getEndDate()));
        }
        */

        // Read discount curve from file
        //URL url = VanillaPricer.class.getResource(args[9]);
        FileReader f = new FileReader(args[9]);
        BufferedReader reader = new BufferedReader(f);
        Hashtable<String, Double> discountCurve = new Hashtable<String, Double>();
        String line = "";
        while ((line = reader.readLine()) != null)
        {
        	String point[] = line.split("\t");
        	discountCurve.put(point[0], Double.parseDouble(point[1]));
        }
        reader.close();
        
        ArrayList<ZeroCouponDataPoint> zcCurve = new ArrayList<ZeroCouponDataPoint>();
        Set<String> periods = discountCurve.keySet();
        for (String p : periods)
        {
        	zcCurve.add(new ZeroCouponDataPoint(valueDate, p, discountCurve.get(p)));
        }
        zcCurve.sort((x, y) -> x.getEndDate().compareTo(y.getEndDate()));
        setZcCurve(zcCurve);
        /*
        for (ZeroCouponDataPoint p : zcCurve)
        {
            System.out.println(String.format("Period: %s, Date: %tD, DF: %.6f", p.getPeriod(), p.getEndDate(), p.getDiscountFactor()));
        }
        */

        // Calculate coupon rates
        floatCoupons.forEach(x -> x.CalculateDiscountFactor(zcCurve));
        // Set current float
        if (currentFloat > 0)
        {
            floatCoupons.get(0).setForwardRate(currentFloat);
        }
        floatCoupons.forEach(x -> x.CalculateCashFlow(notional, spread));
        // Re-calculate final cash flow (with redemption)
        floatCoupons.get(floatCoupons.size() - 1).CalculateCashFlow(notional, spread, true);
        setFloatCoupons(floatCoupons);
        /*
        for (Coupon c : floatCoupons)
        {
            System.out.println(String.format("Date: %tD, Rate: %.6f, Cash flow: %.2f", c.getEndDate(), c.getForwardRate(), c.getCashFlow()));
        }
        */

        // Calculate float leg NPV
        double floatLegNpv = floatCoupons.stream().mapToDouble(x -> x.getCashFlow() * x.getEndDiscountFactor()).sum();
        setFloatLegNpv(floatLegNpv);
        double targetLegNpv = floatLegNpv - npv;
        //System.out.println(String.format("Float leg NPV = %1$.2f, Target leg NPV = %2$.2f", floatLegNpv, targetLegNpv));

        // Solve for coupon rate
        fixedCoupons.forEach(x -> x.CalculateDiscountFactor(zcCurve));
        double fixedRate = Solver.Solve(notional, fixedCoupons, floatCoupons, targetLegNpv);
        fixedCoupons.forEach(x -> x.setForwardRate(fixedRate));
        fixedCoupons.forEach(x -> x.CalculateCashFlow(notional));
        // Re-calculate final cash flow (with redemption)
        fixedCoupons.get(fixedCoupons.size() - 1).CalculateCashFlow(notional, 0, true);
        double fixedLegNpv = fixedCoupons.stream().mapToDouble(x -> x.getCashFlow() * x.getEndDiscountFactor()).sum();
        setFixedLegNpv(fixedLegNpv);
        setFixedCoupons(fixedCoupons);
        /*
        for (Coupon c : fixedCoupons)
        {
        	System.out.println(String.format("Date: %tD, Rate: %.6f, Cash flow: %.2f", c.getEndDate(), c.getForwardRate(), c.getCashFlow()));
        }
        */

        //System.out.println(String.format("Calculated fixed rate: %.6f", fixedRate));
        setCalculatedFixedRate(fixedRate);
	}
	
	public void setZcCurve(ArrayList<ZeroCouponDataPoint> curve)
	{
		_zcCurve = curve;
	}
	
	public ArrayList<ZeroCouponDataPoint> getZcCurve()
	{
		return _zcCurve;
	}
	
	public void setMaturityDate(String matdate)
	{
		_maturityDate = matdate;
	}
	
	public String getMaturityDate()
	{
		return _maturityDate;
	}
	
	public void setCalculatedFixedRate(double rate)
	{
		_calculatedFixedRate = rate;
	}
	
	public String getCalculatedFixedRate()
	{
		return String.format("%.4f", _calculatedFixedRate * 100);
	}
	
	public void setFixedLegNpv(double npv)
	{
		_fixedLegNpv = npv;
	}
	
	public String getFloatLegNpv()
	{
		return String.format("%.2f", _floatLegNpv);
	}
	
	public void setFloatLegNpv(double npv)
	{
		_floatLegNpv = npv;
	}
	
	public String getFixedLegNpv()
	{
		return String.format("%.2f", _fixedLegNpv);
	}
	
	public void setFixedCoupons(ArrayList<Coupon> coupons)
	{
		_fixedCoupons = coupons;
	}
	
	public ArrayList<Coupon> getFixedCoupons()
	{
		return _fixedCoupons;
	}
	
	public void setFloatCoupons(ArrayList<Coupon> coupons)
	{
		_floatCoupons = coupons;
	}
	
	public ArrayList<Coupon> getFloatCoupons()
	{
		return _floatCoupons;
	}

}
