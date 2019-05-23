package eisti.handincap;

public class LinkUpOrDown extends Link{
	
	public enum Type{
		ESCALIER,
		ASCENSEUR
	}
	
	private Type type;

	public LinkUpOrDown(KeyPoint deNemeNoeud, KeyPoint versNemeNoeud, int taille, Type t) {
		super(deNemeNoeud, versNemeNoeud, taille);
		System.out.println("Link up or down construit de type " + t.name());
		this.type = t;
		// TODO Auto-generated constructor stub
	}

	public Type getType() {
		return type;
	}

}
