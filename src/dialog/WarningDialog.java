package dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WarningDialog extends Dialog {
	private JButton okButton;
	private String message;

	public WarningDialog(JDialog parent, String message) {
		this(message);

		// create dialog
		dialog = new JDialog(parent, "Warning", true);
	}

	public WarningDialog(JFrame parent, String message) {
		this(message);

		// create dialog
		dialog = new JDialog(parent, "Warning", true);
	}

	public WarningDialog(String message) {
		this.message = message;
	}

	public void showWarningDialog() {

		// set layout 
		dialog.setLayout(new GridBagLayout());

		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 0, 10, 0);

		// set confirmation message
		JLabel label = new JLabel(message);
		dialog.add(label, bagConstraints);

		// add OK button
		okButton = new JButton("OK");
		okButton.addActionListener(e -> close());
		bagConstraints.gridy++;
		dialog.add(okButton, bagConstraints);

		// set dialog properties
		dialog.setSize(400, 160);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
}
