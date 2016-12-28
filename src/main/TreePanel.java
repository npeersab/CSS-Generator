package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import theme.ThemedJPanel;

public class TreePanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	// reference to parent frame
	private MainFrame parent;
	
	private JScrollPane treePane;
	
	public TreePanel(MainFrame parent) {
		setLayout(new GridBagLayout());
		this.parent = parent;
		
		// get theme
		themeColor = parent.getThemeColor();
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
		setBackground(themeColor.backGroundDark);
		revalidate();
	}
}
