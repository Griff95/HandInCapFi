package eisti.handincap.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eisti.handincap.utils.HandincapStaticMethods;

public class StartHandincapFi extends JFrame {
	private JPanel startPanel;
	
	public StartHandincapFi() {
		this.setTitle("HandincapFi - Start");
		//this.setSize(500,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		startPanel = new JPanel();
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("Que voulez-vous faire ? \n");
		//Font font = new Font("Arial",Font.BOLD,30);
		//label.setFont(font);
		JButton loadProject = new JButton ("Charger un ancien projet..");
		JButton newProject = new JButton ("Créer un nouveau projet..");
		label.setAlignmentX(CENTER_ALIGNMENT);
		JLabel bvn = new JLabel("Bienvenue sur HandincapFi");
		JLabel logo = new JLabel(HandincapStaticMethods.getScaledImageIcon("logo.jpg", 7));
		loadProject.setAlignmentX(CENTER_ALIGNMENT);
		newProject.setAlignmentX(CENTER_ALIGNMENT);
		logo.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentX(CENTER_ALIGNMENT);
		bvn.setAlignmentX(CENTER_ALIGNMENT);
		
		startPanel.add(Box.createVerticalStrut(5));
		startPanel.add(bvn);
		startPanel.add(Box.createVerticalStrut(5));
		startPanel.add(logo);
		startPanel.add(Box.createVerticalStrut(20));
		startPanel.add(Box.createHorizontalStrut(300));
		startPanel.add(label);
		startPanel.add(Box.createVerticalStrut(10));
		startPanel.add(loadProject);
		startPanel.add(Box.createVerticalStrut(5));
		startPanel.add(newProject);
		startPanel.add(Box.createVerticalStrut(5));

		
		this.add(startPanel);
		this.pack();
		
		loadProject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StartHandincapFi.this.dispose();
				LoadProjectFrame loadFrame = new LoadProjectFrame();
				
			}
			
		});
		
		newProject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StartHandincapFi.this.dispose();
				NewProjectFrame newProjectFrame = new NewProjectFrame();
				
			}
			
		});
	}
	
	public static void main (String[] args) {
		StartHandincapFi startFrame = new StartHandincapFi();		
	}
}
