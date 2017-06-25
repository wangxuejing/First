package proxy;

public class Service implements FunctionInterface{

	@Override
	public void methodA() {
		System.out.println("service methodA");
		
	}

	@Override
	public void methodB() {
		System.out.println("service methodB");
	}

}
