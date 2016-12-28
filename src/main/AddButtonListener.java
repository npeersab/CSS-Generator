package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import addProperty.AddProperty;
import addSelector.AddSelector;
import css.Property;
import css.Selector;
import editProperty.EditProperty;

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
			new AddSelector(parent);
			break;
		case 2:
			new AddProperty(
					parent, (Selector) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject());
			break;
		case 3:
			Selector selector = (Selector) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();
			new EditProperty(
					parent, selector, (Property) ((DefaultMutableTreeNode) path.getPathComponent(2)).getUserObject());
			break;
		}
	}
	
	public void setPath(TreePath path) {
		this.path = path;
	}
}
