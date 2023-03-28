package poly_rpg;

import java.util.ArrayList;

class Wolf extends Unit {
	
	public Wolf(String name, int hp, int MaxHp, int maxPower) {
		super(name, hp, MaxHp, maxPower);
	}
	
	public ArrayList<Unit> fight(ArrayList<Unit> enemy) {
		if(super.getHp()>0) {
			int power = GameManager.rn.nextInt(super.getMaxPower()) + 1;
			int ran = GameManager.rn.nextInt(10);
			if (ran == 1) {
				skill(enemy, power);
			} else {
				nomalAtt(enemy, power);
			}
		}
		return enemy;
	}

	private void skill(ArrayList<Unit> enemy, int power) {
		power *= 2;
		while (true) {
			int ran = GameManager.rn.nextInt(enemy.size());
			if (enemy.get(ran).getHp() > 0) {
				if (enemy.get(ran).getHp() - power > 0) {
					enemy.get(ran).setHp(enemy.get(ran).getHp() - power);
				} else {
					enemy.get(ran).setHp(0);
				}
				System.out.printf("[%s] 물어뜯기 스킬 발동!",super.getName());
				System.out.printf("[%s 데미지 %d x2!]\n",enemy.get(ran).getName(),power/2);
				break;
			} else {
				continue;
			}
		}
	}

	private void nomalAtt(ArrayList<Unit> enemy, int power) {
		while (true) {
			int ran = GameManager.rn.nextInt(enemy.size());
			if (enemy.get(ran).getHp() > 0) {
				if (enemy.get(ran).getHp() - power > 0) {
					enemy.get(ran).setHp(enemy.get(ran).getHp() - power);
				} else {
					enemy.get(ran).setHp(0);
				}
				System.out.printf("[%s] 공격!",super.getName());
				System.out.printf("[%s 데미지 %d]\n",enemy.get(ran).getName(),power);
				break;
			} else {
				continue;
			}
		}
	}
}
