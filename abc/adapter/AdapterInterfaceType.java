package adapter;

/**
 * 接口适配器:   当InterfaceB中定义了过多接口，而我们并不想全部实现，就在接口与具体实现之间 多了抽象类InterfacBWrapper<p>
 * 
 * InterfacBWrapper 实现了InterfaceB 但并未有具体实现，当我们要实现某个具体接口或多过个接口时，只需要继承InterfacBWrapper，并重写对应接口即可<p>
 * 
 * 因此，该适配器其实完成了，多接口向单接口/任意数量接口的适配
 * 
 * @author wangliuyang
 *
 */
public class AdapterInterfaceType extends InterfaceBWrapper{

	@Override
	public void methodB1() {
		System.out.print("method b1");
		
	}

	
	
}
