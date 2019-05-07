import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepo extends JDBCRepoBase<Person> implements Repository<Person>
{
	public PersonRepo()
	{
		this.setSchema("Person");
		this.setTable("Person");
	}
	
	
	public Person get(int ID)
	{
		Statement statement = null;
		ResultSet rs = null;
		Person person = new Person();
	
		try
		{
			String sql = "select * from " + getSchema() + "." + getTable() + " where ID = " + ID + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs != null)
			{
				rs.next();				
				Person.setID(ID);
				Person.setAddressID(rs.getInt("AddressID"));
				Person.setFirstName(rs.getString("FirstName"));
				Person.setLastName(rs.getString("LastName"));
				Person.setPhoneNumber(rs.getInt("PhoneNumber"));
				
				return Person;
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	
	public Person getFirst()
	{
		Statement statement = null;
		ResultSet rs = null;
		Person Person = new Person();
		
		try
		{
			// top 1 is T-SQL specific
			String sql = "select top 1 * from " + getSchema() + "." + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			rs.next();
			Person.setID(rs.getInt("ID"));
			Person.setAddressID(rs.getInt("AddressID"));
			Person.setFirstName(rs.getString("FirstName"));
			Person.setLastName(rs.getString("LastName"));
			Person.setPhoneNumber(rs.getInt("PhoneNumber"));
			
			return Person;
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	
	public ArrayList<Person> getAll()
	{
		ArrayList<Person> list = new ArrayList<Person>();
		Statement statement = null;
		ResultSet rs = null;
		
		try
		{
			String sql = "select * from " + getSchema() + "." + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				// Adding a new salesRep object to the list from the results set data
				list.add((Person) new Person(rs.getInt("AddressID"),  rs.getString("FirstName"), rs.getString("LastNane"), rs.getInt("PhoneNumber")).setID(rs.getInt("ID")));				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}	

	
	@Override
	public int add(Person person)
	{
		try
		{
			int primaryKey = 0;
			String sql = "INSERT INTO " + getSchema() + "." + getTable() + " (AddressID, FirstName, LastName, PhoneNumber) VALUES(" + person.getAddressID() + ", '" + person.getFirstName() + "', '" + person.getLastName() + "', " + person.getPhoneNumber() + ");";
			
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
	
	
	@Override
	public boolean update(Person addr)
	{
		try
		{
			Statement statement = conn.createStatement();
			String sql = "UPDATE " + getSchema() + "." + getTable() + " SET AddressID = " + addr.getAddressID() + ", FirstName = '" + addr.getFirstName() + "', LastName = '" + addr.getLastName() + ", PhoneNumber = " + addr.getPhoneNumber() + " where ID = " + addr.getID()  + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean delete(Person addr)
	{
		try
		{
			// Using a concurrent update ResultSet
			Statement statement = conn.createStatement();
			String sql = "DELETE FROM Person.Person where ID=" + addr.getID() + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;		
	}
	
	
	public ArrayList<Person> getCollection(String sql)
	{	
		Person person = new Person();
		ArrayList<Person> list = new ArrayList<Person>();
		Statement statement = null;
		ResultSet rs = null;			
		
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				person.setID(rs.getInt("ID"));
				person.setAddressID(rs.getInt("AddressID"));
				person.setFirstName(rs.getString("FirstName"));
				person.setLastName(rs.getString("LastName"));
				person.setPhoneNumber(rs.getString("PhoneNumber"));
				
				list.add(person);				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}
	
	
	public ArrayList<Person> search(String whereClause)
	{
		ArrayList<Person> list;
		
		String sql = "SELECT * FROM " + getSchema() + "." + getTable() + " " + whereClause + ";";
		// Calling getCollection with the above SQL
		list = new ArrayList<Person>( getCollection(sql) );
		
		return list;
}
