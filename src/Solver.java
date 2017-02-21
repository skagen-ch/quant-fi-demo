import java.util.ArrayList;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;

public final class Solver {
	private Solver(){}
	
    public static double Solve(double n, ArrayList<Coupon> c, double tnpv)
    {
		final double target = (n * c.get(c.size()-1).getNbYears() * c.get(c.size()-1).getEndDiscountFactor()) + tnpv;
		final double df = -c.stream().mapToDouble(x -> n * x.getNbYears() * x.getEndDiscountFactor()).sum();
		
		NewtonRaphsonSolver solver = new NewtonRaphsonSolver();
    	
    	UnivariateDifferentiableFunction func = new UnivariateDifferentiableFunction()
		{
			@Override
    		public double value(double r)
    		{
				double legNpv = -target;
				for (Coupon c : c)
				{
					legNpv += r * n * c.getNbYears() * c.getEndDiscountFactor();
				}
    			return -legNpv;
    		}

			@Override
			public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
				DerivativeStructure legNpv = new DerivativeStructure(1, 1, -target);
				for (Coupon c : c)
				{
					legNpv = (t.multiply(n).multiply(c.getNbYears()).multiply(c.getEndDiscountFactor())).add(legNpv);
				}
    			return legNpv.negate();
			}
		};
    	
    	return solver.solve(100, func, -1.0, 1.0);
    }
    
}
