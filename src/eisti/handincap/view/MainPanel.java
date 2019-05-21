package eisti.handincap.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eisti.handincap.Building;
import eisti.handincap.Site;

public class MainPanel extends JPanel {
	
	private Site abstraction;
	private MapLabel mapLabel;
	private JButton etageDessus;
	private JButton etageDessous;
	private JButton nouveauBatiment;
	
	private int etageCourant;

	public MainPanel(Site abstraction) {
		this.abstraction = abstraction;

		Border b = BorderFactory.createEmptyBorder(5, 15, 5, 15);
		this.setBorder(b);
		
		etageDessus = new JButton("Ajouter un Etage au dessus");
		etageDessous = new JButton("Ajouter un Etage au dessous");
		if (abstraction.getBuildings().isEmpty()) {
			mapLabel = new MapLabel();
			System.out.println("on est la hein");
			etageDessus.setEnabled(false);
			etageDessous.setEnabled(false);
		} else {
			mapLabel = new MapLabel(abstraction.getBuildingIndexed(0));
		}
		mapLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel controlMapPanel = new JPanel();
		nouveauBatiment = new JButton("Ajouter un Batiment");
		//etageDessus.addActionListener(new AddEtageAction(abstraction, DESSUS));
		//etageDessous.addActionListener(new AddEtageAction(abstraction, DESSOUS));
		//nouveauBatiment.addActionListener(new AddBatimentAction(abstraction));
		controlMapPanel.add(etageDessus);
		controlMapPanel.add(etageDessous);
		controlMapPanel.add(nouveauBatiment);
		
		JPanel destinationPanel = new JPanel();
		destinationPanel.add(new JLabel("Définir destination : "));
		JComboBox<String> combo;
		destinationPanel.add(combo = new JComboBox<String>());
		combo.addItem("Condorcet");
		destinationPanel.add(combo = new JComboBox<String>());
		combo.addItem("Numero Etage");
		destinationPanel.add(combo = new JComboBox<String>());
		combo.addItem("Nom Salle");
		destinationPanel.add(new JButton("Go"));
		
		
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(mapLabel);
		this.add(controlMapPanel);
		this.add(destinationPanel);
	
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}

