package eisti.handincap.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eisti.handincap.Site;
import eisti.handincap.control.AddEtageAction;

public class AddEtageDialog extends JDialog {
	
	private JButton ok;
	JLabel selectedFile;
	private MapLabel mapLabel;
	private Site abstraction;
	AddEtageAction.Type type;
	
	public AddEtageDialog(Site a, MapLabel m, AddEtageAction.Type t) {
		super((JDialog) null, "Ajouter un batiment", true);
		this.mapLabel = m;
		this.abstraction = a;
		this.type = t;
		this.setLocationRelativeTo(null);
		this.setSize(250,150);
		this.setLayout(new FlowLayout());
		this.add(new JLabel("Définir la carte de l'étage : "));
		JButton parcourir = new JButton("Parcourir..");
		selectedFile = new JLabel();
		this.add(selectedFile);
		parcourir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFileChooser(selectedFile);
			}
		});
		this.add(parcourir);
		ok = new JButton("OK");
		this.add(ok);
		
	}
	
	protected void openFileChooser(JLabel selectedFile) {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String carteBatimentPath = fc.getSelectedFile().getPath();
			selectedFile.setText(carteBatimentPath);
			AddEtageAction control = new AddEtageAction(mapLabel, this, carteBatimentPath, abstraction, type);
			ok.addActionListener(control);
			abstraction.addPropertyChangeListener(control);
		}
		
	}
}
