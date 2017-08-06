package theme;

import java.awt.Color;

public enum ThemeColor {
	black(Color.BLACK, Color.LIGHT_GRAY, Color.WHITE, Color.CYAN,
			Color.GREEN, Color.MAGENTA),
	
	white(Color.LIGHT_GRAY, Color.WHITE, Color.BLACK, Color.RED,
			new Color(36, 0, 133), Color.MAGENTA),
	
	red(new Color(255, 0, 0), new Color(255, 208, 196),
			new Color(0, 0, 0), new Color(255, 255, 255), 
			new Color(103, 0, 255), Color.GRAY),
	blue(new Color(62, 154, 255), new Color(133, 191, 255),
			new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 0, 0), new Color(34, 170, 33));
	public Color backGroundDark, backGroundLight, font, brace, property, value;
	
	
	// constructor
	private ThemeColor(Color backGroundDark, Color backGroundLight, Color font, Color brace, 
			Color property, Color value) {
		this.backGroundDark = backGroundDark;
		this.backGroundLight = backGroundLight;
		this.font = font;
		this.brace = brace;
		this.property = property;
		this.value = value;
	}
}
