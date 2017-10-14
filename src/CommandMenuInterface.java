import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public interface CommandMenuInterface {

	//Exit
	public MenuItem exit();
	// Start New Game
	public Button startNewGame(Stage stage, Scene scene);
	// Load Game
	public Button loadGame(Stage stage, Scene scene);
	// Save Game
	public Button saveGame(Stage stage, Scene scene);
	// Check Player Statistics
	public Button checkPlayerStat(Stage stage, Scene scene);
}
