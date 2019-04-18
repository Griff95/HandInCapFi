package eisti.handincap.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	
	private MapPanel mapPanel = new MapPanel();
	private JPanel container = new JPanel();

	public MainPanel() {


		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.magenta);
		Border b = BorderFactory.createEmptyBorder(5, 15, 5, 15);
		this.setBorder(b);

		

		container = new JPanel();
		container.setAlignmentX(CENTER_ALIGNMENT);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		JLabel posLabel = new JLabel("Position de l'utilisateur :		X = , Y = ");
		container.setBackground(Color.yellow);
		container.add(posLabel);
		container.add(new JButton("Definir la destination"));
		this.add(mapPanel);
		this.add(container);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}