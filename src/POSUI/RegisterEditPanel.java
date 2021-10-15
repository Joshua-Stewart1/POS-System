package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Register;
import POSPD.Store;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, Boolean isAdd) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblRegisterEdit = new JLabel("Register Edit");
		lblRegisterEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRegisterEdit.setBounds(180, 10, 150, 30);
		add(lblRegisterEdit);
		
		JLabel lblRegisterNumber = new JLabel("Register Number:");
		lblRegisterNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRegisterNumber.setBounds(45, 170, 115, 25);
		add(lblRegisterNumber);
		
		textField = new JTextField(register.getNumber());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(155, 170, 165, 25);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				register.setNumber(textField.getText());
				
				if(isAdd)
				{
					store.addRegister(register);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
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
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(285, 320, 85, 20);
		add(btnCancel);
	}
}
