package css;

public class PropertyDetailsList {
	public static PropertyDetails[] color, backgroundBorder, basicBox, flexibleBoxLayout, font,
	text, textDecoration;
	public static final String ABSOLUTE = "absolute", ALLOW_END = "allow-end", AUTO = "auto",
			BASELINE = "baseline", BLOCK = "block", BORDER_BOX = "border-box", BOTH = "both",
			BOTTOM = "bottom", BREAK_ALL = "break-all", BREAK_WORD = "break-word",
			CAPITALIZE = "capitalize", CENTER = "center", COLLAPSE = "collapse",
			COLOR = "color", COLOR_DODGE = "color-dodge", COLUMN = "column",
			COLUMN_REVERSE = "column-reverse", CONTAIN = "contain", CONTENT_BOX = "content-box", 
			COVER = "cover", DARKEN = "darken", DASHED = "dashed", DISTRIBUTE = "distribute",
			DOTTED = "dotted", DOUBLE = "double", END = "end", FIRST = "first",	FIXED = "fixed", 
			FLEX_END = "flex-end", FLEX_START = "flex-start", FORCE_END = "force-end", GROOVE = "groove",
			HIDDEN = "hidden", INITIAL = "initial", INHERIT = "inherit", 
			INLINE = "inline",
			INSET = "inset", INTER_CLUSTER = "inter-cluster", 
			INTER_IDEOGRAPH = "inter-ideograph", INTER_WORD = "inter-word",
			JUSTIFY = "justify", KASHIDA = "kashida", KEEP_ALL = "keep-all",
			LAST = "last", LEFT = "left",
			LIGHTEN = "lighten", LINE_THROUGH = "line-through", LIST_ITEM = "list-item",
			LOCAL = "local", LOWERCASE = "lowercase", LUMINOSITY = "luminosity",
			MULTIPLY = "multiply", 
			NONE = "none", NORMAL = "normal", 
			NO_REPEAT = "no-repeat", NOWRAP = "nowrap",
			OUTSET = "outset",
			OVERLAY = "overlay", OVERLINE = "overline", 
			PADDING_BOX = "padding-box", PRE = "pre", PRE_LINE = "pre-line",
			PRE_WRAP = "pre-wrap",
			RELATIVE = "relative",
			REPEAT = "repeat", REPEAT_X = "repeat-x", REPEAT_Y = "repeat-y", 
			RIDGE = "ridge", RIGHT = "right", ROW = "row", ROW_REVERSE = "row-reverse",
			SATURATION = "saturation",
			SCREEN = "screen", SCROLL = "scroll", 
			SOLID = "solid", START = "start", STATIC = "static",
			STRETCH = "stretch", TOP = "top", TRIM = "trim", 
			UNDERLINE = "underline", UPPERCASE = "uppercase", VISIBLE = "visible", WAVY = "wavy",
			WRAP = "wrap", WRAP_REVERSE = "wrap-reverse",
	
			BACKGROUND_PROP[] = new String[] {
					BORDER_BOX, PADDING_BOX, CONTENT_BOX, INITIAL, INHERIT
			},
			BORDER_STYLE_VALUES[] = new String[] {
					NONE, HIDDEN, DOTTED, DASHED, SOLID, DOUBLE, GROOVE, RIDGE,
					INSET, OUTSET, INITIAL, INHERIT						
			},
			OVERFLOW_VALUES[] = new String[] {
					VISIBLE, HIDDEN, SCROLL, AUTO, INITIAL, INHERIT
			},
			TEXT_DECORATION_LINE[] = new String[] {
 					NONE, UNDERLINE, OVERLINE, LINE_THROUGH, INITIAL, INHERIT
 			};
	public static final Range<Integer> RANGE_0_TO_100 = new Range<Integer>(0, 100),
			RANGE_0_TO_20 = new Range<Integer>(0, 20), RANGE_0_TO_200 = new Range<Integer>(0, 200),
			RANGE_n100_to_100 = new Range<Integer>(-100, 100),
			RANGE_0_to_150 = new Range<Integer>(0, 150), RANGE_n50_to_50 = new Range<Integer>(-50, 50),
			RANGE_n20_to_20 = new Range<Integer>(-20, 20), RANGE_n5_to_50 = new Range<Integer>(-5, 50); 
	
	public PropertyDetailsList() {
		color = new PropertyDetails[2];
		int cnt = 0;
			
		color[cnt++] = new PropertyDetails(
				COLOR, "Sets the color of text", ValueType.COLOR);
		
		color[cnt] = new PropertyDetails(
				"opacity", "Sets the opacity level for an element", ValueType.DOUBLE,
				new Range<Double>(0.0, 1.0));
		
		backgroundBorder = new PropertyDetails[29];
		cnt = 0;
						
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-attachment",
				"Sets whether a background image is fixed or scrolls with the rest of the page",
				ValueType.STRING,new String[] {
						SCROLL , FIXED, LOCAL, INITIAL, INHERIT
				});
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-blend-mode",
				"Specifies the blending mode of each background layer (color/image)",
				ValueType.STRING, new String[] {
						NORMAL, MULTIPLY, SCREEN, OVERLAY, DARKEN, LIGHTEN, COLOR_DODGE,
						SATURATION, COLOR, LUMINOSITY
				});
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-color", "Specifies the background color of an element", ValueType.COLOR);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-image", "Specifies one or more background images for an element",
				ValueType.STRING);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-position", "Specifies the position of a background image",
				ValueType.STRING, new String[] {
						LEFT + " " + TOP, LEFT + " " + CENTER, LEFT + " " + BOTTOM, RIGHT + " " + TOP,
						RIGHT + " " + CENTER, RIGHT + " " + BOTTOM, CENTER + " " + TOP,
						CENTER + " " + CENTER, CENTER + " " + BOTTOM	
				});
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-repeat", "Sets how a background image will be repeated", ValueType.STRING,
				new String[] {
						REPEAT, REPEAT_X, REPEAT_Y, NO_REPEAT, INITIAL, INHERIT
				});
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-clip", "Specifies the painting area of the background", ValueType.STRING, 
				BACKGROUND_PROP);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"background-origin", "Specifies where the background image(s) is/are positioned",
				ValueType.STRING, BACKGROUND_PROP);
		
		backgroundBorder[cnt++] = new PropertyDetails("background-size", 
				"Specifies the size of the background image(s)", ValueType.STRING, new String[] {
						AUTO, COVER, CONTAIN, INITIAL, INHERIT						
				});
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-bottom-color", "Sets the color of the bottom border", ValueType.COLOR);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-bottom-left-radius", "Defines the shape of the border of the bottom-left corner",
				ValueType.PIXEL, RANGE_0_TO_100);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-bottom-right-radius", "Defines the shape of the border of the bottom-right corner",
				ValueType.PIXEL, RANGE_0_TO_100);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-bottom-style", "Sets the style of the bottom border", ValueType.STRING,
				BORDER_STYLE_VALUES);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-bottom-width", "Sets the width of the bottom border", ValueType.PIXEL,
				RANGE_0_TO_20);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-color", "Sets the color of the four borders", ValueType.COLOR);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-left-color", "Sets the color of the left border", ValueType.COLOR);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-left-style", "Sets the style of the left border", ValueType.STRING,
				BORDER_STYLE_VALUES);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-left-width", "Sets the width of the left border", ValueType.PIXEL,
				RANGE_0_TO_20);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-radius",
				"A shorthand property for setting all the four border-*-radius properties",
				ValueType.PIXEL, RANGE_0_TO_100);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-right-color", "Sets the color of the right border", ValueType.COLOR);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-right-style", "Sets the style of the right border", ValueType.STRING,
				BORDER_STYLE_VALUES);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-right-width", "Sets the width of the right border", ValueType.PIXEL, 
				RANGE_0_TO_20);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-style", "Sets the style of the four borders", ValueType.STRING,
				BORDER_STYLE_VALUES);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-top-color", "Sets the color of the top border", ValueType.COLOR);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-top-left-radius", "Defines the shape of the border of the top-left corner",
				ValueType.PIXEL, RANGE_0_TO_100);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-top-right-radius", "Defines the shape of the border of the top-right corner", 
				ValueType.PIXEL, RANGE_0_TO_100);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-top-style", "Sets the style of the top border", ValueType.STRING,
				BORDER_STYLE_VALUES);
		
		backgroundBorder[cnt++] = new PropertyDetails(
				"border-top-width", "Sets the width of the top border", ValueType.PIXEL, RANGE_0_TO_20);
		
		backgroundBorder[cnt] = new PropertyDetails(
				"border-width", "Sets the width of the four borders", ValueType.PIXEL, RANGE_0_TO_20);
		
		basicBox = new PropertyDetails[28];
		cnt = 0;
		
		basicBox[cnt++] = new PropertyDetails(
				"bottom", "Specifies the bottom position of a positioned element", ValueType.PIXEL, 
				RANGE_n100_to_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"clear", "Specifies which sides of an element where other floating elements are not allowed",
				ValueType.STRING, new String[] {
					NONE, LEFT, RIGHT, BOTH, INITIAL, INHERIT	
				});
		
		basicBox[cnt++] = new PropertyDetails(
				"display", " 	Specifies how a certain HTML element should be displayed",
				ValueType.STRING, new String[] {
					INLINE, BLOCK, LIST_ITEM, NONE, INITIAL, INHERIT	
				});
		
		basicBox[cnt++] = new PropertyDetails(
				"float", "Specifies whether or not a box should float", ValueType.STRING, new String[] {
						NONE, LEFT, RIGHT, INITIAL, INHERIT
				});
		
		basicBox[cnt++] = new PropertyDetails(
				"height", "Sets the height of an element", ValueType.PIXEL, 
				new Range<Integer>(0, 1000));
		
		basicBox[cnt++] = new PropertyDetails(
				LEFT, "Specifies the left position of a positioned element", ValueType.PIXEL,
				RANGE_n100_to_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"margin-bottom", "Sets the bottom margin of an element", ValueType.PIXEL, 
				RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"margin-left", "Sets the left margin of an element", ValueType.PIXEL, RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"margin-right", "Sets the right margin of an element", ValueType.PIXEL, RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"margin-top", "Sets the top margin of an element", ValueType.PIXEL, RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"max-height", " 	Sets the maximum height of an element", ValueType.PIXEL, 
				RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"max-width", "Sets the maximum width of an element", ValueType.PIXEL, RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"min-height", "Sets the minimum height of an element", ValueType.PIXEL, RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"min-width", "Sets the minimum width of an element", ValueType.PIXEL, RANGE_0_TO_200);
		
		basicBox[cnt++] = new PropertyDetails(
				"overflow", "Specifies what happens if content overflows an element's box",
				ValueType.STRING, OVERFLOW_VALUES);
		
		basicBox[cnt++] = new PropertyDetails(
				"overflow-x",
				"Specifies whether or not to clip the left/right edges of the content, if"
				+ " it overflows the element's content area 3 overflow-y", ValueType.STRING,
				OVERFLOW_VALUES);
		
		basicBox[cnt++] = new PropertyDetails(
				"overflow-y", "Specifies whether or not to clip the top/bottom edges of "
						+ "the content, if it overflows the element's content area",
						ValueType.STRING, OVERFLOW_VALUES);
		
		basicBox[cnt++] = new PropertyDetails(
				"padding-bottom", "Sets the bottom padding of an element", ValueType.PIXEL,
				RANGE_0_TO_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"padding-left", "Sets the left padding of an element", ValueType.PIXEL, 
				RANGE_0_TO_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"padding-right", "Sets the right padding of an element", ValueType.PIXEL,
				RANGE_0_TO_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"padding-top", "Sets the top padding of an element", ValueType.PIXEL, RANGE_0_TO_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"position", "Specifies the type of positioning method used for an element",
				ValueType.STRING, new String[] {
						STATIC, ABSOLUTE, FIXED, RELATIVE, INITIAL, INHERIT
				});
		
		basicBox[cnt++] = new PropertyDetails(
				RIGHT, "Specifies the right position of a positioned element", 
				ValueType.PIXEL, RANGE_n100_to_100);
		
		basicBox[cnt++] = new PropertyDetails(TOP, "Specifies the top position of a positioned element",
				ValueType.PIXEL, RANGE_n100_to_100);
		
		basicBox[cnt++] = new PropertyDetails(
				"visibility", "Specifies whether or not an element is visible", ValueType.STRING,
				new String[] {
						VISIBLE, HIDDEN, COLLAPSE, INITIAL, INHERIT
				});
		
		basicBox[cnt++] = new PropertyDetails(
				"width", "Sets width of an element", ValueType.PIXEL, RANGE_0_to_150);
		
		basicBox[cnt++] = new PropertyDetails(
				"vertical-align", "Sets the vertical alignment of an element",
				ValueType.PIXEL, RANGE_n50_to_50);
		
		basicBox[cnt] = new PropertyDetails(
				"z-index", "Sets the stack order of a positioned element",
				ValueType.INTEGER, RANGE_n50_to_50);
		
		flexibleBoxLayout = new PropertyDetails[10];
		cnt = 0;
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"align-content", "Specifies the alignment between the lines inside a flexible "
						+ "container when the items do not use all available space", ValueType.STRING,
						new String[] {
						STRETCH, CENTER, FLEX_START, FLEX_END, "space-between", "space-around", 
						INITIAL, INHERIT
				});
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"align-items", "Specifies the alignment for items inside a flexible container",
				ValueType.STRING, new String[] {
						STRETCH, CENTER, FLEX_START, FLEX_END, BASELINE, INITIAL, INHERIT
				});
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"align-self", "Specifies the alignment for selected items inside a flexible container",
				ValueType.STRING, new String[] {
						AUTO, STRETCH, CENTER, FLEX_START, FLEX_END, BASELINE, INITIAL, INHERIT						
				});
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"flex-basis", "Specifies the initial length of a flexible item", ValueType.PIXEL,
				RANGE_0_TO_200);
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"flex-direction", "Specifies the direction of the flexible items", ValueType.STRING, 
				new String[] {
						ROW, ROW_REVERSE, COLUMN, COLUMN_REVERSE, INITIAL, INHERIT
				});
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"flex-grow", "Specifies how much the item will grow relative to the rest",
				ValueType.INTEGER, RANGE_0_TO_20);
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"flex-shrink", "Specifies how the item will shrink relative to the rest",
				ValueType.INTEGER, RANGE_0_TO_20);
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"flex-wrap", "Specifies whether the flexible items should wrap or not",
				ValueType.STRING, new String[] {
						NOWRAP, WRAP, WRAP_REVERSE, INITIAL,INHERIT
				});
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"justify-content", "Specifies the alignment between the items inside a flexible"
						+ " container when the items do not use all available space",
						ValueType.STRING, new String[] {
								FLEX_START, FLEX_END, CENTER, "space-between", "space-around",
								INITIAL, INHERIT
						});
		
		flexibleBoxLayout[cnt++] = new PropertyDetails(
				"order", "Sets the order of the flexible item, relative to the rest",
				ValueType.INTEGER, RANGE_0_TO_100);
		
		text = new PropertyDetails[13];
		cnt = 0;
		
		text[cnt++] = new PropertyDetails(
				"hanging-punctuation", "Specifies whether a punctuation character may"
						+ " be placed outside the line box", ValueType.STRING, new String[] {
								NONE, FIRST, LAST, ALLOW_END, FORCE_END, INITIAL, INHERIT								
						});
		
		text[cnt++] = new PropertyDetails(
				"letter-spacing", "Increases or decreases the space between characters in a text",
				ValueType.PIXEL, RANGE_n20_to_20);
		
		text[cnt++] = new PropertyDetails(
				"line-height", "Sets the line height", ValueType.PIXEL, RANGE_0_TO_20);
		
	 	text[cnt++] = new PropertyDetails(
	 			"tab-size", "Specifies the length of the tab-character", ValueType.INTEGER,
	 			RANGE_0_TO_20);
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"text-align", "Specifies the horizontal alignment of text", ValueType.STRING,
	 			new String[] {
	 					LEFT, RIGHT, CENTER, JUSTIFY, INITIAL, INHERIT
	 			});
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"text-align-last", "Describes how the last line of a block or a line right"
	 					+ " before a forced line break is aligned when text-align is \"justify\"",
	 					ValueType.STRING, new String[] {
	 							AUTO, LEFT, RIGHT, CENTER, JUSTIFY, START, END, INITIAL, INHERIT
	 					});
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"text-indent", "Specifies the indentation of the first line in a text-block",
	 			ValueType.PIXEL, RANGE_0_TO_100);
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"text-justify", "Specifies the justification method used when text-align is \"justify\"",
	 			ValueType.STRING, new String[] {
	 					AUTO, INTER_WORD, INTER_IDEOGRAPH, INTER_CLUSTER, DISTRIBUTE, KASHIDA,
	 					TRIM, INITIAL, INHERIT
	 			});
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"text-transform", "Controls the capitalization of text", ValueType.STRING, new String[] {
	 					NONE, CAPITALIZE, UPPERCASE, LOWERCASE, INITIAL, INHERIT
	 			});
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"white-space", "Specifies how white-space inside an element is handled", 
	 			ValueType.STRING, new String[] {
	 					NORMAL, NOWRAP, PRE, PRE_LINE, PRE_WRAP, INITIAL, INHERIT
	 			});
	 			
	 	text[cnt++] = new PropertyDetails(
	 			"word-break", "Specifies line breaking rules for non-CJK scripts", ValueType.STRING,
	 			new String[] {
	 					NORMAL, BREAK_ALL, KEEP_ALL, INITIAL, INHERIT
	 			});
	 	
	 	text[cnt++] = new PropertyDetails(
	 			"word-spacing", "Increases or decreases the space between words in a text", 
	 			ValueType.PIXEL, RANGE_n5_to_50);
	 	
	 	text[cnt] = new PropertyDetails(
	 			"word-wrap", " 	Allows long, unbreakable words to be broken and wrap to the next line",
	 			ValueType.STRING, new String[] {
	 					NORMAL, BREAK_WORD, INITIAL, INHERIT
	 			});
	 	
	 	textDecoration = new PropertyDetails[4];
	 	cnt = 0;
	 	
	 	textDecoration[cnt++] = new PropertyDetails(
	 			"text-decoration", "Specifies the decoration added to text", ValueType.STRING,
	 			TEXT_DECORATION_LINE);
	 	
	 	textDecoration[cnt++] = new PropertyDetails(
	 			"text-decoration-color", "Specifies the color of the text-decoration", ValueType.COLOR);
	 	
	 	textDecoration[cnt++] = new PropertyDetails(
	 			"text-decoration-line", " 	Specifies the type of line in a text-decoration", 
	 			ValueType.STRING, TEXT_DECORATION_LINE);
	 	
	 	textDecoration[cnt] = new PropertyDetails(
	 			"text-decoration-style", "Specifies the style of the line in a text decoration",
	 			ValueType.STRING, new String[] {
	 					SOLID, DOUBLE, DOTTED, DASHED, WAVY, INITIAL, INHERIT
	 			});
	 	
	 	font = new PropertyDetails[100];
	 	cnt = 0;
	}
}