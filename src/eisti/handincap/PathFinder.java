package eisti.handincap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PathFinder implements Serializable {


	//private ArrayList<KeyPoint> noeuds;
	//private ArrayList<Link> liaisons;
	private KeyPointUser userPos;
	private KeyPointDestination destination;
	private ArrayList<Link> chemin;
	private boolean ascenseur;
	private int cheminIndexCourrant;


	/*
	// Utile lorsqu'aucune destination n'a été entrée
	public PathFinder(Building bat, KeyPointUser userPosition) {
		this.liaisons = bat.getLiaisons();
		this.noeuds = bat.getPoints();
		this.userPos = userPosition;

		int size = (liaisons == null)? 0 : liaisons.size();

		// Lie les liaisons aux noeuds associes
		for (int liaisonEffectuee = 0 ; liaisonEffectuee < size ; liaisonEffectuee++) {
			this.noeuds.get(liaisons.get(liaisonEffectuee).getDeNemeNoeud()).getLiaisons().add(liaisons.get(liaisonEffectuee));
			this.noeuds.get(liaisons.get(liaisonEffectuee).getVersNemeNoeud()).getLiaisons().add(liaisons.get(liaisonEffectuee));
		}
	}
	*/
	
	public PathFinder(KeyPointUser userPosition, boolean a) {
		this.userPos = userPosition;
		this.setDestination(null);
		this.chemin = new ArrayList<Link>();
		this.ascenseur = a;
		this.setCheminIndexCourrant(0);

		
		
		//Initialisation de l'algorithme de Dijkstra en se basant sur le noeud le plus proche de l'utilisateur comme noeud de depart
		/*int noeudDepart = pointProche(userPos).num;
		this.calculPlusCourtChemin(noeudDepart);
		this.cheminParcouru(noeudDepart, noeudArrivee);*/
	}
	

	// Donne le noeud le plus proche du noeud selectionne
	public KeyPoint pointProche(ArrayList<KeyPoint> noeuds, KeyPointUser user) {
		double distMin = Double.MAX_VALUE;
		double dist;
		KeyPoint pointPlusProche = null;
		for (KeyPoint pt : noeuds) {
			dist = calculDistance(user,pt);
			if (dist < distMin) {
				distMin = dist;
				pointPlusProche = pt;
			}
		}
		return pointPlusProche;
	}
	
	// Donne la distance entre deux noeuds
	public double calculDistance(KeyPoint user, KeyPoint pt) {
		double distance = 0;
		if (user.getZ() != pt.getZ()) {
			distance = Math.abs(user.getZ() - pt.getZ())*10;
		}
		distance += Math.sqrt(Math.pow((user.getX()-pt.getX()), 2)+Math.pow((user.getY()-pt.getY()),2));
		return distance;
	}
	
	
	public void initialisation(ArrayList<KeyPoint> noeuds, ArrayList<Link> liaisons) {
		// Vide les liaisons des noeuds
		for (KeyPoint noeud : noeuds) {
			noeud.setLiaisons(new ArrayList<Link>());
			noeud.setVisite(false);
		}
		
		// Lie les liaisons aux noeuds
		for (int liaisonEffectuee = 0 ; liaisonEffectuee < liaisons.size() ; liaisonEffectuee++) {
			noeuds.get(liaisons.get(liaisonEffectuee).getDeNemeNoeud()).getLiaisons().add(liaisons.get(liaisonEffectuee));
			noeuds.get(liaisons.get(liaisonEffectuee).getVersNemeNoeud()).getLiaisons().add(liaisons.get(liaisonEffectuee));
		}
		
		//Initialisation de l'algorithme de Dijkstra en se basant sur le noeud le plus proche de l'utilisateur comme noeud de depart
		int noeudDepart = pointProche(noeuds, userPos).num;
		this.calculPlusCourtChemin(noeuds, liaisons, noeudDepart);
	}
	
	
	public void calculChemin(ArrayList<KeyPoint> noeuds, ArrayList<Link> liaisons, KeyPoint noeudArrivee) {
		int noeudDepart = pointProche(noeuds, userPos).num;
		this.cheminParcouru(noeuds, liaisons, noeudDepart, noeudArrivee.num);
	}

	
	/*
	// Initialise la liste de noeuds ÃÂ  partir de la liste des liaisons
	public PathFinder(Link[] liaisons) {
		this.liaisons = liaisons;

		// CrÃÂ©ÃÂ© le nb de noeuds en fonction des liaisons
		this.nbDeNoeuds = calculNbNoeuds(liaisons);
		this.noeuds = new KeyPoint[this.nbDeNoeuds];

		// ImplÃÂ©mentation des noeuds
		for (int n = 0 ; n < this.nbDeNoeuds ; n++) {
			this.noeuds[n] = new KeyPoint(n);
		}

		// Relie les liaisons ÃÂ  leurs noeuds
		this.nbDeLiaisons = liaisons.length;
		for (int liaisonEffectuee = 0 ; liaisonEffectuee < nbDeLiaisons ; liaisonEffectuee++) {
			this.noeuds[liaisons[liaisonEffectuee].getDeNemeNoeud()].getLiaisons().add(liaisons[liaisonEffectuee]);
			this.noeuds[liaisons[liaisonEffectuee].getVersNemeNoeud()].getLiaisons().add(liaisons[liaisonEffectuee]);
		}
	}
	*/

	/*
	// Obtient le numÃÂ©ro maximum parmi les noeuds et ajoute 1 pour tenir en compte le noeud 0
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
	*/


	// Permet d'obtenir la liste des noeuds a  parcourir pour aller du noeud de depart au noeud d'arrivee
	public void cheminParcouru(ArrayList<KeyPoint> noeuds, ArrayList<Link> liaisons, int noeudDepart, int noeudArrivee) {
		ArrayList<KeyPoint> chemin = new ArrayList<KeyPoint>();
		int noeudEtudie = noeudArrivee;
		int noeudAEtudier = noeudEtudie;
		int minDist = noeuds.get(noeudEtudie).getDistanceSource();
		chemin.add(noeuds.get(noeudArrivee));
		// Part du noeud d'arrivee et remonte a la source en prenant le chemin le plus court
		while (noeuds.get(noeudEtudie).getDistanceSource() != 0) {
			ArrayList<Link> liaisonsDuNoeud = noeuds.get(noeudEtudie).getLiaisons();
			for (int liaisonEtudiee = 0 ; liaisonEtudiee < liaisonsDuNoeud.size() ; liaisonEtudiee++) {
				int noeudVoisin = liaisonsDuNoeud.get(liaisonEtudiee).getVoisin(noeudEtudie);
				if (noeuds.get(noeudVoisin).getDistanceSource() < minDist) {
					minDist = noeuds.get(noeudVoisin).getDistanceSource();
					noeudAEtudier = noeudVoisin;
				}
			}
			noeudEtudie = noeudAEtudier;
			chemin.add(noeuds.get(noeudEtudie));
		}
		Collections.reverse(chemin);
		// Affichage des resultats
		//testResultat(noeudDepart, noeudArrivee, chemin);
		creerChemin(chemin);
	}
	
	// Permet d'obtenir le chemin des liaisons a parcourir a partir du chemin des noeuds a parcourir
	public void creerChemin(ArrayList<KeyPoint> cheminNoeuds) {
		this.chemin = new ArrayList<Link>();
		KeyPoint noeudEtudie;
		for (int i = 0 ; i < cheminNoeuds.size()-1 ; i++) {
			noeudEtudie = cheminNoeuds.get(i);
			for (Link liaisonEtudiee : noeudEtudie.getLiaisons()) {
				if (liaisonEtudiee.getVersNemeNoeud() == cheminNoeuds.get(i+1).num) {
					this.chemin.add(liaisonEtudiee);
				} else if (liaisonEtudiee.getDeNemeNoeud() == cheminNoeuds.get(i+1).num) {
					this.chemin.add(liaisonEtudiee);
				}
			}
		}
	}
	

	// L'algorithme de Dijkstra qui va, a  partir d'un noeud source, initialiser tous les noeuds associes
	public void calculPlusCourtChemin(ArrayList<KeyPoint> noeuds, ArrayList<Link> liaisons, int noeudDepart) {
		// Le noeud de depart est la source
		noeuds.get(noeudDepart).setDistanceSource(0);
		int noeudEtudie = noeudDepart;

		// Parcours chaque noeud
		for (int i = 0 ; i < noeuds.size() ; i++) {
			// Liste des liaisons du noeud parcouru
			ArrayList<Link> liaisonsDuNoeud = noeuds.get(noeudEtudie).getLiaisons();

			// Parcours les liaisons du noeud actuel
			for (int liaisonEtudiee = 0 ; liaisonEtudiee < liaisonsDuNoeud.size() ; liaisonEtudiee++) {
				// Obtention du numero du noeud voisin
				int noeudVoisin = liaisonsDuNoeud.get(liaisonEtudiee).getVoisin(noeudEtudie);

				if (!noeuds.get(noeudVoisin).getVisite()) {
					int tailleCheminPotentiel = noeuds.get(noeudEtudie).getDistanceSource() + liaisonsDuNoeud.get(liaisonEtudiee).getTaille();
					if (tailleCheminPotentiel < noeuds.get(noeudVoisin).getDistanceSource()) {
						noeuds.get(noeudVoisin).setDistanceSource(tailleCheminPotentiel);
					}
				}
			}
			// Tout les voisins ayant ete testes, le noeud est considere comme visite
			noeuds.get(noeudEtudie).setVisite(true);
			noeudEtudie = getNoeudPlusCourtChemin(noeuds, liaisons);
		}
	}

	private int getNoeudPlusCourtChemin(ArrayList<KeyPoint> noeuds, ArrayList<Link> liaisons) {
		int numNoeudAnalyse = 0;
		int distAccumulee = Integer.MAX_VALUE;
		for (int i = 0 ; i < noeuds.size() ; i++) {
			int distActuelle = noeuds.get(i).getDistanceSource();
			if (!noeuds.get(i).getVisite() && distActuelle < distAccumulee) {
				distAccumulee = distActuelle;
				numNoeudAnalyse = i;
			}
		}
		return numNoeudAnalyse;
	}


	// Test de rendu :
	/*public void testResultat(int noeudDepart, int noeudArrivee, ArrayList<KeyPoint> chemin) {
		String sortie = "Nombre de noeuds : " + noeuds.size();
		sortie += "\nNombre de liaisons : " + this.liaisons.size();

		//for (int i = 0 ; i < this.noeuds.size() ; i++) {
		//	sortie += ("\nTest unitaire :\nLe chemin le plus court du noeud " + noeudDepart + " au noeud " + i + " est : " + noeuds.get(i).getDistanceSource());
		//}

		sortie += "\n\nLe chemin le plus court du noeud de depart au noeud d'arrivee est de : " + noeuds.get(noeudArrivee).getDistanceSource();

		sortie += "\n\nVous devez vous diriger vers le noeud de coordonnees (X=" + chemin.get(1).getX() + " ; Y=" + chemin.get(1).getY() + ") qui se situe aÂ  " + + chemin.get(1).getDistanceSource() + " metres.";

		System.out.println(sortie);
	}*/
	
	public ArrayList<Link> getChemin() {
		return chemin;
	}
	
	public KeyPointUser getUserPos() {
		return userPos;
	}

	public void setUserPos(KeyPointUser userPos) {
		this.userPos = userPos;
	}
	
	public void setUserPos(int x, int y, int z) {
		this.userPos.setX(x);
		this.userPos.setY(y);
		this.userPos.setZ(z);
	}


	public boolean isAscenseur() {
		return ascenseur;
	}


	public void setAscenseur(boolean ascenseur) {
		this.ascenseur = ascenseur;
	}


	public KeyPointDestination getDestination() {
		return destination;
	}


	public void setDestination(KeyPointDestination destination) {
		this.destination = destination;
	}


	public int getCheminIndexCourrant() {
		return cheminIndexCourrant;
	}


	public void setCheminIndexCourrant(int cheminIndexCourrant) {
		this.cheminIndexCourrant = cheminIndexCourrant;
	}
}