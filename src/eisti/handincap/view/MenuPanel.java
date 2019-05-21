package eisti.handincap.view;
//je ne sais plus si il n'y en a pas trop ici ?
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import eisti.handincap.Building;
import eisti.handincap.Site;

public class MenuPanel extends JPanel {
	Site abstraction;
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

	public MenuPanel(Site abstraction) {
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
		a.setPressedIcon(passon);
		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (a.getIcon() == son) {
					a.setIcon(passon);
				}
				else {
					a.setIcon(son);
				}
			}
		});
				
		//redimenssion micro
		int imgMicroH = micro.getIconHeight()/2;
		int imgMicroW = micro.getIconWidth()/2;
		Image scaledMicroImg = micro.getImage()
				.getScaledInstance(imgMicroW, imgMicroH,  java.awt.Image.SCALE_SMOOTH);
		micro = new ImageIcon(scaledMicroImg);
		//redimension pas micro
		int imgPasMicroH = pasmicro.getIconHeight()/2;
		int imgPasMicroW = pasmicro.getIconWidth()/2;
		Image scaledPasMicroImg = pasmicro.getImage()
				.getScaledInstance(imgPasMicroW, imgPasMicroH,  java.awt.Image.SCALE_SMOOTH);
		pasmicro = new ImageIcon(scaledPasMicroImg);
		
		//le bouton micro
		JButton b = new JButton (micro);
		b.setPressedIcon(pasmicro);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (b.getIcon() == micro) {
					b.setIcon(pasmicro);
				}
				else {
					b.setIcon(micro);
				}
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
		JButton c = new JButton (carte);
		c.setPressedIcon(fleche);
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c.getIcon() == carte) {
					c.setIcon(fleche);
				}
				else {
					c.setIcon(carte);
				}
			}
		});
		
		
		//redimenssion ascenseur
		int imgAscenseurH = ascenseur.getIconHeight()/2;
		int imgAscenseurW = ascenseur.getIconWidth()/2;
		Image scaledAscenseurImg = ascenseur.getImage()
				.getScaledInstance(imgAscenseurW, imgAscenseurH,  java.awt.Image.SCALE_SMOOTH);
		ascenseur = new ImageIcon(scaledAscenseurImg);
		
		//le bouton escaliers
		JButton d = new JButton (escalier);
		d.setPressedIcon(ascenseur);
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (d.getIcon() == escalier) {
					d.setIcon(ascenseur);
				}
				else {
					d.setIcon(escalier);
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
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		c.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.setContentAreaFilled(false);
		c.setBorderPainted(false);
		d.setAlignmentX(Component.CENTER_ALIGNMENT);
		d.setContentAreaFilled(false);
		d.setBorderPainted(false);


		
		this.add(logoLabel);
		this.add(handincapLabel);
		//this.add(paramLabel);
		//this.add(parametreLabel);
		this.setBackground(Color.pink);
		this.add(a);
		this.add(b);
		this.add(c);
		this.add(d);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	

}