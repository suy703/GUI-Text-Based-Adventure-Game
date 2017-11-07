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
	
	Boolean lockMainMenu = true;
	Boolean loadGame = true;
	Boolean unlockSaveGame = false;
	Boolean backToGame = false;

	Player player;
	
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms TownHub = new Rooms(false, "1A", "Town Hub", "D00, D02, D03, D05", "");
		Rooms DrugStore = new Rooms(false, "1B", "Drug Store", "D01, D00", "I1, I2, I4");
		Rooms Inn = new Rooms(false, "1C", "Inn", "D01, D02", "I3");
		Rooms Saloon = new Rooms(false, "1D", "Saloon", "D03, D04", "");
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,600);
		VBox topPane = new VBox();
		Pane centerPane = new Pane();
		//StackPane rightPane = new StackPane();
		HBox bottomPane = new HBox();
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
		
		//BOTTOM PANE------------------------------------------------------------------------------------
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
			}	
			//MAIN MENU
			//TownHub
		    if(lockMainMenu && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("new game")) || 
		    		(DrugStore.getRoomLocks() && (command.equalsIgnoreCase("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Inn.getRoomLocks() && (command.equalsIgnoreCase("2") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("east"))) ||
		    		(Saloon.getRoomLocks() && (command.equalsIgnoreCase("1") ||  command.equalsIgnoreCase("town hub") || command.equalsIgnoreCase("west")))){ //1. Start New Game
				
		    	if(command.equalsIgnoreCase("new game")) {
					player = new Player();
					System.out.println("Player Created");
					player.inventory.add(new Weapon("0001", "test item", "ignore description" ,10, 10)); //Testing Inventory
				}
				lockMainMenu = false;
				unlockSaveGame = true;
				room.TownHub_1A();
				TownHub.setRoomLocks(true);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
		    }
		    //Load Game
		    else if(lockMainMenu && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("load game"))) { //2. Load Game
				
		    	commandMenu.setLoadGameStory(displayStory, "Load Game");
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Back");
				commandMenu.loadGame("testGame.ini");
				loadGame = false;
			}
		    //Back
			else if(lockMainMenu && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("back"))) { //1. Back
				
				commandMenu.setLoadGameStory(displayStory, "Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is "
						+ "fed up with his cows getting rustled by a gang known as the Long Riders. He hears the "
						+ "sheriff won’t be much help, so he takes matters into his own hands. Heck starts his "
						+ "adventure in center of Bombay Hill, one of three towns controlled by the Long Riders, "
						+ "his plan is to find the leader of the gang and taking them out.");
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. New Game\n" + "2. Load Game\n" + "3. Credit");
				loadGame = true;
			}
		    //DrugStore
			else if((TownHub.getRoomLocks() && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north west"))) ||
					(Inn.getRoomLocks() && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("drug store") || command.equalsIgnoreCase("north")))) {
				
				room.DrugStore_1B();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(true);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(false);
			}
			else if((command.equalsIgnoreCase("3") || command.equalsIgnoreCase("search room")) && DrugStore.getRoomLocks()) {
				
				Potion healthPotion = new Potion("i1", "Health Potion (HP)\n", "A bottle shaped like a human heart with a red liquid inside.", 2, "Potion", 3);
				Potion attackPotion = new Potion("i2", "Attack Potion (AP)\n", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.", 3, "Potion", 2);
				Weapon pistol = new Weapon("i7", "Pistol (P)\n", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!", 5, 5);
				commandMenu.setLoadGameStory(displayStory, "Drug Store\n\n" + healthPotion.getName() + attackPotion.getName() + pistol.getName() + "\n -> Drug Store (North West)");
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Examine Item\n" + "2. Inventory\n" + "3. Save Game");
			}
		    //Inn
			else if((DrugStore.getRoomLocks() && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south"))) || 
					(TownHub.getRoomLocks() && (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("inn") || command.equalsIgnoreCase("south west")))) {
				
				room.Inn_1C();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(true);
				Saloon.setRoomLocks(false);
			}
		    //Saloon
			else if((TownHub.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("saloon") || command.equalsIgnoreCase("east")))) {
				
				room.Saloon_1D();
				TownHub.setRoomLocks(false);
				DrugStore.setRoomLocks(false);
				Inn.setRoomLocks(false);
				Saloon.setRoomLocks(true);
			}
			//Inventory
			else if(TownHub.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
					DrugStore.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
					Inn.getRoomLocks() && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("inventory")) ||
					Saloon.getRoomLocks() && (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("inventory"))) {
				//ERROR ATM!!! - Initialize player class
				commandMenu.setLoadGameStory(displayStory, player.displayInventory());
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Select Item (Enter Item Number)\n" + "1. Back");
				
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
			}
		    //Back
			else if(backToGame && (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("back"))) { //1. Back
				
				//CHANGE backToGame INTO A INVENTORY BOOLEAN PROPERTY
				//TEST PURPOSE
				if(TownHub.getRoomLocks()==true) {
					
					room.TownHub_1A();
					TownHub.setRoomLocks(true);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					System.out.println("TownHub");
				}
				else if(DrugStore.getRoomLocks()==true) {
					
					room.DrugStore_1B();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(true);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(false);
					System.out.println("DrugStore");
				}
				else if(Inn.getRoomLocks()==true) {
					
					room.Inn_1C();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(true);
					Saloon.setRoomLocks(false);
					System.out.println("Inn");
				}
				else if(Saloon.getRoomLocks()==true) {
					
					room.Saloon_1D();
					TownHub.setRoomLocks(false);
					DrugStore.setRoomLocks(false);
					Inn.setRoomLocks(false);
					Saloon.setRoomLocks(true);
					System.out.println("Saloon");
				}
			}
		    //Save Game
			else if(unlockSaveGame && (command.equalsIgnoreCase("4") || command.equalsIgnoreCase("5") || command.equalsIgnoreCase("save game"))) {
				
				commandMenu.saveGame("testGame.ini");
			}
		    
			//Exit Game
			if(command.equalsIgnoreCase("exit")) {
				
				System.exit(0);
			}
		});
        /*
		//TESTING PURPOSE
		borderPane.setOnMouseMoved(e -> {
			
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
		});
		*/
		borderPane.setBackground(background);
		centerPane.getChildren().addAll(displayStory, displayCommand, viewMap, viewIcon);
		bottomPane.getChildren().add(inputCommand);
		mainMenu = new Scene(borderPane);
		
		System.out.println("Game interface initialized"); // Testing purpose
	}
}
