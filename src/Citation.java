public class Citation {

	private int id;
	private String contenu;
	private String auteur;
	private String date;
	private String source;

	public void Citation() {

	}

	public int getId() {
		return this.id;
	}

	public String getIdString() {
		return String.valueOf(this.id);
	}

	public String getContenu() {
		return this.contenu;
	}

	public String getAuteur() {
		return this.auteur;
	}

	public String getSource() {
		return (this.source.equals("")) ? "null" : this.source;
	}

	public String toString() {
		return "{id : " + id + ", auteur : " + auteur + ", date : " + date + ", source : " + source + " , contenu : " + contenu + "}";
	}
}
