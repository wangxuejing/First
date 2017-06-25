package test.packagea;

import test.Factory;
import test.FactoryImpl;

public class FactoryImpl2 implements Factory {

	@Override
	public FactoryImpl produce() {
		System.out.println("FactoryImpl");
		return null;
	}

}
