package res;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import dialog.DialogBox;
import main.MainClass;

public class ImgSrc {
	public static Image getImage(String path) {
		Image image = null;
		
		try {
			image = ImageIO.read(
					new File(path));
		} catch (IOException e) {
			DialogBox dialogBox = new DialogBox(MainClass.frame, 
					"Some files are missing please re-install CSS-Generator", DialogBox.WARNING);
			dialogBox.showDialogBox();
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
