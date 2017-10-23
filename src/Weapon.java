public class Weapon extends Items{
	int attackPower;
	
	public Weapon(String id, String name, String description, int price, int attackPower) {
		super(id, name, description, price);
		this.attackPower = attackPower;
	}
	
	
	public int getAttackPower() {
		return attackPower;
	}


	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
}
