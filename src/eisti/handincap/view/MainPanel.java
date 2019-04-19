package eisti.handincap.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eisti.handincap.Building;

public class MainPanel extends JPanel {
	
	private Building abstraction;
	private MapLabel mapLabel;
	private JPanel container = new JPanel();
	private JPanel mapPanel = new JPanel();
	private JLabel posLabel =  new JLabel("Position de l'utilisateur :		X = , Y = ");

	public MainPanel(Building abstraction) {
		this.abstraction = abstraction;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.magenta);
		Border b = BorderFactory.createEmptyBorder(5, 15, 5, 15);
		this.setBorder(b);

		

		container = new JPanel();
		container.setAlignmentX(CENTER_ALIGNMENT);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.setBackground(Color.yellow);
		container.add(posLabel);
		container.add(new JButton("Definir la destination"));
		mapLabel = new MapLabel(abstraction.getMaps().get(0));
		mapPanel.add(mapLabel);
		this.add(mapPanel);
		this.add(container);
	
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}

