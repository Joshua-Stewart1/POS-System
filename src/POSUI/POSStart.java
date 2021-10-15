package POSUI;

import POSPD.Store;

public class POSStart {

	public static void main(String[] args)
	{
		Store store = new Store();
		store.load();
		POSFrame.open(store);
	}
	
}
