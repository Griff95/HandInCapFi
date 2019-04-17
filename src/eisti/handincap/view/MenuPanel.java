package eisti.handincap.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuPanel extends JPanel {
	JLabel handincapLabel = new JLabel("Hand'in CapFi");

	public MenuPanel() {
		this.setBackground(Color.pink);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border bord = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(bord);
		this.add(handincapLabel);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
