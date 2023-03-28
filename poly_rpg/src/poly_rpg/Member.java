package poly_rpg;

class Member {
	private String name;
	private int y;
	private int x;
	private boolean party;
	
	public Member(String name, int y, int x, boolean party) {
		this.name = name;
		this.y = y;
		this.x = x;
		this.party = party;
	}
	public String getName() {
		return this.name;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public boolean getParty() {
		return this.party;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setParty(boolean party) {
		this.party = party;
	}

	public void setName(String name) {
		this.name = name;
	}
}
