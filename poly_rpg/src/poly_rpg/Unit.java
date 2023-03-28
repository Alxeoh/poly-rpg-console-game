package poly_rpg;

class Unit {
	private String name;
	private int hp;
	private int MaxHp;
	private int maxPower;
	
	public Unit(String name, int hp, int MaxHp, int maxPower) {
		this.name = name;
		this.hp = hp;
		this.MaxHp = MaxHp;
		this.maxPower = maxPower;
	}
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
	}
	public int getMaxHp() {
		return this.MaxHp;
	}
	public int getMaxPower() {
		return this.maxPower;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setName(String name) {
		this.name = name;
	}
}
