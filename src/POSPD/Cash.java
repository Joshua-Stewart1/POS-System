package POSPD;

import java.math.BigDecimal;

/**
 * Cash represents a payment made with physical currency
 */
public class Cash extends Payment
{

	/**
	 * A default constructor
	 */
	public Cash()
	{
		setAmount(new BigDecimal("0.00"));
		setAmtTendered(new BigDecimal("0.00"));
	}

	/**
	 * A constructor that initializes the amounts in the payment
	 * @param amount The amount of money required to pay for the sale
	 * @param amtTendered The amount of money given to pay for the sale
	 */
	public Cash(String amount, BigDecimal amtTendered)
	{
		setAmount(new BigDecimal(amount));
		setAmtTendered(amtTendered);
	}

	/**
	 * countsAsCash - Checks if the payment is a cash payment
	 * @return Returns true because the payment is made with cash
	 */
	public Boolean countsAsCash()
	{
		return true;
	}

	/**
	 * toString - Creates a string representing the amount of cash paid
	 * @return A string representing the amount of cash paid
	 */
	public String toString()
	{
		return getAmount().toString();
	}

}