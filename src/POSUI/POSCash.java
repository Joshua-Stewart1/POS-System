package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import POSPD.Cash;
import POSPD.Payment;
import POSPD.Sale;
import POSPD.Store;
import javax.swing.JTextField;
import java.awt.Color;

public class POSCash extends JPanel {
	private JTextField textField;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public POSCash(JFrame currentFrame, JPanel previousPanel, Store store, Sale sale) {
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
				try
				{
					BigDecimal amtTendered = new BigDecimal(textField.getText());
					Cash cash = new Cash();
					cash.setAmtTendered(amtTendered);
					cash.setAmount(sale.calcAmountForPayment(amtTendered));
					sale.addPayment(cash);
					textField.setText("");
					lblError.setVisible(false);

					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(previousPanel);
					currentFrame.getContentPane().revalidate();
					currentFrame.repaint();
				}
				catch(NumberFormatException n)
				{
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
		
		JLabel lblAmountTendered = new JLabel("Amount Tendered:");
		lblAmountTendered.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAmountTendered.setBounds(35, 70, 110, 20);
		add(lblAmountTendered);
		
		textField = new JTextField();
		textField.setBounds(155, 70, 100, 20);
		add(textField);
		textField.setColumns(10);
		
		lblError = new JLabel("Error - Invalid Amount Entry");
		lblError.setForeground(Color.RED);
		lblError.setBounds(80, 45, 180, 15);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblError);
		lblError.setVisible(false);
	}
}
