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
	TextArea displayCommand = new TextArea("Action\n" +"-> Start New Game (Start)\n" + "-> Load Game (Load)\n" 
			 + "-> Credit (C)");
	
	Boolean lockMainMenu = true;
	Boolean unlockSaveGame = false;
	
	Boolean townHub = true;
	Boolean drugStore = true;
	Boolean inn = true;
	Boolean saloon = true;
	Player player;
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		
		
		
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
				//command = "start";
			}
			//MAIN MENU
			//TownHub
			if((command.equalsIgnoreCase("start") && lockMainMenu) || command.equalsIgnoreCase("east") && townHub || command.equalsIgnoreCase("west")) {
				room.TownHub_1A();
				if(command.equalsIgnoreCase("start")) {
					player = new Player();
					System.out.println("Player Created");
					player.inventory.add(new Weapon("0001", "test item", "ignore description" ,10, 10)); //Testing Inventory
				}
				lockMainMenu = false;
				unlockSaveGame = true;
				townHub = false;
			}
			//Load Game
			else if(command.equalsIgnoreCase("load") && lockMainMenu) {
				commandMenu.setLoadGameStory(displayStory, "Load Game");
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "-> Back (Back)");
				commandMenu.loadGame("testGame.ini");
			}
			//Back
			else if(command.equalsIgnoreCase("back") && lockMainMenu) {
				commandMenu.setLoadGameStory(displayStory, "Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is "
						+ "fed up with his cows getting rustled by a gang known as the Long Riders. He hears the "
						+ "sheriff won’t be much help, so he takes matters into his own hands. Heck starts his "
						+ "adventure in center of Bombay Hill, one of three towns controlled by the Long Riders, "
						+ "his plan is to find the leader of the gang and taking them out.");
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "-> Start New Game (Start)\n" + "-> Load Game (Load)\n" + "-> Credit (Credit)");
			}
			//DrugStore
			else if((command.equalsIgnoreCase("north west") && drugStore) || command.equalsIgnoreCase("north")) {
				room.DrugStore_1B();
				townHub = true;
			}
			else if(command.equalsIgnoreCase("sr") && drugStore) {
				Potion healthPotion = new Potion("i1", "Health Potion (HP)\n", "A bottle shaped like a human heart with a red liquid inside.", 2, "Potion", 3);
				Potion attackPotion = new Potion("i2", "Attack Potion (AP)\n", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.", 3, "Potion", 2);
				Weapon pistol = new Weapon("i7", "Pistol (P)\n", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!", 5, 5);
				commandMenu.setLoadGameStory(displayStory, "Drug Store\n\n" + healthPotion.getName() + attackPotion.getName() + pistol.getName() + "\n -> Drug Store (North West)");
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "-> Examine Item (EI)\n" 
						+ "-> Inventory (I)\n" + "-> Save Game (SG)");
			}
			//Inn
			else if((command.equalsIgnoreCase("south") && inn) || command.equalsIgnoreCase("south west")) {
				room.Inn_1C();
				townHub = true;
			}
			//Saloon
			else if(command.equalsIgnoreCase("east") && saloon) {
				room.Saloon_1D();
			}
			
			//Save Game
			if(command.equalsIgnoreCase("sg") && unlockSaveGame) {
				commandMenu.saveGame("testGame.ini");
			}
			//Exit Game
			if(command.equalsIgnoreCase("exit")) {
				System.exit(0);
			}
			
			//Inventory
			if(command.equalsIgnoreCase("i")) {
				commandMenu.setLoadGameStory(displayStory, player.displayInventory());
				commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "->Select Item (Enter Item Number)\n" + "->Back (B)");
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
