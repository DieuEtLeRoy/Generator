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


	public void Source() {
		this.id = 0;
		this.nom = " ";
		this.date = " ";
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

	public String toString() {
		return "{id : " + id + ", nom : " + nom + ", date : " + date + "}";
	}
	

	
	public void replace(File file) throws IOException {
		Path path = Paths.get(file.getAbsolutePath());
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll(":source_nom", this.getNom());
		content = content.replaceAll(":source_date", this.getDate());
		content = content.replaceAll(":source_id", this.getIdString());
		Files.write(path, content.getBytes(charset));
	}
}
