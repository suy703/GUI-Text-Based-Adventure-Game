import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameInterface {
	//GAME BACKGROUND----------------------------------------------------------------------------------
	private final Image gameBackground = new Image("file:images/background.jpg");
	private final BackgroundImage backgroundImage = new BackgroundImage(gameBackground, null, null, null, null);
	private final Background background = new Background(backgroundImage);
	
	Scene mainMenu;
	
	Image icon = new Image("file:images/icon.jpg");
	Image map = new Image("file:images/BombayHill.png"); 
	ImageView viewIcon = new ImageView();
	ImageView viewMap = new ImageView();
	
	TextArea displayStory = new TextArea("Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is "
			+ "fed up with his cows getting rustled by a gang known as the Long Riders. He hears the "
			+ "sheriff won�t be much help, so he takes matters into his own hands. Heck starts his "
			+ "adventure in center of Bombay Hill, one of three towns controlled by the Long Riders, "
			+ "his plan is to find the leader of the gang and taking them out.");
	TextArea displayCommand = new TextArea("Action\n" + "1. New Game\n" + "2. Load Game\n" 
			 + "3. Credit");
	
	Boolean lockMainMenu = true; //This will change to false when the game is started
	Boolean lockLoadGame = true; //This will change to false when the game is started
	Boolean unlockSaveGame = false; //This will change to true when the game is started
	Boolean backToGame = false;
	Boolean newGame = true; //This will change to false when the game is started
	Boolean inventoryView = false;
	boolean storeView = false;
	boolean itemView = false;
	int itemIndex;


	Boolean testPuzzle = true;
	Boolean unlockDoor = false;
	
	Player player;
	Potion healthPotion = new Potion("i1", "Health Potion", "A bottle shaped like a human heart with a red liquid inside.\n", 2, "H", 3);
	Potion attackPotion = new Potion("i2", "Attack Potion", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.\n", 3, "A", 2);
	Weapon pistol = new Weapon("i7", "Pistol", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!\n", 5, 5);
	Items[] drugstore1 = {healthPotion,attackPotion, pistol};
	Store store1 = new Store(drugstore1);
	
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms TownHub = new Rooms("Bombay Hill", false, "1A", "Town Hub", "", "");
		Rooms DrugStore = new Rooms("Bombay Hill", false, "1B", "Drug Store", "", "I1, I2, I4");
		Rooms Inn = new Rooms("Bombay Hill", false, "1C", "Inn", "", "I3");
		Rooms Saloon = new Rooms("Bombay Hill", false, "1D", "Saloon", "", "");
		Rooms Jail = new Rooms("Bombay Hill", false, "1E", "Jail", "", "");
	
		Puzzles puzzle = new Puzzles(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,600);
		VBox topPane = new VBox();
		Pane centerPane = new Pane();
		//StackPane rightPane = new StackPane();
		HBox bottomPane = new HBox();
		bottomPane.setStyle("-fx-background-color: #000000;");
		bottomPane.setPadding(new Insets(10, 10, 10, 10));
		bottomPane.setSpacing(10);
		borderPane.setTop(topPane);
		borderPane.setCenter(centerPane);
		borderPane.setBottom(bottomPane);
		
		//CENTER PANE--------------------------------------------------------------------------------------
		//DISPLAY STORY
		commandMenu.setDisplayStory(displayStory);
		displayStory = commandMenu.getDisplayStory();
		//COMMAND MENU
		commandMenu.setDisplayCommand(displayCommand);
		displayCommand = commandMenu.getDisplayCommand();
		//DISPLAY MAP
		
		//BOTTOM PANE-------------------------------------------------------------------------------------
		Text prompt = new Text(); //Error message
		//EVENTHANDLER COMAMND
        TextField inputCommand = new TextField();
        inputCommand.setPromptText("Enter your command.");
        inputCommand.setPrefWidth(280);
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
					TownHub.setRoomExits("D00");
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
							+ "He hears the sheriff won�t be much help, so he takes matters into his own hands. Heck starts his adventure in center of Bombay Hill, one of "
							+ "three towns controlled by the Long Riders, his plan is to find the leader of the gang and taking them out.", "Action\n" + "1. New Game\n" 
							+ "2. Load Game\n" + "3. Credit");
					lockLoadGame = true;
				}
			    //TOWN HUB---------------------------------------------------------------------------------------------------------------------------
				else if((DrugStore.getRoomLocks() && !storeView &&(command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
			    		(Inn.getRoomLocks() && (command.equals("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
			    		(Saloon.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west")))) {
					//TOWN HUB > Door
					if(DrugStore.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) {
						room.display("Drug Store > Door\n\nA wooden door to the Drug Store that people are going in and out of periodically. There doesn�t seem to be anything standing in your way "
						+ "if you wanted to go through.\n\nDo you want to enter the Town Hub?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						DrugStore.setRoomLocks(false);
						DrugStore.setRoomExits("D01");
					}
					else if(Inn.getRoomLocks() && (command.equals("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) {
						room.display("Inn > Door\n\nThe door looks well worn and like it�s in need of repairs.\n\n" 
						+ "Do you want to enter the Town Hub?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						Inn.setRoomLocks(false);
						Inn.setRoomExits("D04");
					}
					else if(Saloon.getRoomLocks() && (command.equals("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west"))) {
						room.display("Saloon > Door\n\nA pair of saloon doors that are swinging slightly in the wind. You can vaguely make out the "
						+ "shapes of people inside over the doors.\n\nDo you want to enter the Saloon?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						Saloon.setRoomLocks(false);
						Saloon.setRoomExits("D07");
					}
				}
				else if(TownHub.getRoomExits().contains("D00") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")) ||
						(DrugStore.getRoomExits().contains("D01") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
						(TownHub.getRoomExits().contains("D05") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
						(Inn.getRoomExits().contains("D04") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) ||
						(TownHub.getRoomExits().contains("D06") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n"))) ||
						(Saloon.getRoomExits().contains("D07") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")))) {
					
					room.TownHub_1A();
					TownHub.setRoomLocks(true);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					DrugStore.setRoomExits("");
					Inn.setRoomExits("");
					Saloon.setRoomExits("");
				}
			    //DRUG STORE-------------------------------------------------------------------------------------------------------------------------
				else if((TownHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) ||
						(Inn.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north")))) {
					//DRUG STORE > Door
					if(TownHub.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) {
						room.display("Town Hub > Door\n\nA wooden door to the Drug Store that people are going in and out of periodically. There doesn�t seem to be anything standing in your way "
						+ "if you wanted to go through.\n\nDo you want to enter the Drug Store?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						TownHub.setRoomLocks(false);
						TownHub.setRoomExits("D00");
					}
					else if(Inn.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north"))) {
						room.display("Inn > Door\n\nA narrow passageway leading to a small, dimly lit room.\n\nDo you want to enter the Drug Store?", "Action\n" 
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
				}
			    //DRUG STORE > Shop
				else if(DrugStore.getRoomLocks() && !storeView &&(command.equalsIgnoreCase("3") || command.equalsIgnoreCase("shop"))) {
					storeView = true;
					room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + 
							" gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
				
				}
			    //Purchase Item -Health Potion
				else if(DrugStore.getRoomLocks() && !inventoryView && storeView && (command.equals("1") || command.equalsIgnoreCase("Health Potion"))){
					player.buyItem(store1.storeInventory[0],commandMenu,prompt,store1);
					room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + 
							" gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
					
				} 
			    //Purchase Item -Attack Potion
				else if(DrugStore.getRoomLocks() &&!inventoryView && storeView && (command.equals("2") || command.equalsIgnoreCase("Attack Potion"))){
					player.buyItem(store1.storeInventory[1],commandMenu,prompt,store1);
					room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + 
							" gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
					
				}
			    //Purchase Item -Pistol
				else if(DrugStore.getRoomLocks() &&!inventoryView && storeView && (command.equals("3") || command.equalsIgnoreCase("Pistol"))){
					player.buyItem(store1.storeInventory[2],commandMenu,prompt,store1);
					room.display("Drug Store > Shop\n\n" + store1.displayStore() + "\nYou have " + player.gold + " gold", "Action\n" + "0. Back\n" + store1.displayPurchaseCommands());
					
				}
			    //Sell Item Menu
				else if(DrugStore.getRoomLocks() && !inventoryView &&storeView && (command.equals("4") || command.equalsIgnoreCase("Sell"))){
					inventoryView = true;
					room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					
				}
			    //Sell Item 1
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("1"))) {
					if(player.inventory.size()>0) {
						player.sellItem(player.inventory.get(0),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						System.out.println(player.inventory.size()+ "S");
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}
				}
			  //Sell Item 2
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("2"))) {
					if(player.inventory.size()>1) {
						player.sellItem(player.inventory.get(1),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}
				}
			  //Sell Item 3
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("3"))) {
					if(player.inventory.size()>2) {
						player.sellItem(player.inventory.get(2),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
			  //Sell Item 4
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("4"))) {
					if(player.inventory.size()>3) {
						player.sellItem(player.inventory.get(3),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}
				}
			  //Sell Item 5
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("5"))) {
					if(player.inventory.size()>4) {
						player.sellItem(player.inventory.get(4),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
			  //Sell Item 6
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("6"))) {
					if(player.inventory.size()>5) {
						player.sellItem(player.inventory.get(5),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
			  //Sell Item 7
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("7"))) {
					if(player.inventory.size()>6) {
						player.sellItem(player.inventory.get(6),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
			  //Sell Item 8
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("8"))) {
					if(player.inventory.size()>7) {
						player.sellItem(player.inventory.get(7),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
			  //Sell Item 9
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("9"))) {
					if(player.inventory.size()>8) {
						player.sellItem(player.inventory.get(8),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
			  //Sell Item 10
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("10"))) {
					if(player.inventory.size()>9) {
						player.sellItem(player.inventory.get(2),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}
				else if(DrugStore.getRoomLocks() && inventoryView &&storeView && (command.equals("11"))) {
					if(player.inventory.size()>10) {
						player.sellItem(player.inventory.get(10),commandMenu,prompt,store1);
						room.display(player.displayInventory() + "\nYou have " + player.gold + " gold", player.sellItemCommands());
					}else {
						commandMenu.prompt(prompt, "INVALID COMMAND");
					}	
				}

			    //DRUG STORE > Shop > Back
				else if(DrugStore.getRoomLocks() && (command.equals("0") || command.equalsIgnoreCase("back"))) {
					
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
				}
			    //INN--------------------------------------------------------------------------------------------------------------------------------
				else if((DrugStore.getRoomLocks() && (command.equals("2") &&!storeView || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) || 
						(TownHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west")))) {
					//INN > Door
					if(DrugStore.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) {
						room.display("Drug Store > Door\n\nA narrow passageway leading to a small, dimly lit room.\n\nDo you want to enter the Inn?", "Action\n" 
						+ "0. No (N)\n" + "1. Yes (Y)");
						DrugStore.setRoomLocks(false);
						DrugStore.setRoomExits("D02");
					}
					else if(TownHub.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west"))) {
						room.display("Town Hub > Door\n\nA sign above the door reads �Inn�. The door looks well worn and like it�s in need of repairs.\n\n" 
						+ "Do you want to enter the Inn?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
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
				}
			    //INN > Search Room
				else if(Inn.getRoomLocks() && Inn.getRoomID().equals("1C") && (command.equals("3") || command.equalsIgnoreCase("search room"))) {
					
					Items gold = new Items("i3", "Gold (1)", "A shiney gold coin with a creepy looking face in the center.", 1);
					room.display("Drug Store > Search Room\n\n" + gold.getName() + "---------------------------------------------\n" + gold.getDescription(), "Action\n" 
					+ "0. Back\n" + "1. Pickup Item\n" + "2. Inventory\n" + "3. Save Game\n");
				}
			    //INN > Search Room > Back
				else if(Inn.getRoomLocks() && Inn.getRoomID().equals("1C") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
					
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
				}
			    //SALOON-----------------------------------------------------------------------------------------------------------------------------
				else if((TownHub.getRoomLocks() && (command.equals("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) ||
						(Jail.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north")))) {
					//SALOON > Door
					if(TownHub.getRoomLocks() && (command.equals("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) {
						room.display("Town Hub > Door\n\nA pair of saloon doors that are swinging slightly in the wind. You can vaguely make out the "
						+ "shapes of people inside over the doors.\n\nDo you want to enter the Saloon?", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						TownHub.setRoomLocks(false);
						TownHub.setRoomExits("D06");
					}
					//SALOON > Locked Door/Unlocked Door
					else if(Jail.getRoomLocks() && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
						
						if(Jail.getRoomLocks() && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north"))) {
							
							room.display("Jail > Locked Door\n\nA very well guarded door that leads into the Deputy Sheriff�s office. There must be "
							+ "some way to get in there...", "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
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
							
							room.display("Jail > Unlocked Door\n\nA very well guarded door that leads into the Deputy Sheriff�s office. There must be "
							+ "some way to get in there...", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
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
					}
					else if(Jail.getRoomExits().contains("D09") && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("unlock door"))) {
						
						room.display("Jail > Locked Door\n\nA very well guarded door that leads into the Deputy Sheriff�s office. There must be "
						+ "some way to get in there...", "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
						Jail.setRoomExits("D09");
						commandMenu.prompt(prompt, "CANNOT OPEN DOOR");
						
					}
					else if(Jail.getRoomExits().contains("D09") && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y"))) {
						
						room.Saloon_1D();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(false);
						
						TownHub.setRoomExits("");
					}
				}
			    //SALOON > Search Room
				else if(Saloon.getRoomLocks() && Saloon.getRoomID().equals("1D") && (command.equals("3") || command.equalsIgnoreCase("search room"))) {
					
					//ENCOUNTER HENCHMAN THEN FIGHT BOSS
					//Call Monster class
					room.display("Drug Store > Search Room\n\nHenchman", "Action\n" + "0. Back\n" + "1. Fight Henchman\n" + "2. Inventory\n" + "3. Save Game\n");
				}
			    //SALOON > Search Room > Back
				else if(Saloon.getRoomLocks() && Saloon.getRoomID().equals("1D") && (command.equals("0") || command.equalsIgnoreCase("back"))) {
					
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					Jail.setRoomLocks(false);
					
					TownHub.setRoomExits("");
					DrugStore.setRoomExits("");
				}
			    //JAIL-------------------------------------------------------------------------------------------------------------------------------
				else if((Saloon.getRoomLocks() && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
					//JAIL > Locked Door/Unlocked Door
					if((Saloon.getRoomLocks() && testPuzzle && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
						
						room.display("Saloon > Locked Door\n\nA very well guarded door that leads into the Deputy Sheriff�s office. There must be "
						+ "some way to get in there...", "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
						Saloon.setRoomLocks(false);
						Saloon.setRoomExits("D08");
						//SOLVE & UNLOCK PUZZLE
						//If FALSE, cannot access jail and throws error message
						//unlockDoor = true; //Testing Purpose
						if(unlockDoor == true) { 
							unlockDoor = true;
							testPuzzle = false;
						}
					}
					else if((Saloon.getRoomLocks() && unlockDoor && (command.equals("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
						room.display("Saloon > Unlocked Door\n\nA very well guarded door that leads into the Deputy Sheriff�s office. There must be "
						+ "some way to get in there...", "Action\n" + "0. No (N)\n" + "1. Yes (Y)");
						Saloon.setRoomLocks(false);
						Saloon.setRoomExits("D08");
						Jail.setRoomExits("");
					}
				}
				else if((Saloon.getRoomExits().contains("D08") && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y") || command.equalsIgnoreCase("unlock door")))) {
					
					if(Saloon.getRoomExits().contains("D08") && testPuzzle && (command.equals("1") || command.equalsIgnoreCase("unlock door"))) {
						
						room.display("Saloon > Locked Door\n\nA very well guarded door that leads into the Deputy Sheriff�s office. There must be "
						+ "some way to get in there...", "Action\n" + "0. Leave Door\n" + "1. Unlock Door");
						Saloon.setRoomExits("D08");
						commandMenu.prompt(prompt, "CANNOT OPEN DOOR");
					}
					else if(Saloon.getRoomExits().contains("D08") && unlockDoor && (command.equals("1") || command.equalsIgnoreCase("yes") || command.equalsIgnoreCase("y")) ||
							(Jail.getRoomExits().contains("D09") && (command.equals("0") || command.equalsIgnoreCase("no") || command.equalsIgnoreCase("n")))) {
						
						room.Jail_1E();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(true);
						//testPuzzle = true; //Testing Purpose
						//unlockDoor = false; //Testing Purpose
					}
				}
				//Inventory
				if(!storeView && (TownHub.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
						DrugStore.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
						Inn.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
						Saloon.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("inventory")))) {
					
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
						System.out.println("TownHub");
					}
					else if(DrugStore.getRoomLocks()==true) {
						
						room.DrugStore_1B();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(true);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						System.out.println("DrugStore");
					}
					else if(Inn.getRoomLocks()==true) {
						
						room.Inn_1C();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(true);
						Saloon.setRoomLocks(false);
						Jail.setRoomLocks(false);
						System.out.println("Inn");
					}
					else if(Saloon.getRoomLocks()==true) {
						
						room.Saloon_1D();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(false);
						System.out.println("Saloon");
					}
					else if(Jail.getRoomLocks()==true) {
						
						room.Jail_1E();
						TownHub.setRoomLocks(false);
						DrugStore.setRoomLocks(false);
						Inn.setRoomLocks(false);
						Saloon.setRoomLocks(true);
						Jail.setRoomLocks(true);
						System.out.println("Jail");
					}
				}
				/*
			    //Save Game
				if(unlockSaveGame && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("4") || command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) {
					
					commandMenu.prompt(prompt, "GAME SAVED");
					commandMenu.saveGame("testGame.ini");
					System.out.println("Save Game"); //Testing Purpose
				}
				*/
			}
			//Exit Game
			if(command.equalsIgnoreCase("exit")) {
				
				System.exit(0);
			}
		});
		borderPane.setBackground(background);
		centerPane.getChildren().addAll(displayStory, displayCommand, viewMap, viewIcon);
		bottomPane.getChildren().addAll(inputCommand, prompt);
		mainMenu = new Scene(borderPane);
		
		System.out.println("Game interface initialized"); // Testing purpose
	}
}
