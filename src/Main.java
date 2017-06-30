import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	static File jsonFile = new File("data.json");
	static final File TEMPLATE_INDEX_HEADER = new File("indexHeader.html");
	static final File TEMPLATE_INDEX_TOREPEAT = new File("indexToRepeat.html");
	static final File TEMPLATE_INDEX_FOOTER = new File("indexFooter.html");
	static final File TEMPLATE_CITATION = new File("citation.html");
	static final String PATH_SITE = "site/";
	static final String PATH_CITATION = PATH_SITE + "citation/";

	public static void main(String[] args) throws IOException {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String data = loadFile(jsonFile);
		final Citations citations = gson.fromJson(data, Citations.class);

		System.out.println(citations);

		//Gestion de l'Index
		String index = PATH_SITE + "index.html";
		copyFile(TEMPLATE_INDEX_HEADER, new File(index));
		
		FileWriter fw = new FileWriter(index, true); 
		BufferedWriter bw = new BufferedWriter(fw); 
		PrintWriter end = new PrintWriter(bw); 
		
		for (Citation c : citations.getCitations()) {
			System.out.println("index, citation : " + c.getIdString());
			String temp = "temp";
			copyFile(TEMPLATE_INDEX_TOREPEAT, new File(temp));
			replace(temp, ":auteur", c.getAuteur());
			replace(temp, ":contenu", c.getContenu());
			replace(temp, ":source", c.getSource());
			replace(temp, ":id", c.getIdString());
			
			end.println(loadFile(new File(temp)));
		}
		end.println(loadFile(TEMPLATE_INDEX_FOOTER));
		end.close();
		bw.close();
		fw.close();
		
		
				
		// Pour chaque citation on crée la page HTML associée en complétant le template
		for (Citation c : citations.getCitations()) {
			String out = PATH_CITATION + c.getIdString() + ".html";

			System.out.println("Copie de la citation : " + c.getId());
			copyFile(TEMPLATE_CITATION, new File(out));
			replace(out, ":auteur", c.getAuteur());
			replace(out, ":contenu", c.getContenu());
			replace(out, ":source", c.getSource());
			replace(out, ":id", c.getIdString());

		}

		
		
	}

	public static void replaceAll(){
		
	}

	public static void copyFile(File src, File dest) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(src));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
		byte[] buf = new byte[4096];
		int n;
		while ((n = in.read(buf, 0, buf.length)) > 0)
			out.write(buf, 0, n);

		in.close();
		out.close();
	}

	public static void replace(String file, String in, String out) throws IOException {
		Path path = Paths.get(file);
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll(in, out);
		Files.write(path, content.getBytes(charset));
	}

	public static String loadFile(File f) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
			StringWriter out = new StringWriter();
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
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
