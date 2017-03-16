import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class zcCurvePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8662587502644572657L;
	private static JTable tblZcCurve;
	private JPanel pZcCurve;

	/**
	 * Create the panel.
	 */
	public zcCurvePanel() {
		setForeground(Color.ORANGE);
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setupZcPanel();
	}
	private void setupZcPanel()
	{
		
		pZcCurve = new JPanel();
		pZcCurve.setForeground(Color.ORANGE);
		pZcCurve.setBackground(Color.BLACK);
		add(pZcCurve);
		
		tblZcCurve = new JTable();
		tblZcCurve.setForeground(Color.ORANGE);
		tblZcCurve.setBackground(Color.BLACK);
		
		JScrollPane spZcCurve = new JScrollPane(tblZcCurve);
		pZcCurve.add(spZcCurve);
		
	}
	
	public void setTblZcCurve(ArrayList<ZeroCouponDataPoint> zc)
	{
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
		
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.addColumn("Period");
		dataModel.addColumn("Date");
		dataModel.addColumn("Discount Factor");
		for (ZeroCouponDataPoint p : zc)
		{
			String[] s = {p.getPeriod(), dateFormat.format(p.getEndDate()),String.format("%.6f", p.getDiscountFactor())};
			dataModel.addRow(s);
		}
		tblZcCurve.setModel(dataModel);
	}
	public static JTable getTblZcCurve() {
		return tblZcCurve;
	}
}
