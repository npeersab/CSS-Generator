package panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frames.MainFrame;

public class TreePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// reference to parent frame
	private MainFrame parent;
	
	private JScrollPane treePane;
	
	public TreePanel(MainFrame parent) {
		setLayout(new GridBagLayout());
		this.parent = parent;
	}
	
	public void updateTree() {
		// remove existing tree
		removeAll();

		treePane = new JScrollPane(
				parent.getCssTree(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.insets = new Insets(5, 5, 5, 5);
		add(treePane, bagConstraints);
		setBackground(Color.DARK_GRAY);
		revalidate();
	}
}
