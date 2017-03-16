import java.util.ArrayList;

public interface Solver {
	public double Solve(double notional, ArrayList<Coupon> fixedCoupons, ArrayList<Coupon> floatingCoupons, double targetNpv);
}
