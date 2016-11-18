package css;

import java.util.LinkedList;

public class PropertyDetailsList {
	private static LinkedList<PropertyDetails> propertyDetails;
	public static final String COLOR = "Color", STRING = "String", DOUBLE = "Double", PIXEL = "Pixel", INITIAL = "initial",
			INHERIT = "inherit", TOP = "top", BOTTOM = "bottom", RIGHT = "right", LEFT = "left", CENTER = "center",
			INTEGER = "Integer", NONE = "none", BOTH = "both", BORDER_STYLE[] = new String[] {
					NONE, "hidden", "dotted", "dashed", "solid", "double", "groove", "ridge", "inset", "outset",
					INITIAL, INHERIT						
			};
	public static final Range<Integer> RADIUS_RANGE = new Range<Integer>(0, 100), BORDER_RANGE = new Range<Integer>(0, 20);
	
	static {
		propertyDetails = new LinkedList<PropertyDetails>();
			
		propertyDetails.add(new PropertyDetails(
				"color", "Sets the color of text", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"opacity", "Sets the opacity level for an element", DOUBLE, new Range<Double>(0.0, 1.0)));
		
		propertyDetails.add(new PropertyDetails(
				"background-attachment", "Sets whether a background image is fixed or scrolls with the rest of the page",
				STRING, new String[] {
						"scroll", "fixed", "local", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails(
				"background-blend-mode", "Specifies the blending mode of each background layer (color/image)",
				STRING, new String[] {
						"normal", "multiply", "screen", "overlay", "darken", "lighten", "color-dodge",
						"saturation", "color", "luminosity"
				}));
		
		propertyDetails.add(new PropertyDetails(
				"background-color", "Specifies the background color of an element", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"background-image", "Specifies one or more background images for an element", STRING));
		
		propertyDetails.add(new PropertyDetails(
				"background-position", "Specifies the position of a background image", STRING,
				new String[] {
						LEFT + " " + TOP, LEFT + " " + CENTER, LEFT + " " + BOTTOM, RIGHT + " " + TOP,
						RIGHT + " " + CENTER, RIGHT + " " + BOTTOM, CENTER + " " + TOP, CENTER + " " + CENTER,
						CENTER + " " + BOTTOM	
				}));
		
		propertyDetails.add(new PropertyDetails(
				"background-repeat", "Sets how a background image will be repeated", STRING,
				new String[] {
						"repeat", "repeat-x", "repeat-y", "no-repeat", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails(
				"background-clip", "Specifies the painting area of the background", STRING, 
				new String[] {
						"border-box", "padding-box", "content-box", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails(
				"background-origin", "Specifies where the background image(s) is/are positioned", STRING, 
				new String[] {
						"padding-box", "border-box", "content-box", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails("background-size", "Specifies the size of the background image(s)",
				STRING, new String[]{
						"auto", "cover", "contain", INITIAL, INHERIT						
				}));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-color", "Sets the color of the bottom border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-left-radius", "Defines the shape of the border of the bottom-left corner",
				PIXEL, RADIUS_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-right-radius", "Defines the shape of the border of the bottom-right corner", PIXEL, 
				RADIUS_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-style", "Sets the style of the bottom border", STRING, BORDER_STYLE));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-width", "Sets the width of the bottom border", PIXEL, BORDER_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-color", "Sets the color of the four borders", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-left-color", "Sets the color of the left border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-left-style", "Sets the style of the left border", STRING, BORDER_STYLE));
		
		propertyDetails.add(new PropertyDetails(
				"border-left-width", "Sets the width of the left border", PIXEL, BORDER_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-radius", "A shorthand property for setting all the four border-*-radius properties",
				PIXEL, RADIUS_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-right-color", "Sets the color of the right border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-right-style", "Sets the style of the right border", STRING, BORDER_STYLE));
		
		propertyDetails.add(new PropertyDetails(
				"border-right-width", "Sets the width of the right border", PIXEL, BORDER_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-style", "Sets the style of the four borders", STRING, BORDER_STYLE));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-color", "Sets the color of the top border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-left-radius", "Defines the shape of the border of the top-left corner", PIXEL, RADIUS_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-right-radius", "Defines the shape of the border of the top-right corner", PIXEL, 
				RADIUS_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-style", "Sets the style of the top border", STRING, BORDER_STYLE));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-width", "Sets the width of the top border", PIXEL, BORDER_RANGE));
		
		propertyDetails.add(new PropertyDetails(
				"border-width", "Sets the width of the four borders", PIXEL, BORDER_RANGE));
		
		
		propertyDetails.add(new PropertyDetails(
				"bottom", "Specifies the bottom position of a positioned element", PIXEL, new Range<Integer>(-100, 10)));
		
		propertyDetails.add(new Pr)
	}
}

class PropertyDetails {
	String name, description, valueType, possibleValues[];
	Range range;
	
	public PropertyDetails(String name, String description, String valueType, Range range, String possibleValues[]) {
		this.name = name;
		this.description = description;
		this.valueType = valueType;
		this.range = range;
		this.possibleValues = possibleValues;
	}
	
	public PropertyDetails(String name, String description, String valueType) {
		this(name, description, valueType, null, null);
	}

	public PropertyDetails(String name, String description, String valueType, Range range) {
		this(name, description, valueType, range, null);
	}

	public PropertyDetails(String name, String description, String valueType, String[] possibleValues) {
		this(name, description, valueType, null, possibleValues);
	}
	
}

class Range<type> {
	type min, max;
	
	public Range(type min, type max) {
		this.min = min;
		this.max = max;
	}
}