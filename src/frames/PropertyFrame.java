package frames;

import css.PropertyDetailsList;
import css.PropertyType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PropertyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton cancelButton, selectButton;
	
	public PropertyFrame() {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 5, 10, 5);
		
		add(new JLabel("Select Type of the Property : "), bagConstraints);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> dispose());
		bagConstraints.gridy++;
		add(cancelButton, bagConstraints);
		
		PropertyType propertytypes[] = {PropertyType.animation, PropertyType.background_and_borders,
				PropertyType.basic_box, PropertyType.basic_user_interface, PropertyType.color,
				PropertyType.filter_effects, PropertyType.flexible_box, PropertyType.fonts,
				PropertyType.generated_content, PropertyType.image_replaced_content,
				PropertyType.lists_and_counters, PropertyType.marquee, PropertyType.masking,
				PropertyType.multi_column, PropertyType.paged_media, PropertyType.speech,
				PropertyType.table, PropertyType.text, PropertyType.text_decoration,
				PropertyType.transform, PropertyType.transition, PropertyType.writing_modes
				};
		
		JComboBox<PropertyType> comboBox = new JComboBox<PropertyType>(propertytypes);
		bagConstraints.gridx++;
		bagConstraints.gridy = 0;
		add(comboBox, bagConstraints);
		
		selectButton = new JButton("Select");
		selectButton.addActionListener(e -> {
			switch ((PropertyType) comboBox.getSelectedItem()) {
			case animation:
				break;
			case background_and_borders:
				new SelectProperty(PropertyDetailsList.backgroundBorder);
				break;
			case basic_box:
				new SelectProperty(PropertyDetailsList.basicBox);
				break;
			case basic_user_interface:
				break;
			case color:
				new SelectProperty(PropertyDetailsList.color);
				break;
			case filter_effects:
				break;
			case flexible_box:
				new SelectProperty(PropertyDetailsList.flexibleBoxLayout);
				break;
			case fonts:
				break;
			case generated_content:
				break;
			case image_replaced_content:
				break;
			case lists_and_counters:
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
				break;
			case text:
				break;
			case text_decoration:
				break;
			case transform:
				break;
			case transition:
				break;
			case writing_modes:
				break;
			default:
				break;
				
			}
		});
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
}
