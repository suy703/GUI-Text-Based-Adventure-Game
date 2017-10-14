import javafx.application.Application;
import javafx.stage.Stage;

public class OutlawOasis extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		
		Rooms room = new Rooms();
		room.mainMenu(stage, room.mainMenu);
		stage.setTitle("Outlaw Oasis"); // Set the stage title
		stage.setScene(room.mainMenu); // Place the scene in the stage
		stage.show(); // Display the stage
	}

}
