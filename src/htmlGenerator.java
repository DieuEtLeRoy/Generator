import java.io.File;
import java.io.IOException;

public class htmlGenerator {
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
	
	
	public static void generate(Model model){
		final long startTime = System.currentTimeMillis();
		System.out.println("---- DEBUT DE LA GENERATION DES PAGES STATIQUES ------");
		
		generateIndex(model);
		
		int nbCitation = generateCitation(model);
		int nbAuteur = generateSource(model);
		int nbSource = generateAuteur(model);
		
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
	
	
	public static int generateIndex(Model model){
		System.out.println("INDEX - DEBUT");
		File index = new File(SITE_PATH + "index.html");
		FileManager.copyFile(TEMPLATE_HEADER, index, false);
		for (Citation c : model.getCitations().getCitations()) {
			System.out.println("	citation : " + c.getId());
			File tempo = new File("temp");
			FileManager.copyFile(TEMPLATE_INDEX_TOREPEAT, tempo, false);
			
			Source s = model.getSources().getById(c.getSourceId());
			Auteur a = model.getAuteurs().getById(s.getAuteurId());
			
			c.replace(tempo);
			a.replace(tempo);
			s.replace(tempo);
			
			FileManager.copyFile(tempo, index, true);
			tempo.delete();
		}
		FileManager.copyFile(TEMPLATE_FOOTER, index, true);
		System.out.println("INDEX - FIN");
		return 0;
	}
	
	public static int generateCitation(Model model){
		int count = 0;
		System.out.println("CITATIONS - DEBUT");
		// Pour chaque citation on crée la page HTML associée en complétant le template
		for (Citation c : model.getCitations().getCitations()) {
			System.out.println("	citation : " + c.getId());
			File out = new File(PATH_CITATION + c.getIdString() + ".html");
			FileManager.copyFile(TEMPLATE_HEADER, out, false);
			FileManager.copyFile(TEMPLATE_CITATION, out, true);
			FileManager.copyFile(TEMPLATE_FOOTER, out, true);

			Source s = model.getSources().getById(c.getSourceId());
			Auteur a = model.getAuteurs().getById(s.getAuteurId());
			
			c.replace(out);
			a.replace(out);
			s.replace(out);
			count++;
		}
		System.out.println("CITATIONS - FIN");
		return count;
	}
	
	public static int generateSource(Model model){
		int count = 0;
		System.out.println("SOURCES - DEBUT");
		// Pour chaque sources on crée la page HTML associée en complétant le template
		for (Source s : model.getSources().getSources()) {
			System.out.println("	source : " + s.getId());
			File out = new File(PATH_SOURCE + s.getIdString() + ".html");
			FileManager.copyFile(TEMPLATE_HEADER, out, false);
			FileManager.copyFile(TEMPLATE_SOURCE, out, true);
			FileManager.copyFile(TEMPLATE_FOOTER, out, true);
			s.replace(out);
			count++;
		}
		System.out.println("SOURCES - FIN");
		
		
		return count;
	}
	public static int generateAuteur(Model model){
		int count = 0;
		System.out.println("AUTEURS - DEBUT");
		// Pour chaque auteur on crée la page HTML associée en complétant le template
		for (Auteur a : model.getAuteurs().getAuteurs()) {
			System.out.println("	auteur : " + a.getId());
			File out = new File(PATH_AUTEUR + a.getIdString() + ".html");
			FileManager.copyFile(TEMPLATE_HEADER, out, false);
			FileManager.copyFile(TEMPLATE_AUTEUR, out, true);
			FileManager.copyFile(TEMPLATE_FOOTER, out, true);
			a.replace(out);
			count++;
		}
		System.out.println("AUTEURS - FIN");
		return count;
	}
}
