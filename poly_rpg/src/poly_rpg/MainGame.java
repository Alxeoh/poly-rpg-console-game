package poly_rpg;

public class MainGame {
	public static void main(String[] args) {
		
		GameManager game = GameManager.getInstance();
		UserManager user = UserManager.getInstance();

		while(true) {
			user.printAll();
			user.printMene();
			game.printMenu();
			String sel = GameManager.inputString();
			if(sel.equals("1")) {
				user.addUser();
			} else if (sel.equals("2")) {
				user.deleteUser();
			} else if (sel.equals("3")) {
				user.login();
			} else if (sel.equals("4")) {
				user.logout();
			} else if (sel.equals("5")) {
				if(user.getLog().equals("logOut")) {
					System.out.println("[로그인 필요]\n");
				}
				else {
					game.play();
				}
			}
			
		}
		
	}

}
