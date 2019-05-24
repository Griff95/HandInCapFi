package eisti.handincap.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import eisti.handincap.KeyPoint;
import eisti.handincap.Link;
import eisti.handincap.Site;
import eisti.handincap.view.MapLabel;

public class AddLinkAction implements MouseListener {

	private KeyPoint pressed;
	private KeyPoint released;
	private Site abstraction;
	private MapLabel view;
	
	public AddLinkAction(Site abstraction, MapLabel map) {
		this.abstraction = abstraction;
		this.view = map;
	}


	public void mousePressed(MouseEvent e) {
		pressed = null;
		int x = e.getX();
		int y = e.getY();
		for (KeyPoint k : abstraction.getBuildingIndexed(view.getBatimentCourant()).getPoints()) {
			if (k.getZ() == view.getEtageCourant()) {
				int xk = k.getX();
				int yk = k.getY();
				if (x < xk + MapLabel.DIAMETRE_KEYPOINT/2 &&
						x > xk - MapLabel.DIAMETRE_KEYPOINT/2 &&
						y < yk + MapLabel.DIAMETRE_KEYPOINT/2 &&
						y > yk - MapLabel.DIAMETRE_KEYPOINT/2) {
					pressed = k;
					break;				
				}
			}
		}
	}


	public void mouseReleased(MouseEvent e) {
		released = null;
		int x = e.getX();
		int y = e.getY();
		for (KeyPoint k : abstraction.getBuildingIndexed(view.getBatimentCourant()).getPoints()) {
			if (k.getZ() == view.getEtageCourant()) {
				int xk = k.getX();
				int yk = k.getY();
				if (x < xk + MapLabel.DIAMETRE_KEYPOINT/2 &&
						x > xk - MapLabel.DIAMETRE_KEYPOINT/2 &&
						y < yk + MapLabel.DIAMETRE_KEYPOINT/2 &&
						y > yk - MapLabel.DIAMETRE_KEYPOINT/2) {
					released = k;
					break;				
				}
			}
		}
		if (released != null && pressed != null && !released.equals(pressed)) {
			// System.out.println("posé dans l'canapé !");
			// Ajout de liaison
			Link l = new Link(pressed, released);
			abstraction.getBuildingIndexed(view.getBatimentCourant()).getLiaisons().add(l);
		}

	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
