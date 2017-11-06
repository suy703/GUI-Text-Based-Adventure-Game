
public class Character {
	String name;
	int hp;
	int maxHp;
	int baseAtk;
	
	public Character(String name, int hp, int atk) {
		this.name = name;
		this.hp = hp;
		maxHp = hp;
		this.baseAtk = atk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getbaseAtk() {
		return baseAtk;
	}

	public void setAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}
	
	
	

}
