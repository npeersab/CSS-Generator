package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import css.Property;
import css.Selector;
import frames.MainFrame;

public class RemoveButtonListener implements ActionListener {
	private TreePath path;
	private MainFrame parent;
			
	public RemoveButtonListener(MainFrame parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = 
				(DefaultMutableTreeNode) path.getLastPathComponent();
		if (path.getPathCount() == 2) {
			parent.getCssFile().removeSelector(
					(Selector) node.getUserObject());
		}

		if (path.getPathCount() == 3) {
			Selector selector = (Selector) (
					(DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();
			selector.removeProperty((Property) node.getUserObject());
		}
		
		DefaultTreeModel model = (DefaultTreeModel) parent.getCssTree().getModel();
		if (node.getParent() != null)
			model.removeNodeFromParent(node);
		parent.getAddButton().setVisible(false);
		parent.getRemoveButton().setVisible(false);
		
		parent.fileEdited();
	}
	
	public void setPath(TreePath path) {
		this.path = path;
	}
}
