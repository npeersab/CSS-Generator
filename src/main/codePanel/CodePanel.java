package main.codePanel;

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
import css.CSSFile;
import css.Selector;
import main.LeftPanel;

public class CodePanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	private HashMap<Selector, SelectorCodePanel> selectorHashMap;
	private GridBagConstraints bagConstraints;
	private JLabel emptyLabel;
	
	// constructor
	public CodePanel(LeftPanel parent) {
		// set layout and create constraints
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.insets = new Insets(5, 5, 0, 0);
		
		// get theme
		themeColor = parent.getThemeColor();
		
		JLabel label = new JLabel("No file selected");
		label.setForeground(themeColor.font);
		add(label, bagConstraints);
		label = new JLabel("Create or open existing file from file menu");
		label.setForeground(themeColor.font);
		bagConstraints.gridy++;
		add(label, bagConstraints);
		setBackground(themeColor.backGroundLight);
		
		bagConstraints.weighty = 50;
		add(new JLabel(), bagConstraints);
	}
	
	public void updatePanel(CSSFile cssFile) {
		// remove all existing components
		removeAll();
		
		// get list of selectors
		LinkedList<Selector> selectorsList = cssFile.getSelectorsList();
		Iterator<Selector> iterator = selectorsList.iterator();
		
		// create hash map to store selector and corresponding code panel
		selectorHashMap = new HashMap<Selector, SelectorCodePanel>();
		
		bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = 0;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		
		// add all selector panels
		while (iterator.hasNext()) {
			Selector selector = iterator.next();
			SelectorCodePanel selectorCodePanel = new SelectorCodePanel(this, selector);
			bagConstraints.gridy++;
			add(selectorCodePanel, bagConstraints);
			selectorHashMap.put(selector, selectorCodePanel);
		}
		
		// add extra label to cover remaining space
		bagConstraints.gridy++;
		bagConstraints.weighty = 50;
		emptyLabel = new JLabel();
		add(emptyLabel, bagConstraints);
	}
	
	// add new selector in code
	public void addSelector(Selector selector) {
		// remove empty space
		remove(emptyLabel);
		
		// add new selector panel
		SelectorCodePanel selectorCodePanel = new SelectorCodePanel(this, selector);
		selectorHashMap.put(selector, selectorCodePanel);
		bagConstraints.weighty = 1;
		bagConstraints.gridy++;
		add(selectorCodePanel, bagConstraints);
		
		// cover remaining space
		bagConstraints.gridy++;
		bagConstraints.weighty = 50;
		add(emptyLabel, bagConstraints);
		
		revalidate();
		repaint();
	}
	
	// remove selector from panel
	public void removeSelector(Selector selector) {
		remove(selectorHashMap.get(selector));
		revalidate();
		repaint();
	}
	
	// update selector panel
	public void updateSelectorPanel(Selector selector) {
		SelectorCodePanel selectorCodePanel = selectorHashMap.get(selector);
		selectorCodePanel.updatePanel();
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		setBackground(themeColor.backGroundLight);
		
		// apply theme to children
		if (selectorHashMap != null) {
			Collection<SelectorCodePanel> codePanels = selectorHashMap.values();
			Iterator<SelectorCodePanel> iterator = codePanels.iterator();
			while (iterator.hasNext()) {
				iterator.next().applyTheme(themeColor);
			}
		}
	}
}
