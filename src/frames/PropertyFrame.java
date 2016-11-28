package frames;

import css.PropertyDetails;
import css.PropertyDetailsList;
import css.PropertyType;
import css.Range;
import css.ValueType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class PropertyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<PropertyType> propertyTypeComboBox;
	private JComboBox<PropertyDetails> propertyComboBox;
	private SelectProperty selectProperty;
	private SelectValue selectValue;
	
	public PropertyFrame() {
		setLayout(new GridBagLayout());	
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		
		add(new PropertyTypePanel(), bagConstraints);
		
		selectProperty = new SelectProperty(PropertyDetailsList.backgroundBorder);
		bagConstraints.gridy++;
		add(selectProperty, bagConstraints);
		
		selectValue = new SelectValue();
		bagConstraints.gridy++;
		add(selectValue, bagConstraints);
		
		setTitle("Select Property Type");
		setIconImage(ImgSrc.getImageIcon());
		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private class PropertyTypePanel extends JPanel implements ActionListener {	
		private static final long serialVersionUID = 1L;

		public PropertyTypePanel() {
			setLayout(new GridBagLayout());
			GridBagConstraints bagConstraints = new GridBagConstraints();
			bagConstraints.gridx = bagConstraints.gridy = 0;
			bagConstraints.insets = new Insets(10, 5, 10, 5);
							
			add(new JLabel("Select Type of the Property : "), bagConstraints);
						
			PropertyType propertytypes[] = {
					PropertyType.animation, PropertyType.background_and_borders,
					PropertyType.basic_box, PropertyType.basic_user_interface, PropertyType.color,
					PropertyType.filter_effects, PropertyType.flexible_box, PropertyType.fonts,
					PropertyType.generated_content, PropertyType.image_replaced_content,
					PropertyType.marquee, PropertyType.masking, PropertyType.multi_column,
					PropertyType.paged_media, PropertyType.speech, PropertyType.table,
					PropertyType.text, PropertyType.text_decoration, PropertyType.writing_modes
					};
			
			propertyTypeComboBox= new JComboBox<PropertyType>(propertytypes);
			propertyTypeComboBox.addActionListener(this);
			bagConstraints.gridx++;
			bagConstraints.gridy = 0;
			add(propertyTypeComboBox, bagConstraints);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			PropertyDetails propertyDetails[] = null;
			
			switch ((PropertyType) propertyTypeComboBox.getSelectedItem()) {
			case animation:
				break;
			case background_and_borders:
				propertyDetails = PropertyDetailsList.backgroundBorder;
				break;
			case basic_box:
				propertyDetails = PropertyDetailsList.basicBox;
				break;
			case basic_user_interface:
				break;
			case color:
				propertyDetails = PropertyDetailsList.color;
				break;
			case filter_effects:
				break;
			case flexible_box:
				propertyDetails = PropertyDetailsList.flexibleBoxLayout;
				break;
			case fonts:
				propertyDetails = PropertyDetailsList.font;
				break;
			case generated_content:
				break;
			case image_replaced_content:
				break;
			case marquee:
				break;
			case masking:
				break;
			case multi_column:
				break;
			case paged_media:
				break;
			case speech:
				break;
			case table:
				propertyDetails = PropertyDetailsList.table;
				break;
			case text:
				propertyDetails = PropertyDetailsList.text;
				break;
			case text_decoration:
				propertyDetails = PropertyDetailsList.textDecoration;
				break;
			case writing_modes:
				propertyDetails = PropertyDetailsList.writingModes;
				break;
			default:
				break;	
			}
			selectProperty.setBox(propertyDetails);
			selectValue.setValueChooser(propertyDetails[0].getType());
		}		
	}
	
	private class SelectProperty extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		
		public SelectProperty(PropertyDetails propertyDetails[]) {
			setLayout(new GridBagLayout());
			GridBagConstraints bagConstraints = new GridBagConstraints();
			bagConstraints.fill = GridBagConstraints.HORIZONTAL;
			bagConstraints.gridx = bagConstraints.gridy = 0;
			
			add(new JLabel("Select Property : "), bagConstraints);
			setBox(propertyDetails);
		}
		
		public void setBox(PropertyDetails propertyDetails[]) {
			if (propertyComboBox != null)
				remove(propertyComboBox);
			
			propertyComboBox = new JComboBox<PropertyDetails>(propertyDetails);
			propertyComboBox.addActionListener(this);
			GridBagConstraints bagConstraints = new GridBagConstraints();
			bagConstraints.fill = GridBagConstraints.HORIZONTAL;
			bagConstraints.gridx = 1;
			bagConstraints.gridy = 0;
			add(propertyComboBox, bagConstraints);
			updateUI();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			PropertyDetails propertyDetails = (PropertyDetails) propertyComboBox.getSelectedItem();
			selectValue.setValueChooser(propertyDetails.getType());
		}
	}
	
	private class SelectValue extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public SelectValue() {
			setLayout(new GridBagLayout());
		}
		
		public void setValueChooser(ValueType valueType) {
			
			removeAll();
			
			GridBagConstraints bagConstraints = new GridBagConstraints();
			bagConstraints.fill = GridBagConstraints.HORIZONTAL;
			bagConstraints.gridx = bagConstraints.gridy = 0;
			PropertyDetails propertyDetails = (PropertyDetails) propertyComboBox.getSelectedItem(); 
			
			
			switch (valueType) {
			case COLOR:
				add(new JColorChooser());
				break;
			case DOUBLE:
				break;
			case INTEGER:
				break;
			case PIXEL:
				Range<?> range =  propertyDetails.getRange();
				JSlider slider = new JSlider((Integer) range.getMin(),(Integer) range.getMax(),(Integer) range.getMin());
				slider.setPaintTicks(true);
				slider.setMajorTickSpacing(50);
				slider.setMinorTickSpacing(25);
				slider.setPaintLabels(true);
				add(slider);
				break;
			case STRING:
				add(new JComboBox<String>(propertyDetails.getPossibleValues()));
				break;
			default:
				break;
			}
			updateUI();
		}
	}
}