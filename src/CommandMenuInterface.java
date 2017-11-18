import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public interface CommandMenuInterface {

	//CENTER PANE--------------------------------------------------------------------------------------
	//DISPLAY STORY
	public TextArea getDisplayStory();
	public void setDisplayStory(TextArea displayStory);
	//COMMAND MENU
	public TextArea getDisplayCommand();
	public void setDisplayCommand(TextArea displayCommand);
	//SAVE GAME
	public void saveGame(String fileName);
	public void writeFile();
	public void closeFile();
	//LOAD GAME
	public void loadGame(String fileName);
	public TextArea getLoadGameStory();
	public TextArea getLoadGameCommand();
	public void setLoadGameStory(TextArea displayStory, String story);
	public void setLoadGameCommand(TextArea displayCommand, String command);
	//NAVIGATE MAP
	public ImageView getNavigateMap();
	public ImageView getNavigateIcon();
	public void setNavigateMap(Image map, ImageView viewMap, int x, int y);
	public void setNavigateIcon(Image icon, ImageView viewIcon, int x, int y);
	//BOTTOM PANE---------------------------------------------------------------------------------------
	//PROMPT MESSAGE
	public void prompt(Text prompt, String message);
}
