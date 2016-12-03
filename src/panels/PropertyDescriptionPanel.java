package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import frames.PropertyFrame;

public class PropertyDescriptionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea description;
	JScrollPane scrollPane;
	
	public PropertyDescriptionPanel(PropertyFrame parent) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.WEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(5, 0, 5, 0);
		
		add(new JLabel("Description : "), bagConstraints);
		
		// add description in the panel
		description = new JTextArea(5, 40);
		description.setEditable(false);
		description.setMargin(new Insets(5, 5, 5, 5));
		parent.setDescription(description);
		scrollPane = new JScrollPane(description);
		bagConstraints.gridy++;
		add(scrollPane, bagConstraints);
	}
}
