package eisti.handincap;

import java.util.ArrayList;

public class Site {
	
	private String name;
	private ArrayList<Building> buildings;
	
	public Site(String n) {
		this.name = n;
		buildings = new ArrayList<Building>();
	}
	
	public void addBuilding(Building b) {
		buildings.add(b);
	}
	
	public void removeBuilding(Building b) {
		if (buildings.contains(b)) buildings.remove(b);
	}
	
	public Building getBuildingIndexed(int i) {
		return buildings.get(i);
	}
	
	public ArrayList<Building> getBuildings() {
		return buildings;
	}

}
