import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDriver 
{

private Connection connection = null;

	public void connect()
	{
		try
	    {
	        Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch (ClassNotFoundException e) 
		{
	        System.out.println("Driver not found");
	        return;
	    }
	    System.out.println("Driver registered");
	    
	    try {
	        connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;");
	        System.out.println("SQL connection established");
	
	    } 
	    catch (SQLException e) 
	    {
	        System.out.println("Connection failed");
	        return;
	    } 
	}
	
	public void disconnect()
    {
        try
        {
            if(connection != null)
                connection.close();
            System.out.println("Connection closed");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void insertAddress()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into Address (Address, City, State, Zip) values ('";
		System.out.println("what is the address?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("what is the city?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("what is the state?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("what is the zip code?");
		statement = statement + textScan.nextLine() + "')";
		
		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void insertPerson()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into Person (AddressID, FirstName, LastName, Phone) values (";
		System.out.println("what is the address ID?");
		statement = statement + textScan.nextLine() + ", '";
		System.out.println("what is the first name?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("what is the last name?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("what is the phone number?");
		statement = statement + textScan.nextLine() + "')";
		
		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void insertSalesRep()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into SalesRep (PersonID, Region) values (";
		System.out.println("what is the person ID?");
		statement = statement + textScan.nextLine() + ", '";
		System.out.println("what is the region?");
		statement = statement + textScan.nextLine() + "')";
		
		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	public void insertCustomer()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into Customer (PersonID, SalesRepID) values (";
		System.out.println("what is the person ID?");
		statement = statement + textScan.nextLine() + ", ";
		System.out.println("what is the rep ID?");
		statement = statement + textScan.nextLine() + ")";

		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void insertItem()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into Item (Name, Description, Inventory, Price) values ('";
		System.out.println("what is the name?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("what is the description?");
		statement = statement + textScan.nextLine() + "', '";
		System.out.println("how many is in inventory?");
		statement = statement + textScan.nextLine() + ", ";
		System.out.println("what is the price");
		statement = statement + textScan.nextLine() + ")";
		
		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void insertOrder()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into [Order] (CustomerID, DateOrdered) values (";
		System.out.println("what is the customer ID?");
		statement = statement + textScan.nextLine() + ", '";
		System.out.println("what is the order date?");
		statement = statement + textScan.nextLine() + "')";
		
		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	public void insertOrderItem()
	{
		Statement insertStmt = null;
		String statement;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
		statement = "insert into OrderItem (OrderID, ItemID, Quantity) values (";
		System.out.println("what is the order ID?");
		statement = statement + textScan.nextLine() + ", ";
		System.out.println("what is the item ID?");
		statement = statement + textScan.nextLine() + ", ";
		System.out.println("how many were ordered?");
		statement = statement + textScan.nextLine() + ")";

		insertStmt = connection.createStatement();
        insertStmt.execute(statement);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void update()
	{
		Statement updateStmt = null;
		String table;
		String column;
		String value;
		int ID;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
			System.out.println("what table to select from?");
			table = textScan.nextLine();
			System.out.println("what entry ID to edit?");
			ID = textScan.nextInt();
			System.out.println("what column to edit?");
			column = textScan.nextLine();
			System.out.println("what value to replace with?");
			value = textScan.nextLine();
			
			updateStmt = connection.createStatement();
	        updateStmt.execute("update " + table + " set " + column + " = '" + value + "' where ID = " + ID);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void select()
	{
		Statement selectStmt = null;
		String table;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
			System.out.println("what table to select from?");
			table = textScan.nextLine();
			ResultSet rs = selectStmt.executeQuery("select * from " + table);
            while(rs.next())
            {
                System.out.println(rs.getString(1));    //First Column
                System.out.println(rs.getString(2));    //Second Column
                System.out.println(rs.getString(3));    //Third Column
                System.out.println(rs.getString(4));    //Fourth Column
                System.out.println(rs.getString(5));    //Fifth Column
            }
		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
	
	public void delete()
	{
		Statement deleteStmt = null;
		String table;
		int ID;
		Scanner textScan = new Scanner(System.in);
		
		try
		{
			System.out.println("what table to select from?");
			table = textScan.nextLine();
			System.out.println("what entry ID to delete?");
			ID = textScan.nextInt();
			
			deleteStmt = connection.createStatement();
	        deleteStmt.execute("delete from " + table + " where ID = " + ID);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		textScan.close();
	}
}
