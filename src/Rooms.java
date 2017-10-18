import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class Rooms implements RoomInterface {
	
	BackgroundImage backgroundImage;
	Background background;
	Scene mainMenu, room01, room02, room03, room04, room05, room06, room07, room08, room09, room10,
		room11, room12, room13, room14, room15, room16, room17, room18, room19, room20,
		room21, room22, room23, room24, room25, room26, room27, room28, room29, room30;

	Image gameBackground, map;
	public TextArea displayStory, displayCommand;
	TextField inputCommand;
	
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu();
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,550);
		VBox topPane = new VBox();
		VBox leftPane = new VBox();
		StackPane rightPane = new StackPane();
		HBox bottomPane = new HBox();
		leftPane.setAlignment(Pos.CENTER_LEFT);
		leftPane.setPadding(new Insets(10, 10, 10, 10));
		leftPane.setSpacing(10);
		rightPane.setAlignment(Pos.CENTER_RIGHT);
		rightPane.setPadding(new Insets(10, 10, 10, 10));
		bottomPane.setPadding(new Insets(10, 10, 10, 10));
		bottomPane.setSpacing(10);
		
		borderPane.setTop(topPane);
		borderPane.setLeft(leftPane);
		borderPane.setRight(rightPane);
		borderPane.setBottom(bottomPane);
		
		//GAME BACKGROUND----------------------------------------------------------------
		gameBackground = new Image("file:images/background.jpg");
		backgroundImage = new BackgroundImage(gameBackground, null, null, null, null);
		background = new Background(backgroundImage);

		//TOP PANE------------------------------------------------------------------
		//MENU PROPERTIES
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem exit = commandMenu.exit();
		
		//LEFT PANE------------------------------------------------------------------
		//DISPLAY STORY
		displayStory = new TextArea("Outlaw Oasis");
		displayStory.setEditable(false);
		displayStory.setWrapText(true);
		displayStory.setPrefHeight(250);
		displayStory.setPrefWidth(280);
		//COMMAND MENU
		displayCommand = new TextArea("Action\n" +"-> Start New Game\n" + "-> Load Game\n" + 
				"-> Player Stats\n" + "-> Credit");
		displayCommand.setEditable(false);
		displayCommand.setPrefHeight(100);
		displayCommand.setPrefWidth(280);
		
		//RIGHT PANE-------------------------------------------------------------------
		//MAPS
		map = new Image("file:images/BombayHill.png");
		ImageView bombayHill = new ImageView();
        bombayHill.setImage(map);
		
		//BOTTOM PANE------------------------------------------------------------------
		//EVENTHANDLER COMAMND
        inputCommand = new TextField();
		inputCommand.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				stage.setScene(room01); // Change scene or room
				System.out.println(inputCommand.getText()); // Testing Purpose
				inputCommand.clear();
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
		leftPane.getChildren().addAll(displayStory, displayCommand);
		rightPane.getChildren().addAll(bombayHill);
		bottomPane.getChildren().add(inputCommand);
		
		mainMenu = new Scene(borderPane);
	}
	
	public void room01(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu();
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,550);
		VBox topPane = new VBox();
		VBox leftPane = new VBox();
		StackPane rightPane = new StackPane();
		HBox bottomPane = new HBox();
		leftPane.setAlignment(Pos.CENTER_LEFT);
		leftPane.setPadding(new Insets(10, 10, 10, 10));
		leftPane.setSpacing(10);
		rightPane.setAlignment(Pos.CENTER_RIGHT);
		rightPane.setPadding(new Insets(10, 10, 10, 10));
		bottomPane.setPadding(new Insets(10, 10, 10, 10));
		bottomPane.setSpacing(10);
		
		borderPane.setTop(topPane);
		borderPane.setLeft(leftPane);
		borderPane.setRight(rightPane);
		borderPane.setBottom(bottomPane);
		
		//GAME BACKGROUND----------------------------------------------------------------
		gameBackground = new Image("file:images/background.jpg");
		backgroundImage = new BackgroundImage(gameBackground, null, null, null, null);
		background = new Background(backgroundImage);

		//TOP PANE------------------------------------------------------------------
		//MENU PROPERTIES
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem exit = commandMenu.exit();
		
		//LEFT PANE------------------------------------------------------------------
		//DISPLAY STORY
		displayStory = new TextArea("Outlaw Oasis");
		displayStory.setEditable(false);
		displayStory.setWrapText(true);
		displayStory.setPrefHeight(250);
		displayStory.setPrefWidth(280);
		//COMMAND MENU
		displayCommand = new TextArea("Action\n");
		displayCommand.setEditable(false);
		displayCommand.setPrefHeight(100);
		displayCommand.setPrefWidth(280);
		
		//RIGHT PANE-------------------------------------------------------------------
		//MAPS
		map = new Image("file:images/BombayHill.png");
		ImageView bombayHill = new ImageView();
        bombayHill.setImage(map);
		
		//BOTTOM PANE------------------------------------------------------------------
		//EVENTHANDLER COMAMND
        inputCommand = new TextField();
		inputCommand.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				stage.setScene(scene); // Change scene or room
				System.out.println(inputCommand.getText()); // Testing Purpose
				inputCommand.clear();
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
		leftPane.getChildren().addAll(displayStory, displayCommand);
		rightPane.getChildren().addAll(bombayHill);
		bottomPane.getChildren().add(inputCommand);
		
		room01 = new Scene(borderPane);
	}
}
