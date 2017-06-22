package birdge;

public class RectanglePaitingCost extends PaintingCost {
	
	public RectanglePaitingCost(Shape s,double perCost) {
		shape=s;
		price=perCost;
		
	}

	@Override
	public double getCost() {
		
		return shape.getArea()*price;
	}

}
