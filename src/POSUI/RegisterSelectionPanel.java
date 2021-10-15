package POSUI;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import POSPD.Register;
import POSPD.Store;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class RegisterSelectionPanel extends JPanel {

	DefaultListModel<Register> listModel;
	JList<Register> list;
	
	/**
	 * Create the panel.
	 */
	public RegisterSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblRegisterSelect = new JLabel("Register Select");
		lblRegisterSelect.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRegisterSelect.setBounds(180, 10, 150, 30);
		add(lblRegisterSelect);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, new Register(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAdd.setBounds(85, 320, 85, 20);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEdit.setBounds(185, 320, 85, 20);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				store.removeRegister(list.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.setBounds(285, 320, 85, 20);
		btnDelete.setEnabled(false);
		add(btnDelete);

		//Add all of the Registers to listModel.
		listModel = new DefaultListModel<Register>();
		for (Register r: store.getRegisters().values())
				listModel.addElement(r);
					
		//JList with list of Registers
		list = new JList<Register>(listModel);
		list.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
					
					if (store.isRegisterUsed((Register)list.getSelectedValue()))
					{
						btnDelete.setEnabled(false);
					}
					else
					{
						btnDelete.setEnabled(true);
					}
				}

			}
		});
		list.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		list.setBounds(85, 50, 300, 200);
		add(list);
		
	}
}
