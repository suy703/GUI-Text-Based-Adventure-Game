/**
 * This class is used to create in game stores the the player can buy and sell items at.
 * @author Alex
 *
 */
public class Store {
	/**
	 * An array of Items to be sold in the store
	 */
	Items[] storeInventory;
	
	/**
	 * Contructor. Creates the store and initializes its item list (Array)
	 * @param storeInventory
	 */
	Store(Items[] storeInventory){
		this.storeInventory = storeInventory;
	}

	/**
	 * Retrieves the store inventory
	 * @return The store inventory
	 */
	public Items[] getStoreInventory() {
		return storeInventory;
	}

	/**
	 *  Changes the store's invetpry
	 * @param storeInventory
	 */
	public void setStoreInventory(Items[] storeInventory) {
		this.storeInventory = storeInventory;
	}
	
	/**
	 * Displays the names and descriptions of all items in the store.
	 * @return The names and descriptions of all items in the store.
	 */
	public String displayStore() {
		String display = "";
		for(int i = 0; i < storeInventory.length; i++) {
			display += storeInventory[i].name + " - " + storeInventory[i].price + " gold\n";
			display += storeInventory[i].description;
			display += "---------------------------\n";
		}
		return display;
	}
	
	/**
	 * Displays textual commands to be entered by the user in order to purchase items in the store.
	 * @return
	 */
	public String displayPurchaseCommands() {
		String display = "";
		for(int i = 0; i < storeInventory.length; i++) {
			display += (i+1)+ ". Buy " + storeInventory[i].name + " (" + storeInventory[i].price + " gold)\n";
		}
		display += (storeInventory.length+1) + ". Sell Items";
		return display;
	}
	

}


