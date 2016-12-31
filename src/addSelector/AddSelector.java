package addSelector;

import css.Selector;
import css.SelectorType;
import dialog.ButtonEvent;
import dialog.ButtonEventListener;
import main.MainFrame;
import res.ImgSrc;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddSelector {
	// components
	private JTextField textField;
	private JComboBox<SelectorType> selector;
	private JButton addButton, cancelButton;
	private JDialog dialog;
	private List<ButtonEventListener> buttonEventListeners = 
			new ArrayList<ButtonEventListener>();
	
	// reference to parent
	private MainFrame parent;
	
	public AddSelector(MainFrame parent) {
		this.parent = parent;		
	}
	
	public void showAddSelector() {		
		// create new dialog
		dialog = new JDialog(parent, "Add Selector", true);
		
		// set layout
		dialog.setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 10);
		
		// add labels
		dialog.add(new JLabel("Select Selector Type : "), bagConstraints);
		bagConstraints.gridy++;
		dialog.add(new JLabel("Enter Selector name : "), bagConstraints);
				
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
		dialog.add(selector, bagConstraints);
		
		// add textField to get selector name
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		bagConstraints.gridy++;
		dialog.add(textField, bagConstraints);
		
		// create action listener
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if (button.equals(addButton))
					dispatchButtonEvent(new ButtonEvent(dialog, ButtonEvent.YES));
				else
					dialog.dispose();
			}
		};
		
		//  add addButton
		addButton = new JButton("Add Selector");
		addButton.addActionListener(actionListener);
		bagConstraints.anchor = GridBagConstraints.EAST;
		bagConstraints.fill = GridBagConstraints.NONE;
		bagConstraints.gridy++;
		dialog.add(addButton, bagConstraints);
		
		// add cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		bagConstraints.gridx++;
		dialog.add(cancelButton, bagConstraints);
					
		dialog.setIconImage(ImgSrc.getImageIcon());
		dialog.setSize(600, 200);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
	}

	public Selector getSelector() {
		String selectorName = textField.getText();
		// display error if no name entered
		if(selectorName.length() == 0) {
			JOptionPane.showMessageDialog(dialog, 
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
			
			return new Selector(selectorName, type);
		}
		return null;
	}
	
	// close the dialog box
    public void close() {
        if(dialog != null) {
            dialog.dispose();
        }
    }
    
    // add new listener
    public void addButtonEventListener(ButtonEventListener buttonEventListener) {
        if(!buttonEventListeners.contains(buttonEventListener)) {
            buttonEventListeners.add(buttonEventListener);
        }
    }

    // remove the listener
    public void removeButtonEventListener(ButtonEventListener buttonEventListener) {
    	buttonEventListeners.remove(buttonEventListener);
    }

    // perform action when an event is occurred
    public void dispatchButtonEvent(ButtonEvent evt) {
        for(ButtonEventListener buttonEventListener: buttonEventListeners) {
            buttonEventListener.handleButtonEvent(evt);
        }
    }
}