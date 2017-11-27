import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameControl {
	
	GameDatabase read = new GameDatabase("database/Doors.txt");
	String[] doorFile = read.readFile();
	
	Boolean lockMainMenu = true; //This will change to false when the game is started
	Boolean lockLoadGame = true; //This will change to false when the game is started
	Boolean unlockSaveGame = false; //This will change to true when the game is started
	Boolean backToGame = false;
	Boolean newGame = true; //This will change to false when the game is started
	Boolean inventoryView = false;
	boolean storeView = false;
	boolean itemView = false;
	boolean monsterAlive = true;
	
	int itemIndex;

	int maxHealth = 280;
	int damage = 0;
	int totalHealth = 280;
	
	int monsterMaxHealth = 280;
	int monsterTotalHealth = 280;

	Boolean testPuzzle = true;
	Boolean unlockDoor = false;
	Boolean inBattle = true;
	
	Potion healthPotion = new Potion("i1", "Health Potion", "A bottle shaped like a human heart with a red liquid inside.\n", 2, "H", 30);
	Potion attackPotion = new Potion("i2", "Attack Potion", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.\n", 3, "A", 100);
	Potion bagOfGold = new Potion("i3", "Bag of Coins", "A small bag of shiney gold coins with a creepy looking face in the center.", 2, "G", 2);
	
	Weapon dagger = new Weapon("i4", "Dagger", "A small, silver knife with a carved wooden handle.",3,1);
	Weapon animalClaw = new Weapon("i5", "Animal Claw", "A small but sharp claw from an animal. Not much better than using your fists.",1,2);
	Weapon boneClub = new Weapon("i6","Bone Club", "A club made from a bone of unknown origin.", 2,3);
	Weapon pistol = new Weapon("i7", "Pistol", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!", 5, 5);
	Weapon revolver = new Weapon("i8", "Revolver", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!",6,7);
	Weapon goldenRevolver = new Weapon("i9", "Golden Revolver", "A heavy, gold revolver. There is an inscription on the side that reads “To the Sheriff, may your enemies always turn to gold”.", 10,10);
	
	Items[] drugstore1 = {healthPotion,attackPotion, pistol};
	Store store1 = new Store(drugstore1);
	
	ArrayList<Items> inn1CItems = new ArrayList<Items>(Arrays.asList(bagOfGold));
	ArrayList<Items> saloon3EItems = new ArrayList<Items>(Arrays.asList(bagOfGold,pistol));
	ArrayList<Items> inn4CItems = new ArrayList<Items>(Arrays.asList(attackPotion,bagOfGold));
	
	Items[] drugstore2 = {healthPotion,attackPotion,revolver,goldenRevolver};
	Store store2 = new Store(drugstore2);
	
	Puzzles puzzle = new Puzzles("");
	
	Player player;
	//DISPLAY HEALTH BAR
	public void displayHealthBar(Rectangle maxHealthBar, Rectangle healthBar, Image healthIcon, ImageView viewHealthIcon) {
		//Setting the properties of the rectangle 
		maxHealthBar.setX(595); 
		maxHealthBar.setY(35); //215
		maxHealthBar.setWidth(maxHealth); 
		maxHealthBar.setHeight(20);
		maxHealthBar.setStroke(Color.WHITE);
		maxHealthBar.setFill(Color.CRIMSON);
	  
		healthBar.setX(595);
		healthBar.setY(35); //215
		healthBar.setWidth(maxHealth);
		healthBar.setHeight(20);
		healthBar.setStroke(Color.WHITE);
		healthBar.setFill(Color.GREEN);
		
		viewHealthIcon.setImage(healthIcon);
		viewHealthIcon.setLayoutX(595);
		viewHealthIcon.setLayoutY(20); //200
	}
	//ADJUST HEALTH BAR
	public int healthMeter(Rectangle healthBar, int totalHealth, int adjustHealth, Text prompt) {
		CommandMenu commandMenu = new CommandMenu();
		//adjustHealth = damage(negative value)
		if(adjustHealth < 0) {
			totalHealth = totalHealth + adjustHealth;
			healthBar.setWidth(totalHealth);
			healthBar.getWidth();
		}
		//adjustHealth = heal(positive value)
		else if (adjustHealth > 0 && totalHealth != maxHealth) {
			totalHealth = totalHealth + adjustHealth;
			healthBar.setWidth(totalHealth);
			healthBar.getWidth();
		}
		if(totalHealth == maxHealth) {
			commandMenu.prompt(prompt, "HEALTH IS FULL");
		}
		else if(totalHealth == 0) {
			commandMenu.prompt(prompt, "GAME OVER");
		}
		return totalHealth;
	}
	//DISPLAY MONSTER HEALTH BAR
	public void displayMonsterHealthBar(Rectangle monsterMaxHealthBar, Rectangle monsterHealthBar, Image monsterIcon, ImageView viewMonsterIcon) {
		//Setting the properties of the rectangle 
		monsterMaxHealthBar.setX(595); 
		monsterMaxHealthBar.setY(75);
		monsterMaxHealthBar.setWidth(monsterMaxHealth); 
		monsterMaxHealthBar.setHeight(20);
		monsterMaxHealthBar.setStroke(Color.WHITE);
		monsterMaxHealthBar.setFill(Color.CRIMSON);
		
		monsterHealthBar.setX(595); 
		monsterHealthBar.setY(75);
		monsterHealthBar.setWidth(monsterMaxHealth); 
		monsterHealthBar.setHeight(20);
		monsterHealthBar.setStroke(Color.WHITE);
		monsterHealthBar.setFill(Color.PURPLE);
		
		viewMonsterIcon.setImage(monsterIcon);
		viewMonsterIcon.setLayoutX(595);
		viewMonsterIcon.setLayoutY(60);
	}
	//REMOVE MONSTER HEALTH BAR
	public void removeMonsterHealthBar(Rectangle monsterMaxHealthBar, Rectangle monsterHealthBar, ImageView viewMonsterIcon) {
		monsterMaxHealthBar.setY(-75);
		monsterHealthBar.setY(-75);
		viewMonsterIcon.setLayoutY(-60);
	}
	//ADJUST MONSTER HEALTH BAR
	public int monsterHealthMeter(Rectangle monsterHealthBar, int monsterTotalHealth, int adjustHealth, Text prompt) {
		CommandMenu commandMenu = new CommandMenu();
		//adjustHealth = damage(negative value)
		if(adjustHealth < 0) {
			monsterTotalHealth = monsterTotalHealth + adjustHealth;
			monsterHealthBar.setWidth(monsterTotalHealth);
			monsterHealthBar.getWidth();
		}
		if(monsterTotalHealth <= 0) {
			commandMenu.prompt(prompt, "MONSTER DEFEATED");
		}
		return monsterTotalHealth;
	}
	
	public void gameControl(Text commandText, TextField inputCommand, Text prompt, Image icon, Image map, ImageView viewIcon, ImageView viewMap, TextArea displayStory, 
			TextArea displayCommand, Rectangle maxHealthBar, Rectangle healthBar, Image healthIcon, ImageView viewHealthIcon, Rectangle monsterMaxHealthBar, 
			Rectangle monsterHealthBar, Image monsterIcon, ImageView viewMonsterIcon) {
		//ReadFile read = new ReadFile();
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		
		Rooms TownHub = new Rooms("Bombay Hill", false, "1A", "Town Hub", "");
		Rooms DrugStore = new Rooms("Bombay Hill", false, "", "Drug Store", "");
		Rooms Inn = new Rooms("Bombay Hill", false, "", "Inn", "", inn1CItems);
		Rooms Saloon = new Rooms("Bombay Hill", false, "", "Saloon", "");
		Rooms Jail = new Rooms("Bombay Hill", false, "1E", "Jail", "");
		Rooms MainDesertHub = new Rooms("Town Access", false, "", "Main Desert Hub", "");
		Rooms AccessPath1 = new Rooms("Town Access", false, "", "Access Path 1", "");
		Rooms AccessPath2 = new Rooms("Town Access", false, "", "Access Path 2", "");
		
		Monsters monster = new Monsters();
		//CENTER PANE------------------------------------------------------------------------------------------------------------------------
		//DISPLAY STORY & COMMAND MENU
		commandMenu.setDisplayStory(displayStory);
		displayStory = commandMenu.getDisplayStory();
		commandMenu.setDisplayCommand(displayCommand);
		displayCommand = commandMenu.getDisplayCommand();
		//BOTTOM PANE-----------------------------------------------------------------------------------------------------------------------
		//EVENTHANDLER COMAMND
		commandText.setText("Command: ");
		commandText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		commandText.setFill(Color.WHITE);
		inputCommand.setPromptText("Enter your command.");
        inputCommand.setPrefWidth(432);
		inputCommand.setOnKeyPressed(e -> {
		String command = "";
		if(e.getCode() == KeyCode.ENTER) {
			command = inputCommand.getText();
			System.out.println(inputCommand.getText()); // Testing Purpose
			inputCommand.clear();
			commandMenu.prompt(prompt, ""); //Reset prompt
		}	
		
		if(TownHub.getRoomLevel().equals("Bombay Hill")) {
			//MAIN MENU-------------------------------------------------------------------------------------------------------------------------
		    if(lockMainMenu && (command.equals("1") || command.equalsIgnoreCase("new game"))){ //1. Start New Game
		    	
		    	if(command.equalsIgnoreCase("new game")|| (command.equals("1") && newGame)) {
		    		newGame = false;
					player = new Player();
					System.out.println("Player Created");
					player.inventory.add(dagger); //Testing Inventory
				}
		    	lockMainMenu = false;
				unlockSaveGame = true;
				room.TownHub_1A();
				TownHub.setRoomLocks(true);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				MainDesertHub.setRoomLocks(false);
				TownHub.setRoomExits("D00");
				
				displayHealthBar(maxHealthBar, healthBar, healthIcon, viewHealthIcon);
				
				puzzle.setPuzzleID(" ");
				System.out.println("Loading Town Hub : Location Game Control"); // Testing purpose
		    }
		    //MAIN MENU > Load Game
		    else if(lockMainMenu && (command.equals("2") || command.equalsIgnoreCase("load game"))) {

		    	room.display("Load Game", "Action\n" + "0. Back");
				commandMenu.loadGame("testGame.ini");
				lockLoadGame = false;
			}
		    //MAIN MENU > Load Game > Back
			else if(lockMainMenu && (command.equals("0") || command.equalsIgnoreCase("back"))) {
				
				room.display("Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is fed up with his cows getting rustled by a gang known as the Long Riders. "
						+ "He hears the sheriff won’t be much help, so he takes matters into his own hands. Heck starts his adventure in center of Bombay Hill, one of "
						+ "three towns controlled by the Long Riders, his plan is to find the leader of the gang and taking them out.", "Action\n" + "1. New Game\n" 
						+ "2. Load Game\n" + "3. Credit");
				lockLoadGame = true;
			}
		    //TOWN HUB---------------------------------------------------------------------------------------------------------------------------
			else if(!inventoryView && !itemView && (DrugStore.getRoomLocks() && !storeView &&(command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Inn.getRoomLocks() && (command.equals("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Saloon.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west"))) ||
		    		(MainDesertHub.getRoomLocks() && (command.equals("3") ||  command.equalsIgnoreCase("bombay hill") || command.equalsIgnoreCase("south")))) {
				//TOWN HUB > Door
				if(!inventoryView && !itemView && DrugStore.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) {
					room.display(doorFile[0] + "\n\n" + doorFile[1] + "\n\n" + doorFile[2], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					DrugStore.setRoomLocks(false);
					DrugStore.setRoomExits("D01");
					
				}
				else if(!inventoryView && !itemView && Inn.getRoomLocks() && (command.equals("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) {
					room.display(doorFile[3] + "\n\n" + doorFile[4] + "\n\n" + doorFile[5], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					Inn.setRoomLocks(false);
					Inn.setRoomExits("D04");
				}
				else if(!inventoryView && !itemView && Saloon.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west"))) {
					room.display(doorFile[6] + "\n\n" + doorFile[7] + "\n\n" + doorFile[8], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					Saloon.setRoomLocks(false);
					Saloon.setRoomExits("D07");
				}
				else if(!inventoryView && !itemView && MainDesertHub.getRoomLocks() && (command.equals("3") ||  command.equalsIgnoreCase("bombay hill") || command.equalsIgnoreCase("south"))) {
					room.display("Main Desert Hub\n\nA path that leads out of the wilderness and into town\n\nDo you want to enter Town Hub?. ", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					MainDesertHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D11");
				}
			}
			else if(!inventoryView && !itemView && TownHub.getRoomExits().contains("D00") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")) ||
					(DrugStore.getRoomExits().contains("D01") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(TownHub.getRoomExits().contains("D05") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Inn.getRoomExits().contains("D04") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(TownHub.getRoomExits().contains("D06") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Saloon.getRoomExits().contains("D07") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(MainDesertHub.getRoomExits().equals("D10") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(MainDesertHub.getRoomExits().equals("D11") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
				
				room.TownHub_1A();
				TownHub.setRoomLocks(true);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				MainDesertHub.setRoomLocks(false);
				
				DrugStore.setRoomExits("");
				Inn.setRoomExits("");
				Saloon.setRoomExits("");
				
				DrugStore.setRoomID("");
				Saloon.setRoomID("");
				Inn.setRoomID("");
				
			}
		    //DRUG STORE-------------------------------------------------------------------------------------------------------------------------
			else if(!inventoryView && !itemView && (TownHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) ||
					(Inn.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north")))) {
				//DRUG STORE > Door
				if(!inventoryView && !itemView && TownHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) {
					room.display(doorFile[9] + "\n\n" + doorFile[10] + "\n\n" + doorFile[11], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					TownHub.setRoomExits("D00");
					
				}
				else if(!inventoryView && !itemView && Inn.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north"))) {
					room.display(doorFile[12] + "\n\n" + doorFile[13] + "\n\n" + doorFile[14], "Action\n" 
							+ "0. No (N)\n" + "1. Yes (Y)");
					Inn.setRoomLocks(false);
					Inn.setRoomExits("D03");
					
				}
			}
			else if(!inventoryView && !itemView && (TownHub.getRoomExits().contains("D00") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(DrugStore.getRoomExits().contains("D01") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(DrugStore.getRoomExits().contains("D02") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Inn.getRoomExits().contains("D03") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
				
				room.DrugStore_1B();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(true);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				
				TownHub.setRoomExits("");
				Inn.setRoomExits("");
				
				DrugStore.setRoomID("1B");
				Inn.setRoomID("");
			}
		    //DRUG STORE > Shop
			else if(!inventoryView && !itemView && room.isStore && DrugStore.getRoomID().equals("1B") && !storeView &&(command.equalsIgnoreCase("3") || command.equalsIgnoreCase("shop"))) {
				storeView = true;
				room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + 
						" gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
				DrugStore.setRoomLocks(false);
			}
		    //Purchase Item -Health Potion
			else if(DrugStore.getRoomID().equals("1B") && !inventoryView && storeView && (command.equals("1") || command.equalsIgnoreCase("Health Potion"))){
				player.buyItem(store1.storeInventory[0],commandMenu,prompt,store1);
				room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + 
						" gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
				
			} 
		    //Purchase Item -Attack Potion
			else if(DrugStore.getRoomID().equals("1B") &&!inventoryView && storeView && (command.equals("2") || command.equalsIgnoreCase("Attack Potion"))){
				player.buyItem(store1.storeInventory[1],commandMenu,prompt,store1);
				room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + 
						" gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
				
			}
		    //Purchase Item -Pistol
			else if(DrugStore.getRoomID().equals("1B") &&!inventoryView && storeView && (command.equals("3") || command.equalsIgnoreCase("Pistol"))){
				player.buyItem(store1.storeInventory[2],commandMenu,prompt,store1);
				room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + " gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
				
			}
		    //Sell Item Menu
			else if(DrugStore.getRoomID().equals("1B") && !inventoryView &&storeView && (command.equals("4") || command.equalsIgnoreCase("Sell"))){
				inventoryView = true;
				room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				
			}
		    //Sell Item 1
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("1"))) {
				if(player.inventory.size()>0) {
					player.sellItem(player.inventory.get(0),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					System.out.println(player.inventory.size()+ "S");
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
		  //Sell Item 2
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("2"))) {
				if(player.inventory.size()>1) {
					player.sellItem(player.inventory.get(1),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
		  //Sell Item 3
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("3"))) {
				if(player.inventory.size()>2) {
					player.sellItem(player.inventory.get(2),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
		  //Sell Item 4
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("4"))) {
				if(player.inventory.size()>3) {
					player.sellItem(player.inventory.get(3),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
		  //Sell Item 5
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("5"))) {
				if(player.inventory.size()>4) {
					player.sellItem(player.inventory.get(4),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
		  //Sell Item 6
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("6"))) {
				if(player.inventory.size()>5) {
					player.sellItem(player.inventory.get(5),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
		  //Sell Item 7
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("7"))) {
				if(player.inventory.size()>6) {
					player.sellItem(player.inventory.get(6),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
		  //Sell Item 8
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("8"))) {
				if(player.inventory.size()>7) {
					player.sellItem(player.inventory.get(7),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
		  //Sell Item 9
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("9"))) {
				if(player.inventory.size()>8) {
					player.sellItem(player.inventory.get(8),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
		  //Sell Item 10
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("10"))) {
				if(player.inventory.size()>9) {
					player.sellItem(player.inventory.get(2),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}
			else if(DrugStore.getRoomID().equals("1B") && inventoryView &&storeView && (command.equals("11"))) {
				if(player.inventory.size()>10) {
					player.sellItem(player.inventory.get(10),commandMenu,prompt,store1);
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
				}else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}	
			}

		    //DRUG STORE > Shop > Back
			else if(DrugStore.getRoomID().equals("1B") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
				
				inventoryView = false;
				itemView = false;
				storeView = false;
				
				room.DrugStore_1B();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(true);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				storeView = false;
				inventoryView = false;
				
				TownHub.setRoomExits("");
				Inn.setRoomExits("");
				
				DrugStore.setRoomID("1B");
				Inn.setRoomID("");
			}
		    //INN--------------------------------------------------------------------------------------------------------------------------------
			else if(!inventoryView && !itemView && (DrugStore.getRoomLocks() && (command.equals("2") &&!storeView || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) || 
					(TownHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west")))) {
				//INN > Door
				if(DrugStore.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) {
					room.display(doorFile[15] + "\n\n" + doorFile[16] + "\n\n" + doorFile[17], "Action\n" 
							+ "0. No (N)\n" + "1. Yes (Y)");
					DrugStore.setRoomLocks(false);
					DrugStore.setRoomExits("D02");
				}
				else if(!inventoryView && !itemView && TownHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west"))) {
					room.display(doorFile[18] + "\n\n" + doorFile[19] + "\n\n" + doorFile[20], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					TownHub.setRoomExits("D05");
				}
			}
			else if(!inventoryView && !itemView && (DrugStore.getRoomExits().contains("D02") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(Inn.getRoomExits().contains("D03") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(TownHub.getRoomExits().contains("D05") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(Inn.getRoomExits().contains("D04") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
				
				room.Inn_1C();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(true);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				
				TownHub.setRoomExits("");
				DrugStore.setRoomExits("");
				
				Inn.setRoomID("1C");
				DrugStore.setRoomID("");
			}
		  //INN > Search Room
			else if(!itemView&& !inventoryView&& Inn.getRoomID().equals("1C") && (command.equals("3") || command.equalsIgnoreCase("search room"))) {
				room.display("Inn > Search Room\n\n" +Inn.displayItems(), "Action\n" 
							+ "0. Back\n" + "1. Pickup Item\n" + "2. Inventory\n" + "3. Save Game\n");
						Inn.setRoomLocks(false);
			}
		    //INN > Search Room > Back
			else if(Inn.getRoomID().equals("1C") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
				inventoryView = false;
				itemView = false;
				storeView = false;
				
				room.Inn_1C();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(true);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				
				TownHub.setRoomExits("");
				DrugStore.setRoomExits("");
				
				Inn.setRoomID("1C");
				DrugStore.setRoomID("");
			}
			else if(!inventoryView && !itemView && Inn.getRoomID().equals("1C") && (command.equals("1") || command.equalsIgnoreCase("pick up item"))) {
				if(Inn.items.size()>0) {
					player.inventory.add(Inn.items.get(0));
					commandMenu.prompt(prompt, Inn.items.get(0).name.toUpperCase()+ " ADDED TO INVETORY");
					Inn.items.remove(0);
					
					inventoryView = false;
					itemView = false;
					storeView = false;
					
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
					
					Inn.setRoomID("1C");
					DrugStore.setRoomID("");
					
				}else {
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
					
					Inn.setRoomID("1C");
					DrugStore.setRoomID("");
					commandMenu.prompt(prompt, "NOTHING TO PICK UP");
				}
			}
            //SALOON----------------------------------------------------------------------------------------------------------------------------------------------------------
			else if(!inventoryView && !itemView && (TownHub.getRoomLocks() && (command.equals("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) ||
					(Jail.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north")))) {
				//SALOON > Door
				if(TownHub.getRoomLocks() && (command.equals("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) {
					room.display(doorFile[21] + "\n\n" + doorFile[22] + "\n\n" + doorFile[23], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					TownHub.setRoomExits("D06");
					
					System.out.println("Requesting to enter Saloon : Location Game control"); // Testing purpose
				}
				//SALOON > Locked Door/Unlocked Door
				else if(!inventoryView && !itemView && Jail.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
					
					if(!inventoryView && !itemView && Jail.getRoomLocks() && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
						
						room.display(doorFile[24] + "\n\n" + doorFile[25], "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
						Jail.setRoomLocks(false);
						Jail.setRoomExits("D09");
						//AFTER FIGHT W/ BOSS IN JAIL
						//unlockDoor = true; //Testing Purpose
						if(unlockDoor == true) { 
							unlockDoor = true;
							testPuzzle = false;
						}
					}
					else if(!inventoryView && !itemView && Jail.getRoomLocks() && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
						
						room.display(doorFile[26] + "\n\n" + doorFile[27], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						Jail.setRoomLocks(false);
						Jail.setRoomExits("D09");
						
					}
				}
			}
			else if(!inventoryView && !itemView && (TownHub.getRoomExits().contains("D06") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) || 
					(Saloon.getRoomExits().contains("D07") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Saloon.getRoomExits().contains("D08") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Jail.getRoomExits().contains("D09") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
				
				if(!inventoryView && !itemView && (TownHub.getRoomExits().contains("D06") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) || 
					(Saloon.getRoomExits().contains("D07") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Saloon.getRoomExits().contains("D08") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
					room.Saloon_1D();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					
					Saloon.setRoomID("1D");
				}
				else if(!inventoryView && !itemView && Jail.getRoomExits().contains("D09") && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("unlock door"))) {
					
					room.display(doorFile[24] + "\n\n" + doorFile[25], "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
					Jail.setRoomExits("D09");
					commandMenu.prompt(prompt, "CANNOT OPEN DOOR");
					
					System.out.println("Unlock Puzzle to access Jail Room : Location Game Control"); // Testing Purpose
					System.out.println("Jail is locked : " + Jail.getRoomLocks()); // Testing Purpose
				}
								
				else if(!inventoryView && !itemView && Jail.getRoomExits().contains("D09") && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) {
					
					room.Saloon_1D();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					Saloon.setRoomID("1D");
				}
			}
		    
// -----------------------------------------------------------------------------------------------------------------------------	
 
		    //SALOON > Search Room
			else if(Saloon.getRoomID().contains("1D") && (command.equals("3") || command.equalsIgnoreCase("search room"))) {
				
				//ENCOUNTER HENCHMAN THEN FIGHT BOSS
				//Call Monster class
				
				// Encountering Henchman
				monster.setMonsterID("M6"); // M6 = HenchMan
				room.display("Saloon Store > Search Room > Monster Encounter\n\nHenchman\n\n" + monster.getMonsterDescription() , 
										"Action\n" + "0. Run away\n" + "1. Fight Henchman\n" + "2. Inventory\n" + "3. Save Game\n");
				Saloon.setRoomLocks(false);
				puzzle.setPuzzleID("P01"); // set puzzle ID to P01
				
				System.out.println("Searching around Saloon : Location Game Control");// Testing purpose
				System.out.println("Hench Man encountered! : Location Game Control"); // Testing purpose
			} 

		    // Fighting Henchman
			else if(puzzle.getPuzzleID().contains("P01") && command.equalsIgnoreCase("1") || command.equals("fight henchman")) {
				displayMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, monsterIcon, viewMonsterIcon);
				room.display("Saloon > Henchman\n\nThe Henchman is attacking you back! Quickly take time out!", "Action\n" + "0. Back\n" + "1. Attack");
				puzzle.setPuzzleID("P02");				
				System.out.println("Fighting HenchMan : Location Game Control"); // Testing purpose	
			}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		    
		    // Attacking Henchman
			else if(puzzle.getPuzzleID().contains("P02") && (command.equals("1") || command.equalsIgnoreCase("attack"))) {

				Random damageProb = new Random();
				int damageMonster = -(damageProb.nextInt(50 + 1) + player.attackDamage()); // Damage monster probability between -50 to -25
				System.out.println(damageMonster); //Display Amount of damage done to monster
				
				if(this.monsterTotalHealth > 0) {//When monster health is greater than 0 = true
					this.monsterTotalHealth = monsterHealthMeter(monsterHealthBar, this.monsterTotalHealth, damageMonster, prompt); //Player does damage to monster's health bar
					this.totalHealth = healthMeter(healthBar, this.totalHealth, -4, prompt); //Monster does damage to player's health bar
					room.display("Saloon > Henchman Battle\n\nYou must take him out fast!", "Action\n" + "0. Runaway\n" +"1. Attack");
					
					System.out.println("Attacking Henchman : Location Game Control"); // Testing purpose
				}
				else {
					puzzle.setPuzzleID("P03");
					monsterTotalHealth = 280; // Reinitialize monster health for Deputy Sheriff
					room.Jail_1E();
					room.display("Jail > Henchman\n\n"
							+ "You took some damage from that fight. The Deputy Sheriff who regularly comes to the Saloon saw you start a fight with the "
							+ "HenchMan and arrested you. Therefore you are sentenced for six months for aggrevated assult"
							+ "at the Bombay County Jail. The mission is critical and you do not have time to serve six months. "
							+ "Your priority is to kill the leader of the gang Long Riders. The Deputy Sheriff is old and fat but "
							+ "he is the big boss of this jail house. ", "0. Back\n" + "1. Fight Deputy Sheriff");
					
					unlockDoor = true;
					System.out.println("Sent to Jail : Location Game Control"); // Testing purpose
				}
			}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		    
            // Fighting Deputy Sheriff		    
			else if(puzzle.getPuzzleID().contains("P03") && (command.equals("1") || command.equalsIgnoreCase("fight Deputy Sheriff"))) {
				
				displayMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, monsterIcon, viewMonsterIcon);
				room.display("Jail > Deputy Sheriff\n\n “Heh. You think you can best me? I’m the fastest draw in town.”", "Action\n" + "0. Back\n" + "1. Attack");
				
				puzzle.setPuzzleID("P04");
				System.out.println("Attacking Deputy Sheriff : Location Game Control"); // Testing purpose
		   }
		    // Attacking Deputy Sheriff
			else if(puzzle.getPuzzleID().contains("P04") && (command.equals("1") || command.equalsIgnoreCase("attack"))) {
				Random damageProb = new Random();
				int deputyDamage = -(damageProb.nextInt(50 + 1) + player.attackDamage()); // Damage monster probability between -50 to -25
				System.out.println(deputyDamage); //Display Amount of damage done to monster
				
				if(this.monsterTotalHealth > 0) {//When monster health is greater than 0 = true
					this.monsterTotalHealth = monsterHealthMeter(monsterHealthBar, this.monsterTotalHealth, deputyDamage, prompt); //Player does damage to monster's health bar
					this.totalHealth = healthMeter(healthBar, this.totalHealth, -4, prompt); //Monster does damage to player's health bar
					room.display("Saloon > Deputy Sheriff\n\nThe Deputy Sheriff is a tough guy. He is known as the boss "
							+ "at this town hub. He runs the jail, but this will not stop you from taking him out!", "Action\n" + "0. Runaway\n" +"1. Attack");
					
					System.out.println("Attacking Deputy Sheriff : Location Game Control"); // Testing purpose
				}
				// Key Dropped
				else {
					puzzle.setPuzzleID("P05");
					room.display("Jail > Deputy Sheriff\n\n"
							+ "A key is dropped from Deputy Sheriff! Quickly grab it (and his gold) and unlock the jail! You do not have much time"
							+ " other deputies are making their way to the cell from the sound of gun fire! ", "0. Back\n" + "1. Pick up key");
					player.gold += 4;
					
					System.out.println("Key is dropped : Location Game Control"); // Testing purpose
				}
			}
		    
		    // picking up key
			else if(puzzle.getPuzzleID().contains("P05") && command.equalsIgnoreCase("1") || command.equalsIgnoreCase("pick up  key")) {
				puzzle.setPuzzleID("P06");
				room.display("Jail > Deputy Sheriff\n\n"
						+ "Now that you have the key, the jail may now be unlocked and you have earned respect as the new boss around the town hub! Go ahead and unlock"
						+ " the jail and head back out to the Saloon.", "0. Back\n" + "1. Unlock Jail");
				
				System.out.println("Jail is unlocked : Location Game Control"); // Testing purpose
			}
		    
		    //jail unlocked returning to saloon
			else if(puzzle.getPuzzleID().contentEquals("P06") && command.equalsIgnoreCase("1") || command.equalsIgnoreCase("unlock jail")) {
				room.Saloon_1D();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(true);
				Jail.setRoomLocks(false);
				Saloon.setRoomExits("D08");
				
				TownHub.setRoomExits("");
				
				Saloon.setRoomID("1D");
				
				puzzle.setPuzzleID("P07");
				room.Saloon_1D();
				room.display("Saloon\n\nYou unlocked the jail and now is a free man. You are back out at the Saloon covered dirt and sweat after the"
						+ "fight with the Deputy Sheriff. However, your mission is critical and you must keep moving. The leader of the "
						+ " Long Riders must be taken out before more cows are killed. It's time to make your way out of this old Town Hub and move on.", "Action\n" + "1. Town Hub");
				System.out.println("Returning to Saloon : Location Game Control"); // Testing purpose
			}
	    
//-------------------------------------------------------------------------------------------------------------------------------    
		    //SALOON > Search Room > Back
			else if(Saloon.getRoomID().equals("1D") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
				inventoryView = false;
				itemView = false;
				storeView = false;
				
				room.Saloon_1D();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(true);
				Jail.setRoomLocks(false);
				Saloon.setRoomExits("D08");
				
				TownHub.setRoomExits("");
				
				Saloon.setRoomID("1D");
				
				System.out.println("Loading back to Saloon : Location Game Control"); // Testing purpose
			}
		    		   
		    //JAIL-------------------------------------------------------------------------------------------------------------------------------
			else if(!inventoryView && !itemView && (Saloon.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
				//JAIL > Locked Door/Unlocked Door
				if(!inventoryView && !itemView && (Saloon.getRoomLocks() && testPuzzle && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
					
					// + "\n\n" + doorFile[32]
					room.display(doorFile[28] + "\n\n" + doorFile[29], "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
					Saloon.setRoomLocks(false);
					Saloon.setRoomExits("D08");
					
					System.out.println("Console Message #1 : Requesting to enter Jail room : Location Game Interface"); // Testing purpose
					System.out.println("Jail is locked : " + Jail.getRoomLocks()); // Testing Purpose @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					
					//SOLVE & UNLOCK PUZZLE
					//If FALSE, cannot access jail and throws error message
					//unlockDoor = true; //Testing Purpose
					if(unlockDoor == true) { 
						unlockDoor = true;
						testPuzzle = false;
					}
					
				}
				//JAIL > Unlocked Door
				else if(!inventoryView && !itemView && (Saloon.getRoomLocks() && unlockDoor && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
					room.display(doorFile[30] + "\n\n" + doorFile[31], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					Saloon.setRoomLocks(false);
					Saloon.setRoomExits("D08");
					Jail.setRoomExits("");	
										
					System.out.println("Console Message #2 : Requesting to enter Jail room : Location Game Interface"); // Testing purpose
				}
			}

		    //JAIL > Locked Door
			else if(!inventoryView && !itemView && (Saloon.getRoomExits().contains("D08") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y") || command.equalsIgnoreCase("unlock door")))) {
				
				if(!inventoryView && !itemView && Saloon.getRoomExits().contains("D08") && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("unlock door"))) {
					
					room.display(doorFile[28] + "\n\n" + doorFile[29], "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
					Saloon.setRoomExits("D08");
					commandMenu.prompt(prompt, "CANNOT OPEN DOOR");		
					
					System.out.println("Attempting to enter locked Jail : Location Game Interface"); // Testing purpose

				}
				else if(!inventoryView && !itemView && Saloon.getRoomExits().contains("D08") && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")) ||
						(Jail.getRoomExits().contains("D09") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
					
					room.Jail_1E();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(true);
					
					Saloon.setRoomID("");
					
					System.out.println("Inside Jail : Location Game Control");
					//testPuzzle = true; //Testing Purpose
					//unlockDoor = false; //Testing Purpose
					
				}
			}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@		    
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		    
			//INVENTORY
			else if(!storeView && (TownHub.getRoomLocks() && (command.equals("5") || command.equalsIgnoreCase("inventory")) ||
								 DrugStore.getRoomLocks() && (command.equals("4") || command.equalsIgnoreCase("inventory")) ||
								 	   Inn.getRoomLocks() && (command.equals("4") || command.equalsIgnoreCase("inventory")) ||
					                Saloon.getRoomLocks() && (command.equals("4") || command.equalsIgnoreCase("inventory"))) ||
					                Saloon.getRoomID().equals("1D") && (command.equals("2") || command.equalsIgnoreCase("inventory")) ||
					                   Inn.getRoomID().equals("1C") && (command.equals("2") || command.equalsIgnoreCase("inventory"))) {
				
				room.display(player.displayInventory(), "Action\n" + "Select Item (Enter Item Number)\n" + "0. Back");
				inventoryView = true;
				if(TownHub.getRoomLocks()==true) {
					
					backToGame = TownHub.getRoomLocks();
					System.out.println("TownHub");
				}
				else if(DrugStore.getRoomLocks()==true) {
					
					backToGame = DrugStore.getRoomLocks();
					System.out.println("DrugStore");
				}
				else if(Inn.getRoomLocks()==true) {
					
					backToGame = Inn.getRoomLocks();
					System.out.println("Inn");
				}
				else if(Saloon.getRoomLocks()==true) {
					
					backToGame = Saloon.getRoomLocks();
					System.out.println("Saloon");
				}
				else if(Jail.getRoomLocks()==true) {
					
					backToGame = Jail.getRoomLocks();
					System.out.println("Jail");
				}
			}
			else if(!storeView && inventoryView && command.equals("1")) {
				if(player.inventory.size()>0) {
					room.display(player.inventory.get(0).name + "\n-----------------\n" + player.inventory.get(0).description,player.inventory.get(0).displayItemOptions(player));
					itemView = true;
					itemIndex = 0;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
				
			}
			else if(!storeView && inventoryView && command.equals("2")) {
				if(player.inventory.size()>1) {
					room.display(player.inventory.get(1).name + "\n-----------------\n" + player.inventory.get(1).description,player.inventory.get(1).displayItemOptions(player));
					itemView = true;
					itemIndex = 1;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("3")) {
				if(player.inventory.size()>2) {
					room.display(player.inventory.get(2).name + "\n-----------------\n" + player.inventory.get(2).description,player.inventory.get(2).displayItemOptions(player));
					itemView = true;
					itemIndex =2;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("4")) {
				if(player.inventory.size()>3) {
					room.display(player.inventory.get(3).name + "\n-----------------\n" + player.inventory.get(3).description,player.inventory.get(3).displayItemOptions(player));
					itemView = true;
					itemIndex =3;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("5")) {
				if(player.inventory.size()>4) {
					room.display(player.inventory.get(4).name + "\n-----------------\n" + player.inventory.get(4).description,player.inventory.get(4).displayItemOptions(player));
					itemView = true;
					itemIndex =4;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("6")) {
				if(player.inventory.size()>5) {
					room.display(player.inventory.get(5).name + "\n-----------------\n" + player.inventory.get(5).description,player.inventory.get(5).displayItemOptions(player));
					itemView = true;
					itemIndex =5;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("7")) {
				if(player.inventory.size()>6) {
					room.display(player.inventory.get(6).name + "\n-----------------\n" + player.inventory.get(6).description,player.inventory.get(6).displayItemOptions(player));
					itemView = true;
					itemIndex =6;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("8")) {
				if(player.inventory.size()>7) {
					room.display(player.inventory.get(7).name + "\n-----------------\n" + player.inventory.get(7).description,player.inventory.get(7).displayItemOptions(player));
					itemView = true;
					itemIndex =7;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("9")) {
				if(player.inventory.size()>8) {
					room.display(player.inventory.get(8).name + "\n-----------------\n" + player.inventory.get(8).description,player.inventory.get(8).displayItemOptions(player));
					itemView = true;
					itemIndex =8;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
			}
			else if(!storeView && inventoryView && command.equals("10")) {
				if(player.inventory.size()>9) {
					room.display(player.inventory.get(9).name + "\n-----------------\n" + player.inventory.get(9).description,player.inventory.get(9).displayItemOptions(player));
					itemView = true;
					itemIndex =9;
				}
				else {
					commandMenu.prompt(prompt, "INVALID COMMAND");
				}
				
			}
			else if(itemView  && (command.equals("0"))||command.equalsIgnoreCase("back")) {
				if(TownHub.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;

					room.TownHub_1A();
					TownHub.setRoomLocks(true);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					MainDesertHub.setRoomLocks(false);
					
					DrugStore.setRoomExits("");
					Inn.setRoomExits("");
					Saloon.setRoomExits("");
					
					DrugStore.setRoomID("");
					Saloon.setRoomID("");
					Inn.setRoomID("");
					System.out.println("TownHub");
				}
				else if(DrugStore.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;

					
					room.DrugStore_1B();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(true);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					Inn.setRoomExits("");
					
					DrugStore.setRoomID("1B");
					Inn.setRoomID("");
					System.out.println("DrugStore");
				}
				else if(Inn.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;

					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
					
					Inn.setRoomID("1C");
					DrugStore.setRoomID("");
					System.out.println("Inn");
				}
				else if(Saloon.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;

					room.Saloon_1D();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					
					Saloon.setRoomID("1D");
					System.out.println("Saloon");
				}
				else if(Jail.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.Jail_1E();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(true);
					
					Saloon.setRoomID("");
					System.out.println("Jail");
				}
			}
			else if(itemView && /*(command.equals("1")) || */command.equalsIgnoreCase("use")||command.equalsIgnoreCase("equip")||command.equalsIgnoreCase("unequip")){
				System.out.println("Item View Handlers Works");
				if(player.inventory.get(itemIndex).canUse && command.equalsIgnoreCase("use")) {
					if(player.inventory.get(itemIndex).name.equals("Health Potion")){
						healthMeter(healthBar, this.totalHealth, healthPotion.strength, prompt);
						player.inventory.remove(itemIndex);
					}else {
						player.useItem((Potion)player.inventory.get(itemIndex), commandMenu, prompt);
						System.out.println("Use Item Success");
					}
					if(TownHub.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.TownHub_1A();
						TownHub.setRoomLocks(true);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						MainDesertHub.setRoomLocks(false);
						
						DrugStore.setRoomExits("");
						Inn.setRoomExits("");
						Saloon.setRoomExits("");
						
						DrugStore.setRoomID("");
						Saloon.setRoomID("");
						Inn.setRoomID("");
						System.out.println("TownHub");
					}
					else if(DrugStore.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.DrugStore_1B();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(true);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						Inn.setRoomExits("");
						
						DrugStore.setRoomID("1B");
						Inn.setRoomID("");
						System.out.println("DrugStore");
					}
					else if(Inn.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Inn_1C();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(true);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						DrugStore.setRoomExits("");
						
						Inn.setRoomID("1C");
						DrugStore.setRoomID("");
						System.out.println("Inn");
					}
					else if(Saloon.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Saloon_1D();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						
						Saloon.setRoomID("1D");
						System.out.println("inv " + inventoryView);
						System.out.println("st " + storeView);
						System.out.println("Saloon");
					}
					else if(Jail.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Jail_1E();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(true);
						
						Saloon.setRoomID("");
						System.out.println("Jail");
					} else if(MainDesertHub.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.MainDesertHub_2A();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						MainDesertHub.setRoomLocks(true);
						AccessPath1.setRoomLocks(false);
						AccessPath2.setRoomLocks(false);
						
						MainDesertHub.setRoomExits("");
						AccessPath2.setRoomExits("");
						
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("2A"); //Open up Search Area command
						removeMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon); //Remove monster health bar
						this.monsterTotalHealth = this.monsterMaxHealth; //Restore monster health
					}
					else if(AccessPath1.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.AccessPath1_2B();
						MainDesertHub.setRoomLocks(false);
						AccessPath1.setRoomLocks(true);
						AccessPath2.setRoomLocks(false);
						
						MainDesertHub.setRoomExits("");
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("");
					}
					else if(AccessPath2.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.AccessPath2_2C();
						MainDesertHub.setRoomLocks(false);
						AccessPath1.setRoomLocks(false);
						AccessPath2.setRoomLocks(true);
						
						MainDesertHub.setRoomExits("");
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("");
						}
				}else if(player.inventory.get(itemIndex).canEquip && player.inventory.get(itemIndex).equals(player.equippedWeap)) {
					player.unequipWeapon();
					commandMenu.prompt(prompt, player.inventory.get(itemIndex).name.toUpperCase() + " UNEQUIPPED");
					System.out.println("UnEq Success");
					if(TownHub.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.TownHub_1A();
						TownHub.setRoomLocks(true);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						MainDesertHub.setRoomLocks(false);
						
						DrugStore.setRoomExits("");
						Inn.setRoomExits("");
						Saloon.setRoomExits("");
						
						DrugStore.setRoomID("");
						Saloon.setRoomID("");
						Inn.setRoomID("");
						System.out.println("TownHub");
					}
					else if(DrugStore.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.DrugStore_1B();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(true);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						Inn.setRoomExits("");
						
						DrugStore.setRoomID("1B");
						Inn.setRoomID("");
						System.out.println("DrugStore");
					}
					else if(Inn.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Inn_1C();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(true);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						DrugStore.setRoomExits("");
						
						Inn.setRoomID("1C");
						DrugStore.setRoomID("");
						System.out.println("Inn");
					}
					else if(Saloon.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Saloon_1D();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						
						Saloon.setRoomID("1D");
						System.out.println("inv " + inventoryView);
						System.out.println("st " + storeView);
						System.out.println("Saloon");
					}
					else if(Jail.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Jail_1E();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(true);
						
						Saloon.setRoomID("");
						System.out.println("Jail");
					} else if(MainDesertHub.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.MainDesertHub_2A();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						MainDesertHub.setRoomLocks(true);
						AccessPath1.setRoomLocks(false);
						AccessPath2.setRoomLocks(false);
						
						MainDesertHub.setRoomExits("");
						AccessPath2.setRoomExits("");
						
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("2A"); //Open up Search Area command
						removeMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon); //Remove monster health bar
						this.monsterTotalHealth = this.monsterMaxHealth; //Restore monster health
					}
					else if(AccessPath1.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.AccessPath1_2B();
						MainDesertHub.setRoomLocks(false);
						AccessPath1.setRoomLocks(true);
						AccessPath2.setRoomLocks(false);
						
						MainDesertHub.setRoomExits("");
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("");
					}
					else if(AccessPath2.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.AccessPath2_2C();
						MainDesertHub.setRoomLocks(false);
						AccessPath1.setRoomLocks(false);
						AccessPath2.setRoomLocks(true);
						
						MainDesertHub.setRoomExits("");
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("");
						}
				}else if(player.inventory.get(itemIndex).canEquip && !player.inventory.get(itemIndex).equals(player.equippedWeap)) {
					player.equipWeapon(player.inventory.get(itemIndex));
					commandMenu.prompt(prompt, player.inventory.get(itemIndex).name.toUpperCase() + " EQUIPPED");
					System.out.println("Equip Success");
					if(TownHub.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.TownHub_1A();
						TownHub.setRoomLocks(true);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						MainDesertHub.setRoomLocks(false);
						
						DrugStore.setRoomExits("");
						Inn.setRoomExits("");
						Saloon.setRoomExits("");
						
						DrugStore.setRoomID("");
						Saloon.setRoomID("");
						Inn.setRoomID("");
						System.out.println("TownHub");
					}
					else if(DrugStore.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.DrugStore_1B();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(true);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						Inn.setRoomExits("");
						
						DrugStore.setRoomID("1B");
						Inn.setRoomID("");
						System.out.println("DrugStore");
					}
					else if(Inn.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Inn_1C();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(true);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						DrugStore.setRoomExits("");
						
						Inn.setRoomID("1C");
						DrugStore.setRoomID("");
						System.out.println("Inn");
					}
					else if(Saloon.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Saloon_1D();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
						
						Saloon.setRoomID("1D");
						System.out.println("inv " + inventoryView);
						System.out.println("st " + storeView);
						System.out.println("Saloon");
					}
					else if(Jail.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.Jail_1E();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(true);
						
						Saloon.setRoomID("");
						System.out.println("Jail");
					}
					else if(MainDesertHub.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.MainDesertHub_2A();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						MainDesertHub.setRoomLocks(true);
						AccessPath1.setRoomLocks(false);
						AccessPath2.setRoomLocks(false);
						
						MainDesertHub.setRoomExits("");
						AccessPath2.setRoomExits("");
						
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("2A"); //Open up Search Area command
						removeMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon); //Remove monster health bar
						this.monsterTotalHealth = this.monsterMaxHealth; //Restore monster health
					}
					else if(AccessPath1.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.AccessPath1_2B();
						MainDesertHub.setRoomLocks(false);
						AccessPath1.setRoomLocks(true);
						AccessPath2.setRoomLocks(false);
						
						MainDesertHub.setRoomExits("");
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("");
					}
					else if(AccessPath2.getRoomLocks()==true) {
						storeView = false;
						inventoryView = false;
						itemView = false;
						
						room.AccessPath2_2C();
						MainDesertHub.setRoomLocks(false);
						AccessPath1.setRoomLocks(false);
						AccessPath2.setRoomLocks(true);
						
						MainDesertHub.setRoomExits("");
						AccessPath1.setRoomExits("");
						AccessPath2.setRoomExits("");
						MainDesertHub.setRoomID("");
						}
				
				}else {
					System.out.println("PROBLEM");
				}
			}
			else if(itemView && command.equalsIgnoreCase("drop")) {
				player.dropItem(player.inventory.get(itemIndex), commandMenu, prompt);
				if(TownHub.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.TownHub_1A();
					TownHub.setRoomLocks(true);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					MainDesertHub.setRoomLocks(false);
					
					DrugStore.setRoomExits("");
					Inn.setRoomExits("");
					Saloon.setRoomExits("");
					
					DrugStore.setRoomID("");
					Saloon.setRoomID("");
					Inn.setRoomID("");
					System.out.println("TownHub");
				}
				else if(DrugStore.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.DrugStore_1B();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(true);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					Inn.setRoomExits("");
					
					DrugStore.setRoomID("1B");
					Inn.setRoomID("");
					System.out.println("DrugStore");
				}
				else if(Inn.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
					
					Inn.setRoomID("1C");
					DrugStore.setRoomID("");
					System.out.println("Inn");
				}
				else if(Saloon.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.Saloon_1D();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					
					Saloon.setRoomID("1D");
					System.out.println("inv " + inventoryView);
					System.out.println("st " + storeView);
					System.out.println("Saloon");
				}
				else if(Jail.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.Jail_1E();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(true);
					
					Saloon.setRoomID("");
					System.out.println("Jail");
				}else if(MainDesertHub.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.MainDesertHub_2A();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					MainDesertHub.setRoomLocks(true);
					AccessPath1.setRoomLocks(false);
					AccessPath2.setRoomLocks(false);
					
					MainDesertHub.setRoomExits("");
					AccessPath2.setRoomExits("");
					
					AccessPath1.setRoomExits("");
					AccessPath2.setRoomExits("");
					MainDesertHub.setRoomID("2A"); //Open up Search Area command
					removeMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon); //Remove monster health bar
					this.monsterTotalHealth = this.monsterMaxHealth; //Restore monster health
				}
				else if(AccessPath1.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.AccessPath1_2B();
					MainDesertHub.setRoomLocks(false);
					AccessPath1.setRoomLocks(true);
					AccessPath2.setRoomLocks(false);
					
					MainDesertHub.setRoomExits("");
					AccessPath1.setRoomExits("");
					AccessPath2.setRoomExits("");
					MainDesertHub.setRoomID("");
				}
				else if(AccessPath2.getRoomLocks()==true) {
					storeView = false;
					inventoryView = false;
					itemView = false;
					
					room.AccessPath2_2C();
					MainDesertHub.setRoomLocks(false);
					AccessPath1.setRoomLocks(false);
					AccessPath2.setRoomLocks(true);
					
					MainDesertHub.setRoomExits("");
					AccessPath1.setRoomExits("");
					AccessPath2.setRoomExits("");
					MainDesertHub.setRoomID("");
					}
				
			}
		    
		    //Back
			else if(backToGame && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
				storeView = false;
				inventoryView = false;
				itemView = false;
				
				if(TownHub.getRoomLocks()==true) {
					
					room.TownHub_1A();
					TownHub.setRoomLocks(true);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					MainDesertHub.setRoomLocks(false);
					
					DrugStore.setRoomExits("");
					Inn.setRoomExits("");
					Saloon.setRoomExits("");
					
					DrugStore.setRoomID("");
					Saloon.setRoomID("");
					Inn.setRoomID("");
					System.out.println("TownHub");
				}
				else if(DrugStore.getRoomLocks()==true) {
					
					room.DrugStore_1B();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(true);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					Inn.setRoomExits("");
					
					DrugStore.setRoomID("1B");
					Inn.setRoomID("");
					System.out.println("DrugStore");
				}
				else if(Inn.getRoomLocks()==true) {
					
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
					
					Inn.setRoomID("1C");
					DrugStore.setRoomID("");
					System.out.println("Inn");
				}
				else if(Saloon.getRoomLocks()==true) {
					
					room.Saloon_1D();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					
					Saloon.setRoomID("1D");
					System.out.println("Saloon");
				}
				else if(Jail.getRoomLocks()==true) {
					
					room.Jail_1E();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					Jail.setRoomLocks(true);
					
					Saloon.setRoomID("");
					System.out.println("Jail");
				}
			}
		    //Save Game
			if(!storeView && !inventoryView && !itemView && unlockSaveGame && ((TownHub.getRoomLocks() && (command.equalsIgnoreCase("6") || command.equalsIgnoreCase("save game"))) ||
					(DrugStore.getRoomLocks() && (command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) ||
					(Inn.getRoomLocks() && (command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) ||
					(Saloon.getRoomLocks() && (command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) ||
					(Jail.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("save game"))))) {
				
				commandMenu.prompt(prompt, "GAME SAVED");
				commandMenu.saveGame("testGame.ini");
			}
		}
		
		if(!inventoryView && !itemView && MainDesertHub.getRoomLevel().equals("Town Access")) {
			//MAIN DESERT HUB-----------------------------------------------------------------------------------------------------------------------
			//MAIN DESERT HUB > Path Way
			if(!inventoryView && !itemView && (TownHub.getRoomLocks() && (command.equals("4") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("north"))) ||
					(AccessPath1.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("west"))) ||
					(AccessPath2.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("east")))) {
				if(!inventoryView && !itemView && TownHub.getRoomLocks() && (command.equals("4") ||  command.equalsIgnoreCase("access town") || command.equalsIgnoreCase("north"))) {
					room.display("Town Hub > Path Way\n\nA path that leads out of town and into the wilderness.\n\nDo you want to enter Town Access?. ", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D10");
				}
				else if(!inventoryView && !itemView && AccessPath1.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("west"))) {
					room.display("Access Path 1 > Path Way\n\nThe path continues East to Fort Birman.\n\nDo you want to enter the Main Desert Hub?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					AccessPath1.setRoomLocks(false);
					AccessPath1.setRoomExits("D13");
				}
				else if(!inventoryView && !itemView && AccessPath2.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("east"))) {
					room.display("Access Path 2 > Path Way\n\nA path leading West through a mountain pass.\n\nDo you want to enter the Main Desert Hub?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					AccessPath2.setRoomLocks(false);
					AccessPath2.setRoomExits("D15");
				}
			}
			else if(!inventoryView && !itemView && MainDesertHub.getRoomExits().equals("D10") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")) ||
					(MainDesertHub.getRoomExits().equals("D11") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(MainDesertHub.getRoomExits().equals("D12") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(MainDesertHub.getRoomID().contains("2A") && (command.equals("0") || command.equalsIgnoreCase("runaway") || command.equalsIgnoreCase("back"))) ||
					(AccessPath1.getRoomExits().equals("D13") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) || 
					(MainDesertHub.getRoomExits().equals("D14") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(AccessPath2.getRoomExits().equals("D15") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
				room.MainDesertHub_2A();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				MainDesertHub.setRoomLocks(true);
				AccessPath1.setRoomLocks(false);
				AccessPath2.setRoomLocks(false);
				
				MainDesertHub.setRoomExits("");
				AccessPath2.setRoomExits("");
				
				AccessPath1.setRoomExits("");
				AccessPath2.setRoomExits("");
				MainDesertHub.setRoomID("2A"); //Open up Search Area command
				removeMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon); //Remove monster health bar
				this.monsterTotalHealth = this.monsterMaxHealth; //Restore monster health
			}
			//MAIN DESERT HUB > Search Area
			else if(MainDesertHub.getRoomID().contains("2A") && (command.equals("4") || command.equalsIgnoreCase("search area"))) {
				//ENCOUNTER MONSTER
				MainDesertHub.setRoomLocks(false);
				MainDesertHub.setRoomID("2A1"); //Open up attack monster
				displayMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, monsterIcon, viewMonsterIcon);
				room.display("Main Desert Hub > Search Area\n\nPack of Coyotes-------------------------------------\nA pack of rabid looking "
						+ "coyotes. Their eyes are sunken in, and their fur matted; they look as if they haven’t eaten for weeks.", "Action\n" + "0. Runaway\n" +"1. Attack");
				
				System.out.println("Encountering Coyotes : Location Game Control"); // Testing purpose
			}
			//ATTACK MONSTER
			else if(MainDesertHub.getRoomID().contains("2A1") && (command.equals("1") || command.equalsIgnoreCase("attack"))) {
				Random damageProb = new Random();
				int damageMonster = -(damageProb.nextInt(50 + 1) + 25); // Damage monster probability between -50 to -25
				System.out.println(damageMonster); //Display Amount of damage done to monster
				
				if(this.monsterTotalHealth > 0) {//When monster health is greater than 0 = true
					this.monsterTotalHealth = monsterHealthMeter(monsterHealthBar, this.monsterTotalHealth, damageMonster, prompt); //Player does damage to monster's health bar
					this.totalHealth = healthMeter(healthBar, this.totalHealth, -4, prompt); //Monster does damage to player's health bar
					room.display("Main Desert Hub > Search Area\n\nPack of Coyotes-------------------------------------\nA pack of rabid looking "
							+ "coyotes. Their eyes are sunken in, and their fur matted; they look as if they haven’t eaten for weeks.\n\n\"Grrrrarrrr\"", "Action\n" + "0. Runaway\n" +"1. Attack");
				}
				else {
					commandMenu.prompt(prompt, "YOU LOOT 1 GOLD AND AN ANIMAL CLAW");
					player.inventory.add(animalClaw);
					player.gold++;
					room.display("Main Desert Hub > Search Area\n\n", "Action\n" + "0. Back\n");
				}
				
				System.out.println("Attacking Coyotes : Location Game Control"); // Testing purpose
			}
			
			//ACCESS PATH 1-----------------------------------------------------------------------------------------------------------------------
			//ACCESS PATH 1 > Path Way
			else if(!inventoryView && !itemView && (MainDesertHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("access path 1") || command.equalsIgnoreCase("east"))) ||
					(AccessPath1.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("fort birman") || command.equalsIgnoreCase("east")))) {
				if(!inventoryView && !itemView && MainDesertHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("access path 1") || command.equalsIgnoreCase("east"))) {
					room.display("Main Desert Hub > Path Way\n\nThe path continues East to Fort Birman.\n\nDo you want to enter the Access Path 1?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					MainDesertHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D12");
				}
				//ACCESS PATH 1 > FORT BIRMAN (BROWN LOCK)
				else if(!inventoryView && !itemView && AccessPath1.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("fort birman") || command.equalsIgnoreCase("east"))) {
					room.display("Access Path 1 > Path Way\n\nA loan shark is blocking the way to the next town, he seems to be looking for something. "
							+ "Probably shouldn’t bother him until you know what he wants.", "Action\n" + "0. Leave Door\n" + "1. ?????");
				}
			}
			else if(!inventoryView && !itemView && (MainDesertHub.getRoomExits().equals("D12") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(AccessPath1.getRoomExits().equals("D13") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
				room.AccessPath1_2B();
				MainDesertHub.setRoomLocks(false);
				AccessPath1.setRoomLocks(true);
				AccessPath2.setRoomLocks(false);
				
				MainDesertHub.setRoomExits("");
				AccessPath1.setRoomExits("");
				AccessPath2.setRoomExits("");
				MainDesertHub.setRoomID("");
			}
			//ACCESS PATH 2-----------------------------------------------------------------------------------------------------------------------
			//ACCESS PATH 2 > Path Way
			else if(!inventoryView && !itemView && MainDesertHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("access path 2") || command.equalsIgnoreCase("west")) ||
					(AccessPath2.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("nebelung point") || command.equalsIgnoreCase("west")))) {
				if(!inventoryView && !itemView && MainDesertHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("access path 2") || command.equalsIgnoreCase("west"))) {
					room.display("Main Desert Hub > Path Way\n\nA path leading West through a mountain pass.\n\nDo you want to enter the Access Path 2?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					MainDesertHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D14");
				}
				//ACCESS PATH2 > NEBELUNG POINT (BROWN LOCK)
				else if(!inventoryView && !itemView && AccessPath2.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("nebelung point") || command.equalsIgnoreCase("west"))) {
					room.display("Access Path 2 > Path Way\n\nA giant rock is blocking the path of the next time. If you had a giant stick of "
							+ "dynamite, you could probably clear the way.", "Action\n" + "0. Leave Door\n" + "1. Use dynamite (0)");
				}
			}
			else if(!inventoryView && !itemView && (MainDesertHub.getRoomExits().equals("D14") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(AccessPath2.getRoomExits().equals("D15") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
				room.AccessPath2_2C();
				MainDesertHub.setRoomLocks(false);
				AccessPath1.setRoomLocks(false);
				AccessPath2.setRoomLocks(true);
				
				MainDesertHub.setRoomExits("");
				AccessPath1.setRoomExits("");
				AccessPath2.setRoomExits("");
				MainDesertHub.setRoomID("");
			}
			//INVENTORY-------------------------------------------------------------------------------------------------------------------------------------------------
			else if(!storeView && (MainDesertHub.getRoomLocks() && (command.equals("5") || command.equalsIgnoreCase("inventory"))) ||
								  (AccessPath1.getRoomLocks() && (command.equals("4") || command.equalsIgnoreCase("inventory"))) ||
								  (AccessPath2.getRoomLocks() && (command.equals("4") || command.equalsIgnoreCase("inventory")))) {
				
				room.display(player.displayInventory(), "Action\n" + "Select Item (Enter Item Number)\n" + "0. Back");
				inventoryView = true;
				if(MainDesertHub.getRoomLocks()==true) {
					
					backToGame = MainDesertHub.getRoomLocks();
					System.out.println("MainDesertHub");
				}
				else if(AccessPath1.getRoomLocks()==true) {
					
					backToGame = AccessPath1.getRoomLocks();
					System.out.println("AccessPath1");
				}
				else if(AccessPath2.getRoomLocks()==true) {
					
					backToGame = AccessPath2.getRoomLocks();
					System.out.println("AccessPath2");
				}
			}
			//INVENTORY > Back
			else if(backToGame && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
				storeView = false;
				inventoryView = false;
				itemView = false;
				
				if(MainDesertHub.getRoomLocks()==true) {
					
					room.MainDesertHub_2A();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					MainDesertHub.setRoomLocks(true);
					AccessPath1.setRoomLocks(false);
					AccessPath2.setRoomLocks(false);
					
					MainDesertHub.setRoomExits("");
					AccessPath2.setRoomExits("");
					
					AccessPath1.setRoomExits("");
					AccessPath2.setRoomExits("");
					MainDesertHub.setRoomID("2A"); //Open up Search Area command
					removeMonsterHealthBar(monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon); //Remove monster health bar
					this.monsterTotalHealth = this.monsterMaxHealth; //Restore monster health
				}
				else if(AccessPath1.getRoomLocks()==true) {
					
					room.AccessPath1_2B();
					MainDesertHub.setRoomLocks(false);
					AccessPath1.setRoomLocks(true);
					AccessPath2.setRoomLocks(false);
					
					MainDesertHub.setRoomExits("");
					AccessPath1.setRoomExits("");
					AccessPath2.setRoomExits("");
					MainDesertHub.setRoomID("");
				}
				else if(AccessPath2.getRoomLocks()==true) {
					
					room.AccessPath2_2C();
					MainDesertHub.setRoomLocks(false);
					AccessPath1.setRoomLocks(false);
					AccessPath2.setRoomLocks(true);
					
					MainDesertHub.setRoomExits("");
					AccessPath1.setRoomExits("");
					AccessPath2.setRoomExits("");
					MainDesertHub.setRoomID("");
				}
			}
		}
		//Exit Game
		if(command.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
	});
	}	
}
