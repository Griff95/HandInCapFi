package eisti.handincap.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

import t2s.son.LecteurTexte;

public class HandincapStaticMethods {
	private static final int MAPSCALE = 2; // On r�duit la taille des images des plans par 3


	public static ImageIcon getScaledImageIcon(String imgPath) {
		ImageIcon myMap = new ImageIcon(imgPath);
		int imgH = myMap.getIconHeight()/MAPSCALE;
		int imgW = myMap.getIconWidth()/MAPSCALE;
		Image scaledImg = myMap.getImage()
				.getScaledInstance(imgW, imgH,  java.awt.Image.SCALE_SMOOTH);
		return (new ImageIcon(scaledImg));
	}
	
	public static ImageIcon getScaledImageIcon(String imgPath, int scale) {
		ImageIcon myMap = new ImageIcon(imgPath);
		int imgH = myMap.getIconHeight()/scale;
		int imgW = myMap.getIconWidth()/scale;
		Image scaledImg = myMap.getImage()
				.getScaledInstance(imgW, imgH,  java.awt.Image.SCALE_SMOOTH);
		return (new ImageIcon(scaledImg));
	}
	
	public static void speak(String toVocal) {
		LecteurTexte lecteur = new LecteurTexte(toVocal);
		lecteur.play();
	}

}
