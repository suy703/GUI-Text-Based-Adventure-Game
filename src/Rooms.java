import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Rooms implements Requirements {

	Scene main;
	@Override
	public void titleScreen(Stage stage, Scene scene) {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(1280, 720);
		main = new Scene(borderPane);
		
	}

	
}
