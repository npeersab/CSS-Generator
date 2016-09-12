import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CSSFile {

	String filename;
	CSSSelelctor css[];

	public CSSFile(String filenm) throws Exception {

		filename = filenm;
		File file = new File(filename);

		if(file.canRead()) {
			FileReader freader = new FileReader(file);
			BufferedReader br = new BufferedReader(freader);
			
			int c;
			while((c = br.read()) != -1) {
				char ch = (char) c;
				switch(ch) {
					case '{' :
						String 
				}
			}
		}
	}
}

class test {

	public static void main(String args[]) throws Exception {
		
		new CSSFile("test.css");
	}
}
