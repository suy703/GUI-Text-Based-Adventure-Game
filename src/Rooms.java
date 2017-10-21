import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

	//TOP PANE---------------------------------------------------------------------------------------
	//MENU PROPERTIES
	MenuBar menuBar = new MenuBar();
	Menu menuFile = new Menu("File");
	
	Image map;
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
		
		//TOP PANE---------------------------------------------------------------------------------------
		//MENU PROPERTIES
		MenuItem exit = commandMenu.exit();
		
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
			if(command.equalsIgnoreCase("s")) {
				viewMap = commandMenu.navigateMap(map, viewMap); // In Progress
			
			}
			else if(command.equalsIgnoreCase("l")) {
				displayStory = commandMenu.loadGameStory(displayStory);
				displayCommand = commandMenu.loadGameCommand(displayCommand);
				
			}

		});
        /*
		//TESTING PURPOSE
		borderPane.setOnMouseMoved(e -> {
			
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
		});
		*/
		borderPane.setBackground(background);
		menuBar.getMenus().add(menuFile);
		menuFile.getItems().add(exit);
		topPane.getChildren().add(menuBar);
		centerPane.getChildren().addAll(displayStory, displayCommand, viewMap);
		bottomPane.getChildren().add(inputCommand);
		mainMenu = new Scene(borderPane);
	}
}
