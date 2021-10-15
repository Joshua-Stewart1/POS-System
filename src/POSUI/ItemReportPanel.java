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

import POSPD.Item;
import POSPD.Store;

public class ItemReportPanel extends JPanel {
	private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("M/d/yy");
	private JTextPane textPane;
	private DatePicker datePicker;

	/**
	 * Create the panel.
	 */
	public ItemReportPanel(JFrame currentFrame, Store store)
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
					textPane.setText(generateItemReport(store, datePicker.getDate()));
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
		
		JLabel lblItemReport = new JLabel("Item Report");
		lblItemReport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemReport.setBounds(180, 10, 150, 30);
		add(lblItemReport);
		
		textPane = new JTextPane();
		textPane.setBounds(50, 110, 370, 190);
		add(textPane);
		
		datePicker = new DatePicker();
		datePicker.setBounds(170, 60, 160, 25);
		add(datePicker);
		
	}
	
	public String generateItemReport(Store store, LocalDate date)
	{
		String newLine = "\n";
		String tab = "\t";
		String report = "";
		
		BigDecimal totalSales = new BigDecimal("0.00");
		int totalItemsSold = 0;
		
		report += "Item Report for:" + date.format(dateTimeFormat) + newLine;
		report += newLine + "Number" + tab + "Name" + tab + tab + "Sales" + tab + "# Sold" + newLine;
		for (Item i : store.getItems().values())
		{
			report += i.getNumber() + tab + i.getDescription();
			if(i.getDescription().length() < 11)
			{
				report += tab;
			}
			report += tab + i.getSalesForDate(date) + tab + i.getNumSoldForDate(date) + newLine;
			totalSales = totalSales.add(i.getSalesForDate(date));
			totalItemsSold += i.getNumSoldForDate(date);
			
		}
		report += newLine + "Total:" + tab + tab + tab +totalSales + tab + totalItemsSold + tab;
		return report;
	}
}
