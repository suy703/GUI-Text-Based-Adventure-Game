import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;



public class InvTest extends Application{
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<String> al = new ArrayList<String>(Arrays.asList("Gun","Terminator Gun"));
		ChoiceBox<String> cb = new ChoiceBox<String>();
		for(int i = 0; i<al.size(); i++)
			cb.getItems().add(al.get(i));
		Scene scene = new Scene(cb,200,300);
		primaryStage.setTitle("Inv");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
