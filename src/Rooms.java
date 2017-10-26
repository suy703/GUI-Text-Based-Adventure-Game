import javafx.geometry.Insets;
import javafx.scene.Scene;
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
import javafx.scene.control.TextArea;

public class Rooms {
	CommandMenu commandMenu = new CommandMenu();
	
	
	//GAME BACKGROUND----------------------------------------------------------------------------------
	final Image gameBackground = new Image("file:images/background.jpg");
	final BackgroundImage backgroundImage = new BackgroundImage(gameBackground, null, null, null, null);
	final Background background = new Background(backgroundImage);
	
	Scene mainMenu;
	
	Image map, icon;
	ImageView viewIcon = new ImageView();
	ImageView viewMap = new ImageView();
	TextArea displayStory, displayCommand;
	
	Boolean lockMainMenu = true;
	Boolean unlockSaveGame = false;
	
	Boolean drugStore = true;
	Boolean inn = true;
	
	public void mainMenu(Stage stage, Scene scene) {
		
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
		displayStory = commandMenu.displayStory(displayStory);
		//COMMAND MENU
		displayCommand = commandMenu.displayCommand(displayCommand);

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
			if((command.equalsIgnoreCase("s") && lockMainMenu) || command.equalsIgnoreCase("e")) {
				TownHub_1A();
				lockMainMenu = false;
				unlockSaveGame = true;
				inn = false;
			}
			//Load Game
			else if(command.equalsIgnoreCase("l") && lockMainMenu) {
				displayStory = commandMenu.loadGameStory(displayStory, "Load Game");
				displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Back (B)");
				commandMenu.loadGame("testGame.ini");
			}
			//Back
			else if(command.equalsIgnoreCase("b") && lockMainMenu) {
				viewMap.setOpacity(0);
				viewIcon.setOpacity(0);
				displayStory = commandMenu.loadGameStory(displayStory, "Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is "
						+ "fed up with his cows getting rustled by a gang known as the Long Riders. He hears the "
						+ "sheriff won’t be much help, so he takes matters into his own hands. Heck starts his "
						+ "adventure in center of Bombay Hill, one of three towns controlled by the Long Riders, "
						+ "his plan is to find the leader of the gang and taking them out.");
				displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Start New Game (S)\n" + "-> Load Game (L)\n" + "-> Credit (C)");
			}
			//Save Game
			if(command.equalsIgnoreCase("sg") && unlockSaveGame) {
				commandMenu.saveGame("testGame.ini");
			}
			//Exit Game
			if(command.equalsIgnoreCase("exit")) {
				System.exit(0);
			}
			
			//DrugStore
			if(command.equalsIgnoreCase("nw") && drugStore) {
				DrugStore_1B();
				inn = true;
			}
			else if(command.equalsIgnoreCase("sr") && drugStore) {
				Potion healthPotion = new Potion("i1", "Health Potion (HP)\n", "A bottle shaped like a human heart with a red liquid inside.", 2, "Potion", 3);
				Potion attackPotion = new Potion("i2", "Attack Potion (AP)\n", "A perfectly round bottle with a cork in the top. The liquid has a strange yellow color to it but it smells like a delicious tropical fruit when you uncork it.", 3, "Potion", 2);
				Weapon pistol = new Weapon("i7", "Pistol (P)\n", "A shiney, metalic, little gun. It might be small but it could definitely do some damage!", 5, 5);
				displayStory = commandMenu.loadGameStory(displayStory, "Drug Store\n\n" + healthPotion.getName() + attackPotion.getName() + pistol.getName() + "\n -> Drug Store (NW)");
				displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Examine Item (EI)\n" 
						+ "-> Inventory (I)\n" + "-> Save Game (SG)");
			}
			
			//Inn
			if(command.equalsIgnoreCase("s") && inn) {
				Inn_1C();
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
	}
	//Town Hub
	public void TownHub_1A() {
		
		viewMap = commandMenu.navigateMap(map, viewMap);
		viewIcon = commandMenu.navigateIcon(icon, viewIcon, 420, 275);
		displayStory = commandMenu.loadGameStory(displayStory, "Town Hub\n\n" + "A dusty road leads into town and a tumbleweed "
				+ "rolls across your path.  In the town square there is a statue of a menacing looking figure with a sheriff’s "
				+ "badge on. The buildings look a bit run down and people seem to shuffle from place to place without interacting. "
				+ "To the East there is a bustling saloon. To your North-West you see a drug store and to the South-West, an Inn.\n\n"
				+ "-> Drug Store (NW)\n" + "-> Inn (SW)\n" + "-> Saloon (E)");
		displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Inventory (I)\n" + "-> Save Game (SG)");
	}
	//Drug Store
	public void DrugStore_1B() {
		
		viewMap = commandMenu.navigateMap(map, viewMap);
		viewIcon = commandMenu.navigateIcon(icon, viewIcon, 340, 250);
		displayStory = commandMenu.loadGameStory(displayStory, "Drug Store\n\n" + "A mostly bare room with a worn down looking counter "
				+ "top. Glass bottles full of medicine line the walls, but they look as if they haven’t been touched in years. You "
				+ "could almost measure the amount of dust accumulation on them. A man with a large mustache and a tattered suit "
				+ "stands behind the counter looking hopeful. To the South is a small passageway leading into a small, dimly lit, room "
				+ "and to the East is the door leading to the Town Hub.\n\n" + "-> Town Hub (E)\n" + "-> Inn (S)");
		displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Search Room (SR)\n" 
				+ "-> Inventory (I)\n" + "-> Save Game (SG)");
	}
	//Inn
	public void Inn_1C() {
		
		viewMap = commandMenu.navigateMap(map, viewMap);
		viewIcon = commandMenu.navigateIcon(icon, viewIcon, 345, 334);
		displayStory = commandMenu.loadGameStory(displayStory, "Inn\n\n" + "You walk into the inn and you see "
				+ "a counter with a bell on it. There doesn’t appear to be anyone around, but you see a few "
				+ "rooms with the doors closed. There are a few dusty painting hanging on the wall, they look "
				+ "like they have seen better days. There is a hallway to the North that leads into a larger, "
				+ "well lit room and to the East is the door to the Town Hub. The inn keeper comes up before "
				+ "you get a chance to ring the bell and asks you if you want a room.\n\n" + "-> Drug Store (N)\n" + "-> Town Hub (E)");
		displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Search Room (SR)\n" 
				+ "-> Inventory (I)\n" + "-> Save Game (SG)");
	}
}
