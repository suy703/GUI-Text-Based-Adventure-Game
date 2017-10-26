
public class Monsters {
	String monsterName;
	String monsterDescription;
	String monsterLocation;
	double monsterProbability;
	int monsterHealth;
	int monsterAttackPower;
	String itemDropped;
	public Monsters(String monsterName, String monsterDescription, String monsterLocation, double monsterProbability,
			int monsterHealth, int monsterAttackPower, String itemDropped) {
		super();
		this.monsterName = monsterName;
		this.monsterDescription = monsterDescription;
		this.monsterLocation = monsterLocation;
		this.monsterProbability = monsterProbability;
		this.monsterHealth = monsterHealth;
		this.monsterAttackPower = monsterAttackPower;
		this.itemDropped = itemDropped;
	}
	public String getMonsterName() {
		return monsterName;
	}
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	public String getMonsterDescription() {
		return monsterDescription;
	}
	public void setMonsterDescription(String monsterDescription) {
		this.monsterDescription = monsterDescription;
	}
	public String getMonsterLocation() {
		return monsterLocation;
	}
	public void setMonsterLocation(String monsterLocation) {
		this.monsterLocation = monsterLocation;
	}
	public double getMonsterProbability() {
		return monsterProbability;
	}
	public void setMonsterProbability(double monsterProbability) {
		this.monsterProbability = monsterProbability;
	}
	public int getMonsterHealth() {
		return monsterHealth;
	}
	public void setMonsterHealth(int monsterHealth) {
		this.monsterHealth = monsterHealth;
	}
	public int getMonsterAttackPower() {
		return monsterAttackPower;
	}
	public void setMonsterAttackPower(int monsterAttackPower) {
		this.monsterAttackPower = monsterAttackPower;
	}
	public String getItemDropped() {
		return itemDropped;
	}
	public void setItemDropped(String itemDropped) {
		this.itemDropped = itemDropped;
	}
	
	
	

}
