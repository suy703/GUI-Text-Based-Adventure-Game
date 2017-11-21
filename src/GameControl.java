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
	
	Potion healthPotion = new Potion("i1", "Health Potion", "A bottle shaped like a human heart with a red liquid inside.\n", 2, "H", 3);
	Potion attackPotion = new Potion("i2", "Attack Potion", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.\n", 3, "A", 2);
	Weapon pistol = new Weapon("i7", "Pistol", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!\n", 5, 5);
	Items[] drugstore1 = {healthPotion,attackPotion, pistol};
	Store store1 = new Store(drugstore1);
	Puzzles puzzle = new Puzzles("");
	
	Player player;
	//CENTER PANE--------------------------------------------------------------------------------------
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
	
	//DISPLAY STORY & COMMAND MENU
	public void controlDisplay(TextArea displayStory, TextArea displayCommand) {
		CommandMenu commandMenu = new CommandMenu();
		commandMenu.setDisplayStory(displayStory);
		displayStory = commandMenu.getDisplayStory();
		commandMenu.setDisplayCommand(displayCommand);
		displayCommand = commandMenu.getDisplayCommand();
	}
	//BOTTOM PANE-------------------------------------------------------------------------------------
	//EVENTHANDLER COMAMND
	public void gameControl(Text commandText, TextField inputCommand, Text prompt, Image icon, Image map, ImageView viewIcon, ImageView viewMap, TextArea displayStory, 
			TextArea displayCommand, Rectangle maxHealthBar, Rectangle healthBar, Image healthIcon, ImageView viewHealthIcon, Rectangle monsterMaxHealthBar, 
			Rectangle monsterHealthBar, Image monsterIcon, ImageView viewMonsterIcon) {
		//ReadFile read = new ReadFile();
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		
		Rooms TownHub = new Rooms("Bombay Hill", false, "1A", "Town Hub", "", "");
		Rooms DrugStore = new Rooms("Bombay Hill", false, "", "Drug Store", "", "I1, I2, I4");
		Rooms Inn = new Rooms("Bombay Hill", false, "", "Inn", "", "I3");
		Rooms Saloon = new Rooms("Bombay Hill", false, "", "Saloon", "", "");
		Rooms Jail = new Rooms("Bombay Hill", false, "1E", "Jail", "", "");
		Rooms MainDesertHub = new Rooms("Town Access", false, "", "Main Desert Hub", "", "");
		Rooms AccessPath1 = new Rooms("Town Access", false, "", "Access Path 1", "", "");
		
		Monsters monster = new Monsters();
		
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
					Items testWeap = new Weapon("0001", "test item", "ignore description" ,10, 10);//Testing Inventory
					player.inventory.add(testWeap); //Testing Inventory
					player.equipWeapon(player.inventory.get(0));
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
			else if((DrugStore.getRoomLocks() && !storeView &&(command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Inn.getRoomLocks() && (command.equals("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Saloon.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west"))) ||
		    		(MainDesertHub.getRoomLocks() && (command.equals("3") ||  command.equalsIgnoreCase("bombay hill") || command.equalsIgnoreCase("south")))) {
				//TOWN HUB > Door
				if(DrugStore.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) {
					room.display(doorFile[0] + "\n\n" + doorFile[1] + "\n\n" + doorFile[2], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					DrugStore.setRoomLocks(false);
					DrugStore.setRoomExits("D01");
				}
				else if(Inn.getRoomLocks() && (command.equals("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) {
					room.display(doorFile[3] + "\n\n" + doorFile[4] + "\n\n" + doorFile[5], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					Inn.setRoomLocks(false);
					Inn.setRoomExits("D04");
				}
				else if(Saloon.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west"))) {
					room.display(doorFile[6] + "\n\n" + doorFile[7] + "\n\n" + doorFile[8], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					Saloon.setRoomLocks(false);
					Saloon.setRoomExits("D07");
				}
				else if(MainDesertHub.getRoomLocks() && (command.equals("3") ||  command.equalsIgnoreCase("bombay hill") || command.equalsIgnoreCase("south"))) {
					room.display("Main Desert Hub\n\nA path that leads out of the wilderness and into town\n\nDo you want to enter Town Hub?. ", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					MainDesertHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D11");
				}
			}
			else if(TownHub.getRoomExits().contains("D00") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")) ||
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
			else if((TownHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) ||
					(Inn.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north")))) {
				//DRUG STORE > Door
				if(TownHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) {
					room.display(doorFile[9] + "\n\n" + doorFile[10] + "\n\n" + doorFile[11], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					TownHub.setRoomExits("D00");
				}
				else if(Inn.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north"))) {
					room.display(doorFile[12] + "\n\n" + doorFile[13] + "\n\n" + doorFile[14], "Action\n" 
							+ "0. No (N)\n" + "1. Yes (Y)");
					Inn.setRoomLocks(false);
					Inn.setRoomExits("D03");
				}
			}
			else if((TownHub.getRoomExits().contains("D00") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
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
			else if(DrugStore.getRoomID().equals("1B") && !storeView &&(command.equalsIgnoreCase("3") || command.equalsIgnoreCase("shop"))) {
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
			else if((DrugStore.getRoomLocks() && (command.equals("2") &&!storeView || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) || 
					(TownHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west")))) {
				//INN > Door
				if(DrugStore.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) {
					room.display(doorFile[15] + "\n\n" + doorFile[16] + "\n\n" + doorFile[17], "Action\n" 
							+ "0. No (N)\n" + "1. Yes (Y)");
					DrugStore.setRoomLocks(false);
					DrugStore.setRoomExits("D02");
				}
				else if(TownHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west"))) {
					room.display(doorFile[18] + "\n\n" + doorFile[19] + "\n\n" + doorFile[20], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					TownHub.setRoomExits("D05");
				}
			}
			else if((DrugStore.getRoomExits().contains("D02") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
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
			else if(Inn.getRoomID().equals("1C") && (command.equals("3") || command.equalsIgnoreCase("search room"))) {
				
				Items gold = new Items("i3", "Gold (1)", "A shiney gold coin with a creepy looking face in the center.", 1);
				room.display("Drug Store > Search Room\n\n" + gold.getName() + "---------------------------------------------\n" + gold.getDescription(), "Action\n" 
						+ "0. Back\n" + "1. Pickup Item\n" + "2. Inventory\n" + "3. Save Game\n");
				Inn.setRoomLocks(false);
			}
		    //INN > Search Room > Back
			else if(Inn.getRoomID().equals("1C") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
				
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
		    
            //SALOON----------------------------------------------------------------------------------------------------------------------------------------------------------
			else if((TownHub.getRoomLocks() && (command.equals("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) ||
					(Jail.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north")))) {
				//SALOON > Door
				if(TownHub.getRoomLocks() && (command.equals("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) {
					room.display(doorFile[21] + "\n\n" + doorFile[22] + "\n\n" + doorFile[23], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					TownHub.setRoomExits("D06");
					
					System.out.println("Requesting to enter Saloon : Location Game control"); // Testing purpose
				}
				//SALOON > Locked Door/Unlocked Door
				else if(Jail.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
					
					if(Jail.getRoomLocks() && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
						
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
					else if(Jail.getRoomLocks() && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
						
						room.display(doorFile[26] + "\n\n" + doorFile[27], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						Jail.setRoomLocks(false);
						Jail.setRoomExits("D09");
						
					}
				}
			}
			else if((TownHub.getRoomExits().contains("D06") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) || 
					(Saloon.getRoomExits().contains("D07") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Saloon.getRoomExits().contains("D08") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(Jail.getRoomExits().contains("D09") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
				
				if((TownHub.getRoomExits().contains("D06") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) || 
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
				else if(Jail.getRoomExits().contains("D09") && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("unlock door"))) {
					
					room.display(doorFile[24] + "\n\n" + doorFile[25], "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
					Jail.setRoomExits("D09");
					commandMenu.prompt(prompt, "CANNOT OPEN DOOR");
					
					System.out.println("Unlock Puzzle to access Jail Room : Location Game Control"); // Testing Purpose
					System.out.println("Jail is locked : " + Jail.getRoomLocks()); // Testing Purpose
				}
								
				else if(Jail.getRoomExits().contains("D09") && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) {
					
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
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		   
		    /* This still needs work, fighting the henchman works, still some bug
		     * Pending completion
		     */
		    
		    //SALOON > Search Room
			else if(Saloon.getRoomID().contains("1D") && (command.equals("3") || command.equalsIgnoreCase("search room"))) {
				
				//ENCOUNTER HENCHMAN THEN FIGHT BOSS
				//Call Monster class
				
				monster.setMonsterID("M6"); // M6 = HenchMan
				room.display("Saloon Store > Search Room > Monster Encounter\n\nHenchman\n\n" + monster.getMonsterDescription() , "Action\n" + "0. Back\n" + "1. Fight Henchman\n" + "2. Inventory\n" + "3. Save Game\n");
				Saloon.setRoomLocks(false);

				puzzle.setPuzzleID("P01"); // set puzzle ID to P01
				puzzle.setPuzzleLock(true);
				
				System.out.println("Searching around Saloon : Location Game Control"); // Testing purpose
				System.out.println("Hench Man encountered! : Location Game Control"); // Testing purpose
			} 

		    // Encountering Henchman
			else if(Saloon.getRoomID().contains("1D") && puzzle.getPuzzleID().contains("P01") && puzzle.getPuzzleLock() && command.equalsIgnoreCase("1") || command.equals("fight henchman")) {

				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(true);
				Jail.setRoomLocks(false);
				Jail.setRoomID("1E");
				room.Jail_1E();
				
				puzzle.setPuzzleID("P01"); // set puzzle ID to P01
				puzzle.setPuzzleLock(true);
				
				room.display("Jail > Deputy Sheriff\n\n"
						+ "You took some damage from that fight. The Deputy Sheriff who regularly comes to the Saloon saw you start a fight with the "
						+ "HenchMan and arrested you. Therefore you are sentenced for six months for aggrevated assult"
						+ "at the Bombay County Jail. The mission is critical and you do not have time to serve six months. "
						+ "Your priority is to kill the leader of the gang Long Riders. The Deputy Sheriff is old and fat but "
						+ "he is the big boss of this jail house. ", "0. Back\n" + "1. Fight Deuputy Sheriff");
				
				monster.setMonsterID("M12");
				
				healthMeter(healthBar, 100, -10 , prompt);
				
				System.out.println("Fighting HenchMan : Location Game Control"); // Testing purpose	
			}
		    
		    // Encountering Sheriff
		    
			/*else if(puzzle.getPuzzleID().contains("P01") && monster.getMonsterID().contains("M12") && puzzle.getPuzzleLock() && command.equals("1") || command.equalsIgnoreCase("fight deputy sheriff")) {
				
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				
				puzzle.setPuzzleID("P01"); // set puzzle ID to P01
				puzzle.setPuzzleLock(true);
				
				Jail.setRoomLocks(true);
				Jail.setRoomID("1E");
				
				System.out.println("Fighting Deputy Sheriff : Location Game Control"); // Testing purpose	
			} */
		    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@		    
		    
//-------------------------------------------------------------------------------------------------------------------------------    
		    //SALOON > Search Room > Back
			else if(Saloon.getRoomID().equals("1D") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
				
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
			else if((Saloon.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
				//JAIL > Locked Door/Unlocked Door
				if((Saloon.getRoomLocks() && testPuzzle && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
					
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
				else if((Saloon.getRoomLocks() && unlockDoor && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
					room.display(doorFile[30] + "\n\n" + doorFile[31], "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					Saloon.setRoomLocks(false);
					Saloon.setRoomExits("D08");
					Jail.setRoomExits("");	
										
					System.out.println("Console Message #2 : Requesting to enter Jail room : Location Game Interface"); // Testing purpose
				}
			}

		    //JAIL > Locked Door
			else if((Saloon.getRoomExits().contains("D08") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y") || command.equalsIgnoreCase("unlock door")))) {
				
				if(Saloon.getRoomExits().contains("D08") && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("unlock door"))) {
					
					room.display(doorFile[28] + "\n\n" + doorFile[29], "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
					Saloon.setRoomExits("D08");
					commandMenu.prompt(prompt, "CANNOT OPEN DOOR");		
					
					System.out.println("Attempting to enter locked Jail : Location Game Interface"); // Testing purpose

				}
				else if(Saloon.getRoomExits().contains("D08") && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")) ||
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
			//Inventory
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
			
		    //Back
			else if(backToGame && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
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
			if(unlockSaveGame && ((TownHub.getRoomLocks() && (command.equalsIgnoreCase("6") || command.equalsIgnoreCase("save game"))) ||
					(DrugStore.getRoomLocks() && (command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) ||
					(Inn.getRoomLocks() && (command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) ||
					(Saloon.getRoomLocks() && (command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) ||
					(Jail.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("save game"))))) {
				
				commandMenu.prompt(prompt, "GAME SAVED");
				commandMenu.saveGame("testGame.ini");
			}
		}
		
		if(MainDesertHub.getRoomLevel().equals("Town Access")) {
			//MAIN DESERT HUB-----------------------------------------------------------------------------------------------------------------------
			//MAIN DESERT HUB > Path Way
			if((TownHub.getRoomLocks() && (command.equals("4") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("north"))) ||
					(AccessPath1.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("west")))) {
				if(TownHub.getRoomLocks() && (command.equals("4") ||  command.equalsIgnoreCase("access town") || command.equalsIgnoreCase("north"))) {
					room.display("Town Hub > Path Way\n\nA path that leads out of town and into the wilderness.\n\nDo you want to enter Town Access?. ", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					TownHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D10");
				}
				else if(AccessPath1.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town access") || command.equalsIgnoreCase("west"))) {
					room.display("Access Path 1 > Path Way\n\nThe path continues East to Fort Birman.\n\nDo you want to enter the Main Desert Hub?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					AccessPath1.setRoomLocks(false);
					AccessPath1.setRoomExits("D13");
				}
			}
			else if(MainDesertHub.getRoomExits().equals("D10") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")) ||
					(MainDesertHub.getRoomExits().equals("D11") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(MainDesertHub.getRoomExits().equals("D12") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
					(MainDesertHub.getRoomID().contains("2A") && (command.equals("0") || command.equalsIgnoreCase("runaway") || command.equalsIgnoreCase("back"))) ||
					(AccessPath1.getRoomExits().equals("D13") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
				room.MainDesertHub_2A();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				MainDesertHub.setRoomLocks(true);
				AccessPath1.setRoomLocks(false);
				
				DrugStore.setRoomExits("");
				Inn.setRoomExits("");
				Saloon.setRoomExits("");
				MainDesertHub.setRoomExits("");
				
				DrugStore.setRoomID("");
				Saloon.setRoomID("");
				Inn.setRoomID("");
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
					room.display("Main Desert Hub > Search Area\n\n", "Action\n" + "0. Back\n");
				}
			}
			
			//ACCESS PATH 1-----------------------------------------------------------------------------------------------------------------------
			//ACCESS PATH 1 > Path Way
			else if(MainDesertHub.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("access path 1") || command.equalsIgnoreCase("east"))) {
				if(MainDesertHub.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("access path 1") || command.equalsIgnoreCase("east"))) {
					room.display("Main Desert Hub > Path Way\n\nThe path continues East to Fort Birman.\n\nDo you want to enter the Access Path 1?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
					MainDesertHub.setRoomLocks(false);
					MainDesertHub.setRoomExits("D12");
				}
			}
			else if((MainDesertHub.getRoomExits().equals("D12") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
					(AccessPath1.getRoomExits().equals("D13") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
				room.AccessPath1_2B();
				MainDesertHub.setRoomLocks(false);
				AccessPath1.setRoomLocks(true);
				
				MainDesertHub.setRoomExits("");
				AccessPath1.setRoomExits("");
			}
		}
		//Exit Game
		if(command.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
	});
	}	
}
