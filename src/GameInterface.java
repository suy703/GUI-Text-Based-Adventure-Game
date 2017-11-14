import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		Rooms room = new Rooms(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		GameControl control = new GameControl();
	
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
      
		control.gameControl(inputCommand, prompt, commandMenu, room);
			
		borderPane.setBackground(background);
		centerPane.getChildren().addAll(displayStory, displayCommand, viewMap, viewIcon);
		bottomPane.getChildren().addAll(inputCommand, prompt);
		mainMenu = new Scene(borderPane);
		
		System.out.println("Game interface initialized"); // Testing purpose
	}
}
