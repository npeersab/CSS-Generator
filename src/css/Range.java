package css;

public class Range<type> {
	private type min, max;
	
	// constructor
	public Range(type min, type max) {
		this.min = min;
		this.max = max;
	}
	
	// return minimum value
	public type getMin() {
		return min;
	}
	
	// return maximum value
	public type getMax() {
		return max;
	}
}