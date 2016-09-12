package css;

import css.Property;

public class CSSSelector {
	
	String name, type;
	Property property;
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

}
