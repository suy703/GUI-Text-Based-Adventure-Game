import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CommandMenu implements CommandMenuInterface {

	private Formatter file;
	private Scanner readFile;
	
	TextArea displayStory = new TextArea("Outlaw Oasis\n\n" + "You are a cowboy named Texas Heck, who is "
			+ "fed up with his cows getting rustled by a gang known as the Long Riders. He hears the "
			+ "sheriff won’t be much help, so he takes matters into his own hands. Heck starts his "
			+ "adventure in center of Bombay Hill, one of three towns controlled by the Long Riders, "
			+ "his plan is to find the leader of the gang and taking them out.");
	TextArea displayCommand = new TextArea("Action\n" +"-> Start New Game (S)\n" + "-> Load Game (L)\n" 
			 + "-> Credit (C)");
	
	Image icon = new Image("file:images/icon.jpg");
	Image map = new Image("file:images/BombayHill.png"); 
	
	//CENTER PANE--------------------------------------------------------------------------------------
	//DISPLAY STORY
	public TextArea displayStory(TextArea displayStory) {
		displayStory = this.displayStory;
		displayStorySetting(displayStory);
		return displayStory;
	}
	//DISPLAY SETTING
	public void displayStorySetting(TextArea displayStory) {
		displayStory.setEditable(false);
		displayStory.setWrapText(true);
		displayStory.setLayoutX(10);
		displayStory.setLayoutY(200);
		displayStory.setPrefHeight(335);
		displayStory.setPrefWidth(280);
	}
	
	//COMMAND MENU-------------------------------------------------------------------------------------
	public TextArea displayCommand(TextArea displayCommand) {
		displayCommand = this.displayCommand;
		displayCommandSetting(displayCommand);
		return displayCommand;
	}
	//COMMAND SETTING
	public void displayCommandSetting(TextArea displayCommand) {
		displayCommand.setEditable(false);
		displayCommand.setLayoutX(310);
		displayCommand.setLayoutY(405);
		displayCommand.setPrefHeight(130);
		displayCommand.setPrefWidth(280);
	}
	
	//SAVE GAME----------------------------------------------------------------------------------------
	public void saveGame(String fileName) {
		
		try {
			
			file = new Formatter(fileName);
			System.out.println("Game Saved");
		}
		catch(Exception e) {
			System.out.println("error: file did not save");
		}
		writeFile();
		closeFile();
	}
	//WRITE FILE
	public void writeFile() {
		/*
		 * Write saved game. Use... file.format(format, args);
		 */
	}
	//CLOSE FILE
	public void closeFile() {
		
		file.close();
	}
	
	//LOAD GAME----------------------------------------------------------------------------------------
	public void loadGame(String fileName) {
		
		try {
			
			readFile = new Scanner(new File(fileName));
		}
		catch(Exception e) {
			System.out.println("error: could not find file");
		}
		
		//Read file
		while(readFile.hasNext()) {
			/*
			 * Read what is in the saved game file
			 */
		}
		//Close File
		readFile.close();
	}
	public TextArea loadGameStory(TextArea displayStory, String story) {
		int getStoryLength = this.displayStory.getLength();
		this.displayStory.replaceText(0, getStoryLength, story);
		displayStory = this.displayStory;
		displayStorySetting(displayStory);
		return displayStory;
	}
	public TextArea loadGameCommand(TextArea displayCommand, String command) {
		int getCommandLength = this.displayCommand.getLength();
		this.displayCommand.replaceText(0, getCommandLength, command);
		displayCommand = this.displayCommand;
		displayCommandSetting(displayCommand);
		return displayCommand;
	}

	//NAVIGATE MAP--------------------------------------------------------------------------------------
	public ImageView navigateMap(Image map, ImageView viewMap) {
		map = this.map;
        viewMap.setImage(map);
        viewMap.setLayoutX(310);
        viewMap.setLayoutY(200);
        return viewMap;
	}
	public ImageView navigateIcon(Image icon, ImageView viewIcon, int x, int y) {
		icon = this.icon;
		viewIcon.setImage(icon);
        viewIcon.setLayoutX(x);
        viewIcon.setLayoutY(y);
		return viewIcon;
	}
}
