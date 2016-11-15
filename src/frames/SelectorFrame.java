package frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		
		bagConstraints.gridy++;
		add(new JLabel("Enter Selector name : "), bagConstraints);
		
		JButton cancelButton = new JButton("Cancel");
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);
		
		String selectors[] = {"Universal", "Element Type Selector", "ID Selector", "Class Selector"};
		JComboBox<String> selector = new JComboBox<String>(selectors);
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(selector, bagConstraints);
		
		JTextField textField = new JTextField();
		bagConstraints.gridy++;
		add(textField, bagConstraints);
		
		bagConstraints.insets = new Insets(10, 20, 10, 0);
		JButton addButton = new JButton("Add Selector");
		bagConstraints.gridy++;
		add(addButton, bagConstraints);
					
		setIconImage(ImgSrc.getImageIcon());
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
}
