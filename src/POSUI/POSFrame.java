package POSUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POSPD.Store;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;

public class POSFrame extends JFrame {

	private JPanel contentPane;
	JFrame currentFrame;

	/**
	 * Launch the application.
	 */
	public static void open(Store store) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSFrame frame = new POSFrame(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public POSFrame(Store store) {
		currentFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaintenance = new JMenu("Maintenance");
		mnMaintenance.setFont(new Font("Times New Roman", Font.BOLD, 14));
		menuBar.add(mnMaintenance);
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mntmStore.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmStore);
		
		JMenuItem mntmTaxCategories = new JMenuItem("Tax Categories");
		mntmTaxCategories.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mntmTaxCategories.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmTaxCategories);
		
		JMenuItem mntmItems = new JMenuItem("Items");
		mntmItems.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mntmItems.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new ItemSelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmItems);
		
		JMenuItem mntmCashier = new JMenuItem("Cashiers");
		mntmCashier.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mntmCashier.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		mnMaintenance.add(mntmCashier);
		
		JMenuItem mntmRegisters = new JMenuItem("Registers");
		mntmRegisters.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mntmRegisters.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmRegisters);
		
		JMenu mnPos = new JMenu("POS");
		mnPos.setFont(new Font("Times New Roman", Font.BOLD, 14));
		menuBar.add(mnPos);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new POSLogin(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mntmLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mnPos.add(mntmLogin);
		
		JMenu mnReports = new JMenu("Reports");
		mnReports.setFont(new Font("Times New Roman", Font.BOLD, 14));
		menuBar.add(mnReports);
		
		JMenuItem mntmCashierReport = new JMenuItem("Cashier Report");
		mntmCashierReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new CashierReportPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mntmCashierReport.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mnReports.add(mntmCashierReport);
		
		JMenuItem mntmItemReport = new JMenuItem("Item Report");
		mntmItemReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new ItemReportPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mntmItemReport.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mnReports.add(mntmItemReport);
		
		JMenuItem mntmDailyReport = new JMenuItem("Daily Report");
		mntmDailyReport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				getContentPane().add(new DailyReportPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mntmDailyReport.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mnReports.add(mntmDailyReport);

		contentPane = new POSHomePanel(store);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
