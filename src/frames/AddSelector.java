package frames;

import css.Selector;
import css.SelectorType;
import res.ImgSrc;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddSelector extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<SelectorType> selector;
	private MainFrame parent;
	
	public AddSelector(MainFrame parent) {

		this.parent = parent;
		
		// disable parent window
		enableParent(false);
		
		// set layout and GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 10);
		
		// add labels
		add(new JLabel("Select Selector Type : "), bagConstraints);
		bagConstraints.gridy++;
		add(new JLabel("Enter Selector name : "), bagConstraints);
				
		// add selector type comboBox
		SelectorType selectorType[] = SelectorType.values();
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
		
		// add textField to get selector name
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		bagConstraints.gridy++;
		add(textField, bagConstraints);
		
		//  add addButton
		JButton addButton = new JButton("Add Selector");
		addButton.addActionListener(this);
		bagConstraints.anchor = GridBagConstraints.EAST;
		bagConstraints.fill = GridBagConstraints.NONE;
		bagConstraints.gridy++;
		add(addButton, bagConstraints);
		
		// add cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			dispose();
			enableParent(true);
			});
		bagConstraints.gridx++;
		add(cancelButton, bagConstraints);
					
		setTitle("Add new Selector");
		setIconImage(ImgSrc.getImageIcon());
		setSize(600, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				enableParent(true);
				dispose();
			}
		});
		setVisible(true);
		setAlwaysOnTop(true);
	}
	
	// disable/enable parent window
		private void enableParent(boolean b) {
			parent.enableWindow(b);
			setAlwaysOnTop(!b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectorName = textField.getText();
		// display error if no name entered
		if(selectorName.length() == 0) {
			JOptionPane.showMessageDialog(AddSelector.this, 
					"Please enter Selector name", "No Selector name", 
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			SelectorType type = (SelectorType) selector.getSelectedItem();
			// set symbol to selector according to type
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
			parent.addSelector(temp_selector);
			dispose();
			enableParent(true);
		}		
	}
}