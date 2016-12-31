package main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import addProperty.AddProperty;
import addSelector.AddSelector;
import css.Property;
import css.Selector;
import dialog.ButtonEventListener;
import dialog.ButtonEvent;
import editProperty.EditProperty;
import main.MainFrame;

public class AddButtonListener implements ActionListener {
	private TreePath path;
	private MainFrame parent;
	
	public AddButtonListener(MainFrame parent) {
		this.parent = parent;
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {		
		switch (path.getPathCount()) {
		// if clicked on file name in tree
		case 1:
			AddSelector addSelector = new AddSelector(parent);
			addSelector.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					if (event.getId() == ButtonEvent.YES) {
						Selector selector = addSelector.getSelector();
						if (selector != null)
							parent.addSelector(selector);
						else
							return;
					}
					addSelector.close();
				}
			});
			addSelector.showAddSelector();
			
			break;
		case 2:
			Selector selector = (Selector) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();
			AddProperty addProperty = new AddProperty(parent);
			addProperty.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					if (event.getId() == ButtonEvent.YES) {
						Property property = addProperty.getProperty();
						if (property != null)
							parent.addProperty(selector, property);
						else return;
					}
					addProperty.close();
				}
			});
			addProperty.showAddProperty();
			break;
		case 3:
			selector = (Selector) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();
			EditProperty editProperty = new EditProperty(
					parent, selector, 
					(Property) ((DefaultMutableTreeNode) path.getPathComponent(2)).getUserObject());
			editProperty.showEditProperty();
			
			break;
		}
	}
	
	public void setPath(TreePath path) {
		this.path = path;
	}
}
