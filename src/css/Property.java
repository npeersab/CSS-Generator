package css;

public class Property {
	private String name, value;
		
	public Property(String name) {
		this.name = name;
	}
	
	public Property(String name, String value) {
		this(name);
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return "\n\t" + name + " : " + value + ";";
	}
	
	public String getName() {
		return name;
	}
}
