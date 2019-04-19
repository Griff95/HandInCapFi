package eisti.handincap.pathfinder;


// Graphique comprenant les liaisons et les noeuds
public class Graph {

	private Noeud[] noeuds;
	private int nbDeNoeuds;
	private Liaison[] liaisons;
	private int nbDeliaisons;
	
	
	public Graph(Liaison[] liaisons) {
		this.liaisons = liaisons;
		
		// Cr�� le nb de noeuds en fonction des liaisons
		this.nbDeNoeuds = calculNbNoeuds(Liaisons);
		this.noeuds = new Noeud[this.nbDeNoeuds];
		
		// Impl�mentation des noeuds
		for (int n = 0 ; n < this.nbDeNoeuds ; n++) {
			this.noeuds[n] = new Noeud();
		}
		
		// Relie les liaisons � leurs noeuds
		this.nbDeliaisons = liaisons.length;
	}


	public Noeud[] getNoeuds() {
		return noeuds;
	}


	public int getNbDeNoeuds() {
		return nbDeNoeuds;
	}


	public Liaison[] getLiaisons() {
		return liaisons;
	}


	public int getNbDeliaisons() {
		return nbDeliaisons;
	}
	
	
}
