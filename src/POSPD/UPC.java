package POSPD;

/**
 * UPC represents the universal product code for an item
 */
public class UPC
{

	/**
	 * The number value for the UPC
	 */
	private String uPC;
	/**
	 * The item the UPC is tied to
	 */
	private Item item;

	public String getUPC()
	{
		return this.uPC;
	}

	public void setUPC(String uPC)
	{
		this.uPC = uPC;
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
	public UPC()
	{
		setUPC("");
		setItem(new Item());
	}

	/**
	 * A constructor that initializes the value of the UPC
	 * @param upc
	 */
	public UPC(String upc, Item item)
	{
		this();
		setUPC(upc);
		setItem(item);
		item.addUPC(this);
	}

	/**
	 * toString - Creates a string of the UPC
	 * @return A string representing the UPC
	 */
	public String toString()
	{
		return uPC;
	}

}