package css;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;
import css.Property;

public class Selector {
	private String name;
	private SelectorType type;
	private LinkedList<Property> propertiesList;

	// constructor
	public Selector(String name, SelectorType type) {
		this.name = name;
		this.type = type;
		
		propertiesList = new LinkedList<Property>();
	}
	
	// add new property in list
	public void addProperty(Property property) {
		propertiesList.add(property);
	}
	
	// add new property in list by name and value
	public void addProperty(String name, String value) {
		addProperty(new Property(name, value));
	}
	
	// remove property  from list
	public void removeProperty(Property property) {
		propertiesList.remove(property);
	}
	
	// return code for selector
	public String getCode() {
		StringBuffer buff = new StringBuffer();
		Iterator<Property> iterator = propertiesList.iterator();
					
		buff.append(name + " {");
		
		while (iterator.hasNext())
			buff.append(iterator.next().getCode());
		buff.append("\n}");
		
		return buff.toString();
	}
	
	// return tree of selector
	public DefaultMutableTreeNode getTree() {
		DefaultMutableTreeNode prop = new DefaultMutableTreeNode(this);
		Iterator<Property> iterator = propertiesList.iterator();
		
		while (iterator.hasNext())
			prop.add(new DefaultMutableTreeNode(iterator.next()));
					
		return prop;
	}
	
	// return name of selector
	public String toString() {
		return name; 
	}
	
	// return type of selector
	public SelectorType getType() {
		return type;
	}
	
	// return list of properties
	public LinkedList<Property> getPropertiesList() {
		return propertiesList;
	}
}

