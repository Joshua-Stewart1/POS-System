package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * TaxRate represents a given tax for an item within a given date
 */
public class TaxRate implements Comparable<TaxRate>
{

	/**
	 * The tax for a given item
	 */
	private BigDecimal taxRate;
	/**
	 * The date for which the tax rate goes into effect
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate()
	{
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate)
	{
		this.taxRate = taxRate;
	}

	public LocalDate getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	/**
	 * A default constructor
	 */
	public TaxRate()
	{
		setTaxRate(new BigDecimal("0.00"));
		setEffectiveDate(LocalDate.now());
	}

	/**
	 * A constructor that initializes the values of the tax rate
	 * @param effectiveDate The date for which the tax rate goes into effect
	 * @param rate The tax for a given item
	 */
	public TaxRate(LocalDate effectiveDate, BigDecimal rate)
	{
		this();
		setTaxRate(rate);
		setEffectiveDate(effectiveDate);
	}

	/**
	 * isEffective - Determines if the tax rate is in effect
	 * @param date The current date
	 * @return Returns true if the given date is at the same time or after the effective date
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
	 * compareTo - compares the tax rate to another tax rate
	 * @param taxRate The other tax rate to compare to
	 * @return Returns a negative number if the other tax rate has a later effective date, returns zero if they have effective date, and a positive number if it has an earlier effective date
	 */
	public int compareTo(TaxRate taxRate)
	{
		return this.effectiveDate.compareTo(taxRate.getEffectiveDate());
	}

	/**
	 * toString - Create a string representing the tax rate
	 * @return A string of the tax rate
	 */
	public String toString()
	{
		return taxRate.toString();
	}

}