package theme;

import javax.swing.JFrame;
import theme.ThemeColor;

public class ThemedJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	protected ThemeColor themeColor;
	
	// getter and setter for theme color
	public ThemeColor getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(ThemeColor themeColor) {
		this.themeColor = themeColor;
	}
}
