package eisti.handincap;

import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {


	private KeyPoint[] noeuds;
	private int nbDeNoeuds;
	private Link[] liaisons;
	private int nbDeLiaisons;


	public PathFinder(Link[] liaisons, KeyPoint[] noeuds) {
		this.liaisons = liaisons;
		this.nbDeLiaisons = liaisons.length;

		this.noeuds = noeuds;
		this.nbDeNoeuds = noeuds.length;

		// Vérifie s'il n'y a pas de noeud abandonné
		if (nbDeNoeuds > nbDeLiaisons) {
			System.out.println("Erreur : noeud seul dans le graphe.");
		}

		// Lie les liaisons aux noeuds associés
		for (int liaisonEffectuee = 0 ; liaisonEffectuee < nbDeLiaisons ; liaisonEffectuee++) {
			this.noeuds[liaisons[liaisonEffectuee].getDeNemeNoeud()].getLiaisons().add(liaisons[liaisonEffectuee]);
			this.noeuds[liaisons[liaisonEffectuee].getVersNemeNoeud()].getLiaisons().add(liaisons[liaisonEffectuee]);
		}
	}

/*
	// Initialise la liste de noeuds à partir de la liste des liaisons
	public PathFinder(Link[] liaisons) {
		this.liaisons = liaisons;

		// Créé le nb de noeuds en fonction des liaisons
		this.nbDeNoeuds = calculNbNoeuds(liaisons);
		this.noeuds = new KeyPoint[this.nbDeNoeuds];

		// Implémentation des noeuds
		for (int n = 0 ; n < this.nbDeNoeuds ; n++) {
			this.noeuds[n] = new KeyPoint(n);
		}

		// Relie les liaisons à leurs noeuds
		this.nbDeLiaisons = liaisons.length;
		for (int liaisonEffectuee = 0 ; liaisonEffectuee < nbDeLiaisons ; liaisonEffectuee++) {
			this.noeuds[liaisons[liaisonEffectuee].getDeNemeNoeud()].getLiaisons().add(liaisons[liaisonEffectuee]);
			this.noeuds[liaisons[liaisonEffectuee].getVersNemeNoeud()].getLiaisons().add(liaisons[liaisonEffectuee]);
		}
	}*/


	// Obtient le numéro maximum parmi les noeuds et ajoute 1 pour tenir en compte le noeud 0
	private int calculNbNoeuds(Link[] liaisons) {
		int nbDeNoeuds = 0;

		for (Link e : liaisons) {
			if (e.getVersNemeNoeud() > nbDeNoeuds) {
				nbDeNoeuds = e.getVersNemeNoeud();
			}
			if (e.getDeNemeNoeud() > nbDeNoeuds) {
				nbDeNoeuds = e.getDeNemeNoeud();
			}
		}

		nbDeNoeuds++;

		return nbDeNoeuds;
	}


	// Permet d'obtenir la liste des noeuds à parcourir pour aller du noeud de départ au noeud d'arrivée
	public void cheminParcouru(int noeudDepart, int noeudArrivee) {
		ArrayList<KeyPoint> chemin = new ArrayList<KeyPoint>();
		int noeudEtudie = noeudArrivee;
		int noeudAEtudier = noeudEtudie;
		int minDist = this.noeuds[noeudEtudie].getDistanceSource();
		chemin.add(this.noeuds[noeudArrivee]);
		// Part du noeud d'arrivée et remonte à la source en prenant le chemin le plus court
		while (this.noeuds[noeudEtudie].getDistanceSource() != 0) {
			ArrayList<Link> liaisonsDuNoeud = this.noeuds[noeudEtudie].getLiaisons();
			for (int liaisonEtudiee = 0 ; liaisonEtudiee < liaisonsDuNoeud.size() ; liaisonEtudiee++) {
				int noeudVoisin = liaisonsDuNoeud.get(liaisonEtudiee).getVoisin(noeudEtudie);
				if (this.noeuds[noeudVoisin].getDistanceSource() < minDist) {
					minDist = this.noeuds[noeudVoisin].getDistanceSource();
					noeudAEtudier = noeudVoisin;
				}
			}
			noeudEtudie = noeudAEtudier;
			chemin.add(this.noeuds[noeudEtudie]);
		}
		Collections.reverse(chemin);
		// Affichage des résultats
		testResultat(noeudDepart, noeudArrivee, chemin);
	}

	// L'algorithme de Dijkstra qui va, à partir d'un noeud source, initialiser tous les noeuds associés
	public void calculPlusCourtChemin(int noeudDepart) {
		// Le noeud de départ est la source
		this.noeuds[noeudDepart].setDistanceSource(0);
		int noeudEtudie = noeudDepart;

		// Parcours chaque noeud
		for (int i = 0 ; i < this.noeuds.length ; i++) {
			// Liste des liaisons du noeud parcouru
			ArrayList<Link> liaisonsDuNoeud = this.noeuds[noeudEtudie].getLiaisons();

			// Parcours les liaisons du noeud actuel
			for (int liaisonEtudiee = 0 ; liaisonEtudiee < liaisonsDuNoeud.size() ; liaisonEtudiee++) {
				// Obtention du numéro du noeud voisin
				int noeudVoisin = liaisonsDuNoeud.get(liaisonEtudiee).getVoisin(noeudEtudie);

				if (!this.noeuds[noeudVoisin].getVisite()) {
					int tailleCheminPotentiel = this.noeuds[noeudEtudie].getDistanceSource() + liaisonsDuNoeud.get(liaisonEtudiee).getTaille();
					if (tailleCheminPotentiel < noeuds[noeudVoisin].getDistanceSource()) {
						noeuds[noeudVoisin].setDistanceSource(tailleCheminPotentiel);
					}
				}
			}
			// Tout les voisins ayant été testés, le noeud est considéré comme visité
			noeuds[noeudEtudie].setVisite(true);

			noeudEtudie = getNoeudPlusCourtChemin();
		}
	}

	private int getNoeudPlusCourtChemin() {
		int numNoeudAnalyse = 0;
		int distAccumulee = Integer.MAX_VALUE;

		for (int i = 0 ; i < this.noeuds.length ; i++) {
			int distActuelle = this.noeuds[i].getDistanceSource();

			if (!this.noeuds[i].getVisite() && distActuelle < distAccumulee) {
				distAccumulee = distActuelle;
				numNoeudAnalyse = i;
			}
		}

		return numNoeudAnalyse;
	}


	// Test de rendu :
	public void testResultat(int noeudDepart, int noeudArrivee, ArrayList<KeyPoint> chemin) {
		String sortie = "Nombre de noeuds : " + this.nbDeNoeuds;
		sortie += "\nNombre de liaisons : " + this.nbDeLiaisons;

		for (int i = 0 ; i < this.noeuds.length ; i++) {
			sortie += ("\nTest unitaire :\nLe chemin le plus court du noeud " + noeudDepart + " au noeud " + i + " est : " + noeuds[i].getDistanceSource());
		}

		sortie += "\n\nLe chemin le plus court du noeud de départ au noeud d'arrivée est de : " + noeuds[noeudArrivee].getDistanceSource();

		sortie += "\n\nVous devez vous diriger vers le noeud de coordonnées (X=" + chemin.get(1).getX() + " ; Y=" + chemin.get(1).getY() + ") qui se situe à " + + chemin.get(1).getDistanceSource() + " mètres.";

		System.out.println(sortie);
	}


	public KeyPoint[] getNoeuds() {
		return noeuds;
	}


	public int getNbDeNoeuds() {
		return nbDeNoeuds;
	}


	public Link[] getLiaisons() {
		return liaisons;
	}


	public int getNbDeliaisons() {
		return nbDeLiaisons;
	}

}
