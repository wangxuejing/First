package decorator;

public class Weapon2 implements AttackPower {
	private AttackPower a;
	private static final int ADD_ATTACK=100;
	public Weapon2(AttackPower a) {
		this.a=a;
	}
	
	@Override
	public int getAttackPower() {
		
		return a.getAttackPower()+ADD_ATTACK;
	}

}
