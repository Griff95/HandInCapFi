package eisti.handincap.view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eisti.handincap.Site;
import eisti.handincap.control.AddEtageAction;

public class LoadProjectFrame extends JFrame {
	
	private String savedFilePath;
	
	public LoadProjectFrame(JFrame frame) {
		super();
		this.setTitle("HandincapFi - Charger un Projet");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JButton browse = new JButton("Parcourir..");
		JLabel selectedFile = new JLabel("");
		selectedFile.setAlignmentX(CENTER_ALIGNMENT);
		JPanel browsePanel = new JPanel();
		//browsePanel.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		browsePanel.add(browse);
		//browsePanel.add(selectedFile);
		
		JButton ok = new JButton("OK");
		ok.setEnabled(false);
		
		browse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					savedFilePath = fc.getSelectedFile().getPath();
					selectedFile.setText(savedFilePath);
					LoadProjectFrame.this.pack();
					ok.setEnabled(true);
					
					
					
				}
				
			}
			
		});
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileInputStream fis;
				try {
					File projectToLoad = new File(savedFilePath);
					//System.out.println(projectToLoad.getClass());
					//if (projectToLoad.)
					//fis = new FileInputStream();
					fis = new FileInputStream(savedFilePath);
					ObjectInputStream ois = new ObjectInputStream(fis); 
					Site project = (Site)ois.readObject();
					LoadProjectFrame.this.dispose();
					MainFrame main = new MainFrame(project);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					//System.out.println("iciciicicic");
					JDialog d = new JDialog(LoadProjectFrame.this, "Erreur - Chargement de fichier");
					d.setLayout(new FlowLayout());
					d.add(new JLabel("Vous devez sélectionner un fichier '.ser'"));
					d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					d.setLocationRelativeTo(null);
					d.setVisible(true);
					d.pack();
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}				
			}
			
		});
		JPanel okPanel = new JPanel();
		okPanel.add(ok);
		JLabel lab = new JLabel("Sélectionner le fichier de sauvegarde : ");
		lab.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel loadProjectPanel = new JPanel();
		loadProjectPanel.setLayout(new BoxLayout(loadProjectPanel, BoxLayout.PAGE_AXIS));
		loadProjectPanel.add(lab);
		loadProjectPanel.add(browsePanel);
		loadProjectPanel.add(selectedFile);
		loadProjectPanel.add(okPanel);
		
		this.add(loadProjectPanel);
		this.pack();
	}

}
