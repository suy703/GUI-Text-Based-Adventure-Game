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
	//SAVE GAME
	public void saveGame(String fileName);
	public void writeFile();
	public void closeFile();
	//LOAD GAME
	public void loadGame(String fileName);
	public TextArea loadGameStory(TextArea displayStory, String story);
	public TextArea loadGameCommand(TextArea displayCommand, String command);
	//NAVIGATE MAP
	public ImageView navigateMap(Image map, ImageView viewMap);
	public ImageView navigateIcon(Image icon, ImageView viewIcon, int x, int y);
}
