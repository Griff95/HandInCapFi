package eisti.handincap.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ImageObserver {
	MainPanel content = new MainPanel();

	public MainFrame(){
		this.setTitle("Bouton");
		this.setSize(1024, 576);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		this.setLayout(new BorderLayout());
		
		//Centre de l'application
		this.getContentPane().add(content, BorderLayout.CENTER);
		
		//Partie gauche (Settings) de l'application
		MenuPanel menuPan = new MenuPanel();
		
		this.getContentPane().add(menuPan, BorderLayout.WEST);
		
		
		this.setVisible(true);
	}

	public static void main (String[] args) {
		MainFrame main = new MainFrame();
	}
}
