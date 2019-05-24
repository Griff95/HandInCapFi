package eisti.handincap;

public class LinkUpOrDown extends Link{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Type{
		ESCALIER,
		ASCENSEUR
	}
	
	private Type type;

	public LinkUpOrDown(KeyPoint deNemeNoeud, KeyPoint versNemeNoeud, Type t) {
		super(deNemeNoeud, versNemeNoeud);
		System.out.println("Link up or down construit de type " + t.name());
		this.type = t;
		// TODO Auto-generated constructor stub
	}

	public Type getType() {
		return type;
	}

}
