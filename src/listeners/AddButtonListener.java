package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import css.Selector;
import frames.MainFrame;
import frames.PropertyFrame;
import frames.SelectorFrame;

public class AddButtonListener implements ActionListener {
	private TreePath path;
	private MainFrame parent;
	
	public AddButtonListener(MainFrame parent) {
		this.parent = parent;
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (path.getPathCount()) {
		case 1:
			new SelectorFrame(parent);
			break;
		case 2:
			new PropertyFrame(
					parent, (Selector) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject());
			break;
		}
	}
	
	public void setPath(TreePath path) {
		this.path = path;
	}
}
