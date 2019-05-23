package eisti.handincap.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import eisti.handincap.Building;
import eisti.handincap.KeyPoint;
import eisti.handincap.Site;
import eisti.handincap.view.MapLabel;

public class AddEtageAction implements ActionListener, PropertyChangeListener {
	
	public enum Type {
		DESSUS,
		DESSOUS
	}
	
	JDialog dia;
	String imgPath;
	Site abstraction;
	private MapLabel mapView;
	Type type;

	public AddEtageAction(MapLabel map, JDialog dialog, String imgPath, Site abstraction, Type type) {
		this.mapView = map;
		this.dia = dialog;
		this.imgPath = imgPath;
		this.abstraction = abstraction;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (type == Type.DESSUS) {
			for (KeyPoint k :abstraction.getBuildingIndexed(0).getPoints()) {
				if (k.getZ() > mapView.getEtageCourant()) k.setZ(k.getZ()+1);
			}
			abstraction.getBuildingIndexed(0).addEtageAtIndex(imgPath, mapView.getEtageCourant()+1);
		}
		if (type == Type.DESSOUS) {
			// On incrémente les coord z des points de tous les étages au dessus de l'étage courant (lui aussi inclu)
			for (KeyPoint k :abstraction.getBuildingIndexed(0).getPoints()) {
				if (k.getZ() >= mapView.getEtageCourant()) k.setZ(k.getZ()+1);
			}
			abstraction.getBuildingIndexed(0).addEtageAtIndex(imgPath, mapView.getEtageCourant());
		}

		dia.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		//System.out.println("propertyChange");
		if (arg0.getPropertyName().equals("addbuildings")) {
			//System.out.println("propertyChange - addBuilding");
			//mapView.setIcon(new ImageIcon("logo.png"));
			mapView.revalidate();
			mapView.repaint();
			
		}
	}

}
