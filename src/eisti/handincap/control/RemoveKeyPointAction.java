package eisti.handincap.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointUser;
import eisti.handincap.Link;
import eisti.handincap.Site;
import eisti.handincap.view.MapLabel;

public class RemoveKeyPointAction implements ActionListener {
	
	private MapLabel view;
	private Site abstraction;
	private KeyPoint k;
	private int batC;

	public RemoveKeyPointAction(MapLabel mapLabel, Site abstraction, KeyPoint k, int b) {
		super();
		this.view = mapLabel;
		this.abstraction = abstraction;
		this.k = k;
		this.batC = b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!(k instanceof KeyPointUser)) {
			ArrayList<Link> liaisons = abstraction.getBuildingIndexed(batC).getLiaisons();
			Iterator itr = liaisons.iterator(); 
	        while (itr.hasNext()) 
	        { 
	            Link l = (Link) itr.next(); 
	            if (l.getDeNemeNoeud() == k.num || l.getVersNemeNoeud() == k.num) {
	            	itr.remove(); 	            	
	            }
	        }
			abstraction.getBuildingIndexed(batC).getPoints().remove(k);
		}		
	}

}
