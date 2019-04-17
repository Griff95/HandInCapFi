package eisti.handincap.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainPanel extends JPanel {

	JPanel panel = new JPanel();
	JLabel imgLabel;
	private Image myMap;

	public MainPanel() {


		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.magenta);
		Border b = BorderFactory.createEmptyBorder(5, 15, 5, 15);
		this.setBorder(b);
		
		ImageIcon myMap = new ImageIcon("test.jpg");
		int imgH = myMap.getIconHeight()/3;
		int imgW = myMap.getIconWidth()/3;
		Image scaledImg = myMap.getImage()
				.getScaledInstance(imgW, imgH,  java.awt.Image.SCALE_SMOOTH);
		myMap = new ImageIcon(scaledImg);
		imgLabel = new JLabel(myMap);
		imgLabel.setPreferredSize(new Dimension(imgW, imgH));
		panel.setBackground(Color.blue);
		panel.setPreferredSize(new Dimension(400,400));
		panel.add(imgLabel);

		JPanel container = new JPanel();
		container.add(new JButton("Définir la destination"));
		container.setBackground(Color.yellow);

		this.add(panel);
		this.add(container);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}