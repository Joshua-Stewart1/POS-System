package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Credit represents a form of payment that is made using a credit or debit card
 */
public class Credit extends AuthorizedPayment
{

	/**
	 * The type of card used to make the payment
	 */
	private String cardType;
	/**
	 * The number representing the account for the card
	 *  
	 */
	private String acctNumber;
	/**
	 * The date that the card expires
	 */
	private LocalDate expireDate;

	public String getCardType()
	{
		return this.cardType;
	}

	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}

	public String getAcctNumber()
	{
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber)
	{
		this.acctNumber = acctNumber;
	}

	public LocalDate getExpireDate()
	{
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate)
	{
		this.expireDate = expireDate;
	}

	/**
	 * The default constructor
	 */
	public Credit()
	{
		setCardType("");
		setAcctNumber("");
		setExpireDate(LocalDate.now());
		setAmount(new BigDecimal("0.00"));
		setAmtTendered(new BigDecimal("0.00"));
	}

	/**
	 * A constructor to initialize the values of the card
	 * @param cardType The type of card used to make the payment
	 * @param acctNumber The number representing the account for the card
	 * @param expireDate The date that the card expires
	 */
	public Credit(String cardType, String acctNumber, String expireDate)
	{
		this();
		setCardType(cardType);
		setAcctNumber(acctNumber);
		setExpireDate(LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("M/d/yyyy")));
	}

	/**
	 * isAuthorized - Checks if the card is able to make a payment
	 * @return Returns true if the card is able to make a payment
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * toString - Creates a string representing the card's information
	 * @return A string representing the information on the card
	 */
	public String toString()
	{
		return cardType + " - " + acctNumber + " - " + expireDate;
	}

}