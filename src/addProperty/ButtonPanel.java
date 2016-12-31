package addProperty;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ButtonPanel(AddProperty parent) {
		// set layout to grid bag
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(5, 5, 5, 5);
		
		// add ADD button
		JButton addButton = new JButton("Add");
		parent.setAddButton(addButton);
		bagConstraints.gridx++;
		add(addButton, bagConstraints);
		
		// add cancel button
		JButton cancelButton = new JButton("Cancel");
		parent.setCancelButton(cancelButton);
		bagConstraints.gridx++;
		add(cancelButton, bagConstraints);
	}
}
