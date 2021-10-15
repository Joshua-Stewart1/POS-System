package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Price represents the cost of an item
 */
public class Price implements Comparable<Price>
{

	/**
	 * The amount of money to charge for the item
	 */
	private BigDecimal price;
	/**
	 * The date after which the price can be used
	 */
	private LocalDate effectiveDate;
	/**
	 * The item to which the price is associated
	 */
	private Item item;

	public BigDecimal getPrice()
	{
		return this.price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public LocalDate getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * A default constructor
	 */
	public Price()
	{
		setPrice(new BigDecimal("0.00"));
		setEffectiveDate(LocalDate.now());
	}

	/**
	 * A constructor initializing the values of the price
	 * @param price The amount of money to charge for the item
	 * @param effectiveDate The date after which the price can be used
	 */
	public Price(String price, String effectiveDate, Item item)
	{
		this();
		setPrice(new BigDecimal(price));
		setEffectiveDate(LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy")));
		setItem(item);
		item.addPrice(this);
	}

	/**
	 * isEffective - Checks to see if the price is has gone into effect
	 * @param date The current date
	 * @return Returns true if the given date is the same as or after the effective date
	 */
	public Boolean isEffective(LocalDate date)
	{
		Boolean effective;
		
		if(this.effectiveDate.compareTo(date) < 0)
		{
			effective = true;
		}
		else
		{
			effective = false;
		}
		return effective;
	}

	/**
	 * calcAmountForQty - Calculates the total price for a given number of items of this price
	 * @param quantity The number of items of this price
	 * @return The total price of the given number of items
	 */
	public BigDecimal calcAmountForQty(int quantity)
	{
		return price.multiply(new BigDecimal(Integer.toString(quantity)));
	}

	/**
	 * compareTo - Compares the effectiveDate of the price to that of another price
	 * @param price Another price to compare the effectiveDate to
	 * @return Returns a negative number if the effective date is before that of the other price, zero if they have the same effective date, and a positive number if the effective date is after that of the other price
	 */
	public int compareTo(Price price)
	{
		return this.effectiveDate.compareTo(price.effectiveDate);
	}

	/**
	 * toString - Creates a string representing the price
	 * @return A string representing the price
	 */
	public String toString()
	{
		return price.toString();
	}

}