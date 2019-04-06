package eisti.handincap;

import java.util.ArrayList;

public class BuildingMap {
	
	private int id;
	private String name;
	private ArrayList<KeyPoint> keyPoints;
	
	public BuildingMap(int id, String name, ArrayList<KeyPoint> keyPoints) {
		this.id = id;
		this.name = name;
		this.keyPoints = keyPoints;
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
	
	public ArrayList<KeyPoint> getKeyPoints() {
		return keyPoints;
	}
	
	public void setKeyPoints(ArrayList<KeyPoint> keyPoints) {
		this.keyPoints = keyPoints;
	}
	
	public void addKeyPoints(KeyPoint keyPoint) {
		this.keyPoints.add(keyPoint);
	}
	
	public void removeKeyPoint(KeyPoint k) {
		this.keyPoints.remove(k);
	}
	
}
