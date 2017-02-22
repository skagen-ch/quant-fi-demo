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

	public static void main(String[] args) throws java.text.ParseException, NumberFormatException, IOException {
		Scanner input = new Scanner(System.in);
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
        Date valueDate = null;
        Date settle = null;
        
        System.out.print("Please provide valuation date (format: dd/mm/yyyy)>");
        String dateAsString = input.next();
        try
        {
        	valueDate = dateFormat.parse(dateAsString);
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }
        
        System.out.print("Please provide settlement date (format: dd/mm/yyyy)>");
        dateAsString = input.next();
        try
        {
        	settle = dateFormat.parse(dateAsString);
        }
        catch (ParseException e)
        {
        	e.printStackTrace();
        }

        System.out.print("Please provide fixed leg coupon frequency (A/S/Q/M)>");
        String fixFrq = input.next();
        System.out.print("Please provide float leg coupon frequency (A/S/Q/M)>");
        String floatFrq = input.next();
        System.out.print("Please provide the notional value>");
        double notional = input.nextDouble();
        System.out.print("Please provide swap tenor in number of years>");
        String tenor = input.next();
        System.out.print("Please provide the current index value>");
        double currentFloat = input.nextDouble();
        System.out.print("Please provide the bp spread over the index rate>");
        double spread = input.nextDouble()/10000;
        System.out.print("Please provide the swap NPV>");
        double npv = input.nextDouble();
        System.out.println();
        input.close();
        
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
        System.out.println("Calculated maturity: " + dateFormat.format(maturity));

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

        System.out.println();
        // Read discount curve from file
        System.out.println("Discount curve (from file)");
        URL url = VanillaPricer.class.getResource("marketdata.txt");
        FileReader f = new FileReader(url.getPath());
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
        for (ZeroCouponDataPoint p : zcCurve)
        {
            System.out.println(String.format("Period: %s, Date: %tD, DF: %.6f", p.getPeriod(), p.getEndDate(), p.getDiscountFactor()));
        }

        System.out.println();
        // Calculate coupon rates
        System.out.println("Calculate floating cash flows");
        floatCoupons.forEach(x -> x.CalculateDiscountFactor(zcCurve));
        // Set current float
        if (currentFloat > 0)
        {
            floatCoupons.get(0).setForwardRate(currentFloat);
        }
        floatCoupons.forEach(x -> x.CalculateCashFlow(notional, spread));
        // Re-calculate final cash flow (with redemption)
        floatCoupons.get(floatCoupons.size() - 1).CalculateCashFlow(notional, spread, true);
        for (Coupon c : floatCoupons)
        {
            System.out.println(String.format("Date: %tD, Rate: %.6f, Cash flow: %.2f", c.getEndDate(), c.getForwardRate(), c.getCashFlow()));
        }

        System.out.println();
        // Calculate float leg NPV
        double floatLegNpv = floatCoupons.stream().mapToDouble(x -> x.getCashFlow() * x.getEndDiscountFactor()).sum();
        double targetLegNpv = floatLegNpv - npv;
        System.out.println(String.format("Float leg NPV = %1$.2f, Target leg NPV = %2$.2f", floatLegNpv, targetLegNpv));

        System.out.println();
        // Solve for coupon rate
        System.out.println("Calculate fixed cash flows");
        fixedCoupons.forEach(x -> x.CalculateDiscountFactor(zcCurve));
        double fixedRate = Solver.Solve(notional, fixedCoupons, targetLegNpv);
        fixedCoupons.forEach(x -> x.setForwardRate(fixedRate));
        fixedCoupons.forEach(x -> x.CalculateCashFlow(notional));
        // Re-calculate final cash flow (with redemption)
        fixedCoupons.get(fixedCoupons.size() - 1).CalculateCashFlow(notional, 0, true);
        for (Coupon c : fixedCoupons)
        {
        	System.out.println(String.format("Date: %tD, Rate: %.6f, Cash flow: %.2f", c.getEndDate(), c.getForwardRate(), c.getCashFlow()));
        }

        System.out.println();
        System.out.println(String.format("Calculated fixed rate: %.6f", fixedRate));
	}

}
