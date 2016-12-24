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
	private JLabel selectorName;

	public SelectorCodePanel(Selector selector) {
		// set layout and create bag constraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.WEST;
		bagConstraints.gridwidth = 1;
		bagConstraints.insets = new Insets(10, 5, 0, 0);

		// add selector name
		selectorName = new JLabel(selector.toString());
		selectorName.setForeground(Color.WHITE);
		add(selectorName, bagConstraints);
		
		// add {
		bagConstraints.gridx++;
		JLabel label = new JLabel(" {");
		label.setForeground(Color.CYAN);
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
