/**
 * This class extends the Items class and creates objects to be used by the player in order to alter their statistics
 * @author Alex
 *
 */
public class Potion extends Items{
	/**
	 * The type of potion. "H" denotes health, "A" denotes attack power, "G" denotes gold
	 */
	public String type;
	/**
	 * The strength of the potion
	 */
	public int strength;
	
	/**
	 * Constructor. Creates the potion object and initializes its values.
	 * @param id Item Id
	 * @param name Item Name
	 * @param description Item Description
	 * @param price Item Price
	 * @param type Potion type
	 * @param strength Potion Strength
	 */
	public Potion(String id, String name, String description, int price, String type, int strength) {
		super(id, name, description, price);
		this.type = type;
		this.strength = strength;
		canUse = true;
		
	}
	/**
	 * Retrieves the potions type
	 * @return The potion type
	 */

	public String getType() {
		return type;
	}
	
	/**
	 * Changes the potion type
	 * @param type The new Potion type
	 */

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retrieves the potions strength
	 * @return The potions strength
	 */
	public int getStrength() {
		return strength;
	}
	
	/**
	 * Changes the potion's strength
	 * @param strength The potion's new strength
	 */

	public void setStrength(int strength) {
		this.strength = strength;
	}

}
