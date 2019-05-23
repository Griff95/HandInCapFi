package eisti.handincap.control;

import t2s.son.LecteurTexte;

public class SpeakUpAction implements Runnable{
	
	private String vocal;

	public SpeakUpAction(String vocal) {
		this.vocal = vocal;
		
		
	}

	@Override
	public void run() {
		LecteurTexte lecteur = new LecteurTexte(vocal);
		lecteur.playAll();
	}

}
