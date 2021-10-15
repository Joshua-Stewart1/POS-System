package POSPD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Session represents a given time of a cashier being in use
 */
public class Session
{

	/**
	 * The cashier who operated the session
	 */
	private Cashier cashier;
	/**
	 * The register that the session is operated on
	 */
	private Register register;
	/**
	 * The sales made during the session
	 */
	private ArrayList<Sale> sales;
	/**
	 * The date and time the session was started
	 */
	private LocalDateTime startDateTime;
	/**
	 * The date and time the session was ended
	 */
	private LocalDateTime endDateTime;
	/**
	 * The amount of cash when the session was started
	 */
	private BigDecimal startCash;
	/**
	 * The amount of cash when the session was ended
	 */
	private BigDecimal endCash;

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	public Register getRegister()
	{
		return this.register;
	}

	public void setRegister(Register register)
	{
		this.register = register;
	}

	public ArrayList<Sale> getSales()
	{
		return this.sales;
	}

	public LocalDateTime getStartDateTime()
	{
		return this.startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime()
	{
		return this.endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	public BigDecimal getEndCash()
	{
		return this.endCash;
	}

	public void setEndCash(BigDecimal endCash)
	{
		this.endCash = endCash;
	}
	
	public BigDecimal getStartCash()
	{
		return this.startCash;
	}

	public void setStartCash(BigDecimal startCash)
	{
		this.startCash = startCash;
	}

	/**
	 * A default constructor
	 */
	public Session()
	{
		sales = new ArrayList<Sale>();
		setStartDateTime(LocalDateTime.now());
		setEndDateTime(LocalDateTime.now());
		setEndCash(new BigDecimal("0.00"));
	}

	/**
	 * A constructor that initializes the values of the Session
	 * @param cashier The cashier who operated the session
	 * @param register The register that the session is operated on
	 */
	public Session(Cashier cashier, Register register)
	{
		this();
		setCashier(cashier);
		cashier.addSession(this);
		setRegister(register);
		setStartCash(register.getCashDrawer().getCashAmount());
		setEndCash(register.getCashDrawer().getCashAmount());
	}

	/**
	 * addSale - Add a sale to the session
	 * @param sale The sale to add to the session
	 */
	public void addSale(Sale sale)
	{
		sales.add(sale);
	}

	/**
	 * removeSale - Removes a sale from the session
	 * @param sale The sale to remove from the session
	 */
	public void removeSale(Sale sale)
	{
		sales.remove(sale);
	}

	/**
	 * calcCashCountDiff - calculates the difference in the amount of money in the register 
	 *      and amount of money that should be in the register
	 * @return The difference in the predicted and actual amount of cash in the register
	 */
	public BigDecimal calcCashCountDiff()
	{
		BigDecimal diff = new BigDecimal("0.00");
		BigDecimal amountExpected = getStartCash();
		
		for(Sale s: sales)
		{
			for(Payment p: s.getPayments())
			{
				if(p.countsAsCash())
				{
					amountExpected = amountExpected.add(p.getAmount());
				}
			}
		}
		
		if(amountExpected.compareTo(endCash) >= 0)
		{
			diff = amountExpected.subtract(endCash);
		}
		else
		{
			diff = endCash.subtract(amountExpected);
		}
		
		return diff.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * toString - Creates a string representing the session
	 * @return A string representing the session
	 */
	public String toString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/YY h:mm a"); 
		
		String result = "Session: Cashier:" + cashier.getPerson().getName() + " Register:" + register.getNumber();
		result = result + " Date:" + formatter.format(startDateTime) + " Total:";
		
		BigDecimal total = new BigDecimal("0.00");
		
		for(Sale s : sales)
		{
			total = total.add(s.calcTotal());
		}
		
		result = result + total;
		
		for(Sale s : sales)
		{
			result = result + "\n\n " + s.toString();
		}
		
		return result;
	}

}