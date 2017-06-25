package test;

public class FactoryImpl implements Factory<Test> {

	@Override
	public Test produce() {
		System.out.println("Test");
		return null;
	}

	

}
