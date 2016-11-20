package frames;

import java.awt.GridBagLayout;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;

import css.PropertyDetails;

public class SelectProperty extends JFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<PropertyDetails> comboBox;
	
	public SelectProperty(PropertyDetails[] propertyDetails) {
		setLayout(new GridBagLayout());
		comboBox = new JComboBox<PropertyDetails>(propertyDetails);
		add(comboBox);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		add(slider);
		
		switch (propertyDetails[0].getType()) {
		case COLOR:
			JColorChooser chooser = new JColorChooser();
			add(chooser);
			break;
		case DOUBLE:
	///		JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
	//		add(slider);
			break;
		case INTEGER:
			break;
		case PIXEL:
			break;
		case STRING:
			JComboBox<String> box = new JComboBox<>(propertyDetails[0].getPossibleValues());
			add(box);
			break;
		default:
			break;
		}
		
		setTitle("Add new Property");
		setIconImage(ImgSrc.getImageIcon());
		setSize(700, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}