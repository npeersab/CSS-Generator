package css;

public class Property {
	
	private String name, value;
	public Property next;
	
	Property(String name) {
		
		this.name = name;
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
