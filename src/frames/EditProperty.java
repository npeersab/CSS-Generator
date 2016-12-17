package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import css.Property;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;

public class EditProperty extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	// components in frame
	private JColorChooser chooser;
	private JSlider slider;
	private JComboBox<?> comboBox;
	private JButton updateButton, cancelButton;
	
	// reference to property
	private Property property;
	
	// reference to parent frame
	private MainFrame parent;
	
	// details of property
	PropertyDetails propertyDetails;

	public EditProperty(MainFrame parent, Property property) {
		// store reference
		this.property = property;
		this.parent = parent;
		
		// set layout to GridBaglayout and create GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);		

		// add labels for property and value
		add(new JLabel("Property: "), bagConstraints);
		bagConstraints.gridy++;
		add(new JLabel("New Value: "), bagConstraints);
		
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(new JLabel(property.getName()), bagConstraints);
		
		// add value selector
		bagConstraints.gridy++;
		propertyDetails = PropertyDetailsList.getDetails(property);
		switch (propertyDetails.getType()) {
		case COLOR:
			// increase size of frame to fit color chooser
			setSize(870, 480);
			chooser = new JColorChooser();
			chooser.getSelectionModel();
			add(chooser, bagConstraints);
			break;
		case DOUBLE:
			break;
		case INTEGER:			
			setSlider(propertyDetails);
			bagConstraints.gridy++;
			add(slider, bagConstraints);
			setSize(600, 200);
			break;
		case PIXEL:
			setSlider(propertyDetails);
			add(slider, bagConstraints);
			setSize(600, 200);
			break;
		case STRING:
			comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());
			comboBox.setSelectedItem(property.getValue());
			add(comboBox, bagConstraints);
			setSize(450, 200);
			break;
		case TIME:
			setSlider(propertyDetails);
			add(slider, bagConstraints);
			setSize(600, 200);
			break;
		default:
			break;
		}
		
		// create and add updatebutton
		updateButton = new JButton("Update Value");
		updateButton.addActionListener(this);
		bagConstraints.anchor = GridBagConstraints.NORTHEAST;
		bagConstraints.gridy++;
		add(updateButton, bagConstraints);
		
		// create and add cancelbutton
		cancelButton = new JButton("Cancel");
		bagConstraints.gridx++;
		add(cancelButton, bagConstraints);
		
		setTitle("Edit Property");
		setIconImage(parent.getIconImage());
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	// set slider according to property type
	public void setSlider(PropertyDetails propertyDetails) {
		Range<?> range =  propertyDetails.getRange();
		Integer min = (Integer) range.getMin(),
				max = (Integer) range.getMax();
		slider = new JSlider(min, max, ((min + max) / 2));
		Dimension dimension = slider.getPreferredSize();
		dimension.width += 120;
		dimension.height += 27;
		slider.setPreferredSize(dimension);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(max/5);
		slider.setMinorTickSpacing(max/10);
		slider.setPaintLabels(true);
	}

	// after pressing update button
	@Override
	public void actionPerformed(ActionEvent e) {
		property.setValue(getValue());
		parent.editProperty();
		dispose();
	}

	// return selected value
	public String getValue() {
		String value = null;
		switch(propertyDetails.getType()) {
		case COLOR:
			Color color = chooser.getColor();
			value = String.format("#%02x%02x%02x", color.getRed(), color.getBlue(), color.getGreen());
			break;
		case DOUBLE:
			break;
		case INTEGER:
			value = Integer.toString(slider.getValue());
			break;
		case PIXEL:
			value = slider.getValue() + "px";
			break;
		case STRING:
			value = (String) comboBox.getSelectedItem();
			break;
		case TIME:
			value = slider.getValue() + "s";
			break;
		default:
			break;

		}
		return value;
	}
}
