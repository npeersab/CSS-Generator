package panels;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;
import css.ValueType;
import frames.AddProperty;

public class SelectValuePanel extends JPanel implements ChangeListener {
	private static final long serialVersionUID = 1L;
	// reference to parent
	private AddProperty parent;
	
	// components
	private JSlider slider;
	private JColorChooser chooser;
	private JComboBox<String> comboBox;
	
	public SelectValuePanel(AddProperty parent) {
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
		
		add(new JLabel("Select Value: "), bagConstraints);
		PropertyDetails propertyDetails = 
				(PropertyDetails) parent.getPropertyComboBox().getSelectedItem(); 
		bagConstraints.gridx++;
		
		// add value selector according to value to type
		switch (valueType) {
		case COLOR:
			// increase size of frame to fit color chooser
			parent.setSize(800, 650);
			chooser = new JColorChooser();
			chooser.getSelectionModel().addChangeListener(this);
			add(chooser, bagConstraints);
			break;
		case DOUBLE:
			break;
		case INTEGER:			
			addSlider(propertyDetails, bagConstraints);
			break;
		case PIXEL:
			addSlider(propertyDetails, bagConstraints);
			break;
		case STRING:
			comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());  
			add(comboBox, bagConstraints);
			parent.setSize(parent.FRAME_SIZE);
			break;
		case TIME:
			addSlider(propertyDetails, bagConstraints);
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
		
		// update parent frame size
		parent.setSize(parent.FRAME_SIZE);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
