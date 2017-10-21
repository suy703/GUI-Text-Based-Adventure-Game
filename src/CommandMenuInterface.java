import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public interface CommandMenuInterface {

	//CENTER PANE--------------------------------------------------------------------------------------
	//DISPLAY STORY
	public TextArea displayStory(TextArea displayStory);
	public void displayStorySetting(TextArea displayStory);
	//COMMAND MENU
	public TextArea displayCommand(TextArea displayCommand);
	public void displayCommandSetting(TextArea displayCommand);
	//LOAD GAME
	public TextArea loadGameStory(TextArea displayStory);
	public TextArea loadGameCommand(TextArea displayCommand);
	//NAVIGATE MAP
	public ImageView navigateMap(Image map, ImageView viewMap);
	//EXIT
	public MenuItem exit();
}
