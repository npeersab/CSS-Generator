package abstractClass;

import javax.swing.JPanel;
import theme.ThemeColor;

public class ThemedJPanel extends JPanel {
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
