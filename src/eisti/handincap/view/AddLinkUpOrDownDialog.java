package eisti.handincap.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointDestination;
import eisti.handincap.Link;
import eisti.handincap.LinkUpOrDown;
import eisti.handincap.Site;
import eisti.handincap.utils.HandincapStaticMethods;

public class AddLinkUpOrDownDialog extends JDialog {

	public enum Type {
		UP,
		DOWN
	}
	


	private JLabel mapUpOrDown;
	private JComboBox<LinkUpOrDown.Type> typeCombo;
	private Site abstraction;
	private Type type;
	private KeyPoint k1;
	private int batC;
	private int etaC;

	public AddLinkUpOrDownDialog(int etaC, int batC, Site abstraction, Type type, KeyPoint k1) {
		super();
		this.batC = batC;
		this.etaC = etaC;
		this.abstraction = abstraction;
		this.type = type;
		this.k1 = k1;
		this.add(new JLabel("Selection du type de liaisons : "));
		typeCombo = new JComboBox<LinkUpOrDown.Type>(LinkUpOrDown.Type.values());
		this.add(typeCombo);
		this.add(new JLabel("Selection du point à lier : "));
		if (type == Type.UP) {
			if (abstraction.getBuildingIndexed(batC).getEtages().size()-1 > etaC) {
				int etaUpOrDown = etaC+1;
				mapUpOrDown = new MapLinkLabel(HandincapStaticMethods.getScaledImageIcon(abstraction.getBuildingIndexed(batC).getEtages().get(etaUpOrDown)));
				this.add(mapUpOrDown);
			} else {
				this.add(new JLabel("Impossible, il n'y a pas d'étage au dessus !"));
			}
		} else {
			if (0 < etaC) {
				int etaUpOrDown = etaC-1;
				mapUpOrDown = new MapLinkLabel(HandincapStaticMethods.getScaledImageIcon(abstraction.getBuildingIndexed(batC).getEtages().get(etaUpOrDown)));
				this.add(mapUpOrDown);
			} else {
				this.add(new JLabel("Impossible, il n'y a pas d'étage au dessous !"));

			}
		}
		this.setLocationRelativeTo(null);
		this.setSize(900,600);
		this.setLayout(new FlowLayout());
	}


	class MapLinkLabel extends JLabel {
		private int etaUpOrDown;

		public MapLinkLabel(ImageIcon scaledImageIcon) {
			super(scaledImageIcon);
			etaUpOrDown = (type == Type.UP)? etaC+1 : etaC-1;
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					for(KeyPoint k : abstraction.getBuildingIndexed(batC).getPoints()) {
						if (k.getZ() == etaUpOrDown) {
							int xk = k.getX();
							int yk = k.getY();
							if (x < xk + MapLabel.DIAMETRE_KEYPOINT/2 &&
									x > xk - MapLabel.DIAMETRE_KEYPOINT/2 &&
									y < yk + MapLabel.DIAMETRE_KEYPOINT/2 &&
									y > yk - MapLabel.DIAMETRE_KEYPOINT/2) {
								abstraction.getBuildingIndexed(batC).getLiaisons().add(new LinkUpOrDown(k1, k, (LinkUpOrDown.Type) typeCombo.getSelectedItem()));
								AddLinkUpOrDownDialog.this.dispose();
								break;
							}
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (!abstraction.getBuildings().isEmpty()) {
				if (!abstraction.getBuildingIndexed(batC).getEtages().isEmpty()) {
					// print img
					this.setIcon(HandincapStaticMethods.getScaledImageIcon(abstraction.getBuildingIndexed(batC).getEtages().get(etaUpOrDown)));
					Graphics2D g2d = (Graphics2D) g;

					//print links
					g2d.setColor(Color.ORANGE);
					g2d.setStroke(new BasicStroke(5));
					for (Link l : abstraction.getBuildingIndexed(batC).getLiaisons()) {
						KeyPoint k1 = l.getK1();
						KeyPoint k2 = l.getK2();
						if (k1.getZ() == etaUpOrDown && k2.getZ() == etaUpOrDown) {
							g2d.drawLine(k1.getX(), k1.getY(), k2.getX(), k2.getY());						
						} else {
							if (k1.getZ() == etaC) {
								if (k2.getZ()+1 == etaC)
									g2d.drawString("To UP", k2.getX()+10, k2.getY());
								if (k2.getZ()-1 == etaC)
									g2d.drawString("To DOWN", k2.getX()+10, k2.getY()+15);
							} else if (k2.getZ() == etaC) {
								if (k1.getZ()+1 == etaC)
									g2d.drawString("To UP", k1.getX()+10, k1.getY());
								if (k1.getZ()-1 == etaC)
									g2d.drawString("To DOWN", k1.getX()+10, k1.getY()+15);
							}
						}
					}

					// print keypoint
					for (KeyPoint p : abstraction.getBuildingIndexed(batC).getPoints()) {
						if (p.getZ() == etaUpOrDown) {
							g.setColor(Color.gray);
							if (p instanceof KeyPointDestination) {
								g2d.setColor(Color.BLUE);
								g2d.drawString(((KeyPointDestination) p).getName(), p.getX(), p.getY()-15);
								g.setColor(Color.BLUE);
							}
							g.fillOval((int) p.getX()-MapLabel.DIAMETRE_KEYPOINT/2, (int) p.getY()-MapLabel.DIAMETRE_KEYPOINT/2, MapLabel.DIAMETRE_KEYPOINT, MapLabel.DIAMETRE_KEYPOINT);		
						}

					}								
				}
			}
		}
	};
}

