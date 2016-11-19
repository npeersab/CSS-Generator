package css;

public class Range<type> {
	private type min, max;
	
	public Range(type min, type max) {
		this.min = min;
		this.max = max;
	}
	
	type getMin() {
		return min;
	}
	
	type getMax() {
		return max;
	}
}