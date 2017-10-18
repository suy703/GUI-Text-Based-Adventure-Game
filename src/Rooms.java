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

public class Rooms implements RoomInterface {
	
	//GAME BACKGROUND----------------------------------------------------------------------------------
	final Image gameBackground = new Image("file:images/background.jpg");
	final BackgroundImage backgroundImage = new BackgroundImage(gameBackground, null, null, null, null);
	final Background background = new Background(backgroundImage);
	
	Scene mainMenu, room01, loadGame, room03, room04, room05, room06, room07, room08, room09, room10,
		room11, room12, room13, room14, room15, room16, room17, room18, room19, room20,
		room21, room22, room23, room24, room25, room26, room27, room28, room29, room30;

	Image map;
	TextArea displayStory, displayCommand;
	
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu();
		
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
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem exit = commandMenu.exit();
		
		//CENTER PANE--------------------------------------------------------------------------------------
		//DISPLAY STORY
		displayStory = new TextArea("Outlaw Oasis");
		displayStory.setEditable(false);
		displayStory.setWrapText(true);
		displayStory.setLayoutX(10);
		displayStory.setLayoutY(200);
		displayStory.setPrefHeight(330);
		displayStory.setPrefWidth(280);
		
		//COMMAND MENU
		displayCommand = new TextArea("Action\n" +"-> Start New Game (S)\n" + "-> Load Game (L)\n" + 
				"-> Player Stats (P)\n" + "-> Credit (C)");
		displayCommand.setEditable(false);
		displayCommand.setLayoutX(310);
		displayCommand.setLayoutY(405);
		displayCommand.setPrefHeight(125);
		displayCommand.setPrefWidth(280);

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
				stage.setScene(room01); // Change scene or room
			
			}
			else if(command.equalsIgnoreCase("l")) {
				stage.setScene(loadGame);
				
			}

		});
        
		//TESTING PURPOSE
		borderPane.setOnMouseMoved(e -> {
			
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
		});
		
		borderPane.setBackground(background);
		
		menuBar.getMenus().add(menuFile);
		menuFile.getItems().add(exit);
		
		topPane.getChildren().add(menuBar);
		centerPane.getChildren().addAll(displayStory, displayCommand);
		bottomPane.getChildren().add(inputCommand);
		
		mainMenu = new Scene(borderPane);
	}
	
	public void room01(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu();
		
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
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem exit = commandMenu.exit();
		
		//CENTER PANE--------------------------------------------------------------------------------------
		//DISPLAY STORY
		displayStory = new TextArea("Outlaw Oasis");
		displayStory.setEditable(false);
		displayStory.setWrapText(true);
		displayStory.setLayoutX(10);
		displayStory.setLayoutY(200);
		displayStory.setPrefHeight(330);
		displayStory.setPrefWidth(280);
		
		//COMMAND MENU
		displayCommand = new TextArea("Action\n" +"-> Start New Game (S)\n" + "-> Load Game (L)\n" + 
				"-> Player Stats (P)\n" + "-> Credit (C)");
		displayCommand.setEditable(false);
		displayCommand.setLayoutX(310);
		displayCommand.setLayoutY(405);
		displayCommand.setPrefHeight(125);
		displayCommand.setPrefWidth(280);
		
		//MAPS
		map = new Image("file:images/BombayHill.png");
		ImageView bombayHill = new ImageView();
        bombayHill.setImage(map);
        bombayHill.setLayoutX(310);
        bombayHill.setLayoutY(200);
		
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
				stage.setScene(room01); // Change scene or room
			
			}
			else if(command.equalsIgnoreCase("l")) {
				stage.setScene(loadGame);
				
			}

		});
        
		//TESTING PURPOSE
		borderPane.setOnMouseMoved(e -> {
			
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
		});
		
		borderPane.setBackground(background);
		
		menuBar.getMenus().add(menuFile);
		menuFile.getItems().add(exit);
		
		topPane.getChildren().add(menuBar);
		centerPane.getChildren().addAll(displayStory, displayCommand, bombayHill);
		bottomPane.getChildren().add(inputCommand);
		
		room01 = new Scene(borderPane);
	}

	public void loadGame(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu();
		
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
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem exit = commandMenu.exit();
		
		//CENTER PANE--------------------------------------------------------------------------------------
		//DISPLAY STORY
		displayStory = new TextArea("Load Game");
		displayStory.setEditable(false);
		displayStory.setWrapText(true);
		displayStory.setLayoutX(10);
		displayStory.setLayoutY(200);
		displayStory.setPrefHeight(330);
		displayStory.setPrefWidth(280);
		
		//COMMAND MENU
		displayCommand = new TextArea("Action\n" + "-> Main Menu (M)");
		displayCommand.setEditable(false);
		displayCommand.setLayoutX(310);
		displayCommand.setLayoutY(405);
		displayCommand.setPrefHeight(125);
		displayCommand.setPrefWidth(280);

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
			if(command.equalsIgnoreCase("m")) {
				stage.setScene(mainMenu); // Change scene or room
			
			}

		});
        
		//TESTING PURPOSE
		borderPane.setOnMouseMoved(e -> {
			
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
		});
		
		borderPane.setBackground(background);
		
		menuBar.getMenus().add(menuFile);
		menuFile.getItems().add(exit);
		
		topPane.getChildren().add(menuBar);
		centerPane.getChildren().addAll(displayStory, displayCommand);
		bottomPane.getChildren().add(inputCommand);
		
		loadGame = new Scene(borderPane);
	}
}
