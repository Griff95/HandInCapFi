package eisti.handincap.pathfinder;

public class Liaison {
	
	// Le noeud dont la liaison provient et où elle mène
	private int deNemeNoeud;
	private int versNemeNoeud;
	
	// Sa taille
	private int taille;
	
	
	// Initialisation des valeurs à l'appel de fonction
	public Liaison(int deNemeNoeud, int versNemeNoeud, int taille) {
		this.deNemeNoeud = deNemeNoeud;
		this.versNemeNoeud = versNemeNoeud;
		this.taille = taille;
	}
	
	
	// Récupération du noeud voisin au noeud actuel
	public int getVoisin(int numVoisin) {
		if (this.deNemeNoeud == numVoisin) {
			return this.versNemeNoeud;
		} else {
			return this.deNemeNoeud;
		}
	}
}
