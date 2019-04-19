package eisti.handincap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;

import eisti.handincap.control.ControlMapLabel.ControlAction;

import java.util.Observable;

public class BuildingMap extends Observable implements Serializable{
	
	private int id;
	private String name;
	private String imgPath;
	private ArrayList<KeyPoint> keyPoints;
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public BuildingMap() {
		this.id = -1;
		this.name = "";
		this.imgPath = "test.jpg";
		this.keyPoints = new ArrayList<KeyPoint>();
	}
	
	public BuildingMap(int id, String name, String imgPath, ArrayList<KeyPoint> keyPoints) {
		this.id = id;
		this.name = name;
		this.imgPath = imgPath;
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
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public ArrayList<KeyPoint> getKeyPoints() {
		return keyPoints;
	}
	
	public void setKeyPoints(ArrayList<KeyPoint> keyPoints) {
		this.keyPoints = keyPoints;
		this.setChanged();
		this.notifyObservers(null);
	}
	
	public void addKeyPoint(KeyPoint keyPoint) {
		this.keyPoints.add(keyPoint);
		this.setChanged();
		this.notifyObservers(null);
	}
	
	public void addKeyPoint(int x, int y) {
		this.keyPoints.add(new KeyPoint(x,y));
		this.setChanged();
		this.notifyObservers(ControlAction.ADD_POINT);
	}
	
	public void removeKeyPoint(KeyPoint k) {
		this.keyPoints.remove(k);
		this.setChanged();
		this.notifyObservers(null);
	}
}
