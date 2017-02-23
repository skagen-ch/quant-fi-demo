

import java.awt.EventQueue;
import javax.swing.JFrame;


public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1413204728690274925L;
	private mainPanel currentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setTitle("Vanilla Pricer");
		currentPanel = new mainPanel();
		setupFrame();
	}
	
	private void setupFrame()
	{
		setContentPane(currentPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
	}

}
