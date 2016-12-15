package css;

public class PropertyDetails {
	private String name, description, possibleValues[];
	private ValueType valueType;
	private Range<?> range;
	
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
	
	public String toString() {
		return name;
	}
	
	public ValueType getType() {
		return valueType;
	}
	
	public String[] getPossibleValues() {
		return possibleValues;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Range<?> getRange() {
		return range;
	}
	
	public String getName() {
		return name;
	}
}

