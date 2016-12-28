package theme;

import javax.swing.JPanel;
import theme.ThemeColor;

public abstract class ThemedJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected ThemeColor themeColor;
	
	// getter and setter for theme color
	public ThemeColor getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(ThemeColor themeColor) {
		this.themeColor = themeColor;
	}
	
	public abstract void applyTheme(ThemeColor themeColor);
}
