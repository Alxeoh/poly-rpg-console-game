package poly_rpg;

import java.util.ArrayList;

class Warrior extends Unit {
	public Warrior(String name, int hp, int MapHp, int maxPower, boolean party) {
		super(name, MapHp, hp, maxPower, party);
	}
	public void fight(ArrayList<Unit> enemy) {
		System.out.printf("[%s턴]\n", super.getName());
		System.out.print("1. 일반공격 2. 스킬사용");
		String sel = GameManager.inputString();
		int power = GameManager.rn.nextInt(super.getMaxPower()) + 1;
		if (sel.equals("1")) {
			nomalAtt(enemy, power);
		} else if (sel.equals("2")) {
			skill(enemy, power);
		}
	}

	private void skill(ArrayList<Unit> enemy, int power) {
		power *= 2;
		while (true) {
			int ran = GameManager.rn.nextInt(enemy.size()) + 1;
			if (enemy.get(ran).getHp() > 0) {
				if (enemy.get(ran).getHp() - power > 0) {
					enemy.get(ran).setHp(enemy.get(ran).getHp() - power);
				} else {
					enemy.get(ran).setHp(0);
					enemy.get(ran).setName("사망");
				}
				System.out.printf("[%s] 강타 스킬 발동!",super.getName());
				System.out.printf("[데미지 %d x2!]\n",power/2);
				break;
			} else {
				continue;
			}
		}
	}

	private void nomalAtt(ArrayList<Unit> enemy, int power) {
		while (true) {
			int ran = GameManager.rn.nextInt(enemy.size());
			System.out.println(ran);
			if (enemy.get(ran).getHp() > 0) {
				if (enemy.get(ran).getHp() - power > 0) {
					enemy.get(ran).setHp(enemy.get(ran).getHp() - power);
				} else {
					enemy.get(ran).setHp(0);
					enemy.get(ran).setName("사망");
				}
				System.out.printf("[%s] 공격!",super.getName());
				System.out.printf("[데미지 %d]\n",power);
				break;
			} else {
				continue;
			}
		}
	}
}
