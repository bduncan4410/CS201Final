import java.awt.HeadlessException;
import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import org.hibernate.Session;


public class Application
{
	public static void main(String[] args)
	{
		AddressRepo addressRepo = new AddressRepo();
		PersonRepo personRepo = new PersonRepo();
		CustomerRepo custRepo= new CustomerRepo();
		SalesRepRepo  SalesRepRepo = new SalesRepRepo();
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
		
		String tableNames[] = {"Order", "OrderItem", "Item"};
		int tableChoice = JOptionPane.showOptionDialog(null, "Please select an option.\n", "", 0, JOptionPane.QUESTION_MESSAGE, null, tableNames, tableNames[0]);
		
		String operations[] = {"Get First", "Get All", "Add", "Update", "Delete"};
		int operationsChoice = JOptionPane.showOptionDialog(null, "Please select an option.\n", "", 0, JOptionPane.QUESTION_MESSAGE, null, operations, operations[0]);
		
		switch((tableNames[tableChoice + 1]).toString())
		 {
		 case "Address":
			switch((operations[operationsChoice] + 1).toString())
			{
			
			case "Get First":
				JOptionPane.showMessageDialog(null, addressRepo.getFirst().toString());
				break;
			case "Get All":
				ArrayList<Address> tempList = new ArrayList<Address>();
				tempList = addressRepo.getAll();
				TextArea text = new TextArea();
				for (int i = 0; i < tempList.size(); i++)
				{
					text.append(tempList.get(i).toString() + "\n");
				}
				JOptionPane.showMessageDialog(null, text);
				break;
			case "Add":
				do
				{
					String streetAddress = JOptionPane.showInputDialog("Please enter street address");
					String city = JOptionPane.showInputDialog("Please enter the City");
					String state = JOptionPane.showInputDialog("Please enter the State");
					int zip = Integer.parseInt(JOptionPane.showInputDialog("Please enter the zip code"));
					
					Address address = new Address(streetAddress, city, state, zip);
					
					addressRepo.add(address);

					
				}
				while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
				break;
			case "Update":
				do
				{
					String streetAddress = JOptionPane.showInputDialog("Please enter street address");
					String city = JOptionPane.showInputDialog("Please enter the City");
					String state = JOptionPane.showInputDialog("Please enter the State");
					int zip = Integer.parseInt(JOptionPane.showInputDialog("Please enter the zip code"));
					int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of address to update"));
					
					Address address = new Address(streetAddress, city, state, zip);
					address.setID(ID);
					addressRepo.update(address);

					
				}
				while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
				break;
			case "Delete":
				Address address = new Address();
				do
				{
					int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the Address to delete."));
					address.setID(ID);
				}
				while(!(addressRepo.delete(address)));
				break;
			}
		break;
		 case "Customer":
			 switch((operations[operationsChoice] + 1).toString())
				{
				
				case "Get First":
					JOptionPane.showMessageDialog(null, custRepo.getFirst().toString());
					break;
				case "Get All":
					ArrayList<Customer> tempList = new ArrayList<Customer>();
					tempList = custRepo.getAll();
					TextArea text = new TextArea();
					for (int i = 0; i < tempList.size(); i++)
					{
						text.append(tempList.get(i).toString() + "\n");
					}
					JOptionPane.showMessageDialog(null, text);
					break;
				case "Add":
					do
					{
						int PersonID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Person ID"));
						int salesRepID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Sales Rep ID"));
						
						
						Customer customer = new Customer(PersonID, salesRepID);
						
						custRepo.add(customer);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Update":
					do
					{
						int PersonID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Person ID"));
						int salesRepID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Sales Rep ID"));
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of customer to update"));
						
						Customer customer = new Customer(PersonID, salesRepID);
						customer.setID(ID);
						custRepo.update(customer);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Delete":
					Customer customer = new Customer();
					do
					{
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the customer to delete."));
						customer.setID(ID);
					}
					while(!(custRepo.delete(customer)));
					break;
				}
			break;
		 case "SalesRep":
			 switch((operations[operationsChoice] + 1).toString())
				{
				
				case "Get First":
					JOptionPane.showMessageDialog(null, addressRepo.getFirst().toString());
					break;
				case "Get All":
					ArrayList<SalesRep> tempList = new ArrayList<SalesRep>();
					tempList = SalesRepRepo.getAll();
					TextArea text = new TextArea();
					for (int i = 0; i < tempList.size(); i++)
					{
						text.append(tempList.get(i).toString() + "\n");
					}
					JOptionPane.showMessageDialog(null, text);
					break;
				case "Add":
					do
					{
						int personID = Integer.parseInt(JOptionPane.showInputDialog("Please enter person ID"));
						String region = JOptionPane.showInputDialog("Please enter the region");
						
						
						SalesRep salesRep = new SalesRep(personID, region);
						
						SalesRepRepo.add(salesRep);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Update":
					do
					{
						int personID = Integer.parseInt(JOptionPane.showInputDialog("Please enter person ID"));
						String region = JOptionPane.showInputDialog("Please enter the region");
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of SalesRep to update"));
						
						SalesRep SalesRep = new SalesRep(personID, region);
						SalesRep.setID(ID);
						SalesRepRepo.update(SalesRep);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Delete":
					SalesRep salesRep = new SalesRep();
					do
					{
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the SalesRep to delete."));
						salesRep.setID(ID);
					}
					while(!(SalesRepRepo.delete(salesRep)));
					break;
				}
			break;
		 case "Person":
			 switch((operations[operationsChoice] + 1).toString())
				{
				
				case "Get First":
					JOptionPane.showMessageDialog(null, personRepo.getFirst().toString());
					break;
				case "Get All":
					ArrayList<Person> tempList = new ArrayList<Person>();
					tempList = personRepo.getAll();
					TextArea text = new TextArea();
					for (int i = 0; i < tempList.size(); i++)
					{
						text.append(tempList.get(i).toString() + "\n");
					}
					JOptionPane.showMessageDialog(null, text);
					break;
				case "Add":
					do
					{
						int addressID = Integer.parseInt(JOptionPane.showInputDialog("Please enter address ID"));
						String firstName = JOptionPane.showInputDialog("Please enter first name");
						String lastName = JOptionPane.showInputDialog("Please enter the lastName");
						String phoneNumber = JOptionPane.showInputDialog("Please enter phone number");
						
						
						Person person = new Person(addressID, firstName, lastName, phoneNumber);
						
						personRepo.add(person);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Update":
					do
					{
						int addressID = Integer.parseInt(JOptionPane.showInputDialog("Please enter address ID"));
						String firstName = JOptionPane.showInputDialog("Please enter first name");
						String lastName = JOptionPane.showInputDialog("Please enter the lastName");
						String phoneNumber = JOptionPane.showInputDialog("Please enter phone numbesr");
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of person to update"));
						
						Person person = new Person(addressID, firstName, lastName, phoneNumber);
						person.setID(ID);
						personRepo.update(person);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Delete":
					Person person = new Person();
					do
					{
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the person to delete."));
						person.setID(ID);
					}
					while(!(personRepo.delete(person)));
					break;
				}
			break;
		 case "Order":
			 switch((operations[operationsChoice] + 1).toString())
				{
				
				case "Get First":
					JOptionPane.showMessageDialog(null, orderRepo.getFirst().toString());
					break;
				case "Get All":
					ArrayList<Order> tempList = new ArrayList<Order>();
					tempList = (ArrayList<Order>) orderRepo.getAll();
					TextArea text = new TextArea();
					for (int i = 0; i < tempList.size(); i++)
					{
						text.append(tempList.get(i).toString() + "\n");
					}
					JOptionPane.showMessageDialog(null, text);
					break;
				case "Add":
					do
					{
						int custID = Integer.parseInt(JOptionPane.showInputDialog("Please enter customer iD"));
						String date = JOptionPane.showInputDialog("Please enter the date of order");
						
						Order order = new Order(custID, date);
						
						orderRepo.add(order);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Update":
					do
					{
						int custID = Integer.parseInt(JOptionPane.showInputDialog("Please enter customer iD"));
						String date = JOptionPane.showInputDialog("Please enter the date of order");
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of order to update"));
						
						Order order = new Order(custID, date);
						order.setID(ID);
						orderRepo.update(order);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Delete":
					Order order = new Order();
					do
					{
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the order to delete."));
						order.setID(ID);
					}
					while(!(orderRepo.delete(order)));
					break;
				}
			break;
		 case "OrderItem":
			 switch((operations[operationsChoice] + 1).toString())
				{
				
				case "Get First":
					JOptionPane.showMessageDialog(null, orderItemRepo.getFirst().toString());
					break;
				case "Get All":
					ArrayList<OrderItem> tempList = new ArrayList<OrderItem>();
					tempList = (ArrayList<OrderItem>) orderItemRepo.getAll();
					TextArea text = new TextArea();
					for (int i = 0; i < tempList.size(); i++)
					{
						text.append(tempList.get(i).toString() + "\n");
					}
					JOptionPane.showMessageDialog(null, text);
					break;
				case "Add":
					do
					{
						int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID"));
						int  itemID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ItemID"));
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount of items bought"));
						
						OrderItem orderItem = new OrderItem(orderID, itemID, quantity);
						
						orderItemRepo.add(orderItem);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Update":
					do
					{ 
						int orderID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the order ID"));
						int  itemID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ItemID"));
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount of items bought"));
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of orderItem to update"));
						
						OrderItem orderItem = new OrderItem(orderID, itemID, quantity);
						orderItem.setID(ID);
						orderItemRepo.update(orderItem);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Delete":
					OrderItem orderItem = new OrderItem();
					do
					{
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the orderItem to delete."));
						orderItem.setID(ID);
					}
					while(!(orderItemRepo.delete(orderItem)));
					break;
				}
			break;
		 case "Item":
			 switch((operations[operationsChoice] + 1).toString())
				{
				
				case "Get First":
					JOptionPane.showMessageDialog(null, itemRepo.getFirst().toString());
					break;
				case "Get All":
					ArrayList<Item> tempList = new ArrayList<Item>();
					tempList = (ArrayList<Item>) itemRepo.getAll();
					TextArea text = new TextArea();
					for (int i = 0; i < tempList.size(); i++)
					{
						text.append(tempList.get(i).toString() + "\n");
					}
					JOptionPane.showMessageDialog(null, text);
					break;
				case "Add":
					do
					{
						String name = JOptionPane.showInputDialog("Please enter name of item");
						String desc = JOptionPane.showInputDialog("Please enter the description of item");
						int inventory = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount in inventory"));
						double price = Double.parseDouble(JOptionPane.showInputDialog("Please enter the price of item"));
						
						Item item = new Item(name, desc, inventory, price);
						
						itemRepo.add(item);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to add more?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				}
				case "Update":
					do
					{
						String name = JOptionPane.showInputDialog("Please enter name of item");
						String desc = JOptionPane.showInputDialog("Please enter the description of item");
						int inventory = Integer.parseInt(JOptionPane.showInputDialog("Please enter the amount in inventory"));
						double price = Double.parseDouble(JOptionPane.showInputDialog("Please enter the price of item"));
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of item to update"));
						
						Item item = new Item(name, desc, inventory, price);
						item.setID(ID);
						itemRepo.update(item);

						
					}
					while(JOptionPane.showConfirmDialog(null, "Would you like to update any more rows?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
					break;
				case "Delete":
					Item item = new Item();
					do
					{
						int ID = Integer.parseInt(JOptionPane.showInputDialog("Please enter the ID of the item to delete."));
						item.setID(ID);
					}
					while(!(itemRepo.delete(item)));
					break;
			
		 }
			 
		 

		HibernateUtilities.getSessionFactory().close();
		
	
	}
}
