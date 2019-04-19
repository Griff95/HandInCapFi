package eisti.handincap.control;

import java.awt.Dialog.ModalityType;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;

import eisti.handincap.Building;
import eisti.handincap.BuildingMap;
import eisti.handincap.KeyPoint;
import eisti.handincap.view.MapLabel;

public class ControlMapLabel implements MouseListener, Observer {
	
	public enum ControlAction {
			ADD_POINT,
			REMOVE_POINT;
	}

	private MapLabel view;
	private BuildingMap model;

	public ControlMapLabel(MapLabel v, BuildingMap m) {
		this.view = v;
		this.model = m;
	}
	
	
	
	// Act on View from Model changes
	@Override
	public void update(Observable o, Object arg) {
		if (arg == ControlAction.ADD_POINT) {
			ArrayList<KeyPoint> keyPoints = model.getKeyPoints();
			KeyPoint k = keyPoints.get(keyPoints.size()-1);
			view.getPoints().add(new Point(k.getX(), k.getY()));
		}
		
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
			int x = e.getX();
			int y = e.getY();
			JDialog dialog = new JDialog();
	        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        dialog.setLocation(e.getLocationOnScreen());
	        dialog.setVisible(true);
			boolean deleted = false;
			ArrayList<KeyPoint> keyPoints = model.getKeyPoints();
			for (KeyPoint k : keyPoints) {
				if (x >= k.getX()-MapLabel.DIAMETRE_KEYPOINT/2 &&
						x <= k.getX()+MapLabel.DIAMETRE_KEYPOINT/2 &&
						y >= k.getY()-MapLabel.DIAMETRE_KEYPOINT/2 &&
						y <= k.getY()+MapLabel.DIAMETRE_KEYPOINT/2) {
					//model.removeKeyPoint((int) e.getX(),(int) e.getY());
					deleted = true;
				}
			}
			if (!deleted) {
				model.addKeyPoint(e.getX(), e.getY());
			}
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
