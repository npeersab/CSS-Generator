package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import main.listener.MouseKeyListener;
import theme.ThemeColor;
import theme.ThemedJPanel;

public class TreePanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	// reference to parent frame
	private MainFrame parent;

	private JScrollPane treePane;
	private JTree cssTree;

	public TreePanel(MainFrame parent) {
		setLayout(new GridBagLayout());
		this.parent = parent;

		// get theme
		themeColor = parent.getThemeColor();
	}

	public void updateTree() {
		// remove existing tree
		removeAll();

		cssTree = parent.getCssTree();
		cssTree.addMouseListener(new MouseKeyListener(parent, cssTree));
		treePane = new JScrollPane(
				cssTree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.insets = new Insets(5, 5, 5, 5);
		add(treePane, bagConstraints);
		setBackground(themeColor.backGroundDark);
		applyTheme(themeColor);
		revalidate();
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		setBackground(themeColor.backGroundDark);
		updateTreeTheme(themeColor);
	}

	public void updateTreeTheme(ThemeColor themeColor) {
		cssTree.setBackground(themeColor.backGroundLight);

		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) cssTree.getCellRenderer();
		cellRenderer.setTextNonSelectionColor(themeColor.font);
		cellRenderer.setTextSelectionColor(themeColor.font);

		cellRenderer.setBackgroundNonSelectionColor(themeColor.backGroundLight);
		cellRenderer.setBackgroundSelectionColor(themeColor.backGroundDark);

		cellRenderer.setBorderSelectionColor(themeColor.backGroundDark);
	}
}
