package addProperty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Hashtable;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;
import css.ValueType;

public class ValuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// reference to parent
	private AddProperty parent;

	// components
	private JSlider slider;
	private JColorChooser chooser;
	private JComboBox<String> comboBox;
	private JTextField valueTextField;

	// constructor
	public ValuePanel(AddProperty parent) {
		// store reference to parent
		this.parent = parent;

		// set layout
		setLayout(new GridBagLayout());

		// set value chooser for first property in the list
		setValueChooser(PropertyDetailsList.animation[0].getType());
	}

	public void setValueChooser(ValueType valueType) {
		// remove all existing components
		removeAll();

		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;

		add(new JLabel("Value: "), bagConstraints);
		PropertyDetails propertyDetails = 
				(PropertyDetails) parent.getPropertyComboBox().getSelectedItem(); 
		bagConstraints.gridx++;

		// add value selector according to value to type
		switch (valueType) {
		case COLOR:
			// increase size of frame to fit color chooser
			parent.setSize(800, 650);
			chooser = new JColorChooser();
			add(chooser, bagConstraints);
			break;
		case DOUBLE:
			addSlider(propertyDetails, bagConstraints, true);
			break;
		case INTEGER:			
			addSlider(propertyDetails, bagConstraints, false);
			break;
		case PIXEL:
			addSlider(propertyDetails, bagConstraints, false);
			break;
		case STRING:
			String possibleValue[] = propertyDetails.getPossibleValues();
			// add combo box is property has possible values 
			if (possibleValue != null) {
				comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());  
				add(comboBox, bagConstraints);
			}
			else {
				valueTextField = new JTextField(20);
				add(valueTextField, bagConstraints);
			}
			parent.setSize(parent.FRAME_SIZE);
			break;
		case TIME:
			addSlider(propertyDetails, bagConstraints, false);
			break;
		default:
			break;
		}
		// set parent location to center
		parent.setLocationRelativeTo(null);
		updateUI();
	}

	// return selected value
	public String getValue() {
		String value = null;
		PropertyDetails propertyDetails = 
				(PropertyDetails) parent.getPropertyComboBox().getSelectedItem();
		switch(propertyDetails.getType()) {
		case COLOR:
			Color color = chooser.getColor();
			value = String.format("#%02x%02x%02x", color.getRed(), color.getBlue(), color.getGreen());
			break;
		case DOUBLE:
			value = Double.toString(((double) slider.getValue()) / 100);
			break;
		case INTEGER:
			value = Integer.toString(slider.getValue());
			break;
		case PIXEL:
			value = slider.getValue() + "px";
			break;
		case STRING:
			if (propertyDetails.getPossibleValues() != null)
				value = (String) comboBox.getSelectedItem();
			else
				value = valueTextField.getText();
			break;
		case TIME:
			value = slider.getValue() + "s";
			break;
		default:
			break;

		}
		return value;
	}

	public void addSlider(PropertyDetails propertyDetails, GridBagConstraints bagConstraints, Boolean d) {
		// get range of property value
		Range<?> range =  propertyDetails.getRange();
		int min = 0, max = 0;
		if (d) {
			min = (int) (((Double) range.getMin()) * 100);
			max = (int) (((Double) range.getMax()) * 100);
		}
		else {
			min = (Integer) range.getMin();
			max = (Integer) range.getMax();
		}

		// create slider according to range
		slider = new JSlider(min, max, ((min + max) / 2));
		Dimension dimension = slider.getPreferredSize();

		// increase size of slider
		dimension.width += 150;
		dimension.height += 27;
		slider.setPreferredSize(dimension);

		// set slider paint ticks
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(max/4);
		slider.setMinorTickSpacing(max/20);

		// add custom label to slider
		Hashtable<Integer, JLabel> hashtable = new Hashtable<Integer, JLabel>();
		String unit = propertyDetails.getType().getUnit();
		String label1 = null, label2 = null, label3 = null, label4 = null, label5 = null;
		
		// calculate different points to be displayed on slider
		int num1 = min,
			num2 = min < 0 ? min / 2 : max / 4,
			num3 = min < 0 ? 0 : max / 2,
			num4 = min < 0 ? max / 2 : (max * 3) / 4,
			num5 = max;
		
		// create labels
		if (d) {
			label1 = ((double) num1 / 100) + unit;
			label2 = ((double) num2 / 100) + unit;
			label3 = ((double) num3 / 100) + unit;
			label4 = ((double) num4 / 100) + unit;
			label5 = ((double) num5 / 100) + unit;
		}
		else {	
			label1 = num1 + unit;
			label2 = num2 + unit;
			label3 = num3 + unit;
			label4 = num4 + unit;
			label5 = max + unit;
		}
		
		// map values with labels
		hashtable.put(new Integer(num1), new JLabel(label1));
		hashtable.put(new Integer(num2), new JLabel(label2));
		hashtable.put(new Integer(num3), new JLabel(label3));
		hashtable.put(new Integer(num4), new JLabel(label4));
		hashtable.put(new Integer(num5), new JLabel(label5));
		
		// set labels to slider
		slider.setLabelTable(hashtable);
		slider.setPaintLabels(true);

		// add slider to panel
		add(slider, bagConstraints);

		// update parent frame size
		parent.setSize(parent.FRAME_SIZE);
	}
}
