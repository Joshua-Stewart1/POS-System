package POSUI;

import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Item;
import POSPD.Register;
import POSPD.Sale;
import POSPD.SaleLineItem;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Color;

public class POSSale extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblInvalidUpc;
	private JButton btnEndSession;
	DefaultListModel<SaleLineItem> listModel;
	JList<SaleLineItem> list;
	
	private Sale sale;

	/**
	 * Create the panel.
	 */
	public POSSale(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JPanel currentPanel = this;
		
		sale = new Sale();
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSale.setBounds(200, 30, 50, 15);
		add(lblSale);
		
		JLabel lblCashier = new JLabel("Cashier: " + session.getCashier());
		lblCashier.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCashier.setBounds(20, 30, 120, 15);
		add(lblCashier);

		
		JLabel lblRegister = new JLabel("Register: " + session.getRegister());
		lblRegister.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRegister.setBounds(20, 50, 120, 15);
		add(lblRegister);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItem.setBounds(20, 85, 40, 20);
		add(lblItem);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Item item = new Item();
				item = store.findItemForUPC(textField.getText());
				
				if(!item.getNumber().equals(""))
				{
					Boolean isInList = false;
					int quantity;
					try
					{
						quantity = Integer.parseInt(textField_6.getText());
					}
					catch(NumberFormatException n)
					{
						quantity = 1;
						textField_6.setText("1");
					}
					

					for (SaleLineItem s: sale.getSaleLineItems())
					{
						if(s.getItem().equals(item))
						{
							isInList = true;
							s.setQuantity(s.getQuantity() + quantity);
						}
					}
					
					if(!isInList)
					{
						SaleLineItem sli = new SaleLineItem(sale, item, Integer.toString(quantity));
					}
					lblInvalidUpc.setVisible(false);
					btnEndSession.setEnabled(false);
				}
				else
				{
					lblInvalidUpc.setVisible(true);
				}
				
				textField.setText("");
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		textField.setBounds(60, 85, 100, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblQuantity.setBounds(170, 85, 60, 20);
		add(lblQuantity);
		
		textField_6 = new JTextField("1");
		textField_6.setBounds(230, 85, 40, 20);
		add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, currentPanel, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPayment.setBounds(20, 285, 120, 20);
		add(btnPayment);
		
		JButton btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				if(sale.isPaymentEnough())
				{
					btnCompleteSale.setEnabled(true);
				}
				else
				{
					btnCompleteSale.setEnabled(false);
				}
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		btnCompleteSale.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				session.addSale(sale);
				sale = new Sale();
				btnEndSession.setEnabled(true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnCompleteSale.setBounds(145, 285, 120, 20);
		add(btnCompleteSale);
		
		JButton btnCancelSale = new JButton("Cancel Sale");
		btnCancelSale.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sale = new Sale();
				btnEndSession.setEnabled(true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnCancelSale.setBounds(20, 325, 120, 20);
		add(btnCancelSale);
		
		btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSEndSession(currentFrame, store, session));
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnEndSession.setBounds(145, 325, 120, 20);
		add(btnEndSession);
		
		JLabel lblSubtotal = new JLabel("SubTotal:");
		lblSubtotal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubtotal.setBounds(300, 115, 60, 20);
		add(lblSubtotal);
		
		textField_1 = new JTextField();
		textField_1.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_1.setText(sale.calcSubTotal().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_1.setBounds(370, 115, 100, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTax.setBounds(330, 155, 30, 20);
		add(lblTax);
		
		textField_2 = new JTextField();
		textField_2.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_2.setText(sale.calcTax().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_2.setBounds(370, 155, 100, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTotal.setBounds(320, 195, 40, 20);
		add(lblTotal);
		
		textField_3 = new JTextField();
		textField_3.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_3.setText(sale.calcTotal().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_3.setBounds(370, 195, 100, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAmtTendered = new JLabel("Amt Tendered:");
		lblAmtTendered.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAmtTendered.setBounds(280, 265, 90, 20);
		add(lblAmtTendered);
		
		textField_4 = new JTextField();
		textField_4.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				textField_4.setText(sale.calcAmtTendered().toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_4.setBounds(370, 265, 100, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblChange.setBounds(320, 305, 50, 20);
		add(lblChange);
		
		textField_5 = new JTextField();
		textField_5.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				BigDecimal changeDisplay = new BigDecimal("0.00");
				if(changeDisplay.compareTo(sale.calcChange()) < 0)
				{
					changeDisplay = sale.calcChange();
				}
				textField_5.setText(changeDisplay.toString());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		textField_5.setBounds(370, 305, 100, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		JCheckBox chckbxTaxFree = new JCheckBox("Tax Free");
		chckbxTaxFree.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(chckbxTaxFree.isSelected())
				{
					sale.setTaxFree(true);
				}
				else
				{
					sale.setTaxFree(false);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		chckbxTaxFree.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		chckbxTaxFree.setBounds(330, 60, 95, 20);
		add(chckbxTaxFree);
		
		listModel = new DefaultListModel<SaleLineItem>();
		for (SaleLineItem s: sale.getSaleLineItems())
				listModel.addElement(s);
					
		//JList with list of Registers
		list = new JList<SaleLineItem>(listModel);
		list.addAncestorListener(new AncestorListener()
		{
			public void ancestorAdded(AncestorEvent event)
			{
				listModel = new DefaultListModel<SaleLineItem>();
				for (SaleLineItem s: sale.getSaleLineItems())
						listModel.addElement(s);
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		list.setBounds(25, 115, 265, 150);
		add(list);
		
		lblInvalidUpc = new JLabel("Invalid UPC");
		lblInvalidUpc.setForeground(Color.RED);
		lblInvalidUpc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblInvalidUpc.setBounds(170, 55, 80, 15);
		add(lblInvalidUpc);
		lblInvalidUpc.setVisible(false);
	}
}
