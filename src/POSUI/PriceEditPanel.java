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
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.Price;
import POSPD.PromoPrice;

import javax.swing.JCheckBox;

public class PriceEditPanel extends JPanel {

	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	JCheckBox chckbxPromoPrice;
	
	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, JPanel currentPanel, Item item, Price price, Boolean isAdd) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("M/d/yy");
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				price.setPrice(new BigDecimal(textField.getText()));
				price.setEffectiveDate(LocalDate.parse(textField_1.getText(), dateTimeFormat));
				
				if(isAdd)
				{
					if(chckbxPromoPrice.isSelected())
					{
						PromoPrice promo = new PromoPrice();
						promo.setPrice(new BigDecimal(textField.getText()));
						promo.setEffectiveDate(LocalDate.parse(textField_1.getText(), dateTimeFormat));
						promo.setEndDate(LocalDate.parse(textField_2.getText(), dateTimeFormat));
						item.addPrice(promo);
					}
					else
					{
						item.addPrice(price);
					}
				}
				else
				{
					if(chckbxPromoPrice.isSelected())
					{
						((PromoPrice)price).setEndDate(LocalDate.parse(textField_2.getText(), dateTimeFormat));
					}
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
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPrice.setBounds(70, 135, 45, 15);
		add(lblPrice);
		
		textField = new JTextField(price.getPrice().toString());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(170, 135, 130, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEffectiveDate.setBounds(70, 185, 100, 15);
		add(lblEffectiveDate);
		
		textField_1 = new JTextField(dateTimeFormat.format(price.getEffectiveDate()));
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setBounds(170, 185, 130, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEndDate.setBounds(70, 235, 65, 15);
		add(lblEndDate);
		lblEndDate.setVisible(false);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_2.setBounds(170, 235, 130, 20);
		add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		
		chckbxPromoPrice = new JCheckBox("Promo Price");
		chckbxPromoPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(chckbxPromoPrice.isSelected())
				{
					lblEndDate.setVisible(true);
					textField_2.setVisible(true);
				}
				else
				{
					lblEndDate.setVisible(false);
					textField_2.setVisible(false);
				}	
			}
		});
		chckbxPromoPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		chckbxPromoPrice.setBounds(150, 80, 130, 20);
		add(chckbxPromoPrice);
		
		if(isAdd)
		{
			chckbxPromoPrice.setEnabled(true);
		}
		else
		{
			chckbxPromoPrice.setEnabled(false);
			
			if(price instanceof PromoPrice)
			{
				chckbxPromoPrice.setSelected(true);
				lblEndDate.setVisible(true);
				textField_2.setVisible(true);
				textField_2.setText(dateTimeFormat.format(((PromoPrice)price).getEndDate()));
			}
			else
			{
				chckbxPromoPrice.setSelected(false);
				lblEndDate.setVisible(false);
				textField_2.setVisible(false);
			}
		}
	}
}
