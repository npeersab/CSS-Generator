package main.codePanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import theme.ThemeColor;
import theme.ThemedJPanel;
import css.Property;

public class PropertyCodePanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	
	// components
	JLabel name, value, colon, semiColon;
	
	public PropertyCodePanel(SelectorCodePanel parent, Property property) {
		// set layout
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		
		// get theme
		themeColor = parent.getThemeColor();
		
		// add property name
		name = new JLabel(property.getName());
		name.setForeground(themeColor.property);
		add(name, bagConstraints);
		
		// add :
		colon = new JLabel(": ");
		colon.setForeground(themeColor.font);
		bagConstraints.gridx++;
		add(colon, bagConstraints);
					
		// add value
		value = new JLabel(property.getValue());
		value.setForeground(themeColor.value);
		bagConstraints.gridx++;
		add(value, bagConstraints);
		
		// add ;
		semiColon = new JLabel(";");
		semiColon.setForeground(themeColor.font);
		bagConstraints.gridx++;
		add(semiColon, bagConstraints);
		
		setBackground(themeColor.backGroundLight);
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		
		name.setForeground(themeColor.property);
		colon.setForeground(themeColor.font);
		value.setForeground(themeColor.value);
		semiColon.setForeground(themeColor.font);
		setBackground(themeColor.backGroundLight);
	}
}
