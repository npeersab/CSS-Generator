package main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import css.Property;
import css.Selector;
import main.MainFrame;

public class RemoveElementListener implements ActionListener {
	private TreePath path;
	private MainFrame parent;
			
	public RemoveElementListener(MainFrame parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = 
				(DefaultMutableTreeNode) path.getLastPathComponent();
		if (path.getPathCount() == 2) {
			Selector selector = (Selector) node.getUserObject();
			parent.removeSelector(selector);
		}

		if (path.getPathCount() == 3) {
			Selector selector = (Selector) (
					(DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();
			parent.removeProperty(selector, (Property) node.getUserObject());
		}
		parent.fileEdited();
	}
	
	public void setPath(TreePath path) {
		this.path = path;
	}
}

