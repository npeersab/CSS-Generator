package main;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import res.ImgSrc;

public class IntroFrame extends JDialog {
	private static final long serialVersionUID = 1L;

	public IntroFrame() {
		// set image
		add(new JLabel(new ImageIcon(ImgSrc.getIntroImage())));
		
		// set dialog properties
		setSize(600, 236);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
	}
}
