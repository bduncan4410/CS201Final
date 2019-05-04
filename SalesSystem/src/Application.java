import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;

public class Application
{
	public static void main(String[] args)
	{
		AddressRepo addressRepo = new AddressRepo();
		//PersonRepo personRepo = new PersonRepo();
		//SalesRepRepo SrRepo = new SalesRepRepo();
		CustomerRepo custRepo = new CustomerRepo();
		OrderRepo orderRepo = new OrderRepo();
		OrderItemRepo orderItemRepo = new OrderItemRepo();
		ItemRepo itemRepo = new ItemRepo();
		
		// Multiple schemas: https://stackoverflow.com/questions/39393773/how-to-configure-multiple-schemas-with-hibernate
		// Look at bottom answer with multiple cfg.xml files for simplest solution.  Update HibernateUtilities to do this
		// if you want more than one schema in an application.
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		session.getTransaction();
		session.close();
		
		//System.out.println("Good to go!");
		
		String tableNames[] = {"Address", "Customer", "SalesRep", "Person", "Order", "OrderItem", "Item"};
		int tableChoice = JOptionPane.showOptionDialog(null, "Please select an option.\n", "", 0, JOptionPane.QUESTION_MESSAGE, null, tableNames, tableNames[0]);
		
		String operations[] = {"Get First", "Get All", "Add", "Update", "Delete", "Get Collection"};
		int operationsChoice = JOptionPane.showOptionDialog(null, "Please select an option.\n", "", 0, JOptionPane.QUESTION_MESSAGE, null, operations, operations[0]);
		
		switch((tableNames[tableChoice + 1]).toString())
		 {
		 case "Address":
			switch((operations[operationsChoice] + 1).toString())
			{
			
			case "Get First":
				System.out.println(addressRepo.getFirst().toString());
				break;
			case "Get All":
				ArrayList<Address> tempList = new ArrayList<Address>();
				tempList = addressRepo.getAll();
				for (int i = 0; i < tempList.size(); i++)
				{
					System.out.println(tempList.get(i).toString());
				}
				break;
			case "Add":
				System.out.println(addressRepo.getFirst().toString());
				break;
			case "Update":
				break;
			case "Delete":
				break;
			case "Get Collection":
				break;
			}
		break;
		 case "Customer":
			switch((operations[operationsChoice] + 1).toString())
				{
				case "Get First":
					
					break;
				case "Get All":
					
					break;
				case "Add":
					break;
				case "Update":
					break;
				case "Delete":
					
				case "Get Collection":
					break;
				}
		 case "SalesRep":
		 case "Person":
		 case "Order":
		 case "OrderItem":
		 case "Item":
			 
			 
		 }
		
		HibernateUtilities.getSessionFactory().close();
		
	}
}