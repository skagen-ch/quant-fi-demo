using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;
using System.Windows.Forms;

namespace VanillaSwap
{
    public partial class VanillaPricerGui : Form
    {
        Pricer pricer;
        public VanillaPricerGui()
        {
            InitializeComponent();
            setDefaultValues();
            pricer = new Pricer();
        }

        private void setDefaultValues()
        {
            DateTime todayDate = DateTime.Today;
            DateTime defaultSettle = DateUtils.AddPeriod(todayDate, "2D");
            dtpValuationDate.Value = todayDate;
            dtpSettle.Value = defaultSettle;
            cboFixFrq.SelectedItem = "Annual";
            cboFloatFrq.SelectedItem = "Semi-annual";
        }

        [STAThreadAttribute]
        private void btnCalculate_Click(object sender, EventArgs e)
        {
            string pathToMarketData = "marketdata.json";
            OpenFileDialog fd = new OpenFileDialog();
            if (fd.ShowDialog() == DialogResult.OK)
            {
                pathToMarketData = fd.FileName;
            }

            string[] args = new[]
            {
                dtpValuationDate.Value.ToShortDateString(),
                dtpSettle.Value.ToShortDateString(),
                cboFixFrq.SelectedItem.ToString().Substring(0,1),
                cboFloatFrq.SelectedItem.ToString().Substring(0,1),
                txtNotional.Text,
                txtTenor.Text,
                txtCurFloat.Text,
                txtSpread.Text,
                txtNpv.Text,
                pathToMarketData
            };
            pricer.Calculate(args);
            txtMaturity.Text = pricer.MaturityDate;
            txtFloatLegNpv.Text = pricer.FloatLegNpv.ToString("N2", CultureInfo.InvariantCulture);
            txtFixedRate.Text = pricer.CalculatedFixedRate.ToString("F4", CultureInfo.InvariantCulture);
            txtFixLegNpv.Text = pricer.FixedLegNpv.ToString("N2", CultureInfo.InvariantCulture);
        }
    }
}
