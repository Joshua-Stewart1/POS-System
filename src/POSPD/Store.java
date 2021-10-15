package POSPD;

import java.util.*;

import POSDB.StoreDM;

/**
 * Store represents a particular store
 */
public class Store
{

	/**
	 * A number to represent which store it is
	 */
	private String number;
	/**
	 * The name associated with the store
	 */
	private String name;
	/**
	 * A list of all the tax categories for items in the store
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	/**
	 * A list of all of the items in the store
	 */
	private TreeMap<String, Item> items;
	/**
	 * A list of the cashiers at the store
	 */
	private TreeMap<String, Cashier> cashiers;
	/**
	 * A list of the registers at the store
	 */
	private TreeMap<String, Register> registers;
	/**
	 * A list of all of the cashier sessions for the store
	 */
	private ArrayList<Session> sessions;
	/**
	 * A list of all of the UPCs used in the store
	 */
	private TreeMap<String, UPC> upcs;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TreeMap<String, TaxCategory> getTaxCategories()
	{
		return this.taxCategories;
	}

	public TreeMap<String, Item> getItems()
	{
		return this.items;
	}

	public TreeMap<String, Cashier> getCashiers()
	{
		return this.cashiers;
	}

	public TreeMap<String, Register> getRegisters()
	{
		return this.registers;
	}

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}

	public TreeMap<String, UPC> getUpcs()
	{
		return this.upcs;
	}

	/**
	 * A default constructor
	 */
	public Store()
	{
		setName("");
		setNumber("");
		taxCategories = new TreeMap<String, TaxCategory>();
		items = new TreeMap<String, Item>();
		cashiers = new TreeMap<String, Cashier>();
		registers = new TreeMap<String, Register>();
		sessions = new ArrayList<Session>();
		upcs = new TreeMap<String, UPC>();
	}

	/**
	 * A constructor that initializes the values of the store
	 * @param number The number representing the store
	 * @param name The name associated with the store
	 */
	public Store(String number, String name)
	{
		this();
		setName(name);
		setNumber(number);
	}

	/**
	 * addItem - Adds an item to the store
	 * @param item The item to add
	 */
	public void addItem(Item item)
	{
		items.put(item.getNumber(), item);
	}

	/**
	 * removeItem - Remove an item from the store
	 * @param item The item to remove
	 */
	public void removeItem(Item item)
	{
		items.remove(item.getNumber());
	}

	/**
	 * addUPC - Add a UPC to the store
	 * @param upc The UPC to add
	 */
	public void addUPC(UPC upc)
	{
		upcs.put(upc.getUPC(), upc);
	}

	/**
	 * removeUPC - Remove a UPC from the store
	 * @param upc The UPC to remove
	 */
	public void removeUPC(UPC upc)
	{
		upcs.remove(upc.getUPC());
	}

	/**
	 * addRegister - Add a register to the store
	 * @param register The register to add
	 */
	public void addRegister(Register register)
	{
		registers.put(register.getNumber(), register);
	}

	/**
	 * removeRegister - Remove a register from the store
	 * @param register The register to remove
	 */
	public void removeRegister(Register register)
	{
		registers.remove(register.getNumber());
	}

	/**
	 * addTaxCategory - Add a tax category to the store
	 * @param taxCategory The tax category to add
	 */
	public void addTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * removeTaxCategory - Remove a tax category from the store
	 * @param taxCategory The tax category to remove
	 */
	public void removeTaxCategory(TaxCategory taxCategory)
	{
		taxCategories.remove(taxCategory.getCategory());
	}

	/**
	 * addCashier - Add a cashier to the store
	 * @param cashier The cashier to add
	 */
	public void addCashier(Cashier cashier)
	{
		cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * removeCashier - Remove a cashier from the store
	 * @param cashier The cashier to remove
	 */
	public void removeCashier(Cashier cashier)
	{
		cashiers.remove(cashier.getNumber());
	}
	
	/**
	 * addSession - Add a session to the store
	 * @param session The session to add
	 */
	public void addSession(Session session)
	{
		sessions.add(session);
	}

	/**
	 * removeSession - Remove a session from the store
	 * @param session The session to remove
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * findRegisterByNumber - Retrieve a given register based on its number
	 * @param number The number of register to find
	 * @return The register with the given number
	 */
	public Register findRegisterByNumber(String number)
	{
		Register reg = new Register();
		
		for(Register r :registers.values())
		{
			if(r.getNumber().equals(number))
			{
				reg = r;
			}
		}
		
		return reg;
	}

	/**
	 * findItemForUPC - Find an item based on its UPC
	 * @param upc The UPC of the item to find
	 * @return The item with the given UPC
	 */
	public Item findItemForUPC(String upc)
	{
		Item item = new Item();
		
		for(UPC u :upcs.values())
		{
			if(u.getUPC().equals(upc))
			{
				item = u.getItem();
			}
		}
		
		return item;
	}

	/**
	 * findItemForNumber - Find an item based on its identification number
	 * @param number The identification number for the item
	 * @return The item with the given identification number
	 */
	public Item findItemForNumber(String number)
	{
		Item item = new Item();
		
		for(Item i :items.values())
		{
			if(i.getNumber().equals(number))
			{
				item = i;
			}
		}
		
		return item;
	}

	/**
	 * findCashierForNumber - Find a cashier based on their ID number
	 * @param number The ID number of the cashier
	 * @return The cashier with the given ID number
	 */
	public Cashier findCashierForNumber(String number)
	{
		Cashier cashier = new Cashier();
		
		for(Cashier c :cashiers.values())
		{
			if(c.getNumber().equals(number))
			{
				cashier = c;
			}
		}
		
		return cashier;
	}

	/**
	 * findTaxCategoryByName - Find a tax category by its name
	 * @param category The name of the tax category
	 * @return The requested tax category
	 */
	public TaxCategory findTaxCategoryByName(String category)
	{
		TaxCategory taxCategory = new TaxCategory();
		
		for(TaxCategory c :taxCategories.values())
		{
			if(c.getCategory().equals(category))
			{
				taxCategory = c;
			}
		}
		
		return taxCategory;
	}
	
	/**
	 * isRegisterUsed - Check if a register has sessions associated with it
	 * @param register - The register to check for use
	 * @return True if the register is used
	 */
	public Boolean isRegisterUsed(Register register)
	{
		Boolean isUsed = false;
		
		for(Session s : sessions)
		{
			if(s.getRegister() == register)
			{
				isUsed = true;
			}
		}
		
		return isUsed;
	}
	
	/**
	 * isCashierUsed - Check if a cashier has sessions associated with it
	 * @param cashier - The cashier to check for use
	 * @return True if the cashier is used
	 */
	public Boolean isCashierUsed(Cashier cashier)
	{
		Boolean isUsed = false;
		
		for(Session s : sessions)
		{
			if(s.getCashier() == cashier)
			{
				isUsed = true;
			}
		}
		
		return isUsed;
	}
	
	/**
	 * isTaxCategoryUsed - Check if a tax category has items associated with it
	 * @param taxCategory - The tax category to check for use
	 * @return True if the tax category is used
	 */
	public Boolean isTaxCategoryUsed(TaxCategory taxCategory)
	{
		Boolean isUsed = false;
		
		for(Item i : items.values())
		{
			if(i.getTaxCategory() == taxCategory)
			{
				isUsed = true;
			}
		}
		return isUsed;
	}
	
	/**
	 * isItemUsed - Check if an item has sales associated with it
	 * @param item - The item to check for use
	 * @return True if the item is used
	 */
	public Boolean isItemUsed(Item item)
	{
		Boolean isUsed = false;
		
		for(Session s : sessions)
		{
			for(Sale sa : s.getSales())
			{
				for(SaleLineItem sli : sa.getSaleLineItems())
				{
					if(sli.getItem() == item)
					{
					isUsed = true;
					}
				}
			}
			
		}
		return isUsed;
	}
	
	/**
	 * load - Load store information from a file
	 */
	public void load()
	{
		StoreDM.loadStore(this);
	}

	/**
	 * toString - Creates a string representing the information of the store
	 * @return A string representing the store's information
	 */
	public String toString()
	{
		return name;
	}

}