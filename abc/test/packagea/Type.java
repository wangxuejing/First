package test.packagea;

public enum Type {
	AS(1), BO(2);
	
	
	private int type;

	Type(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	@Override
	public String toString() {
		switch (type) {
		case 1:
			return "ASSSS";			
		case 2:
			return"BOO";
		default:
			return super.toString();
		}	
	}
	
}
