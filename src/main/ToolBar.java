package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import res.ImgSrc;
import theme.ThemeColor;
import theme.ThemedJPanel;

public class ToolBar extends ThemedJPanel {
	private static final long serialVersionUID = 1L;
	
	// buttons
	JButton newButton, openButton, saveButton, saveAsButton; 	
	
	public ToolBar(MainFrame parent) {
		// set layout
		setLayout(new GridBagLayout());
			
		// create constraint
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.weightx = bagConstraints.weighty = 0.1;
		bagConstraints.insets = new Insets(0, 5, 0, 0);
		
		// create new button
		newButton = new JButton(new ImageIcon(ImgSrc.getNewFileIcon()));
		newButton.setBorderPainted(false);
		Insets marginInsets = new Insets(4, 4, 4, 4);
		newButton.setMargin(marginInsets);
		newButton.addActionListener(e -> parent.newFile());
		add(newButton, bagConstraints);
		
		// create open button
		openButton = new JButton(new ImageIcon(ImgSrc.getOpenFileIcon()));
		openButton.setBorderPainted(false);
		openButton.setMargin(marginInsets);
		openButton.addActionListener(e -> parent.openFile());
		bagConstraints.gridx++;
		add(openButton, bagConstraints);
		
		// create save button
		saveButton = new JButton(new ImageIcon(ImgSrc.getSaveFileIcon()));
		parent.setSaveButton(saveButton);
		saveButton.setBorderPainted(false);
		saveButton.setMargin(marginInsets);
		saveButton.setEnabled(false);
		saveButton.addActionListener(e -> parent.save());
		bagConstraints.gridx++;
		add(saveButton, bagConstraints);
		
		// create saveAs button
		saveAsButton = new JButton(new ImageIcon(ImgSrc.getSaveAsIcon()));
		parent.setSaveAsButton(saveAsButton);
		saveAsButton.setBorderPainted(false);
		saveAsButton.setMargin(marginInsets);
		saveAsButton.setEnabled(false);
		saveAsButton.addActionListener(e -> parent.saveAs());
		bagConstraints.gridx++;
		add(saveAsButton, bagConstraints);
		
		// add empty label to cover remaining area
		JLabel label = new JLabel();
		bagConstraints.weightx = 20;
		bagConstraints.gridx++;
		add(label, bagConstraints);
		
		applyTheme(parent.getThemeColor());
	}

	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		setBackground(themeColor.backGroundDark);
		
		newButton.setBackground(themeColor.backGroundLight);
		openButton.setBackground(themeColor.backGroundLight);
		saveButton.setBackground(themeColor.backGroundLight);
		saveAsButton.setBackground(themeColor.backGroundLight);
	}
}
