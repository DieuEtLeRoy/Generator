import java.util.ArrayList;

public class Auteurs {

	private ArrayList<Auteur> auteurs;

	public void Auteurs() {

	}

	public String toString() {
		return "auteurs : " + this.auteurs.toString();
	}

	public ArrayList<Auteur> getAuteurs() {
		return auteurs;

	}
	
	public Auteur getById(int id){
		for(Auteur a : this.auteurs){
			if(a.getId() == id){
				return a;
			}
		}
		return (new Auteur());
	}
}
