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
			+ "sheriff won’t be much help, so he takes matters into his own hands. Heck starts his "
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

	Player player;
	
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms TownHub = new Rooms(false, "1A", "Town Hub", "D00, D02, D03, D05", "");
		Rooms DrugStore = new Rooms(false, "1B", "Drug Store", "D01, D00", "I1, I2, I4");
		Rooms Inn = new Rooms(false, "1C", "Inn", "D01, D02", "I3");
		Rooms Saloon = new Rooms(false, "1D", "Saloon", "D03, D04", "");
		Rooms Jail = new Rooms(false, "1E", "Jail", "D04", "");
		
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
			//MAIN MENU
			//TownHub
		    if(lockMainMenu && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("new game")) || 
		    		(DrugStore.getRoomLocks() && (command.equalsIgnoreCase("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Inn.getRoomLocks() && (command.equalsIgnoreCase("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Saloon.getRoomLocks() && (command.equalsIgnoreCase("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west")))){ //1. Start New Game
		    	
		    	if(command.equalsIgnoreCase("new game")|| (command.equalsIgnoreCase("1") && newGame)) {
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
				
				System.out.println("Town Hub called : Location Game Interface"); // Testing purpose

		    }
		    //Load Game
		    else if(lockMainMenu && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("load game"))) {
				
		    	room.display("Load Game", "Action\n" + "0. Back");
				commandMenu.loadGame("testGame.ini");
				lockLoadGame = false;
			}
		    //Back
			else if(lockMainMenu && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
				
				room.display("Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is fed up with his cows getting rustled by a gang known as the Long Riders. "
						+ "He hears the sheriff won’t be much help, so he takes matters into his own hands. Heck starts his adventure in center of Bombay Hill, one of "
						+ "three towns controlled by the Long Riders, his plan is to find the leader of the gang and taking them out.", "Action\n" + "1. New Game\n" 
						+ "2. Load Game\n" + "3. Credit");
				lockLoadGame = true;
			}
		    //DrugStore
			else if((TownHub.getRoomLocks() && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) ||
					(Inn.getRoomLocks() && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north")))) {
				
				room.DrugStore_1B();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(true);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				
				System.out.println("Drug Store Called : Location Game Interface"); // Testing purpose

			}
			else if(DrugStore.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("search room"))) {
				
				Potion healthPotion = new Potion("i1", "Health Potion (HP)", "A bottle shaped like a human heart with a red liquid inside.\n", 2, "H", 3);
				Potion attackPotion = new Potion("i2", "Attack Potion (AP)", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.\n", 3, "A", 2);
				Weapon pistol = new Weapon("i7", "Pistol (P)", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!\n", 5, 5);
				room.display("Drug Store > Search Room\n\n" + healthPotion.getName() + "----------------------------------\n" + healthPotion.getDescription() 
				+ attackPotion.getName() + "----------------------------------\n" + attackPotion.getDescription() + pistol.getName() 
				+ "---------------------------------------------\n" + pistol.getDescription(), "Action\n" + "0. Back\n" + "1. Pickup Item\n" + "2. Inventory\n" 
				+ "3. Save Game\n");
			}
			else if(DrugStore.getRoomLocks() && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
				
				room.DrugStore_1B();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(true);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
			}
		    //Inn
			else if((DrugStore.getRoomLocks() && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) || 
					(TownHub.getRoomLocks() && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west")))) {
				
				room.Inn_1C();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(true);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
				
			}
			else if(Inn.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("search room"))) {
				
				Items gold = new Items("i3", "Gold (1)", "A shiney gold coin with a creepy looking face in the center.", 1);
				room.display("Drug Store > Search Room\n\n" + gold.getName() + "---------------------------------------------\n" + gold.getDescription(), "Action\n" 
				+ "0. Back\n" + "1. Pickup Item\n" + "2. Inventory\n" + "3. Save Game\n");
				
			}
			else if(Inn.getRoomLocks() && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
				
				room.Inn_1C();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(true);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(false);
			}
		    
// ----------------------------------------------------------------------------------------------------------------------------------------------------		    
		    //Saloon
		    
		    // If player is inside the saloon
			else if ((TownHub.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east"))) ||
					(Jail.getRoomLocks() && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("north")))) {
				
				room.Saloon_1D();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(true);
				Jail.setRoomLocks(false);
				
				System.out.println("Saloon called : Location Game Interface"); // Testing purpose
			} 
			
			// if player is inside the saloon AND wants to go to jail, the puzzle will be prompt
			else if(Saloon.getRoomLocks() && command.equalsIgnoreCase("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")) {
				puzzle.puzzleP01();
				System.out.println("Jail is locked by a puzzle"); // testing purpose
				
		    } 
			
			// if player is inside saloon and puzzle is prompt but hits the "back" command
			else if(Saloon.getRoomLocks() && puzzle.getPuzzleLock() && command.equalsIgnoreCase("3") || command.equalsIgnoreCase("back")) {
				
		    	room.Saloon_1D();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(true);
				Jail.setRoomLocks(false);
				
				System.out.println("Back out in the Saloon from the puzzle"); // Testing purpose
		    }
			
			// if player is in the saloon and puzzle is prompt but hits the "hint" command
            else if(Saloon.getRoomLocks() && puzzle.getPuzzleLock() && command.equalsIgnoreCase("hint") || command.equalsIgnoreCase("2")) {

               	room.Saloon_1D();
            	TownHub.setRoomLocks(false);
            	DrugStore.setRoomLocks(false);
            	Inn.setRoomLocks(false);
            	Saloon.setRoomLocks(true);
            	Jail.setRoomLocks(false);
            	
				puzzle.setPuzzleID("P01");
				puzzle.display(puzzle.getPuzzleHint(), "Action\n" + "1. Hint\n" + "2. Description\n" + "3. Back");
				System.out.println("Back out in the Saloon from the puzzle"); // Testing purpose
		    }
		    	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
		    //Jail
			else if((Saloon.getRoomLocks() && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("jail") || command.equalsIgnoreCase("south")))) {
				
				room.Jail_1E();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
				Jail.setRoomLocks(true);
				System.out.println("Jail Room called : Location GameInterface"); // Testing purpose

			}

			//Inventory
			else if(TownHub.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
					DrugStore.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
					Inn.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
					Saloon.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("inventory"))) {
				
				room.display(player.displayInventory(), "Action\n" + "Select Item (Enter Item Number)\n" + "0. Back");
				
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
		    //Back
			else if(backToGame && (command.equalsIgnoreCase("0") || command.equalsIgnoreCase("back"))) {
				
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
		    //Save Game
			else if(unlockSaveGame && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("4") || command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) {
				
				commandMenu.prompt(prompt, "GAME SAVED");
				commandMenu.saveGame("testGame.ini");
				System.out.println("Save Game"); //Testing Purpose
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
