package main;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import theme.ThemeColor;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	public MenuBar(MainFrame parent) {
		JMenuItem newfile = new JMenuItem("New");
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		newfile.setMnemonic(KeyEvent.VK_N);
		newfile.addActionListener(e -> parent.newFile());
		
		JMenuItem openfile = new JMenuItem("Open File...");
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		openfile.setMnemonic(KeyEvent.VK_O);
		openfile.addActionListener(e -> parent.openFile());
		
		parent.save = new JMenuItem("Save");
		parent.save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		parent.save.setMnemonic(KeyEvent.VK_S);
		parent.save.setEnabled(false);
		parent.save.addActionListener(e -> parent.save());
		
		parent.saveAs  = new JMenuItem("Save As...");
		parent.saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		parent.saveAs.setEnabled(false);
		parent.saveAs.addActionListener(e -> parent.saveAs());
		parent.saveAs.setMnemonic(KeyEvent.VK_S);
				
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(e -> parent.exit());
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.add(newfile);
		file.add(openfile);
		file.addSeparator();
		file.add(parent.save);
		file.add(parent.saveAs);
		file.addSeparator();
		file.add(exit);
		
		add(file);
		
		JMenu theme = new JMenu("Theme");
		theme.setMnemonic(KeyEvent.VK_T);
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButtonMenuItem black = new JRadioButtonMenuItem("Black");
		black.addActionListener(e -> parent.applyTheme(ThemeColor.black));
		black.setMnemonic(KeyEvent.VK_B);
		buttonGroup.add(black);
		theme.add(black);
		
		JRadioButtonMenuItem white = new JRadioButtonMenuItem("White");
		white.addActionListener(e -> parent.applyTheme(ThemeColor.white));
		white.setSelected(true);
		white.setMnemonic(KeyEvent.VK_W);
		buttonGroup.add(white);
		theme.add(white);
		
		JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red");
		red.addActionListener(e -> parent.applyTheme(ThemeColor.red));
		red.setSelected(true);
		red.setMnemonic(KeyEvent.VK_R);
		buttonGroup.add(red);
		theme.add(red);
		
		JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blue");
		blue.addActionListener(e -> parent.applyTheme(ThemeColor.blue));
		blue.setSelected(true);
		blue.setMnemonic(KeyEvent.VK_L);
		buttonGroup.add(blue);
		theme.add(blue);

		JMenu view = new JMenu("View");
		view.add(theme);
		view.setMnemonic(KeyEvent.VK_V);
		add(view);
	}
}
