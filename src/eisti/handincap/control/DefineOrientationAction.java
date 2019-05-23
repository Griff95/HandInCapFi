package eisti.handincap.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;

import eisti.handincap.PathFinder;

public class DefineOrientationAction implements ActionListener {
	
	private PathFinder pf;
	private JSlider orientation;

	public DefineOrientationAction(PathFinder pf, JSlider value) {
		this.pf = pf;
		this.orientation = value;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		pf.getUserPos().setOrientation(orientation.getValue());
		System.out.println(pf.getUserPos().getOrientation());
	}

}
