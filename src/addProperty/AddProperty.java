package addProperty;

import css.Property;
import css.PropertyDetails;
import css.PropertyGroup;
import dialog.Dialog;
import main.MainFrame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class AddProperty extends Dialog {
	
	// panels
	private PropertyPanel propertyPanel;
	private ValuePanel valuePanel;
	private PropertyGroupPanel propertyGroupPanel;
	private ButtonPanel buttonPanel;
	private DescriptionPanel descriptionPanel;
	
	// components
	private JComboBox<PropertyGroup> propertyGroupComboBox;
	private JComboBox<PropertyDetails> propertyComboBox;
	private JButton addButton, cancelButton;
	private JTextArea description;
		
	// default frame size;
	public final Dimension FRAME_SIZE = new Dimension(550, 400);
	
	// reference to parent
	private MainFrame parent;

	// Constructor
	public AddProperty(MainFrame parent) {
		// store the references
		this.parent = parent;
	}
	
	public void showAddProperty() {
		// create dialog
		dialog = new JDialog(parent, "Add new Property", true);		
		
		// set layout to GridBaglayout and create GridBagConstraints
		dialog.setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);
					
		// add propertyTypePanel
		propertyGroupPanel = new PropertyGroupPanel(this);
		dialog.add(propertyGroupPanel, bagConstraints);
		
		descriptionPanel = new DescriptionPanel(this);
		
		// add propertyPanel
		propertyPanel = new PropertyPanel(this);
		bagConstraints.gridy++;
		dialog.add(propertyPanel, bagConstraints);
		
		// add description panel
		bagConstraints.gridy++;
		dialog.add(descriptionPanel, bagConstraints);
		
		// add valuePanel
		valuePanel = new ValuePanel(this);
		bagConstraints.gridy++;
		dialog.add(valuePanel, bagConstraints);
		
		// add buttonPanel
		buttonPanel = new ButtonPanel(this);
		bagConstraints.anchor = GridBagConstraints.EAST;
		bagConstraints.gridy++;
		dialog.add(buttonPanel, bagConstraints);
		
		// action listener for cancel button
		cancelButton.addActionListener(e -> dialog.dispose());
			
		// set dialog properties
		dialog.setSize(FRAME_SIZE);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(parent);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	// return property
	public Property getProperty() {
		String value = valuePanel.getValue();
		PropertyDetails propertyDetails = (PropertyDetails) propertyComboBox.getSelectedItem();
		return new Property(
				propertyDetails.toString(), value, propertyDetails.getDescription()); 
	}

	
	// update description
	public void updateDescription(String description) {
		StringBuffer stringBuffer = new StringBuffer(description);
		// resize description
		test: if (stringBuffer.length() > 41) {
			int n = stringBuffer.indexOf(" ", 40);
			try {
				stringBuffer.insert(n, '\n');

				if (stringBuffer.length() > 41) {
					n = stringBuffer.indexOf(" ", 70);
					stringBuffer.insert(n, '\n');
				}
			} catch (ArrayIndexOutOfBoundsException exe) {
				break test;
			}
		}
		stringBuffer.insert(0, ' ');
		this.description.setText(stringBuffer.toString());
	}
	
	// getter and setter for property description
	public JTextArea getDescription() {
		return description;
	}
	public void setDescription(JTextArea description) {
		this.description = description;
	}
	
	//getter and setter for cancelButton
	public JButton getCancelButton() {
		return cancelButton;
	}
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	// getter and setter for propertyGroupComboBox
	public JComboBox<PropertyGroup> getPropertyGroupComboBox() {
		return propertyGroupComboBox;
	}
	public void setPropertyGroupComboBox(JComboBox<PropertyGroup> propertyTypeComboBox) {
		this.propertyGroupComboBox = propertyTypeComboBox;
	}

	// getter and setter for propertyComboBox
	public JComboBox<PropertyDetails> getPropertyComboBox() {
		return propertyComboBox;
	}
	public void setPropertyComboBox(JComboBox<PropertyDetails> propertyComboBox) {
		this.propertyComboBox = propertyComboBox;
	}

	// getter and setter for ValuePanel
	public ValuePanel getValuePanel() {
		return valuePanel;
	}
	public void setValuePanel(ValuePanel valuePanel) {
		this.valuePanel = valuePanel;
	}

	// getter and setter for PropertyPanel
	public PropertyPanel getPropertyPanel() {
		return propertyPanel;
	}
	public void setSelectPropertyPanel(PropertyPanel propertyPanel) {
		this.propertyPanel = propertyPanel;
	}

	// getter and setter for addButton
	public JButton getAddButton() {
		return addButton;
	}
	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}
}