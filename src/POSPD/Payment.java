package POSPD;

import java.math.BigDecimal;

/**
 * Payment represents a given amount of money in exchange for the products sold to the customer
 */
public abstract class Payment
{

	/**
	 * The amount of money required to pay for the sale
	 */
	private BigDecimal amount;
	/**
	 * The amount of money given to pay for the sale
	 */
	private BigDecimal amtTendered;

	public BigDecimal getAmount()
	{
		return this.amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getAmtTendered()
	{
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}

	/**
	 * countsAsCash - Checks if the payment is a cash payment
	 * @return Returns true if the payment is made with cash
	 */
	public abstract Boolean countsAsCash();

}