"""
Module for performing various date calculations using relevant market conventions
"""
from datetime import date
from datetime import timedelta
from dateutil.relativedelta import relativedelta

def year_frac(start_date, end_date, convention = 'ACTACT'):
    'calculates the number of years as a fraction, using the given daycount convention'
    year_basis = 365
    thirty_day_month = is_feb = False
    
    if convention == 'ACT360':
        year_basis = 360
    elif convention == 'ACT365':
        pass
    elif convention == '30360':
        thirty_day_month = True
        year_basis = 360
    else: # assume ACT/ACT
        one_year_date = start_date + relativedelta(months=12)
        year_basis = (one_year_date - start_date).days
        
    # Find number of whole years
    whole_years = relativedelta(end_date, start_date).years
    if whole_years > 0:
        start_date = start_date + relativedelta(months=whole_years*12)
    
    # Loop through months
    total_number_of_days = 0
    while start_date <= end_date:
        # If end date is not in same month, then find last day
        if start_date.month != end_date.month:
            month_start_date = start_date - timedelta(days=start_date.day - 1)
            month_end_date = month_start_date + relativedelta(months=1)
        else:
            month_end_date = end_date #- timedelta(days=1)
        
        # Find actual number of days in month or partial month
        actual_number_of_days = (month_end_date - start_date).days
        
        # Increment total by number of days in (partial) month following convention
        if thirty_day_month:
            is_feb = start_date.month == 2
            if is_feb and actual_number_of_days >= 28:
                total_number_of_days += 30
            else:
                total_number_of_days += min(actual_number_of_days, 30)
        else:
            total_number_of_days += actual_number_of_days
        
        # Increment start date and repeat
        start_date = start_date + relativedelta(months=1)
        
    return whole_years + (total_number_of_days/year_basis)

# Unit test
assert year_frac(date(2017,1,1), date(2018,1,1), 'ACTACT') == 1, 'Calculation failed for 1Y Act/Act'
assert year_frac(date(2017,1,1), date(2018,1,1), 'ACT360') == 1, 'Calculation failed for 1Y Act/360'
assert year_frac(date(2017,1,1), date(2018,1,1), 'ACT365') == 1, 'Calculation failed for 1Y Act/365'
assert year_frac(date(2017,1,1), date(2018,1,1), '30360') == 1, 'Calculation failed for 1Y 30/360'
assert year_frac(date(2017,1,1), date(2018,6,1), 'ACTACT') - 1.41369986 < 0.0000001, 'Calculation failed for 1Y6M Act/Act'
assert year_frac(date(2017,1,1), date(2018,6,1), 'ACT360') - 1.43333333 < 0.0000001, 'Calculation failed for 1Y6M Act/360'
assert year_frac(date(2017,1,1), date(2018,6,1), 'ACT365') - 1.41369986 < 0.0000001, 'Calculation failed for 1Y6M Act/365'
assert year_frac(date(2017,1,1), date(2018,6,1), '30360') - 1.41666667 < 0.0000001, 'Calculation failed for 1Y6M 30/360'