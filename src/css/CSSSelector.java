package css;

import javax.swing.tree.DefaultMutableTreeNode;

import css.Property;

public class CSSSelector {
	
	private String name, type;
	private Property property;
	CSSSelector next;

	public CSSSelector(String name, String type) {
		
		if(type.equals("Universal Selector"))
			this.name = "*";
		else
			this.name = name;
		this.type = type;
	}
	
	public void add(Property property) {
		
		if(this.property == null) {
			this.property = property;
		}
		else {
			Property temp = this.property;
			while(temp.next != null)
				temp = temp.next;
			temp.next = property;
		}
	}
	
	public String toString() {
		
		StringBuffer buff = new StringBuffer();
		Property temp = property;
	
		buff.append(name + " {");
		
		while (temp != null) {
			buff.append(temp);
			temp = temp.next;
		}
		
		buff.append("\n}");
		
		return buff.toString();
	}
	
	public DefaultMutableTreeNode getTree() {
		
		DefaultMutableTreeNode prop = new DefaultMutableTreeNode(name);
		Property temp = property;
		
		while(temp != null) {
			
			prop.add(new DefaultMutableTreeNode(temp.getName()));
			temp = temp.next;
		}
		
		return prop;
	}
	
	public String getName() {
		
		return name; 
	}
}
