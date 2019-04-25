
public class SalesRep extends EntityBase
{
	private int personID;
	private String region;
	
	
	
	/**
	 * 
	 */
	public SalesRep()
	{
		
	}
	/**
	 * @param personID
	 * @param region
	 */
	public SalesRep(int personID, String region)
	{
		
		setPersonID(personID);
		setRegion(region);
	}
	public int getPersonID()
	{
		return personID;
	}
	public String getRegion()
	{
		return region;
	}
	public void setPersonID(int personID)
	{
		this.personID = personID;
	}
	public void setRegion(String region)
	{
		this.region = region;
	}
	
	
}
