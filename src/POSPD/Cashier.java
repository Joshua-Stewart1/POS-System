package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Cashier represents a person who operates the cash register
 */
public class Cashier
{

	/**
	 * The cashier's employee number
	 */
	private String number;
	/**
	 * The person who is the cashier
	 */
	private Person person;
	/**
	 * The sales sessions that the cashier makes
	 */
	private ArrayList<Session> sessions;
	/**
	 * The cashier's password to sign in to the register
	 */
	private String password;

	public String getNumber()
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public Person getPerson()
	{
		return this.person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	public ArrayList<Session> getSessions()
	{
		return this.sessions;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * A default constructor
	 */
	public Cashier()
	{
		sessions = new ArrayList<Session>();
		setNumber("");
		setPassword("");
		setPerson(new Person());
	}

	/**
	 * A constructor that initializes the values of cashier
	 * @param number The cashier's employee number
	 * @param person The person who is the cashier
	 * @param password The cashier's password to sign in to the register
	 */
	public Cashier(String number, Person person, String password)
	{
		this();
		setNumber(number);
		setPerson(person);
		setPassword(password);
	}

	/**
	 * addSession - adds a session that the cashier runs
	 * @param session The session to add to the list
	 */
	public void addSession(Session session)
	{
		sessions.add(session);
	}

	/**
	 * removeSession - Remove a session from the cashier's list of session
	 * @param session The session to remove from the cashier's information
	 */
	public void removeSession(Session session)
	{
		sessions.remove(session);
	}

	/**
	 * isAuthorized - Determines if the cashier is allowed to use the system
	 * @param password The password to try to sign in with
	 * @return Returns true if the password entered is correct
	 */
	public Boolean isAuthorized(String password)
	{
		Boolean authorized;
		
		if(this.password.equals(password))
		{
			authorized = true;
		}
		else
		{
			authorized = false;
		}
		
		return authorized;
	}
	
	/**
	 * getTotalSalesForDate - Calculate the total for all sales on a day
	 * @param date - The date to get sales for
	 * @return The total of all of the sales
	 */
	public BigDecimal getTotalSalesForDate(LocalDate date)
	{
		BigDecimal total = new BigDecimal("0.00");
		
		for (Session s: sessions)
		{	
			if(s.getStartDateTime().toLocalDate().equals(date))
			{
				for(Sale sa: s.getSales())
				{
					total = total.add(sa.calcTotal());
				}
			}
		}
		
		return total;
	}

	/**
	 * getNumItemsSoldForDate - Calculate the the number of items sold on a day
	 * @param date - The date to get number of sales for
	 * @return The total number of items sold for the date
	 */
	public int getNumItemsSoldForDate(LocalDate date)
	{
		int numItemsSold = 0;
		
		for (Session s: sessions)
		{	
			if(s.getStartDateTime().toLocalDate().equals(date))
			{
				for(Sale sa: s.getSales())
				{
					numItemsSold += sa.calcItemsSold();
				}
			}
		}
		
		return numItemsSold;
	}
	
	/**
	 * getTotalAmtDiff - Calculate the amount difference for all sales on a day
	 * @param date - The date to get amount difference for
	 * @return The total amount difference for the date
	 */
	public BigDecimal getTotalAmtDiff(LocalDate date)
	{
		BigDecimal diff = new BigDecimal("0.00");
		
		for (Session s: sessions)
		{	
			if(s.getStartDateTime().toLocalDate().equals(date))
			{
				diff = diff.add(s.calcCashCountDiff());
			}
		}
		
		return diff;
	}
	
	/**
	 * toString - Creates a string that represents the cashier's information
	 * @return A string that represents the cashier's information
	 */
	public String toString()
	{
		return person.getName();
	}

}