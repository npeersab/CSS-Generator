package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private AddProperty parent;
	private JSlider slider;
	private JColorChooser chooser;
	private JComboBox<String> comboBox;
	
	public SelectValuePanel(AddProperty parent) {
		this.parent = parent;
		
		setLayout(new GridBagLayout());
		
		// set value chooser for first property in the list
		setValueChooser(PropertyDetailsList.animation[0].getType());
	}
	
	public void setValueChooser(ValueType valueType) {
		removeAll();
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		add(new JLabel("Select Value : "), bagConstraints);
		
		PropertyDetails propertyDetails = 
				(PropertyDetails) parent.getPropertyComboBox().getSelectedItem(); 
		
		bagConstraints.gridx++;
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
			setSlider(propertyDetails);
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			parent.setSize(parent.FRAME_SIZE);
			break;
		case PIXEL:
			setSlider(propertyDetails);
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			parent.setSize(parent.FRAME_SIZE);
			break;
		case STRING:
			bagConstraints.gridx++;
			comboBox = new JComboBox<String>(propertyDetails.getPossibleValues());  
			add(comboBox, bagConstraints);
			parent.setSize(parent.FRAME_SIZE);
			break;
		case TIME:
			setSlider(propertyDetails);
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			parent.setSize(parent.FRAME_SIZE);
			break;
		default:
			break;
		}
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

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
