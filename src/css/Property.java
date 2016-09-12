package css;

public class Property {
	
	String name, value;
	Property next;
	
	Property(String name) {
		
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		
		return "\n\t" + name + " : " + value + ";";
	}
}
