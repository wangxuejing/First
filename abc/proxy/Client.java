package proxy;

public class Client {
	
	public static void main(String[] args) {
		FunctionInterface fuction=new Proxy();
		
		fuction.methodA();
		fuction.methodB();
	}

}
