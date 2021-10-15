package POSUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;

import javax.swing.JList;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TaxCategoryEditPanel extends JPanel {
	DefaultListModel<TaxRate> listModel;
	JList<TaxRate> list;
	JPanel currentPanel = this;
	
	private JTextField textField;
	
	
	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				taxCategory.setCategory(textField.getText());
				
				if(isAdd)
				{
					store.addTaxCategory(taxCategory);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
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
				currentFrame.getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCancel.setBounds(285, 320, 85, 20);
		add(btnCancel);
		
		JLabel lblTaxCategoryEdit = new JLabel("Tax Category Edit");
		lblTaxCategoryEdit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTaxCategoryEdit.setBounds(180, 10, 150, 30);
		add(lblTaxCategoryEdit);
		
		JLabel lblName = new JLabel("Category:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(40, 115, 65, 15);
		add(lblName);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField.setBounds(105, 115, 130, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, taxCategory, new TaxRate(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAdd.setBounds(220, 260, 75, 20);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, taxCategory, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEdit.setBounds(300, 260, 75, 20);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				taxCategory.removeTaxRate(list.getSelectedValue());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.repaint();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDelete.setBounds(380, 260, 75, 20);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		//Add all of the Tax Rates to listModel
		listModel = new DefaultListModel<TaxRate>();
		for (TaxRate tr: taxCategory.getTaxRates())
				listModel.addElement(tr);
					
		//JList with list of Tax Rates
		list = new JList<TaxRate>(listModel);
		list.addAncestorListener(new AncestorListener()
		{
			public void ancestorAdded(AncestorEvent event)
			{
				listModel = new DefaultListModel<TaxRate>();
				for (TaxRate tr: taxCategory.getTaxRates())
						listModel.addElement(tr);
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
		list.setBounds(250, 115, 170, 120);
		add(list);
		
	}
}
