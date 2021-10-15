package POSPD;

/**
 * Register represents a cash register in the store
 */
public class Register
{

	/**
	 * Which register it is
	 */
	private String number;
	/**
	 * The cash drawer inside of the register
	 */
	private CashDrawer cashDrawer;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public CashDrawer getCashDrawer()
	{
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer)
	{
		this.cashDrawer = cashDrawer;
	}

	/**
	 * A default constructor
	 */
	public Register()
	{
		setNumber("");
		setCashDrawer(new CashDrawer());
;	}

	/**
	 * A constructor that initializes the values of the register
	 * @param number Which register it is
	 */
	public Register(String number)
	{
		this();
		setNumber(number);
	}

	/**
	 * toString - Creates a string representing the cash register
	 */
	public String toString()
	{
		return number;
	}

}