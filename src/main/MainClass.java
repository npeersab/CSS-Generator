package main;

import css.PropertyDetailsList;
import css.Selector;
import css.SelectorType;
import frames.MainFrame;
import frames.PropertyFrame;

public class MainClass {
	public static MainFrame frame;
	
	public static void main(String[] args) throws Exception {
		new PropertyDetailsList();
		//frame = new MainFrame();
		new PropertyFrame(new Selector("test", SelectorType.class_selector));
	}
}
