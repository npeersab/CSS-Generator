package main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import theme.ThemeColor;
import theme.ThemedJPanel;
import codePanel.CodePanel;

public class LeftPanel extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	private JLabel header;
	private CodePanel codePanel;
	ButtonPanel buttonPanel;

	public LeftPanel(MainFrame parent) {
		// set layout
		setLayout(new GridBagLayout());
		
		// create constraint
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 20, 10, 5);
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.weightx = bagConstraints.weighty = 0.1;
		
		// get theme
		themeColor = parent.getThemeColor();
		
		// add header
		header = new JLabel("Welcome to CSS Generator");
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setForeground(themeColor.font);
		add(header, bagConstraints);
		
		// add code panel
		codePanel = new CodePanel(this);
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
		buttonPanel = new ButtonPanel(parent);
		parent.setButtonPanel(buttonPanel);
		bagConstraints.gridy++;
		bagConstraints.weighty = 0.01;
		add(buttonPanel, bagConstraints);
		
		setBackground(themeColor.backGroundDark);
	}
	
	public void setHeader(String header) {
		this.header.setText(header);
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		
		header.setForeground(themeColor.font);
		setBackground(themeColor.backGroundDark);
		
		buttonPanel.applyTheme(themeColor);
	}
}
