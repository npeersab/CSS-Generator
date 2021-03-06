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
import css.PropertyGroup;

// Panel which will display Combo Box to select Property
public class PropertyGroupPanel extends JPanel implements ActionListener {	
	private static final long serialVersionUID = 1L;
	
	// reference to parent frame
	private AddProperty parent;

	public PropertyGroupPanel(AddProperty parent) {
		// store reference
		this.parent = parent;
		
		// set layout
		setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
								
		add(new JLabel("Select Property Group: "), bagConstraints);
				
		// Array which will contain all property types
		PropertyGroup[] propertyGroups = PropertyGroup.values();
		
		parent.setPropertyGroupComboBox(new JComboBox<PropertyGroup>(propertyGroups));
		parent.getPropertyGroupComboBox().addActionListener(this);
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(parent.getPropertyGroupComboBox(), bagConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PropertyDetails propertyDetails[] = null;
		
		// change property list according to property group
		switch ((PropertyGroup) parent.getPropertyGroupComboBox().getSelectedItem()) {
		case animation:
			propertyDetails = PropertyDetailsList.animation;
			break;
		case background_and_borders:
			propertyDetails = PropertyDetailsList.backgroundBorder;
			break;
		case basic_box:
			propertyDetails = PropertyDetailsList.basicBox;
			break;
		case basic_user_interface:
			propertyDetails = PropertyDetailsList.basicUserInterface;
			break;
		case color:
			propertyDetails = PropertyDetailsList.color;
			break;
		case flexible_box:
			propertyDetails = PropertyDetailsList.flexibleBoxLayout;
			break;
		case fonts:
			propertyDetails = PropertyDetailsList.font;
			break;
		case multi_column:
			propertyDetails = PropertyDetailsList.multiColumnLayout;
			break;
		case table:
			propertyDetails = PropertyDetailsList.table;
			break;
		case text:
			propertyDetails = PropertyDetailsList.text;
			break;
		case text_decoration:
			propertyDetails = PropertyDetailsList.textDecoration;
			break;
		case writing_modes:
			propertyDetails = PropertyDetailsList.writingModes;
			break;
		default:
			break;
		}
		parent.getPropertyPanel().setPropertyComboBox(propertyDetails);
		parent.getValuePanel().setValueChooser(propertyDetails[0].getType());
		parent.updateDescription(propertyDetails[0].getDescription());
	}		
}
