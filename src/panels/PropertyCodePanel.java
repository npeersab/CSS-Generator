package panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import css.Property;

public class PropertyCodePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public PropertyCodePanel(Property property) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		
		// add property name
		JLabel label = new JLabel(property.getName());
		label.setForeground(Color.GREEN);
		add(label, bagConstraints);
		
		// add :
		label = new JLabel(": ");
		label.setForeground(Color.WHITE);
		bagConstraints.gridx++;
		add(label, bagConstraints);
					
		// add value
		label = new JLabel(property.getValue());
		label.setForeground(Color.MAGENTA);
		bagConstraints.gridx++;
		add(label, bagConstraints);
		
		// add ;
		label = new JLabel(";");
		label.setForeground(Color.WHITE);
		bagConstraints.gridx++;
		add(label, bagConstraints);
		
		setBackground(Color.GRAY);
	}
}
