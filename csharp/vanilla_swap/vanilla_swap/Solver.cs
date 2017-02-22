using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using mn = MathNet.Numerics;

namespace vanilla_swap
{
    public static class Solver
    {
        public static double Solve(double notional, IList<Coupon> coupons, double targetNpv)
        {
            return mn.RootFinding.RobustNewtonRaphson.FindRoot(
                r => 
                    targetNpv - (coupons.Sum(x => notional * r * x.NbYears * x.EndDiscountFactor) + (notional * coupons.Last().NbYears * coupons.Last().EndDiscountFactor)),
                r =>
                    -(coupons.Sum(x => notional * x.NbYears * x.EndDiscountFactor)),
                -1, 1);
        }
    }
}
