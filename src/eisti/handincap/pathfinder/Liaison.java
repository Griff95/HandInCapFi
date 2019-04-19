package eisti.handincap.pathfinder;

public class Liaison {
	
	// Le noeud dont la liaison provient et o� elle m�ne
	private int deNemeNoeud;
	private int versNemeNoeud;
	
	// Sa taille
	private int taille;
	
	
	// Initialisation des valeurs � l'appel de fonction
	public Liaison(int deNemeNoeud, int versNemeNoeud, int taille) {
		this.deNemeNoeud = deNemeNoeud;
		this.versNemeNoeud = versNemeNoeud;
		this.taille = taille;
	}
	
	
	// R�cup�ration du noeud voisin au noeud actuel
	public int getVoisin(int numVoisin) {
		if (this.deNemeNoeud == numVoisin) {
			return this.versNemeNoeud;
		} else {
			return this.deNemeNoeud;
		}
	}
}
