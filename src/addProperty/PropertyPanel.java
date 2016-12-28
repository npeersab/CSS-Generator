package addProperty;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import css.PropertyDetails;
import css.PropertyDetailsList;

public class PropertyPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// reference to parent frame
	private AddProperty parent;
	
	private JComboBox<PropertyDetails> propertyComboBox;

	public PropertyPanel(AddProperty parent) {
		// store reference to parent frame
		this.parent = parent;
		
		// set layout
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;

		add(new JLabel("Select Property : "), bagConstraints);
		setPropertyComboBox(PropertyDetailsList.animation);
		parent.updateDescription(PropertyDetailsList.animation[0].getDescription());
	}

	// change property combo box
	public void setPropertyComboBox(PropertyDetails propertyDetails[]) {
		if (propertyComboBox != null)
			remove(propertyComboBox);

		propertyComboBox = new JComboBox<PropertyDetails>(propertyDetails);
		propertyComboBox.addActionListener(this);
		parent.setPropertyComboBox(propertyComboBox);

		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 0;
		add(propertyComboBox, bagConstraints);

		updateUI();
	}

	// change value chooser according to value type of selected property
	@Override
	public void actionPerformed(ActionEvent e) {
		PropertyDetails propertyDetails = (PropertyDetails) propertyComboBox.getSelectedItem();
		parent.getValuePanel().setValueChooser(propertyDetails.getType());
		parent.updateDescription(propertyDetails.getDescription());
	}
}
