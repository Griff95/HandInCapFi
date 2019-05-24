package eisti.handincap;

public class KeyPointDestination extends KeyPoint {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	public KeyPointDestination(int x, int y, int z, String nomDeSalle) {
		super(x, y, z);
		this.name = nomDeSalle;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}

}
