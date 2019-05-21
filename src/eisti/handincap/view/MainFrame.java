package eisti.handincap.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eisti.handincap.Building;
import eisti.handincap.Site;

public class MainFrame extends JFrame {
	
	private Site abstraction;

	private MainPanel content;
	private MenuPanel menuPan;
	private JMenuBar menuBar;

	public MainFrame(Site s){
		this.abstraction = s ;
		content = new MainPanel(abstraction);
		menuPan = new MenuPanel(abstraction);
		this.setTitle("HandinCapFi Application");
		this.setSize(1024, 576);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem newMenuItem = new JMenuItem("Nouveau..");
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Le menuItem Nouveau.. est cliqué");
				
			}
			
		});
		JMenuItem openMenuItem = new JMenuItem("Ouvrir..");
		JMenuItem saveMenuItem = new JMenuItem("Sauvegarder..");
		JMenuItem quitMenuItem = new JMenuItem("Quitter");
		menuFichier.add(newMenuItem);
		menuFichier.add(openMenuItem);
		menuFichier.add(saveMenuItem);
		menuFichier.add(quitMenuItem);
		menuBar.add(menuFichier);
		this.setJMenuBar(menuBar);
		
		this.setLayout(new BorderLayout());
		this.getContentPane().add(content, BorderLayout.CENTER);	
		this.getContentPane().add(menuPan, BorderLayout.WEST);
		
		
		
		this.setVisible(true);
	}

	public static void main (String[] args) {
		Site project = new Site("EISTI");
		MainFrame main = new MainFrame(project);
	}
}
