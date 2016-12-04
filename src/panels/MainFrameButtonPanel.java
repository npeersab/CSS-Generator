package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import frames.MainFrame;
import listeners.AddButtonListener;
import listeners.RemoveButtonListener;

public class MainFrameButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// buttons
	private JButton addButton, removeButton;
	
	// buttonListeners
	private AddButtonListener addButtonListener;
	private RemoveButtonListener removeButtonListener;

	public MainFrameButtonPanel(MainFrame parent) {
		
		
		// create addButton
		addButton = new JButton();
		parent.setAddButton(addButton);
		
		addButtonListener = new AddButtonListener(parent);		
		addButton.addActionListener(addButtonListener);
		parent.setAddButtonListener(addButtonListener);
		addButton.setVisible(false);

		// create removeButton
		removeButton = new JButton();
		parent.setRemoveButton(removeButton);
		
		removeButton.setMnemonic(KeyEvent.VK_DELETE);
		removeButtonListener = new RemoveButtonListener(parent);
		parent.setRemoveButtonListener(removeButtonListener);
		removeButton.addActionListener(removeButtonListener);
		removeButton.setVisible(false);
		
		// set layout and add buttons
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.insets = new Insets(10, 10, 10, 10);
		bagConstraints.anchor = GridBagConstraints.WEST;
		add(addButton, bagConstraints);
		bagConstraints.gridy++;
		add(removeButton, bagConstraints);
	}
}
