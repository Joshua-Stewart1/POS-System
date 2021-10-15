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

import POSPD.TaxCategory;
import POSPD.TaxRate;
import javax.swing.JTextField;

public class TaxRateEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(JFrame currentFrame, JPanel currentPanel, TaxCategory taxCategory, TaxRate taxRate, Boolean isAdd) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("M/d/yy");
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				taxRate.setEffectiveDate(LocalDate.parse(textField_1.getText(), dateTimeFormat));
				taxRate.setTaxRate(new BigDecimal(textField.getText()));
				
				if(isAdd)
				{
					taxCategory.addTaxRate(taxRate);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});

		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSave.setBounds(85, 320, 85, 20);
		add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(285, 320, 85, 20);
		add(btnCancel);
		
		JLabel lblTaxRateEdit = new JLabel("Tax Rate Edit");
		lblTaxRateEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTaxRateEdit.setBounds(180, 10, 150, 30);
		add(lblTaxRateEdit);
		
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRate.setBounds(70, 115, 45, 15);
		add(lblRate);
		
		textField = new JTextField(taxRate.getTaxRate().toString());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(120, 115, 130, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDate.setBounds(70, 165, 45, 15);
		add(lblDate);
		
		textField_1 = new JTextField(dateTimeFormat.format(taxRate.getEffectiveDate()));
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setBounds(120, 165, 130, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
	}
}
