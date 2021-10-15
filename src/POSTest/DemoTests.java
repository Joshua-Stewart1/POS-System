package POSTest;

import POSPD.Cashier;
import POSPD.Item;
import POSPD.Register;
import POSPD.Session;
import POSPD.Store;
import POSPD.UPC;

public class DemoTests 
{
	
	public static void ac_1_test(Store store)
	{
		System.out.println("AC Criteria #1");
		System.out.println("==============");
		System.out.println();
		System.out.println("==============");
		System.out.println("Store");
		System.out.println("==============");
		System.out.println(store);
		System.out.println("==============");
		System.out.println("Items");
		System.out.println("==============");
		for (Item i : store.getItems().values())
		{
			UPC upc = new UPC();
			
			for(UPC u : i.getUPCs().values())
			{
				upc = u;
			}
			System.out.print(upc + " ");
			System.out.println(i);
			
		}
		System.out.println("==============");
		System.out.println();
	}
	
	public static void ac_2_test(Store store)
	{
		System.out.println("AC Criteria #2");
		System.out.println("==============");
		System.out.println();
		System.out.println("==============");
		System.out.println("Store");
		System.out.println("==============");
		System.out.println(store);
		System.out.println("==============");
		System.out.println("Cashiers");
		System.out.println("==============");
		for (Cashier c : store.getCashiers().values())
		{
			System.out.println(c);
		}
		System.out.println("==============");
		System.out.println();
	}
	
	public static void ac_3_test(Store store)
	{
		System.out.println("AC Criteria #3");
		System.out.println("==============");
		System.out.println();
		System.out.println("==============");
		System.out.println("Store");
		System.out.println("==============");
		System.out.println(store);
		System.out.println("==============");
		System.out.println("Registers");
		System.out.println("==============");
		for (Register r : store.getRegisters().values())
		{
			System.out.println(r);
		}
		System.out.println("==============");
		System.out.println();
	}
	
	public static void ac_4_test(Store store)
	{
		System.out.println("AC Criteria #4");
		System.out.println("==============");
		System.out.println();
		System.out.println("==============");
		System.out.println("Store");
		System.out.println("==============");
		System.out.println(store);
		System.out.println("==============");
		System.out.println("Sessions");
		System.out.println("==============");
		for (Session s : store.getSessions())
		{
			System.out.println(s);
		}
		System.out.println("==============");
		System.out.println();
	}
	
	public static void ac_2_1_test(Store store)
	{
		System.out.println(store);
		System.out.println("==============");
		System.out.println("Cashiers");
		System.out.println("==============");
		for (Cashier c : store.getCashiers().values())
		{
			System.out.println(c);
		}
		System.out.println("==============");
		System.out.println("Registers");
		System.out.println("==============");
		for (Register r : store.getRegisters().values())
		{
			System.out.println(r);
		}
		System.out.println("==============");
		System.out.println("Items");
		System.out.println("==============");
		for (Item i : store.getItems().values())
		{
			System.out.println(i);
		}
		System.out.println("==============");
		System.out.println("Sessions");
		System.out.println("==============");
		for (Session s : store.getSessions())
		{
			System.out.println(s);
			System.out.println();
		}
		System.out.println("==============");
	}
}
