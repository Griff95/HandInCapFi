package eisti.handincap.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class HandincapStaticMethods {
	private static final int MAPSCALE = 3; // On réduit la taille des images des plans par 3


	public static ImageIcon getScaledImageIcon(String imgPath) {
		ImageIcon myMap = new ImageIcon(imgPath);
		int imgH = myMap.getIconHeight()/MAPSCALE;
		int imgW = myMap.getIconWidth()/MAPSCALE;
		Image scaledImg = myMap.getImage()
				.getScaledInstance(imgW, imgH,  java.awt.Image.SCALE_SMOOTH);
		return (new ImageIcon(scaledImg));
	}

}
