import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.TextArea;

public class Rooms {
	
	Boolean locks = true;
	String ID = "";
	String name = "";
	String exits = "";
	String items = "";
	
	Image map, icon;
	ImageView viewIcon, viewMap;
	TextArea displayStory, displayCommand;
	
	public Rooms(Boolean locks, String ID, String name, String exits, String items) {
		
		this.locks = locks;
		this.ID = ID;
		this.name = name;
		this.exits = exits;
		this.items = items;
	}
	
	public Rooms(Image map, Image icon, ImageView viewIcon, ImageView viewMap, TextArea displayStory, TextArea displayCommand) {
		 this.map = map;
		 this.icon = icon;
		 this.viewIcon = viewIcon;
		 this.viewMap = viewMap;
		 this.displayStory = displayStory;
		 this.displayCommand = displayCommand;
	 }
	
	public Boolean getRoomLocks() {
		return this.locks;
	}
	public void setRoomLocks(Boolean locks) {
		this.locks = locks;
	}
	public String getRoomID() {
		return this.ID;
	}
	public void setRoomID(String ID) {
		this.ID = ID;
	}
	public String getRoomName() {
		return this.name;
	}
	public void setRoomName(String name) {
		this.name = name;
	}
	public String getRoomExits() {
		return this.exits;
	}
	public void setRoomExits(String exits) {
		this.exits = exits;
	}
	public String getRoomItems() {
		return this.items;
	}
	public void setRoomItems(String items) {
		this.items = items;
	}
	
	//Display Room
	public void display(String story, String command) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setLoadGameStory(displayStory, story);
		commandMenu.setLoadGameCommand(displayCommand, command);
	}
	
	//Town Hub
	public void TownHub_1A() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap);
		commandMenu.setNavigateIcon(icon, viewIcon, 420, 275);
		commandMenu.setLoadGameStory(displayStory, "Town Hub\n\n" + "A dusty road leads into town and a tumbleweed "
				+ "rolls across your path.  In the town square there is a statue of a menacing looking figure with a sheriff’s "
				+ "badge on. The buildings look a bit run down and people seem to shuffle from place to place without interacting. "
				+ "To the East there is a bustling saloon. To your North-West you see a drug store and to the South-West, an Inn.");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Drug Store (North West)\n" + "2. Inn (South West)\n" + "3. Saloon (East)\n" 
				+ "4. Inventory\n" + "5. Save Game");
		
		System.out.println("Inside Town Hub"); // Testing purpose

	}
	//Drug Store
	public void DrugStore_1B() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap);
		commandMenu.setNavigateIcon(icon, viewIcon, 340, 250);
		commandMenu.setLoadGameStory(displayStory, "Drug Store\n\n" + "A mostly bare room with a worn down looking counter "
				+ "top. Glass bottles full of medicine line the walls, but they look as if they haven’t been touched in years. You "
				+ "could almost measure the amount of dust accumulation on them. A man with a large mustache and a tattered suit "
				+ "stands behind the counter looking hopeful. To the South is a small passageway leading into a small, dimly lit, room "
				+ "and to the East is the door leading to the Town Hub.");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Town Hub (East)\n" + "2. Inn (South)\n" + "3. Search Room\n" 
				+ "4. Inventory\n" + "5. Save Game");
		
		System.out.println("Inside Drug Store"); // Testing purpose
	}
	//Inn
	public void Inn_1C() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap);
		commandMenu.setNavigateIcon(icon, viewIcon, 345, 334);
		commandMenu.setLoadGameStory(displayStory, "Inn\n\n" + "You walk into the inn and you see "
				+ "a counter with a bell on it. There doesn’t appear to be anyone around, but you see a few "
				+ "rooms with the doors closed. There are a few dusty painting hanging on the wall, they look "
				+ "like they have seen better days. There is a hallway to the North that leads into a larger, "
				+ "well lit room and to the East is the door to the Town Hub. The inn keeper comes up before "
				+ "you get a chance to ring the bell and asks you if you want a room." );
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Drug Store (North)\n" + "2. Town Hub (East)\n" + "3. Search Room\n" 
				+ "4. Inventory\n" + "5. Save Game");
		
		System.out.println("Inside Inn"); // Testing purpose

	}
	//Saloon
	public void Saloon_1D() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap);
		commandMenu.setNavigateIcon(icon, viewIcon, 515, 250); 
		commandMenu.setLoadGameStory(displayStory, "Saloon\n\n" + "This is the most people you have seen in one place. The bar is filled "
				+ "with forlorn looking people drowning their sorrows in alcohol. There is a poker table in the back, men in expensive "
				+ "looking suits sit at it smoking cigars and laughing. As you walk in, you notice that all eyes are on you. The barkeep "
				+ "gives you an inquisitive look, and then goes back to wiping down the bar with a rag that is dirtier than the bar. You "
				+ "notice a large door with a padlock on it to the South and to the West is the door to the Town Hub.");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Town Hub (West)\n" + "2. Jail (South)\n"+ "3. Inventory\n" 
				+ "4. Save Game");
		
		System.out.println("Inside Saloon Store"); // Testing purpose

	}
	//Jail
	public void Jail_1E() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap);
		commandMenu.setNavigateIcon(icon, viewIcon, 495, 340); 
		commandMenu.setLoadGameStory(displayStory, "Jail\n\n" + "A small room with a couple of cells in the corner. The Deputy Sheriffs "
				+ "desk is in the other corner. As the Deputy Sheriff shoves you into the cells, you notice a small window. Between the "
				+ "bars of the window you can see the sun shining down on your horse, waiting for you outside..");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Saloon (North)\n" + "2. Inventory\n" + "3. Save Game");
		
		System.out.println("Inside Jail"); // Testing purpose
	}
}
