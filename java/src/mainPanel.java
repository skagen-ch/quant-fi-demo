

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class mainPanel extends JPanel {
	private JTextField txtValuationDate;
	private JTextField txtSettlementDate;
	private JTextField txtNotional;
	private JTextField txtTenor;
	private JTextField txtCurFloat;
	private JTextField txtSpread;
	private JTextField txtNpv;
	private JLabel lblTitle;
	private JLabel lblValuationDate;
	private JLabel lblSettlementDate;
	private JLabel lblFixed;
	private JLabel lblFloat;
	private JLabel lblFrequency;
	private JComboBox cboFixedFrq;
	private JComboBox cboFloatFrq;
	private JLabel lblNotional;
	private JLabel lblTenor;
	private JLabel lblCurrentIndex;
	private JLabel lblSpread;
	private JLabel lblNpv;
	
	private JLabel lblCalculatedFixedRate;
	private JTextField txtCalculatedFixedRate;
	private JButton btnCalculate;
	private VanillaPricer vanillaPricer;

	/**
	 * Create the panel.
	 */
	public mainPanel() {
		setForeground(Color.ORANGE);
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{160, 153, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblTitle = new JLabel("Vanilla Pricer");
		lblValuationDate = new JLabel("Valuation Date:");
		txtValuationDate = new JTextField();
		lblSettlementDate = new JLabel("Settlement Date:");
		txtSettlementDate = new JTextField();
		lblFixed = new JLabel("Fixed");
		lblFloat = new JLabel("Float");
		lblFrequency = new JLabel("Frequency");
		cboFixedFrq = new JComboBox();
		cboFixedFrq.setModel(new DefaultComboBoxModel(new String[] {"Annual", "Semi-annual", "Quarterly", "Monthly"}));
		cboFloatFrq = new JComboBox();
		cboFloatFrq.setModel(new DefaultComboBoxModel(new String[] {"Annual", "Semi-annual", "Quarterly", "Monthly"}));
		lblNotional = new JLabel("Notional:");
		txtNotional = new JTextField();
		lblTenor = new JLabel("Tenor:");
		txtTenor = new JTextField();
		lblCurrentIndex = new JLabel("Current index:");
		txtCurFloat = new JTextField();
		lblSpread = new JLabel("Spread:");
		txtSpread = new JTextField();
		lblNpv = new JLabel("NPV:");
		txtNpv = new JTextField();
		lblCalculatedFixedRate = new JLabel("Fixed Rate:");
		txtCalculatedFixedRate = new JTextField();
		btnCalculate = new JButton("Calculate");
		vanillaPricer = new VanillaPricer();
		setupPanel();
	}
	
	private void setupPanel()
	{
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
        Date todayDate = new Date();
        Date defaultSettle = DateUtils.AddPeriod(todayDate, "2D");
        
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		
		lblValuationDate.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblValuationDate = new GridBagConstraints();
		gbc_lblValuationDate.anchor = GridBagConstraints.EAST;
		gbc_lblValuationDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblValuationDate.gridx = 0;
		gbc_lblValuationDate.gridy = 1;
		add(lblValuationDate, gbc_lblValuationDate);
		
		GridBagConstraints gbc_txtValuationDate = new GridBagConstraints();
		gbc_txtValuationDate.anchor = GridBagConstraints.WEST;
		gbc_txtValuationDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtValuationDate.gridx = 1;
		gbc_txtValuationDate.gridy = 1;
		add(txtValuationDate, gbc_txtValuationDate);
		txtValuationDate.setColumns(10);
		txtValuationDate.setText(dateFormat.format(todayDate));
		
		lblSettlementDate.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblSettlementDate = new GridBagConstraints();
		gbc_lblSettlementDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblSettlementDate.anchor = GridBagConstraints.EAST;
		gbc_lblSettlementDate.gridx = 0;
		gbc_lblSettlementDate.gridy = 2;
		add(lblSettlementDate, gbc_lblSettlementDate);
		
		GridBagConstraints gbc_txtSettlementDate = new GridBagConstraints();
		gbc_txtSettlementDate.anchor = GridBagConstraints.WEST;
		gbc_txtSettlementDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtSettlementDate.gridx = 1;
		gbc_txtSettlementDate.gridy = 2;
		add(txtSettlementDate, gbc_txtSettlementDate);
		txtSettlementDate.setColumns(10);
		txtSettlementDate.setText(dateFormat.format(defaultSettle));
		
		lblFixed.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblFixed = new GridBagConstraints();
		gbc_lblFixed.anchor = GridBagConstraints.WEST;
		gbc_lblFixed.insets = new Insets(0, 0, 5, 5);
		gbc_lblFixed.gridx = 1;
		gbc_lblFixed.gridy = 4;
		add(lblFixed, gbc_lblFixed);
		
		lblFloat.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblFloat = new GridBagConstraints();
		gbc_lblFloat.anchor = GridBagConstraints.WEST;
		gbc_lblFloat.insets = new Insets(0, 0, 5, 0);
		gbc_lblFloat.gridx = 2;
		gbc_lblFloat.gridy = 4;
		add(lblFloat, gbc_lblFloat);
		
		lblFrequency.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblFrequency = new GridBagConstraints();
		gbc_lblFrequency.anchor = GridBagConstraints.EAST;
		gbc_lblFrequency.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrequency.gridx = 0;
		gbc_lblFrequency.gridy = 5;
		add(lblFrequency, gbc_lblFrequency);
		
		GridBagConstraints gbc_cboFixedFrq = new GridBagConstraints();
		gbc_cboFixedFrq.anchor = GridBagConstraints.WEST;
		gbc_cboFixedFrq.insets = new Insets(0, 0, 5, 5);
		gbc_cboFixedFrq.gridx = 1;
		gbc_cboFixedFrq.gridy = 5;
		add(cboFixedFrq, gbc_cboFixedFrq);
		cboFixedFrq.setSelectedItem("Annual");
		
		GridBagConstraints gbc_cboFloatFrq = new GridBagConstraints();
		gbc_cboFloatFrq.anchor = GridBagConstraints.WEST;
		gbc_cboFloatFrq.insets = new Insets(0, 0, 5, 0);
		gbc_cboFloatFrq.gridx = 2;
		gbc_cboFloatFrq.gridy = 5;
		add(cboFloatFrq, gbc_cboFloatFrq);
		cboFloatFrq.setSelectedItem("Semi-annual");
		
		lblNotional.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblNotional = new GridBagConstraints();
		gbc_lblNotional.anchor = GridBagConstraints.EAST;
		gbc_lblNotional.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotional.gridx = 0;
		gbc_lblNotional.gridy = 6;
		add(lblNotional, gbc_lblNotional);
		
		GridBagConstraints gbc_txtNotional = new GridBagConstraints();
		gbc_txtNotional.anchor = GridBagConstraints.WEST;
		gbc_txtNotional.insets = new Insets(0, 0, 5, 5);
		gbc_txtNotional.gridx = 1;
		gbc_txtNotional.gridy = 6;
		add(txtNotional, gbc_txtNotional);
		txtNotional.setColumns(10);
		txtNotional.setText("10000000");
		
		lblTenor.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblTenor = new GridBagConstraints();
		gbc_lblTenor.anchor = GridBagConstraints.EAST;
		gbc_lblTenor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTenor.gridx = 0;
		gbc_lblTenor.gridy = 7;
		add(lblTenor, gbc_lblTenor);
		
		GridBagConstraints gbc_txtTenor = new GridBagConstraints();
		gbc_txtTenor.anchor = GridBagConstraints.WEST;
		gbc_txtTenor.insets = new Insets(0, 0, 5, 5);
		gbc_txtTenor.gridx = 1;
		gbc_txtTenor.gridy = 7;
		add(txtTenor, gbc_txtTenor);
		txtTenor.setColumns(10);
		txtTenor.setText("5");
		
		lblCurrentIndex.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblCurrentIndex = new GridBagConstraints();
		gbc_lblCurrentIndex.anchor = GridBagConstraints.EAST;
		gbc_lblCurrentIndex.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentIndex.gridx = 0;
		gbc_lblCurrentIndex.gridy = 8;
		add(lblCurrentIndex, gbc_lblCurrentIndex);
		
		GridBagConstraints gbc_txtCurFloat = new GridBagConstraints();
		gbc_txtCurFloat.anchor = GridBagConstraints.WEST;
		gbc_txtCurFloat.insets = new Insets(0, 0, 5, 5);
		gbc_txtCurFloat.gridx = 1;
		gbc_txtCurFloat.gridy = 8;
		add(txtCurFloat, gbc_txtCurFloat);
		txtCurFloat.setColumns(10);
		txtCurFloat.setText("0.024");
		
		lblSpread.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblSpread = new GridBagConstraints();
		gbc_lblSpread.anchor = GridBagConstraints.EAST;
		gbc_lblSpread.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpread.gridx = 0;
		gbc_lblSpread.gridy = 9;
		add(lblSpread, gbc_lblSpread);
		
		GridBagConstraints gbc_txtSpread = new GridBagConstraints();
		gbc_txtSpread.anchor = GridBagConstraints.WEST;
		gbc_txtSpread.insets = new Insets(0, 0, 5, 5);
		gbc_txtSpread.gridx = 1;
		gbc_txtSpread.gridy = 9;
		add(txtSpread, gbc_txtSpread);
		txtSpread.setColumns(10);
		txtSpread.setText("0");
		
		lblNpv.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblNpv = new GridBagConstraints();
		gbc_lblNpv.anchor = GridBagConstraints.EAST;
		gbc_lblNpv.insets = new Insets(0, 0, 5, 5);
		gbc_lblNpv.gridx = 0;
		gbc_lblNpv.gridy = 10;
		add(lblNpv, gbc_lblNpv);
		
		GridBagConstraints gbc_txtNpv = new GridBagConstraints();
		gbc_txtNpv.anchor = GridBagConstraints.WEST;
		gbc_txtNpv.insets = new Insets(0, 0, 5, 5);
		gbc_txtNpv.gridx = 1;
		gbc_txtNpv.gridy = 10;
		add(txtNpv, gbc_txtNpv);
		txtNpv.setColumns(10);
		txtNpv.setText("0");
		
		lblCalculatedFixedRate.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblCalculatedFixedRate = new GridBagConstraints();
		gbc_lblCalculatedFixedRate.anchor = GridBagConstraints.EAST;
		gbc_lblCalculatedFixedRate.insets = new Insets(0, 0, 0, 5);
		gbc_lblCalculatedFixedRate.gridx = 0;
		gbc_lblCalculatedFixedRate.gridy = 11;
		add(lblCalculatedFixedRate, gbc_lblCalculatedFixedRate);
		
		GridBagConstraints gbc_txtCalculatedFixedRate = new GridBagConstraints();
		gbc_txtCalculatedFixedRate.anchor = GridBagConstraints.WEST;
		gbc_txtCalculatedFixedRate.insets = new Insets(0, 0, 0, 5);
		gbc_txtCalculatedFixedRate.gridx = 1;
		gbc_txtCalculatedFixedRate.gridy = 11;
		add(txtCalculatedFixedRate, gbc_txtCalculatedFixedRate);
		txtCalculatedFixedRate.setColumns(10);	
		
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args) {
				try {
					String marketDataPath = "marketdata.txt";
					JFileChooser fileChooser = new JFileChooser();
					int returnVal = fileChooser.showOpenDialog(btnCalculate);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						marketDataPath =  fileChooser.getSelectedFile().getAbsolutePath();
					}
					
					vanillaPricer.main(new String[]{
			        	txtValuationDate.getText(),
					    txtSettlementDate.getText(),
					    cboFixedFrq.getSelectedItem().toString().substring(0, 1),
					    cboFloatFrq.getSelectedItem().toString().substring(0, 1),
				        txtNotional.getText(),
				        txtTenor.getText(),
					    txtCurFloat.getText(),
				        txtSpread.getText(),
			        	txtNpv.getText(),
			        	marketDataPath}
					);
					zcCurvePanel.setTblZcCurve(vanillaPricer.getZcCurve());
					txtCalculatedFixedRate.setText(vanillaPricer.getCalculatedFixedRate());
					cashFlowsPanel.setTblFixedCashFlows(vanillaPricer.getFixedCoupons());
					cashFlowsPanel.setTblFloatCashFlows(vanillaPricer.getFloatCoupons());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(btnCalculate, e.getStackTrace());
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(btnCalculate, e.getStackTrace());
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(btnCalculate, e.getStackTrace());
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.gridx = 2;
		gbc_btnCalculate.gridy = 11;
		add(btnCalculate, gbc_btnCalculate);}

	public double getSomeValue()
	{
		return 1.2345;
	}
}
