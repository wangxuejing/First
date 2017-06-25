package bridge;
/**
 * 在该示例中可以看到用同一个抽象类的方法绘制出了不同的形状，
 * 
 * @author wangliuyang
 *
 */
public class Testor {
	public static void main(String[] args) {
		Shape circle=new MyShape(new CirclePainter());
		circle.draw();
		
		Shape rectangle=new MyShape(new RectanglePainter());		
		rectangle.draw();
	}

}
