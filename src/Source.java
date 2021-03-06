import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Source {

	private int id;
	private String nom;
	private String date;
	private String slug;
	private int auteur_id;


	public void Source() {
		this.id = 0;
		this.nom = " ";
		this.date = " ";
		this.auteur_id = 0;
		this.slug = " ";
		
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

	public String getDate() {
		return this.date;
	}
	
	public String getSlug() {
		return this.slug;
	}
	
	public int getAuteurId() {
		return this.auteur_id;
	}

	public String toString() {
		return "{id : " + id + ", nom : " + nom + ", date : " + date + ", auteur_id : " + auteur_id + "}";
	}
	

	
	public void replace(File file){
		Path path = Paths.get(file.getAbsolutePath());
		Charset charset = StandardCharsets.UTF_8;
		String content = "";
		try {
			content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll(":source_nom", this.getNom());
			content = content.replaceAll(":source_date", this.getDate());
			content = content.replaceAll(":source_id", this.getIdString());
			content = content.replaceAll(":source_slug", this.getSlug());
			Files.write(path, content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		this.slug = "";
		if(!this.nom.equals("") && !this.nom.equals(" ")){
			this.slug += this.nom + "-";
		}
		this.slug += "source_" + this.id;
		this.slug = this.slug.replace(' ', '-');
		this.slug = this.slug.replace('\'', '-');
		this.slug = this.slug.toLowerCase();
	}
}
