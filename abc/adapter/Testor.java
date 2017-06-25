package adapter;

/**
 *  测试类
 * @author wangliuyang
 *
 */
public class Testor {
	
	public static void main(String[] args) {
		InterfaceB b=new AdapterClassType();
		b.methodB1();
		
		InterfaceB b2=new AdapterObjectType(new ClassA());
		b2.methodB1();
			
	}

}
