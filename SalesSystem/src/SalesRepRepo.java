import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalesRepRepo extends JDBCRepoBase<SalesRep> implements Repository<SalesRep>
{
	
	public SalesRepRepo()
	{
		this.setSchema("Person");
		this.setTable("SalesRep");
	}
	
	
	public SalesRep get(int ID)
	{
		Statement statement = null;
		ResultSet rs = null;
		SalesRep salesRep = new SalesRep();
	
		try
		{
			String sql = "select * from " + getSchema() + "." + getTable() + " where ID = " + ID + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs != null)
			{
				rs.next();				
				salesRep.setID(ID);
				salesRep.setPersonID(rs.getInt("PersonID"));
				salesRep.setRegion(rs.getString("Region"));
				
				return salesRep;
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	
	public SalesRep getFirst()
	{
		Statement statement = null;
		ResultSet rs = null;
		SalesRep salesRep = new SalesRep();
		
		try
		{
			// top 1 is T-SQL specific
			String sql = "select top 1 * from " + getSchema() + "." + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			rs.next();
			salesRep.setID(rs.getInt("ID"));
			salesRep.setPersonID(rs.getInt("PersonID"));
			salesRep.setRegion(rs.getString("Region"));
			return salesRep;
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	
	public ArrayList<SalesRep> getAll()
	{
		ArrayList<SalesRep> list = new ArrayList<SalesRep>();
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
				list.add((SalesRep) new SalesRep(rs.getInt("PersonID"),  rs.getString("Region")).setID(rs.getInt("ID")));				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}	

	
	@Override
	public int add(SalesRep rep)
	{
		try
		{
			int primaryKey = 0;
			String sql = "INSERT INTO " + getSchema() + "." + getTable() + " (PersonID, Region) VALUES(" + rep.getPersonID() + ", " + rep.getRegion() + ");";
			
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
	public boolean update(SalesRep addr)
	{
		try
		{
			Statement statement = conn.createStatement();
			String sql = "UPDATE " + getSchema() + "." + getTable() + " SET PersonID = " + addr.getPersonID() + ", Region = '" + addr.getRegion() + "' where ID = " + addr.getID()  + ";";
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
	public boolean delete(SalesRep addr)
	{
		try
		{
			// Using a concurrent update ResultSet
			Statement statement = conn.createStatement();
			String sql = "DELETE FROM Person.SalesRep where ID=" + addr.getID() + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;		
	}
	
	
	public ArrayList<SalesRep> getCollection(String sql)
	{	
		SalesRep salesRep = new SalesRep();
		ArrayList<SalesRep> list = new ArrayList<SalesRep>();
		Statement statement = null;
		ResultSet rs = null;			
		
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				salesRep.setID(rs.getInt("ID"));
				salesRep.setPersonID(rs.getInt("PersonID"));
				salesRep.setRegion(rs.getString("Region"));
				
				
				list.add(salesRep);				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}
	
	
	public ArrayList<SalesRep> search(String whereClause)
	{
		ArrayList<SalesRep> reps;
		
		String sql = "SELECT * FROM " + getSchema() + "." + getTable() + " " + whereClause + ";";
		// Calling getCollection with the above SQL
		reps = new ArrayList<SalesRep>( getCollection(sql) );
		
		return reps;
	}
}