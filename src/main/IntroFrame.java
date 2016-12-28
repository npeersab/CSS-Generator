package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import res.ImgSrc;

public class IntroFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public IntroFrame() {

		// set image
		add(new JLabel(new ImageIcon(ImgSrc.getIntroImage())));
		setSize(600, 236);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated (true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setIconImage(ImgSrc.getImageIcon());
		setTitle("Starting CSS Generator");
		setVisible(true);
	}
}
