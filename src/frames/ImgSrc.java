package frames;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImgSrc {
	public static Image getImageIcon() {
		Image image = null;
		
		try {
			image = ImageIO.read(
					new File("/usr/local/CSS-Generator/res/icon.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"some files are missing please re-install CSS-Generator",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		return image;
	}
}
