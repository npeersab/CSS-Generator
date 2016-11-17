package css;

import css.CSSSelector;
import css.Property;
import main.MainClass;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

public class CSSFile {
	private File file ;
	private LinkedList<CSSSelector> selectorsList;

	public CSSFile(File newfile) {
		file = newfile;
		selectorsList = new LinkedList<CSSSelector>();
	}

	public CSSFile() {
		this(null);
	}
	
	public void ReadFile() {
		if (file.canRead()) {
			FileReader filereader = null;
			try {
				filereader = new FileReader(file);
			}
			catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(MainClass.frame, "Unable to open file", "Error", JOptionPane.ERROR_MESSAGE);
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

					switch (ch) {

						case '{' :
							temp_selector = new CSSSelector(buff.toString().trim(), type);
							buff.delete(0, buff.length());
							type = "Element Type Selector";
							break;

						case '}' :
							selectorsList.add(temp_selector);
							break;

						case ':' :
							temp_prop = new Property(buff.toString().trim());
							buff.delete(0, buff.length());
							break;

						case ';' :
							temp_prop.setValue(buff.toString().trim());
							buff.delete(0, buff.length());
							temp_selector.addProperty(temp_prop);
							break;

						case '*' :
							type = "Universal Selector";
							buff.append(ch);
							break;

						case '#' :
							type = "ID Selector";
							buff.append(ch);
							break;

						case '.' :
							type = "Class Selector";
							buff.append(ch);
							break;

						default :
							buff.append(ch);
					} // switch
				} // while
				br.close();
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(MainClass.frame,
						"Unable to read file",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} // if
	} // ReadFile

	public void SaveFile() {
		FileWriter filewriter = null;
				
		try {
			filewriter = new FileWriter(file);
			filewriter.write(this.toString());
			filewriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(MainClass.frame,
					"Error While Saving file",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	} 
	
	public void addSelector(CSSSelector selector) {
		selectorsList.add(selector);
	}

	// removes selector by name
	public void removeSelector(String name) {
		Iterator<CSSSelector> iterator = selectorsList.iterator();
		
		while (iterator.hasNext())
			if (iterator.next().getName() == name)
				iterator.remove();
	}
	
	// removes property from selector
	public void removeProperty(String selector, String property) { 
		Iterator<CSSSelector> iterator = selectorsList.iterator();
		CSSSelector temp = null;
		
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getName() == selector) {
				temp.removeProperty(property);
				break;
			}
		}
	}

	// adds a property to the selector
	public void addProperty(String selector, String property, String value) {	
		Iterator<CSSSelector> iterator = selectorsList.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().getName() == selector) {
				
			}
		}
	}

	// returns the selectors in the form of string
	public String toString() {
		Iterator<CSSSelector> iterator = selectorsList.iterator();
		StringBuffer buff= new StringBuffer();

		while (iterator.hasNext())
			buff.append(iterator.next() + "\n\n");
		
		return buff.toString();
	}
	
	public String getName() {
		return file.getName();
	}
	
	public DefaultMutableTreeNode getTree() {
		DefaultMutableTreeNode selectors = new DefaultMutableTreeNode(this.getName());
		Iterator<CSSSelector> iterator = selectorsList.iterator();
				
		while (iterator.hasNext())
			selectors.add(iterator.next().getTree());
			
		return selectors;
	}
}
