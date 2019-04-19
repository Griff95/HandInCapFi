package eisti.handincap;

import java.util.ArrayList;

public class Building {
	private int id;
	private String name;
	private ArrayList<BuildingMap> maps;
	private int nbFloors;
	
	public Building() {
		this.id = -1 ;
		this.name = "";
		this.maps = new ArrayList<BuildingMap>();
		BuildingMap bm = new BuildingMap();
		maps.add(bm);
		int nbFloors = 0;
	}
	
	public Building(int id, String n, ArrayList<BuildingMap> maps, int nbFloors) {
		this.id = id;
		this.name = n;
		this.maps = maps;
		this.setNbFloors(nbFloors);
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
	
	public ArrayList<BuildingMap> getMaps() {
	 return maps;
	}
	
	public void setMaps(ArrayList maps) {
		this.maps = maps;
	}
	
	public void addBuildingMap(BuildingMap m) {
		this.maps.add(m);
	}
	
	public void removeBuildingMap(BuildingMap m) {
		this.maps.remove(m);
	}

	public int getNbFloors() {
		return nbFloors;
	}

	public void setNbFloors(int nbFloors) {
		this.nbFloors = nbFloors;
	}
}
