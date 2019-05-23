package eisti.handincap.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eisti.handincap.Site;
import eisti.handincap.control.AddEtageAction;
import eisti.handincap.control.AddKeyPointAction;

public class AddKeyPointDestinationDialog extends JDialog {
	private MapLabel view;
	private JTextField nomSalle;
	private Site abstraction;
	private Type type;
	private MouseEvent e; 
	
	public AddKeyPointDestinationDialog(MapLabel view, Site abstraction, MouseEvent e) {
		super();
		this.view = view;
		this.abstraction = abstraction;
		this.e = e;
		this.nomSalle = new JTextField("nomDestination");
		
		this.setLocationRelativeTo(null);
		this.setSize(250,150);
		this.setLayout(new FlowLayout());
		this.add(new JLabel("Définir le nom de la destination : "));
		JButton ok = new JButton("OK");
		this.add(nomSalle);
		this.add(ok);
		
		AddKeyPointAction control = new AddKeyPointAction(AddKeyPointAction.Type.DEST, e, view, abstraction, null, view.getEtageCourant(), view.getBatimentCourant(), nomSalle);
		ok.addActionListener(control);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		abstraction.getBuildingIndexed(view.getBatimentCourant()).addPropertyChangeListener(control);
	}
}
