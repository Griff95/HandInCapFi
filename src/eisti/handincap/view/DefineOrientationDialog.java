package eisti.handincap.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eisti.handincap.PathFinder;
import eisti.handincap.control.DefineOrientationAction;

public class DefineOrientationDialog extends JDialog {
	private JSlider slider;
	private PathFinder pf;
	
	public DefineOrientationDialog(PathFinder p) {
		super();
		this.pf = p;
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setSize(300, 100);
		
		
		JLabel infoLabel = new JLabel(Integer.toString(pf.getUserPos().getOrientation()));
		
		slider = new JSlider();
		slider.setMaximum(359);
		slider.setMinimum(0);
		slider.setValue(pf.getUserPos().getOrientation());
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				infoLabel.setText(Integer.toString(slider.getValue()));
			}
			
		});
		JButton ok = new JButton("OK");
		ok.addActionListener(new DefineOrientationAction(pf, slider));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefineOrientationDialog.this.dispose();
			}
		});
		
		this.add(new JLabel("Définir l'orientation en degré (0 à 359°) : "));
		this.add(slider);
		this.add(infoLabel);
		this.add(ok);
		
	}
	
}
