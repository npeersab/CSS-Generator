package codePanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import theme.ThemedJPanel;
import css.Property;

public class PropertyCodePanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	
	public PropertyCodePanel(SelectorCodePanel parent, Property property) {
		// set layout
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		
		// get theme
		themeColor = parent.getThemeColor();
		
		// add property name
		JLabel label = new JLabel(property.getName());
		label.setForeground(themeColor.property);
		add(label, bagConstraints);
		
		// add :
		label = new JLabel(": ");
		label.setForeground(themeColor.font);
		bagConstraints.gridx++;
		add(label, bagConstraints);
					
		// add value
		label = new JLabel(property.getValue());
		label.setForeground(themeColor.value);
		bagConstraints.gridx++;
		add(label, bagConstraints);
		
		// add ;
		label = new JLabel(";");
		label.setForeground(themeColor.font);
		bagConstraints.gridx++;
		add(label, bagConstraints);
		
		setBackground(themeColor.backGroundLight);
	}
}
