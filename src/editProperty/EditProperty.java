package editProperty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import css.Property;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;
import css.Selector;
import css.ValueType;
import dialog.Dialog;
import main.MainFrame;

public class EditProperty extends Dialog {
	// components in frame
	private JColorChooser chooser;
	private JSlider slider;
	private JComboBox<?> comboBox;
	private JButton updateButton, cancelButton;
	private JTextField valueTextField;
	private JLabel valueLabel;

	// reference to parent objects
	private Property property;
	private Selector selector;
	private MainFrame parent;

	// details of property
	private PropertyDetails propertyDetails;

	// default frame size
	private final Dimension DEFAULT_SIZE = new Dimension(700, 200);

	public EditProperty(MainFrame parent, Selector selector, Property property) {
		// store reference
		this.property = property;
		this.parent = parent;
		this.selector = selector;
	}

	public void showEditProperty() {
		// create dialog
		dialog = new JDialog(parent, "Edit Property", true);

		// set layout to GridBaglayout and create GridBagConstraints
		dialog.setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);		

		// add labels for property and value
		dialog.add(new JLabel("Property: "), bagConstraints);
		bagConstraints.gridy++;
		dialog.add(new JLabel("New Value: "), bagConstraints);

		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		dialog.add(new JLabel(property.getName()), bagConstraints);

		// add value selector
		bagConstraints.gridy++;
		propertyDetails = PropertyDetailsList.getDetails(property);
		switch (propertyDetails.getType()) {
		case COLOR:
			// increase size of frame to fit color chooser
			dialog.setSize(870, 480);
			chooser = new JColorChooser();
			chooser.getSelectionModel();
			dialog.add(chooser, bagConstraints);
			break;
		case DOUBLE:
			addSlider(propertyDetails, bagConstraints, true);
			dialog.setSize(DEFAULT_SIZE);
			break;
		case INTEGER:			
			addSlider(propertyDetails, bagConstraints, false);
			dialog.setSize(DEFAULT_SIZE);
			break;
		case PIXEL:
			addSlider(propertyDetails, bagConstraints, false);
			dialog.setSize(DEFAULT_SIZE);
			break;
		case STRING:
			String possibleValue[] = propertyDetails.getPossibleValues();
			if (possibleValue != null) {
				comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());  
				dialog.add(comboBox, bagConstraints);
				comboBox.setSelectedItem(property.getValue());
			}
			else {
				valueTextField = new JTextField(20);
				dialog.add(valueTextField, bagConstraints);
			}
			dialog.setSize(DEFAULT_SIZE);
			break;
		case TIME:
			addSlider(propertyDetails, bagConstraints, false);
			dialog.setSize(DEFAULT_SIZE);
			break;
		default:
			break;
		}

		// create and add updateButton
		updateButton = new JButton("Update Value");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				property.setValue(getValue());
				parent.editProperty(selector);
				dialog.dispose();
			}
		});
		bagConstraints.anchor = GridBagConstraints.NORTHEAST;
		bagConstraints.gridy++;
		dialog.add(updateButton, bagConstraints);

		// create and add cancelButton
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			dialog.dispose();
		});
		bagConstraints.gridx++;
		dialog.add(cancelButton, bagConstraints);

		// set dialog properties
		dialog.setLocationRelativeTo(parent);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}

	// set slider according to property type
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
		slider = new JSlider(min, max, getOldValue(property));
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
		dialog.add(slider, bagConstraints);

		// add label to display value
		valueLabel = new JLabel();
		bagConstraints.gridx++;
		dialog.add(valueLabel, bagConstraints);

		updateValueLabel(propertyDetails);
		
		bagConstraints.gridx--;
	}

	// return selected value
	public String getValue() {
		String value = null;
		// convert the value into string
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
			value = slider.getValue() + ValueType.PIXEL.getUnit();
			break;
		case STRING:
			if (propertyDetails.getPossibleValues() != null)
				value = (String) comboBox.getSelectedItem();
			else
				value = valueTextField.getText();
			break;
		case TIME:
			value = slider.getValue() + ValueType.TIME.getUnit();
			break;
		default:
			break;

		}
		return value;
	}

	// return previous value of property
	public int getOldValue(Property property) {
		String value = property.getValue();
		int num;
		Range<?> range = propertyDetails.getRange();

		switch(propertyDetails.getType()) {
		case COLOR:
			break;
		case DOUBLE:
			// get previous value
			double doubleValue = Float.parseFloat(property.getValue());

			// get range of property
			double doubleMin = (double) range.getMin(),
					doubleMax = (double) range.getMax();

			// if value is small than minValue return minValue
			if (doubleValue < doubleMin)
				return (int) (doubleMin * 100);

			// if value is large than maxValue return maxValue
			if (doubleValue > doubleMax)
				return (int) (doubleMax * 100);

			// return value if it is range
			return (int) (doubleValue * 100);
		case INTEGER:
			num = Integer.parseInt(value);
			return getValueFromRange(num, range);
		case PIXEL:
			num = Integer.parseInt(value.substring(0, value.indexOf(ValueType.PIXEL.getUnit())));
			return getValueFromRange(num, range);
		case STRING:
			break;
		case TIME:
			num = Integer.parseInt(value.substring(0, value.indexOf('s')));
			return getValueFromRange(num, range);
		default:
			break;
		}
		return 0;
	}

	public int getValueFromRange(int num, Range<?> range) {
		int min, max;

		// get minimum and max values
		min = (int) range.getMin();
		max = (int) range.getMax();

		// if value is small than minValue return minValue
		if (num < min)
			return min;

		// if value is large than maxValue return maxValue
		if (num > max)
			return max;

		// return value if it is range
		return num;
	}
	
	public void updateValueLabel(PropertyDetails propertyDetails) {
		String value;
		String unit = propertyDetails.getType().getUnit();
		if (propertyDetails.getType() == ValueType.DOUBLE)
			value = String.format("%2.2f", slider.getValue() / 100.0);
		else
			value = String.valueOf(slider.getValue());
		valueLabel.setText("[ " + value + unit + " ]");
	}
}
