package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import POSPD.Item;
import POSPD.Price;
import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.UPC;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ItemEditPanel extends JPanel {
	DefaultListModel<UPC> listModel;
	JList<UPC> list;
	DefaultListModel<Price> listModel1;
	JList<Price> list1;
	JComboBox<TaxCategory> comboBox;
	JPanel currentPanel = this;
	
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {

		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				item.setNumber(textField.getText());
				item.setDescription(textField_1.getText());
				item.setTaxCategory((TaxCategory)comboBox.getSelectedItem());
				
				if(isAdd)
				{
					store.addItem(item);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemSelectionPanel(currentFrame, store));
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
				currentFrame.getContentPane().add(new ItemSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(285, 320, 85, 20);
		add(btnCancel);
		
		JLabel lblItemEdit = new JLabel("Item Edit");
		lblItemEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblItemEdit.setBounds(180, 10, 150, 30);
		add(lblItemEdit);
		
		JLabel lblItemNumber = new JLabel("Item Number:");
		lblItemNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItemNumber.setBounds(10, 110, 85, 15);
		add(lblItemNumber);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDescription.setBounds(10, 150, 85, 15);
		add(lblDescription);
		
		JLabel lblTaxCategory = new JLabel("Tax Category:");
		lblTaxCategory.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTaxCategory.setBounds(10, 190, 85, 15);
		add(lblTaxCategory);
		
		textField = new JTextField(item.getNumber());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(100, 110, 120, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_1.setBounds(100, 150, 150, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		DefaultComboBoxModel<TaxCategory> tcList = new DefaultComboBoxModel<TaxCategory>();
		tcList.addAll(store.getTaxCategories().values());
		
		comboBox = new JComboBox<TaxCategory>(tcList);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setBounds(100, 190, 120, 25);	
		if(!isAdd)
		{
			comboBox.setSelectedItem(item.getTaxCategory());
		}
		
		add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, currentPanel, item, new UPC(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAdd.setBounds(240, 120, 75, 20);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, currentPanel, item, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEdit.setBounds(320, 120, 75, 20);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				store.removeUPC(list.getSelectedValue());
				item.removeUPC(list.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.setBounds(400, 120, 75, 20);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		//Add all of the UPCs to listModel
		listModel = new DefaultListModel<UPC>();
		for (UPC u: item.getUPCs().values())
				listModel.addElement(u);
					
		//JList with list of UPCs
		list = new JList<UPC>(listModel);
		list.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				listModel = new DefaultListModel<UPC>();
				for (UPC u: item.getUPCs().values())
						listModel.addElement(u);
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		list.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				}

			}
		});
		list.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		list.setBounds(285, 40, 170, 75);
		add(list);
		
		JButton btnAdd1 = new JButton("Add");
		btnAdd1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, item, new Price(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAdd1.setBounds(240, 230, 75, 20);
		add(btnAdd1);
		
		JButton btnEdit1 = new JButton("Edit");
		btnEdit1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, item, list1.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEdit1.setBounds(320, 230, 75, 20);
		btnEdit1.setEnabled(false);
		add(btnEdit1);
		
		JButton btnDelete1 = new JButton("Delete");
		btnDelete1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				item.removePrice(list1.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, item, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnDelete1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete1.setBounds(400, 230, 75, 20);
		btnDelete1.setEnabled(false);
		add(btnDelete1);
		
		//Add all of the prices to listModel
		listModel1 = new DefaultListModel<Price>();
		for (Price p: item.getPrices())
				listModel1.addElement(p);
					
		//JList with list of prices
		list1 = new JList<Price>(listModel1);
		list1.addAncestorListener(new AncestorListener() {
			
			public void ancestorAdded(AncestorEvent event) {
				
				listModel1 = new DefaultListModel<Price>();
				for (Price p: item.getPrices())
						listModel1.addElement(p);
				list1.setModel(listModel1);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		list1.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (list1.getSelectedValue() != null) 
				{
					btnEdit1.setEnabled(true);
					btnDelete1.setEnabled(true);
				}

			}
		});
		list1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		list1.setBounds(285, 150, 170, 75);
		add(list1);
	}
}
