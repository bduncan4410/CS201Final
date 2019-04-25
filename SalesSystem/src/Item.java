
public class Item extends EntityBase
{
	private String name;
 	private String description;
 	private int inventory;
 	private double price;
 	
	/**
	 * 
	 */
	public Item()
	{
		
	}
	
	/**
	 * @param name
	 * @param description
	 * @param inventory
	 * @param price
	 */
	public Item(String name, String description, int inventory, double price)
	{
		setDescription(description);
		setInventory(inventory);
		setName(name);
		setPrice(price);
	}

	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public int getInventory()
	{
		return inventory;
	}
	public double getPrice()
	{
		return price;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public void setInventory(int inventory)
	{ 
		if (inventory > 0)
		{
			this.inventory = inventory;
		}
	}
	public void setPrice(double price)
	{
		if(price > 0)
		{
			this.price = price;
		}
	}
}
