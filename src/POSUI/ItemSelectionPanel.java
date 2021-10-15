package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSPD.Item;
import POSPD.Store;
import POSPD.TaxCategory;

public class ItemSelectionPanel extends JPanel {
	DefaultListModel<Item> listModel;
	JList<Item> list;
	
	/**
	 * Create the panel.
	 */
	public ItemSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblItemSelect = new JLabel("Item Select");
		lblItemSelect.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemSelect.setBounds(180, 10, 150, 30);
		add(lblItemSelect);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, new Item(), true));
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
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, list.getSelectedValue(), false));
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
				store.removeItem(list.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.setBounds(285, 320, 85, 20);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		//Add all of the Items to listModel
		listModel = new DefaultListModel<Item>();
		for (Item i: store.getItems().values())
				listModel.addElement(i);
					
		//JList with list of Items
		list = new JList<Item>(listModel);
		list.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
					
					if (store.isItemUsed((Item)list.getSelectedValue()))
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
