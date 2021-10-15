package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CashierEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cashier.getPerson().setName(textField.getText());
				cashier.setNumber(textField_1.getText());
				cashier.getPerson().setAddress(textField_2.getText());
				cashier.getPerson().setSSN(textField_3.getText());
				cashier.getPerson().setCity(textField_4.getText());
				cashier.getPerson().setState(textField_5.getText());
				cashier.getPerson().setZip(textField_6.getText());
				cashier.getPerson().setPhone(textField_7.getText());
				cashier.setPassword(passwordField.getText());
				
				if(isAdd)
				{
					store.addCashier(cashier);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
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
				currentFrame.getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(285, 320, 85, 20);
		add(btnCancel);
		
		JLabel lblCashierEdit = new JLabel("Cashier Edit");
		lblCashierEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCashierEdit.setBounds(180, 10, 150, 30);
		add(lblCashierEdit);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(40, 115, 45, 15);
		add(lblName);
		
		textField = new JTextField(cashier.getPerson().getName());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(105, 115, 130, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNumber.setBounds(40, 75, 65, 15);
		add(lblNumber);
		
		textField_1 = new JTextField(cashier.getNumber());
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setBounds(105, 75, 75, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAddress.setBounds(40, 155, 65, 15);
		add(lblAddress);
		
		textField_2 = new JTextField(cashier.getPerson().getAddress());
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_2.setBounds(105, 155, 130, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSsn.setBounds(220, 75, 45, 15);
		add(lblSsn);
		
		textField_3 = new JTextField(cashier.getPerson().getSSN());
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_3.setBounds(255, 75, 130, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCity.setBounds(40, 195, 65, 15);
		add(lblCity);
		
		textField_4 = new JTextField(cashier.getPerson().getCity());
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_4.setBounds(105, 195, 130, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblState.setBounds(245, 195, 45, 15);
		add(lblState);
		
		textField_5 = new JTextField(cashier.getPerson().getState());
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_5.setBounds(285, 195, 40, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblZip = new JLabel("ZIP:");
		lblZip.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblZip.setBounds(335, 195, 45, 15);
		add(lblZip);
		
		textField_6 = new JTextField(cashier.getPerson().getZip());
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_6.setBounds(375, 195, 65, 20);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhone.setBounds(40, 235, 45, 15);
		add(lblPhone);
		
		textField_7 = new JTextField(cashier.getPerson().getPhone());
		textField_7.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_7.setBounds(105, 235, 130, 20);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPassword.setBounds(40, 275, 65, 15);
		add(lblPassword);
		
		passwordField = new JPasswordField(cashier.getPassword());
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField.setBounds(105, 275, 130, 20);
		add(passwordField);
		
	}
}
