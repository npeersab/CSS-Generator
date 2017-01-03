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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JLabel valueLabel;
	private String unit;

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
			parent.getDialog().setSize(800, 650);
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
			parent.getDialog().setSize(parent.FRAME_SIZE);
			break;
		case TIME:
			addSlider(propertyDetails, bagConstraints, false);
			break;
		default:
			break;
		}
		// set parent location to center
		parent.getDialog().setLocationRelativeTo(null);
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
		unit = propertyDetails.getType().getUnit();
		String labels[] = new String[5];
		
		// calculate different points to be displayed on slider
		int nums[] = new int[5];
		nums[0] = min;
		nums[1] = min < 0 ? min / 2 : max / 4;
		nums[2] = min < 0 ? 0 : max / 2;
		nums[3] = min < 0 ? max / 2 : (max * 3) / 4;
		nums[4] = max;
		
		// create labels
		if (d) {
			for (int i = 0; i < 5; i++)
				labels[i] = ((double) nums[i] / 100) + unit;
		}
		else {	
			for (int i = 0; i < 5; i++)
				labels[i] = nums[i] + unit;
		}
		
		// map values with labels
		for (int i = 0; i < 5; i++) 
			hashtable.put(new Integer(nums[i]), new JLabel(labels[i]));
		
		// set labels to slider
		slider.setLabelTable(hashtable);
		slider.setPaintLabels(true);

		// add listener to slider
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateValueLabel(propertyDetails);
			}
		});
		
		// add slider to panel
		add(slider, bagConstraints);
		
		// add label to display value
		valueLabel = new JLabel();
		bagConstraints.gridx++;
		add(valueLabel, bagConstraints);
		
		updateValueLabel(propertyDetails);

		// update parent frame size
		parent.getDialog().setSize(parent.FRAME_SIZE);
	}
	
	public void updateValueLabel(PropertyDetails propertyDetails) {
		String value;
		if (propertyDetails.getType() == ValueType.DOUBLE)
			value = String.format("%2.2f", slider.getValue() / 100.0);
		else
			value = String.valueOf(slider.getValue());
		valueLabel.setText("[ " + value + unit + " ]");
	}
}
