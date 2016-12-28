package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExitConfirmation extends JFrame {
	private static final long serialVersionUID = 1L;
	// components
	private JButton noButton, cancelButton, yesButton;
	
	// reference to main frame
	private MainFrame parent;

	public ExitConfirmation(MainFrame parent) {
		// store reference
		this.parent = parent;
		
		// disable main frame
		enableParent(false);
		
		// set layout and create bagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.EAST;
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
		//bagConstraints.gridx++;
		bagConstraints.gridy++;
		add(noButton, bagConstraints);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			dispose();
			enableParent(true);
		});
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
		
		// set frame properties
		setSize(600, 160);
		setTitle("Exit Confirmation");
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(parent.getIconImage());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				enableParent(true);
				dispose();
			}
		});
		setVisible(true);
	}
	
	// disable/enable parent window
	private void enableParent(boolean b) {
		parent.enableWindow(b);
		setAlwaysOnTop(!b);
	}
}
