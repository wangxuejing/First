package birdge;

public class Main {
	
	public static void main(String[] args) {
		PaintingCost cost=new RectanglePaitingCost(new Rectangle(20.1, 30.5), 30.4);
		
		
		System.out.println("一个长方形要涂料花费："+cost.getCost());
		
	}

}
