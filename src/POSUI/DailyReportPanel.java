package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.github.lgooddatepicker.components.DatePicker;

import POSPD.Cashier;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

public class DailyReportPanel extends JPanel {
	private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("M/d/yy");
	private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm:ss a");
	private JTextPane textPane;
	private DatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public DailyReportPanel(JFrame currentFrame, Store store)
	{
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(datePicker.getDate() != null)
				{
					textPane.setText(generateDailyReport(store, datePicker.getDate()));
				}
			}
		});

		btnGenerate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnGenerate.setBounds(85, 320, 100, 20);
		add(btnGenerate);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClose.setBounds(270, 320, 100, 20);
		add(btnClose);
		
		JLabel lblDailyReport = new JLabel("Daily Report");
		lblDailyReport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDailyReport.setBounds(180, 10, 150, 30);
		add(lblDailyReport);
		
		textPane = new JTextPane();
		textPane.setBounds(20, 110, 440, 190);
		add(textPane);
		
		datePicker = new DatePicker();
		datePicker.setBounds(170, 60, 160, 25);
		add(datePicker);
		
	}
	
	public String generateDailyReport(Store store, LocalDate date)
	{
		String newLine = "\n";
		String tab = "\t";
		String report = "";
		
		BigDecimal totalSales = new BigDecimal("0.00");
		BigDecimal totalCash = new BigDecimal("0.00");
		BigDecimal totalCheck = new BigDecimal("0.00");
		BigDecimal totalCredit = new BigDecimal("0.00");
		int totalItemsSold = 0;
		
		report += "Daily Report for:" + date.format(dateTimeFormat) + newLine;
		report += newLine + "Time" + tab + "Sales" + tab + "# Sold" + tab + "Cash" + tab + "Check" + tab + "Credit" + newLine;
		for (Session s : store.getSessions())
		{
			if(s.getStartDateTime().toLocalDate().equals(date))
			{
				for(Sale sa : s.getSales())
				{
					report += sa.getDateTime().format(timeFormat) + tab + sa.calcTotal() + tab + sa.calcItemsSold() + tab 
							+ sa.calcTotalCash() + tab + sa.calcTotalCheck() + tab + sa.calcTotalCredit() + newLine;
					totalSales = totalSales.add(sa.calcTotal());
					totalItemsSold += sa.calcItemsSold();
					totalCash = totalCash.add(sa.calcTotalCash());
					totalCheck = totalCheck.add(sa.calcTotalCheck());
					totalCredit = totalCredit.add(sa.calcTotalCredit());
				}
			}	
		}
		report += newLine + "Total:" + tab + totalSales + tab + totalItemsSold + tab + totalCash + tab + totalCheck + tab + totalCredit;
		return report;
	}
}
