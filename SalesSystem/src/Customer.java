
public class Customer extends EntityBase
{
	private int personID;
	private int salesRepID;
	/**
	 * 
	 */
	public Customer()
	{
		
	}
	
	/**
	 * @param personID
	 * @param salesRepID
	 */
	public Customer(int personID, int salesRepID)
	{
		setPersonID(personID);
		setSalesRepID(salesRepID);
	}

	public int getPersonID()
	{
		return personID;
	}
	public int getSalesRepID()
	{
		return salesRepID;
	}
	public void setPersonID(int personID)
	{
		this.personID = personID;
	}
	public void setSalesRepID(int salesRepID)
	{
		this.salesRepID = salesRepID;
	}
	
	
}
