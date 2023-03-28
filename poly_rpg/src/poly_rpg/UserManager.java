package poly_rpg;

import java.util.ArrayList;

class UserManager {
	
	static ArrayList<User> list = new ArrayList<>();
	private String log = "logOut";
	private UserManager() {
		
	}
	
	private static UserManager instance = new UserManager();
	
	public static UserManager getInstance() {
		return instance;
	}
	
	private boolean duplCheck(String id) {
		boolean check = false;
		for(int i = 0; i< list.size();i++) {
			if(list.get(i).getId().equals(id)) {
				check = true;
			}
		}
		return check;
	}
	
	public String getLog() {
		return this.log;
	}
	
	private int indexOf(String id) {
		int idx = -1;
		for(int i = 0; i< list.size();i++) {
			if(list.get(i).getId().equals(id)) {
				idx = i;
			}
		}
		return idx;
	}
	private boolean pwCheck(String id, String pw) {
		boolean check = false;
		int idx = indexOf(id);
		if(list.get(idx).getPw().equals(pw)) {
			check = true;
		}
		return check;
	}
	
	public void addUser() {
		System.out.print("회원가입 할 아이디 입력 : ");
		String id = GameManager.inputString();
		if(!duplCheck(id)) {
			System.out.print("회원가입 할 패스워드 입력 : ");
			String pw = GameManager.inputString();
			System.out.print("회원님 이름 입력 : ");
			String name = GameManager.inputString();
			User temp = new User(name, id, pw);
			list.add(temp);
		} else {
			System.out.println("중복된 아이디가 있습니다.");
		}
	}
	
	public void deleteUser() {
		System.out.print("탈퇴 할 아이디 입력 : ");
		String id = GameManager.inputString();
		if(duplCheck(id)) {
			System.out.print("해당 아이디의 패스워드를 입력하세요 : ");
			String pw = GameManager.inputString();
			if(pwCheck(id, pw)) {
				list.remove(indexOf(id));
				System.out.println("해당 아이디는 탈퇴되었습니다.");
			} else {
				System.out.println("패스워드를 다시 확인하세요.");
			}
		} else {
			System.out.println("등록되어있는 아이디가 아닙니다.");
		}
	}

	public void login() {
		if(this.log.equals("logOut")) {
			System.out.print("로그인 할 아이디 입력 : ");
			String id = GameManager.inputString();
			if(duplCheck(id)) {
				System.out.print("해당 아이디의 패스워드 입력 : ");
				String pw = GameManager.inputString();
				if(list.get(indexOf(id)).getPw().equals(pw)){
					System.out.printf("%s님 환영합니다.\n", list.get(indexOf(id)).getName());
					this.log = id;
					Player.userName = list.get(indexOf(log)).getName();
				} else {
					System.out.println("패스워드를 다시 확인하세요.");
				}
			} else {
				System.out.println("등록되어 있는 아이디가 아닙니다.");
			}
		} else {
			System.out.println("이미 로그인 상태입니다, 로그아웃을 해주세요.");
		}
	}
	
	public void logout() {
		if(!this.log.equals("logOut")) {
			System.out.printf("로그인 되어있는 아이디는 %s 입니다,\n해당아이디의 비밀번호를 입력하세요 : ", log);
			String pw = GameManager.inputString();
			if(list.get(indexOf(log)).getPw().equals(pw)) {
				System.out.println("해당 아이디는 로그아웃 되었습니다.");
				this.log = "logOut";
			} else {
				System.out.println("패스워드를 다시 확인하세요.");
			}
		} else {
			System.out.println("로그인 상태가 아닙니다.");
		}
	}
	
	public void printMene() {
		System.out.println("[1.회원가입]");
		System.out.println("[2.회원탈퇴]");
		System.out.println("[3.로그인]");
		System.out.println("[4.로그아웃]");
	}
	
	public void printAll() {
		for(int i = 0; i< list.size();i++) {
			System.out.printf("id : %s/ pw : %s / name: %s \n",list.get(i).getId(),list.get(i).getPw(),list.get(i).getName());
		}
	}
}
