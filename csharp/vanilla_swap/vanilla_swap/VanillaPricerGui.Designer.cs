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
            this.lblValuationDate = new System.Windows.Forms.Label();
            this.lblSettle = new System.Windows.Forms.Label();
            this.lblTenor = new System.Windows.Forms.Label();
            this.lblMaturity = new System.Windows.Forms.Label();
            this.lblNotional = new System.Windows.Forms.Label();
            this.lblNpv = new System.Windows.Forms.Label();
            this.lblFrequency = new System.Windows.Forms.Label();
            this.lblFixed = new System.Windows.Forms.Label();
            this.lblFloat = new System.Windows.Forms.Label();
            this.lblCurFloat = new System.Windows.Forms.Label();
            this.lblSpread = new System.Windows.Forms.Label();
            this.lblFixedRate = new System.Windows.Forms.Label();
            this.lblLegNpv = new System.Windows.Forms.Label();
            this.dtpValuationDate = new System.Windows.Forms.DateTimePicker();
            this.dtpSettle = new System.Windows.Forms.DateTimePicker();
            this.txtTenor = new System.Windows.Forms.TextBox();
            this.txtNotional = new System.Windows.Forms.TextBox();
            this.txtNpv = new System.Windows.Forms.TextBox();
            this.cboFixFrq = new System.Windows.Forms.ComboBox();
            this.cboFloatFrq = new System.Windows.Forms.ComboBox();
            this.txtFixedRate = new System.Windows.Forms.TextBox();
            this.txtCurFloat = new System.Windows.Forms.TextBox();
            this.txtSpread = new System.Windows.Forms.TextBox();
            this.txtFixLegNpv = new System.Windows.Forms.TextBox();
            this.txtFloatLegNpv = new System.Windows.Forms.TextBox();
            this.btnCalculate = new System.Windows.Forms.Button();
            this.txtMaturity = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
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
            // lblSettle
            // 
            this.lblSettle.AutoSize = true;
            this.lblSettle.Location = new System.Drawing.Point(346, 14);
            this.lblSettle.Name = "lblSettle";
            this.lblSettle.Size = new System.Drawing.Size(130, 20);
            this.lblSettle.TabIndex = 1;
            this.lblSettle.Text = "Settlement Date:";
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
            // lblMaturity
            // 
            this.lblMaturity.AutoSize = true;
            this.lblMaturity.Location = new System.Drawing.Point(407, 49);
            this.lblMaturity.Name = "lblMaturity";
            this.lblMaturity.Size = new System.Drawing.Size(69, 20);
            this.lblMaturity.TabIndex = 3;
            this.lblMaturity.Text = "Maturity:";
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
            // lblNpv
            // 
            this.lblNpv.AutoSize = true;
            this.lblNpv.Location = new System.Drawing.Point(431, 84);
            this.lblNpv.Name = "lblNpv";
            this.lblNpv.Size = new System.Drawing.Size(45, 20);
            this.lblNpv.TabIndex = 5;
            this.lblNpv.Text = "NPV:";
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
            // lblFixed
            // 
            this.lblFixed.AutoSize = true;
            this.lblFixed.Location = new System.Drawing.Point(205, 120);
            this.lblFixed.Name = "lblFixed";
            this.lblFixed.Size = new System.Drawing.Size(47, 20);
            this.lblFixed.TabIndex = 7;
            this.lblFixed.Text = "Fixed";
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
            // lblCurFloat
            // 
            this.lblCurFloat.AutoSize = true;
            this.lblCurFloat.Location = new System.Drawing.Point(367, 203);
            this.lblCurFloat.Name = "lblCurFloat";
            this.lblCurFloat.Size = new System.Drawing.Size(109, 20);
            this.lblCurFloat.TabIndex = 9;
            this.lblCurFloat.Text = "Current Index:";
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
            // lblFixedRate
            // 
            this.lblFixedRate.AutoSize = true;
            this.lblFixedRate.Location = new System.Drawing.Point(39, 245);
            this.lblFixedRate.Name = "lblFixedRate";
            this.lblFixedRate.Size = new System.Drawing.Size(90, 20);
            this.lblFixedRate.TabIndex = 11;
            this.lblFixedRate.Text = "Fixed Rate:";
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
            // dtpValuationDate
            // 
            this.dtpValuationDate.Location = new System.Drawing.Point(133, 11);
            this.dtpValuationDate.Name = "dtpValuationDate";
            this.dtpValuationDate.Size = new System.Drawing.Size(200, 26);
            this.dtpValuationDate.TabIndex = 13;
            // 
            // dtpSettle
            // 
            this.dtpSettle.Location = new System.Drawing.Point(482, 11);
            this.dtpSettle.Name = "dtpSettle";
            this.dtpSettle.Size = new System.Drawing.Size(200, 26);
            this.dtpSettle.TabIndex = 14;
            // 
            // txtTenor
            // 
            this.txtTenor.Location = new System.Drawing.Point(133, 46);
            this.txtTenor.Name = "txtTenor";
            this.txtTenor.Size = new System.Drawing.Size(200, 26);
            this.txtTenor.TabIndex = 15;
            this.txtTenor.Text = "5";
            // 
            // txtNotional
            // 
            this.txtNotional.Location = new System.Drawing.Point(133, 81);
            this.txtNotional.Name = "txtNotional";
            this.txtNotional.Size = new System.Drawing.Size(200, 26);
            this.txtNotional.TabIndex = 16;
            this.txtNotional.Text = "10000000";
            // 
            // txtNpv
            // 
            this.txtNpv.Location = new System.Drawing.Point(482, 81);
            this.txtNpv.Name = "txtNpv";
            this.txtNpv.Size = new System.Drawing.Size(200, 26);
            this.txtNpv.TabIndex = 17;
            this.txtNpv.Text = "0";
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
            // txtFixedRate
            // 
            this.txtFixedRate.Location = new System.Drawing.Point(133, 242);
            this.txtFixedRate.Name = "txtFixedRate";
            this.txtFixedRate.Size = new System.Drawing.Size(200, 26);
            this.txtFixedRate.TabIndex = 20;
            // 
            // txtCurFloat
            // 
            this.txtCurFloat.Location = new System.Drawing.Point(482, 202);
            this.txtCurFloat.Name = "txtCurFloat";
            this.txtCurFloat.Size = new System.Drawing.Size(200, 26);
            this.txtCurFloat.TabIndex = 21;
            this.txtCurFloat.Text = "0.024";
            // 
            // txtSpread
            // 
            this.txtSpread.Location = new System.Drawing.Point(482, 245);
            this.txtSpread.Name = "txtSpread";
            this.txtSpread.Size = new System.Drawing.Size(200, 26);
            this.txtSpread.TabIndex = 22;
            this.txtSpread.Text = "0";
            // 
            // txtFixLegNpv
            // 
            this.txtFixLegNpv.Location = new System.Drawing.Point(133, 287);
            this.txtFixLegNpv.Name = "txtFixLegNpv";
            this.txtFixLegNpv.Size = new System.Drawing.Size(200, 26);
            this.txtFixLegNpv.TabIndex = 23;
            // 
            // txtFloatLegNpv
            // 
            this.txtFloatLegNpv.Location = new System.Drawing.Point(482, 288);
            this.txtFloatLegNpv.Name = "txtFloatLegNpv";
            this.txtFloatLegNpv.Size = new System.Drawing.Size(200, 26);
            this.txtFloatLegNpv.TabIndex = 24;
            // 
            // btnCalculate
            // 
            this.btnCalculate.Location = new System.Drawing.Point(582, 341);
            this.btnCalculate.Name = "btnCalculate";
            this.btnCalculate.Size = new System.Drawing.Size(100, 32);
            this.btnCalculate.TabIndex = 25;
            this.btnCalculate.Text = "Calculate";
            this.btnCalculate.UseVisualStyleBackColor = true;
            this.btnCalculate.Click += new System.EventHandler(this.btnCalculate_Click);
            // 
            // txtMaturity
            // 
            this.txtMaturity.Location = new System.Drawing.Point(482, 46);
            this.txtMaturity.Name = "txtMaturity";
            this.txtMaturity.Size = new System.Drawing.Size(200, 26);
            this.txtMaturity.TabIndex = 26;
            // 
            // VanillaPricerGui
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(707, 404);
            this.Controls.Add(this.txtMaturity);
            this.Controls.Add(this.btnCalculate);
            this.Controls.Add(this.txtFloatLegNpv);
            this.Controls.Add(this.txtFixLegNpv);
            this.Controls.Add(this.txtSpread);
            this.Controls.Add(this.txtCurFloat);
            this.Controls.Add(this.txtFixedRate);
            this.Controls.Add(this.cboFloatFrq);
            this.Controls.Add(this.cboFixFrq);
            this.Controls.Add(this.txtNpv);
            this.Controls.Add(this.txtNotional);
            this.Controls.Add(this.txtTenor);
            this.Controls.Add(this.dtpSettle);
            this.Controls.Add(this.dtpValuationDate);
            this.Controls.Add(this.lblLegNpv);
            this.Controls.Add(this.lblFixedRate);
            this.Controls.Add(this.lblSpread);
            this.Controls.Add(this.lblCurFloat);
            this.Controls.Add(this.lblFloat);
            this.Controls.Add(this.lblFixed);
            this.Controls.Add(this.lblFrequency);
            this.Controls.Add(this.lblNpv);
            this.Controls.Add(this.lblNotional);
            this.Controls.Add(this.lblMaturity);
            this.Controls.Add(this.lblTenor);
            this.Controls.Add(this.lblSettle);
            this.Controls.Add(this.lblValuationDate);
            this.Name = "VanillaPricerGui";
            this.Text = "Vanilla Pricer";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

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
    }
}