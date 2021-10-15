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

import POSPD.Cashier;
import POSPD.Store;
import POSPD.TaxCategory;

public class TaxCategorySelectionPanel extends JPanel {

	DefaultListModel<TaxCategory> listModel;
	JList<TaxCategory> list;
	
	/**
	 * Create the panel.
	 */
	public TaxCategorySelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblTaxCategorySelect = new JLabel("Tax Category Select");
		lblTaxCategorySelect.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTaxCategorySelect.setBounds(180, 10, 150, 30);
		add(lblTaxCategorySelect);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, new TaxCategory(), true));
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
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, list.getSelectedValue(), false));
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
				store.removeTaxCategory(list.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.setBounds(285, 320, 85, 20);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		//Add all of the Tax Categories to listModel
		listModel = new DefaultListModel<TaxCategory>();
		for (TaxCategory tc: store.getTaxCategories().values())
				listModel.addElement(tc);
					
		//JList with list of Tax Categories
		list = new JList<TaxCategory>(listModel);
		list.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
					
					if (store.isTaxCategoryUsed((TaxCategory)list.getSelectedValue()))
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
