
public class Order extends EntityBase
{
	private int customerID;
	private String date;
	
	
	
	/**
	 * 
	 */
	public Order()
	{
		
	}
	
	/**
	 * @param customerID
	 * @param date2
	 */
	public Order(int customerID, String date2)
	{
		setCustomerID(customerID);
		setDate(date2);
	}

	public int getCustomerID()
	{
		return customerID;
	}
	public String getDate()
	{
		return date;
	}
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	public void setDate(String date2)
	{
		this.date = date2;
	}
}
