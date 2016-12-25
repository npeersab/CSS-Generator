package panels;

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
import frames.AddProperty;


// Panel which will display Combo Box to select Property
public class SelectPropertyGroupPanel extends JPanel implements ActionListener {	
	private static final long serialVersionUID = 1L;
	private AddProperty parent;

	public SelectPropertyGroupPanel(AddProperty parent) {
		this.parent = parent;
		
		setLayout(new GridBagLayout());
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
		parent.getSelectPropertyPanel().setPropertyComboBox(propertyDetails);
		parent.getSelectValuePanel().setValueChooser(propertyDetails[0].getType());
		parent.updateDescription(propertyDetails[0].getDescription());
	}		
}
