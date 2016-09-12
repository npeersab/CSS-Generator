package css;

import css.Property;

public class CSSBlock {
	
	String name;
	Property properties[];

	public CSSBlock(String nm, Property prop[]) {
		
		name = nm;
		properties = prop;
	}
}
