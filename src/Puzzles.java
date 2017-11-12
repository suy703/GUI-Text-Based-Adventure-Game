import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Puzzles {
	
	String puzzleID;
	String puzzleQuestion;
	String puzzleLocation;
	String puzzleHint;
	String puzzleDescription;
	Boolean puzzleLock = true;
	
	Image map, icon;
	ImageView viewIcon, viewMap;
	TextArea displayStory, displayCommand;

	public Puzzles(String puzzleID) {
		this.puzzleID = puzzleID;
	}

	public Puzzles(String puzzleID, String puzzleQuestion, String puzzleLocation, String puzzleHint,
			String puzzleDescription, Boolean puzzleLock) {
		this.puzzleID = puzzleID;
		this.puzzleQuestion = puzzleQuestion;
		this.puzzleLocation = puzzleLocation;
		this.puzzleHint = puzzleHint;
		this.puzzleDescription = puzzleDescription;
		this.puzzleLock = puzzleLock;
	}
	
	public Puzzles(Image map, Image icon, ImageView viewIcon, ImageView viewMap, TextArea displayStory, TextArea displayCommand) {
		 this.map = map;
		 this.icon = icon;
		 this.viewIcon = viewIcon;
		 this.viewMap = viewMap;
		 this.displayStory = displayStory;
		 this.displayCommand = displayCommand;
	 }

	public Boolean getPuzzleLock() {
		return puzzleLock;
	}

	public void setPuzzleLock(Boolean puzzleLock) {
		this.puzzleLock = puzzleLock;
	}

	public String getPuzzleID() {
		return puzzleID;
	}
	
	public void setPuzzleID(String puzzleID) {
		this.puzzleID = puzzleID;
	}
	
	public String getPuzzleQuestion() {
		return puzzleQuestion;
	}

	public void setPuzzleQuestion(String puzzleQuestion) {
		this.puzzleQuestion = puzzleQuestion;
			
	}
	
	public void setPuzzleHint(String puzzleHint) {
		this.puzzleHint = puzzleHint;
	}
	
	public String getPuzzleDescription() {
		return puzzleDescription;
	}
	
	public void setPuzzleDescription(String puzzleDescription) {
		this.puzzleDescription = puzzleDescription;
	}
	
	public String getPuzzleHint() {
		if(puzzleID.equalsIgnoreCase("P01")) {
			puzzleHint = "Player must start a fight with the Henchman in the Saloon. "
					+ "The Deputy Sheriff will then arrest the player and take them to Jail "
					+ "(room 1E). The player can choose to stay in Jail and serve their term, "
					+ "or challenge the Deputy Sheriff to a duel. If the player challenges the "
					+ "Deputy Sheriff to a duel, the player and the Deputy Sheriff will fight "
					+ "and the boss area will be cleared if the fight is won. If the player "
					+ "loses or if they do not choose to duel the Deputy Sheriff, the player "
					+ "will be let out of jail and the player will have to complete the puzzle again. ";
		} else if ( puzzleID.equalsIgnoreCase("P02")) {
			puzzleHint = "The player breaks into the Ransacked General store to "
					+ "find a strange person staring at them. The player must answer a "
					+ "series of questions to determine if they are trustworthy enough "
					+ "for the password to the Saloon. If the player answers the questions "
					+ "correctly, the person will see that the player is one of the “good guys” "
					+ "and will give him the password in hopes that he will be able to take down "
					+ "the Deputy Sheriff inside. ";
		} else if ( puzzleID.equalsIgnoreCase("P03")) {
			puzzleHint = "To be able to use the Inn to restore health, the player must first "
					+ "ring the bell to get the attention of the innkeeper. The bell must be "
					+ "rung in a specific pattern (shave and a haircut) so the innkeeper "
					+ "hears it and comes to show you to your room. ";
		} else if ( puzzleID.equalsIgnoreCase("P04")) {
			puzzleHint = "The guard to the Deputy Sheriffs office tells you that "
					+ "the only way he’ll let you pass through the door is if you win a "
					+ "game of blackjack against the other poker players in the room. "
					+ "He says it rather sarcastically because he thinks you will never beat them.";
		} else if ( puzzleID.equalsIgnoreCase("P05")) {
			puzzleHint = "There are a gang of ruffians blocking the entrance to the Saloon. "
					+ "The player knows there are too many for him to fight on his own, "
					+ "so he refuses to fight them. If the player goes to the Drug Store "
					+ "and buys the Golden Revolver, they will intimidate the ruffians before a "
					+ "fight even starts and the entrance to the Saloon will be open. ";
		} else if ( puzzleID.equalsIgnoreCase("P06")) {
			puzzleHint = "The player must pick a fight with the Gunslinger that is inside "
					+ "the Saloon. If they win the fight, the player will find a key that the "
					+ "Gunslinger dropped out of his pocket during the fight. This opens the door to 4G.";
		} else if ( puzzleID.equalsIgnoreCase("P07")) {
			puzzleHint = "The player must move the book that looks out of place in the bookshelf."
					+ " Once the player does this, the bookshelf will move aside to reveal a secret doorway "
					+ "into a room containing items for the player to use. ";
		} else if ( puzzleID.equalsIgnoreCase("P08")) {
			puzzleHint = "The player must get the key from the chest in room 4H and use it on D26 to enter room 4L. "
					+ "There they will find items to aid them on their journey. ";
		} else if ( puzzleID.equalsIgnoreCase("P09")) {
			puzzleHint = "The player must open up the book that looks out of place in the book shelf."
					+ " Once they do, they will see that the book is hollowed out and there is a small key "
					+ "inside that is shaped like a tree. ";
		} else if ( puzzleID.equalsIgnoreCase("P010")) {
			puzzleHint = "Once the player gets the key shaped like a tree from room 4I, they must go to the "
					+ "room adjacent and open the chest. In the chest they find a skeleton key that lets them "
					+ "access room 4M.  ";
		}
		return puzzleHint;
	}
	
	public void display(String story, String command) {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setLoadGameStory(displayStory, story);
		commandMenu.setLoadGameCommand(displayCommand, command);
	}
	
	public void puzzleP01() {
		CommandMenu commandMenu = new CommandMenu(map, icon, viewIcon, viewMap, displayStory, displayCommand);
		commandMenu.setNavigateMap(map, viewMap);
		commandMenu.setNavigateIcon(icon, viewIcon, 515, 250); 
		commandMenu.setLoadGameStory(displayStory, "The jail house is only for criminals...");
		commandMenu.setLoadGameCommand(displayCommand, "Action\n" + "1. Hint\n" + "2. Description\n" + "3. Back");
		
		System.out.println("Loading Puzzle P01 - Location Puzzles Class"); // Testing purpose
	}
	
	
}
