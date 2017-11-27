import java.util.ArrayList;

import javafx.scene.text.Text;
/**
 * This class is used to create Player objects the represent the user's in game character. 
 * @author Alex
 *
 */
public class Player extends Character{
	/**
	 * List of items the player has.
	 */
	public ArrayList<Items> inventory;
	/**
	 * Weapon item that the player will use in battles.
	 */
	public Weapon equippedWeap;
	/**
	 * The total attack power of the player, including the strength of thr player's weapon.
	 */
	public int totalAtk;
	/**
	 * Additional attack power granted by potions.
	 */
	int bonusAtk;
	
	/**
	 * Constructor. Define's the player's attributes.
	 */
	public Player() {
		super("Texas Heck", 20, 1, 3);
		inventory = new ArrayList<Items>();
		equippedWeap = new Weapon("00", "-empty-", "Your bare hands", 0, 1);
		totalAtk = baseAtk + equippedWeap.attackPower;
		bonusAtk = 0;
		
	}
	
	
	/**
	 * Retrieves the player's inventory.
	 * @return The inventory list.
	 */
	public ArrayList<Items> getInventory() {
		return inventory;
	}

	/**
	 * Changes the inventory list of the player.
	 * @param inventory The new inventory list of the player.
	 */
	public void setInventory(ArrayList<Items> inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Retrieves the player's equipped weapon.
	 * @return The player's equipped weapon.
	 */
	public Weapon getEquippedWeap() {
		return equippedWeap;
	}

	/**
	 * Changes the charcter's equipped weapon.
	 * @param equippedWeap The new weapon
	 */
	public void setEquippedWeap(Weapon equippedWeap) {
		this.equippedWeap = equippedWeap;
	}
	
	/**
	 * Equips a weapon in the player's inventory.
	 * @param item The weapon to be equipped.
	 */
	public void equipWeapon(Items item) {
		if(item.canEquip)
			setEquippedWeap((Weapon)item);
		else
			System.out.println("Can't equip that item");
		totalAtk = baseAtk + equippedWeap.attackPower + bonusAtk; //Updates the totalAtk stat.
	}
	
	/**
	 * Unequip the character's current weapon. Change's it to a "bare hands" default weapon.
	 */
	public void unequipWeapon() {
		equippedWeap = new Weapon("00", "-empty-", "Your bare hands", 0, 1);
		totalAtk = baseAtk + equippedWeap.attackPower + bonusAtk; //Updates the totalAtk stat
	}
	
	/**
	 * Displays a textual description of the characters statistics.
	 * @return A string consisting of of the player's name, gold, hp, attack power, and equipped weapon, all displayed on seperate lines.
	 */
	public String displayPlayerStats() {
		return "Texas Heck\n" + gold + " gold\nHealth: " +hp + "/" + maxHp+ "\nAttack Power: " + totalAtk + "\nWeapon: " + equippedWeap.name;
	}
	
	/**
	 * Displays a textual list of the items in the play's inventory.
	 * @return A list of item names in the player's inventory. If the item is currently equipped, it displays "(EQUIPPED)"
	 *  next to the name. Each item appears on a seperate line.
	 */
	public String displayInventory() {
		String invDisplay= "Your Inventory";
		for(int i = 0; i < inventory.size(); i++ ) {
			if(isEquipped(inventory.get(i)))
				invDisplay += "\n(" + (i+1) + ") " + inventory.get(i).name + " (EQUIPPED)";	
			else
				invDisplay += "\n(" + (i+1) + ") " + inventory.get(i).name;	
		}
		return invDisplay;
	}
	
	/**
	 * Determines weather or not an item is equipped by the player.
	 * @param item The item to be compared to the player's equippedWeapon.
	 * @return A boolean value. Will return true if the item is equipped by the character, false if it is not.
	 */
	public boolean isEquipped(Items item) {
		if(item == equippedWeap)
			return true;
		else
			return false;
		
	}
	
	/**
	 * Removes the price of an item displayed at a store in game  from the player's gold and adds that item to the players inventory.
	 * @param i The item being purchased.
	 */
	public void buyItem(Items i, CommandMenu cm, Text prompt, Store s) {
		if(gold >= i.price && inventory.size() < 10) {
			gold -= i.price;
			inventory.add(i);
			cm.prompt(prompt, i.name.toUpperCase() + " PURCHASED");
			
		}else if(gold < i.price) {
			cm.prompt(prompt, "NOT ENOUGH GOLD");
		}else {
			cm.prompt(prompt, "YOU CAN'T CARRY ANY MORE");
		}
	}
		
	/**
	 * Removes an item from the player's inventory and add's the item's price to the player's gold.
	 * @param i The item being purchased.
	 */
	public void sellItem(Items i, CommandMenu cm, Text prompt, Store s) {
			if(isEquipped(i)){
				cm.prompt(prompt,"CAN'T SELL EQUIPPED ITEMS");
			}else {
				inventory.remove(i);
				gold += i.price;
				cm.prompt(prompt, i.name.toUpperCase() + " SOLD");
				}
		}
	
	/**
	 * Consumes an item and adjusts the player's stats according to the items strength and type (effect).
	 * @param i The potion being consumed.
	 */
	public void useItem(Potion i,  CommandMenu cm, Text prompt) {
		if(i.canUse && i.type.equals("H")) {
			/*if(hp ==maxHp) {
				cm.prompt(prompt, "ALREADY FULL HEALTH");
			}
			else if(i.strength+ hp> maxHp) {
				hp = maxHp;
				cm.prompt(prompt, "HP RESTORED");
			}else {
				cm.prompt(prompt, i.strength + " HP RESTORED");
				hp += i.strength;
			}*/
			inventory.remove(i);
		}else if(i.canUse && i.type.equals("A")){
			bonusAtk = i.strength;
			totalAtk = baseAtk + equippedWeap.attackPower + bonusAtk; //Updates the player's totalAtk
			cm.prompt(prompt, "ATTACK POWER INCREASED");
			inventory.remove(i);
			}
		else if(i.canUse && i.type.equals("G")){
			gold += i.strength;
			cm.prompt(prompt,i.strength+ " GOLD ADDED");
			inventory.remove(i);
			
		}
		else {
			System.out.println("You can't use that item");
		}
	}
	
	/**
	 * Removes an item from the players inventory.
	 * @param i The item being removed from the player's inventory.
	 */
	public void dropItem(Items i,CommandMenu cm, Text prompt) {
		inventory.remove(i);
		cm.prompt(prompt, i.name.toUpperCase() + " DROPPED");
		
	}
	
	/**
	 * Calculates the damage a player will do when attacking
	 * @return The total amount of damage to be dealt
	 */
	public int attackDamage() {
		int dmg;
		totalAtk = baseAtk + equippedWeap.attackPower + bonusAtk;
		dmg = totalAtk;
		bonusAtk = 0; //Potion Effect wears off after attack
		totalAtk = baseAtk + equippedWeap.attackPower + bonusAtk; //The player's totalAtk is updated after the effect of the potion wears off.
		return dmg;
	}
	
	public String sellItemCommands() {
		String display = "0. Back\n";
		for(int i = 0; i < inventory.size(); i++) {
			display +=  (i + 1) + ". Sell " + inventory.get(i).name + "\n";
		}
		return display;
		
	}

}