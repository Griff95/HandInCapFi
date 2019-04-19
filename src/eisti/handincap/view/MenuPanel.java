package eisti.handincap.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eisti.handincap.Building;

public class MenuPanel extends JPanel {
	Building abstraction;
	JLabel handincapLabel = new JLabel("Hand'in CapFi");
	JLabel logoLabel;

	public MenuPanel(Building abstraction) {
		this.abstraction = abstraction;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border bord = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(bord);
		
		ImageIcon logo = new ImageIcon("logo.jpg");
		int imgH = logo.getIconHeight()/8;
		int imgW = logo.getIconWidth()/8;
		Image scaledImg = logo.getImage()
				.getScaledInstance(imgW, imgH,  java.awt.Image.SCALE_SMOOTH);
		logo = new ImageIcon(scaledImg);
		logoLabel = new JLabel(logo);
		logoLabel.setPreferredSize(new Dimension(imgW, imgH));
		
		handincapLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		handincapLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(logoLabel);
		this.add(handincapLabel);

		this.setBackground(Color.pink);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	

}
