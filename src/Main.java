import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.StringWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	static final String JSON_PATH = "json/";
	static final File JSON_CITATIONS = new File(JSON_PATH + "citations.json");
	static final File JSON_AUTEURS = new File(JSON_PATH + "auteurs.json");
	static final File JSON_SOURCES = new File(JSON_PATH + "sources.json");
	
	static final String TEMPLATE_PATH = "template/";
	static final File TEMPLATE_HEADER = new File(TEMPLATE_PATH + "header.html");
	static final File TEMPLATE_INDEX_TOREPEAT = new File(TEMPLATE_PATH + "indexToRepeat.html");
	static final File TEMPLATE_FOOTER = new File(TEMPLATE_PATH + "footer.html");
	static final File TEMPLATE_CITATION = new File(TEMPLATE_PATH + "citation.html");
	static final File TEMPLATE_AUTEUR = new File(TEMPLATE_PATH + "auteur.html");
	static final File TEMPLATE_SOURCE = new File(TEMPLATE_PATH + "source.html");
	
	static final String SITE_PATH = "site/";
	static final String PATH_CITATION = SITE_PATH + "citation_";
	static final String PATH_AUTEUR = SITE_PATH + "auteur_";
	static final String PATH_SOURCE = SITE_PATH + "source_";

	static int nbCitation = 0;
	static int nbAuteur = 0;
	static int nbSource = 0;
	
	public static void main(String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();
		System.out.println("---- DEBUT DE LA GENERATION DES PAGES STATIQUES ------");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String data;
		
		
		//Récupération des citations
		data = loadFile(JSON_CITATIONS);
		final Citations citations = gson.fromJson(data, Citations.class);
		System.out.println(citations);
		
		//Récupération des auteurs
		data = loadFile(JSON_AUTEURS);
		final Auteurs auteurs = gson.fromJson(data, Auteurs.class);
		System.out.println(auteurs);
		
		//Récupération des sources
		data = loadFile(JSON_SOURCES);
		final Sources sources = gson.fromJson(data, Sources.class);
		System.out.println(sources);
		
		//Gestion de l'Index
		System.out.println("INDEX - DEBUT");
		File index = new File(SITE_PATH + "index.html");
		copyFile(TEMPLATE_HEADER, index, false);
		for (Citation c : citations.getCitations()) {
			System.out.println("	citation : " + c.getId());
			File tempo = new File("temp");
			copyFile(TEMPLATE_INDEX_TOREPEAT, tempo, false);
			c.replace(tempo);
			Auteur a = auteurs.getById(c.getAuteurId());
			a.replace(tempo);
			Source s = sources.getById(c.getSourceId());
			s.replace(tempo);
			copyFile(tempo, index, true);
			tempo.delete();
		}
		copyFile(TEMPLATE_FOOTER, index, true);
		System.out.println("INDEX - FIN");
				
		System.out.println("CITATIONS - DEBUT");
		// Pour chaque citation on crée la page HTML associée en complétant le template
		for (Citation c : citations.getCitations()) {
			System.out.println("	citation : " + c.getId());
			File out = new File(PATH_CITATION + c.getIdString() + ".html");
			copyFile(TEMPLATE_HEADER, out, false);
			copyFile(TEMPLATE_CITATION, out, true);
			copyFile(TEMPLATE_FOOTER, out, true);
			c.replace(out);
			Auteur a = auteurs.getById(c.getAuteurId());
			a.replace(out);
			Source s = sources.getById(c.getSourceId());
			s.replace(out);
			nbCitation++;
		}
		System.out.println("CITATIONS - FIN");
		
		System.out.println("AUTEURS - DEBUT");
		// Pour chaque auteur on crée la page HTML associée en complétant le template
		for (Auteur a : auteurs.getAuteurs()) {
			System.out.println("	auteur : " + a.getId());
			File out = new File(PATH_AUTEUR + a.getIdString() + ".html");
			copyFile(TEMPLATE_HEADER, out, false);
			copyFile(TEMPLATE_AUTEUR, out, true);
			copyFile(TEMPLATE_FOOTER, out, true);
			a.replace(out);
			nbAuteur++;
		}
		System.out.println("AUTEURS - FIN");
		
		System.out.println("SOURCES - DEBUT");
		// Pour chaque sources on crée la page HTML associée en complétant le template
		for (Source s : sources.getSources()) {
			System.out.println("	source : " + s.getId());
			File out = new File(PATH_SOURCE + s.getIdString() + ".html");
			copyFile(TEMPLATE_HEADER, out, false);
			copyFile(TEMPLATE_SOURCE, out, true);
			copyFile(TEMPLATE_FOOTER, out, true);
			s.replace(out);
			nbSource++;
		}
		System.out.println("SOURCES - FIN");
		
		final long endTime = System.currentTimeMillis();
		System.out.println("----------------------------------------------------");
		System.out.println("----      FIN DE LA GENERATION DES PAGES      ------");
		System.out.println("----------------------------------------------------");
		System.out.println("--- Nombres de citations générées      : " + nbCitation);
		System.out.println("--- Nombres d'auteurs générés          : " + nbAuteur);
		System.out.println("--- Nombres de sources générées        : " + nbSource);
		System.out.println("--- Temps total d'execution            : " + (endTime - startTime) );
		System.out.println("----------------------------------------------------");

	}

	public static void copyFile(File src, File dest, boolean keep) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(src));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(dest, keep));
		byte[] buf = new byte[4096];
		int n;
		while ((n = in.read(buf, 0, buf.length)) > 0){
			out.write(buf, 0, n);	
		}
		in.close();
		out.close();
	}


	public static String loadFile(File f) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
			StringWriter out = new StringWriter();
			int b;
			while ((b = in.read()) != -1){
				out.write(b);
			}
			out.flush();
			out.close();
			in.close();
			return out.toString();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return null;
	}
}
