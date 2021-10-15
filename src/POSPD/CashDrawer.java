package POSPD;

import java.math.BigDecimal;

/**
 * CashDrawer represents the drawer that holds change in the register
 */
public class CashDrawer
{

	/**
	 * The amount of cash in the drawer
	 */
	private BigDecimal cashAmount;
	/**
	 * Either the open or closed position of the drawer
	 */
	private int position;

	public BigDecimal getCashAmount()
	{
		return this.cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount)
	{
		this.cashAmount = cashAmount;
	}

	public int getPosition()
	{
		return this.position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 * A default constructor
	 */
	public CashDrawer()
	{
		setCashAmount(new BigDecimal("0.00"));
		setPosition(0);
	}

	/**
	 * adCash - Add the given amount of cash to the drawer
	 * @param cash The amount of cash to add
	 */
	public void addCash(BigDecimal cash)
	{
		cashAmount = cashAmount.add(cash);
	}

	/**
	 * removeCash - Remove the given amount of cash from the drawer
	 * @param cash How much cash to remove
	 */
	public void removeCash(BigDecimal cash)
	{
		cashAmount = cashAmount.subtract(cash);
	}

	/**
	 * toString - Creates a string representing the state of the cash drawer
	 */
	public String toString()
	{
		return cashAmount.toString();
	}

}