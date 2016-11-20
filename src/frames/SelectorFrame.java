package frames;

import css.Selector;
import css.SelectorType;
import main.MainClass;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SelectorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<SelectorType> selector;
	
	public SelectorFrame() {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 0);
		
		add(new JLabel("Select Selector Type : "), bagConstraints);
		
		bagConstraints.gridy++;
		add(new JLabel("Enter Selector name : "), bagConstraints);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(
				e -> dispose());
		bagConstraints.fill = GridBagConstraints.NONE;
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);
		
		
		SelectorType selectorType[] = {SelectorType.class_selector, SelectorType.element_type_selector,
				SelectorType.id_selector, SelectorType.universal_selector
		};
		selector = new JComboBox<SelectorType>(selectorType);
		selector.addActionListener( e -> {
				if (((SelectorType) selector.getSelectedItem()) == SelectorType.universal_selector) {
					textField.setText("*");
					textField.setEditable(false);
				}
				else {
					textField.setText("");
					textField.setEditable(true);
				}
		});
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(selector, bagConstraints);
		
		textField = new JTextField();
		bagConstraints.gridy++;
		add(textField, bagConstraints);
		
		bagConstraints.fill = GridBagConstraints.NONE;
		JButton addButton = new JButton("Add Selector");
		addButton.addActionListener(e -> {
			String selectorName = textField.getText();
			if(selectorName.length() == 0) {
				JOptionPane.showMessageDialog(SelectorFrame.this, 
						"Please enter Selector name", "No Selector name", 
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				SelectorType type = (SelectorType) selector.getSelectedItem();
				
				switch (type) {
				case class_selector:
					selectorName = "." + selectorName;
					break;
				case id_selector:
					selectorName = "#" + selectorName;
					break;
				case element_type_selector:
					break;
				case universal_selector:
					break;
				default:
					break;				
				}
				Selector temp_selector = new Selector(selectorName, type);
				MainClass.frame.addSelector(temp_selector);
				dispose();
			}
		});
		bagConstraints.gridy++;
		add(addButton, bagConstraints);
					
		setTitle("Add new Selector");
		setIconImage(ImgSrc.getImageIcon());
		setSize(400, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
