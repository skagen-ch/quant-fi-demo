import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public abstract class CouponList {
	protected ArrayList<Coupon> couponList;
	
	final void generateCoupons(Date settlementDate, Date maturityDate, Frequency frequency, double notional, Double fixedRate, Double currentFloat, Double spread, ArrayList<ZeroCouponDataPoint> zcCurve) {
		generateCouponDates(settlementDate, maturityDate, frequency);
		sortCouponDates();
		calculateDiscountFactors(zcCurve);
		setFixedRate(fixedRate);
		setCurrentFloatRate(currentFloat);
		calculateCashFlow(notional, spread);
		calculateFinalCashFlow(notional, spread);
	}
	
	private void generateCouponDates(Date settlementDate, Date maturityDate, Frequency frequency) {
		couponList = DateUtils.GetCouponDates(settlementDate, maturityDate, frequency);
	}

	private void sortCouponDates() {
		couponList.sort((x, y) -> x.getEndDate().compareTo(y.getEndDate()));
	}

	private void calculateDiscountFactors(ArrayList<ZeroCouponDataPoint> zcCurve) {
		couponList.forEach(x -> x.CalculateDiscountFactor(zcCurve));
	}
	
	abstract void setFixedRate(Double fixedRate);
	
	abstract void setCurrentFloatRate(Double currentFloat);

	private void calculateCashFlow(double notional, Double spread) {
		couponList.forEach(x -> x.CalculateCashFlow(notional, spread == null ? 0 : spread));
	}
	
	private void calculateFinalCashFlow(double notional, Double spread) {
		couponList.get(couponList.size() - 1).CalculateCashFlow(notional, spread == null ? 0 : spread, true);
	}
	
	public double getLegNpv() {
		return couponList.stream().mapToDouble(x -> x.getCashFlow() * x.getEndDiscountFactor()).sum();
	}
	
	public Iterator<Coupon> coupons() {
		return couponList.iterator();
	}
}
