package main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import addProperty.AddProperty;
import addSelector.AddSelector;
import css.CSSFile;
import css.Property;
import css.Selector;
import dialog.ButtonEventListener;
import dialog.DialogBox;
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
			// create new add selector dialog
			AddSelector addSelector = new AddSelector(parent);
			
			// add listener to dialog
			addSelector.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					// if add button is present
					if (event.getId() == ButtonEvent.YES) {
						Selector selector = addSelector.getSelector();
						
						if (selector != null) {
							CSSFile cssFile = parent.getCssFile();
							
							// if the selector is already present
							if (cssFile.contains(selector)) {
								
								// create overwrite confirmation window
								DialogBox dialogBox = new DialogBox(
										addSelector.getDialog(), selector.toString(), DialogBox.OVERWRITE);
								
								// add listener to dialog
								dialogBox.addButtonEventListener(new ButtonEventListener() {
									@Override
									public void handleButtonEvent(ButtonEvent event) {
										
										// if yes button is pressed
										if (event.getId() == ButtonEvent.YES) {
											
											// remove all existing selectors with same name
											cssFile.removeAllSelector(selector);
											
											// add new selector
											parent.addSelector(selector);
											addSelector.close();
										}
										// close confirmation if any button pressed
										dialogBox.close();
									}
								});
								dialogBox.showDialogBox();
							}
							else {
								parent.addSelector(selector);
								addSelector.close();
							}
						}
						else
							return;
					}
				}
			});
			addSelector.showAddSelector();
			
			break;
		case 2:
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getPathComponent(1);
			Selector selector = (Selector) node.getUserObject();
			
			// create new add property dialog
			AddProperty addProperty = new AddProperty(parent);
			
			// add listener to dialog box
			addProperty.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					
					// when add button is pressed
					if (event.getId() == ButtonEvent.YES) {
						Property property = addProperty.getProperty();
						if (property != null) {
							
							// if the property is already present in selector
							if (selector.contains(property)) {
								
								// create new overwrite confirmation dialog
								DialogBox dialogBox = new DialogBox(
										addProperty.getDialog(), property.getName(), DialogBox.OVERWRITE);								
								
								// add listener to dialog
								dialogBox.addButtonEventListener(new ButtonEventListener() {
									@Override
									public void handleButtonEvent(ButtonEvent event) {
										
										// if yes button is pressed
										if (event.getId() == ButtonEvent.YES) {
											
											// remove all properties with same name from  selector
											selector.removeAllProperties(parent, property);
											
											// add new property
											parent.addProperty(selector, property, node);
											
											// expand tree
											parent.getCssTree().expandPath(path);
											addProperty.close();
										}
										// close confirmation if any button pressed
										dialogBox.close();
									}
								});
								dialogBox.showDialogBox();
							}
							else  {
								parent.addProperty(selector, property, node);
								parent.getCssTree().expandPath(path);
								addProperty.close();
							}
						}
						else return;
					}
				}
			});
			addProperty.showAddProperty();
			break;
		case 3:
			// edit the property
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
