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
			break;
		case INTEGER:			
			addSlider(propertyDetails, bagConstraints);
			setSize(DEFAULT_SIZE);
			break;
		case PIXEL:
			addSlider(propertyDetails, bagConstraints);
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
			addSlider(propertyDetails, bagConstraints);
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
	public void addSlider(PropertyDetails propertyDetails, GridBagConstraints bagConstraints) {
		// get range of property value
		Range<?> range =  propertyDetails.getRange();
		Integer min = (Integer) range.getMin(),
				max = (Integer) range.getMax();

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
		hashtable.put(new Integer(min), new JLabel(min + unit));
		int n = min < 0 ? min / 2 : max / 4;
		hashtable.put(new Integer(n), new JLabel(n + unit));
		n = min < 0 ? 0 : max / 2;
		hashtable.put(new Integer(n), new JLabel((n) + unit));
		n = min < 0 ? max / 2 : (max * 3) / 4;
		hashtable.put(new Integer(n), new JLabel(n + unit));
		hashtable.put(new Integer(max), new JLabel(max + unit));
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
	
	// disable/enable parent window
	private void enableParent(boolean b) {
		parent.enableWindow(b);
		setAlwaysOnTop(!b);
	}
}
