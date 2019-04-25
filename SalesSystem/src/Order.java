import java.sql.Date;

public class Order extends EntityBase
{
	private int customerID;
	private Date date;
	
	
	
	/**
	 * 
	 */
	public Order()
	{
		
	}
	
	/**
	 * @param customerID
	 * @param date
	 */
	public Order(int customerID, Date date)
	{
		setCustomerID(customerID);
		setDate(date);
	}

	public int getCustomerID()
	{
		return customerID;
	}
	public Date getDate()
	{
		return date;
	}
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
}
