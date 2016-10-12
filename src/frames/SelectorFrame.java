package frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import css.CSSSelector;

public class SelectorFrame extends JFrame {

	private CSSSelector selector;
		
	public SelectorFrame() {
		
		add(new JLabel("Select Selector Type"));
	}
}
