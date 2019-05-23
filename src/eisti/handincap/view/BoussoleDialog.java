package eisti.handincap.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JDialog;
import javax.swing.JLabel;

import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointUser;
import eisti.handincap.utils.HandincapStaticMethods;

public class BoussoleDialog extends JDialog {
	private KeyPointUser user;
	
	public BoussoleDialog(KeyPointUser user, KeyPoint dest) {
		super();
		this.user = user;
		
		this.setLayout(new FlowLayout());
		this.setSize(100, 100);
		this.setLocationRelativeTo(null);
		JLabel boussole;
		if (dest != null) {
			boussole = new JLabel(HandincapStaticMethods.getScaledImageIcon("images/boussole.jpg", 2)) {
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					int[] xs = computeXsForTriangle(dest);
					int[] ys = computeYsForTriangle(dest);
					g2d.setColor(Color.red);
					g2d.fillPolygon(xs, ys, 3);
					
					
				}
			};
			
		} else {
			boussole = new JLabel("Veuillez définir une destination");
		}
		this.add(boussole);
		this.pack();
	}
	
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
	}
}
