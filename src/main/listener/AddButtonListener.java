package main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.TreePath;
import main.MainFrame;

public class AddButtonListener implements ActionListener {
	private TreePath path;
	private MainFrame parent;

	public AddButtonListener(MainFrame parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource().equals(parent.getAddSelectorButton())) {
			parent.showAddSelectorDialog();
		}
		else {
			switch (path.getPathCount()) {
			case 2:
				parent.showAddPropertyDialog(path);
				break;
			case 3:
				parent.showEditPropertyDialog(path);
				break;
			}
		}
	}

	public void setPath(TreePath path) {
		this.path = path;
	}
}
