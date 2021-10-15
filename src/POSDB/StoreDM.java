package POSDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import POSPD.Cash;
import POSPD.Cashier;
import POSPD.Check;
import POSPD.Credit;
import POSPD.Item;
import POSPD.Payment;
import POSPD.Person;
import POSPD.Price;
import POSPD.PromoPrice;
import POSPD.Register;
import POSPD.Sale;
import POSPD.SaleLineItem;
import POSPD.Session;
import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;
import POSPD.UPC;

public class StoreDM 
{
	public static void loadStore(Store store)
	{
		String fileName = "StoreData_v2019.csv";
		String line = null;
		String dataType = null;
		String[] token;
		Session currentSession = null;
		Sale currentSale = null;
		
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null)
			{
				token = line.split(",");
				dataType = token[0];
				
				if(dataType.equals("Store"))
				{
					store.setName(token[1]);
				}
				else if(dataType.equals("TaxCategory"))
				{
					TaxRate taxRate = new TaxRate(LocalDate.parse(token[3], DateTimeFormatter.ofPattern("M/d/yy")), new BigDecimal(token[2]));
					TaxCategory taxCategory = new TaxCategory(token[1]);
					taxCategory.addTaxRate(taxRate);
					store.addTaxCategory(taxCategory);
				}
				else if(dataType.equals("Cashier"))
				{
					Person person = new Person(token[2], token[4], token[5], token[6], token[7], token[8], token[3]);
					Cashier cashier = new Cashier(token[1], person, token[9]);
					store.addCashier(cashier);
				}
				else if(dataType.equals("Item"))
				{
					Item item = new Item(token[1], token[3]);
					item.setTaxCategory(store.findTaxCategoryByName(token[4]));
					
					UPC upc = new UPC(token[2], item);
					store.addUPC(upc);
					
					Price price = new Price(token[5], token[6], item);
					
					if(token.length > 7)
					{
						price = new PromoPrice(token[7], token[8], token[9], item);
					}
					
					store.addItem(item);
				}
				else if(dataType.equals("Register"))
				{
					Register register = new Register(token[1]);
					store.addRegister(register);
				}
				else if(dataType.equals("Session"))
				{
					Cashier cashier = store.findCashierForNumber(token[1]);
					Register register = store.findRegisterByNumber(token[2]);
					Session session = new Session(cashier, register);
					store.addSession(session);
					currentSession = session;
					session.setEndCash(register.getCashDrawer().getCashAmount());
				}
				else if(dataType.equals("Sale"))
				{
					Sale sale = new Sale(token[1]);
					currentSale = sale;
					currentSession.addSale(sale);
				}
				else if(dataType.equals("SaleLineItem"))
				{
					Item item = store.findItemForNumber(token[1]);
					SaleLineItem sli = new SaleLineItem(currentSale, item, token[2]);
				}
				else if(dataType.equals("Payment"))
				{
					String paymentType = token[1];
					Payment payment = null;
					
					if(paymentType.equals("Cash"))
					{
						payment = new Cash(token[2],new BigDecimal(token[3]));
						currentSession.setEndCash(currentSession.getEndCash().add(payment.getAmount()));
					}
					else if(paymentType.equals("Credit"))
					{
						payment = new Credit(token[4], token[5], token[6]);
						payment.setAmount(new BigDecimal(token[2]));
						payment.setAmtTendered(new BigDecimal(token[3]));
					}
					else if(paymentType.equals("Check"))
					{
						payment = new Check(token[2], token[4], token[5], token[6]);
						payment.setAmtTendered(new BigDecimal(token[3]));
					}
					
					currentSale.addPayment(payment);
				}
				
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) 
		{
			System.out.println( "Unable to open file '" +  fileName + "'");                
		}
		catch(IOException ex) 
		{
			System.out.println (  "Error reading file '" + fileName + "'");   	
		}

	}
}
