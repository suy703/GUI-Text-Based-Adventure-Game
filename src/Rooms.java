import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import javafx.scene.control.TextArea;

public class Rooms {
	
	GameDatabase read = new GameDatabase("database/Rooms.txt");
	String[] readFile = read.readFile();
	
	String levels = "";
	Boolean locks = true;
	String ID = "";
	String name = "";
	String exits = "";
	ArrayList<Items> items = new ArrayList<Items>();
	String roomDescription;
	
	Image map, icon;
	ImageView viewIcon, viewMap;
	TextArea displayStory, displayCommand;
	
	/*
	 * CONSTRUCTOR
	 */
	public Rooms(String level, Boolean locks, String ID, String name, String exits) {
		
		this.levels = level;
		this.locks = locks;
		this.ID = ID;
		this.name = name;
		this.exits = exits;
	}
	public Rooms(String level, Boolean locks, String ID, String name, String exits, ArrayList<Items> items) {
		
		this.levels = level;
		this.locks = locks;
		this.ID = ID;
		this.name = name;
		this.exits = exits;
		this.items = items;
	}
	/*
	 * CONSTRUCTOR
	 */
	public Rooms(Image map, Image icon, ImageView viewIcon, ImageView viewMap, TextArea displayStory, TextArea displayCommand) {
		 this.map = map;
		 this.icon = icon;
		 this.viewIcon = viewIcon;
		 this.viewMap = viewMap;
		 this.displayStory = displayStory;
		 this.displayCommand = displayCommand;
	 }

	public String getRoomLevel() {
		return this.levels;
	}
	public void setRoomLevel(String levels) {
		this.levels = levels;
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
	public ArrayList<Items> getRoomItems() {
		return this.items;
	}
	public void setRoomItems(ArrayList<Items> items) {
		this.items = items;
	}
	
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public String displayItems() {
		String display = "You find: \n";
		if(items.size()>0) {
			for(int i = 0; i < items.size(); i++) {
				display += items.get(i).name + "\n(" + items.get(i).description;
			}
		}else {
			display = "You didn't find anything";
		}
		return display;
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
		commandMenu.setNavigateMap(map, viewMap, 750, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 860, 380);
		commandMenu.setLoadGameStory(displayStory, readFile[0] + "\n\n" + readFile[1]);
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Drug Store (North West)\n" + "2. Inn (South West)\n" + "3. Saloon (East)\n" 
				+ "4. Access Town (North)\n" + "5. Inventory\n" + "6. Save Game");
		
		System.out.println("Loading Town Hub: Location Rooms Class"); // Testing purpose

	}
	//Drug Store
	public void DrugStore_1B() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap, 750, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 780, 350);
		commandMenu.setLoadGameStory(displayStory, readFile[2] + "\n\n" + readFile[3]);
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Town Hub (East)\n" + "2. Inn (South)\n" + "3. Shop\n" 
				+ "4. Inventory\n" + "5. Save Game");
		
		System.out.println("Loading Drug Store : Location Rooms Class"); // Testing purpose
	}
	//Inn
	public void Inn_1C() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap, 750, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 790, 434);
		commandMenu.setLoadGameStory(displayStory, readFile[4] + "\n\n" + readFile[5]);
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Drug Store (North)\n" + "2. Town Hub (East)\n" + "3. Search Room\n" 
				+ "4. Inventory\n" + "5. Save Game");
		
		System.out.println("Loading Inn Room : Location Rooms Class"); // Testing purpose

	}
	//Saloon
	public void Saloon_1D() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap, 750, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 955, 350); 
		commandMenu.setLoadGameStory(displayStory, readFile[6] + "\n\n" + readFile[7]);
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Town Hub (West)\n" + "2. Jail (South)\n"+ "3. Search Room\n" + "4. Inventory\n" 
				+ "5. Save Game");
		
		System.out.println("Loading Saloon Store : Location Rooms class"); // Testing purpose

	}
	//Jail
	public void Jail_1E() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap, 750, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 935, 434); 
		commandMenu.setLoadGameStory(displayStory, readFile[8] + "\n\n" + readFile[9]);
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Saloon (North)\n" + "2. Inventory\n" + "3. Save Game");
		
		System.out.println("Loading Jail : Location Rooms Class"); // Testing purpose
	}
	//Main Desert Hub
	public void MainDesertHub_2A() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Image map = new Image("file:images/TownAccess.png"); 
		commandMenu.setNavigateMap(map, viewMap, 645, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 885, 340); 
		commandMenu.setLoadGameStory(displayStory, "Main Desert Hub\n\nA road is leading to the middle of the desert. The landscape is barren, although "
				+ "there are a few small cacti near the path. There is a fork in the road and a sign with arrows that points both directions. Below the "
				+ "East arrow there is a text that reads “Fort Birman”, and below the West arrow, there is a text that reads “Nebelung Point”. To the "
				+ "South is Bombay Hill.");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Access Path 1 (East)\n" + "2. Access Path 2 (West)\n" + "3. Bombay Hill (South)\n" 
				+ "4. Search Area\n" + "5. Inventory\n" + "6. Save");
		
		System.out.println("Loading Main Desert Hub : Location Rooms Class"); // Testing purpose
	}
	//Access Path 1
	public void AccessPath1_2B() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Image map = new Image("file:images/TownAccess.png"); 
		commandMenu.setNavigateMap(map, viewMap, 645, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 995, 340); 
		commandMenu.setLoadGameStory(displayStory, "Access Path 1\n\nA winding path through a canyon. The canyon walls loom upwards towards the "
				+ "sky, and you can see a sliver of blue. If you squint you can see shadows moving along the canyon rim, but you can’t quite make "
				+ "out what they might be. To the West is the road to Fort Birman, and to the East is the crossroads. ");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Main Desert Hub (West)\n" + "2. Fort Birman (East)\n" + "3. Search Area\n" 
				+ "4. Inventory\n" + "5. Save");
		
		System.out.println("Loading Access Path 1 : Location Rooms Class"); // Testing purpose
	}
	//Access Path 2
	public void AccessPath2_2C() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Image map = new Image("file:images/TownAccess.png"); 
		commandMenu.setNavigateMap(map, viewMap, 645, 300);
		commandMenu.setNavigateIcon(icon, viewIcon, 775, 340); 
		commandMenu.setLoadGameStory(displayStory, "Access Path 2\n\nThe path travels West, up a hill and into the mountains. You start to come "
				+ "across a few pine trees scattered about, and you even pass by a stream. The water looks pure and cold so you stop to take a "
				+ "drink and enjoy the scenery. A bird flies overhead and you hear a rustling in a tree that might be a squirrel. If you continue "
				+ "West on this path, you will come to Nebelung Point, and if you go back East now, you will be at the crossroads again.");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Main Desert Hub (East)\n" + "2. Nebelung Point (West)\n" + "3. Search Area\n" 
				+ "4. Inventory\n" + "5. Save");
		
		System.out.println("Loading Access Path 1 : Location Rooms Class"); // Testing purpose
	}
}
