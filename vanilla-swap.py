# Vanilla swap pricing
from datetime import date
from datetime import datetime
from datetime import timedelta
from dateutil.relativedelta import relativedelta
from operator import attrgetter
from scipy.interpolate import CubicSpline
from matplotlib.finance import date2num
import marketdata
import datetools

DATE_FORMAT = '%d/%m/%Y'

# Coupon frequencies
frequency_dict = {
    'A' : 12,
    'S' : 6,
    'Q' : 3,
    'M' : 1
}

# Zero Coupon curve data point
class ZeroCouponDataPoint:
    'Object representing each point on a discount curve'
    def __init__(self, value_date, period, df):
        self.ValueDate = value_date
        self.Period = period
        self.DiscountFactor = df
        self.zcDates = calc_zc_dates(self.ValueDate, self.Period)
        self.StartDate = self.zcDates[0]
        self.EndDate = self.zcDates[1]

# Coupon base class
class Coupon:
    'Coupon base class'
    StartDiscountFactor = EndDiscountFactor = 1
    
    def __init__(self, coupon_dates, daycount='ACTACT'):
        self.StartDate = coupon_dates[0]
        self.EndDate = coupon_dates[1]
        self.NbDays = (self.EndDate - self.StartDate).days
        self.daycount = daycount
        self.NbYears = datetools.year_frac(self.StartDate, self.EndDate, self.daycount)
    
    def getDiscountFactor(self, discount_curve):
        dates = [date2num(p.EndDate) for p in discount_curve]
        dfs = [p.DiscountFactor for p in discount_curve]
        cs = CubicSpline(dates, dfs)
        self.StartDiscountFactor = cs(date2num(self.StartDate))
        self.EndDiscountFactor = cs(date2num(self.EndDate))
        
# Calculate coupon dates
def get_coupon_dates(settle, maturity, frequency):
    coupon_dates = maturity - relativedelta(months = frequency), maturity
    while coupon_dates[0] > settle:
        yield Coupon(coupon_dates, daycount='ACT360')
        coupon_dates = coupon_dates[0] - relativedelta(months = frequency), coupon_dates[0] 

# Add period to date
def add_period(start_date, period):
    # Parse period
    num = int(period[:-1])
    unit = period[-1]
    
    # Start date adjustment
    if start_date.weekday() > 4:
        start_date += timedelta(days = 7 - start_date.weekday())
    
    end_date = start_date #initialise
    if unit == 'D':
        end_date = start_date + timedelta(days = num)
    elif unit == 'W':
        end_date = start_date + timedelta(weeks = num)
    elif unit == 'M':
        end_date = start_date + relativedelta(months = num)
    else:   #Assume year
        end_date = start_date + relativedelta(months = 12*num)

    # End date adjustment
    if end_date.weekday() > 4:
        end_date += timedelta(days = 7 - start_date.weekday())
    
    return end_date

# Calculate ZC dates
def calc_zc_dates(value_date, period):
    # initialise
    start_date = end_date = value_date
    
    #Calculate dates (special handling of pre-spot)
    if period == '0D':
        return start_date, start_date
    if period == 'ON':
        return start_date, add_period(value_date, '1D')
    elif period == 'TN':
        start_date = add_period(value_date, '1D')
        return start_date, add_period(start_date, '1D')
    elif period == 'SW':
        start_date = add_period(value_date, '2D')
        return start_date, add_period(start_date, '1W')
    else: # Spot (assume 2 day spot lag)
        start_date = add_period(value_date, '2D')
        return start_date, add_period(start_date, period)
    
# Get settlement date, maturity and coupon frequency
#value_date = input('Please provide valuation date (format: dd/mm/yyyy)>')
value_date = '31/1/2017' #for debugging
#settle = input('Please provide settlement date (format: dd/mm/yyyy)>')
settle = '1/2/2017' #for debugging
#tenor = input('Please provide swap tenor in mumber of years>')
tenor = 5 #for debugging
#frequency = input('Please provide fixed leg coupon frequency (A/S/Q/M)>'), input('Please provide float leg coupon frequency (A/S/Q/M)>')
frequency = ('A', 'S') #for debugging

# Calculate maturity date
value_date = datetime.strptime(value_date, DATE_FORMAT)
settle = datetime.strptime(settle, DATE_FORMAT)
maturity = add_period(settle, str(tenor) + 'Y')
print('Calculated maturity: ' + datetime.strftime(maturity, DATE_FORMAT))

# Calculate coupon dates
print('Fixed leg coupon dates:')
fixed_coupons = list(get_coupon_dates(settle, maturity, frequency_dict[frequency[0]]))[::-1]
for d in fixed_coupons:
    print('Start: {!s}, End: {!s}, Years: {!s}'.format(datetime.strftime(d.StartDate,DATE_FORMAT), datetime.strftime(d.EndDate,DATE_FORMAT), d.NbYears))

print('Float leg coupon dates:')
float_coupons = list(get_coupon_dates(settle, maturity, frequency_dict[frequency[1]]))[::-1]
for d in float_coupons:
    print('Start: {!s}, End: {!s}, Years: {!s}'.format(datetime.strftime(d.StartDate,DATE_FORMAT), datetime.strftime(d.EndDate,DATE_FORMAT), d.NbYears))

# Generate discount curve
print('Discount curve (from file)')
zc_curve = [ZeroCouponDataPoint(value_date, k, v) for k, v in marketdata.discount_curve.items()]
zc_curve.sort(key=attrgetter('EndDate'))
for p in zc_curve:
    print('Period: {}, Start: {!s}, End: {!s}, DF: {!s}'.format(p.Period, datetime.strftime(p.StartDate,DATE_FORMAT), datetime.strftime(p.EndDate,DATE_FORMAT), p.DiscountFactor))

# Interpolate discount factors
print('Interpolate discount factors for each fixed coupon')
[c.getDiscountFactor(zc_curve) for c in fixed_coupons]
for c in fixed_coupons:
    print('Date: {!s}, Discount Factor: {!s}'.format(datetime.strftime(c.EndDate,DATE_FORMAT), c.EndDiscountFactor))
    
print('Interpolate discount factors for each float coupon')
[c.getDiscountFactor(zc_curve) for c in float_coupons]
for c in float_coupons:
    print('Date: {!s}, Discount Factor: {!s}'.format(datetime.strftime(c.EndDate,DATE_FORMAT), c.EndDiscountFactor))
    