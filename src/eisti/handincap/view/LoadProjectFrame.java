package eisti.handincap.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eisti.handincap.Site;
import eisti.handincap.control.AddEtageAction;

public class LoadProjectFrame extends JFrame {
	
	private String savedFilePath;
	
	public LoadProjectFrame() {
		super();
		this.setTitle("HandincapFi - Charger un Projet");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JButton browse = new JButton("Parcourir..");
		JLabel selectedFile = new JLabel("");
		JPanel browsePanel = new JPanel();
		browsePanel.add(browse);
		browsePanel.add(selectedFile);
		
		JButton ok = new JButton("OK");
		ok.setEnabled(false);
		
		browse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					savedFilePath = fc.getSelectedFile().getPath();
					selectedFile.setText(savedFilePath);
					ok.setEnabled(true);
					
					
					
				}
				
			}
			
		});
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileInputStream fis;
				try {
					fis = new FileInputStream(savedFilePath);
					ObjectInputStream ois = new ObjectInputStream(fis); 
					Site project = (Site)ois.readObject();
					LoadProjectFrame.this.dispose();
					MainFrame main = new MainFrame(project);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		JPanel okPanel = new JPanel();
		okPanel.add(ok);
		
		
		JPanel loadProjectPanel = new JPanel();
		loadProjectPanel.setLayout(new BoxLayout(loadProjectPanel, BoxLayout.PAGE_AXIS));
		loadProjectPanel.add(new JLabel("Sélectionner le fichier de sauvegarde : "));
		loadProjectPanel.add(browsePanel);
		loadProjectPanel.add(okPanel);
		
		this.add(loadProjectPanel);
		this.pack();
	}
}
