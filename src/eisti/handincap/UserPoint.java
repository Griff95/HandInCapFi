package eisti.handincap;

public class UserPoint extends KeyPoint {
	private int orientation;
	
	public UserPoint(int x, int y, int z) {
		super(x, y, z);
		this.orientation = 0;	// Correspond à vers le haut par rapport à la carte
	}
}
