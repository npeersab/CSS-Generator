package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import css.PropertyDetails;
import css.PropertyDetailsList;
import css.Range;
import css.ValueType;
import frames.PropertyFrame;

public class SelectValuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private PropertyFrame parent;
	
	public SelectValuePanel(PropertyFrame parent) {
		this.parent = parent;
		
		setLayout(new GridBagLayout());
		setValueChooser(PropertyDetailsList.animation[0].getType());
	}
	
	public void setValueChooser(ValueType valueType) {
		
		removeAll();
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		//bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		add(new JLabel("Select Value : "));
		
		PropertyDetails propertyDetails = (PropertyDetails) parent.getPropertyComboBox().getSelectedItem(); 
		Range<?> range = null;
		JSlider slider = null;
		
		switch (valueType) {
		case COLOR:
			JColorChooser chooser = new JColorChooser();
			parent.setSize(800, 600);
			parent.setLocationRelativeTo(null);
			add(chooser, bagConstraints);
			break;
		case DOUBLE:
			break;
		case INTEGER:
			range =  propertyDetails.getRange();
			slider = new JSlider((Integer) range.getMin(),(Integer) range.getMax(),(Integer) range.getMin());
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(50);
			slider.setMinorTickSpacing(25);
			slider.setPaintLabels(true);
			
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			parent.setSize(500, 300);
			break;
		case PIXEL:
			range =  propertyDetails.getRange();
			slider = new JSlider((Integer) range.getMin(),(Integer) range.getMax(),(Integer) range.getMin());
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(50);
			slider.setMinorTickSpacing(25);
			slider.setPaintLabels(true);
			
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			parent.setSize(500, 300);
			break;
		case STRING:
			bagConstraints.gridx++;
			add(new JComboBox<String>(propertyDetails.getPossibleValues()), bagConstraints);
			parent.setSize(500, 300);
			break;
		case TIME:
			range =  propertyDetails.getRange();
			slider = new JSlider((Integer) range.getMin(),(Integer) range.getMax(),(Integer) range.getMin());
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(50);
			slider.setMinorTickSpacing(25);
			slider.setPaintLabels(true);
			
			bagConstraints.gridx++;
			add(slider, bagConstraints);
			parent.setSize(500, 300);
			break;
		default:
			break;
		}
	//	parent.setLocationRelativeTo(null);
		updateUI();
	}
}
