package frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SelectorFrame extends JFrame {

	//private CSSSelector selector;
	private static final long serialVersionUID = 1L;

	public SelectorFrame() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 0);
		
		add(new JLabel("Select Selector Type : "), bagConstraints);
		
		String selectors[] = {"Universal", "Element Type Selector", "ID Selector", "Class Selector"};
		JComboBox<String> selector = new JComboBox<String>(selectors);
		bagConstraints.gridx++;
		add(selector, bagConstraints);
		
		setIconImage(ImgSrc.getImageIcon());
		setSize(500, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
}
