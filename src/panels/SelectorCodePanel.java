package panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import css.Property;
import css.Selector;

public class SelectorCodePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// reference to selector
	private Selector selector;
	
	public SelectorCodePanel(Selector selector) {
		// set layout 
		setLayout(new GridBagLayout());
		
		// store reference to selector
		this.selector = selector;
		
		// update the panel
		updatePanel();
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
		JLabel selectorName = new JLabel(selector.toString());
		selectorName.setForeground(Color.WHITE);
		add(selectorName, bagConstraints);
		
		// add {
		bagConstraints.gridx++;
		JLabel label = new JLabel(" {");
		label.setForeground(Color.CYAN);
		bagConstraints.insets = new Insets(10, 5, 2, 0);
		add(label, bagConstraints);
		
		// get list of all properties
		LinkedList<Property> propertiesList = selector.getPropertiesList();
		Iterator<Property> iterator = propertiesList.iterator();
		
		bagConstraints.gridwidth = 2;
		bagConstraints.gridx--;
		bagConstraints.insets = new Insets(0, 50, 0, 0);
		// add all property panels
		while (iterator.hasNext()) {
			Property property = iterator.next();
			PropertyCodePanel propertyCodePanel = new PropertyCodePanel(property);
			bagConstraints.gridy++;
			add(propertyCodePanel, bagConstraints);
		}
		
		// add }
		bagConstraints.insets = new Insets(0, 5, 0, 0);
		bagConstraints.gridy++;
		label = new JLabel("}");
		label.setForeground(Color.CYAN);
		add(label, bagConstraints);
		
		setBackground(Color.GRAY);
	}
}
