package muzakkir.checkers;

public class Cell {
	private int x;
	private int y;
	private Type type;

	public Cell(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}