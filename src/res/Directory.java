package res;

import java.io.File;

public class Directory {
	
	// return CSS home directory
	public static File getCSSDirectory() {
		String home = System.getProperty("user.home");
		File file = new File(home, "CSS Generator");
		// if directory is not present create new
		if(!file.exists())
			file.mkdir();
		return file;
	}
}
