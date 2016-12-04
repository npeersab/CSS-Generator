package res;

import java.io.File;

public class Directory {
	public static File getHomeDirectory() {
		String home = System.getProperty("user.home");
		return new File(home, "css_generator");
	}
}
