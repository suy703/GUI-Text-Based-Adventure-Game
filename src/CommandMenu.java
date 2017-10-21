import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CommandMenu implements CommandMenuInterface {

	TextArea displayStory = new TextArea("Outlaw Oasis");
	TextArea displayCommand = new TextArea("Action\n" +"-> Start New Game (S)\n" + "-> Load Game (L)\n" + 
			"-> Player Stats (P)\n" + "-> Credit (C)");
	
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
		displayStory.setPrefHeight(330);
		displayStory.setPrefWidth(280);
	}
	
	//COMMAND MENU
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
		displayCommand.setPrefHeight(125);
		displayCommand.setPrefWidth(280);
	}
	
	//LOAD GAME
	public TextArea loadGameStory(TextArea displayStory) {
		int getStoryLength = this.displayStory.getLength();
		this.displayStory.replaceText(0, getStoryLength, "Load Game");
		displayStory = this.displayStory;
		displayStorySetting(displayStory);
		return displayStory;
	}
	public TextArea loadGameCommand(TextArea displayCommand) {
		int getCommandLength = this.displayCommand.getLength();
		this.displayCommand.replaceText(0, getCommandLength, "Action\n " + "-> Back (B)");
		displayCommand = this.displayCommand;
		displayCommandSetting(displayCommand);
		return displayCommand;
	}

	//NAVIGATE MAP
	public ImageView navigateMap(Image map, ImageView viewMap) {
		map = this.map;
        viewMap.setImage(map);
        viewMap.setLayoutX(310);
        viewMap.setLayoutY(200);
        return viewMap;
	}

	// Exit
	public MenuItem exit() {
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> {
		    System.exit(0);
		});
		
		return exit;
	}

}
