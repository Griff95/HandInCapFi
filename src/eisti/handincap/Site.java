package eisti.handincap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

public class Site implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Building> buildings;
	
	// Make it Observable
	private final PropertyChangeSupport pcs;
	
	public Site(String n) {
		this.name = n;
		this.buildings = new ArrayList<Building>();
		//Building b = new Building("Condorcet", "logo.jpg");
		//buildings.add(b);
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public String getName() {
		return name;
	}

	public void addBuilding(Building b) {
		buildings.add(b);
		pcs.firePropertyChange("addbuildings", false, true);
	}
	
	public void removeBuilding(Building b) {
		if (buildings.contains(b)) buildings.remove(b);
		pcs.firePropertyChange("rmbuildings", false, true);
	}
	
	public Building getBuildingIndexed(int i) {
		return buildings.get(i);
	}
	
	public ArrayList<Building> getBuildings() {
		return buildings;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("ozjvzijj");
        pcs.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

}
