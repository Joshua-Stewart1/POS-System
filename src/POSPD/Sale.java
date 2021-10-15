package POSPD;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Sale represents a transaction between the store providing items and the customer giving a payment in return
 */
public class Sale
{

	/**
	 * The set of payments made on the sale
	 */
	private ArrayList<Payment> payments;
	/**
	 * The set of individual lines of items sold on the sale
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	/**
	 * The date and time that the sale was made
	 */
	private LocalDateTime dateTime;
	/**
	 * Whether or not the sale is tax exempt
	 */
	private Boolean taxFree;

	public ArrayList<Payment> getPayments()
	{
		return this.payments;
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return this.saleLineItems;
	}

	public LocalDateTime getDateTime()
	{
		return this.dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree()
	{
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree)
	{
		this.taxFree = taxFree;
	}

	/**
	 * A default constructor
	 */
	public Sale()
	{
		payments = new ArrayList<Payment>();
		saleLineItems = new ArrayList<SaleLineItem>();
		dateTime = LocalDateTime.now();
		taxFree = false;
	}

	/**
	 * A constructor that initializes the values of a sale
	 * @param taxed - Whether or not the sale is tax exempt
	 */
	public Sale(String taxed)
	{
		this();
		if(taxed.equals("Y"))
		{
			setTaxFree(true);
		}
		else
		{
			setTaxFree(false);
		}
		
	}

	/**
	 * addPayment - add a payment to the sale
	 * @param payment The payment to add to the sale
	 */
	public void addPayment(Payment payment)
	{
		payments.add(payment);
	}

	/**
	 * removePayment - Remove a payment from the sale
	 * @param payment The payment to remove from the sale
	 */
	public void removePayment(Payment payment)
	{
		payments.remove(payment);
	}

	/**
	 * addSaleLineItem - Add a sale line item to the item
	 * @param sli The sale line item to add to the sale
	 */
	public void addSaleLineItem(SaleLineItem sli)
	{
		saleLineItems.add(sli);
	}

	/**
	 * removeSaleLineItem - Remove a sale line item from the sale
	 * @param sli The sale line item to remove
	 */
	public void removeSaleLineItem(SaleLineItem sli)
	{
		saleLineItems.remove(sli);
	}

	/**
	 * calcTotal - Calculate the total cost of the sale
	 * @return The total price of the sale
	 */
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal("0.00");
		
		if(taxFree)
		{
			total = calcSubTotal();
		}
		else
		{
			total = calcSubTotal().add(calcTax());
		}
		
		return total;
	}

	/**
	 * calcSubTotal - Calculate the total for the sale before tax
	 * @return The total for the sale before tax
	 */
	public BigDecimal calcSubTotal()
	{
		BigDecimal subTotal = new BigDecimal("0.00");
		
		for(SaleLineItem s : saleLineItems)
		{
			subTotal = subTotal.add(s.calcSubTotal());
		}
		
		return subTotal;
	}

	/**
	 * calcTax - Calculate the amount of tax to charge on the sale
	 * @return The amount of tax to charge on the sale
	 */
	public BigDecimal calcTax()
	{
		BigDecimal taxTotal = new BigDecimal("0.00");
		
		if(!taxFree)
		{	
			for(SaleLineItem s : saleLineItems)
			{
				taxTotal = taxTotal.add(s.calcTax());
			}
		}
		return taxTotal;
	}

	/**
	 * getTotalPayments - Get the total of all of the payments
	 * @return The total of all of the payments
	 */
	public BigDecimal getTotalPayments()
	{
		BigDecimal paymentTotal = new BigDecimal("0.00");
		
		for(Payment p : payments)
		{
			paymentTotal = paymentTotal.add(p.getAmount());
		}
		
		return paymentTotal;
	}

	/**
	 * isPaymentEnough - Determine if the payments given cover the price of the sale
	 * @return Returns true if the payments cover the price of the sale
	 */
	public Boolean isPaymentEnough()
	{
		Boolean enough;
		
		if(getTotalPayments().compareTo(calcTotal()) >= 0)
		{
			enough = true;
		}
		else
		{
			enough = false;
		}
		return enough;
	}

	/**
	 * calcAmountForPayment - Calculate the amount of money used for the payment
	 * @param amtTendered The amount currently paid for the payment
	 */
	public BigDecimal calcAmountForPayment(BigDecimal amtTendered)
	{
		BigDecimal amount = new BigDecimal("0.00");
		
		if((getTotalPayments().add(amtTendered)).compareTo(calcTotal()) <= 0)
		{
			amount = amtTendered;
		}
		else
		{
			amount = calcTotal().subtract(getTotalPayments());
		}
		
		return amount;
	}

	/**
	 * calcChange - Calculate the change that is owed back
	 * @return The amount of change to give back
	 */
	public BigDecimal calcChange()
	{
		return calcAmtTendered().subtract(calcTotal());
	}

	/**
	 * calcAmtTendered - Calculate the total amount paid for the item
	 * @return The total amount paid for the item
	 */
	public BigDecimal calcAmtTendered()
	{
		BigDecimal tenderedTotal = new BigDecimal("0.00");
		
		for(Payment p : payments)
		{
			tenderedTotal = tenderedTotal.add(p.getAmtTendered());
		}
		
		return tenderedTotal;
	}
	
	/**
	 * calcItemsSold - Calculate the total items sold
	 * @return The total items sold
	 */
	public int calcItemsSold()
	{
		int numSold = 0;
		
		for(SaleLineItem s : saleLineItems)
		{
			numSold += s.getQuantity();
		}
		
		return numSold;
	}

	/**
	 * calcTotalCash - Get the total of all of the cash payments
	 * @return The total of all of the cash payments
	 */
	public BigDecimal calcTotalCash()
	{
		BigDecimal cashTotal = new BigDecimal("0.00");
		
		for(Payment p : payments)
		{
			if(p instanceof Cash)
			{
				cashTotal = cashTotal.add(p.getAmount());
			}
		}
		
		return cashTotal;
	}
	
	/**
	 * calcTotalCheck - Get the total of all of the check payments
	 * @return The total of all of the check payments
	 */
	public BigDecimal calcTotalCheck()
	{
		BigDecimal checkTotal = new BigDecimal("0.00");
		
		for(Payment p : payments)
		{
			if(p instanceof Check)
			{
				checkTotal = checkTotal.add(p.getAmount());
			}
		}
		
		return checkTotal;
	}
	
	/**
	 * calcTotalCredit - Get the total of all of the credit card payments
	 * @return The total of all of the credit card payments
	 */
	public BigDecimal calcTotalCredit()
	{
		BigDecimal creditTotal = new BigDecimal("0.00");
		
		for(Payment p : payments)
		{
			if(p instanceof Credit)
			{
				creditTotal = creditTotal.add(p.getAmount());
			}
		}
		
		return creditTotal;
	}
	
	public String toString()
	{
		String result = "Sale: SubTotal = " + calcSubTotal() + " Tax = " + calcTax() + " Total = " + calcTotal();
		result = result + " Payment = " + calcAmtTendered() + " Change = " + calcChange();
		
		for(SaleLineItem sli : saleLineItems)
		{
			result = result + "\n   " + sli.toString();
		}
		
		return result;
	}

}