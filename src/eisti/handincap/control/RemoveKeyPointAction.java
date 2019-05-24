package eisti.handincap.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import eisti.handincap.KeyPoint;
import eisti.handincap.KeyPointUser;
import eisti.handincap.Link;
import eisti.handincap.PathFinder;
import eisti.handincap.Site;
import eisti.handincap.view.MapLabel;

public class RemoveKeyPointAction implements ActionListener {

	private MapLabel view;
	private Site abstraction;
	private PathFinder pf;
	private KeyPoint k;
	private int batC;

	public RemoveKeyPointAction(MapLabel mapLabel, Site abstraction, KeyPoint k, int b, PathFinder pf) {
		super();
		this.view = mapLabel;
		this.abstraction = abstraction;
		this.k = k;
		this.batC = b;
		this.pf = pf;
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
			pf.deleteChemin();
			for (KeyPoint kp : abstraction.getBuildingIndexed(batC).getPoints()) {
				if (kp.num > k.num) {
					System.out.println("decr num d'un point");
					kp.num--;
				}
			}
			KeyPoint.acc--;
			System.out.println(KeyPoint.acc);
			abstraction.getBuildingIndexed(batC).removePoints(k);

		}		
	}

}
