package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.UPC;

public class UPCEditPanel extends JPanel {
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public UPCEditPanel(JFrame currentFrame, JPanel currentPanel, Item item, UPC upc, Boolean isAdd) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				upc.setUPC(textField.getText());
				
				if(isAdd)
				{
					item.addUPC(upc);;
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
		
		JLabel lblUPCEdit = new JLabel("UPC Edit");
		lblUPCEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUPCEdit.setBounds(180, 10, 150, 30);
		add(lblUPCEdit);
		
		JLabel lblUPCCode = new JLabel("UPC Code:");
		lblUPCCode.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUPCCode.setBounds(70, 115, 80, 15);
		add(lblUPCCode);
		
		textField = new JTextField(upc.getUPC());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(140, 115, 130, 20);
		add(textField);
		textField.setColumns(10);
	}
}
