package eisti.handincap.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import eisti.handincap.Building;
import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointDestination;
import eisti.handincap.KeyPointUser;
import eisti.handincap.PathFinder;
import eisti.handincap.Site;
import eisti.handincap.control.AddEtageAction;
import eisti.handincap.utils.HandincapStaticMethods;

public class MainPanel extends JPanel implements PropertyChangeListener {

	private Site abstraction;
	private MapLabel mapLabel;
	private JButton etageDessus;
	private JButton etageDessous;
	private JButton newEtageDessus;
	private JButton newEtageDessous;
	private JComboBox<KeyPointDestination> comboDest;
	private JLabel title;




	public MainPanel(Site abstraction, PathFinder pf) {
		this.abstraction = abstraction;

		Border b = BorderFactory.createEmptyBorder(5, 15, 5, 15);
		this.setBorder(b);

		mapLabel = new MapLabel(abstraction, pf);
		mapLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		title = new JLabel(	abstraction.getName() + " - " +
				abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getNom() + " - Etage " +
				mapLabel.getEtageCourant());
		title.setAlignmentX(Component.CENTER_ALIGNMENT);


		etageDessous = new JButton("Voir étage du dessous");
		etageDessous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapLabel.setEtageCourant(mapLabel.getEtageCourant()-1);
			}
		});

		etageDessus = new JButton("Voir étage du dessus");
		etageDessus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapLabel.setEtageCourant(mapLabel.getEtageCourant()+1);
			}
		});

		newEtageDessus = new JButton("Ajouter un etage au dessus");
		newEtageDessus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddEtageDialog d = new AddEtageDialog(abstraction, mapLabel, AddEtageAction.Type.DESSUS);
				d.setVisible(true);
				repaint();
			}
		});
		newEtageDessous = new JButton("Ajouter un etage au dessous");
		newEtageDessous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddEtageDialog d = new AddEtageDialog(abstraction, mapLabel, AddEtageAction.Type.DESSOUS);
				d.setVisible(true);
				repaint();
			}
		});

		JPanel controlMapPanel = new JPanel();
		controlMapPanel.add(etageDessus);
		controlMapPanel.add(etageDessous);
		controlMapPanel.add(newEtageDessus);
		controlMapPanel.add(newEtageDessous);

		comboDest = new JComboBox<KeyPointDestination>();
		//comboDest.setPreferredSize(new Dimension(10,20));
		//comboDest.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXX");

		comboDest.setModel(new DefaultComboBoxModel(abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getPointsDestination().toArray()));
		abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).addPropertyChangeListener(this);

		//redimenssion micro
		ImageIcon micro = HandincapStaticMethods.getScaledImageIcon("images/microphone.png", 6);
		//redimension pas micro
		/*int imgPasMicroH = pasmicro.getIconHeight()/2;
				int imgPasMicroW = pasmicro.getIconWidth()/2;
				Image scaledPasMicroImg = pasmicro.getImage()
						.getScaledInstance(imgPasMicroW, imgPasMicroH,  java.awt.Image.SCALE_SMOOTH);
				pasmicro = new ImageIcon(scaledPasMicroImg);
		 */
		//le bouton micro
		JButton button = new JButton (micro);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// creer une dialog qui signifie l'écoute de la destination et bonne compréhension
				System.out.println("On veut des instructions vocales ici");
			}
		});

		JButton goDest = new JButton("Go");
		goDest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				pf.calculChemin(abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getPoints(),
								abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getLiaisons(),
								(KeyPointDestination) comboDest.getSelectedItem());
			}

		});


		// Définir la destination
		JPanel destinationPanel = new JPanel();
		destinationPanel.add(new JLabel("Définir destination (Batiment/Etage/Destination) : "));
		destinationPanel.add(comboDest);
		destinationPanel.add(button);
		destinationPanel.add(goDest);


		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setLayout(new BorderLayout());
		//JPanel centerPanel = new JPanel();
		//centerPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(title);
		this.add(mapLabel);
		//this.add(centerPanel, BorderLayout.CENTER);

		//JPanel southPanel = new JPanel();
		//southPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(controlMapPanel);
		this.add(destinationPanel);
		//this.add(southPanel, BorderLayout.SOUTH);
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int batiment = mapLabel.getBatimentCourant();
		int etage = mapLabel.getEtageCourant();
		title.setText(	abstraction.getName() + " - " +
				abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getNom() + " - Etage " +
				mapLabel.getEtageCourant());


		etageDessous.setEnabled(true);
		etageDessus.setEnabled(true);
		if(etage == abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getEtages().size()-1) {
			etageDessus.setEnabled(false);
		}
		if (etage == 0) {
			etageDessous.setEnabled(false);
		}
	}

	// C'est moche mais ça marche 3:)
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		comboDest.setModel(new DefaultComboBoxModel(abstraction.getBuildingIndexed(mapLabel.getBatimentCourant()).getPointsDestination().toArray()));
	}
}

