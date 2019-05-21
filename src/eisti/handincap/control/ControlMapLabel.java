/*package eisti.handincap.control;

import java.awt.Dialog.ModalityType;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import javax.swing.JDialog;

import eisti.handincap.Building;
import eisti.handincap.KeyPoint;
import eisti.handincap.view.MapLabel;

public class ControlMapLabel implements MouseListener{

	public enum ControlAction {
		ADD_POINT,
		REMOVE_POINT;
	}

	private MapLabel view;
	private Building model;

	public ControlMapLabel(MapLabel v, Building m) {
		this.view = v;
		this.model = m;
	}



	// Act on View from Model changes
	@Override
	public void update(Observable o, Object arg) {		
		view.repaint();
	}




	// Act on Model from View event
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof MapLabel) {
			model.addPoint(e.getX(), e.getY(), view.getEtageCourant());

		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}*/
