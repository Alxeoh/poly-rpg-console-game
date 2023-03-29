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
	ArrayList<Wolf> wolves = new ArrayList<>();
	private String name[] = { "플레이어", "상점", "길", "전사", "마법사", "힐러", "늑대", "드래곤" };
	private final int SIZE = 7;
	private int gold;
	Player p = new Player("플레이어", 200, 200, 30);
	Warrior w = new Warrior("전사", 250, 250, 20);
	Magician m = new Magician("마법사", 150, 150, 50);
	Healer h = new Healer("힐러", 200, 200, 15);
	Wolf wolf = new Wolf("늑대", 100, 100, 20);
	BossDragon b = new BossDragon("드래곤", 500, 500, 30);

	private void setMap() {
		for (int i = 0; i < SIZE; i++) { // 길 셋팅
			ArrayList<Stage> temp = new ArrayList<>();
			for (int j = 0; j < SIZE; j++) {
				Stage temp2 = new Stage();
				temp2.setName("길");
				temp.add(temp2);
			}
			this.map.add(temp);
		}
		this.map.get(0).get(0).setName(name[1]);
		this.map.get(1).get(0).setName(name[0]);
		Member player = new Member(name[0], 1, 0, true);
		this.member.add(player);
		this.party.add(player);
		this.battle.add(p);
		int idx = 0;
		int wCount = 0;
		while (wCount < 7) {
			int y = rn.nextInt(this.SIZE);
			int x = rn.nextInt(this.SIZE);
			if (this.map.get(y).get(x).getName().equals(name[2])) {
				if (idx == 0) {
					this.map.get(y).get(x).setName(this.name[3]);
					Member warrior = new Member(name[3], y, x, false);
					this.member.add(warrior);
					this.battle.add(w);
				} else if (idx == 1) {
					this.map.get(y).get(x).setName(name[4]);
					Member magician = new Member(name[4], y, x, false);
					this.member.add(magician);
					this.battle.add(m);
				} else if (idx == 2) {
					this.map.get(y).get(x).setName(name[5]);
					Member healer = new Member(name[5], y, x, false);
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
		for (int i = 0; i < party.size(); i++) {
			System.out.printf("%s", party.get(i).getName());
			if (i < party.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	private void printGold() {
		System.out.println("골드   :" + this.gold +"G");
		System.out.println();
	}
	
	private void getGold(int gold) {
		this.gold = gold;
		System.out.printf("%dG 획득\n", gold);
	}


	private void printMap() {
		System.out.println("======= Adventure Field ========");
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				if(map.get(i).get(j).getName().contains("[사망]")) {
					System.out.print(" 👻 ");
				} else {
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
					} else if (map.get(i).get(j).getName().equals("골드")) {
						System.out.print(" 💰 ");
					} 
				}
			}
			System.out.println();
		}
		printParty();
		printGold();
	}
private void markDeath(String name) {
	int idxI = -1;
	int idxJ = -1;
	for(int i = 0; i< SIZE; i++) {
		for(int j = 0; j< SIZE; j++) {
			if(name.contains(map.get(i).get(j).getName())) {
				idxI = i;
				idxJ = j;
			}
		}
	}
	map.get(idxI).get(idxJ).setName(name);
}
	
	private boolean lifeCheck() {
		boolean check = false;
		for (int i = 0; i < battle.size(); i++) {
			if (battle.get(i).getHp() > 0) {
				check = true;
			} else {
				for (int j = 0; j < party.size(); j++) {
					if (party.get(j).getName().equals(battle.get(i).getName())) {
						party.get(j).setName(party.get(j).getName() + "[사망]");
						markDeath(party.get(j).getName());
					}
				}
			}
		}
		return check;
	}

	private void printHp(ArrayList<Wolf> wolves) {
		System.out.println("======== 플레이어 파티 ========");
		for (int i = 0; i < party.size(); i++) {
			for (int j = 0; j < battle.size(); j++) {
				if (party.get(i).getName().contains(battle.get(j).getName())) {
					System.out.printf("[%d. %s hp : %d/%d]\n\n", i + 1, battle.get(j).getName(), battle.get(j).getHp(),
							battle.get(j).getMaxHp());
				}
			}
		}
		System.out.println("========= 몬스터 파티 =========");
		for (int i = 0; i < wolves.size(); i++) {
			System.out.printf("[%d. %s hp : %d/%d]\n\n", i + 1, wolves.get(i).getName(), wolves.get(i).getHp(),
					wolves.get(i).getMaxHp());
		}
	}

	private ArrayList<Unit> partyUnit() {
		ArrayList<Unit> temp = new ArrayList<>();
		for (int i = 0; i < party.size(); i++) {
			for (int j = 0; j < battle.size(); j++) {
				if (party.get(i).getName().contains(battle.get(j).getName())) {
					temp.add(battle.get(j));
				}
			}
		}
		return temp;
	}

	private boolean enemyLifeCheck(ArrayList<Wolf> wolves) {
		boolean check = true;
		if (wolves.size() > 0) {
			int count = 0;
			for (int j = 0; j < wolves.size(); j++) {
				if (wolves.get(j).getName().contains("[사망]")) {
					count++;
				}
				if (count == wolves.size()) {
					check = false;
				}
			}
		}
		return check;
	}

	public void play() {
		setMap();
		while (true) {
			printMap();
			System.out.printf("                      w(↑)      \n                  a(←)s(↓)d(➞)\n");
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
			else {
				System.out.println("잘못입력하셨습니다.");
				continue;
			}
				
			// 예외처리
			if (y < 0 || y >= this.SIZE || x < 0 || x >= this.SIZE) {
				System.out.println("[접근불가]\n낭떠러지입니다.");
				continue;
			}


			if (!map.get(y).get(x).getName().equals(name[6]) && !map.get(y).get(x).getName().equals(name[1]) ) {
				int idx = -1;
				for (int i = 1; i < this.member.size(); i++) {
					if (map.get(y).get(x).getName().equals(this.member.get(i).getName())
							&& this.member.get(i).getParty() == false) {
						idx = i;
					}
				}
				if (idx != -1) {
					System.out.printf("['%s'파티 합류!]\n", this.member.get(idx).getName());
					this.party.add(this.member.get(idx));
					this.member.get(idx).setParty(true);
				}
				if (map.get(y).get(x).getName().equals("골드")) {
					int ran = rn.nextInt(3000) + 2000;
					getGold(ran);
				}
				map.get(party.get(party.size() - 1).getY()).get(party.get(party.size() - 1).getX()).setName(name[2]);
				for (int i = party.size() - 1; i > 0; i--) {
					party.get(i).setX(party.get(i - 1).getX());
					party.get(i).setY(party.get(i - 1).getY());
				}
				party.get(0).setY(y);
				party.get(0).setX(x);

				for (int i = 0; i < party.size(); i++) {
					map.get(party.get(i).getY()).get(party.get(i).getX()).setName(party.get(i).getName());
				}
				map.get(member.get(0).getY()).get(member.get(0).getX()).setName(name[2]);
				member.get(0).setY(y);
				member.get(0).setX(x);
				map.get(member.get(0).getY()).get(member.get(0).getX()).setName(party.get(0).getName());

			} else if (map.get(y).get(x).getName().equals(name[1])) {
				// 상점메뉴
			} else if (map.get(y).get(x).getName().equals(name[6])) {
				if (lifeCheck()) {
					System.out.println("[플레이어와 늑대의 전투가 시작되었습니다]\n");
					wolves = getWolves();
					while (true) {
						for (int i = 0; i < party.size(); i++) {
							if (enemyLifeCheck(wolves)) {
								if (party.get(i).getName().equals("플레이어")) {
									p.fight(wolves);
								} else if (party.get(i).getName().equals("전사")) {
									w.fight(wolves);
								} else if (party.get(i).getName().equals("마법사")) {
									m.fight(wolves);
								} else if (party.get(i).getName().equals("힐러")) {
									h.fight(wolves);
								}
							}
						}
						if (!enemyLifeCheck(wolves)) {
							System.out.println("========= Battle Finish ========");
							System.out.println("[승리]");
							this.map.get(y).get(x).setName("골드");
							break;
						}
						System.out.println("========= ENEMY ATTACK ========");
						for (int i = 0; i < wolves.size(); i++) {
							if (lifeCheck()) {
								ArrayList<Unit> temp = partyUnit();
								this.battle = wolves.get(i).fight(temp);
							}
						}
						if (!lifeCheck()) {
							System.out.println("========= Battle Finish ========");
							System.out.println("[패배]");
							break;
						}
						System.out.println();
						printBattleMap(party, wolves);
						printHp(wolves);

					}
				} else {
					System.out.println("[전투불능] 모든 인원이 사망하였습니다.");
				}
			} 
		}
	}

	private void printBattleMap(ArrayList<Member> party, ArrayList<Wolf> wolves) {
		int mapSize = party.size() > wolves.size() ? party.size() : wolves.size();
		String[][] map = new String[mapSize][3];
		System.out.println("========== Battle Field ==========");
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = " 🕳 ";
			}
		}
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < 3; j++) {
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
				if (map[i][j].equals("플레이어")) {
					System.out.print(" 💂 ");
				} else if (map[i][j].equals("마법사")) {
					System.out.print(" 🧙 ");
				} else if (map[i][j].equals("전사")) {
					System.out.print(" 🥷 ");
				} else if (map[i][j].equals("힐러")) {
					System.out.print(" 🧚 ");
				} else if (map[i][j].equals("늑대")) {
					System.out.print(" 🐺 ");
				} else if (map[i][j].equals("드래곤")) {
					System.out.print(" 🐉 ");
				} else if (map[i][j].equals("길")) {
					System.out.print(" 🕳 ");
				} else if (map[i][j].contains("[사망]")) {
					System.out.print(" 💀 ");
				} else {
					System.out.print(map[i][j]);
				}

			}
			System.out.println("");
		}
	}

	private ArrayList<Wolf> getWolves() {
		int ran = GameManager.rn.nextInt(4) + 1;
		System.out.printf("늑대 %d마리가 나타났다..!\n", ran);
		ArrayList<Wolf> wolves = new ArrayList<>();
		for (int i = 0; i < ran; i++) {
			Wolf temp = new Wolf("늑대", 100, 100, 20);
			wolves.add(temp);
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
