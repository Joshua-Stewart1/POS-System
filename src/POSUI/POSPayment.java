package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Sale;
import POSPD.Store;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class POSPayment extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPanel displayedPanel;

	/**
	 * Create the panel.
	 */
	public POSPayment(JFrame currentFrame, Store store, JPanel previousPanel, Sale sale)
	{
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JPanel currentPanel = this;
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPayment.setBounds(200, 30, 70, 15);
		add(lblPayment);
		
		JLabel lblPaymentDue = new JLabel("Payment Due:");
		lblPaymentDue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPaymentDue.setBounds(25, 70, 110, 15);
		add(lblPaymentDue);
		
		textField = new JTextField();
		textField.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField.setText(sale.calcTotal().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField.setBounds(25, 100, 110, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAmtTendered = new JLabel("Amount Tendered:");
		lblAmtTendered.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAmtTendered.setBounds(25, 130, 110, 15);
		add(lblAmtTendered);
		
		textField_1 = new JTextField();
		textField_1.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_1.setText(sale.calcAmtTendered().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_1.setBounds(25, 160, 110, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(displayedPanel != null)
				{
					remove(displayedPanel);
				}
				displayedPanel = new POSCash(currentFrame, currentPanel, store, sale);
				add(displayedPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCash.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCash.setBounds(35, 210, 100, 20);
		add(btnCash);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(displayedPanel != null)
				{
					remove(displayedPanel);
				}
				displayedPanel = new POSCheck(currentFrame, currentPanel, store, sale);
				add(displayedPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCheck.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCheck.setBounds(35, 250, 100, 20);
		add(btnCheck);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(displayedPanel != null)
				{
					remove(displayedPanel);
				}
				displayedPanel = new POSCredit(currentFrame, currentPanel, store, sale);
				add(displayedPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCredit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCredit.setBounds(35, 290, 100, 20);
		add(btnCredit);
		
		JButton btnCompletePayment = new JButton("Payment Complete");
		btnCompletePayment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(previousPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnCompletePayment.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				if(sale.isPaymentEnough())
				{
					btnCompletePayment.setEnabled(true);
				}
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		btnCompletePayment.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCompletePayment.setBounds(175, 325, 150, 20);
		add(btnCompletePayment);
		btnCompletePayment.setEnabled(false);
	}
}
