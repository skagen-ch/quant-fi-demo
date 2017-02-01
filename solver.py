"""
Newton-Raphson solver to find fixed rate
"""
from scipy import optimize

def npv(couponRate, notional, coupons, targetNpv):
    legNpv = sum([notional * couponRate * cf.NbYears * cf.EndDiscountFactor for cf in coupons[0:-1]])
    legNpv += (notional * ((1 + couponRate) * coupons[-1].NbYears) * coupons[-1].EndDiscountFactor)
    return targetNpv - legNpv
    
def solve(notional, coupons, targetNpv):
    x0 = 0.05
    return optimize.newton(npv, x0, args=(notional, coupons, targetNpv))