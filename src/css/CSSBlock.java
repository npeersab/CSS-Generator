package css;

import css.Property;

public class CSSBlock {
	
	String name;
	Property properties[] = new Property[50];
	int PropertyCount;

	public CSSBlock(String name) {
		
		this.name = name;
	}
	
	public void add(Property property) {
		
		properties[PropertyCount++] = property;
	}
	
	public String toString() {
		
		StringBuffer buff = new StringBuffer();
		
		buff.append(name + " {");
		for(int i = 0; i < PropertyCount; i++) {
			buff.append(properties[i]);
		}
		buff.append("\n}");
		
		return buff.toString();
	}
}
