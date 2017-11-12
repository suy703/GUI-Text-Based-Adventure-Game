
public class Store {
	Items[] storeInventory;
	
	Store(Items[] storeInventory){
		this.storeInventory = storeInventory;
	}

	public Items[] getStoreInventory() {
		return storeInventory;
	}

	public void setStoreInventory(Items[] storeInventory) {
		this.storeInventory = storeInventory;
	}
	
	public String displayStore() {
		String display = "";
		for(int i = 0; i < storeInventory.length; i++) {
			display += storeInventory[i].name + " - " + storeInventory[i].price + " gold\n";
			display += storeInventory[i].description;
			display += "---------------------------\n";
		}
		return display;
	}
	
	public String displayPurchaseCommands() {
		String display = "";
		for(int i = 0; i < storeInventory.length; i++) {
			display += (i+1)+ ". Buy " + storeInventory[i].name + " (" + storeInventory[i].price + " gold)\n";
		}
		display += (storeInventory.length+2) + ". Sell Items";
		return display;
	}
	

}
