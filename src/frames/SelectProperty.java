package frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import css.PropertyDetails;

public class SelectProperty extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JComboBox<PropertyDetails> comboBox;
	JSlider slider;
	
	public SelectProperty(PropertyDetails[] propertyDetails) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		add(new JLabel("Select Property"), bagConstraints);	
		comboBox = new JComboBox<PropertyDetails>(propertyDetails);
		comboBox.addActionListener(this);
		bagConstraints.gridy++;
		add(comboBox, bagConstraints);
						
		setTitle("Add new Property");
		setIconImage(ImgSrc.getImageIcon());
		setSize(700, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setValuePanel(PropertyDetails propertyDetails) {
		switch (propertyDetails.getType()) {
		case COLOR:
			JColorChooser chooser = new JColorChooser();
			add(chooser);
			System.out.println("color");
			break;
		case DOUBLE:
			slider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
			add(slider);
			break;
		case INTEGER:
			slider = new JSlider(JSlider.HORIZONTAL, -10, 100, 50);
			slider.setMinorTickSpacing(10);
			slider.setMajorTickSpacing(20);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			add(slider);
			break;
		case PIXEL:
			break;
		case STRING:
			JComboBox<String> box = new JComboBox<>(propertyDetails.getPossibleValues());
			add(box);
			break;
		default:
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setValuePanel((PropertyDetails) comboBox.getSelectedItem());		
	}
}