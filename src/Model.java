
public class Model {

	
	private Citations citations;
	private Auteurs auteurs;
	private Sources sources;

	public Model(Citations citations, Auteurs auteurs, Sources sources){
		this.citations = citations;
		this.auteurs = auteurs;
		this.sources = sources;
	}
	
	
	public Citations getCitations() {
		return citations;
	}
	
	public Auteurs getAuteurs() {
		return auteurs;
	}

	public Sources getSources() {
		return sources;
	}

	
}
