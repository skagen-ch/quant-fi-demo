import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class PricerView extends JFrame implements PricerObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1413204728690274925L;
	Pricer vanillaPricer;
	Controller controller;
	private mainPanel mainPanel;
	private cashFlowsPanel cfPanel;
	private zcCurvePanel zcPanel;
	private JTabbedPane tabPane;

	/**
	 * Create the frame.
	 */
	public PricerView(Controller controller, Pricer vanillaPricer) {
		this.controller = controller;
		this.vanillaPricer = vanillaPricer;
		vanillaPricer.registerObserver((PricerObserver)this);
	}
	
	public void setupFrame()
	{
		setTitle("Vanilla Pricer");
		tabPane = new JTabbedPane();
		mainPanel = new mainPanel(this.controller);
		cfPanel = new cashFlowsPanel();
		zcPanel = new zcCurvePanel();
		tabPane.add("Main", mainPanel);
		tabPane.add("Cash flows", cfPanel);
		tabPane.add("Discount Curve (from file)", zcPanel);
		setContentPane(tabPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 550, 550);
		
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
        Date todayDate = new Date();
        Date defaultSettle = DateUtils.AddPeriod(todayDate, "2D");

		// SET DEFAULT VALUES
		mainPanel.getTxtValuationDate().setText(dateFormat.format(todayDate));
		controller.setValuationDate(dateFormat.format(todayDate));
		mainPanel.getTxtSettlementDate().setText(dateFormat.format(defaultSettle));
		controller.setSettlementDate(dateFormat.format(defaultSettle));
		mainPanel.getTxtTenor().setText("5");
		controller.setTenor("5");
		mainPanel.getTxtNotional().setText("10000000");
		controller.setNotional("10000000");
		mainPanel.getTxtNpv().setText("0");
		controller.setNpv("0");
		mainPanel.getCboFixedFrq().setSelectedItem("Annual");
		controller.setFixedFrequency("A");
		mainPanel.getCboFloatFrq().setSelectedItem("Semi-annual");
		controller.setFloatFrequency("S");
		mainPanel.getTxtCurFloat().setText("0.024");
		controller.setCurrentFloat("0.024");
		mainPanel.getTxtSpread().setText("0");
		controller.setSpread("0");
		
		this.pack();
		this.setVisible(true);
	}
	
	public void update() {
		zcPanel.setTblZcCurve(vanillaPricer.getZcCurve());
		mainPanel.getTxtMaturity().setText(vanillaPricer.getMaturityDate());
		mainPanel.getTxtCalculatedFixedRate().setText(vanillaPricer.getCalculatedFixedRate());
		mainPanel.getTxtFixLegNpv().setText(vanillaPricer.getFixedLegNpv());
		mainPanel.getTxtFloatLegNpv().setText(vanillaPricer.getFloatLegNpv());
		cashFlowsPanel.setTblFixedCashFlows(vanillaPricer.getFixedCoupons());
		cashFlowsPanel.setTblFloatCashFlows(vanillaPricer.getFloatCoupons());
	}

}
