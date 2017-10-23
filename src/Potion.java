
public class Potion extends Items{
	public String type;
	public int strength;
	
	public Potion(String id, String name, String description, int price, String type, int strength) {
		super(id, name, description, price);
		this.type = type;
		this.strength = strength;
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	

}
