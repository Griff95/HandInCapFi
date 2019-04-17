package eisti.handincap.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BufferedImage myMap;

	public MainPanel() {
		try {
			myMap = ImageIO.read(new File("test.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(myMap, 0, 0, this);
		//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);    

	}
}