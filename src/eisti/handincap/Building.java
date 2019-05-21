package eisti.handincap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class Building {
	private String nom;
	private ArrayList<String> etages;
	private ArrayList<KeyPoint> points;
	
	// Make it Observable
	private final PropertyChangeSupport pcs;
	
	public Building (String n) {
		this.nom = n;
		this.etages = new ArrayList<String>();
		this.points = new ArrayList<KeyPoint>();
		this.pcs = new PropertyChangeSupport(this);
	}

	public String getNom() {
		return nom;
	}
	
	public void addEtageLast(String imgPath) {
		etages.add(imgPath);
	}
	
	public void addEtageFirst(String imgPath) {
		etages.add(0, imgPath);
	}

	public ArrayList<KeyPoint> getPoints() {
		return points;
	}
	
	public void addPoint(KeyPoint p) {
		this.points.add(p);
		pcs.firePropertyChange("addPoints", null, null);
	}
	
	public void addPoint(int x, int y, int z) {
		this.points.add(new KeyPoint(x,y, z));
		pcs.firePropertyChange("addPoints", null, null);
	}
	
	public void removePoint(KeyPoint p) {
		this.points.remove(p);
		pcs.firePropertyChange("removePoints", null, null);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }
}
