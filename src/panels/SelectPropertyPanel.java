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
import frames.PropertyFrame;

public class SelectPropertyPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PropertyFrame parent;
	private JComboBox<PropertyDetails> propertyComboBox;
		
	public SelectPropertyPanel(PropertyFrame parent) {
		this.parent = parent;
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		
		add(new JLabel("Select Property : "), bagConstraints);
		setPropertyComboBox(PropertyDetailsList.animation);
		setDescription(PropertyDetailsList.animation[0].getDescription());
	}
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		PropertyDetails propertyDetails = (PropertyDetails) propertyComboBox.getSelectedItem();
		parent.getSelectValuePanel().setValueChooser(propertyDetails.getType());
		setDescription(propertyDetails.getDescription());
	}
	
	public void setDescription(String description) {
		parent.getDescription().setText(description);
	}
}
