package test;

public abstract class Singleton<T> {
	
	
	protected abstract T create();
	
	public final T getInstance(){
		synchronized(this){
			if (mInstance==null) {
				mInstance= create();		
			}
			return mInstance;
		}
		
	}
	
	T mInstance;
}
