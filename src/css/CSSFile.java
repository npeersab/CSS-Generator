package css;

import css.CSSSelector;
import css.Property;
import main.mainclass;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

public class CSSFile {

	private File file ;
	private CSSSelector selector;

	public CSSFile(File newfile) {

		file = newfile;
	}

	public void ReadFile() {

		if(file.canRead()) {

			FileReader filereader = null;
			try {
				filereader = new FileReader(file);
			}
			catch(FileNotFoundException e) {

				JOptionPane.showMessageDialog(mainclass.frame, "Unable to open file", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			BufferedReader br = new BufferedReader(filereader);
			int c;
			StringBuffer buff = new StringBuffer();
			CSSSelector temp_selector = null;
			Property temp_prop = null;
			String type = "Element Type Selector";

			try {
				while ((c = br.read()) != -1) {
					char ch = (char) c;

					switch(ch) {

						case '{' :
							temp_selector = new CSSSelector(buff.toString().trim(), type);
							buff.delete(0, buff.length());
							type = "Element Type Selector";
							break;

						case '}' :
							add(temp_selector);
							break;

						case ':' :
							temp_prop = new Property(buff.toString().trim());
							buff.delete(0, buff.length());
							break;

						case ';' :
							temp_prop.setValue(buff.toString().trim());
							buff.delete(0, buff.length());
							temp_selector.add(temp_prop);
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
			}
			catch(IOException e) {
				JOptionPane.showMessageDialog(mainclass.frame, "Unable to read file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} // if
	} // ReadFile

	public void add(CSSSelector selector) {

		if(this.selector == null) {
			this.selector = selector; 
		}
		else { 
			CSSSelector temp = this.selector;
			while(temp.next != null)
				temp = temp.next;
			temp.next = selector;
		}
	}

	public void WriteFile() throws Exception {

		String home = System.getProperty("user.home");
		File cssdir = new File(home, "css_generator");
		if(!cssdir.exists()) 
			cssdir.mkdir();

		FileWriter filewriter = new FileWriter(file);
		filewriter.write(this.toString());
		filewriter.close();
	} 

	public String toString() {

		CSSSelector temp = selector;
		StringBuffer buff= new StringBuffer();

		while(temp != null) {
			buff.append(temp + "\n\n");
			temp = temp.next;
		}
		
		return buff.toString();
	}
	
	public DefaultMutableTreeNode getTree() {
		
		DefaultMutableTreeNode selectors = new DefaultMutableTreeNode(file.getName());
		CSSSelector temp = selector;
		
		while(temp != null) {
			selectors.add(temp.getTree());
			temp = temp.next;
		}
			
		return selectors;
	}
}
