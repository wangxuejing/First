package adapter;

/**
 * 适配器 完成InterfaceB和ClassA的适配
 * 
 * 已有类ClassA实现了大量代码，但又有新的接口InterfaceB标准，通过继承已有类，并实现新接口的方式进行适配（类适配器模式）
 * @author wangliuyang
 *
 */
public class AdapterClassType extends ClassA implements InterfaceB{

	@Override
	public void methodB1() {
		System.out.println("method B1");
		methodA1();		
	}

	@Override
	public void methodB2() {
		
		System.out.println("method B2");
	}
	
}
