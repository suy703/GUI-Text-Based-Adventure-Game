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
			if(command.equalsIgnoreCase("s")) {
				viewMap.setOpacity(100);
				viewIcon.setOpacity(100);
				viewMap = commandMenu.navigateMap(map, viewMap);
				viewIcon = commandMenu.navigateIcon(icon, viewIcon, 420, 275);
				displayStory = commandMenu.loadGameStory(displayStory, "You are a cowboy named Texas Heck, who is "
						+ "fed up with his cows getting rustled by a gang known as the Long Riders. He hears the "
						+ "sheriff won’t be much help, so he takes matters into his own hands. Heck starts his "
						+ "adventure in center of Bombay Hill, one of three towns controlled by the Long Riders, "
						+ "his plan is to find the leader of the gang and taking them out.");
				displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Save Game (SG)");
			
			}
			else if(command.equalsIgnoreCase("l")) {
				displayStory = commandMenu.loadGameStory(displayStory, "Load Game");
				displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" + "-> Back (B)");
				commandMenu.loadGame("testGame.ini");
				
			}
			else if(command.equalsIgnoreCase("b")) {
				viewMap.setOpacity(0);
				viewIcon.setOpacity(0);
				displayStory = commandMenu.loadGameStory(displayStory, "Outlaw Oasis");
				displayCommand = commandMenu.loadGameCommand(displayCommand, "Action\n" +"-> Start New Game (S)\n" + "-> Load Game (L)\n");
			}
			//SAVE GAME
			if(command.equalsIgnoreCase("sg")) {
				commandMenu.saveGame("testGame.ini");
			}
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
	}
}
