package POSPD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * SaleLineItem represents the line of information for a given item
 */
public class SaleLineItem
{

	/**
	 * The item for the sale
	 */
	private Item item;
	/**
	 * The number of items on the sale
	 */
	private int quantity;
	/**
	 * The sale that the saleLineItem is associated with
	 */
	private Sale sale;

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public Sale getSale()
	{
		return this.sale;
	}

	public void setSale(Sale sale)
	{
		this.sale = sale;
	}

	/**
	 * A default constructor
	 */
	public SaleLineItem()
	{
		setQuantity(0);
	}

	/**
	 * A constructor that initializes the values of a sale line item
	 * @param sale The sale that the saleLineItem is associated with
	 * @param item The item for the sale
	 * @param quantity The number of items on the sale
	 */
	public SaleLineItem(Sale sale, Item item, String quantity)
	{
		this();
		setSale(sale);
		setItem(item);
		item.addSaleLineItem(this);
		setQuantity(Integer.parseInt(quantity));
		sale.addSaleLineItem(this);
	}

	/**
	 * calcSubTotal - calculates the price for a given sale line
	 * @return The subtotal for the given sale line
	 */
	public BigDecimal calcSubTotal()
	{
		return item.calcAmountForDateQty(LocalDate.now(), quantity).setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * calcTax - Calculates the tax for the given item
	 * @return The amount of tax charged on the item
	 */
	public BigDecimal calcTax()
	{
		return calcSubTotal().multiply(item.getTaxRateForDate(LocalDate.now())).setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * toString - Creates a string representing the sale line
	 * @return A string representing the sale line
	 */
	public String toString()
	{
		String result = item.getNumber() + " " + item.getDescription() + " " + quantity + "@$";
		Price price = item.getPriceForDate(LocalDate.now());
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/YY"); 
		
		result = result + price + " " + formatter.format(price.getEffectiveDate()) + " $" + calcSubTotal();
		
		return result;
	}

}