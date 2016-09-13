package css;

import css.CSSSelector;
import css.Property;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

public class CSSFile {

	String name;
	CSSSelector block;
	
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
			CSSSelector temp_block = null;
			Property temp_prop = null;
			String type = "Element Type Selector";
			
			while ((c = br.read()) != -1) {
				char ch = (char) c;
				
				switch(ch) {
					
					case '{' :
						temp_block = new CSSSelector(buff.toString().trim(), type);
						buff.delete(0, buff.length());
						type = "Element Type Selector";
						break;
						
					case '}' :
						add(temp_block);
						break;
						
					case ':' :
						temp_prop = new Property(buff.toString().trim());
						buff.delete(0, buff.length());
						break;
						
					case ';' :
						temp_prop.setValue(buff.toString().trim());
						buff.delete(0, buff.length());
						temp_block.add(temp_prop);
						break;
						
					case '*' :
						type = "Universal Selector";
						break;
						
					case '#' :
						type = "ID Selector";
						break;
						
					case '.' :
						type = "Class Selector";
						break;
						
					default :
						buff.append(ch);
				} // switch
			} // while
			br.close();
		} // if
	} // ReadFile
	
	public void add(CSSSelector block) {
		
		if(this.block == null) {
			this.block = block; 
		}
		else { 
			CSSSelector temp = this.block;
			while(temp.next != null)
				temp = temp.next;
			temp.next = block;
		}
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
		
		CSSSelector temp = block;
		StringBuffer buff= new StringBuffer();
		
		while(temp != null) {
			buff.append(temp + "\n\n");
			temp = temp.next;
		}
		return buff.toString();
	}
}
