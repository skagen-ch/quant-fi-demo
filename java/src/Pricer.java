import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface Pricer {
	public void calculate(String marketDataPath) throws FileNotFoundException, NumberFormatException, IOException;
	
	public void setValuationDate(String valuationDate);
	
	public Date getValuationDate();
	
	public void setSettlementDate(String settlementDate);
	
	public Date getSettlementDate();
	
	public void setFixedFrequency(String frq);
	
	public Frequency getFixedFrequency();
	
	public void setFloatFrequency(String frq);
	
	public Frequency getFloatFrequency();
	
	public void setNotional(String notional);

	public double getNotional();

	public void setTenor(String tenor);
	
	public String getTenor();
	
	public void setCurrentFloat(String rate);

	public double getCurrentFloat();

	public void setSpread(String spread);

	public double getSpread();
	
	public void setNpv(String npv);	
	
	public double getNpv();	
	
	public void setZcCurve(ArrayList<ZeroCouponDataPoint> curve);
	
	public ArrayList<ZeroCouponDataPoint> getZcCurve();
	
	public void setMaturityDate(String matdate);
	
	public String getMaturityDate();
	
	public void setCalculatedFixedRate(double rate);
	
	public String getCalculatedFixedRate();
	
	public void setFixedLegNpv(double npv);
	
	public String getFloatLegNpv();
	
	public void setFloatLegNpv(double npv);
	
	public String getFixedLegNpv();
	
	public void setFixedCoupons(ArrayList<Coupon> coupons);
	
	public ArrayList<Coupon> getFixedCoupons();
	
	public void setFloatCoupons(ArrayList<Coupon> coupons);
	
	public ArrayList<Coupon> getFloatCoupons();
	
	public void registerObserver(PricerObserver o);
	
	public void removeObserver(PricerObserver o);
}
