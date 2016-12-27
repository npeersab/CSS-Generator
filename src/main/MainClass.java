package main;

import css.PropertyDetailsList;
import frames.IntroFrame;
import frames.MainFrame;
import theme.ThemeColor;

public class MainClass {
	public static MainFrame frame;
	
	public static void main(String[] args) throws Exception {
		IntroFrame introFrame = new IntroFrame();
		Thread.sleep(1000);
		new PropertyDetailsList();
		ThemeColor.createDefaultThemes();
		frame = new MainFrame();
		Thread.sleep(1000);
		introFrame.dispose();
	}
}
