import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Rooms implements RoomInterface {

	Scene mainMenu, room01, room02, room03, room04, room05, room06, room07, room08, room09, room10,
		room11, room12, room13, room14, room15, room16, room17, room18, room19, room20,
		room21, room22, room23, room24, room25, room26, room27, room28, room29, room30;
	@Override
	public void mainMenu(Stage stage, Scene scene) {
		CommandMenu commandMenu = new CommandMenu();
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(600,600);
		VBox topPane = new VBox();
		VBox centerPane = new VBox();
		centerPane.setAlignment(Pos.CENTER);
		
		borderPane.setTop(topPane);
		borderPane.setCenter(centerPane);
		
		//TOP PANE------------------------------------------------------------------
		//MENU PROPERTIES
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		
		//CENTER PANE------------------------------------------------------------------
		Button startNewGame = commandMenu.startNewGame(stage, scene);
		Button loadGame = commandMenu.loadGame(stage, scene);
		Button playersStats = commandMenu.checkPlayerStat(stage, scene);
		
		menuBar.getMenus().add(menuFile);
		menuFile.getItems().add(commandMenu.exit());
		
		topPane.getChildren().add(menuBar);
		centerPane.getChildren().addAll(startNewGame, loadGame, playersStats);
		
		mainMenu = new Scene(borderPane);
		
	}
}
