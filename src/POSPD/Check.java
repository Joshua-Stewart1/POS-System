package POSPD;

import java.math.BigDecimal;

/**
 * Check represents a payment made with a check
 */
public class Check extends AuthorizedPayment
{

	/**
	 * The number representing the bank
	 */
	private String routingNumber;
	/**
	 * The number for the bank account
	 */
	private String accountNumber;
	/**
	 * The number for the check being used
	 */
	private String checkNumber;

	public String getRoutingNumber()
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber()
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber()
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}

	/**
	 * A default constructor
	 */
	public Check()
	{
		setAmount(new BigDecimal("0.00"));
		setAmtTendered(new BigDecimal("0.00"));
		setAccountNumber("");
		setCheckNumber("");
		setRoutingNumber("");
	}

	/**
	 * A constructor that initializes the values of the check
	 * @param amount The amount of money paid for the sale
	 * @param accountNumber The number for the bank account
	 * @param checkNumber The number for the check being used
	 * @param routingNumber The number representing the bank
	 */
	public Check(String amount, String accountNumber, String checkNumber, String routingNumber)
	{
		this();
		setAmount(new BigDecimal(amount));
		setAccountNumber(accountNumber);
		setCheckNumber(checkNumber);
		setRoutingNumber(routingNumber);
	}

	/**
	 * isAuthorized - Checks if the check is able to make the payment
	 * @return Returns true if the payment is able to be made
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * toString - Creates a string representing the check's information
	 * @return A string representing the information on the check
	 */
	public String toString()
	{
		return routingNumber + " - " + accountNumber + " - " + checkNumber;
	}

}