package css;

import css.Selector;
import dialog.DialogBox;
import css.Property;
import main.MainClass;
import main.MainFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CSSFile {
	private File file ;
	private LinkedList<Selector> selectorsList;

	// reference to parent frame
	private MainFrame parent;

	// hash map to store selector and tree nodes
	private HashMap<Selector, DefaultMutableTreeNode> selectorHashMap;
	
	// selector node
	private DefaultMutableTreeNode selectorsNode;

	public CSSFile(MainFrame parent, File newfile) {
		this.parent = parent;
		file = newfile;
		selectorsList = new LinkedList<Selector>();
		selectorHashMap = new HashMap<Selector, DefaultMutableTreeNode>();
		selectorsNode = new DefaultMutableTreeNode(getName());
	}

	public void ReadFile() {
		if (file.canRead()) {
			FileReader filereader = null;
			try {
				filereader = new FileReader(file);
			}
			catch (FileNotFoundException e) {
				DialogBox dialogBox = new DialogBox(parent, "Unable to Read File", DialogBox.WARNING);
				dialogBox.showDialogBox();
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

	public void saveFile() {
		FileWriter filewriter = null;

		try {
			filewriter = new FileWriter(file);
			filewriter.write(this.toString());
			filewriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(MainClass.frame, "Error While Saving file",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	} 

	// add new selector
	public void addSelector(Selector selector, DefaultMutableTreeNode node) {
		selectorHashMap.put(selector, node);
		selectorsList.add(selector);
	}

	// removes selector from the list
	public void removeSelector(Selector selector) {
		selectorsList.remove(selector);
		if (selectorHashMap.containsKey(selector)) {
			DefaultMutableTreeNode node = selectorHashMap.get(selector);
			if (node != null) {
				DefaultTreeModel model = (DefaultTreeModel) parent.getCssTree().getModel();
				if (node.getParent() != null)
					model.removeNodeFromParent(node);
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

	// return tree node of file
	public DefaultMutableTreeNode getTree() {
		Iterator<Selector> iterator = selectorsList.iterator();

		while (iterator.hasNext()) {
			Selector selector = iterator.next(); 
			DefaultMutableTreeNode selectorNode = selector.getTree();
			selectorsNode.add(selectorNode);
			selectorHashMap.put(selector, selectorNode);
		}

		return selectorsNode;
	}

	// check whether the file contains same selector as parameter
	public boolean contains(Selector selector) {
		Iterator<Selector> iterator = selectorsList.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().toString().equals(selector.toString()))
				return true;
		}
		return false;
	}

	// removes all selector with same name of parameter
	public void removeAllSelector(Selector selector) {
		Iterator<Selector> iterator = selectorsList.iterator();
		loop: while (iterator.hasNext()) {
			Selector selector2 = iterator.next();
			if (selector2.toString().equals(selector.toString())) {
				parent.removeSelector(selector2);
				removeAllSelector(selector);
				break loop;
			}
		}
	}

	// getter and setter for file
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

	// getter for selectorList
	public LinkedList<Selector> getSelectorsList() {
		return selectorsList;
	}
	
	// getter and setter for selector node
	public DefaultMutableTreeNode getSelectorsNode() {
		return selectorsNode;
	}
	
	public void setSelectorsNode(DefaultMutableTreeNode selectorsNode) {
		this.selectorsNode = selectorsNode;
	}
}
