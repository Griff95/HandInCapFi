package eisti.handincap;

public class KeyPoint {
	
	private int id;
	private String name;
	private int x;
	private int y;

	public KeyPoint(int id, int x, int y) {
		this.name = "toto";
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public KeyPoint(int x, int y) {
		this.name = "";
		this.id = -1;
		this.x = x;
		this.y =y;
	}

	public int getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
