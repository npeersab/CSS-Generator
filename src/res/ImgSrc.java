package res;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImgSrc {
	public static Image getImage(String path) {
		Image image = null;
		
		try {
			image = ImageIO.read(
					new File(path));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"some files are missing please re-install CSS-Generator",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		return image;
	}
	
	public static Image getIntroImage() {
		return getImage("/usr/local/CSS-Generator/res/IntroImage.png");
	}
	
	public static Image getImageIcon() {
		return getImage("/usr/local/CSS-Generator/res/icon.png");
	}
}
