package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import frames.MainFrame;

public class MainFrameLeftPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel header;
	private CodePanel codePanel;

	public MainFrameLeftPanel(MainFrame parent) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 20, 10, 5);
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.weightx = bagConstraints.weighty = 0.1;
		
		// add header
		header = new JLabel("Welcome to CSS Generator");
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setForeground(Color.WHITE);
		add(header, bagConstraints);
		
		// add code panel
		codePanel = new CodePanel();
		parent.setCodePanel(codePanel);
		JScrollPane scrollPane = new JScrollPane(
				codePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(7);
		bagConstraints.weighty = 1;
		bagConstraints.gridy++;
		bagConstraints.insets = new Insets(0, 5, 5, 5);
		add(scrollPane, bagConstraints);
		
		// create and add buttons panel
		MainFrameButtonPanel buttonPanel = new MainFrameButtonPanel(parent);
		bagConstraints.gridy++;
		bagConstraints.weighty = 0.01;
		add(buttonPanel, bagConstraints);
		
		setBackground(Color.DARK_GRAY);
	}
}
