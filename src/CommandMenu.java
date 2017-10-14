import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class CommandMenu implements CommandMenuInterface {

	// Exit
	public MenuItem exit() {
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> {
		    System.exit(0);
		});
		
		return exit;
	}
	
	// Start New Game
	public Button startNewGame(Stage stage, Scene scene) {
		Button startNewGame = new Button("Start New Game");
		startNewGame.setOnAction(e -> {
			stage.setScene(scene);
		});
		
		return startNewGame;
	}
	// Load Game
	public Button loadGame(Stage stage, Scene scene) {
		Button loadGame = new Button("Load Game");
		loadGame.setOnAction(e -> {
			stage.setScene(scene);
		});
		
		return loadGame;
	}
	// Save Game
	public Button saveGame(Stage stage, Scene scene) {
		Button saveGame = new Button("Save Game");
		saveGame.setOnAction(e -> {
			stage.setScene(scene);
		});
		
		return saveGame;
	}
	// Check Player Statistics
	public Button checkPlayerStat(Stage stage, Scene scene) {
		Button checkPlayerStat = new Button("Check Player Statistics");
		checkPlayerStat.setOnAction(e -> {
			stage.setScene(scene);
		});
		
		return checkPlayerStat;
	}
	
	
}
