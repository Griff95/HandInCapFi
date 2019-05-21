package eisti.handincap;

import java.util.ArrayList;

public class KeyPoint {
	
	public static int acc = 0;
	
	public int num;// Définit le nom du noeud
	private int x, y ,z; // Coordonnées du point
	private int distanceSource = Integer.MAX_VALUE; // Initialise la valeur de la distance entre le noeud et la source � l'infini
	private boolean visite; // Indique si le noeud a �t� visit� ou non
	private ArrayList<Link> liaisons; // Liste des liaisons

	
	
	public KeyPoint(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.num = acc;
		acc++;
		this.visite = false;
		liaisons = new ArrayList<Link>();
	}

	// Getters
	public int getDistanceSource() {
		return distanceSource;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getVisite() {
		return visite;
	}
	
	public ArrayList<Link> getLiaisons() {
		// Envoie toutes les liaisons du noeud
		return liaisons;
	}


	// Setters
	public void setDistanceSource(int distanceSource) {
		this.distanceSource = distanceSource;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setVisite(boolean visite) {
		this.visite = visite;
	}

	public void setLiaisons(ArrayList<Link> liaisons) {
		this.liaisons = liaisons;
	}
	
	public void addLiaison(Link liaison) {
		this.liaisons.add(liaison);
	}
	
	public void removeLiaison(Link liaison) {
		this.liaisons.remove(liaison);
	}
}
