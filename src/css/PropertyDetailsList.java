package css;

import java.util.LinkedList;

public class PropertyDetailsList {
	private static LinkedList<PropertyDetails> color, backgroundBorder, basicBox, flexibleBoxLayout,
	textProperties;
	public static final String INITIAL = "initial", INHERIT = "inherit", TOP = "top",
			BOTTOM = "bottom", RIGHT = "right", LEFT = "left", CENTER = "center", NONE = "none",
			BOTH = "both",
			VISIBLE = "visible", AUTO = "auto", HIDDEN = "hidden", SCROLL = "scroll", FIXED = "fixed",
			STRETCH = "stretch", FLEX_START = "flex-start", FLEX_END = "flex-end", BASELINE = "baseline",
			BORDER_STYLE_VALUES[] = new String[] {
					NONE, HIDDEN, "dotted", "dashed", "solid", "ValueType.DOUBLE", "groove", "ridge", "inset", "outset",
					INITIAL, INHERIT						
			},
			OVERFLOW_VALUES[] = new String[] {
					VISIBLE, HIDDEN, SCROLL, AUTO, INITIAL, INHERIT
			};
	public static final Range<Integer> RANGE_0_TO_100 = new Range<Integer>(0, 100),
			RANGE_0_TO_20 = new Range<Integer>(0, 20), RANGE_0_TO_200 = new Range<Integer>(0, 200),
			RANGE_100_to_100 = new Range<Integer>(-100, 100), RANGE_0_to_150 = new Range<Integer>(0, 150),
			RANGE_50_to_50 = new Range<Integer>(-50, 50), RANGE_20_to_20 = new Range<Integer>(-20, 20);
	
	static {
		color = new LinkedList<PropertyDetails>();
			
		color.add(new PropertyDetails(
				"color", "Sets the color of text", ValueType.COLOR));
		
		backgroundBorder = new LinkedList<PropertyDetails>();
		
		backgroundBorder.add(new PropertyDetails(
				"opacity", "Sets the opacity level for an element", ValueType.DOUBLE, new Range<Double>(0.0, 1.0)));
		
		backgroundBorder.add(new PropertyDetails(
				"background-attachment",
				"Sets whether a background image is fixed or scrolls with the rest of the page", ValueType.STRING,
				new String[] {
						SCROLL , FIXED, "local", INITIAL, INHERIT
				}));
		
		backgroundBorder.add(new PropertyDetails(
				"background-blend-mode", "Specifies the blending mode of each background layer (color/image)",
				ValueType.STRING, new String[] {
						"normal", "multiply", "screen", "overlay", "darken", "lighten", "color-dodge",
						"saturation", "color", "luminosity"
				}));
		
		backgroundBorder.add(new PropertyDetails(
				"background-color", "Specifies the background color of an element", ValueType.COLOR));
		
		backgroundBorder.add(new PropertyDetails(
				"background-image", "Specifies one or more background images for an element", ValueType.STRING));
		
		backgroundBorder.add(new PropertyDetails(
				"background-position", "Specifies the position of a background image", ValueType.STRING,
				new String[] {
						LEFT + " " + TOP, LEFT + " " + CENTER, LEFT + " " + BOTTOM, RIGHT + " " + TOP,
						RIGHT + " " + CENTER, RIGHT + " " + BOTTOM, CENTER + " " + TOP,
						CENTER + " " + CENTER, CENTER + " " + BOTTOM	
				}));
		
		backgroundBorder.add(new PropertyDetails(
				"background-repeat", "Sets how a background image will be repeated", ValueType.STRING,
				new String[] {
						"repeat", "repeat-x", "repeat-y", "no-repeat", INITIAL, INHERIT
				}));
		
		backgroundBorder.add(new PropertyDetails(
				"background-clip", "Specifies the painting area of the background", ValueType.STRING, 
				new String[] {
						"border-box", "padding-box", "content-box", INITIAL, INHERIT
				}));
		
		backgroundBorder.add(new PropertyDetails(
				"background-origin", "Specifies where the background image(s) is/are positioned",
				ValueType.STRING,	new String[] {
						"padding-box", "border-box", "content-box", INITIAL, INHERIT
				}));
		
		backgroundBorder.add(new PropertyDetails("background-size", 
				"Specifies the size of the background image(s)", ValueType.STRING, new String[]{
						AUTO, "cover", "contain", INITIAL, INHERIT						
				}));
		
		backgroundBorder.add(new PropertyDetails(
				"border-bottom-color", "Sets the color of the bottom border", ValueType.COLOR));
		
		backgroundBorder.add(new PropertyDetails(
				"border-bottom-left-radius", "Defines the shape of the border of the bottom-left corner",
				ValueType.PIXEL, RANGE_0_TO_100));
		
		backgroundBorder.add(new PropertyDetails(
				"border-bottom-right-radius", "Defines the shape of the border of the bottom-right corner",
				ValueType.PIXEL, RANGE_0_TO_100));
		
		backgroundBorder.add(new PropertyDetails(
				"border-bottom-style", "Sets the style of the bottom border", ValueType.STRING, BORDER_STYLE_VALUES));
		
		backgroundBorder.add(new PropertyDetails(
				"border-bottom-width", "Sets the width of the bottom border", ValueType.PIXEL, RANGE_0_TO_20));
		
		backgroundBorder.add(new PropertyDetails(
				"border-color", "Sets the color of the four borders", ValueType.COLOR));
		
		backgroundBorder.add(new PropertyDetails(
				"border-left-color", "Sets the color of the left border", ValueType.COLOR));
		
		backgroundBorder.add(new PropertyDetails(
				"border-left-style", "Sets the style of the left border", ValueType.STRING, BORDER_STYLE_VALUES));
		
		backgroundBorder.add(new PropertyDetails(
				"border-left-width", "Sets the width of the left border", ValueType.PIXEL, RANGE_0_TO_20));
		
		backgroundBorder.add(new PropertyDetails(
				"border-radius", "A shorthand property for setting all the four border-*-radius properties",
				ValueType.PIXEL, RANGE_0_TO_100));
		
		backgroundBorder.add(new PropertyDetails(
				"border-right-color", "Sets the color of the right border", ValueType.COLOR));
		
		backgroundBorder.add(new PropertyDetails(
				"border-right-style", "Sets the style of the right border", ValueType.STRING, BORDER_STYLE_VALUES));
		
		backgroundBorder.add(new PropertyDetails(
				"border-right-width", "Sets the width of the right border", ValueType.PIXEL, RANGE_0_TO_20));
		
		backgroundBorder.add(new PropertyDetails(
				"border-style", "Sets the style of the four borders", ValueType.STRING, BORDER_STYLE_VALUES));
		
		backgroundBorder.add(new PropertyDetails(
				"border-top-color", "Sets the color of the top border", ValueType.COLOR));
		
		backgroundBorder.add(new PropertyDetails(
				"border-top-left-radius", "Defines the shape of the border of the top-left corner",
				ValueType.PIXEL, RANGE_0_TO_100));
		
		backgroundBorder.add(new PropertyDetails(
				"border-top-right-radius", "Defines the shape of the border of the top-right corner", ValueType.PIXEL, 
				RANGE_0_TO_100));
		
		backgroundBorder.add(new PropertyDetails(
				"border-top-style", "Sets the style of the top border", ValueType.STRING, BORDER_STYLE_VALUES));
		
		backgroundBorder.add(new PropertyDetails(
				"border-top-width", "Sets the width of the top border", ValueType.PIXEL, RANGE_0_TO_20));
		
		backgroundBorder.add(new PropertyDetails(
				"border-width", "Sets the width of the four borders", ValueType.PIXEL, RANGE_0_TO_20));
		
		basicBox = new LinkedList<PropertyDetails>();
		
		basicBox.add(new PropertyDetails(
				"bottom", "Specifies the bottom position of a positioned element", ValueType.PIXEL, RANGE_100_to_100));
		
		basicBox.add(new PropertyDetails(
				"clear", "Specifies which sides of an element where other floating elements are not allowed",
				ValueType.STRING, new String[] {
					NONE, LEFT, RIGHT, BOTH, INITIAL, INHERIT	
				}));
		
		basicBox.add(new PropertyDetails(
				"display", " 	Specifies how a certain HTML element should be displayed", ValueType.STRING,
				new String[] {
					"inline", "block", "list-item", NONE, INITIAL, INHERIT	
				}));
		
		basicBox.add(new PropertyDetails(
				"float", "Specifies whether or not a box should float", ValueType.STRING, new String[] {
						NONE, LEFT, RIGHT, INITIAL, INHERIT
				}));
		
		basicBox.add(new PropertyDetails(
				"height", "Sets the height of an element", ValueType.PIXEL, new Range<Integer>(0, 1000)));
		
		basicBox.add(new PropertyDetails(
				LEFT, "Specifies the left position of a positioned element", ValueType.PIXEL,
				new Range<Integer>(-100, 100)));
		
		basicBox.add(new PropertyDetails(
				"margin-bottom", "Sets the bottom margin of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"margin-left", "Sets the left margin of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"margin-right", "Sets the right margin of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"margin-top", "Sets the top margin of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"max-height", " 	Sets the maximum height of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"max-width", "Sets the maximum width of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"min-height", "Sets the minimum height of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"min-width", "Sets the minimum width of an element", ValueType.PIXEL, RANGE_0_TO_200));
		
		basicBox.add(new PropertyDetails(
				"overflow", "Specifies what happens if content overflows an element's box",
				ValueType.STRING, OVERFLOW_VALUES));
		
		basicBox.add(new PropertyDetails(
				"overflow-x",
				"Specifies whether or not to clip the left/right edges of the content, if"
				+ " it overflows the element's content area 3 overflow-y", ValueType.STRING, OVERFLOW_VALUES));
		
		basicBox.add(new PropertyDetails(
				"overflow-y", "Specifies whether or not to clip the top/bottom edges of "
						+ "the content, if it overflows the element's content area",
						ValueType.STRING, OVERFLOW_VALUES));
		
		basicBox.add(new PropertyDetails(
				"padding-bottom", "Sets the bottom padding of an element", ValueType.PIXEL, RANGE_0_TO_100));
		
		basicBox.add(new PropertyDetails(
				"padding-left", "Sets the left padding of an element", ValueType.PIXEL, RANGE_0_TO_100));
		
		basicBox.add(new PropertyDetails(
				"padding-right", "Sets the right padding of an element", ValueType.PIXEL, RANGE_0_TO_100));
		
		basicBox.add(new PropertyDetails(
				"padding-top", "Sets the top padding of an element", ValueType.PIXEL, RANGE_0_TO_100));
		
		basicBox.add(new PropertyDetails(
				"position", "Specifies the type of positioning method used for an element", ValueType.STRING, 
				new String[] {
						"static", "absolute", FIXED, "relative", INITIAL, INHERIT
				}));
		
		basicBox.add(new PropertyDetails(
				RIGHT, "Specifies the right position of a positioned element", ValueType.PIXEL, RANGE_100_to_100));
		
		basicBox.add(new PropertyDetails(TOP, "Specifies the top position of a positioned element",
				ValueType.PIXEL, RANGE_100_to_100));
		
		basicBox.add(new PropertyDetails(
				"visibility", "Specifies whether or not an element is visible", ValueType.STRING, new String[] {
						VISIBLE, HIDDEN, "collapse", INITIAL, INHERIT
				}));
		
		basicBox.add(new PropertyDetails(
				"width", "Sets width of an element", ValueType.PIXEL, RANGE_0_to_150));
		
		basicBox.add(new PropertyDetails(
				"vertical-align", "Sets the vertical alignment of an element", ValueType.PIXEL, RANGE_50_to_50));
		
		basicBox.add(new PropertyDetails(
				"z-index", "Sets the stack order of a positioned element", ValueType.INTEGER, RANGE_50_to_50));
		
		flexibleBoxLayout = new LinkedList<PropertyDetails>();
		
		flexibleBoxLayout.add(new PropertyDetails(
				"align-content", "Specifies the alignment between the lines inside a flexible "
						+ "container when the items do not use all available space", ValueType.STRING, new String[] {
						STRETCH, CENTER, FLEX_START, FLEX_END, "space-between", "space-around", 
						INITIAL, INHERIT
				}));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"align-items", "Specifies the alignment for items inside a flexible container",
				ValueType.STRING, new String[] {
						STRETCH, CENTER, FLEX_START, FLEX_END, BASELINE, INITIAL, INHERIT
				}));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"align-self", "Specifies the alignment for selected items inside a flexible container",
				ValueType.STRING, new String[] {
						AUTO, STRETCH, CENTER, FLEX_START, FLEX_END, BASELINE, INITIAL, INHERIT						
				}));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"flex-basis", "Specifies the initial length of a flexible item", ValueType.PIXEL, RANGE_0_TO_200));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"flex-direction", "Specifies the direction of the flexible items", ValueType.STRING, 
				new String[] {
						"row", "row-reverse", "column", "column-reverse", INITIAL, INHERIT
				}));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"flex-grow", "Specifies how much the item will grow relative to the rest",
				ValueType.INTEGER, RANGE_0_TO_20));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"flex-shrink", "Specifies how the item will shrink relative to the rest",
				ValueType.INTEGER, RANGE_0_TO_20));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"flex-wrap", "Specifies whether the flexible items should wrap or not",
				ValueType.STRING, new String[] {
						"nowrap", "wrap", "wrap-reverse", INITIAL,INHERIT
				}));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"justify-content", "Specifies the alignment between the items inside a flexible"
						+ " container when the items do not use all available space",
						ValueType.STRING, new String[] {
								FLEX_START, FLEX_END, CENTER, "space-between", "space-around",
								INITIAL, INHERIT
						}));
		
		flexibleBoxLayout.add(new PropertyDetails(
				"order", "Sets the order of the flexible item, relative to the rest", ValueType.INTEGER, 
				RANGE_0_TO_100));
		
		textProperties = new LinkedList<PropertyDetails>();
		
		textProperties.add(new PropertyDetails(
				"hanging-punctuation", "Specifies whether a punctuation character may"
						+ " be placed outside the line box", ValueType.STRING, new String[] {
								NONE, "first", "last", "allow-end", "force-end", INITIAL, INHERIT								
						}));
		
		textProperties.add(new PropertyDetails(
				"letter-spacing", "Increases or decreases the space between characters in a text",
				ValueType.PIXEL, RANGE_20_to_20));
		
		//textProperties.add(new PropertyDetails(, description, valueType, range))
	}
}