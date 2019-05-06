import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

public class CustomerRepo extends JDBCRepoBase<Customer> implements Repository<Customer>
{
	/**
	 * Default constructor.  It sets the Schema to Person. and the Table to Customer for the repository
	 */
	public CustomerRepo()
	{
		this.setSchema("Person");
		this.setTable("Customer");
	}
	
	/** Retrieves an Customer from the datastore with the given ID,
	 *  or return null if not found
	 * @return the Customer record with the given ID, or null if not found
	 */
	public Customer get(int ID)
	{
		Statement statement = null;
		ResultSet rs = null;
		Customer Customer = new Customer();
	
		try
		{
			String sql = "select * from " + schema + table + " where ID = " + ID + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs != null)
			{
				rs.next();				
				Customer.setID(ID);
				Customer.setPersonID(rs.getInt("PersonID"));
				Customer.setSalesRepID(rs.getInt("SalesRepID"));
				
				return Customer;
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	/**
	 * getFirst will return the first Customer record out of the datastore, 
	 *  or null if there are no records 
	 * @return the first Customer record in the database
	 */
	public Customer getFirst()
	{
		Statement statement = null;
		ResultSet rs = null;
		Customer Customer = new Customer();
		
		try
		{
			// top 1 is T-SQL specific
			String sql = "select top 1 * from " + getSchema() + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			rs.next();
			Customer.setID(rs.getInt("ID"));
			Customer.setPersonID(rs.getInt("PersonID"));
			Customer.setSalesRepID(rs.getInt("SalesRepID"));
			return Customer;
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	/**
	 * Retrieves all records from the datastore in one ArrayList
	 * getAll is not a safe call on very large record sets and should be removed from the 
	 *   interface/implementation if dealing with very large sets of data
	 * @return All Customer records out of the datastore in an ArrayList, or an empty ArrayList if there are no records
	 */
	public ArrayList<Customer> getAll()
	{
		ArrayList<Customer> list = new ArrayList<Customer>();
		Statement statement = null;
		ResultSet rs = null;
		
		try
		{
			String sql = "select * from " + getSchema() + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				// Adding a new Customer object to the list from the results set data
				list.add((Customer) new Customer(rs.getInt("PersonID"),  rs.getInt("SalesRepID")).setID(rs.getInt("ID")));				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}	

	/**
	 * Add an Customer record to the datastore.
	 * This uses the getters of the cust parameter to build an 
	 *   insert SQL string and execute it
	 * @return The primary key assigned by the database or -1 if the insert was unsuccessful 
	 */
	@Override
	public int add(Customer cust)
	{
		try
		{
			int primaryKey = 0;
			String sql = "INSERT INTO " + getSchema() + getTable();
			sql += " (, PersonID, State, Zip) VALUES('";
			sql += cust.getPersonID() + "', '" + cust.getSalesRepID() + ");";
			
			Statement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = null;
			statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
			rs = statement.getGeneratedKeys();
			rs.next();
			primaryKey = rs.getInt(1);
			return primaryKey; // return the new PK
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	 * Update an existing Customer record in the datastore.
	 *   This uses the getters of the addr parameter to build an 
	 *   update SQL string and execute it
	 * @return true if successful, false if not
	 */
	@Override
	public boolean update(Customer addr)
	{
		try
		{
			Statement statement = conn.createStatement();
			String sql = "UPDATE " + getSchema() + getTable() + " SET ";
			sql += "PersonID = '" + addr.getPersonID();
			sql += "', SalesRepID = '" + addr.getSalesRepID();
			sql += "' where ID = " + addr.getID()  + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete an Customer record from the datastore.
	 * Foreign Key constraints with the Person table may cause this to fail
	 * if the person record isn't deleted first
	 * @return true if successful, false if not
	 */
	@Override
	public boolean delete(Customer addr)
	{
		try
		{
			// Using a concurrent update ResultSet
			Statement statement = conn.createStatement();
			String sql = "DELETE FROM Person.Customer where ID=" + addr.getID() + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;		
	}
	
	/**
	 * getCollection will retrieve a group of Customeres that match a given SQL select statement
	 *   Any valid where clause can be passed. 
	 *   <br/>
	 *   Examples:
	 *   String matching: Select * from Person.Customer where Customer like '%1600%'
	 *   Zip code: where Select * from Person.Customer where Zip > 33333
	 * @return ArrayList with records matching the sql select query
	 */
	public ArrayList<Customer> getCollection(String sql)
	{	
		Customer Customer = new Customer();
		ArrayList<Customer> list = new ArrayList<Customer>();
		Statement statement = null;
		ResultSet rs = null;			
		
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				Customer.setID(rs.getInt("ID"));
				Customer.setPersonID(rs.getInt("PersonID"));
				Customer.setSalesRepID(rs.getInt("SalesRepID"));
				
				
				list.add(Customer);				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}
	
	/**
	 * search will do an SQL lookup matching an Customer record with the where clause provided.
	 * Calls getCollection to retrieve results, and returns an ArrayList of the Customer instances
	 *   that match.
	 * <br/>
	 *   Examples:
	 *   String matching: where Customer like '%1600%'
	 *   Zip code: where Zip > 33333
	 * @return ArrayList with records matching the sql where clause parameter
	 */
	public ArrayList<Customer> search(String whereClause)
	{
		ArrayList<Customer> Customeres;
		
		String sql = "SELECT * FROM " + getSchema() + getTable() + " " + whereClause + ";";
		// Calling getCollection with the above SQL
		Customeres = new ArrayList<Customer>( getCollection(sql) );
		
		return Customeres;
	}
}