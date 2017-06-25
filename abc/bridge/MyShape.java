package bridge;

public class MyShape extends Shape {

	public MyShape(Painter b) {
		super(b);
		
	}

	@Override
	public void draw() {
		b.draw();

	}

}
