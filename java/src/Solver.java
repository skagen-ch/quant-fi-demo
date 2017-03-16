import java.util.Iterator;

public interface Solver {
	public double Solve(double notional, Iterator<Coupon> fixedCoupons, Iterator<Coupon> floatingCoupons, double targetNpv);
}
