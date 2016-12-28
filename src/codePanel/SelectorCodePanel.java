package codePanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;

import theme.ThemeColor;
import theme.ThemedJPanel;
import css.Property;
import css.Selector;

public class SelectorCodePanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	
	// reference to selector
	private Selector selector;
	
	// to map property with code panel
	HashMap<Property, PropertyCodePanel> propertyHashMap;
	
	// components
	JLabel selectorName, openBrace, closeBrace;
		
	public SelectorCodePanel(CodePanel parent, Selector selector) {
		// set layout 
		setLayout(new GridBagLayout());
		
		// store reference to selector
		this.selector = selector;
		
		// get theme
		themeColor = parent.getThemeColor();
		
		propertyHashMap = new HashMap<Property, PropertyCodePanel>();
		
		// update the panel
		updatePanel();
		
		setBackground(themeColor.backGroundLight);
	}
	
	public void updatePanel() {
		// remove all existing code
		removeAll();
		
		// create constraint
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.WEST;
		bagConstraints.gridwidth = 1;
		bagConstraints.insets = new Insets(10, 5, 0, 0);

		// add selector name
		selectorName = new JLabel(selector.toString());
		selectorName.setForeground(themeColor.font);
		add(selectorName, bagConstraints);
		
		// add {
		bagConstraints.gridx++;
		openBrace = new JLabel(" {");
		openBrace.setForeground(themeColor.brace);
		bagConstraints.insets = new Insets(10, 5, 2, 0);
		add(openBrace, bagConstraints);
		
		// get list of all properties
		LinkedList<Property> propertiesList = selector.getPropertiesList();
		Iterator<Property> iterator = propertiesList.iterator();
		
		bagConstraints.gridwidth = 2;
		bagConstraints.gridx--;
		bagConstraints.insets = new Insets(0, 50, 0, 0);
		// add all property panels
		Property property = null;
		PropertyCodePanel propertyCodePanel = null;
		while (iterator.hasNext()) {
			property = iterator.next();
			propertyCodePanel = new PropertyCodePanel(this, property);
			propertyHashMap.put(property, propertyCodePanel);
			bagConstraints.gridy++;
			add(propertyCodePanel, bagConstraints);
		}
		
		// add }
		bagConstraints.insets = new Insets(0, 5, 0, 0);
		bagConstraints.gridy++;
		closeBrace = new JLabel("}");
		closeBrace.setForeground(themeColor.brace);
		add(closeBrace, bagConstraints);
		
		revalidate();
		repaint();
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);

		selectorName.setForeground(themeColor.font);
		openBrace.setForeground(themeColor.brace);
		closeBrace.setForeground(themeColor.brace);
		setBackground(themeColor.backGroundLight);
		
		Collection<PropertyCodePanel> propertyCodePanels = propertyHashMap.values();
		Iterator<PropertyCodePanel> iterator = propertyCodePanels.iterator();
		while (iterator.hasNext())
			iterator.next().applyTheme(themeColor);
	}
}
