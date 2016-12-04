package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		// remove existing treePane
		if (treePane != null) 
			remove(treePane);
		
		treePane = new JScrollPane(
				parent.getCssTree(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.fill = GridBagConstraints.BOTH;
		add(treePane, bagConstraints);
		revalidate();
	}
}
