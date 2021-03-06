package addProperty;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DescriptionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea description;
	private JScrollPane scrollPane;
	
	public DescriptionPanel(AddProperty parent) {
		// set layout 
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(5, 0, 5, 0);
		
		// add description label
		add(new JLabel("Description: "), bagConstraints);
		
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
