import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Citation {

	private int id;
	private String contenu;

	private int source_id;

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
	

	
	public int getSourceId() {
		return this.source_id;
	}

	public String toString() {
		return "{id : " + id + ", source_id : " + source_id + ", contenu : " + contenu + "}";
	}
	

	
	public void replace(File file){
		Path path = Paths.get(file.getAbsolutePath());
		Charset charset = StandardCharsets.UTF_8;
		String content = "";
		try {
			content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll(":citation_contenu", this.getContenu());
			content = content.replaceAll(":citation_id", this.getIdString());
			Files.write(path, content.getBytes(charset));
		} catch (IOException e) {
			System.out.println("ERREUR -- Lecture du fichier : " + path);
			e.printStackTrace();
		}
	}
}
