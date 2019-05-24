package eisti.handincap.view;
//je ne sais plus si il n'y en a pas trop ici ?
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import eisti.handincap.Building;
import eisti.handincap.PathFinder;
import eisti.handincap.Site;
import eisti.handincap.control.SpeakUpAction;
import eisti.handincap.utils.HandincapStaticMethods;
import t2s.son.LecteurTexte;

public class MenuPanel extends JPanel {
	JLabel handincapLabel = new JLabel("Hand'in CapFi");
	JLabel logoLabel;

	//JLabel paramLabel;
	//JLabel parametreLabel = new JLabel("Parametres"); //petit texte parametres

	//declaration et imports des images
	ImageIcon son = new ImageIcon("images/son.png");
	ImageIcon passon = new ImageIcon("images/passon.png");
	ImageIcon micro = new ImageIcon("images/microphone.png");
	ImageIcon pasmicro = new ImageIcon("images/pasmicrophone.png");
	ImageIcon fleche = new ImageIcon("images/fleche.png");
	ImageIcon carte = new ImageIcon("images/carte.png");
	ImageIcon escalier = new ImageIcon("images/escaliers.png");
	ImageIcon ascenseur = new ImageIcon("images/ascenseur.png");

	public MenuPanel(Site abstraction, PathFinder pf) {
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

		//image des parametres
		/*ImageIcon param = new ImageIcon("images/parametres.png");
		int imgParamH = param.getIconHeight();
		int imgParamW = param.getIconWidth();
		Image scaledParamImg = param.getImage()
				.getScaledInstance(imgParamW, imgParamH,  java.awt.Image.SCALE_SMOOTH);
		param = new ImageIcon(scaledParamImg);
		paramLabel = new JLabel(param);
		paramLabel.setPreferredSize(new Dimension(imgParamW, imgParamH));*/


		//redimensionner la taille de l'image du bouton son
		int imgSonH = son.getIconHeight()/2;
		int imgSonW = son.getIconWidth()/2;
		Image scaledSonImg = son.getImage()
				.getScaledInstance(imgSonW, imgSonH,  java.awt.Image.SCALE_SMOOTH);
		son = new ImageIcon(scaledSonImg);

		//le boutton son
		JButton a = new JButton (son);
		a.setPressedIcon(son);
		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String vocal;
				if (abstraction.getBuildingIndexed(0).getPoints().isEmpty()) {
					System.out.println("ici1");
					vocal = "veuillez définir des points";
				}
				else if (abstraction.getBuildingIndexed(0).getLiaisons().isEmpty()) {
					System.out.println("ici2");
					vocal = "veuillez définir des liaisons";
				}
				else if (pf.getDest() == null) {
					System.out.println("ici3");
					vocal = "veuillez définir une destination";
				}
				else if (pf.getChemin().size() > 1) {
					System.out.println("ici4");
					vocal = "veuillez avancer au point suivant";
				}
				else {
					System.out.println("ici5");
					vocal = "vous etes arriver à destination";
				}
				SpeakUpAction speak = new SpeakUpAction(vocal);
				speak.run();

			}
		});



		//redimenssion carte
		int imgCarteH = (int) (carte.getIconHeight()/1.28);
		int imgCarteW = (int) (carte.getIconWidth()/1.28);
		Image scaledCarteImg = carte.getImage()
				.getScaledInstance(imgCarteW, imgCarteH,  java.awt.Image.SCALE_SMOOTH);
		carte = new ImageIcon(scaledCarteImg);
		//redimenssion fleche
		int imgFlecheH = fleche.getIconHeight()/2;
		int imgFlecheW = fleche.getIconWidth()/2;
		Image scaledFlecheImg = fleche.getImage()
				.getScaledInstance(imgFlecheW, imgFlecheH,  java.awt.Image.SCALE_SMOOTH);
		fleche = new ImageIcon(scaledFlecheImg);

		//le bouton plan
		JButton c = new JButton (fleche);
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BoussoleFrame d = new BoussoleFrame(pf.getUserPos(), pf.getNextKeyPointFromChemin());
				d.setVisible(true);
			}
		});


		//redimenssion ascenseur
		int imgAscenseurH = ascenseur.getIconHeight()/2;
		int imgAscenseurW = ascenseur.getIconWidth()/2;
		Image scaledAscenseurImg = ascenseur.getImage()
				.getScaledInstance(imgAscenseurW, imgAscenseurH,  java.awt.Image.SCALE_SMOOTH);
		ascenseur = new ImageIcon(scaledAscenseurImg);

		JButton d;
		//le bouton escaliers
		if (pf.isAscenseur()) {
			d = new JButton (ascenseur);
			d.setPressedIcon(escalier);	
		} else {
			d = new JButton (escalier);
			d.setPressedIcon(ascenseur);
		}
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (d.getIcon() == escalier) {
					d.setIcon(ascenseur);
					pf.setAscenseur(true);
				}
				else {
					d.setIcon(escalier);
					pf.setAscenseur(false);
				}
			}
		});

		handincapLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		handincapLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//paramLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//paramLabel.setToolTipText("Paramètres");
		//parametreLabel.setAlignmentX(Component.BOTTOM_ALIGNMENT);

		a.setAlignmentX(Component.CENTER_ALIGNMENT);
		a.setContentAreaFilled(false);
		a.setBorderPainted(false);
		/*
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		 */
		
		c.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.setContentAreaFilled(false);
		c.setBorderPainted(false);
		d.setAlignmentX(Component.CENTER_ALIGNMENT);
		d.setContentAreaFilled(false);
		d.setBorderPainted(false);



		this.add(logoLabel);
		this.add(Box.createVerticalStrut(10));
		this.add(handincapLabel);
		//this.add(paramLabel);
		//this.add(parametreLabel);
		//this.setBackground(Color.pink);
		this.add(Box.createVerticalStrut(50));
		this.add(a);
		this.add(Box.createVerticalStrut(10));
		//this.add(b);
		//this.add(Box.createVerticalStrut(10));
		this.add(c);
		this.add(Box.createVerticalStrut(10));
		this.add(d);
		this.add(Box.createVerticalStrut(10));
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}


}