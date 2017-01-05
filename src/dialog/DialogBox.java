package dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DialogBox extends Dialog {
	// dialog type
	public static final int SAVE_MODIFIED = 1, OVERWRITE = 2, WARNING = 3;
	private int type;

	// message
	private String message;

	// title
	private String title;
	
	// buttons
	private JButton yesButton, noButton, cancelButton;

	// constructor for JFrame
	public DialogBox(JFrame parent, String name, int type) {
		this(name, type);

		dialog = new JDialog(parent, title, true);
	}

	// constructor for JDialog
	public DialogBox(JDialog parent, String name, int type) {
		this(name, type);

		dialog = new JDialog(parent, title, true);
	}

	DialogBox(String name, int type) {
		this.type = type;
		switch (type) {
		case SAVE_MODIFIED:
			message = "\'" + name + "\' has been  modified. Save changes ?";
			title = "Save Changes?";
			break;
		case OVERWRITE:
			message = "\'" + name + "\' already exists. Do you want to overwrite it?";
			title = "Overwrite ?";
			break;
		case WARNING:
			message = name;
			title = "Warning";
		}
	}

	public void showDialogBox() {
		// set layout 
		dialog.setLayout(new GridBagLayout());

		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 0, 10, 0);

		// set confirmation message
		JLabel label = new JLabel(message);
		dialog.add(label, bagConstraints);		

		// create action listener for buttons
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				JButton button = (JButton) e.getSource();

				if (button.equals(yesButton))
					id = ButtonEvent.YES;
				else if (button.equals(noButton))
					id = ButtonEvent.NO;
				else if (button.equals(cancelButton))
					id = ButtonEvent.CANCEL;

				dispatchButtonEvent(new ButtonEvent(dialog, id));
			}
		};
		
		switch (type) {
		case SAVE_MODIFIED:
			// add no button
			bagConstraints.insets = new Insets(10, 0, 10, 10);
			bagConstraints.anchor = GridBagConstraints.EAST;
			noButton = new JButton("No");
			noButton.addActionListener(actionListener);
			bagConstraints.gridy++;
			dialog.add(noButton, bagConstraints);
			
			// add cancel button
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(actionListener);
			bagConstraints.gridx++;
			dialog.add(cancelButton, bagConstraints);
			
			// add yes button
			yesButton = new JButton("Yes");
			yesButton.addActionListener(actionListener);
			bagConstraints.gridx++;
			dialog.add(yesButton, bagConstraints);
			dialog.setSize(700, 160);
			break;
			
		case OVERWRITE:
			// add no button
			bagConstraints.insets = new Insets(10, 0, 10, 10);
			bagConstraints.anchor = GridBagConstraints.EAST;
			noButton = new JButton("No");
			noButton.addActionListener(actionListener);
			bagConstraints.gridy++;
			dialog.add(noButton, bagConstraints);

			// add yes button
			yesButton = new JButton("Yes");
			yesButton.addActionListener(actionListener);
			bagConstraints.gridx++;
			dialog.add(yesButton, bagConstraints);
			dialog.setSize(700, 160);
			break;
			
		case WARNING:
			// add OK button
			yesButton = new JButton("OK");
			yesButton.addActionListener(e -> close());
			bagConstraints.gridy++;
			dialog.add(yesButton, bagConstraints);
			dialog.setSize(500, 160);
			break;
		}
		
		// set dialog properties
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}
