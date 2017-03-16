import java.util.Iterator;

public final class ClosedSolver implements Solver {
	
    public double Solve(double notional, Iterator<Coupon> fixedCoupons, Iterator<Coupon> floatingCoupons, double targetNpv)
    {
    	double initialGuess = 0.05;
    	
    	double discountFactorAtMaturity;
    	double discountedNotional = 0;
    	
    	double legNpv = 0.0;
		double derivativeValue = 0.0;
		while (fixedCoupons.hasNext()) {
			Coupon c = fixedCoupons.next();
			legNpv += initialGuess * notional * c.getNbYears() * c.getEndDiscountFactor();
			derivativeValue += notional * c.getNbYears() * c.getEndDiscountFactor();
			
			if (!fixedCoupons.hasNext()) {
				discountFactorAtMaturity = c.getEndDiscountFactor();
				discountedNotional = notional * discountFactorAtMaturity;
			}
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
