package css;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;
import css.Property;

public class Selector {
	private String name;
	private SelectorType type;
	private LinkedList<Property> propertiesList;

	public Selector(String name, SelectorType type) {
		this.name = name;
		this.type = type;
		
		propertiesList = new LinkedList<Property>();
	}
	
	public void addProperty(Property property) {
		propertiesList.add(property);
	}
	
	public void addProperty(String name, String value) {
		addProperty(new Property(name, value));
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		Iterator<Property> iterator = propertiesList.iterator();
					
		buff.append(name + " {");
		
		while (iterator.hasNext())
			buff.append(iterator.next());
		buff.append("\n}");
		
		return buff.toString();
	}
	
	public DefaultMutableTreeNode getTree() {
		DefaultMutableTreeNode prop = new DefaultMutableTreeNode(this.getName());
		Iterator<Property> iterator = propertiesList.iterator();
		
		while (iterator.hasNext())
			prop.add(new DefaultMutableTreeNode(iterator.next().getName()));
					
		return prop;
	}
	
	public String getName() {
		return name; 
	}
	
	public SelectorType getType() {
		return type;
	}

	public void removeProperty(String property) {
		Iterator<Property> iterator = propertiesList.iterator();
	
		while (iterator.hasNext())
			if (iterator.next().getName() == property)
				iterator.remove();
	}
}

