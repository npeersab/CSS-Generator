package main;

import css.PropertyDetailsList;
import frames.IntroFrame;
import frames.MainFrame;

public class MainClass {
	public static MainFrame frame;
	
	public static void main(String[] args) throws Exception {
		IntroFrame introFrame = new IntroFrame();
		new PropertyDetailsList();
		frame = new MainFrame();
		Thread.sleep(500);
		introFrame.dispose();
	}
}
