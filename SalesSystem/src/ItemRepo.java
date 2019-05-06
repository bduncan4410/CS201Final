import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.hibernate.query.Query;

public class ItemRepo extends HibernateRepoBase<Item> implements Repository<Item>
{
	/**
	 * Default constructor.  It sets the Schema to Person. and the Table to Item for the repository
	 */
	public ItemRepo()
	{
		this.setSchema("[Order]");
		this.setTable("[Item]");
	}
	
	/**
	 * get will retrieve an Item from the datastore with the given ID,
	 *  or return null if not found
	 * @return the Item record with the given ID, or null if not found
	 */
	public Item get(int ID)
	{
		Item getItem = new Item();
		
		try
		{
			getItem = (Item) session.get(Item.class, ID);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return getItem;
	}
	
	/**
	 * getFirst will return the first Item record out of the datastore, 
	 *  or null if there are no records 
	 * @return the first Item record in the database
	 */
	public Item getFirst()
	{
		Item Item = new Item();
		
		try
		{
			Query query = session.createQuery("from [Item] ORDER by ID");
			java.util.List list = query.getResultList();
			
			if (list.size() > 0)
			{
				Item = (Item) list.remove(0);				
			}
			else
			{
				Item = null;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return Item;
	}
	
	/**
	 * getCollection will retrieve a group of Items that match a given HQL statement
	 *   Any valid where clause can be passed. 
	 *   <br/>
	 *   Examples (see search documentation also):
	 *   String matching: from Item where Item like '%1600%'
	 *   Zip code: where from Item where Zip > 33333
	 * @return ArrayList with records matching the hql query
	 */
	public ArrayList<Item> getCollection(String hql)
	{	
		ArrayList<Item> list = new ArrayList<Item>();
		try
		{
			Query query = session.createQuery(hql);
			list = new ArrayList<Item>(query.list());
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}

	/**
	 * Add an Item record to the datastore.
	 * This uses the session saveOrUpdate method to create a new record or update an existing record 
	 * @return The primary key assigned by the database or -1 if the insert was unsuccessful 
	 */
	@Override
	public int add(Item addr)
	{
		int ItemID = -1;
		
		try
		{
			session.saveOrUpdate(addr);
			transaction.commit();
			
			if (addr.getID() > 0)
			{
				ItemID = addr.getID();
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return ItemID;
	}
	
	/**
	 * Update an existing Item record in the datastore.
	 *   This uses the session saveOrUpdate method to create a new record or update an existing record 
	 *   update SQL string and execute it
	 * @return true if successful, false if not
	 */
	@Override
	public boolean update(Item addr)
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
	 * Delete an Item record from the datastore.
	 * Foreign Key constraints with the Person table may cause this to fail
	 * if the person record isn't deleted first
	 * @return true if successful, false if not
	 */
	@Override
	public boolean delete(Item addr)
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
	 * search will do an HQL lookup matching an ItemItem record with the where clause provided.
	 * Calls getCollection to retrieve results, and returns an ArrayList of the Item instances
	 *   that match.
	 * <br/>
	 *   Examples:
	 *   String matching: where Item like '%1600%'
	 *   Zip code: where Zip > 33333
	 * @return ArrayList with records matching the sql where clause parameter
	 */
	public ArrayList<Item> search(String term)
	{
		ArrayList<Item> Items = new ArrayList<Item>();
		// no select in HQL.  from <Object> where <class field> is syntax
		String hql = "from [Item] " + term;		
		// Calling getCollection with the above SQL
		Items = new ArrayList<Item>( getCollection(hql) );
		
		return Items;
	}
}