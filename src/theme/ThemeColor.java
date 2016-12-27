package theme;

import java.awt.Color;

public class ThemeColor {
	public Color backGroundDark, backGroundLight, font, brace, property, value;
	public static ThemeColor black, white;
	
	// constructor
	public ThemeColor(Color backGroundDark, Color backGroundLight, Color font, Color brace, 
			Color property, Color value) {
		this.backGroundDark = backGroundDark;
		this.backGroundLight = backGroundLight;
		this.font = font;
		this.brace = brace;
		this.property = property;
		this.value = value;
	}
	
	public static void createDefaultThemes() {
		black = new ThemeColor(Color.BLACK, Color.GRAY, Color.WHITE, Color.CYAN,
				Color.GREEN, Color.MAGENTA);
		
		white = new ThemeColor(Color.WHITE, Color.WHITE, Color.BLACK, Color.RED,
				Color.BLACK, Color.MAGENTA);
		
	}
}
