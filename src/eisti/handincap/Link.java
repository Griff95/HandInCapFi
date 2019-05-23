package eisti.handincap;

import java.io.Serializable;

public class Link implements Serializable{

	// Le noeud dont la liaison provient et où elle mène avec les coordonnées associées
	private int deNemeNoeud;
	private int versNemeNoeud;
	private KeyPoint k1;
	private KeyPoint k2;

	// Sa taille
	private int taille;


	// Initialisation des valeurs à l'appel de fonction
	public Link(KeyPoint deNemeNoeud, KeyPoint versNemeNoeud, int taille) {
		this.k1 = deNemeNoeud;
		this.k2 = versNemeNoeud;
		this.deNemeNoeud = deNemeNoeud.num;
		this.versNemeNoeud = versNemeNoeud.num;
		this.taille = taille;
	}

	public KeyPoint getK1() {
		return k1;
	}

	public KeyPoint getK2() {
		return k2;
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