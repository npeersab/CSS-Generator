package css;

import java.util.LinkedList;

public class PropertyDetailsList {
	private static LinkedList<PropertyDetails> propertyDetails;
	public static final String COLOR = "Color", STRING = "String", DOUBLE = "Double",PIXEL = "Pixel",
			INITIAL = "initial", INHERIT = "inherit", TOP = "top", BOTTOM = "bottom", RIGHT = "right",
			LEFT = "left", CENTER = "center", INTEGER = "Integer", NONE = "none", BOTH = "both",
			VISIBLE = "visible", AUTO = "auto", HIDDEN = "hidden", SCROLL = "scroll", FIXED = "fixed",
			BORDER_STYLE_VALUES[] = new String[] {
					NONE, HIDDEN, "dotted", "dashed", "solid", "double", "groove", "ridge", "inset", "outset",
					INITIAL, INHERIT						
			},
			OVERFLOW_VALUES[] = new String[] {
					VISIBLE, HIDDEN, SCROLL, AUTO, INITIAL, INHERIT
			};
	public static final Range<Integer> RANGE_0_TO_100 = new Range<Integer>(0, 100),
			RANGE_0_TO_20 = new Range<Integer>(0, 20), RANGE_0_TO_200 = new Range<Integer>(0, 200),
			RANGE_100_to_100 = new Range<Integer>(-100, 100), RANGE_0_to_150 = new Range<Integer>(0, 150),
			RANGE_50_to_50 = new Range<Integer>(-50, 50);
	
	static {
		propertyDetails = new LinkedList<PropertyDetails>();
			
		propertyDetails.add(new PropertyDetails(
				"color", "Sets the color of text", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"opacity", "Sets the opacity level for an element", DOUBLE, new Range<Double>(0.0, 1.0)));
		
		propertyDetails.add(new PropertyDetails(
				"background-attachment",
				"Sets whether a background image is fixed or scrolls with the rest of the page", STRING,
				new String[] {
						SCROLL , FIXED, "local", INITIAL, INHERIT
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
						RIGHT + " " + CENTER, RIGHT + " " + BOTTOM, CENTER + " " + TOP,
						CENTER + " " + CENTER, CENTER + " " + BOTTOM	
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
				"background-origin", "Specifies where the background image(s) is/are positioned",
				STRING,	new String[] {
						"padding-box", "border-box", "content-box", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails("background-size", 
				"Specifies the size of the background image(s)", STRING, new String[]{
						AUTO, "cover", "contain", INITIAL, INHERIT						
				}));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-color", "Sets the color of the bottom border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-left-radius", "Defines the shape of the border of the bottom-left corner",
				PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-right-radius", "Defines the shape of the border of the bottom-right corner",
				PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-style", "Sets the style of the bottom border", STRING, BORDER_STYLE_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"border-bottom-width", "Sets the width of the bottom border", PIXEL, RANGE_0_TO_20));
		
		propertyDetails.add(new PropertyDetails(
				"border-color", "Sets the color of the four borders", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-left-color", "Sets the color of the left border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-left-style", "Sets the style of the left border", STRING, BORDER_STYLE_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"border-left-width", "Sets the width of the left border", PIXEL, RANGE_0_TO_20));
		
		propertyDetails.add(new PropertyDetails(
				"border-radius", "A shorthand property for setting all the four border-*-radius properties",
				PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"border-right-color", "Sets the color of the right border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-right-style", "Sets the style of the right border", STRING, BORDER_STYLE_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"border-right-width", "Sets the width of the right border", PIXEL, RANGE_0_TO_20));
		
		propertyDetails.add(new PropertyDetails(
				"border-style", "Sets the style of the four borders", STRING, BORDER_STYLE_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-color", "Sets the color of the top border", COLOR));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-left-radius", "Defines the shape of the border of the top-left corner",
				PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-right-radius", "Defines the shape of the border of the top-right corner", PIXEL, 
				RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-style", "Sets the style of the top border", STRING, BORDER_STYLE_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"border-top-width", "Sets the width of the top border", PIXEL, RANGE_0_TO_20));
		
		propertyDetails.add(new PropertyDetails(
				"border-width", "Sets the width of the four borders", PIXEL, RANGE_0_TO_20));
		
		propertyDetails.add(new PropertyDetails(
				"bottom", "Specifies the bottom position of a positioned element", PIXEL, RANGE_100_to_100));
		
		propertyDetails.add(new PropertyDetails(
				"clear", "Specifies which sides of an element where other floating elements are not allowed",
				STRING, new String[] {
					NONE, LEFT, RIGHT, BOTH, INITIAL, INHERIT	
				}));
		
		propertyDetails.add(new PropertyDetails(
				"display", " 	Specifies how a certain HTML element should be displayed", STRING,
				new String[] {
					"inline", "block", "list-item", NONE, INITIAL, INHERIT	
				}));
		
		propertyDetails.add(new PropertyDetails(
				"float", "Specifies whether or not a box should float", STRING, new String[] {
						NONE, LEFT, RIGHT, INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails(
				"height", "Sets the height of an element", PIXEL, new Range<Integer>(0, 1000)));
		
		propertyDetails.add(new PropertyDetails(
				LEFT, "Specifies the left position of a positioned element", PIXEL,
				new Range<Integer>(-100, 100)));
		
		propertyDetails.add(new PropertyDetails(
				"margin-bottom", "Sets the bottom margin of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"margin-left", "Sets the left margin of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"margin-right", "Sets the right margin of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"margin-top", "Sets the top margin of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"max-height", " 	Sets the maximum height of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"max-width", "Sets the maximum width of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"min-height", "Sets the minimum height of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"min-width", "Sets the minimum width of an element", PIXEL, RANGE_0_TO_200));
		
		propertyDetails.add(new PropertyDetails(
				"overflow", "Specifies what happens if content overflows an element's box",
				STRING, OVERFLOW_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"overflow-x",
				"Specifies whether or not to clip the left/right edges of the content, if"
				+ "it overflows the element's content area 3 overflow-y", STRING, OVERFLOW_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"overflow-y", "Specifies whether or not to clip the top/bottom edges of"
				+"the content, if it overflows the element's content area", STRING, OVERFLOW_VALUES));
		
		propertyDetails.add(new PropertyDetails(
				"padding-bottom", "Sets the bottom padding of an element", PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"padding-left", "Sets the left padding of an element", PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"padding-right", "Sets the right padding of an element", PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"padding-top", "Sets the top padding of an element", PIXEL, RANGE_0_TO_100));
		
		propertyDetails.add(new PropertyDetails(
				"position", "Specifies the type of positioning method used for an element", STRING, 
				new String[] {
						"static", "absolute", FIXED, "relative", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails(
				RIGHT, "Specifies the right position of a positioned element", PIXEL, RANGE_100_to_100));
		
		propertyDetails.add(new PropertyDetails(TOP, "Specifies the top position of a positioned element",
				PIXEL, RANGE_100_to_100));
		
		propertyDetails.add(new PropertyDetails(
				"visibility", "Specifies whether or not an element is visible", STRING, new String[] {
						VISIBLE, HIDDEN, "collapse", INITIAL, INHERIT
				}));
		
		propertyDetails.add(new PropertyDetails(
				"width", "Sets width of an element", PIXEL, RANGE_0_to_150));
		
		propertyDetails.add(new PropertyDetails(
				"vertical-align", "Sets the vertical alignment of an element", PIXEL, RANGE_50_to_50));
		
		propertyDetails.add(new PropertyDetails(
				"z-index", "Sets the stack order of a positioned element", INTEGER, RANGE_50_to_50));
		
		propertyDetails.add(new P)
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