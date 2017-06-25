package decorator;

public class HeroA implements AttackPower{
	int attack=10;//初始攻击

	@Override
	public int getAttackPower() {
		
		return attack;
	}

}
