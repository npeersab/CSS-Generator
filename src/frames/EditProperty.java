package frames;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import css.Property;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;

public class EditProperty extends JFrame {
	private static final long serialVersionUID = 1L;
	private JColorChooser chooser;
	private JSlider slider;
	private JComboBox<?> comboBox;

	public EditProperty(MainFrame parent, Property property) {
		// set layout to GridBaglayout and create GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);		

		PropertyDetails propertyDetails = PropertyDetailsList.getDetails(property);
		switch (propertyDetails.getType()) {
		case COLOR:
			// increase size of frame to fit color chooser
			setSize(800, 650);
			chooser = new JColorChooser();
			chooser.getSelectionModel();
			add(chooser, bagConstraints);
			break;
		case DOUBLE:
			break;
		case INTEGER:			
			setSlider(propertyDetails);
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			setSize(300, 300);
			break;
		case PIXEL:
			setSlider(propertyDetails);
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			setSize(300, 300);
			break;
		case STRING:
			bagConstraints.gridx++;
			comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());
			comboBox.setSelectedItem(property.getValue());
			add(comboBox, bagConstraints);
			setSize(300, 300);
			break;
		case TIME:
			setSlider(propertyDetails);
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			setSize(300, 300);
			break;
		default:
			break;
		}
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
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
}
