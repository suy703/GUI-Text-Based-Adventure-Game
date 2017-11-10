/**
 * This class creates Character objects that represent players or monsters in the game.
 * 
 * @author Alex
 */
public class Character {
	/**
	 * Character's name
	 */
	String name;
	/**
	 * Character's health points.
	 */
	int hp;
	/**
	 * Character's maximum health points.
	 */
	int maxHp;
	/**
	 * Character's base attack power.
	 */
	int baseAtk;
	/**
	 * Character's gold.
	 */
	int gold;
	
	/**
	 * Constructor. Defines the character's attributes.
	 * @param name Character's name;
	 * @param hp Character's health points.
	 * @param atk Character's attack power.
	 * @param gold Character's gold.
	 */
	public Character(String name, int hp, int atk, int gold) {
		this.name = name;
		this.hp = hp;
		maxHp = hp;
		this.baseAtk = atk;
		this.gold = gold;
	}
	
	/**
	 * Retrieves character's base attack.
	 * @return The value of baseAtk;
	 */
	public int getBaseAtk() {
		return baseAtk;
	}
	
	/**
	 * Changes the value of the character's base attack.
	 * @param baseAtk The new value of the charcter's base attack.
	 */
	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}
	
	/**
	 * Retrieves the charcter's gold. 
	 * @return The value of gold.
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * Changes the value of the character's gold.
	 * @param gold The new value the character's gold.
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * Retrieves the character's name.
	 * @return The charcter's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Changes the charcter's name.
	 * @param name The new character name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the charcter's health points value.
	 * @return The value of hp.
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Changes the charcter's health point value.
	 * @param hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Retrieves the value of the charcter's maximum health points.
	 * @return The value of maxHp
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * Changes the value of maxHp.
	 * @param maxHp The new value of maxHp.
	 */
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
}
