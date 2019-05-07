
public class Person extends EntityBase
{
	private int addressID;
	private String firstName = "";
	private String lastName = "";
	private String phoneNumber  = "";

	/**
	 * Default constructor
	 */
	public Person()
	{
		
	}
	
	/**Overloaded Constructor
	 * @param addressID Foreign key to the address table's ID
	 * @param firstName First Name of person
	 * @param lastName Last name of person
	 * @param phoneNumber The persons phone number
	 */
	public Person(int addressID, String firstName, String lastName, String phoneNumber)
	{
		
		setAddressID(addressID);
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
	}
	
	public int getAddressID()
	{
		return addressID;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setAddressID(int addressID)
	{
		this.addressID = addressID;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setPhoneNumber(String string)
	{
		this.phoneNumber = string;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Person))
		{
			return false;
		}
		
		Person other = (Person) obj;
		if (!(addressID == (other.addressID)))
		{
			return false;
		}
		if (!firstName.equals(other.firstName))
		{
			return false;
		}
		if (!lastName.equals(other.lastName))
		{
			return false;
		}
		if (phoneNumber != other.phoneNumber)
		{
			return false;
		}
		return true;
	}


	
}
