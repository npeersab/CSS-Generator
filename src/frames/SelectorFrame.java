package frames;

import css.Selector;
import css.SelectorType;
import main.MainClass;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SelectorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
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
		cancelButton.addActionListener(
				e -> dispose());
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);

		SelectorType selectorType[] = {SelectorType.class_selector, SelectorType.element_type_selector,
				SelectorType.id_selector, SelectorType.universal_selector
		};
		JComboBox<SelectorType> selector = new JComboBox<SelectorType>(selectorType);
		selector.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((SelectorType) selector.getSelectedItem()) == SelectorType.universal_selector) {
					textField.setText("*");
					textField.setEditable(false);
				}
				else {
					textField.setText("");
					textField.setEditable(true);
				}
			}
		});
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(selector, bagConstraints);
		
		textField = new JTextField();
		bagConstraints.gridy++;
		add(textField, bagConstraints);
		
		bagConstraints.insets = new Insets(10, 20, 10, 0);
		JButton addButton = new JButton("Add Selector");
		addButton.addActionListener(e -> {
			Selector temp_selector = new Selector(textField.getText(),
					(SelectorType) selector.getSelectedItem());
			MainClass.frame.addSelector(temp_selector);
			dispose();
		});
		bagConstraints.gridy++;
		add(addButton, bagConstraints);
					
		setTitle("Add new Selector");
		setIconImage(ImgSrc.getImageIcon());
		setSize(400, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
}
