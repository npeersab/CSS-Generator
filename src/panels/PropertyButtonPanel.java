package panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import frames.PropertyFrame;

public class PropertyButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public PropertyButtonPanel(PropertyFrame parent) {
		JButton addButton = new JButton("Add");
		addButton.addActionListener(parent);
		parent.setAddButton(addButton);
		add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(
				e -> parent.dispose()
		);
		parent.setCancelButton(cancelButton);
		add(cancelButton);
	}
}
