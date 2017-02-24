import java.util.ArrayList;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;

public final class Solver {
	private Solver(){}
	
    public static double Solve(double notional, ArrayList<Coupon> fixedCoupons, ArrayList<Coupon> floatingCoupons, double targetNpv)
    {
    	double initialGuess = 0.05;
    	
    	final double discountFactorAtMaturity = fixedCoupons.get(fixedCoupons.size()-1).getEndDiscountFactor();
    	final double discountedNotional = notional * discountFactorAtMaturity;
    	
    	double legNpv = 0.0;
		double derivativeValue = 0.0; 
		for (Coupon c : fixedCoupons)
		{
			legNpv += initialGuess * notional * c.getNbYears() * c.getEndDiscountFactor();
			derivativeValue += notional * c.getNbYears() * c.getEndDiscountFactor();
		}
		legNpv += discountedNotional;

		double delta = legNpv - targetNpv;
    	return initialGuess - (delta/derivativeValue);
		
		/*
		NewtonRaphsonSolver solver = new NewtonRaphsonSolver();
    	
    	UnivariateDifferentiableFunction func = new UnivariateDifferentiableFunction()
		{
			@Override
    		public double value(double r)
    		{
				double legNpv = 0.0;
				for (Coupon c : fixedCoupons)
				{
					legNpv += r * notional * c.getNbYears() * c.getEndDiscountFactor();
				}
				legNpv += discountedNotional;
    			return (legNpv - targetNpv);
    		}

			@Override
			public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
				DerivativeStructure legNpv = new DerivativeStructure(1, 1, 0.0);
				for (Coupon c : fixedCoupons)
				{
					legNpv += t.multiply(notional).multiply(c.getNbYears()).multiply(c.getEndDiscountFactor());
				}
				legNpv += discountedNotional;
    			return (legNpv - targetNpv);
			}
		};
    	
    	return solver.solve(100, func, 0.05);
    	*/
    }
    
}
