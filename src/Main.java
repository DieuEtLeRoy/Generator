import java.io.File;
import java.io.IOException;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	static final String JSON_PATH = "json/";
	static final File JSON_CITATIONS = new File(JSON_PATH + "citations.json");
	static final File JSON_AUTEURS = new File(JSON_PATH + "auteurs.json");
	static final File JSON_SOURCES = new File(JSON_PATH + "sources.json");

	static int nbCitation = 0;
	static int nbAuteur = 0;
	static int nbSource = 0;
	
	public static void main(String[] args){
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String data;
		
		
		//Récupération des citations
		data = FileManager.loadFile(JSON_CITATIONS);
		final Citations citations = gson.fromJson(data, Citations.class);
		citations.init();
		System.out.println(citations);
		
		//Récupération des auteurs
		data = FileManager.loadFile(JSON_AUTEURS);
		final Auteurs auteurs = gson.fromJson(data, Auteurs.class);
		auteurs.init();
		System.out.println(auteurs);
		
		//Récupération des sources
		data = FileManager.loadFile(JSON_SOURCES);
		final Sources sources = gson.fromJson(data, Sources.class);
		sources.init();
		System.out.println(sources);
		
		Model model = new Model(citations, auteurs, sources);
		
		//Generation des pages
		htmlGenerator.generate(model);
		
		

	}

}
