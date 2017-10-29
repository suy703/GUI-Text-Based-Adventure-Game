
public class Puzzles {
	
	String puzzleID;
	String puzzleQuestion;
	String puzzleLocation;
	String puzzleHint;
	String puzzleDescription;
	
	public Puzzles() {
		
	}
	
	public Puzzles(String puzzleID, String puzzleLocation, String puzzleHint, String puzzleDescription) {
		super();
		this.puzzleID = puzzleID;
		this.puzzleLocation = puzzleLocation;
		this.puzzleHint = puzzleHint;
		this.puzzleDescription = puzzleDescription;
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
	
	public String getPuzzleLocation() {
		return puzzleLocation;
	}
	
	public void setPuzzleLocation(String puzzleLocation) {
		this.puzzleLocation = puzzleLocation;
	}
	
	public String getPuzzleHint() {
		return puzzleHint;
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
	
	

}
