package css;

import css.Selector;
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
	private LinkedList<Selector> selectorsList;

	public CSSFile(File newfile) {
		file = newfile;
		selectorsList = new LinkedList<Selector>();
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
			Selector temp_selector = null;
			Property temp_prop = null;
			SelectorType type = SelectorType.element_type_selector;

			try {
				while ((c = br.read()) != -1) {
					char ch = (char) c;

					switch (ch) {

						case '{' :
							temp_selector = new Selector(buff.toString().trim(), type);
							buff.delete(0, buff.length());
							type = SelectorType.element_type_selector;
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
							type = SelectorType.universal_selector;
							buff.append(ch);
							break;

						case '#' :
							type = SelectorType.id_selector;
							buff.append(ch);
							break;

						case '.' :
							type = SelectorType.class_selector;
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
	
	public void addSelector(Selector selector) {
		selectorsList.add(selector);
	}

	// removes selector from the list
	public void removeSelector(Selector selector) {
			selectorsList.remove(selector);
	}
	
	// adds a property to the selector
	public void addProperty(String selector, String property, String value) {	
		Iterator<Selector> iterator = selectorsList.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().toString() == selector) {
				
			}
		}
	}

	// returns the selectors in the form of string
	public String toString() {
		Iterator<Selector> iterator = selectorsList.iterator();
		StringBuffer buff= new StringBuffer();

		while (iterator.hasNext())
			buff.append(iterator.next().getCode() + "\n\n");
		
		return buff.toString();
	}
	
	public String getName() {
		return file.getName();
	}
	
	public DefaultMutableTreeNode getTree() {
		DefaultMutableTreeNode selectors = new DefaultMutableTreeNode(this.getName());
		Iterator<Selector> iterator = selectorsList.iterator();
				
		while (iterator.hasNext())
			selectors.add(iterator.next().getTree());
			
		return selectors;
	}
}
