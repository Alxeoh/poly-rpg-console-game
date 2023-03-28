package poly_rpg;

class User {
	private String name;
	private String id;
	private String pw;
	private int gold;
	public User(String name, String id, String pw) {
		this.name = name;
		this.id = id;
		this.pw = pw;
	}
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	public String getPw() {
		return this.pw;
	}
	public int getGold() {
		return this.gold;
	}
	public void set(int gold) {
		this.gold = gold;
	}
	
}
