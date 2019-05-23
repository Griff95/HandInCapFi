package eisti.handincap.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eisti.handincap.Building;
import eisti.handincap.Site;
import eisti.handincap.control.AddEtageAction;

public class NewProjectFrame extends JFrame {

	private JPanel newProjectPanel;
	private String firstMapPath = "";
	
	public NewProjectFrame() {
		this.setTitle("HandincapFi - Nouveau Projet");
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel label = new JLabel("Nouveau Projet");
		Font font = new Font("Arial",Font.BOLD,30);
		label.setFont(font);
		label.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel nomProjetPanel = new JPanel();
		JLabel nomProjetLabel = new JLabel("Nom du Projet : ");
		JTextField nomProjetField = new JTextField(7);
		nomProjetPanel.add(nomProjetLabel);
		nomProjetPanel.add(nomProjetField);
		
		JPanel nomBatimentPanel = new JPanel();
		JLabel nomBatimentLabel = new JLabel("Nom du Batiment : ");
		JTextField nomBatimentField = new JTextField(7);
		nomBatimentPanel.add(nomBatimentLabel);
		nomBatimentPanel.add(nomBatimentField);
		
		JPanel firstMapPanel = new JPanel();
		JLabel firstMapLabel = new JLabel("Image du premier étage : ");
		JButton browse = new JButton("Parcourir..");
		
		JLabel selectedFile = new JLabel("");
		firstMapPanel.add(firstMapLabel);
		firstMapPanel.add(browse);
		firstMapPanel.add(selectedFile);
		
		JButton ok = new JButton("OK");
		ok.setEnabled(false);
		
		newProjectPanel = new JPanel();
		newProjectPanel.setLayout(new BoxLayout(newProjectPanel, BoxLayout.PAGE_AXIS));
		newProjectPanel.add(label);
		newProjectPanel.add(nomProjetPanel);
		newProjectPanel.add(nomBatimentPanel);
		newProjectPanel.add(firstMapPanel);
		newProjectPanel.add(ok);
		
		// Listeners
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Site abstraction = new Site(nomProjetField.getText());
				abstraction.addBuilding(new Building(nomBatimentField.getText(), firstMapPath));
				NewProjectFrame.this.dispose();
				MainFrame main = new MainFrame(abstraction);
			}
			
		});
		browse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					firstMapPath = fc.getSelectedFile().getPath();
					selectedFile.setText(firstMapPath);
					ok.setEnabled(true);
					NewProjectFrame.this.pack();
				}
			}
			
		});
		
		
		this.add(newProjectPanel);
		this.pack();
	}
}
