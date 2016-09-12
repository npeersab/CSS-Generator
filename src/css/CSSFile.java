package css;

import css.CSSBlock;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class CSSFile {

	String name;
	CSSBlock block[] = new CSSBlock[100];
	int BlockCount;
	
	public CSSFile(String nm) throws Exception {
		
		name = nm;
		File file = new File(name);
		
		if(file.canRead()) {
			FileReader filereader = new FileReader(file); 
			BufferedReader br = new BufferedReader(filereader);
			
			int c;
			while ((c = br.read()) != -1) {
				char ch = (char) c;
				
				switch(ch) {
					
					case '{' :
						break;
						
					case '}' :
						break;
						
					case ':' :
						break;
						
					case ';' :
						break;
						
					default :
						
				}
			}
		}
	}
}
