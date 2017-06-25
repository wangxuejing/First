package decorator;


public class Testor {

	public static void main(String[] args) {
		AttackPower heroA=new HeroA();
		System.out.println("没有带装备前的输出："+heroA.getAttackPower());
		
		AttackPower heroAWeapon1=new Weapon1(heroA);
		System.out.println("带上武器1之后的输出："+heroAWeapon1.getAttackPower());
		
		AttackPower heroAWeapon2=new Weapon2(heroA);
		System.out.println("带上武器2之后的输出："+heroAWeapon2.getAttackPower());
		
		//在武器1的基础上再带上武器2
		AttackPower heroAWeapon3=new Weapon2(heroAWeapon1);
		System.out.println("带上武器1和武器2之后的输出："+heroAWeapon3.getAttackPower());
		 
		
		//除了我们使用上述的实现相同接口 调用装饰后的对象方法，我们还可以在装饰类中增加扩展方法，例如java中的BufferedReader装饰InputStream之后
		//新增了readline方法
				
	}
}
