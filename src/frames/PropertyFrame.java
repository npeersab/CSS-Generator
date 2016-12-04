package frames;

import css.Property;
import css.PropertyDetails;
import css.PropertyType;
import css.Selector;
import panels.PropertyButtonPanel;
import panels.PropertyDescriptionPanel;
import panels.SelectPropertyPanel;
import panels.SelectPropertyTypePanel;
import panels.SelectValuePanel;
import res.ImgSrc;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PropertyFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// panels
	private SelectPropertyPanel selectPropertyPanel;
	private SelectValuePanel selectValuePanel;
	private SelectPropertyTypePanel selectPropertyTypePanel;
	private PropertyButtonPanel buttonPanel;
	private PropertyDescriptionPanel descriptionPanel;
	
	// components
	private JComboBox<PropertyType> propertyTypeComboBox;
	private JComboBox<PropertyDetails> propertyComboBox;
	private JButton addButton, cancelButton;
	private JTextArea description;
	
	// to keep reference of selector in which property is going to be added
	private Selector selector;
	
	// default frame size;
	public final Dimension FRAME_SIZE = new Dimension(600, 500);
	
	// reference to parent
	private MainFrame parent;

	// Constructor
	public PropertyFrame(MainFrame parent, Selector selector) {
		// store the selector in which property is to be added
		this.selector = selector;
		
		this.parent = parent;
		
		// set layout to GridBaglayout and create GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);
					
		// add propertyTypePanel
		selectPropertyTypePanel = new SelectPropertyTypePanel(this);
		add(selectPropertyTypePanel, bagConstraints);
		
		descriptionPanel = new PropertyDescriptionPanel(this);
		
		// add propertyPanel
		selectPropertyPanel = new SelectPropertyPanel(this);
		bagConstraints.gridy++;
		add(selectPropertyPanel, bagConstraints);
		
		// add description panel
		bagConstraints.gridy++;
		add(descriptionPanel, bagConstraints);
		
		// add valuePanel
		selectValuePanel = new SelectValuePanel(this);
		bagConstraints.gridy++;
		add(selectValuePanel, bagConstraints);
		
		// add buttonPanel
		buttonPanel = new PropertyButtonPanel(this);
		bagConstraints.anchor = GridBagConstraints.CENTER;
		bagConstraints.gridy++;
		add(buttonPanel, bagConstraints);
		
					
		setTitle("Select Property Type");
		setIconImage(ImgSrc.getImageIcon());
		setSize(FRAME_SIZE);
		setLocationRelativeTo(parent);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	// getter and setter for propertyTypeComboBox
	public JComboBox<PropertyType> getPropertyTypeComboBox() {
		return propertyTypeComboBox;
	}

	public void setPropertyTypeComboBox(JComboBox<PropertyType> propertyTypeComboBox) {
		this.propertyTypeComboBox = propertyTypeComboBox;
	}

	// getter and setter for propertyComboBox
	public JComboBox<PropertyDetails> getPropertyComboBox() {
		return propertyComboBox;
	}

	public void setPropertyComboBox(JComboBox<PropertyDetails> propertyComboBox) {
		this.propertyComboBox = propertyComboBox;
	}

	// getter and setter for selectValuePanel
	public SelectValuePanel getSelectValuePanel() {
		return selectValuePanel;
	}

	public void setSelectValuePanel(SelectValuePanel selectValuePanel) {
		this.selectValuePanel = selectValuePanel;
	}

	// getter and setter for selectPropertyPanel
	public SelectPropertyPanel getSelectPropertyPanel() {
		return selectPropertyPanel;
	}
	
	public void setSelectPropertyPanel(SelectPropertyPanel selectPropertyPanel) {
		this.selectPropertyPanel = selectPropertyPanel;
	}

	// getter and setter for addButton
	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}
	
	//getter and setter for cancelButton
	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String value = selectValuePanel.getValue();
		Property property = new Property(
				((PropertyDetails) propertyComboBox.getSelectedItem()).toString(), value); 
		selector.addProperty(property);	
		parent.addProperty(property);
		dispose();
	}

	// getter and setter for property description
	public JTextArea getDescription() {
		return description;
	}

	public void setDescription(JTextArea description) {
		this.description = description;
	}
}