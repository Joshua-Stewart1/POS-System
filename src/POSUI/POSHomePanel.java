package POSUI;

import javax.swing.JPanel;

import POSPD.Store;
import javax.swing.JLabel;
import java.awt.Font;

public class POSHomePanel extends JPanel
{
	/**
	 * Create the panel.
	 */
	public POSHomePanel(Store store)
	{
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblLabel = new JLabel(store.getName());
		lblLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLabel.setBounds(149, 164, 183, 56);
		add(lblLabel);
		
	}

}
