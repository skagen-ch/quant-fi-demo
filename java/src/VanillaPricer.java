// VanillaPricer.java
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.TimeZone;

public class VanillaPricer implements Pricer {
	private ArrayList<PricerObserver> observers = new ArrayList<PricerObserver>();
	private ArrayList<ZeroCouponDataPoint> _zcCurve;
	private Date valuationDate;
	private Date settle;
	private String fixFrq;
	private String floatFrq;
	private double notional;
	private String tenor;
	private double currentFloat;
	private double spread;
	private double npv;
	private String _maturityDate;
	private Double _calculatedFixedRate;
	private Double _fixedLegNpv;
	private Double _floatLegNpv;
	private Iterator<Coupon> _fixedCoupons;
	private Iterator<Coupon> _floatCoupons;
	private Solver solver;
	TimeZone tz	= TimeZone.getTimeZone("UTC");
	SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
	
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
	
	public VanillaPricer() {
		solver = new ClosedSolver();
		dateFormat.setTimeZone(tz);
	}
	
	public void calculate(String marketDataPath) throws NumberFormatException, IOException {
    	Date valueDate = getValuationDate();
        Date settle = getSettlementDate();
        Frequency fixFrq = getFixedFrequency();
        Frequency floatFrq = getFloatFrequency();
        double notional = getNotional();
        String tenor = getTenor();
        double currentFloat = getCurrentFloat();
        double spread = getSpread();
        double npv = getNpv();
        
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

        // Read discount curve from file
        MarketDataReaderSingleton mdr = MarketDataReaderSingleton.getInstance(marketDataPath);
        Hashtable<String, Double> discountCurve = new Hashtable<String, Double>();
        String line = "";
        while ((line = mdr.readLine()) != null)
        {
        	String point[] = line.split("\t");
        	discountCurve.put(point[0], Double.parseDouble(point[1]));
        }
        mdr.close();
        
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

        // Calculate coupon dates
        CouponList floatCoupons = new FloatingCouponList();
        floatCoupons.generateCoupons(settle, maturity, floatFrq, notional, null, currentFloat, spread, zcCurve);
        setFloatCoupons(floatCoupons.coupons());
        double floatLegNpv = floatCoupons.getLegNpv();
        setFloatLegNpv(floatLegNpv);
        double targetLegNpv = floatLegNpv - npv;
        
        CouponList fixedCoupons = new FixedCouponList();
        fixedCoupons.generateCoupons(settle, maturity, fixFrq, notional, null, null, null, zcCurve);
        // Solve for coupon rate
        double fixedRate = solver.Solve(notional, fixedCoupons.coupons(), floatCoupons.coupons(), targetLegNpv);
        fixedCoupons.generateCoupons(settle, maturity, fixFrq, notional, fixedRate, null, null, zcCurve);
        setFixedLegNpv(fixedCoupons.getLegNpv());
        setFixedCoupons(fixedCoupons.coupons());
       
        setCalculatedFixedRate(fixedRate);
        notifyObservers();
	}

	//GETTERS AND SETTERS
	
	@Override
	public Date getValuationDate() {
		return valuationDate;
	}

	@Override
	public void setValuationDate(String valuationDate) {
        try
        {
        	this.valuationDate = dateFormat.parse(valuationDate);
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }
	}

	@Override
	public Date getSettlementDate() {
		return settle;
	}

	@Override
	public void setSettlementDate(String settle) {
        try
        {
        	this.settle = dateFormat.parse(settle);
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }
	}
	
	@Override
	public void setFixedFrequency(String frq)
	{
		fixFrq = frq;
	}
	
	@Override
	public Frequency getFixedFrequency()
	{
		return frqDict.get(fixFrq);
	}
	
	@Override
	public void setFloatFrequency(String frq)
	{
		floatFrq = frq;
	}
	
	@Override
	public Frequency getFloatFrequency()
	{
		return frqDict.get(floatFrq);
	}

	@Override
	public double getNotional() {
		return notional;
	}

	@Override
	public void setNotional(String notional) {
		this.notional = Double.parseDouble(notional);;
	}

	@Override
	public String getTenor() {
		return tenor;
	}

	@Override
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	@Override
	public void setCurrentFloat(String rate) {
		this.currentFloat = Double.parseDouble(rate);
	}

	@Override
	public double getCurrentFloat() {
		return this.currentFloat;
	}

	@Override
	public void setSpread(String spread) {
		this.spread = Double.parseDouble(spread)/10000.0;
	}

	@Override
	public double getSpread() {
		return this.spread;
	}

	@Override
	public void setNpv(String npv) {
		this.npv = Double.parseDouble(npv);
	}

	@Override
	public double getNpv() {
		return this.npv;
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
	
	public void setFixedCoupons(Iterator<Coupon> coupons)
	{
		_fixedCoupons = coupons;
	}
	
	public Iterator<Coupon> getFixedCoupons()
	{
		return _fixedCoupons;
	}
	
	public void setFloatCoupons(Iterator<Coupon> coupons)
	{
		_floatCoupons = coupons;
	}
	
	public Iterator<Coupon> getFloatCoupons()
	{
		return _floatCoupons;
	}
	
	//OBSERVER PATTERN IMPLEMENTATION
	
	public void registerObserver(PricerObserver o) {
		observers.add(o);
	}

	public void removeObserver(PricerObserver o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers() {
		for (PricerObserver observer : observers) {
			observer.update();
		}
	}

}
