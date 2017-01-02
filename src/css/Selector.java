package css;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import css.Property;
import main.MainFrame;

public class Selector {
	// properties of selector
	private String name;
	private SelectorType type;
	
	// to store list of properties
	private LinkedList<Property> propertiesList;
	
	// to map properties with tree nodes
	private HashMap<Property, DefaultMutableTreeNode> propertyHashMap;

	// constructor
	public Selector(String name, SelectorType type) {
		this.name = name;
		this.type = type;
		
		propertiesList = new LinkedList<Property>();
		propertyHashMap = new HashMap<Property, DefaultMutableTreeNode>();
	}
	
	// add new property in list
	public void addProperty(Property property) {
		propertiesList.add(property);
	}
	
	// add new property with node
	public void addProperty(Property property, DefaultMutableTreeNode node) {
		addProperty(property);
		propertyHashMap.put(property, node);
	}
	
	// remove property  from list
	public void removeProperty(MainFrame parent, Property property) {
		propertiesList.remove(property);
		if (propertyHashMap.containsKey(property)) {
			DefaultMutableTreeNode node = propertyHashMap.get(property);
			if (node != null) {
				DefaultTreeModel model = (DefaultTreeModel) parent.getCssTree().getModel();			
				if (node.getParent() != null)
					model.removeNodeFromParent(node);
			}
		}
	}
	
	// remove all properties with name as parameter
	public void removeAllProperties(MainFrame parent, Property property) {
		Iterator<Property> iterator = propertiesList.iterator();
		loop: while (iterator.hasNext()) {
			Property property2 = iterator.next();
			if (property2.getName().equals(property.getName())) {
				removeProperty(parent, property2);
				removeAllProperties(parent, property2);
				break loop;
			}
		}
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
		DefaultMutableTreeNode properties = new DefaultMutableTreeNode(this);
		Iterator<Property> iterator = propertiesList.iterator();
		
		while (iterator.hasNext()) {
			Property property = iterator.next();
			DefaultMutableTreeNode propertynode = new DefaultMutableTreeNode(property);
			properties.add(propertynode);
			propertyHashMap.put(property, propertynode);
		}
		return properties;
	}
	
	// check whether selector contains the property
	public boolean contains(Property property) {
		Iterator<Property> iterator = propertiesList.iterator();
		while (iterator.hasNext())
			if (iterator.next().getName().equals(property.getName()))
				return true;
		return false;
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

