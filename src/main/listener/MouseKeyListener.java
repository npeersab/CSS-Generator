package main.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import css.CSSFile;
import css.Property;
import css.Selector;
import main.MainFrame;

public class MouseKeyListener extends MouseAdapter {
	// reference to components
	private JTree cssTree;
	private MainFrame parent;
	
	// constructor
	public MouseKeyListener(MainFrame parent, JTree cssTree) {
		this.cssTree = cssTree;
		this.parent = parent;
	}

	// when mouse is clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		// if right button clicked
		if (SwingUtilities.isRightMouseButton(e)) {
			// get row on which mouse is clicked
			int row = cssTree.getRowForLocation(e.getX(), e.getY());
			// select that row
			cssTree.setSelectionRow(row);
			if (row != -1) {
				TreePath path = cssTree.getSelectionModel().getSelectionPath();

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				Object object = node.getUserObject();

				JPopupMenu popupMenu = new JPopupMenu();
				
				// if  file is selected
				if (object instanceof CSSFile) {
					JMenuItem addSelectorItem = new JMenuItem("Add Selector");
					addSelectorItem.addActionListener(event -> parent.showAddSelectorDialog());

					popupMenu.add(addSelectorItem);
				}
				// if selector is selected
				else if (object instanceof Selector) {
					JMenuItem addPropertyItem = new JMenuItem("Add Property");
					addPropertyItem.addActionListener(event -> parent.showAddPropertyDialog(path));

					JMenuItem removeSelectorItem = new JMenuItem("Remove Selector");
					removeSelectorItem.addActionListener(parent.getRemoveButtonListener());

					popupMenu.add(addPropertyItem);
					popupMenu.add(removeSelectorItem);
				}
				// if Property is selected
				else if (object instanceof Property) {
					JMenuItem editPropertyItem = new JMenuItem("Edit Property");
					editPropertyItem.addActionListener(event -> parent.showEditPropertyDialog(path));

					JMenuItem removePropertyItem = new JMenuItem("Remove Property");
					removePropertyItem.addActionListener(parent.getRemoveButtonListener());

					popupMenu.add(editPropertyItem);
					popupMenu.add(removePropertyItem);
				}

				popupMenu.show(cssTree, e.getX(), e.getY());
			}
		}
	}
}
