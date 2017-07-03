import java.util.Arrays;

public class Auteurs {

	private Auteur[] auteurs;

	public void Auteurs() {

	}

	public String toString() {
		return "auteurs : " + Arrays.toString(this.auteurs);
	}

	public Auteur[] getAuteurs() {
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
