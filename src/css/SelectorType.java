package css;

public enum SelectorType {
	class_selector("Class Selector"),
	element_type_selector("Element Type Selector"),
	id_selector("ID Selector"),
	universal_selector("Universal Selector");
	
	private String name;
	
	private SelectorType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
