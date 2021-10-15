package POSPD;

/**
 * Person represents any human associated with a job in problem domain
 */
public class Person
{

	/**
	 * The person's full name
	 */
	private String name;
	/**
	 * The address of the person's place of residence
	 */
	private String address;
	/**
	 * The city the person lives in
	 */
	private String city;
	/**
	 * The state the person lives in
	 */
	private String state;
	/**
	 * The ZIP code of the person's address
	 */
	private String zip;
	/**
	 * The person's phone number
	 */
	private String phone;
	/**
	 * The person's Social Security Number
	 */
	private String sSN;
	/**
	 * The cashier that the person is associated with
	 */
	private Cashier cashier;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return this.city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return this.zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getSSN()
	{
		return this.sSN;
	}

	public void setSSN(String sSN)
	{
		this.sSN = sSN;
	}

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	/**
	 * A default constructor
	 */
	public Person()
	{
		setName("");
		setAddress("");
		setCity("");
		setState("");
		setZip("");
		setPhone("");
		setSSN("");
	}

	/**
	 * A constructor that initializes all of the values of a person as given
	 * @param name The person's full name
	 * @param address The address of the person's place of residence
	 * @param city The city the person lives in
	 * @param state The state the person lives in
	 * @param zip The ZIP code of the person's address
	 * @param phone The person's phone number
	 * @param sSN The person's Social Security Number
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String sSN)
	{
		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setPhone(phone);
		setSSN(sSN);
	}

	/**
	 * toString - Creates a string representing the attributes of the person
	 * @return A string representing the attributes of the person
	 */
	public String toString()
	{
		return name;
	}

}