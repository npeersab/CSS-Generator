package panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import frames.MainFrame;

public class MainFrameDetailsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel selectedItem, itemDetails;

	public MainFrameDetailsPanel(MainFrame parent) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 10, 10, 10);
		
		JLabel label = new JLabel("Welcome to CSS Generator");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		add(label, bagConstraints);
		
		label = new JLabel("Create new CSS file or open existing file from file menu");
		bagConstraints.gridy++;
		add(label, bagConstraints);
	}

	// getter and setter of selectedItem
	public JLabel getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(JLabel selectedItem) {
		this.selectedItem = selectedItem;
	}

	// getter and setter for itemDetails
	public JLabel getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(JLabel itemDetails) {
		this.itemDetails = itemDetails;
	}
}
