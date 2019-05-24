package eisti.handincap;

import java.io.Serializable;
import java.util.ArrayList;

public class KeyPoint implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public static final int DISTANCE_FUSION = 50;
	
	public static int acc = -1;
	
	public int num;// Définit l'id du noeud
	private int x, y ,z; // Coordonnées du point
	
	// PAS METTRE
	private int distanceSource = Integer.MAX_VALUE; // Initialise la valeur de la distance entre le noeud et la source à l'infini
	private boolean visite; // Indique si le noeud a été visité ou non
	private ArrayList<Link> liaisons; // Liste des liaisons

	
	
	public KeyPoint(int x, int y, int z) {
		System.out.println("acc is " + acc);
		this.x = x;
		this.y = y;
		this.z = z;
		this.num = acc;
		acc++;
		this.visite = false;
		liaisons = new ArrayList<Link>();
		System.out.println("KeyPoint n°" + num);
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

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	// Donne la distance entre deux noeuds
	public int getDistanceTo(KeyPoint user) {
		double distance = 0;
		if (user.getZ() != this.getZ()) {
			distance = Math.abs(user.getZ() - this.getZ())*DISTANCE_FUSION;
		}
		distance += Math.sqrt(Math.pow((user.getX()-this.getX()), 2)+Math.pow((user.getY()-this.getY()),2));
		return (int) distance;
	}
	
	public static void setAccumulateur(int i) {
		acc = i;
	}


}
