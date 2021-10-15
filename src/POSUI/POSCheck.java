package POSUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.Cash;
import POSPD.Check;
import POSPD.Payment;
import POSPD.Sale;
import POSPD.Store;

public class POSCheck extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblError;
	
	/**
	 * Create the panel.
	 */
	public POSCheck(JFrame currentFrame, JPanel previousPanel, Store store, Sale sale) {
		setLayout(null);
		setBounds(145, 70, 345, 240);
		
		JPanel currentPanel = this;
		
		JLabel lblEnterCashPayment = new JLabel("Enter Cash Payment");
		lblEnterCashPayment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEnterCashPayment.setBounds(100, 10, 150, 30);
		add(lblEnterCashPayment);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_3.getText().isEmpty())
				{
					try
					{
						BigDecimal amtTendered = new BigDecimal(textField.getText());
						Check check = new Check();
						check.setAmtTendered(amtTendered);
						check.setAmount(sale.calcAmountForPayment(amtTendered));
						check.setRoutingNumber(textField_1.getText());
						check.setAccountNumber(textField_2.getText());
						check.setCheckNumber(textField_3.getText());
						sale.addPayment(check);
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						lblError.setVisible(false);

						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(previousPanel);
						currentFrame.getContentPane().revalidate();
						currentFrame.repaint();
					}
					catch(NumberFormatException n)
					{
						lblError.setText("Error - Invalid Amount Entry");
						lblError.setVisible(true);
					}
				}
				else
				{
					lblError.setText("Error - Missing Field");
					lblError.setVisible(true);
				}
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSave.setBounds(30, 200, 85, 20);
		add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				previousPanel.remove(currentPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(previousPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(230, 200, 85, 20);
		add(btnCancel);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAmount.setBounds(35, 70, 110, 20);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(155, 70, 100, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblRoutingNum = new JLabel("Routing Num:");
		lblRoutingNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRoutingNum.setBounds(35, 100, 110, 20);
		add(lblRoutingNum);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 100, 100, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAccountNum = new JLabel("Account Num:");
		lblAccountNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccountNum.setBounds(35, 130, 110, 20);
		add(lblAccountNum);
		
		textField_2 = new JTextField();
		textField_2.setBounds(155, 130, 100, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCheckNum = new JLabel("Check Num:");
		lblCheckNum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCheckNum.setBounds(35, 160, 110, 20);
		add(lblCheckNum);
		
		textField_3 = new JTextField();
		textField_3.setBounds(155, 160, 100, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setBounds(80, 45, 180, 15);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblError);
		lblError.setVisible(false);
	}

}
