package dialog;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class OverwriteConfirmation extends Dialog {
	private final String name,
	TITLE = "Overwrite Confirmation";

	// components
	private JButton yesButton, noButton;

	public OverwriteConfirmation(String name) {
		this.name = name;
	}

	public OverwriteConfirmation(Frame parent, String name) {
		this(name);
		dialog = new JDialog(parent, TITLE, true);
	}

	public OverwriteConfirmation(JDialog parent, String name) {
		this(name);
		dialog = new JDialog(parent, TITLE, true);
	}

	public void showOverwriteConfirmation() {
		// set layout 
		dialog.setLayout(new GridBagLayout());

		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.EAST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 0, 10, 0);

		// set confirmation message
		JLabel label = new JLabel("\'" + name + "\' already exists."
				+ " Do you want to overwrite it?");
		dialog.add(label, bagConstraints);

		// create action listener for buttons
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				JButton button = (JButton) e.getSource();

				if (button.equals(yesButton))
					id = ButtonEvent.YES;

				dispatchButtonEvent(new ButtonEvent(dialog, id));
			}
		};

		// add no button
		bagConstraints.insets = new Insets(10, 0, 10, 10);
		noButton = new JButton("No");
		noButton.addActionListener(actionListener);
		bagConstraints.gridy++;
		dialog.add(noButton, bagConstraints);

		// add yes button
		yesButton = new JButton("Yes");
		yesButton.addActionListener(actionListener);
		bagConstraints.gridx++;
		dialog.add(yesButton, bagConstraints);

		// set dialog properties
		dialog.setSize(600, 160);
		dialog.setTitle("Exit Confirmation");
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}
