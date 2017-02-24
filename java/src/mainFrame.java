

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;


public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1413204728690274925L;
	private mainPanel mainPane;
	private cashFlowsPanel cfPanel;
	private zcCurvePanel zcPanel;
	private JTabbedPane tabPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainFrame frame = new mainFrame();
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getStackTrace());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setTitle("Vanilla Pricer");
		tabPane = new JTabbedPane();
		mainPane = new mainPanel();
		cfPanel = new cashFlowsPanel();
		zcPanel = new zcCurvePanel();
		setupFrame();
	}
	
	private void setupFrame()
	{
		tabPane.add("Main", mainPane);
		tabPane.add("Cash flows", cfPanel);
		tabPane.add("Discount Curve (from file)", zcPanel);
		setContentPane(tabPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 550, 550);
	}

}
