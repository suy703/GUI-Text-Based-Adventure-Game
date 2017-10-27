/*
 * Sinna, Alex, 
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class OutlawOasis extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		GameInterface ui = new GameInterface();
		ui.mainMenu(stage, ui.mainMenu);
		stage.setTitle("Outlaw Oasis"); // Set the stage title
		stage.setScene(ui.mainMenu); // Place the scene in the stage
		stage.show(); // Display the stage
	}

}
