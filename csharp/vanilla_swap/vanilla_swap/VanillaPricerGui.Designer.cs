namespace VanillaSwap
{
    partial class VanillaPricerGui
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tabContainer = new System.Windows.Forms.TabControl();
            this.tabMain = new System.Windows.Forms.TabPage();
            this.txtMaturity = new System.Windows.Forms.TextBox();
            this.btnCalculate = new System.Windows.Forms.Button();
            this.txtFloatLegNpv = new System.Windows.Forms.TextBox();
            this.txtFixLegNpv = new System.Windows.Forms.TextBox();
            this.txtSpread = new System.Windows.Forms.TextBox();
            this.txtCurFloat = new System.Windows.Forms.TextBox();
            this.txtFixedRate = new System.Windows.Forms.TextBox();
            this.cboFloatFrq = new System.Windows.Forms.ComboBox();
            this.cboFixFrq = new System.Windows.Forms.ComboBox();
            this.txtNpv = new System.Windows.Forms.TextBox();
            this.txtNotional = new System.Windows.Forms.TextBox();
            this.txtTenor = new System.Windows.Forms.TextBox();
            this.dtpSettle = new System.Windows.Forms.DateTimePicker();
            this.dtpValuationDate = new System.Windows.Forms.DateTimePicker();
            this.lblLegNpv = new System.Windows.Forms.Label();
            this.lblFixedRate = new System.Windows.Forms.Label();
            this.lblSpread = new System.Windows.Forms.Label();
            this.lblCurFloat = new System.Windows.Forms.Label();
            this.lblFloat = new System.Windows.Forms.Label();
            this.lblFixed = new System.Windows.Forms.Label();
            this.lblFrequency = new System.Windows.Forms.Label();
            this.lblNpv = new System.Windows.Forms.Label();
            this.lblNotional = new System.Windows.Forms.Label();
            this.lblMaturity = new System.Windows.Forms.Label();
            this.lblTenor = new System.Windows.Forms.Label();
            this.lblSettle = new System.Windows.Forms.Label();
            this.lblValuationDate = new System.Windows.Forms.Label();
            this.tabCashFlows = new System.Windows.Forms.TabPage();
            this.lvFloatingCashFlows = new System.Windows.Forms.ListView();
            this.colhFltDate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colhFltRate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colhFltCashFlow = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.lvFixedCashFlows = new System.Windows.Forms.ListView();
            this.colhFixDate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colhFixRate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colhFixCashFlow = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.tabZcCurve = new System.Windows.Forms.TabPage();
            this.lvZcCurve = new System.Windows.Forms.ListView();
            this.colhZcPeriod = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colhZcDate = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colhDiscountFactor = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.tabContainer.SuspendLayout();
            this.tabMain.SuspendLayout();
            this.tabCashFlows.SuspendLayout();
            this.tabZcCurve.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabContainer
            // 
            this.tabContainer.Controls.Add(this.tabMain);
            this.tabContainer.Controls.Add(this.tabCashFlows);
            this.tabContainer.Controls.Add(this.tabZcCurve);
            this.tabContainer.Location = new System.Drawing.Point(0, 0);
            this.tabContainer.Name = "tabContainer";
            this.tabContainer.SelectedIndex = 0;
            this.tabContainer.Size = new System.Drawing.Size(704, 524);
            this.tabContainer.TabIndex = 0;
            // 
            // tabMain
            // 
            this.tabMain.Controls.Add(this.txtMaturity);
            this.tabMain.Controls.Add(this.btnCalculate);
            this.tabMain.Controls.Add(this.txtFloatLegNpv);
            this.tabMain.Controls.Add(this.txtFixLegNpv);
            this.tabMain.Controls.Add(this.txtSpread);
            this.tabMain.Controls.Add(this.txtCurFloat);
            this.tabMain.Controls.Add(this.txtFixedRate);
            this.tabMain.Controls.Add(this.cboFloatFrq);
            this.tabMain.Controls.Add(this.cboFixFrq);
            this.tabMain.Controls.Add(this.txtNpv);
            this.tabMain.Controls.Add(this.txtNotional);
            this.tabMain.Controls.Add(this.txtTenor);
            this.tabMain.Controls.Add(this.dtpSettle);
            this.tabMain.Controls.Add(this.dtpValuationDate);
            this.tabMain.Controls.Add(this.lblLegNpv);
            this.tabMain.Controls.Add(this.lblFixedRate);
            this.tabMain.Controls.Add(this.lblSpread);
            this.tabMain.Controls.Add(this.lblCurFloat);
            this.tabMain.Controls.Add(this.lblFloat);
            this.tabMain.Controls.Add(this.lblFixed);
            this.tabMain.Controls.Add(this.lblFrequency);
            this.tabMain.Controls.Add(this.lblNpv);
            this.tabMain.Controls.Add(this.lblNotional);
            this.tabMain.Controls.Add(this.lblMaturity);
            this.tabMain.Controls.Add(this.lblTenor);
            this.tabMain.Controls.Add(this.lblSettle);
            this.tabMain.Controls.Add(this.lblValuationDate);
            this.tabMain.Location = new System.Drawing.Point(4, 29);
            this.tabMain.Name = "tabMain";
            this.tabMain.Size = new System.Drawing.Size(696, 438);
            this.tabMain.TabIndex = 0;
            this.tabMain.Text = "Main";
            // 
            // txtMaturity
            // 
            this.txtMaturity.Location = new System.Drawing.Point(482, 46);
            this.txtMaturity.Name = "txtMaturity";
            this.txtMaturity.Size = new System.Drawing.Size(200, 26);
            this.txtMaturity.TabIndex = 26;
            // 
            // btnCalculate
            // 
            this.btnCalculate.Location = new System.Drawing.Point(582, 328);
            this.btnCalculate.Name = "btnCalculate";
            this.btnCalculate.Size = new System.Drawing.Size(100, 32);
            this.btnCalculate.TabIndex = 25;
            this.btnCalculate.Text = "Calculate";
            this.btnCalculate.UseVisualStyleBackColor = true;
            this.btnCalculate.Click += new System.EventHandler(this.btnCalculate_Click);
            // 
            // txtFloatLegNpv
            // 
            this.txtFloatLegNpv.Location = new System.Drawing.Point(482, 288);
            this.txtFloatLegNpv.Name = "txtFloatLegNpv";
            this.txtFloatLegNpv.Size = new System.Drawing.Size(200, 26);
            this.txtFloatLegNpv.TabIndex = 24;
            // 
            // txtFixLegNpv
            // 
            this.txtFixLegNpv.Location = new System.Drawing.Point(133, 287);
            this.txtFixLegNpv.Name = "txtFixLegNpv";
            this.txtFixLegNpv.Size = new System.Drawing.Size(200, 26);
            this.txtFixLegNpv.TabIndex = 23;
            // 
            // txtSpread
            // 
            this.txtSpread.Location = new System.Drawing.Point(482, 245);
            this.txtSpread.Name = "txtSpread";
            this.txtSpread.Size = new System.Drawing.Size(200, 26);
            this.txtSpread.TabIndex = 22;
            this.txtSpread.Text = "0";
            // 
            // txtCurFloat
            // 
            this.txtCurFloat.Location = new System.Drawing.Point(482, 202);
            this.txtCurFloat.Name = "txtCurFloat";
            this.txtCurFloat.Size = new System.Drawing.Size(200, 26);
            this.txtCurFloat.TabIndex = 21;
            this.txtCurFloat.Text = "0.024";
            // 
            // txtFixedRate
            // 
            this.txtFixedRate.Location = new System.Drawing.Point(133, 242);
            this.txtFixedRate.Name = "txtFixedRate";
            this.txtFixedRate.Size = new System.Drawing.Size(200, 26);
            this.txtFixedRate.TabIndex = 20;
            // 
            // cboFloatFrq
            // 
            this.cboFloatFrq.FormattingEnabled = true;
            this.cboFloatFrq.Items.AddRange(new object[] {
            "Annual",
            "Semi-annual",
            "Quarterly",
            "Monthly"});
            this.cboFloatFrq.Location = new System.Drawing.Point(482, 154);
            this.cboFloatFrq.Name = "cboFloatFrq";
            this.cboFloatFrq.Size = new System.Drawing.Size(200, 28);
            this.cboFloatFrq.TabIndex = 19;
            // 
            // cboFixFrq
            // 
            this.cboFixFrq.FormattingEnabled = true;
            this.cboFixFrq.Items.AddRange(new object[] {
            "Annual",
            "Semi-annual",
            "Quarterly",
            "Monthly"});
            this.cboFixFrq.Location = new System.Drawing.Point(133, 154);
            this.cboFixFrq.Name = "cboFixFrq";
            this.cboFixFrq.Size = new System.Drawing.Size(200, 28);
            this.cboFixFrq.TabIndex = 18;
            // 
            // txtNpv
            // 
            this.txtNpv.Location = new System.Drawing.Point(482, 81);
            this.txtNpv.Name = "txtNpv";
            this.txtNpv.Size = new System.Drawing.Size(200, 26);
            this.txtNpv.TabIndex = 17;
            this.txtNpv.Text = "0";
            // 
            // txtNotional
            // 
            this.txtNotional.Location = new System.Drawing.Point(133, 81);
            this.txtNotional.Name = "txtNotional";
            this.txtNotional.Size = new System.Drawing.Size(200, 26);
            this.txtNotional.TabIndex = 16;
            this.txtNotional.Text = "10000000";
            // 
            // txtTenor
            // 
            this.txtTenor.Location = new System.Drawing.Point(133, 46);
            this.txtTenor.Name = "txtTenor";
            this.txtTenor.Size = new System.Drawing.Size(200, 26);
            this.txtTenor.TabIndex = 15;
            this.txtTenor.Text = "5";
            // 
            // dtpSettle
            // 
            this.dtpSettle.Location = new System.Drawing.Point(482, 11);
            this.dtpSettle.Name = "dtpSettle";
            this.dtpSettle.Size = new System.Drawing.Size(200, 26);
            this.dtpSettle.TabIndex = 14;
            // 
            // dtpValuationDate
            // 
            this.dtpValuationDate.Location = new System.Drawing.Point(133, 11);
            this.dtpValuationDate.Name = "dtpValuationDate";
            this.dtpValuationDate.Size = new System.Drawing.Size(200, 26);
            this.dtpValuationDate.TabIndex = 13;
            // 
            // lblLegNpv
            // 
            this.lblLegNpv.AutoSize = true;
            this.lblLegNpv.Location = new System.Drawing.Point(53, 290);
            this.lblLegNpv.Name = "lblLegNpv";
            this.lblLegNpv.Size = new System.Drawing.Size(76, 20);
            this.lblLegNpv.TabIndex = 12;
            this.lblLegNpv.Text = "Leg NPV:";
            // 
            // lblFixedRate
            // 
            this.lblFixedRate.AutoSize = true;
            this.lblFixedRate.Location = new System.Drawing.Point(39, 245);
            this.lblFixedRate.Name = "lblFixedRate";
            this.lblFixedRate.Size = new System.Drawing.Size(90, 20);
            this.lblFixedRate.TabIndex = 11;
            this.lblFixedRate.Text = "Fixed Rate:";
            // 
            // lblSpread
            // 
            this.lblSpread.AutoSize = true;
            this.lblSpread.Location = new System.Drawing.Point(411, 248);
            this.lblSpread.Name = "lblSpread";
            this.lblSpread.Size = new System.Drawing.Size(65, 20);
            this.lblSpread.TabIndex = 10;
            this.lblSpread.Text = "Spread:";
            // 
            // lblCurFloat
            // 
            this.lblCurFloat.AutoSize = true;
            this.lblCurFloat.Location = new System.Drawing.Point(367, 203);
            this.lblCurFloat.Name = "lblCurFloat";
            this.lblCurFloat.Size = new System.Drawing.Size(109, 20);
            this.lblCurFloat.TabIndex = 9;
            this.lblCurFloat.Text = "Current Index:";
            // 
            // lblFloat
            // 
            this.lblFloat.AutoSize = true;
            this.lblFloat.Location = new System.Drawing.Point(542, 120);
            this.lblFloat.Name = "lblFloat";
            this.lblFloat.Size = new System.Drawing.Size(66, 20);
            this.lblFloat.TabIndex = 8;
            this.lblFloat.Text = "Floating";
            // 
            // lblFixed
            // 
            this.lblFixed.AutoSize = true;
            this.lblFixed.Location = new System.Drawing.Point(205, 120);
            this.lblFixed.Name = "lblFixed";
            this.lblFixed.Size = new System.Drawing.Size(47, 20);
            this.lblFixed.TabIndex = 7;
            this.lblFixed.Text = "Fixed";
            // 
            // lblFrequency
            // 
            this.lblFrequency.AutoSize = true;
            this.lblFrequency.Location = new System.Drawing.Point(41, 158);
            this.lblFrequency.Name = "lblFrequency";
            this.lblFrequency.Size = new System.Drawing.Size(88, 20);
            this.lblFrequency.TabIndex = 6;
            this.lblFrequency.Text = "Frequency:";
            // 
            // lblNpv
            // 
            this.lblNpv.AutoSize = true;
            this.lblNpv.Location = new System.Drawing.Point(431, 84);
            this.lblNpv.Name = "lblNpv";
            this.lblNpv.Size = new System.Drawing.Size(45, 20);
            this.lblNpv.TabIndex = 5;
            this.lblNpv.Text = "NPV:";
            // 
            // lblNotional
            // 
            this.lblNotional.AutoSize = true;
            this.lblNotional.Location = new System.Drawing.Point(58, 84);
            this.lblNotional.Name = "lblNotional";
            this.lblNotional.Size = new System.Drawing.Size(71, 20);
            this.lblNotional.TabIndex = 4;
            this.lblNotional.Text = "Notional:";
            // 
            // lblMaturity
            // 
            this.lblMaturity.AutoSize = true;
            this.lblMaturity.Location = new System.Drawing.Point(407, 49);
            this.lblMaturity.Name = "lblMaturity";
            this.lblMaturity.Size = new System.Drawing.Size(69, 20);
            this.lblMaturity.TabIndex = 3;
            this.lblMaturity.Text = "Maturity:";
            // 
            // lblTenor
            // 
            this.lblTenor.AutoSize = true;
            this.lblTenor.Location = new System.Drawing.Point(75, 49);
            this.lblTenor.Name = "lblTenor";
            this.lblTenor.Size = new System.Drawing.Size(54, 20);
            this.lblTenor.TabIndex = 2;
            this.lblTenor.Text = "Tenor:";
            // 
            // lblSettle
            // 
            this.lblSettle.AutoSize = true;
            this.lblSettle.Location = new System.Drawing.Point(346, 14);
            this.lblSettle.Name = "lblSettle";
            this.lblSettle.Size = new System.Drawing.Size(130, 20);
            this.lblSettle.TabIndex = 1;
            this.lblSettle.Text = "Settlement Date:";
            // 
            // lblValuationDate
            // 
            this.lblValuationDate.AutoSize = true;
            this.lblValuationDate.Location = new System.Drawing.Point(10, 14);
            this.lblValuationDate.Name = "lblValuationDate";
            this.lblValuationDate.Size = new System.Drawing.Size(119, 20);
            this.lblValuationDate.TabIndex = 0;
            this.lblValuationDate.Text = "Valuation Date:";
            // 
            // tabCashFlows
            // 
            this.tabCashFlows.Controls.Add(this.lvFloatingCashFlows);
            this.tabCashFlows.Controls.Add(this.groupBox2);
            this.tabCashFlows.Controls.Add(this.lvFixedCashFlows);
            this.tabCashFlows.Controls.Add(this.groupBox1);
            this.tabCashFlows.Location = new System.Drawing.Point(4, 29);
            this.tabCashFlows.Name = "tabCashFlows";
            this.tabCashFlows.Size = new System.Drawing.Size(696, 491);
            this.tabCashFlows.TabIndex = 1;
            this.tabCashFlows.Text = "Cash Flows";
            // 
            // lvFloatingCashFlows
            // 
            this.lvFloatingCashFlows.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colhFltDate,
            this.colhFltRate,
            this.colhFltCashFlow});
            this.lvFloatingCashFlows.Location = new System.Drawing.Point(23, 212);
            this.lvFloatingCashFlows.Name = "lvFloatingCashFlows";
            this.lvFloatingCashFlows.Size = new System.Drawing.Size(654, 266);
            this.lvFloatingCashFlows.TabIndex = 1;
            this.lvFloatingCashFlows.UseCompatibleStateImageBehavior = false;
            this.lvFloatingCashFlows.View = System.Windows.Forms.View.Details;
            // 
            // colhFltDate
            // 
            this.colhFltDate.Text = "Date";
            this.colhFltDate.Width = 124;
            // 
            // colhFltRate
            // 
            this.colhFltRate.Text = "Rate";
            this.colhFltRate.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.colhFltRate.Width = 102;
            // 
            // colhFltCashFlow
            // 
            this.colhFltCashFlow.Text = "Cash Flow";
            this.colhFltCashFlow.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.colhFltCashFlow.Width = 183;
            // 
            // groupBox2
            // 
            this.groupBox2.Location = new System.Drawing.Point(13, 189);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(678, 295);
            this.groupBox2.TabIndex = 3;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Floating Cash Flows";
            // 
            // lvFixedCashFlows
            // 
            this.lvFixedCashFlows.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colhFixDate,
            this.colhFixRate,
            this.colhFixCashFlow});
            this.lvFixedCashFlows.Location = new System.Drawing.Point(23, 30);
            this.lvFixedCashFlows.Name = "lvFixedCashFlows";
            this.lvFixedCashFlows.Size = new System.Drawing.Size(654, 153);
            this.lvFixedCashFlows.TabIndex = 0;
            this.lvFixedCashFlows.UseCompatibleStateImageBehavior = false;
            this.lvFixedCashFlows.View = System.Windows.Forms.View.Details;
            // 
            // colhFixDate
            // 
            this.colhFixDate.Text = "Date";
            this.colhFixDate.Width = 124;
            // 
            // colhFixRate
            // 
            this.colhFixRate.Text = "Rate";
            this.colhFixRate.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.colhFixRate.Width = 102;
            // 
            // colhFixCashFlow
            // 
            this.colhFixCashFlow.Text = "Cash Flow";
            this.colhFixCashFlow.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.colhFixCashFlow.Width = 183;
            // 
            // groupBox1
            // 
            this.groupBox1.Location = new System.Drawing.Point(10, 10);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(681, 179);
            this.groupBox1.TabIndex = 2;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Fixed Cash Flows";
            // 
            // tabZcCurve
            // 
            this.tabZcCurve.Controls.Add(this.lvZcCurve);
            this.tabZcCurve.Location = new System.Drawing.Point(4, 29);
            this.tabZcCurve.Name = "tabZcCurve";
            this.tabZcCurve.Size = new System.Drawing.Size(696, 491);
            this.tabZcCurve.TabIndex = 2;
            this.tabZcCurve.Text = "ZC Curve (from file)";
            // 
            // lvZcCurve
            // 
            this.lvZcCurve.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colhZcPeriod,
            this.colhZcDate,
            this.colhDiscountFactor});
            this.lvZcCurve.Location = new System.Drawing.Point(11, 10);
            this.lvZcCurve.Name = "lvZcCurve";
            this.lvZcCurve.Size = new System.Drawing.Size(533, 466);
            this.lvZcCurve.TabIndex = 0;
            this.lvZcCurve.UseCompatibleStateImageBehavior = false;
            this.lvZcCurve.View = System.Windows.Forms.View.Details;
            // 
            // colhZcPeriod
            // 
            this.colhZcPeriod.Text = "Period";
            this.colhZcPeriod.Width = 96;
            // 
            // colhZcDate
            // 
            this.colhZcDate.Text = "Date";
            this.colhZcDate.Width = 140;
            // 
            // colhDiscountFactor
            // 
            this.colhDiscountFactor.Text = "Discount Factor";
            this.colhDiscountFactor.Width = 100;
            // 
            // VanillaPricerGui
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(707, 524);
            this.Controls.Add(this.tabContainer);
            this.Name = "VanillaPricerGui";
            this.Text = "Vanilla Pricer";
            this.tabContainer.ResumeLayout(false);
            this.tabMain.ResumeLayout(false);
            this.tabMain.PerformLayout();
            this.tabCashFlows.ResumeLayout(false);
            this.tabZcCurve.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabContainer;
        private System.Windows.Forms.TabPage tabMain;
        private System.Windows.Forms.TabPage tabCashFlows;
        private System.Windows.Forms.TabPage tabZcCurve;
        private System.Windows.Forms.Label lblValuationDate;
        private System.Windows.Forms.Label lblSettle;
        private System.Windows.Forms.Label lblTenor;
        private System.Windows.Forms.Label lblMaturity;
        private System.Windows.Forms.Label lblNotional;
        private System.Windows.Forms.Label lblNpv;
        private System.Windows.Forms.Label lblFrequency;
        private System.Windows.Forms.Label lblFixed;
        private System.Windows.Forms.Label lblFloat;
        private System.Windows.Forms.Label lblCurFloat;
        private System.Windows.Forms.Label lblSpread;
        private System.Windows.Forms.Label lblFixedRate;
        private System.Windows.Forms.Label lblLegNpv;
        private System.Windows.Forms.DateTimePicker dtpValuationDate;
        private System.Windows.Forms.DateTimePicker dtpSettle;
        private System.Windows.Forms.TextBox txtTenor;
        private System.Windows.Forms.TextBox txtNotional;
        private System.Windows.Forms.TextBox txtNpv;
        private System.Windows.Forms.ComboBox cboFixFrq;
        private System.Windows.Forms.ComboBox cboFloatFrq;
        private System.Windows.Forms.TextBox txtFixedRate;
        private System.Windows.Forms.TextBox txtCurFloat;
        private System.Windows.Forms.TextBox txtSpread;
        private System.Windows.Forms.TextBox txtFixLegNpv;
        private System.Windows.Forms.TextBox txtFloatLegNpv;
        private System.Windows.Forms.Button btnCalculate;
        private System.Windows.Forms.TextBox txtMaturity;
        private System.Windows.Forms.ListView lvFixedCashFlows;
        private System.Windows.Forms.ColumnHeader colhFixDate;
        private System.Windows.Forms.ColumnHeader colhFixRate;
        private System.Windows.Forms.ColumnHeader colhFixCashFlow;
        private System.Windows.Forms.ColumnHeader colhFltDate;
        private System.Windows.Forms.ColumnHeader colhFltRate;
        private System.Windows.Forms.ColumnHeader colhFltCashFlow;
        private System.Windows.Forms.ListView lvFloatingCashFlows;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.ListView lvZcCurve;
        private System.Windows.Forms.ColumnHeader colhZcPeriod;
        private System.Windows.Forms.ColumnHeader colhZcDate;
        private System.Windows.Forms.ColumnHeader colhDiscountFactor;
    }
}