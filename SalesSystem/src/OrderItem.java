
public class OrderItem extends EntityBase
{
	private int orderID;
	private int itemID;
	private int quantity;
	
	/**
	 * 
	 */
	public OrderItem()
	{
		
	}
	
	/**
	 * @param orderID
	 * @param itemID
	 * @param quantity
	 */
	public OrderItem(int orderID, int itemID, int quantity)
	{
		setItemID(itemID);
		setOrderID(orderID);
		setQuantity(quantity);
	}
	
	public int getOrderID()
	{
		return orderID;
	}
	public int getItemID()
	{
		return itemID;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	public void setQuantity(int quantity)
	{
		if( quantity > 0)
		{
			this.quantity = quantity;
	
		}
	}
	
	
}
