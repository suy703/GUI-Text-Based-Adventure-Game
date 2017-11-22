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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameInterface {
	//GAME BACKGROUND----------------------------------------------------------------------------------
	private final Image gameBackground = new Image("file:images/background.jpg");
	private final BackgroundImage backgroundImage = new BackgroundImage(gameBackground, null, null, null, null);
	private final Background background = new Background(backgroundImage);
	
	Scene mainMenu;
	
	Image icon = new Image("file:images/icon.png");
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
	
	Image healthIcon = new Image("file:images/healthIcon.png");
	ImageView viewHealthIcon = new ImageView();
	Image monsterIcon = new Image("file:images/monsterHealthIcon.png");
	ImageView viewMonsterIcon = new ImageView();
	
	public void mainMenu(Stage stage, Scene scene) {
		GameControl control = new GameControl();
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(1200,800);
		VBox topPane = new VBox();
		Pane centerPane = new Pane();
		Pane bottomPane = new HBox();
		bottomPane.setStyle("-fx-background-color: #000000;");
		bottomPane.setPadding(new Insets(10, 10, 10, 10));
		borderPane.setTop(topPane);
		borderPane.setCenter(centerPane);
		borderPane.setBottom(bottomPane);
		//CENTER PANE--------------------------------------------------------------------------------------
		//PLAYER HEALTH
		Rectangle maxHealthBar = new Rectangle();  
		Rectangle healthBar = new Rectangle();
		Rectangle monsterMaxHealthBar = new Rectangle();
		Rectangle monsterHealthBar = new Rectangle();
		//BOTTOM PANE-------------------------------------------------------------------------------------
		Text commandText = new Text();
		Text prompt = new Text(); //Error message
		//EVENTHANDLER COMAMND
        TextField inputCommand = new TextField();
        control.gameControl(commandText, inputCommand, prompt, icon, map, viewIcon, viewMap, displayStory, displayCommand, maxHealthBar, healthBar, 
        		healthIcon, viewHealthIcon, monsterMaxHealthBar, monsterHealthBar, monsterIcon, viewMonsterIcon);
			
		borderPane.setBackground(background);
		centerPane.getChildren().addAll(maxHealthBar, healthBar, viewHealthIcon, monsterMaxHealthBar, monsterHealthBar, viewMonsterIcon, displayStory, displayCommand, viewMap, viewIcon);
		bottomPane.getChildren().addAll(commandText, inputCommand, prompt);
		mainMenu = new Scene(borderPane);
		
		System.out.println("Game Interface Initialized"); // Testing purpose
	}
}
