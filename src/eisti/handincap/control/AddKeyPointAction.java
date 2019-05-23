package eisti.handincap.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;

import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointDestination;
import eisti.handincap.KeyPointUser;
import eisti.handincap.PathFinder;
import eisti.handincap.Site;
import eisti.handincap.view.AddKeyPointDestinationDialog;
import eisti.handincap.view.MapLabel;

public class AddKeyPointAction implements PropertyChangeListener, ActionListener {

	public enum Type {
		INTERM,
		DEST,
		FUSION,
		USER
	}

	private Type type;
	private MapLabel view;
	private Site abstraction;
	private int etageCourant;
	private int batimentCourant;
	private JTextField nomTextField;
	private MouseEvent event;
	private PathFinder pf;

	// Point autre que destination
	public AddKeyPointAction(Type type, MouseEvent e, MapLabel view, Site abstraction, PathFinder p, int etageCourant, int batimentCourant) {
		this.type = type;
		this.view = view;
		this.abstraction = abstraction;
		this.etageCourant = etageCourant;
		this.batimentCourant = batimentCourant;
		this.event = e;
		this.pf = p;
		this.nomTextField = null;
	}
	
	// Point destination
	public AddKeyPointAction(Type type, MouseEvent e, MapLabel view, Site abstraction, PathFinder p, int etageCourant, int batimentCourant, JTextField n) {
		this.type = type;
		this.view = view;
		this.abstraction = abstraction;
		this.etageCourant = etageCourant;
		this.batimentCourant = batimentCourant;
		this.event = e;
		this.pf = p;
		this.nomTextField = n;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		view.revalidate();
		view.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println(arg0.getSource());
		if (type == Type.INTERM) {
			System.out.println("ajout point intermédiaire dans le modèle");
			KeyPoint k = new KeyPoint(event.getX(), event.getY(), etageCourant);
			abstraction.getBuildingIndexed(batimentCourant).addPoint(k);
		} else if (type == Type.DEST) {
			System.out.println("ajout point destination dans le modèle");
			KeyPointDestination k = new KeyPointDestination(event.getX(), event.getY(), etageCourant, nomTextField.getText());
			abstraction.getBuildingIndexed(batimentCourant).addPoint(k);
		} else if (type == Type.USER) {
			System.out.println("modification du point utilisateur dans pathfinder");
			pf.setUserPos(event.getX(), event.getY(), etageCourant);
		}

	}

}
