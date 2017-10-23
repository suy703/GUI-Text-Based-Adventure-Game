
public class Items {
	public String itemID;
	public String name;
	public String description;
	public int price;
	
	public Items(String id, String name, String description, int price) {
		itemID = id;
		this.name = name;
		this.description = description;
		this.price = price;
		
	}
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
