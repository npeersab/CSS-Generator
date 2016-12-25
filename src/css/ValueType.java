package css;

public enum ValueType {
	COLOR(""), DOUBLE(""), INTEGER(""), PIXEL("px"), STRING(""), TIME("s");
	
	private String unit;
	ValueType(String unit) {
		this.unit = unit;
	}
	
	public String getUnit() {
		return unit;
	}
}
