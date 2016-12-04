package res;

import java.io.File;

public class Directory {
	public static File getCSSDirectory() {
		String home = System.getProperty("user.home");
		File file = new File(home, "CSS Generator");
		if(!file.exists())
			file.mkdir();
		return file;
	}
}
