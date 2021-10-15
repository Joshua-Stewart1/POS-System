package POSPD;

/**
 * AuthorizedPayment represents any form of payment that is not cash
 */
public abstract class AuthorizedPayment extends Payment
{

	/**
	 * A number representing the given payment
	 */
	private String authorizationCode;

	public String getAuthorizationCode()
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
	}

	/**
	 * isAuthorized - Checks if the payment is able to be made
	 * @return Returns true if the payment is able to be made
	 */
	public abstract Boolean isAuthorized();

	/**
	 * countsAsCash - checks if the payment is a cash payment
	 * @return Returns false as the payment is not made with cash
	 */
	public Boolean countsAsCash()
	{
		return false;
	}

}