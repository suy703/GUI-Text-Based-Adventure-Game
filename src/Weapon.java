/**
 * This class extends the Items class. It is used to create Weapon to be used by the player in order to attack and defeat monsters.
 * @author Alex
 *
 */
public class Weapon extends Items{
	/**
	 * The weapon's attack powwer.
	 */
	int attackPower;
	
	/**
	 * Constructor. Creates the weapon object and intializes its values.
	 * 
	 * @param id Item id
	 * @param name Item name
	 * @param description item description
	 * @param price Item Price
	 * @param attackPower Weapon attack power
	 */
	public Weapon(String id, String name, String description, int price, int attackPower) {
		super(id, name, description, price);
		this.attackPower = attackPower;
		canEquip = true;
	}
	
	/**
	 * Retrieves the weapons attack power
	 * @return
	 */
	public int getAttackPower() {
		return attackPower;
	}

	

	/**
	 * Changes the weapons attack power
	 * @param attackPower the weapon's new attack power
	 */
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
}
