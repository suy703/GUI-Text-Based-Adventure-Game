/* Alexander Croghan
 * Henry Huynh
 * Sinna Uy
 * 
 * Georgia Gwinnett Collegw
 * ITEC 3860 Software Development 1
 * Fall 2017
 * Dr. Rahaf Barakat
 * 
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class OutlawOasis extends Application {

	public static void main(String[] args) {
		System.out.println("Main method initialized"); // Testing purpose

		launch(args);
	}
	
	public void start(Stage stage) {
		System.out.println("Start method initialized"); // Testing purpose

		GameInterface ui = new GameInterface();
		ui.mainMenu(stage, ui.mainMenu);
		stage.setTitle("Outlaw Oasis"); // Set the stage title
		stage.setScene(ui.mainMenu); // Place the scene in the stage
		stage.show(); // Display the stage
		

	}

}
