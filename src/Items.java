/**
 * This class creates Items to be used in game by the player.
 * @author Alex
 *
 */
public class Items {
	/**
	 * Unique ID for the Item
	 */
	public String itemID;
	/**
	 * Item Name
	 */
	public String name;
	/**
	 * Item Description
	 */
	public String description;
	/**
	 * Item Price
	 */
	public int price;
	/**
	 * Determines weather or not the player can equip the item
	 */
	boolean canEquip = false;
	/**
	 * determines weather or not the user can use the item
	 */
	boolean canUse = false;
	/** 
	 * Contructor. Creates item object and initializes its values. 
	 * @param id Item id
	 * @param name Item name
	 * @param description Item Description
	 * @param price Item Price
	 */
	public Items(String id, String name, String description, int price) {
		itemID = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	 /**
	  * Retrieves the item id
	  * @return Item Id
	  */
	public String getItemID() {
		return itemID;
	}
	/**
	 * Changes the item's id
	 * @param itemID  The new item ID.
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	/**
	 * Retrieves the item's name
	 * @return The items's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Changes the item's name
	 * @param name The item's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retrieves the item's description
	 * @return The item's description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Changes the item's description
	 * @param description The item's new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Retieves the item's price.
	 * @return The items price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * Changes the items price/
	 * @param price The item's new price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Displays the actions of items in the players inventory
	 * @param p Player object
	 * @return Textual Display of item options
	 */
	public String displayItemOptions(Player p){
		String invDisplay = name +"------------------\n0. Back";
		if(canEquip) {
			if(equals(p.equippedWeap))
				invDisplay += "\nUnequip";
			else
				invDisplay += "\nEquip";
			invDisplay += "\nDrop";
		}else if(canUse) {
			invDisplay += "\nUse\nDrop";
		}else {
			invDisplay += "\nDrop";
		}
		return invDisplay;
	}


}
