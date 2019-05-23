package eisti.handincap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Building implements Serializable{
	private String nom;
	private ArrayList<String> etages;
	private ArrayList<KeyPoint> points;
	private ArrayList<Link> liaisons;
	
	// Make it Observable
	private final PropertyChangeSupport pcs;
	
	public Building (String n) {
		this.nom = n;
		this.etages = new ArrayList<String>();
		this.points = new ArrayList<KeyPoint>();
		this.liaisons = new ArrayList<Link>();
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public Building (String n, String imgPath) {
		this.nom = n;
		this.etages = new ArrayList<String>();
		this.etages.add(imgPath);
		this.points = new ArrayList<KeyPoint>();
		this.liaisons = new ArrayList<Link>();
		this.pcs = new PropertyChangeSupport(this);
	}

	public String getNom() {
		return nom;
	}
	
	public ArrayList<String> getEtages(){
		return etages;
	}

	public ArrayList<KeyPoint> getPoints() {
		return points;
	}
	
	public ArrayList<KeyPointDestination> getPointsDestination() {
		ArrayList<KeyPointDestination> pointsDestination = new ArrayList<KeyPointDestination>();
		for (KeyPoint p : points) {
			if (p instanceof KeyPointDestination) {
				pointsDestination.add((KeyPointDestination) p);
			}
		}
		return pointsDestination;
	}
	
	public void addPoint(KeyPoint p) {
		this.points.add(p);
		pcs.firePropertyChange("addPoint", null, null);
	}
	
	public void addPoint(int x, int y, int z) {
		this.points.add(new KeyPoint(x,y, z));
		pcs.firePropertyChange("addPoint", null, null);
	}
	
	public void removePoint(KeyPoint p) {
		this.points.remove(p);
		pcs.firePropertyChange("removePoint", null, null);
	}
	public void addEtageAtIndex(String imgPath, int i) {
		this.etages.add(i, imgPath);
		pcs.firePropertyChange("addEtage", null, null);
	}
	
	public void addEtage(String imgPath) {
		this.etages.add(imgPath);
		pcs.firePropertyChange("addEtage", null, null);
	}
	
	public void removeEtage(String imgPath) {
		this.points.remove(imgPath);
		pcs.firePropertyChange("removeEtage", null, null);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

	public ArrayList<Link> getLiaisons() {
		return liaisons;
	}

	public void setLiaisons(ArrayList<Link> liaisons) {
		this.liaisons = liaisons;
	}
	
	public String toString() {
		return getNom();
		
	}


}
