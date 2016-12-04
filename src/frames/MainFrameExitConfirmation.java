package frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrameExitConfirmation extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton noButton, cancelButton, yesButton;

	public MainFrameExitConfirmation(MainFrame parent) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 0, 10, 0);
		
		JLabel label = new JLabel("\'" + parent.getCssFile().getName() + "\' has been"
				+ " modified. Save changes ?");
		add(label, bagConstraints);
		
		bagConstraints.insets = new Insets(10, 0, 10, 10);
		noButton = new JButton("No");
		noButton.addActionListener(e -> {
			parent.dispose();
			dispose();
		});
		bagConstraints.gridx++;
		bagConstraints.gridy++;
		add(noButton, bagConstraints);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> dispose());
		bagConstraints.gridx++;
		add(cancelButton, bagConstraints);
		
		yesButton = new JButton("Yes");
		yesButton.addActionListener(e -> {
			parent.save();
			parent.dispose();
			dispose();
		});
		bagConstraints.gridx++;
		add(yesButton, bagConstraints);
		
		setSize(700, 160);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(parent.getIconImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
