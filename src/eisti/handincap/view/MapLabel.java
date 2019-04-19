package eisti.handincap.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;

import eisti.handincap.Building;
import eisti.handincap.BuildingMap;
import eisti.handincap.control.ControlMapLabel;
import eisti.handincap.utils.HandincapStaticMethods;

public class MapLabel extends JLabel {
	
	public static final int DIAMETRE_KEYPOINT = 10;
	
	private BuildingMap abstraction;
	private ArrayList<Point> points;

	public MapLabel(BuildingMap b) {
		super(HandincapStaticMethods.getScaledImageIcon(b.getImgPath()));
		this.abstraction = b;
		this.points = new ArrayList<Point>();
		ControlMapLabel controlMapLabel = new ControlMapLabel(this, abstraction);
		this.addMouseListener(controlMapLabel);
		abstraction.addObserver(controlMapLabel);
		this.setBackground(Color.green);
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Point p : points) {
			g.setColor(Color.blue);
			g.fillOval((int) p.getX()-DIAMETRE_KEYPOINT/2, (int) p.getY()-DIAMETRE_KEYPOINT/2, DIAMETRE_KEYPOINT, DIAMETRE_KEYPOINT);
		}
		
	}
}
