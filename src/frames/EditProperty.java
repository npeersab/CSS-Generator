package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import css.Property;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;
import css.Selector;

public class EditProperty extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	// components in frame
	private JColorChooser chooser;
	private JSlider slider;
	private JComboBox<?> comboBox;
	private JButton updateButton, cancelButton;
	
	// reference to parent objects
	private Property property;
	private Selector selector;
	private MainFrame parent;
	
	// details of property
	private PropertyDetails propertyDetails;
	
	// default frame size
	private final Dimension DEFAULT_SIZE = new Dimension(600, 200);
	private JTextField valueTextField; 

	public EditProperty(MainFrame parent, Selector selector, Property property) {
		// store reference
		this.property = property;
		this.parent = parent;
		this.selector = selector;
		
		// disable parent
		enableParent(false);
		
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
			addSlider(propertyDetails, bagConstraints, true);
			setSize(DEFAULT_SIZE);
			break;
		case INTEGER:			
			addSlider(propertyDetails, bagConstraints, false);
			setSize(DEFAULT_SIZE);
			break;
		case PIXEL:
			addSlider(propertyDetails, bagConstraints, false);
			setSize(DEFAULT_SIZE);
			break;
		case STRING:
			String possibleValue[] = propertyDetails.getPossibleValues();
			if (possibleValue != null) {
				comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());  
				add(comboBox, bagConstraints);
			}
			else {
				valueTextField = new JTextField(20);
				add(valueTextField, bagConstraints);
			}
			setSize(DEFAULT_SIZE);
			break;
		case TIME:
			addSlider(propertyDetails, bagConstraints, false);
			setSize(DEFAULT_SIZE);
			break;
		default:
			break;
		}
		
		// create and add updateButton
		updateButton = new JButton("Update Value");
		updateButton.addActionListener(this);
		bagConstraints.anchor = GridBagConstraints.NORTHEAST;
		bagConstraints.gridy++;
		add(updateButton, bagConstraints);
		
		// create and add cancelButton
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			enableParent(true);
			dispose();
		});
		bagConstraints.gridx++;
		add(cancelButton, bagConstraints);
		
		setTitle("Edit Property");
		setIconImage(parent.getIconImage());
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				enableParent(true);
				dispose();
			}
		});
		setVisible(true);
	}

	// set slider according to property type
	public void addSlider(PropertyDetails propertyDetails, GridBagConstraints bagConstraints, Boolean d) {
		// get range of property value
		Range<?> range =  propertyDetails.getRange();
		Integer min = (Integer) range.getMin(),
				max = (Integer) range.getMax();

		// create slider according to range
		Hashtable<Integer, JLabel> hashtable = new Hashtable<Integer, JLabel>();
		String unit = propertyDetails.getType().getUnit();
		String label1 = null, label2 = null, label3 = null, label4 = null, label5 = null;
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
		
		hashtable.put(new Integer(num1), new JLabel(label1));
		hashtable.put(new Integer(num2), new JLabel(label2));
		hashtable.put(new Integer(num3), new JLabel(label3));
		hashtable.put(new Integer(num4), new JLabel(label4));
		hashtable.put(new Integer(num5), new JLabel(label5));
		slider.setLabelTable(hashtable);
		slider.setPaintLabels(true);

		// add slider to panel
		add(slider, bagConstraints);
	}

	// after pressing update button
	@Override
	public void actionPerformed(ActionEvent e) {
		property.setValue(getValue());
		enableParent(true);
		parent.editProperty(selector);
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
	
	// disable/enable parent window
	private void enableParent(boolean b) {
		parent.enableWindow(b);
		setAlwaysOnTop(!b);
	}
}
