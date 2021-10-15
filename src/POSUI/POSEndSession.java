package POSUI;

import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;

public class POSEndSession extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblError;
	private JButton btnEndSession;

	/**
	 * Create the panel.
	 */
	public POSEndSession(JFrame currentFrame, Store store, Session session)
	{
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblSessionSummary = new JLabel("Session Summary");
		lblSessionSummary.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSessionSummary.setBounds(180, 10, 140, 30);
		add(lblSessionSummary);
		
		JLabel lblCashier = new JLabel("Cashier:     " + session.getCashier());
		lblCashier.setBounds(40, 80, 200, 20);
		lblCashier.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register:    " + session.getRegister());
		lblRegister.setBounds(40, 110, 100, 20);
		lblRegister.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblRegister);
		
		BigDecimal totalSales = new BigDecimal("0.00");
		int numSales = 0;
		
		for (Sale s : session.getSales())
		{
			totalSales = totalSales.add(s.calcTotal());
			numSales++;
		}
		
		JLabel lblNumberSales = new JLabel("Number Sales:");
		lblNumberSales.setBounds(40, 170, 100, 20);
		lblNumberSales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblNumberSales);
		
		textField = new JTextField(Integer.toString(numSales));
		textField.setBounds(150, 170, 120, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblTotalSales = new JLabel("Total Sales:");
		lblTotalSales.setBounds(40, 200, 100, 20);
		lblTotalSales.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblTotalSales);
		
		textField_1 = new JTextField(totalSales.toString());
		textField_1.setBounds(150, 200, 120, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterCash = new JLabel("Enter Cash:");
		lblEnterCash.setBounds(40, 230, 100, 20);
		lblEnterCash.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblEnterCash);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BigDecimal endCash = new BigDecimal("0.00");
				try
				{
					endCash = new BigDecimal(textField_2.getText());
					lblError.setVisible(false);
					btnEndSession.setEnabled(true);
				}
				catch(NumberFormatException n)
				{
					lblError.setVisible(true);
				}
				
				session.setEndCash(endCash);
				textField_3.setText(session.calcCashCountDiff().toString());
			}
		});
		textField_2.setBounds(150, 230, 120, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCashCountDiff = new JLabel("Cash Count Diff :");
		lblCashCountDiff.setBounds(40, 260, 110, 20);
		lblCashCountDiff.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblCashCountDiff);

		textField_3 = new JTextField();
		textField_3.setBounds(150, 260, 120, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblError = new JLabel("Error - Invalid Cash Entry");
		lblError.setForeground(Color.RED);
		lblError.setBounds(200, 100, 170, 15);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(lblError);
		lblError.setVisible(false);
		
		btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(180, 320, 120, 20);
		btnEndSession.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(btnEndSession);
		btnEndSession.setEnabled(false);
		

	}
}
