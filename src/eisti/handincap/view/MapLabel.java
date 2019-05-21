package eisti.handincap.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import eisti.handincap.Building;
import eisti.handincap.KeyPoint;
import eisti.handincap.utils.HandincapStaticMethods;

public class MapLabel extends JLabel {

	public static final int DIAMETRE_KEYPOINT = 10;

	private int etageCourant;
	private Building abstraction;
	JPopupMenu popup;
	
	public MapLabel() {
		super();
		etageCourant = 0;
		this.setPreferredSize(new Dimension(500,500));
		this.setBackground(Color.orange);
		abstraction = null;
		initPopup();
	}

	public MapLabel(Building b) {
		super();
		//super(HandincapStaticMethods.getScaledImageIcon(b.getImgPath()));
		this.abstraction = b;
		etageCourant = 0;
		/*ControlMapLabel controlMapLabel = new ControlMapLabel(this, abstraction);
		this.addMouseListener(controlMapLabel);
		abstraction.addObserver(controlMapLabel);*/
		this.setBackground(Color.green);

		initPopup();
	}

	public void initPopup() {
		this.popup = new JPopupMenu();
		JMenuItem item;
		JMenu addPointFusionMenu = new JMenu("Point Fusion...");
		addPointFusionMenu.add(new JMenuItem("vers étage du dessus"));
		addPointFusionMenu.add(new JMenuItem("vers étage du dessous"));
		addPointFusionMenu.add(new JMenuItem("vers nouveau batiment (pont)"));
		
		JMenu addPointMenu = new JMenu("Ajouter un point repère...");
		addPointMenu.add(new JMenuItem("Point Intermédiaire"));
		addPointMenu.add(new JMenuItem("Point Destination"));
		addPointMenu.add(addPointFusionMenu);
		
		popup.add(item = new JMenuItem("Définir Position utilisateur"));
		popup.add(item = new JMenuItem("Définir l'orientation de l'utilisateur"));
		popup.add(addPointMenu);
		popup.add(item = new JMenuItem("Supprimer un point repère"));
		popup.add(item = new JMenuItem("Ajouter une liaison à partir de ce point"));
		//item.setVisible(false);
		
	    popup.setBorder(new BevelBorder(BevelBorder.RAISED));
	    
		addMouseListener(new MousePopupListener());
	}

	
	public int getEtageCourant() {
		return etageCourant;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (KeyPoint p : abstraction.getPoints()) {
			g.setColor(Color.blue);
			g.fillOval((int) p.getX()-DIAMETRE_KEYPOINT/2, (int) p.getY()-DIAMETRE_KEYPOINT/2, DIAMETRE_KEYPOINT, DIAMETRE_KEYPOINT);
		}

	}


	// Classe interne pour récupérer l'évènement d'un click sur la map et afficher la popup
	class MousePopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			checkPopup(e);
		}

		public void mouseClicked(MouseEvent e) {
			checkPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			checkPopup(e);
		}

		private void checkPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(MapLabel.this, e.getX(), e.getY());
			}
		}
	}

}
