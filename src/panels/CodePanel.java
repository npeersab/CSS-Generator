package panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CodePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public CodePanel() {
		// set layout and create constraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.fill = GridBagConstraints.VERTICAL;
		bagConstraints.weighty = 0;
		
		JLabel label = new JLabel("No file selected");
		label.setForeground(Color.WHITE);
		add(label, bagConstraints);
		label = new JLabel("Create or open existing file from file menu");
		label.setForeground(Color.WHITE);
		bagConstraints.gridy++;
		add(label, bagConstraints);
		setBackground(Color.GRAY);
	}
}
