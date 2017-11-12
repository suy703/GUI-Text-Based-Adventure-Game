public class Monsters{
	
	String monsterID;
	String monsterName;
	String monsterDescription;
	String monsterRoomLocation;
	String itemDropped;
	int monsterProbability;
	int monsterHealth;
	int monsterAttackPower;

	

	public Monsters(String monsterID) {
		this.monsterID = monsterID;
	}

	public String getMonsterID() {
		return monsterID;
	}

	public void setMonsterID(String monsterID) {
		this.monsterID = monsterID;
	}

	public String getMonsterName() {
		if(monsterID.equalsIgnoreCase("M1")) {
			monsterName = "Pack of Coyotes";
		} else if (monsterID.equalsIgnoreCase("M2")) {
			monsterName = "Bear";
		} else if (monsterID.equalsIgnoreCase("M3")) {
			monsterName = "Mountain Man";
		} else if (monsterID.equalsIgnoreCase("M4")) {
			monsterName = "Bandit";
		}else if(monsterID.equalsIgnoreCase("M5")) {
			monsterName = "Woman of the Night";
		} else if (monsterID.equalsIgnoreCase("M6")) {
			monsterName = "Hench Man";
		} else if(monsterID.equalsIgnoreCase("M7")) {
			monsterName = "Deputy Sheriff";
		} else if (monsterID.equalsIgnoreCase("M8")) {
			monsterName = "Saloon Girl";
		} else if (monsterID.equalsIgnoreCase("M9")) {
			monsterName = "Deputy Sheriff #2";
		} else if (monsterID.equalsIgnoreCase("M10")) {
			monsterName = "Gunslinger";
		} else if (monsterID.equalsIgnoreCase("M11")) {
			monsterName = "Deputy Sheriff #3";
		} else if (monsterID.equalsIgnoreCase("M12")) {
			monsterName = "Sheriff";
		}
		return monsterName;
	}
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	public String getMonsterDescription() {
		if(monsterID.equalsIgnoreCase("M1")) {
			monsterDescription = "A pack of rabid looking coyotes. "
					+ "Their eyes are sunken in, and their fur matted; "
					+ "they look as if they haven’t eaten for weeks. ";
		} else if (monsterID.equalsIgnoreCase("M2")) {
			monsterDescription = "A giant grizzly bear, standing around 8 feet tall. "
					+ "It’s claws look as sharp as razors, and it gnashes its teeth "
					+ "at you menacingly";
		} else if (monsterID.equalsIgnoreCase("M3")) {
			monsterDescription = "A grizzly looking man with wild eyes and a beard "
					+ "down to his chest. He is wearing clothes made from animal "
					+ "skins and carries a club made of a bone of unknown origin in his hands. ";
		} else if (monsterID.equalsIgnoreCase("M4")) {
			monsterDescription = "A man of average stature, whose face is shrouded by a red bandana. "
					+ "His clothing is mismatched and ragged, but you can tell it is made from "
					+ "good quality materials. ";
		}else if(monsterID.equalsIgnoreCase("M5")) {
			monsterDescription = "A flashy looking woman with gaudy eyeshadow "
					+ "and bright red lipstick. A feather boa is wrapped around "
					+ "her shoulders and her eyes seem to hold a look of coy amusement.";
		} else if (monsterID.equalsIgnoreCase("M6")) {
			monsterDescription = "A basic grunt. This guy looks as if he hasn’t got a single "
					+ "original idea in his head. I guess that’s why he’s a henchman and not the boss.";
		} else if (monsterID.equalsIgnoreCase("M7")) {
			monsterDescription = "A muscular man with a large handlebar mustache. His narrow "
					+ "eyes and heavy breathing give you a feeling of unease.";
		} else if (monsterID.equalsIgnoreCase("M8")) {
			monsterDescription = "Probably the only woman around who is wearing pants. She’s drinking whisky "
					+ "straight out of the bottle and she definitely doesn’t look like someone you want to mess with.";
		} else if (monsterID.equalsIgnoreCase("M9")) {
			monsterDescription = "A muscular man with a large handlebar mustache. His narrow eyes and"
					+ " heavy breathing give you a feeling of unease.";
		} else if (monsterID.equalsIgnoreCase("M10")) {
			monsterDescription = "A tall man with a large cowboy hat on. His spurs clank ominously as"
					+ " he walks towards you. He fiddles with his guns which are holstered at his side.";
		} else if (monsterID.equalsIgnoreCase("M11")) {
			monsterDescription = "A muscular man with a large handlebar mustache. His narrow eyes and "
					+ "heavy breathing give you a feeling of unease.";
		} else if (monsterID.equalsIgnoreCase("M12")) {
			monsterDescription = "Tall and slender, and at first glance he might look frail. "
					+ "His eyes are an ice cold blue, and you can tell from his gaze "
					+ "that he is utterly unforgiving";
		}
		return monsterDescription;
	}
	public void setMonsterDescription(String monsterDescription) {
		this.monsterDescription = monsterDescription;
	}
	public String getMonsterRoomLocation() {
		return monsterRoomLocation;
	}
	public void setMonsterRoomLocation(String monsterRoomLocation) {
		this.monsterRoomLocation = monsterRoomLocation;
	}
	public String getItemDropped() {
		return itemDropped;
	}
	public void setItemDropped(String itemDropped) {
		this.itemDropped = itemDropped;
	}
	public int getMonsterProbability() {
		return monsterProbability;
	}
	public void setMonsterProbability(int monsterProbability) {
		this.monsterProbability = monsterProbability;
	}
	public int getMonsterHealth() {
		if(monsterID.equalsIgnoreCase("M1")) {
			monsterHealth = 4; // Pack of Coyotes
		} else if (monsterID.equalsIgnoreCase("M2")) {
			monsterHealth = 5; // Bear
		} else if (monsterID.equalsIgnoreCase("M3")) {
			monsterHealth = 6; // Mountain Man
		} else if (monsterID.equalsIgnoreCase("M4")) {
			monsterHealth = 6; // Bandit
		}else if(monsterID.equalsIgnoreCase("M5")) {
			monsterHealth = 5; // Woman of the Night
		} else if (monsterID.equalsIgnoreCase("M6")) {
			monsterHealth = 6; // Henchman
		} else if(monsterID.equalsIgnoreCase("M7")) {
			monsterHealth = 8; // Deputy Sheriff #1
		} else if (monsterID.equalsIgnoreCase("M8")) {
			monsterHealth = 7; // Saloon Girl
		} else if (monsterID.equalsIgnoreCase("M9")) {
			monsterHealth = 9; // Deputy Sheriff #2
		} else if (monsterID.equalsIgnoreCase("M10")) {
			monsterHealth = 9; // Gun Slinger
		} else if (monsterID.equalsIgnoreCase("M11")) {
			monsterHealth = 10; // Deputy Sheriff #3
		} else if (monsterID.equalsIgnoreCase("M12")) {
			monsterHealth = 12; // Sheriff
		}
		return monsterHealth;
	}
	public void setMonsterHealth(int monsterHealth) {
		this.monsterHealth = monsterHealth;
	}
	public int getMonsterAttackPower() {
		if(monsterID.equalsIgnoreCase("M1")) {
			monsterAttackPower = 4; // Pack of Coyotes
		} else if (monsterID.equalsIgnoreCase("M2")) {
			monsterAttackPower = 5; // Bear
		} else if (monsterID.equalsIgnoreCase("M3")) {
			monsterAttackPower = 6; // Mountain Man
		} else if (monsterID.equalsIgnoreCase("M4")) {
			monsterAttackPower = 6; // Bandit
		}else if(monsterID.equalsIgnoreCase("M5")) {
			monsterAttackPower = 2; // Woman of the Night
		} else if (monsterID.equalsIgnoreCase("M6")) {
			monsterAttackPower = 3; // Henchman
		} else if(monsterID.equalsIgnoreCase("M7")) {
			monsterAttackPower = 5; // Deputy Sheriff #1
		} else if (monsterID.equalsIgnoreCase("M8")) {
			monsterAttackPower = 4; // Saloon Girl
		} else if (monsterID.equalsIgnoreCase("M9")) {
			monsterAttackPower = 6; // Deputy Sheriff #2
		} else if (monsterID.equalsIgnoreCase("M10")) {
			monsterAttackPower = 5; // Gun Slinger
		} else if (monsterID.equalsIgnoreCase("M11")) {
			monsterAttackPower = 6; // Deputy Sheriff #3
		} else if (monsterID.equalsIgnoreCase("M12")) {
			monsterAttackPower = 8; // Sheriff
		}
		return monsterAttackPower;
	}
	public void setMonsterAttackPower(int monsterAttackPower) {
		this.monsterAttackPower = monsterAttackPower;
	}
	
	
	
	

}