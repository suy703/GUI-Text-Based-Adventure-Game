import javafx.scene.control.MenuItem;

public class CommandMenu implements CommandMenuInterface {

	// Exit
	public MenuItem exit() {
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> {
		    System.exit(0);
		});
		
		return exit;
	}

}
