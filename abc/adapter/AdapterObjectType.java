package adapter;
/**
 * 完成InterfaceB和ClassA的适配,通过实现新接口，通过内部的ClassA实例来调用已有接口
 * @author wangliuyang
 */
public class AdapterObjectType implements InterfaceB{
	
	private ClassA A;
	
	public  AdapterObjectType(ClassA a) {
		this.A=a;
	}
	
	@Override
	public void methodB1() {
		A.methodA1();
	}

	@Override
	public void methodB2() {
			
	}

}
