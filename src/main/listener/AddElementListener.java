package main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.TreePath;
import main.MainFrame;

public class AddElementListener implements ActionListener {
	private TreePath path;
	private MainFrame parent;

	public AddElementListener(MainFrame parent) {
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		switch (path.getPathCount()) {
		case 2:
			parent.showAddPropertyDialog(path);
			break;
		case 3:
			parent.showEditPropertyDialog(path);
			break;
		}

	}

	public void setPath(TreePath path) {
		this.path = path;
	}
}
