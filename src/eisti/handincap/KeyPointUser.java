package eisti.handincap;

public class KeyPointUser extends KeyPoint {
	private int orientation;
	
	public KeyPointUser(int x, int y, int z) {
		super(x, y, z);
		this.orientation = 0;	// Correspond à vers le haut par rapport à la carte
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
}
