package frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PropertyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String propertytypes[] = {"Animation", "Background and Borders",
			"Basic Box", "Basic User Interface", "Color", "Filter Effects", "Flexible Box",
			"Fonts", "Generated Content", "Image/Replaced Content", "Lists and Counters",
			"Marquee", "Masking", "Multi-column", "Paged Media", "Speech", "Table", "Text",
			"Text Decoration", "Transform", "Transition", "Writing Modes"
		    };
	private JButton cancelButton, selectButton;
	
	public PropertyFrame() {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 5);
		
		add(new JLabel("Select Type of the Property : "), bagConstraints);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> dispose());
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);
		
		JComboBox<String> comboBox = new JComboBox<String>(propertytypes);
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(comboBox, bagConstraints);
		
		selectButton = new JButton("Select");
		bagConstraints.gridy++;
		add(selectButton, bagConstraints);
						
		setTitle("Select Property Type");
		setIconImage(ImgSrc.getImageIcon());
		setSize(500, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
