package theme;

import java.awt.Color;

public class ThemeColor {
	public Color backGroundDark, backGroundLight, font, brace, property, value;
	public static ThemeColor black, white, red, blue;
	
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
		
		white = new ThemeColor(Color.LIGHT_GRAY, Color.WHITE, Color.BLACK, Color.RED,
				new Color(36, 0, 133), Color.MAGENTA);
		
		red = new ThemeColor(new Color(255, 0, 0), new Color(255, 144, 144),
				new Color(0, 0, 0), new Color(255, 255, 255), new Color(103, 0, 255), new Color(177, 0, 177));
		
		blue = new ThemeColor(new Color(33, 98, 175), new Color(17, 150, 207),
				new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 0, 0), new Color(0, 255, 0));
	}
}
