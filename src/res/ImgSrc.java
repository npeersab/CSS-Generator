package res;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import dialog.DialogBox;
import main.MainClass;

public class ImgSrc {
	private static final String PATH = "/usr/local/CSS-Generator/res/";
	public static Image getImage(String file) {
		Image image = null;
		
		try {
			image = ImageIO.read(new File(PATH + file));
		} catch (IOException e) {
			DialogBox dialogBox = new DialogBox(MainClass.frame, 
					"Some files are missing please re-install CSS-Generator", DialogBox.WARNING);
			dialogBox.showDialogBox();
			System.exit(1);
		}
		return image;
	}
	
	public static Image getIntroImage() {
		return getImage("IntroImage.png");
	}
	
	public static Image getImageIcon() {
		return getImage("icon.png");
	}
	
	public static Image getNewFileIcon() {
		return getImage("new.png");
	}
	
	public static Image getOpenFileIcon() {
		return getImage("open.png");
	}
	
	public static Image getSaveFileIcon() {
		return getImage("save.png");
	}
	
	public static Image getSaveAsIcon() {
		return getImage("saveas.png");
	}
}
