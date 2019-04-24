/**
 * A repository object for managing CRUD operations for Address objects
 */

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 * The AddressRepo extends RepoBase and is typed to an Address object.  
 * It provides an entire API to create, read, update, and delete addresses from the datastore 
 * @author calmond
 *
 */

//https://www.mkyong.com/hibernate/hibernate-query-examples-hql/
public class AddressRepo extends RepoBase<Address> implements Repository<Address>
{
	/**
	 * Default constructor.  It sets the Schema to Person. and the Table to Address for the repository
	 */
	public AddressRepo()
	{
		this.setSchema("Person");
		this.setTable("Address");
	}
	
	/**
	 * get will retrieve an address from the datastore with the given ID,
	 *  or return null if not found
	 * @return the Address record with the given ID, or null if not found
	 */
	public Address get(int ID)
	{
		Address getAddress = new Address();
		
		try
		{
			getAddress = (Address) session.get(Address.class, ID);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return getAddress;
	}
	
	/**
	 * getFirst will return the first Address record out of the datastore, 
	 *  or null if there are no records 
	 * @return the first address record in the database
	 */
	public Address getFirst()
	{
		Address address = new Address();
		
		try
		{
			Query query = session.createQuery("from Address order by ID");
			List list = query.list();
			
			if (list.size() > 0)
			{
				address = (Address) list.remove(0);				
			}
			else
			{
				address = null;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return address;
	}
	
	/**
	 * getCollection will retrieve a group of Addresses that match a given HQL statement
	 *   Any valid where clause can be passed. 
	 *   <br/>
	 *   Examples (see search documentation also):
	 *   String matching: from Address where address like '%1600%'
	 *   Zip code: where from Address where Zip > 33333
	 * @return ArrayList with records matching the hql query
	 */
	public ArrayList<Address> getCollection(String hql)
	{	
		ArrayList<Address> list = new ArrayList<Address>();
		try
		{
			Query query = session.createQuery(hql);
			list = new ArrayList<Address>(query.list());
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}

	/**
	 * Add an address record to the datastore.
	 * This uses the session saveOrUpdate method to create a new record or update an existing record 
	 * @return The primary key assigned by the database or -1 if the insert was unsuccessful 
	 */
	@Override
	public int add(Address addr)
	{
		int addressID = -1;
		
		try
		{
			session.saveOrUpdate(addr);
			transaction.commit();
			
			if (addr.getID() > 0)
			{
				addressID = addr.getID();
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return addressID;
	}
	
	/**
	 * Update an existing address record in the datastore.
	 *   This uses the session saveOrUpdate method to create a new record or update an existing record 
	 *   update SQL string and execute it
	 * @return true if successful, false if not
	 */
	@Override
	public boolean update(Address addr)
	{
		try
		{
			session.saveOrUpdate(addr);
			transaction.commit();
			return true;
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete an address record from the datastore.
	 * Foreign Key constraints with the Person table may cause this to fail
	 * if the person record isn't deleted first
	 * @return true if successful, false if not
	 */
	@Override
	public boolean delete(Address addr)
	{
		// None of the others needed this, but delete did
		transaction.begin();
		try
		{
			session.delete(addr);
			transaction.commit();
			return true;
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return false;		
	}
	
	/**
	 * search will do an HQL lookup matching an address record with the where clause provided.
	 * Calls getCollection to retrieve results, and returns an ArrayList of the Address instances
	 *   that match.
	 * <br/>
	 *   Examples:
	 *   String matching: where address like '%1600%'
	 *   Zip code: where Zip > 33333
	 * @return ArrayList with records matching the sql where clause parameter
	 */
	public ArrayList<Address> search(String term)
	{
		ArrayList<Address> addresses = new ArrayList<Address>();
		// no select in HQL.  from <Object> where <class field> is syntax
		String hql = "from Address " + term;		
		// Calling getCollection with the above SQL
		addresses = new ArrayList<Address>( getCollection(hql) );
		
		return addresses;
	}
}