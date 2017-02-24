import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class cashFlowsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6658120697533772719L;
	private static JTable tblFixedCashFlows;
	private static JTable tblFloatCashFlows;
	private JPanel pFloat;
	private JPanel pFixed;
	/**
	 * Create the panel.
	 */
	public cashFlowsPanel() {
		setForeground(Color.ORANGE);
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setupCfPanel();
	}
	
	@SuppressWarnings("serial")
	private void setupCfPanel()
	{
		
		pFixed = new JPanel();
		pFixed.setForeground(Color.ORANGE);
		pFixed.setBackground(Color.BLACK);
		pFixed.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fixed cash flows", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(pFixed);
		
		tblFixedCashFlows = new JTable();
		tblFixedCashFlows.setForeground(Color.ORANGE);
		tblFixedCashFlows.setBackground(Color.BLACK);
		
		JScrollPane spFixed = new JScrollPane(tblFixedCashFlows);
		pFixed.add(spFixed);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		
		pFloat = new JPanel();
		pFloat.setForeground(Color.ORANGE);
		pFloat.setBackground(Color.BLACK);
		pFloat.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Floating cash flows", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		add(pFloat);
		tblFloatCashFlows = new JTable();
		tblFloatCashFlows.setForeground(Color.ORANGE);
		tblFloatCashFlows.setBackground(Color.BLACK);
		
		JScrollPane spFloat = new JScrollPane(tblFloatCashFlows);
		pFloat.add(spFloat);
	}
	
	public static void setTblFixedCashFlows(ArrayList<Coupon> cfs)
	{
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
		
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.addColumn("Date");
		dataModel.addColumn("Rate");
		dataModel.addColumn("Cash flow");
		for (Coupon cf : cfs)
		{
			String[] s = {dateFormat.format(cf.getEndDate()),String.format("%.3f", cf.getForwardRate()*100),String.format("%.2f", cf.getCashFlow())};
			dataModel.addRow(s);
		}
		tblFixedCashFlows.setModel(dataModel);
	}
	public static JTable getTblFixedCashFlows() {
		return tblFixedCashFlows;
	}
	
	public static void setTblFloatCashFlows(ArrayList<Coupon> cfs)
	{
		TimeZone tz	= TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormat	 = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setTimeZone(tz);
		
		DefaultTableModel dataModel = new DefaultTableModel();
		dataModel.addColumn("Date");
		dataModel.addColumn("Rate");
		dataModel.addColumn("Cash flow");
		for (Coupon cf : cfs)
		{
			String[] s = {dateFormat.format(cf.getEndDate()),String.format("%.3f", cf.getForwardRate()*100),String.format("%.2f", cf.getCashFlow())};
			dataModel.addRow(s);
		}
		tblFloatCashFlows.setModel(dataModel);
	}
	public JTable getTblFloatCashFlows() {
		return tblFloatCashFlows;
	}
}
