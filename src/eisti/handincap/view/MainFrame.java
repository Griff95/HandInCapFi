package eisti.handincap.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import eisti.handincap.Building;

public class MainFrame extends JFrame {
	
	private Building abstraction;

	private MainPanel content;
	private MenuPanel menuPan;

	public MainFrame(Building b){
		this.abstraction = b ;
		content = new MainPanel(abstraction);
		menuPan = new MenuPanel(abstraction);
		this.setTitle("Bouton");
		this.setSize(1024, 576);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		this.getContentPane().add(content, BorderLayout.CENTER);	
		this.getContentPane().add(menuPan, BorderLayout.WEST);
		
		this.setVisible(true);
	}

	public static void main (String[] args) {
		MainFrame main = new MainFrame(new Building());
	}
}
