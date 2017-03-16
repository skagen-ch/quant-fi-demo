import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class mainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controller controller;
	private JTextField txtValuationDate;
	private JTextField txtSettlementDate;
	private JTextField txtNotional;
	private JTextField txtTenor;
	private JTextField txtMaturity;
	private JTextField txtCurFloat;
	private JTextField txtSpread;
	private JTextField txtNpv;
	private JLabel lblValuationDate;
	private JLabel lblSettlementDate;
	private JLabel lblFixed;
	private JLabel lblFloat;
	private JLabel lblFrequency;
	private JComboBox<String> cboFixedFrq;
	private JComboBox<String> cboFloatFrq;
	private JLabel lblNotional;
	private JLabel lblTenor;
	private JLabel lblMaturity;
	private JLabel lblCurrentIndex;
	private JLabel lblSpread;
	private JLabel lblNpv;
	private JLabel lblLegNpv;
	private JTextField txtFixLegNpv;
	private JTextField txtFloatLegNpv;
	
	private JLabel lblCalculatedFixedRate;
	private JTextField txtCalculatedFixedRate;
	private JButton btnCalculate;

	/**
	 * Create the panel.
	 */
	public mainPanel(Controller controller) {
		this.controller = controller;
		setForeground(Color.ORANGE);
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{139, 153, 124, 156, 9, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 20, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		createControls();
		setupControls();
	}
	
	private void createControls() {
		lblValuationDate = new JLabel("Valuation Date:");
		lblSettlementDate = new JLabel("Settlement Date:");
		lblTenor = new JLabel("Tenor:");
		lblMaturity = new JLabel("Maturity:");
		lblNotional = new JLabel("Notional:");
		lblNpv = new JLabel("NPV:");
		lblFloat = new JLabel("Float");
		lblFixed = new JLabel("Fixed");
		lblFrequency = new JLabel("Frequency");
		lblLegNpv = new JLabel("Leg NPV:");
		lblSpread = new JLabel("Spread:");
		lblCurrentIndex = new JLabel("Current index:");
		lblCalculatedFixedRate = new JLabel("Fixed Rate:");
		
		setTxtValuationDate(new JTextField());
		setTxtSettlementDate(new JTextField());
		setTxtTenor(new JTextField());
		setTxtMaturity(new JTextField());
		setTxtNotional(new JTextField());
		setTxtNpv(new JTextField());
		setCboFixedFrq(new JComboBox<String>());
		getCboFixedFrq().setModel(new DefaultComboBoxModel(new String[] {"Annual", "Semi-annual", "Quarterly", "Monthly"}));
		setCboFloatFrq(new JComboBox<String>());
		getCboFloatFrq().setModel(new DefaultComboBoxModel(new String[] {"Annual", "Semi-annual", "Quarterly", "Monthly"}));
		setTxtFixLegNpv(new JTextField());
		setTxtFloatLegNpv(new JTextField());
		setTxtSpread(new JTextField());
		setTxtCurFloat(new JTextField());
		setTxtCalculatedFixedRate(new JTextField());

		setBtnCalculate(new JButton("Calculate"));
	}
	
	private void setupControls()
	{
		add(lblValuationDate, createGridBagConstraints(GridBagConstraints.EAST, 0, 1));
		add(lblSettlementDate, createGridBagConstraints(GridBagConstraints.EAST, 2, 1));
		add(lblTenor, createGridBagConstraints(GridBagConstraints.EAST, 0, 2));
		add(lblMaturity, createGridBagConstraints(GridBagConstraints.EAST, 2, 2));
		add(lblNotional, createGridBagConstraints(GridBagConstraints.EAST, 0, 3));
		add(lblNpv, createGridBagConstraints(GridBagConstraints.EAST, 2, 3));
		add(lblFixed, createGridBagConstraints(GridBagConstraints.WEST, 1, 4));
		add(lblFloat, createGridBagConstraints(GridBagConstraints.EAST, 3, 4));
		add(lblFrequency, createGridBagConstraints(GridBagConstraints.EAST, 0, 5));
		add(lblCurrentIndex, createGridBagConstraints(GridBagConstraints.EAST, 2, 6));
		add(lblCalculatedFixedRate, createGridBagConstraints(GridBagConstraints.EAST, 0, 7));
		add(lblSpread, createGridBagConstraints(GridBagConstraints.EAST, 2, 7));
		add(lblLegNpv, createGridBagConstraints(GridBagConstraints.EAST, 0, 8));

		add(getTxtValuationDate(), createGridBagConstraints(GridBagConstraints.EAST, 1, 1));
		add(getTxtSettlementDate(), createGridBagConstraints(GridBagConstraints.EAST, 3, 1));
		add(getTxtTenor(), createGridBagConstraints(GridBagConstraints.EAST, 1, 2));
		add(getTxtMaturity(), createGridBagConstraints(GridBagConstraints.EAST, 3, 2));
		add(getTxtNotional(), createGridBagConstraints(GridBagConstraints.EAST, 1, 3));
		add(getTxtNpv(), createGridBagConstraints(GridBagConstraints.EAST, 3, 3));
		add(getCboFixedFrq(), createGridBagConstraints(GridBagConstraints.EAST, 1, 5));
		add(getCboFloatFrq(), createGridBagConstraints(GridBagConstraints.EAST, 3, 5));
		add(getTxtCurFloat(), createGridBagConstraints(GridBagConstraints.EAST, 3, 6));
		add(getTxtCalculatedFixedRate(), createGridBagConstraints(GridBagConstraints.EAST, 1, 7));
		add(getTxtSpread(), createGridBagConstraints(GridBagConstraints.EAST, 3, 7));
		add(getTxtFixLegNpv(), createGridBagConstraints(GridBagConstraints.EAST, 1, 8));
		add(getTxtFloatLegNpv(), createGridBagConstraints(GridBagConstraints.EAST, 3, 8));
		
		add(btnCalculate, createGridBagConstraints(GridBagConstraints.EAST, 3, 9));

		for (Component c : this.getComponents()){
			if (c instanceof JLabel) c.setForeground(Color.ORANGE);
			if (c instanceof JTextField) ((JTextField)c).setColumns(10);
		}
		
		getTxtValuationDate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setValuationDate(getTxtValuationDate().getText());
			}
		});
		
		getTxtSettlementDate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setSettlementDate(getTxtSettlementDate().getText());
			}
		});
		
		getCboFixedFrq().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setFixedFrequency(getCboFixedFrq().getSelectedItem().toString().substring(0, 1));
			}
		});
		
		getCboFloatFrq().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setFloatFrequency(getCboFloatFrq().getSelectedItem().toString().substring(0, 1));
			}
		});
		
		getTxtNotional().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setNotional(getTxtNotional().getText());
			}
		});
		
		getTxtTenor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setTenor(getTxtTenor().getText());
			}
		});
		
		getTxtCurFloat().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setCurrentFloat(getTxtCurFloat().getText());
			}
		});
		
		getTxtSpread().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setSpread(getTxtSpread().getText());
			}
		});
		
		getTxtNpv().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.setNpv(getTxtNpv().getText());
			}
		});
		
		getBtnCalculate().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String marketDataPath = "marketdata.txt";
					JFileChooser fileChooser = new JFileChooser();
					int returnVal = fileChooser.showOpenDialog(btnCalculate);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						marketDataPath =  fileChooser.getSelectedFile().getAbsolutePath();
					}
					
					controller.calculate(marketDataPath);
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(btnCalculate, e.getStackTrace());
				}
			}
		});

	}
	
	private GridBagConstraints createGridBagConstraints(int anchor, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = anchor;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = x;
		gbc.gridy = y;
		return gbc;
	}
	
	public JTextField getTxtMaturity() {
		return txtMaturity;
	}

	public void setTxtMaturity(JTextField txtMaturity) {
		this.txtMaturity = txtMaturity;
	}

	public JTextField getTxtCalculatedFixedRate() {
		return txtCalculatedFixedRate;
	}

	public void setTxtCalculatedFixedRate(JTextField txtCalculatedFixedRate) {
		this.txtCalculatedFixedRate = txtCalculatedFixedRate;
	}

	public JTextField getTxtFixLegNpv() {
		return txtFixLegNpv;
	}

	public void setTxtFixLegNpv(JTextField txtFixLegNpv) {
		this.txtFixLegNpv = txtFixLegNpv;
	}

	public JTextField getTxtFloatLegNpv() {
		return txtFloatLegNpv;
	}

	public void setTxtFloatLegNpv(JTextField txtFloatLegNpv) {
		this.txtFloatLegNpv = txtFloatLegNpv;
	}

	public JButton getBtnCalculate() {
		return btnCalculate;
	}

	public void setBtnCalculate(JButton btnCalculate) {
		this.btnCalculate = btnCalculate;
	}

	public JTextField getTxtValuationDate() {
		return txtValuationDate;
	}

	public void setTxtValuationDate(JTextField txtValuationDate) {
		this.txtValuationDate = txtValuationDate;
	}

	public JTextField getTxtSettlementDate() {
		return txtSettlementDate;
	}

	public void setTxtSettlementDate(JTextField txtSettlementDate) {
		this.txtSettlementDate = txtSettlementDate;
	}

	public JTextField getTxtTenor() {
		return txtTenor;
	}

	public void setTxtTenor(JTextField txtTenor) {
		this.txtTenor = txtTenor;
	}

	public JTextField getTxtNotional() {
		return txtNotional;
	}

	public void setTxtNotional(JTextField txtNotional) {
		this.txtNotional = txtNotional;
	}

	public JTextField getTxtNpv() {
		return txtNpv;
	}

	public void setTxtNpv(JTextField txtNpv) {
		this.txtNpv = txtNpv;
	}

	public JComboBox<String> getCboFixedFrq() {
		return cboFixedFrq;
	}

	public void setCboFixedFrq(JComboBox<String> cboFixedFrq) {
		this.cboFixedFrq = cboFixedFrq;
	}

	public JComboBox<String> getCboFloatFrq() {
		return cboFloatFrq;
	}

	public void setCboFloatFrq(JComboBox<String> cboFloatFrq) {
		this.cboFloatFrq = cboFloatFrq;
	}

	public JTextField getTxtCurFloat() {
		return txtCurFloat;
	}

	public void setTxtCurFloat(JTextField txtCurFloat) {
		this.txtCurFloat = txtCurFloat;
	}

	public JTextField getTxtSpread() {
		return txtSpread;
	}

	public void setTxtSpread(JTextField txtSpread) {
		this.txtSpread = txtSpread;
	}

}
