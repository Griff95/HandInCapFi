package eisti.handincap.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame(String name) {
		super(name);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//URL de l'image
		String imgUrl="test.jpg";
		ImageIcon icon = new ImageIcon(imgUrl);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);  // transform it back
		//Création de JLable avec un alignement gauche
		JLabel jlabel = new JLabel(icon, JLabel.CENTER);

		//ajouter les deux JLabel a JFrame
		this.getContentPane().add(jlabel);
		this.validate();
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setVisible(true);
	}

	public static void main (String[] args) {
		MainFrame main = new MainFrame("HandincapFi");
	}
}
