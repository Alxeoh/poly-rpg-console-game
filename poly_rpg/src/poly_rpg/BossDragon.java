package poly_rpg;

import java.util.ArrayList;

public class BossDragon extends Wolf{
	public BossDragon(String name, int hp, int MapHp, int maxPower, boolean party) {
		super(name, MapHp, hp, maxPower, party);
	}
	public void fight(ArrayList<Unit> enemy, ArrayList<Member> party) {

	}
}
