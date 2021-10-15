package POSPD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * PromoPrice represents a special promotional price for an item
 */
public class PromoPrice extends Price
{

	/**
	 * The date after which the promotion ends
	 */
	private LocalDate endDate;

	public LocalDate getEndDate()
	{
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * A default constructor
	 */
	public PromoPrice()
	{
		super();
		setEndDate(LocalDate.now());
	}

	/**
	 * A constructor that initializes a promotional price with given values
	 * @param price The amount of money to charge for the item
	 * @param effectiveDate The date after which the price can be used
	 * @param endDate The date after which the promotion ends
	 */
	public PromoPrice(String price, String effectiveDate, String endDate, Item item)
	{
		super(price, effectiveDate, item);
		setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/d/yy")));
	}

	/**
	 * isEffective - Determines if the promotional code is currently in effect
	 * @param date The current date
	 * @return Returns true if the given date is between the promotional price's start and end date, inclusive
	 */
	public Boolean isEffective(LocalDate date)
	{
		Boolean effective;
		
		if((this.getEffectiveDate().compareTo(date) < 0) && (this.getEndDate().compareTo(date) >= 0))
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
	 * compareTo - compares the promotional price with another promotional price
	 * @param price The other price to compare to
	 * @return Returns a negative number if the other price is still in effect and has a later start date, returns zero if both prices have the same start and end date, and returns a positive number if the other price has an earlier start date
	 */
	public int compareTo(Price price)
	{
		return getEffectiveDate().compareTo(price.getEffectiveDate());
	}

	/**
	 * toString - Creates a string that represents the promotional price
	 * @return A string representing the promotional price
	 */
	public String toString()
	{
		return getPrice().toString();
	}

}