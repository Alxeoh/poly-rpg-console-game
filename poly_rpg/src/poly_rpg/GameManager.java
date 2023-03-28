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
	private String name[] = {"Player", "Store", "ROAD", "Warrior", "Magician", "Healer", "Wolf", "BossDragon"};
	private final int SIZE = 7;

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
				} else if (idx == 1) {
					this.map.get(y).get(x).setName(name[4]);
					Member magician =  new Member(name[4],y,x,false);
					this.member.add(magician);
				} else if (idx == 2) {
					this.map.get(y).get(x).setName(name[5]);
					Member healer =  new Member(name[5],y,x,false);
					this.member.add(healer);
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
		for(int i = 0; i<party.size();i++) {
			System.out.printf("name : %s\n",party.get(i).getName());
		}
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
				} 
			}
			System.out.println();
		}
		printParty();
	}
	
	private void fight() {
		
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
				fight();
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
