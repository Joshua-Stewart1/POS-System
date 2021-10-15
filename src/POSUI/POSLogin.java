package POSUI;

import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Register;
import POSPD.Session;
import POSPD.Store;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

public class POSLogin extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JComboBox<Cashier> comboBox;
	private JComboBox<Register> comboBox_1;
	private JLabel lblErrormessage;

	/**
	 * Create the panel.
	 */
	public POSLogin(JFrame currentFrame, Store store) {
		setLayout(null);
		
		setBounds(100, 100, 500, 400);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox.getSelectedItem() != null && comboBox_1.getSelectedItem() != null && !textField.getText().isEmpty())
				{
					Register register = (Register)comboBox_1.getSelectedItem();
					Cashier cashier = (Cashier)comboBox.getSelectedItem();
					
					if(passwordField.getText().equals(cashier.getPassword()))
					{
						register.getCashDrawer().setCashAmount(new BigDecimal(textField.getText()));
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new POSSale(currentFrame, store, new Session(cashier, register)));
						currentFrame.getContentPane().revalidate();
					}
					else
					{
						lblErrormessage.setText("Error - Password Invalid");
						lblErrormessage.setVisible(true);
					}
				}
				else
				{
					lblErrormessage.setText("Missing Field");
					lblErrormessage.setVisible(true);
				}
			}
		});

		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnLogin.setBounds(85, 320, 85, 20);
		add(btnLogin);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(285, 320, 85, 20);
		add(btnCancel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLogin.setBounds(200, 10, 50, 30);
		add(lblLogin);
		
		JLabel lblCashierNumber = new JLabel("Cashier Number:");
		lblCashierNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCashierNumber.setBounds(50, 120, 100, 15);
		add(lblCashierNumber);
		
		DefaultComboBoxModel<Cashier> cList = new DefaultComboBoxModel<Cashier>();
		cList.addAll(store.getCashiers().values());
		
		comboBox = new JComboBox<Cashier>(cList);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setBounds(160, 115, 120, 25);
		add(comboBox);
		
		JLabel lblRegisterNumber = new JLabel("Register Number:");
		lblRegisterNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRegisterNumber.setBounds(50, 160, 120, 20);
		add(lblRegisterNumber);
		
		DefaultComboBoxModel<Register> rList = new DefaultComboBoxModel<Register>();
		rList.addAll(store.getRegisters().values());
		
		comboBox_1 = new JComboBox<Register>(rList);
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_1.setBounds(160, 155, 120, 25);
		add(comboBox_1);
		
		JLabel lblStartingCash = new JLabel("Starting Cash:");
		lblStartingCash.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblStartingCash.setBounds(50, 200, 100, 20);
		add(lblStartingCash);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(160, 200, 120, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPassword.setBounds(50, 240, 100, 15);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField.setBounds(160, 240, 120, 20);
		add(passwordField);
		
		lblErrormessage = new JLabel("Error - Password Invalid");
		lblErrormessage.setForeground(Color.RED);
		lblErrormessage.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblErrormessage.setBounds(150, 80, 210, 15);
		add(lblErrormessage);
		lblErrormessage.setVisible(false);
		
	}
}
