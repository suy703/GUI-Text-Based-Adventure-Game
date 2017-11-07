import java.util.ArrayList;

public class Player extends Character{
	public ArrayList<Items> inventory;
	public Weapon equippedWeap;
	public int totalAtk;
	
	public Player() {
		super("Texas Heck", 20, 1);
		inventory = new ArrayList<Items>();
		equippedWeap = new Weapon("00", "-empty-", "Your bare hands", 0, 1);
		totalAtk = baseAtk + equippedWeap.attackPower;
		
	}
	
	public ArrayList<Items> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Items> inventory) {
		this.inventory = inventory;
	}

	public Weapon getEquippedWeap() {
		return equippedWeap;
	}

	public void setEquippedWeap(Weapon equippedWeap) {
		this.equippedWeap = equippedWeap;
	}
	public void equipWeapon(Items item) {
		if(item.canEquip)
			equippedWeap = (Weapon)item;
		else
			System.out.println("Can't equip that item");
	}
	public void unequipWeapon() {
		equippedWeap = new Weapon("00", "-empty-", "Your bare hands", 0, 1);
	}
	
	public String displayPlayerStats() {
		return "Texas Heck\nHealth: " +hp + "/" + maxHp+ "\nAttack Power: " + totalAtk + "\nWeapon: " + equippedWeap.name;
	}
	
	public String displayInventory() {
		String invDisplay= "Your Inventory";
		for(int i = 0; i < inventory.size(); i++ ) {
			if(isEquipped(inventory.get(i)))
				invDisplay += "\n(" + (i+1) + ") " + inventory.get(0).name + " (EQUIPPED)";	
			else
				invDisplay += "\n(" + (i+1) + ") " + inventory.get(0).name;	
		}
		return invDisplay;
	}
	public boolean isEquipped(Items item) {
		if(item == equippedWeap)
			return true;
		else
			return false;
		
	}

}