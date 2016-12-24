package main;

import css.PropertyDetailsList;
import frames.IntroFrame;
import frames.MainFrame;

public class MainClass {
	public static MainFrame frame;
	
	public static void main(String[] args) throws Exception {
		IntroFrame introFrame = new IntroFrame();
		Thread.sleep(1000);
		new PropertyDetailsList();
		frame = new MainFrame();
		Thread.sleep(1000);
		introFrame.dispose();
	}
}
