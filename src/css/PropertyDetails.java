package css;

public class PropertyDetails {
	String name, description, possibleValues[];
	ValueType valueType;
	Range<?> range;
	
	public PropertyDetails(String name, String description, ValueType valueType, Range<?> range, String possibleValues[]) {
		this.name = name;
		this.description = description;
		this.valueType = valueType;
		this.range = range;
		this.possibleValues = possibleValues;
	}
	
	public PropertyDetails(String name, String description, ValueType valueType) {
		this(name, description, valueType, null, null);
	}

	public PropertyDetails(String name, String description, ValueType valueType, Range<?> range) {
		this(name, description, valueType, range, null);
	}

	public PropertyDetails(String name, String description, ValueType valueType, String[] possibleValues) {
		this(name, description, valueType, null, possibleValues);
	}
	
}

