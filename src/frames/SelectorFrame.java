package frames;

import css.Selector;
import css.SelectorType;
import main.MainClass;
import res.ImgSrc;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private MainFrame parent;
	
	public SelectorFrame(MainFrame parent) {

		this.parent = parent;
		enableParent(false);
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 0);
		
		add(new JLabel("Select Selector Type : "), bagConstraints);
		
		bagConstraints.gridy++;
		add(new JLabel("Enter Selector name : "), bagConstraints);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			dispose();
			enableParent(true);
			});
		bagConstraints.fill = GridBagConstraints.NONE;
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);
		
		
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
				enableParent(true);
			}
		});
		bagConstraints.gridy++;
		add(addButton, bagConstraints);
					
		setTitle("Add new Selector");
		setIconImage(ImgSrc.getImageIcon());
		setSize(400, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				enableParent(true);
			}
		});
		setVisible(true);
		setAlwaysOnTop(true);
	}
	
	public void enableParent(boolean b) {
		parent.setFocusableWindowState(b);
		parent.setEnabled(b);
		parent.removeWindowListener(parent.getWindowAdapter());
		if (b)
			parent.addWindowListener(parent.getWindowAdapter());
		parent.revalidate();	
	}
}
