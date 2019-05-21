package eisti.handincap;

public class Link {

	// Le noeud dont la liaison provient et où elle mène avec les coordonnées associées
	private int deNemeNoeud;
	private int versNemeNoeud;

	// Sa taille
	private int taille;


	// Initialisation des valeurs à l'appel de fonction
	public Link(KeyPoint deNemeNoeud, KeyPoint versNemeNoeud, int taille) {
		this.deNemeNoeud = deNemeNoeud.num;
		this.versNemeNoeud = versNemeNoeud.num;
		this.taille = taille;
	}

	public int getDeNemeNoeud() {
		return deNemeNoeud;
	}

	public int getVersNemeNoeud() {
		return versNemeNoeud;
	}

	public int getTaille() {
		return taille;
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