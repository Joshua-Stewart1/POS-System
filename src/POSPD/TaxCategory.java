package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * TaxCategory represents a number of tax rates for an item
 */
public class TaxCategory
{

	/**
	 * The tax category that the item is in
	 */
	private String category;
	/**
	 * The list of all tax rates for the category
	 */
	private TreeSet<TaxRate> taxRates;

	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates()
	{
		return this.taxRates;
	}

	/**
	 * A default constructor
	 */
	public TaxCategory()
	{
		setCategory("");
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * A constructor that initializes the values of the tax category
	 * @param category The tax category that the item is in
	 */
	public TaxCategory(String category)
	{
		this();
		setCategory(category);
	}

	/**
	 * getTaxRateForDate - Get the tax rate for a given date
	 * @param date The date to get the tax rate for
	 * @return The tax rate for the given date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		TaxRate currentRate = new TaxRate();
		
		for (TaxRate rate : taxRates)
		{
			if(rate.isEffective(date))
			{
				currentRate = rate;
			}
		}
		return currentRate.getTaxRate();
	}

	/**
	 * addTaxRate - add a tax rate to the category
	 * @param taxRate The tax rate to add
	 */
	public void addTaxRate(TaxRate taxRate)
	{
		taxRates.add(taxRate);
	}

	/**
	 * removeTaxRate - removes a tax rate from the category
	 * @param taxRate The taxRate to remove
	 */
	public void removeTaxRate(TaxRate taxRate)
	{
		taxRates.remove(taxRate);
	}

	/**
	 * toString - Creates a string with the information of the taxCategory
	 * @return A string with the information of the taxCategory
	 */
	public String toString()
	{
		return category;
	}

}