package eisti.handincap.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eisti.handincap.Building;
import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointUser;
import eisti.handincap.PathFinder;
import eisti.handincap.Site;

public class MainFrame extends JFrame {

	private Site abstraction;

	private MainPanel content;
	private MenuPanel menuPan;
	private JMenuBar menuBar;

	public MainFrame(Site s){
		this.abstraction = s ;
		KeyPoint.setAccumulateur(s.getBuildingIndexed(0).getPoints().size()-1);
		PathFinder pf = new PathFinder(new KeyPointUser(0, 0, 0), false);
		content = new MainPanel(abstraction, pf);
		menuPan = new MenuPanel(abstraction, pf);
		this.setTitle("HandinCapFi Application");
		this.setSize(1600, 850);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// init menu bar
		menuBar = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem newMenuItem = new JMenuItem("Nouveau..");
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.dispose();
				NewProjectFrame newProjectFrame = new NewProjectFrame(MainFrame.this);
			}

		});
		JMenuItem openMenuItem = new JMenuItem("Ouvrir..");
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.this.dispose();
				LoadProjectFrame loadFrame = new LoadProjectFrame(MainFrame.this);
			}

		});
		JMenuItem saveMenuItem = new JMenuItem("Sauvegarder..");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveProjectDialog d = new SaveProjectDialog(abstraction);
				d.setVisible(true);

			}

		});
		JMenuItem quitMenuItem = new JMenuItem("Quitter");
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
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
		this.pack();
	}

	public static void main (String[] args) {
		Site abstraction = new Site("EISTI");
		abstraction.addBuilding(new Building("Condorcet", "test.jpg"));
		MainFrame main = new MainFrame(abstraction);
	}
}
