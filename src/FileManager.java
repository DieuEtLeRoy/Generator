import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;


public class FileManager {
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
