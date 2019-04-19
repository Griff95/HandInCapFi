package eisti.handincap.pathfinder;

import java.util.ArrayList;


// Représente un noeud sur le graphique
public class Noeud {
	
	// Initialise la valeur de la distance entre le noeud et la source à l'infini
	private int distanceSource = Integer.MAX_VALUE;
	
	// Indique si le noeud a été visité ou non
	private boolean visite;
	
	// Liste des liaisons possibles
	private ArrayList<Liaison> liaisons = new ArrayList<Liaison>();
	
	
	// Getters
	public int getDistanceSource() {
		return distanceSource;
	}
	
	public boolean getVisite() {
		return visite;
	}
	
	public ArrayList<Liaison> getLiaisons() {
		// Envoie toutes les liaisons du noeud
		return liaisons;
	}
	
	
	// Setters
	public void setDistanceSource(int distanceSource) {
		this.distanceSource = distanceSource;
	}
	
	public void setVisite(boolean visite) {
		this.visite = visite;
	}
	
	public void setLiaisons(ArrayList<Liaison> liaisons) {
		this.liaisons = liaisons;
	}
	
}
