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
	public TextArea loadGameStory(TextArea displayStory, String story);
	public TextArea loadGameCommand(TextArea displayCommand, String command);
	//NAVIGATE MAP
	public ImageView navigateMap(Image map, ImageView viewMap);
}
