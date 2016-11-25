package frames;

import css.PropertyDetails;
import css.PropertyDetailsList;
import css.PropertyType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PropertyFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton cancelButton, selectButton;
	private JComboBox<PropertyType> comboBox;
	
	public PropertyFrame() {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 5);
		
		add(new JLabel("Select Type of the Property : "), bagConstraints);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(
				e -> dispose());
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);
		
		PropertyType propertytypes[] = {PropertyType.animation, PropertyType.background_and_borders,
				PropertyType.basic_box, PropertyType.basic_user_interface, PropertyType.color,
				PropertyType.filter_effects, PropertyType.flexible_box, PropertyType.fonts,
				PropertyType.generated_content, PropertyType.image_replaced_content,
				PropertyType.marquee, PropertyType.masking, PropertyType.multi_column,
				PropertyType.paged_media, PropertyType.speech, PropertyType.table,
				PropertyType.text, PropertyType.text_decoration, PropertyType.transform,
				PropertyType.transition, PropertyType.writing_modes
				};
		
		comboBox = new JComboBox<PropertyType>(propertytypes);
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(comboBox, bagConstraints);
		
		selectButton = new JButton("Select");
		selectButton.addActionListener(this);
		bagConstraints.gridy++;
		add(selectButton, bagConstraints);
						
		setTitle("Select Property Type");
		setIconImage(ImgSrc.getImageIcon());
		setSize(500, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PropertyDetails propertyDetails[] = null;
		
		switch ((PropertyType) comboBox.getSelectedItem()) {
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
		case transform:
			break;
		case transition:
			break;
		case writing_modes:
			propertyDetails = PropertyDetailsList.writingModes;
			break;
		default:
			break;
			
		}
		new SelectProperty(propertyDetails);
	}		
}
