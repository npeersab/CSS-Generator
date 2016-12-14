package panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import frames.AddProperty;

public class PropertyDescriptionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea description;
	JScrollPane scrollPane;
	
	public PropertyDescriptionPanel(AddProperty parent) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(5, 0, 5, 0);
		
		add(new JLabel("Description : "), bagConstraints);
		
		// add description in the panel
		description = new JTextArea(3, 30);
		description.setEditable(false);
		description.setCaretColor(Color.BLUE);
		description.setMargin(new Insets(5, 5, 5, 5));
		parent.setDescription(description);
		scrollPane = new JScrollPane(description);
		bagConstraints.gridx++;
		add(scrollPane, bagConstraints);
	}
}
