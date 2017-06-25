package bridge;
/**
 * 桥接模式
 * <p>目的在于抽象和实现之间的解耦，使二者可以独立变化。
 * 
 * 
 * @author wangliuyang
 *
 */
public abstract class Shape {
	Painter b;
	
	public Shape(Painter b) {
		this.b=b;
	}
	
	public abstract void draw();
		
}
