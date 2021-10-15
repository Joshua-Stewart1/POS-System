package POSUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblStoreEdit = new JLabel("Store Edit");
		lblStoreEdit.setBounds(180, 10, 150, 30);
		lblStoreEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(lblStoreEdit);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(45, 170, 115, 25);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		add(lblName);
		
		textField = new JTextField(store.getName());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(105, 170, 165, 25);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(85, 320, 85, 20);
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(285, 320, 85, 20);
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
		add(btnCancel);

	}
}
