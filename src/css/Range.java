package css;

public class Range<type> {
	private type min, max;
	
	public Range(type min, type max) {
		this.min = min;
		this.max = max;
	}
	
	public type getMin() {
		return min;
	}
	
	public type getMax() {
		return max;
	}
}