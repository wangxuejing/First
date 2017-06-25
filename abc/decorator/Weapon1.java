package decorator;

public class Weapon1 implements AttackPower{
	AttackPower a;
	private static final int addAttack=20;//weapon1对攻击力提升为20
	public Weapon1(AttackPower a) {
		this.a=a;
	}
	@Override
	public int getAttackPower() {
		
		return a.getAttackPower()+addAttack;
	}

}
