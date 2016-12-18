package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import frames.AddProperty;

public class PropertyButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public PropertyButtonPanel(AddProperty parent) {
		JButton addButton = new JButton("Add");
		addButton.addActionListener(parent);
		parent.setAddButton(addButton);
		add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		parent.setCancelButton(cancelButton);
		add(cancelButton);
	}
}
