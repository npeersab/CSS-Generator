package css;

public class Property {
	private String name, value, description;
		
	public Property(String name) {
		this.name = name;
	}
	
	public Property(String name, String value) {
		this(name, value, PropertyDetailsList.getDescription(name));
	}
	
	public Property(String name, String value, String description) {
		this.name = name;
		this.value = value;
		this.description = description;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getCode() {
		return "\n\t" + name + ": " + value + ";";
	}
	
	public String toString() {
		return name;
	}

	// getter and setter for description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}