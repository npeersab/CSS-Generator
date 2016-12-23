package panels;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import css.Selector;

public class SelectorCodePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel selectorName;

	public SelectorCodePanel(Selector selector) {
		selectorName = new JLabel(selector.toString());
		selectorName.setForeground(Color.WHITE);
		add(selectorName);
		add(new JLabel(" {"));
		setBackground(Color.GRAY);
	}
}
