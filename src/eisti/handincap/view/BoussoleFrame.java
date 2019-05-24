package eisti.handincap.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointUser;

public class BoussoleFrame extends JFrame {
	//private KeyPointUser user;
	
	public BoussoleFrame(KeyPointUser user, KeyPoint dest) {
		super();
		
		int mecX = user.getX();
		int mecY = user.getY();

		int pointX = dest.getX();
		int pointY = dest.getY();
		int regardDuMec = user.getOrientation();
		
		this.setTitle("La Boussole");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(460,460));
		
		JPanel boussole;
			
			boussole = new JPanel() {
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					try {
						System.out.println("paintComponent");
						double hypo = Math.sqrt((mecX-pointX)*(mecX-pointX)+(mecY-pointY)*(mecY-pointY));
						double adj = Math.sqrt((mecY-pointY)*(mecY-pointY))*(pointY-mecY+0.00000000000000000001)/(Math.abs((mecY-pointY+0.00000000000000000001)));

						double angle = Math.acos(adj/hypo)*180/Math.PI*((pointX-mecX+0.00000000000000000001)/(Math.abs((mecX-pointX+0.00000000000000000001))));
						double angle2 = angle-regardDuMec;
						
						BufferedImage img = ImageIO.read(new File("images/compas2.png"));
					
						System.out.println("angle2 " + angle2);
						
						AffineTransform at = AffineTransform.getTranslateInstance(100,100);
						at.rotate(Math.toRadians(360-angle2),img.getWidth()/2,img.getHeight()/2);
						
						Graphics2D g2d = (Graphics2D) g;
						g2d.drawImage(img, at,null);						
					} catch (IOException e) {
					}
				}
			};
			this.getContentPane().add(boussole);
			this.pack();
	}
	/*
	public int[] computeXsForTriangle(KeyPoint destination) {
		int x = this.getWidth();
		int y = this.getHeight();
		double orientation = getAngleToDest(destination);
		double orientation2 = (Math.PI/2 - getAngleToDest(destination));
		int[] xs = {  (int) (x-Math.cos(orientation)*25), (int) (x+Math.cos(orientation)*25), (int) (x+Math.cos(orientation2)*150) };
		return xs;
	}
	
	public int[] computeYsForTriangle(KeyPoint destination) {
		int x = this.getWidth();
		int y = this.getHeight();
		double orientation = getAngleToDest(destination);
		double orientation2 = (Math.PI/2 - getAngleToDest(destination));
		int[] ys = { (int) (y+Math.sin(orientation)*25), (int) (y-Math.sin(orientation)*25), (int) (y+Math.sin(orientation2)*150) };
		return ys;
	}
	
	public double getAngleToDest(KeyPoint dest) {
		int x = user.getX();
		int y = user.getY();
		int dx = dest.getX() - x;
		int dy = dest.getY() - y;
		double alpha = Math.asin(dx/dy);
		alpha = alpha*180/Math.PI;
		return 90 - alpha;
	}*/
}
