package eisti.handincap.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointDestination;
import eisti.handincap.KeyPointUser;
import eisti.handincap.Link;
import eisti.handincap.PathFinder;
import eisti.handincap.Site;
import eisti.handincap.control.AddKeyPointAction;
import eisti.handincap.utils.HandincapStaticMethods;
import eisti.handincap.view.AddLinkUpOrDownDialog.Type;
import eisti.handincap.control.AddLinkAction;
import eisti.handincap.control.RemoveKeyPointAction;

public class MapLabel extends JLabel {

	public static final int DIAMETRE_KEYPOINT = 15;

	private int batimentCourant;
	private int etageCourant;
	private Site abstraction;
	private PathFinder pf;
	JPopupMenu popup;

	/*public MapLabel() {
		super();
		etageCourant = 0;
		etageCourantImgPath = "";
		this.setPreferredSize(new Dimension(500,500));
		this.setBackground(Color.orange);
		this.setIcon(HandincapStaticMethods.getScaledImageIcon("logo.jpg"));

		abstraction = null;
		initPopup();
	}*/

	public MapLabel(Site b, PathFinder pf) {
		super(HandincapStaticMethods.getScaledImageIcon(b.getBuildingIndexed(0).getEtages().get(0)));
		//this.setPreferredSize(new Dimension(500,500));
		this.abstraction = b;
		this.pf = pf;
		etageCourant = 0;
		batimentCourant = 0;
		/*ControlMapLabel controlMapLabel = new ControlMapLabel(this, abstraction);
		this.addMouseListener(controlMapLabel);
		abstraction.addObserver(controlMapLabel);*/
		//this.setBackground(Color.green);


		this.addMouseListener(new MousePopupListener());
		this.addMouseListener(new AddLinkAction(abstraction, this));
	}

	public void initPopup(MouseEvent e) {
		this.popup = new JPopupMenu();
		JMenuItem item;
		AddKeyPointAction control;
		boolean clickOnPoint = false;
		int x = e.getX();
		int y = e.getY();

		for (KeyPoint k : abstraction.getBuildingIndexed(batimentCourant).getPoints()) {
			if (k.getZ() == etageCourant) {
				int xk = k.getX();
				int yk = k.getY();
				if (x < xk + DIAMETRE_KEYPOINT/2 &&
						x > xk - DIAMETRE_KEYPOINT/2 &&
						y < yk + DIAMETRE_KEYPOINT/2 &&
						y > yk - DIAMETRE_KEYPOINT/2) {
					JMenu addLiaison = new JMenu("Lier ce point à un point de...");
					addLiaison.add(item = new JMenuItem("l'étage du dessus"));
					item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							AddLinkUpOrDownDialog dialog = new AddLinkUpOrDownDialog(etageCourant, batimentCourant, abstraction, Type.UP, k);
							dialog.setVisible(true);
						}

					});
					addLiaison.add(item = new JMenuItem("l'étage du dessous"));
					item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							AddLinkUpOrDownDialog dialog = new AddLinkUpOrDownDialog(etageCourant, batimentCourant, abstraction, Type.DOWN, k);
							dialog.setVisible(true);
						}

					});
					popup.add(addLiaison);
					popup.add(item = new JMenuItem("Supprimer ce point repère"));
					item.addActionListener(new RemoveKeyPointAction(this, abstraction, k, batimentCourant));
					popup.addSeparator();
					break;				
				}
			}
		}


		JMenu addPointMenu = new JMenu("Ajouter un point repère...");

		addPointMenu.add(item = new JMenuItem("Point intermédiaire"));
		control = new AddKeyPointAction(AddKeyPointAction.Type.INTERM, e, this, abstraction, pf, etageCourant, batimentCourant);
		abstraction.getBuildingIndexed(batimentCourant).addPropertyChangeListener(control);
		item.addActionListener(control);

		addPointMenu.add(item = new JMenuItem("Point destination"));
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddKeyPointDestinationDialog d = new AddKeyPointDestinationDialog(MapLabel.this, abstraction, e);
				d.setVisible(true);				
			}

		});


		popup.add(item = new JMenuItem("Définir position utilisateur"));
		control = new AddKeyPointAction(AddKeyPointAction.Type.USER, e, this, abstraction, pf, etageCourant, batimentCourant);
		abstraction.getBuildingIndexed(batimentCourant).addPropertyChangeListener(control);
		item.addActionListener(control);

		popup.add(item = new JMenuItem("Définir l'orientation de l'utilisateur"));
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefineOrientationDialog d = new DefineOrientationDialog(pf);
				d.setVisible(true);
				
			}
			
		});
		popup.add(addPointMenu);

		popup.setBorder(new BevelBorder(BevelBorder.RAISED));
	}


	public int getBatimentCourant() {
		return batimentCourant;
	}

	public void setBatimentCourant(int batimentCourant) {
		this.batimentCourant = batimentCourant;
	}

	public int getEtageCourant() {
		return etageCourant;
	}

	public void setEtageCourant(int i) {
		this.etageCourant = i;
	}

	@Override
	public void paintComponent(Graphics g) {
		//System.out.println("paint map " + etageCourant);
		//System.out.println(abstraction.getBuildingIndexed(0).getEtages().size());
		super.paintComponent(g);
		if (!abstraction.getBuildings().isEmpty()) {
			if (!abstraction.getBuildingIndexed(batimentCourant).getEtages().isEmpty()) {
				// print img
				this.setIcon(HandincapStaticMethods.getScaledImageIcon(abstraction.getBuildingIndexed(batimentCourant).getEtages().get(etageCourant)));
				Graphics2D g2d = (Graphics2D) g;

				//print links
				g2d.setColor(Color.ORANGE);
				g2d.setStroke(new BasicStroke(5));
				for (Link l : abstraction.getBuildingIndexed(batimentCourant).getLiaisons()) {
					KeyPoint k1 = l.getK1();
					KeyPoint k2 = l.getK2();
					if (k1.getZ() == etageCourant && k2.getZ() == etageCourant) {
						g2d.drawLine(k1.getX(), k1.getY(), k2.getX(), k2.getY());						
					} else {
						if (k1.getZ() == etageCourant) {
							if (k2.getZ()+1 == etageCourant)
								g2d.drawString("To DOWN", k1.getX()+10, k1.getY());
							if (k2.getZ()-1 == etageCourant)
								g2d.drawString("To UP", k1.getX()+10, k1.getY()+15);
						} else if (k2.getZ() == etageCourant) {
							if (k1.getZ()+1 == etageCourant)
								g2d.drawString("To DOWN", k2.getX()+10, k2.getY());
							if (k1.getZ()-1 == etageCourant)
								g2d.drawString("To UP", k2.getX()+10, k2.getY()+15);
						}
					}
				}
				
				//print le plus court chemin
				g2d.setColor(Color.YELLOW);
				g2d.setStroke(new BasicStroke(7));
				for (Link l : pf.getChemin()) {
					KeyPoint k1 = l.getK1();
					KeyPoint k2 = l.getK2();
					if (k1.getZ() == etageCourant && k2.getZ() == etageCourant) {
						g2d.drawLine(k1.getX(), k1.getY(), k2.getX(), k2.getY());						
					}
				}

				// print keypoint
				for (KeyPoint p : abstraction.getBuildingIndexed(batimentCourant).getPoints()) {
					if (p.getZ() == etageCourant) {
						g.setColor(Color.gray);
						if (p instanceof KeyPointDestination) {
							g2d.setColor(Color.BLUE);
							g2d.drawString(((KeyPointDestination) p).getName(), p.getX(), p.getY()-15);
							g.setColor(Color.BLUE);
						}
						g.fillOval((int) p.getX()-DIAMETRE_KEYPOINT/2, (int) p.getY()-DIAMETRE_KEYPOINT/2, DIAMETRE_KEYPOINT, DIAMETRE_KEYPOINT);		
					}

				}
				// print user pos and orientation
				g.setColor(Color.RED);
				KeyPointUser p = pf.getUserPos();
				if (p.getZ() == etageCourant) {
					double orientation = p.getOrientation() * Math.PI / 180;
					double orientation2 = (90-p.getOrientation()) * Math.PI / 180;
					//System.out.println(orientation);
					int x = p.getX();
					int y = p.getY();
					//TODO C'est PAS BON !!!
					int[] xs = {  (int) (x-Math.cos(orientation)*DIAMETRE_KEYPOINT/2), (int) (x+Math.cos(orientation)*DIAMETRE_KEYPOINT/2), (int) (x+Math.cos(orientation2)*DIAMETRE_KEYPOINT*2) };
					int[] ys = { (int) (y+Math.sin(orientation)*DIAMETRE_KEYPOINT/2), (int) (y-Math.sin(orientation)*DIAMETRE_KEYPOINT/2), (int) (y+Math.sin(orientation2)*DIAMETRE_KEYPOINT*2) };
					g.fillPolygon(xs, ys, 3);
					g.fillOval((int) p.getX()-DIAMETRE_KEYPOINT/2, (int) p.getY()-DIAMETRE_KEYPOINT/2, DIAMETRE_KEYPOINT, DIAMETRE_KEYPOINT);
				}

			}
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
				initPopup(e);
				popup.show(MapLabel.this, e.getX(), e.getY());
			}
		}
	}

}
