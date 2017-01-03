package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import main.listener.AddButtonListener;
import main.listener.RemoveButtonListener;
import theme.ThemeColor;
import theme.ThemedJPanel;

public class ButtonPanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	// buttons
	private JButton addButton, removeButton;
	
	// buttonListeners
	private AddButtonListener addButtonListener;
	private RemoveButtonListener removeButtonListener;

	public ButtonPanel(MainFrame parent) {		
		// create addButton
		addButton = new JButton("Add Selector");
		parent.setAddButton(addButton);
		
		addButtonListener = new AddButtonListener(parent);		
		addButton.addActionListener(addButtonListener);
		parent.setAddButtonListener(addButtonListener);
		addButton.setEnabled(false);

		// create removeButton
		removeButton = new JButton("Remove Selector");
		parent.setRemoveButton(removeButton);
		
		removeButton.setMnemonic(KeyEvent.VK_DELETE);
		removeButtonListener = new RemoveButtonListener(parent);
		parent.setRemoveButtonListener(removeButtonListener);
		removeButton.addActionListener(removeButtonListener);
		removeButton.setEnabled(false);
		
		// set layout and add buttons
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.insets = new Insets(10, 10, 10, 10);
		bagConstraints.anchor = GridBagConstraints.WEST;
		add(addButton, bagConstraints);
		bagConstraints.gridy++;
		add(removeButton, bagConstraints);
		
		// get theme
		themeColor = parent.getThemeColor();
		
		setBackground(themeColor.backGroundDark);
		setVisible(false);
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		setBackground(themeColor.backGroundDark);
	}
}

