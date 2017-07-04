import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Auteur {

	private int id;
	private String nom;
	private String prenom;
	private String titre;


	public void Auteur() {
		this.id = 0;
		this.nom = " ";
		this.prenom = " ";
		this.titre = " ";
	}

	public int getId() {
		return this.id;
	}

	public String getIdString() {
		return String.valueOf(this.id);
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getTitre() {
		return this.titre;
	}
	
	public String toString() {
		return "{id : " + id + ", titre : " + titre + ", nom : " + nom + ", prenom : " + prenom + "}";
	}
	

	
	public void replace(File file) throws IOException {
		Path path = Paths.get(file.getAbsolutePath());
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll(":auteur_nom", this.getNom());
		content = content.replaceAll(":auteur_prenom", this.getPrenom());
		content = content.replaceAll(":auteur_titre", this.getTitre());
		content = content.replaceAll(":auteur_id", this.getIdString());
		Files.write(path, content.getBytes(charset));
	}
}
