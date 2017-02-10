using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using ns = Newtonsoft.Json;

namespace vanilla_swap
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Please provide valuation date (format: dd/mm/yyyy)>");
            var valueDate = DateTime.Parse(Console.ReadLine());
            Console.Write("Please provide settlement date (format: dd/mm/yyyy)>");
            var settle = DateTime.Parse(Console.ReadLine());
            Console.Write("Please provide fixed leg coupon frequency (A/S/Q/M)>");
            var fixFrq = Console.ReadLine();
            Console.Write("Please provide float leg coupon frequency (A/S/Q/M)>");
            var floatFrq = Console.ReadLine();
            Console.Write("Please provide the notional value>");
            var notional = double.Parse(Console.ReadLine());
            Console.Write("Please provide swap tenor in number of years>");
            var tenor = int.Parse(Console.ReadLine());
            Console.Write("Please provide the current index value>");
            var currentFloat = double.Parse(Console.ReadLine());
            Console.Write("Please provide the bp spread over the index rate>");
            var spread = double.Parse(Console.ReadLine())/10000;
            Console.Write("Please provide the swap NPV>");
            var npv = double.Parse(Console.ReadLine());
            Console.WriteLine();

            // Calculate maturity date
            var maturity = DateUtils.AddPeriod(settle, tenor.ToString() + "Y");
            Console.WriteLine("Calculated maturity: " + maturity.ToShortDateString());

            // Calculate coupon dates
            var frqDict = new Dictionary<string, Frequency>
            {
                {"A", Frequency.Annual },
                {"S", Frequency.SemiAnnual},
                {"Q", Frequency.Quarterly },
                {"M", Frequency.Monthly }
            };
            var fixedCoupons = DateUtils.GetCouponDates(settle, maturity, frqDict[fixFrq]).ToList();
            var floatCoupons = DateUtils.GetCouponDates(settle, maturity, frqDict[floatFrq]).ToList();

            foreach (var cpn in floatCoupons)
            {
                Console.WriteLine(string.Format("Start: {0}, End: {1}", cpn.StartDate, cpn.EndDate));
            }

            Console.WriteLine();
            // Read discount curve from file
            Console.WriteLine("Discount curve (from file)");
            Dictionary<string, double> discountCurve = ns.JsonConvert.DeserializeObject<Dictionary<string, double>>(File.ReadAllText(@"..\..\marketdata.json"));
            var zcCurve = discountCurve.Select(kv => new ZeroCouponDataPoint(valueDate, kv.Key, kv.Value)).ToList();
            zcCurve.Sort(new Comparison<ZeroCouponDataPoint>((x, y) => DateTime.Compare(x.EndDate, y.EndDate)));
            foreach (var p in zcCurve)
            {
                Console.WriteLine(string.Format("Period: {0}, Date: {1}, DF: {2}", p.Period, p.EndDate.ToShortDateString(), p.DiscountFactor));
            }

            Console.WriteLine();
            // Calculate coupon rates
            Console.WriteLine("Calculate floating cash flows");
            floatCoupons.ForEach(x => x.CalculateDiscountFactor(zcCurve));
            // Set current float
            if (currentFloat > 0)
            {
                floatCoupons.First().ForwardRate = currentFloat;
            }
            floatCoupons.ForEach(x => x.CalculateCashFlow(notional, spread));
            // Re-calculate final cash flow (with redemption)
            floatCoupons.Last().CalculateCashFlow(notional, spread, true);
            foreach (var c in floatCoupons)
            {
                Console.WriteLine(string.Format("Date: {0}, Rate: {1}, Cash flow: {2}", c.EndDate.ToShortDateString(), c.ForwardRate, c.CashFlow));
            }

            Console.WriteLine();
            // Calculate float leg NPV
            var floatLegNpv = floatCoupons.Sum(x => x.CashFlow * x.EndDiscountFactor);
            var targetLegNpv = floatLegNpv - npv;
            Console.WriteLine(string.Format("Float leg NPV = {0}, Target leg NPV = {1}", floatLegNpv, targetLegNpv));

            Console.WriteLine();
            // Solve for coupon rate
            Console.WriteLine("Calculate fixed cash flows");
            fixedCoupons.ForEach(x => x.CalculateDiscountFactor(zcCurve));
            var fixedRate = Solver.Solve(notional, fixedCoupons, targetLegNpv);
            fixedCoupons.ForEach(x => x.ForwardRate = fixedRate);
            fixedCoupons.ForEach(x => x.CalculateCashFlow(notional));
            // Re-calculate final cash flow (with redemption)
            fixedCoupons.Last().CalculateCashFlow(notional, 0, true);
            foreach (var c in fixedCoupons)
            {
                Console.WriteLine(string.Format("Date: {0}, Rate: {1}, Cash flow: {2}", c.EndDate.ToShortDateString(), c.ForwardRate, c.CashFlow));
            }

            Console.WriteLine();
            Console.WriteLine(string.Format("Calculated fixed rate: {0}", fixedRate));

            Console.ReadKey();
        }
    }
}
