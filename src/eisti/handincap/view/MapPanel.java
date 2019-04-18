package eisti.handincap.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private static final int MAPSCALE = 3;
	private JLabel mapLabel;

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon myMap = getScaledImageIcon("test.jpg", MAPSCALE);
		mapLabel = new JLabel(myMap);
		mapLabel.setPreferredSize(new Dimension(myMap.getIconWidth(), myMap.getIconHeight()));
		this.setBackground(Color.blue);
		this.setPreferredSize(new Dimension(800,450));
		this.add(mapLabel);
		
		mapLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("MapPanel est cliqué en x = " + arg0.getX() + " et Y = " + arg0.getY());
				//utiliser pattern observer (derprecated ?)
				//posLabel.setText("Position de l'utilisateur :		X = " + arg0.getX() + ", Y = " + arg0.getY());
				System.out.println(mapLabel.getGraphics().getColor().getRed() + " " + mapLabel.getGraphics().getColor().getGreen() + " " + mapLabel.getGraphics().getColor().getBlue());
				mapLabel.getGraphics().fillOval(arg0.getX()-5, arg0.getY()-5, 10, 10);
				
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
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public ImageIcon getScaledImageIcon(String imgPath, int div) {
		ImageIcon myMap = new ImageIcon(imgPath);
		int imgH = myMap.getIconHeight()/div;
		int imgW = myMap.getIconWidth()/div;
		Image scaledImg = myMap.getImage()
				.getScaledInstance(imgW, imgH,  java.awt.Image.SCALE_SMOOTH);
		return (new ImageIcon(scaledImg));
	}
}
