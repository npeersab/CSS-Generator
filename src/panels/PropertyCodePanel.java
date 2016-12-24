package panels;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import css.Property;

public class PropertyCodePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public PropertyCodePanel(Property property) {
		// add property name
		JLabel label = new JLabel(property.getName());
		label.setForeground(Color.GREEN);
		add(label);
		
		// add :
		label = new JLabel(": ");
		label.setForeground(Color.WHITE);
		add(label);
					
		// add value
		label = new JLabel(property.getValue());
		label.setForeground(Color.MAGENTA);
		add(label);
		
		// add ;
		label = new JLabel(";");
		label.setForeground(Color.WHITE);
		add(label);
		
		setBackground(Color.GRAY);
	}
}
