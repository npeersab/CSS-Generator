package css;

import css.CSSBlock;
import css.Property;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

public class CSSFile {

	String name;
	CSSBlock block[] = new CSSBlock[50];
	int BlockCount;
	
	public CSSFile(String filename) {
		
		name = filename;
	}
	
	public void ReadFile() throws Exception {
		
		String home = System.getProperty("user.home");
		File cssdir = new File(home, "css_generator");
		
		File file = new File(cssdir, name);
		
		if(file.canRead()) {
			FileReader filereader = new FileReader(file); 
			BufferedReader br = new BufferedReader(filereader);
			
			int c;
			StringBuffer buff = new StringBuffer();
			CSSBlock tempblock = null;
			Property tempprop = null;
			
			while ((c = br.read()) != -1) {
				char ch = (char) c;
				
				switch(ch) {
					
					case '{' :
						tempblock = new CSSBlock(buff.toString());
						buff.delete(0, buff.length());
						break;
						
					case '}' :
					//	System.out.println(tempblock);
						add(tempblock);
						break;
						
					case ':' :
						tempprop = new Property(buff.toString());
						buff.delete(0, buff.length());
						break;
						
					case ';' :
						tempprop.setValue(buff.toString());
						buff.delete(0, buff.length());
						tempblock.add(tempprop);
						break;
						
					case '\n' :
						break;
						
					default :
						buff.append(ch);
				} // switch
			} // while
			br.close();
		} // if
	} // ReadFile
	
	public void add(CSSBlock block) {
		
		this.block[BlockCount++] = block;
	}

	public void WriteFile() throws Exception {
		
		String home = System.getProperty("user.home");
		File cssdir = new File(home, "css_generator");
		if(!cssdir.exists()) 
			cssdir.mkdir();
		File file = new File(cssdir, name);
		
		FileWriter filewriter = new FileWriter(file);
		filewriter.write(this.toString());
		filewriter.close();
	} 
	
	public String toString() {
		
		StringBuffer buff= new StringBuffer();
		for(int i = 0; i < BlockCount; i++)
			buff.append(block[i] + "\n");
		return buff.toString();
	}
}
