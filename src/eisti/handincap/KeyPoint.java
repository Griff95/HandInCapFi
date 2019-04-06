package eisti.handincap;

public class KeyPoint {
	
	private int id;
	private String name;
	private float lat;
	private float lon;

	public KeyPoint(int id, float lat, float lon) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public int getId() {
		return id;
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
	
	public float getLat() {
		return lat;
	}
	
	public void setLat(float lat) {
		this.lat = lat;
	}
	
	public float getLon() {
		return lon;
	}
	
	public void setLon(float lon) {
		this.lon = lon;
	}
	
}
