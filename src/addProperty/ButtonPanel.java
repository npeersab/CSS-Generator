package addProperty;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

import dialog.ButtonEvent;

public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ButtonPanel(AddProperty parent) {
		// set layout to grid bag
		setLayout(new GridBagLayout());

		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(5, 5, 5, 5);

		// add ADD button
		JButton addButton = new JButton("Add");
		addButton.setMargin(new Insets(2, 25, 2, 25));
		
		// action listener for add button
		addButton.addActionListener(
				e -> parent.dispatchButtonEvent(
						new ButtonEvent(
								parent.getDialog(), ButtonEvent.YES)));
		parent.setAddButton(addButton);
		bagConstraints.gridx++;
		add(addButton, bagConstraints);

		// add cancel button
		JButton cancelButton = new JButton("Cancel");
		parent.setCancelButton(cancelButton);
		// action listener for cancel button
		cancelButton.addActionListener(e -> parent.close());
		bagConstraints.gridx++;
		add(cancelButton, bagConstraints);
	}
}
