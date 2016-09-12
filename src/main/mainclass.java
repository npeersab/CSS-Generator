package main;

import css.CSSFile;

public class mainclass {

	public static void main(String[] args) throws Exception {

		
		CSSFile file = new CSSFile("test.css");
		file.ReadFile();
		System.out.println(file);
		file.WriteFile();
	}
}
