package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Item represents any item sold buy the store
 */
public class Item
{

	/**
	 * The number representing an item
	 */
	private String number;
	/**
	 * A description about the item
	 */
	private String description;
	/**
	 * the sale lines that the item appears in
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * The UPCs associated with the item
	 */
	private TreeMap<String, UPC> uPCs;
	/**
	 * The prices that can be charged for the item
	 */
	private TreeSet<Price> prices;
	/**
	 * The tax category the item falls under
	 */
	private TaxCategory taxCategory;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}

	public TreeMap<String, UPC> getUPCs()
	{
		return this.uPCs;
	}

	public TreeSet<Price> getPrices()
	{
		return this.prices;
	}

	public TaxCategory getTaxCategory()
	{
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory)
	{
		this.taxCategory = taxCategory;
	}

	/**
	 * A default constructor
	 */
	public Item()
	{
		saleLineItems = new ArrayList<SaleLineItem>();
		uPCs = new TreeMap<String, UPC>();
		prices = new TreeSet<Price>();
		setNumber("");
		setDescription("");
		setTaxCategory(new TaxCategory());
	}

	/**
	 * A constructor that initializes the item's attributes
	 * @param number The number representing an item
	 * @param description A description about the item
	 */
	public Item(String number, String description)
	{
		this();
		setNumber(number);
		setDescription(description);
	}

	/**
	 * addSaleLineItem - Add a sale line to the item
	 * @param sli The sale line to add to the item
	 */
	public void addSaleLineItem(SaleLineItem sli)
	{
		saleLineItems.add(sli);
	}

	/**
	 * removeSaleLineItem - Remove a sale line from the item
	 * @param sli The sale line to remove from the item
	 */
	public void removeSaleLineItem(SaleLineItem sli)
	{
		saleLineItems.remove(sli);
	}
	
	/**
	 * addPrice - Add a price to the item
	 * @param price The price to add to the item
	 */
	public void addPrice(Price price)
	{
		prices.add(price);
	}

	/**
	 * removePrice - Remove a price from the item
	 * @param price The price to remove from the item
	 */
	public void removePrice(Price price)
	{
		prices.remove(price);
	}

	/**
	 * addUPC - Add a UPC to the item
	 * @param upc The UPC to add to the item
	 */
	public void addUPC(UPC upc)
	{
		uPCs.put(upc.getUPC(), upc);
	}

	/**
	 * removeUPC - Remove a UPC associated with item
	 * @param upc The UPC to remove from the item
	 */
	public void removeUPC(UPC upc)
	{
		uPCs.remove(upc.getUPC());
	}

	/**
	 * getPriceForDate - Returns the price of the item for a given date
	 * @param date The date to check the price for
	 */
	public Price getPriceForDate(LocalDate date)
	{
		Price priceForDate = new Price();
		
		for(Price p : prices)
		{
			if(p.isEffective(date))
			{
				priceForDate = p;
			}
		}
		
		return priceForDate;
	}

	/**
	 * getTaxRateForDate - Get the tax rate for a given date
	 * @param date The date to check the tax rate for
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		return taxCategory.getTaxRateForDate(date);
	}

	/**
	 * calcAmountForDateQty - Calculate the amount paid for a given quantity of the item on a given date
	 * @param date The date to get the price and tax rate for
	 * @param quantity The amount of the item to be purchased
	 */
	public BigDecimal calcAmountForDateQty(LocalDate date, int quantity)
	{
		Price currentPrice = getPriceForDate(date);
		
		return currentPrice.calcAmountForQty(quantity);
	}
	
	/**
	 * getSalesForDate - Calculate the amount of money made for an item on a given date
	 * @param date The date to get the sales for
	 * @return The total amount of money made on the item on the given date
	 */
	public BigDecimal getSalesForDate(LocalDate date)
	{
		BigDecimal salesTotal = new BigDecimal("0.00");
		
		for(SaleLineItem sli: saleLineItems)
		{
			if(sli.getSale().getDateTime().toLocalDate().equals(date))
			{
				salesTotal = salesTotal.add(sli.calcSubTotal());
			}
		}
		
		return salesTotal;
	}
	
	/**
	 * getNumSoldForDate - Calculate the amount an item sold on a given date
	 * @param date The date to get the sales for
	 * @return The total amount of the item sold on the given date
	 */
	public int getNumSoldForDate(LocalDate date)
	{
		int numSales = 0;
		
		for(SaleLineItem sli: saleLineItems)
		{
			if(sli.getSale().getDateTime().toLocalDate().equals(date))
			{
				numSales += sli.getQuantity();
			}
		}
		
		return numSales;
	}

	/**
	 * toString - Creates a string representing the information of the item
	 * @return A string representing the information of the item
	 */
	public String toString()
	{
		String result = number + " " + description + " ";
		
		result = result + "Price:" + getPriceForDate(LocalDate.now()) + " ";
		result = result + "TaxRate:" +  getTaxRateForDate(LocalDate.now()) + " ";
		
		UPC upc = null;
		
		for(UPC u : getUPCs().values())
		{
			upc = u;
		}
		
		return    result + upc;
	}

}