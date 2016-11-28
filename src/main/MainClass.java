package main;

import css.PropertyDetailsList;
import frames.MainFrame;
import frames.PropertyFrame;

public class MainClass {
	public static MainFrame frame;
	
	public static void main(String[] args) throws Exception {
		new PropertyDetailsList();
	//	frame = new MainFrame();
		
		System.out.println();
		new PropertyFrame();
	}
}
