package eisti.handincap.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import eisti.handincap.Site;

public class SaveProjectDialog extends JDialog {
	
	public SaveProjectDialog(Site abstraction) {
		this.setTitle("HandincapFi - Sauvegarder Projet");
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JLabel label = new JLabel("Dossier et nom de sauvegarde : ");
		JButton browse = new JButton("Parcourir..");
		JPanel saveProjectPanel = new JPanel();
		saveProjectPanel.add(label);
		saveProjectPanel.add(browse);
		browse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new File("/home/eisti"));
			    int retrival = chooser.showSaveDialog(null);
			    if (retrival == JFileChooser.APPROVE_OPTION) {
			    	try {
			    		File f = new File(chooser.getSelectedFile().getAbsolutePath());
						FileOutputStream fos = new FileOutputStream(f.getPath()+".ser");
						ObjectOutputStream oos = new ObjectOutputStream(fos); 
						oos.writeObject(abstraction);
						oos.close();
						SaveProjectDialog.this.dispose();

					} catch (FileNotFoundException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					} 
			    }
			}
			
		});
		
		
		this.add(saveProjectPanel);
		this.pack();
	}
}
