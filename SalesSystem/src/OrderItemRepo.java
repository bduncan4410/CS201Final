import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.hibernate.query.Query;

public class OrderItemRepo extends HibernateRepoBase<OrderItem> implements Repository<OrderItem>
{
	/**
	 * Default constructor.  It sets the Schema to Person. and the Table to OrderItem for the repository
	 */
	public OrderItemRepo()
	{
		this.setSchema("[Order]");
		this.setTable("[OrderItem]");
	}
	
	/**
	 * get will retrieve an OrderItem from the datastore with the given ID,
	 *  or return null if not found
	 * @return the OrderItem record with the given ID, or null if not found
	 */
	public OrderItem get(int ID)
	{
		OrderItem getOrderItem = new OrderItem();
		
		try
		{
			getOrderItem = (OrderItem) session.get(OrderItem.class, ID);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return getOrderItem;
	}
	
	/**
	 * getFirst will return the first OrderItem record out of the datastore, 
	 *  or null if there are no records 
	 * @return the first OrderItem record in the database
	 */
	public OrderItem getFirst()
	{
		OrderItem OrderItem = new OrderItem();
		
		try
		{
			Query query = session.createQuery("from [OrderItem] ORDER by ID");
			java.util.List list = query.getResultList();
			
			if (list.size() > 0)
			{
				OrderItem = (OrderItem) list.remove(0);				
			}
			else
			{
				OrderItem = null;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return OrderItem;
	}
	
	/**
	 * getCollection will retrieve a group of OrderItems that match a given HQL statement
	 *   Any valid where clause can be passed. 
	 *   <br/>
	 *   Examples (see search documentation also):
	 *   String matching: from OrderItem where OrderItem like '%1600%'
	 *   Zip code: where from OrderItem where Zip > 33333
	 * @return ArrayList with records matching the hql query
	 */
	public ArrayList<OrderItem> getCollection(String hql)
	{	
		ArrayList<OrderItem> list = new ArrayList<OrderItem>();
		try
		{
			Query query = session.createQuery(hql);
			list = new ArrayList<OrderItem>(query.list());
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}

	/**
	 * Add an OrderItem record to the datastore.
	 * This uses the session saveOrUpdate method to create a new record or update an existing record 
	 * @return The primary key assigned by the database or -1 if the insert was unsuccessful 
	 */
	@Override
	public int add(OrderItem addr)
	{
		int OrderItemID = -1;
		
		try
		{
			session.saveOrUpdate(addr);
			transaction.commit();
			
			if (addr.getID() > 0)
			{
				OrderItemID = addr.getID();
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return OrderItemID;
	}
	
	/**
	 * Update an existing OrderItem record in the datastore.
	 *   This uses the session saveOrUpdate method to create a new record or update an existing record 
	 *   update SQL string and execute it
	 * @return true if successful, false if not
	 */
	@Override
	public boolean update(OrderItem addr)
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
	 * Delete an OrderItem record from the datastore.
	 * Foreign Key constraints with the Person table may cause this to fail
	 * if the person record isn't deleted first
	 * @return true if successful, false if not
	 */
	@Override
	public boolean delete(OrderItem addr)
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
	 * search will do an HQL lookup matching an OrderItem record with the where clause provided.
	 * Calls getCollection to retrieve results, and returns an ArrayList of the OrderItem instances
	 *   that match.
	 * <br/>
	 *   Examples:
	 *   String matching: where OrderItem like '%1600%'
	 *   Zip code: where Zip > 33333
	 * @return ArrayList with records matching the sql where clause parameter
	 */
	public ArrayList<OrderItem> search(String term)
	{
		ArrayList<OrderItem> OrderItems = new ArrayList<OrderItem>();
		// no select in HQL.  from <Object> where <class field> is syntax
		String hql = "from [OrderItem] " + term;		
		// Calling getCollection with the above SQL
		OrderItems = new ArrayList<OrderItem>( getCollection(hql) );
		
		return OrderItems;
	}
}