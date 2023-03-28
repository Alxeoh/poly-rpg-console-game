package poly_rpg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

class GameManager {
	static BufferedReader br = null;
	static BufferedWriter bw = null;
	static StringBuffer buffer = new StringBuffer();
	static Random rn = new Random();
	ArrayList<ArrayList<Stage>> map = new ArrayList<>();
	ArrayList<Member> member = new ArrayList<>();
	ArrayList<Member> party = new ArrayList<>();
	ArrayList<Unit> battle = new ArrayList<>();
	private String name[] = {"Player", "Store", "ROAD", "Warrior", "Magician", "Healer", "Wolf", "BossDragon"};
	private final int SIZE = 7;
	Player p = new Player("플레이어", 200, 200, 30, true);
	Warrior w = new Warrior("전사", 0, 250, 20, false);
	Magician m = new Magician("마법사", 150, 150, 50, false);
	Healer h = new Healer("힐러",200,200,15, false);
	Wolf wolf = new Wolf("늑대",100, 100, 20, false);
	BossDragon b = new BossDragon("드래곤",500,500,30, false);

	private void setMap() {
		for (int i = 0; i < SIZE; i++) { // 길 셋팅
			ArrayList<Stage> temp = new ArrayList<>();
			for (int j = 0; j < SIZE; j++) {
				Stage temp2 = new Stage();
				temp2.setName("ROAD");
				temp.add(temp2);
			}
			this.map.add(temp);
		}
		this.map.get(0).get(0).setName(name[1]);
		this.map.get(1).get(0).setName(name[0]);
		Member player = new Member(name[0], 1,0,true);
		this.member.add(player);
		this.battle.add(p);
		this.party.add(player);
		int idx = 0;
		int wCount = 0;
		while (wCount < 7) {
			int y = rn.nextInt(this.SIZE);
			int x = rn.nextInt(this.SIZE);
			if (this.map.get(y).get(x).getName().equals(name[2])) {
				if (idx == 0) {
					this.map.get(y).get(x).setName(this.name[3]);
					Member warrior =  new Member(name[3],y,x,false);
					this.member.add(warrior);
					this.battle.add(w);
				} else if (idx == 1) {
					this.map.get(y).get(x).setName(name[4]);
					Member magician =  new Member(name[4],y,x,false);
					this.member.add(magician);
					this.battle.add(m);
				} else if (idx == 2) {
					this.map.get(y).get(x).setName(name[5]);
					Member healer =  new Member(name[5],y,x,false);
					this.member.add(healer);
					this.battle.add(h);
				} else if (idx == 3) {
					this.map.get(y).get(x).setName(this.name[6]);
					wCount++;
					idx--;
				}
				idx++;
			} else {
				continue;
			}
		}

	}
	

//	🧙🏻‍♂️🧚🏻🥷🏻🐺🕳️🏆🐉
	private void printParty() {
		System.out.print("파티원 : ");
		for(int i = 0; i<party.size();i++) {
			System.out.printf("%s",party.get(i).getName());
			if(i<party.size()-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	private void printMap() {
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				if (map.get(i).get(j).getName().equals(name[0])) {
					System.out.print(" 💂 ");
				} else if (map.get(i).get(j).getName().equals(name[1])) {
					System.out.print(" 🏡 ");
				} else if (map.get(i).get(j).getName().equals(name[2])) {
					System.out.print(" 🕳 ");
				} else if (map.get(i).get(j).getName().equals(name[3])) {
					System.out.print(" 🥷 ");
				} else if (map.get(i).get(j).getName().equals(name[4])) {
					System.out.print(" 🧙 ");
				} else if (map.get(i).get(j).getName().equals(name[5])) {
					System.out.print(" 🧚 ");
				} else if (map.get(i).get(j).getName().equals(name[6])) {
					System.out.print(" 🐺 ");
				} else if (map.get(i).get(j).getName().equals(name[7])) {
					System.out.print(" 🐉 ");
				} else if (map.get(i).get(j).getName().contains("[사망]")) {
					System.out.println("💀");
				}
			}
			System.out.println();
		}
		printParty();
	}
	
	private boolean lifeCheck() {
		boolean check = false;
		for(int i = 0; i< battle.size();i++){
			if(battle.get(i).getHp()>0) {
				check = true;
			} else {
				for(int j = 0; j < party.size();j++) {
					if(party.get(j).getName().equals(battle.get(i).getName())) {
						party.get(j).setName("[사망]"+party.get(j).getName());
					}
				}
			}
		}
		return check;
	}
	
	public void play () {
		setMap();
		while (true) {
			printMap();
			System.out.printf("                  w(↑)      \n              a(←)s(↓)d(➞)\n");
			System.out.print("입력 : ");
			String dir = inputString();
			int y = this.member.get(0).getY();
			int x = this.member.get(0).getX();

			if (dir.equals("a"))
				x--;
			else if (dir.equals("s"))
				y++;
			else if (dir.equals("d"))
				x++;
			else if (dir.equals("w"))
				y--;
				
			// 예외처리
			if (y < 0 || y >= this.SIZE || x < 0 || x >= this.SIZE) {
				System.out.println("[접근불가]\n낭떠러지입니다.");
				continue;
			}
			if (map.get(y).get(x).getName().equals(name[2]) || (map.get(y).get(x).getName().equals(member.get(1).getName()) && member.get(1).getParty() == true) || (map.get(y).get(x).getName().equals(member.get(2).getName()) && member.get(2).getParty() == true ) || (map.get(y).get(x).getName().equals(member.get(3).getName()) && member.get(3).getParty() == true )) {
				System.out.println(party.size());
					map.get(party.get(party.size()-1).getY()).get(party.get(party.size()-1).getX()).setName(name[2]);
					for(int i = party.size()-1;i>0; i--) {
						party.get(i).setX(party.get(i-1).getX());
						party.get(i).setY(party.get(i-1).getY());
					}
					party.get(0).setY(y);
					party.get(0).setX(x);
					
					for(int i = 0; i < party.size(); i ++) {
						map.get(party.get(i).getY()).get(party.get(i).getX()).setName(party.get(i).getName());
					}
					map.get(member.get(0).getY()).get(member.get(0).getX()).setName(name[2]);
					member.get(0).setY(y);
					member.get(0).setX(x);
					map.get(member.get(0).getY()).get(member.get(0).getX()).setName(name[0]);
			
			} else if(map.get(y).get(x).getName().equals(name[1])) {
				// 상점메뉴
			} else if(map.get(y).get(x).getName().equals(name[6])) {
				System.out.println("[플레이어와 늑대의 전투가 시작되었습니다]\n");
				ArrayList<Unit> wolves = getWolves(wolf);
				printBattleMap(party, wolves);
				System.out.println(party.size());
				while(true) {
					if(lifeCheck()) {
						for(int i = 0; i<party.size();i++) {
							if(party.get(i).getName().equals("Player")) {
								p.fight(wolves);
							} else if(party.get(i).getName().equals(w.getName())) {
								w.fight(wolves);
							} else if(party.get(i).getName().equals(m.getName())) {
								m.fight(wolves);
							} else if(party.get(i).getName().equals(h.getName())) {
								h.fight(wolves);
							}
						}
					} else {
						System.out.println("[전투불능] 파티원 전원 사망");
					}
				}
			}
			int idx = -1;
			for(int i = 1; i< this.member.size();i++) {
				if(map.get(y).get(x).getName().equals(this.member.get(i).getName()) && this.member.get(i).getParty() == false) {
					idx = i;
				}
			}
			if(idx != -1) {
				System.out.printf("['%s'파티 합류!]\n",this.member.get(idx).getName());
				this.party.add(this.member.get(idx));
				this.member.get(idx).setParty(true);
			}
			
		}
	}

	private void printBattleMap(ArrayList<Member> party, ArrayList<Unit> wolves) {
		int mapSize = party.size()>wolves.size() ? party.size() : wolves.size();
		String[][] map = new String[mapSize][3];
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = "ROAD";
			}
		}
		for(int i = 0; i< mapSize; i++) {
			for(int j = 0; j<3;j++) {
				map[i][1] = "⏐";
			}
		}
		for (int i = 0; i < party.size(); i++) {
			map[i][0] = party.get(i).getName();
		}
		for (int i = 0; i < wolves.size(); i++) {
			map[i][2] = wolves.get(i).getName();
		}
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j].equals("Player")) {
					System.out.print(" 💂 ");
				} else if (map[i][j].equals("Magician")) {
					System.out.print(" 🧙 ");
				} else if (map[i][j].equals("Warrior")) {
					System.out.print(" 🥷 ");
				} else if (map[i][j].equals("Healer")) {
					System.out.print(" 🧚 ");
				} else if (map[i][j].equals("늑대")) {
					System.out.print(" 🐺 ");
				} else if (map[i][j].equals("드래곤")) {
					System.out.print(" 🐉 ");
				} else if (map[i][j].equals("ROAD")) {
					System.out.print(" 🕳 ");
				} else {
					System.out.print(map[i][j]);
				}
			}
			System.out.println("");
		}
	}
	
	
	private ArrayList<Unit> getWolves(Wolf enemy){
		int ran = GameManager.rn.nextInt(4) + 1;
		System.out.printf("%s %d마리가 나타났다..!\n", enemy.getName(), ran);
		ArrayList<Unit> wolves = new ArrayList<>();
		for (int i = 0; i < ran; i++) {
			wolves.add(enemy);
		}
		return wolves;
	}

	public void printMenu() {
		System.out.println("[5.플레이]");
	}

	private GameManager() {

	}

	private static GameManager instance = new GameManager();

	public static GameManager getInstance() {
		return instance;
	}

	public static String inputString() {

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			buffer.delete(0, buffer.length());
			buffer.append(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
