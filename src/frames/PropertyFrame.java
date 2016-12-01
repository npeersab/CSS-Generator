package frames;

import css.PropertyDetails;
import css.PropertyType;
import css.Selector;
import panels.PropertyButtonPanel;
import panels.SelectPropertyPanel;
import panels.SelectPropertyTypePanel;
import panels.SelectValuePanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class PropertyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<PropertyType> propertyTypeComboBox;
	private JComboBox<PropertyDetails> propertyComboBox;
	private SelectPropertyPanel selectPropertyPanel;
	private SelectValuePanel selectValuePanel;
	private SelectPropertyTypePanel selectPropertyTypePanel;
	private PropertyButtonPanel buttonPanel;
	private JButton addButton, cancelButton;
		
	// Constructor
	public PropertyFrame(Selector selector) {
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);
					
		// add propertyTypePanel
		selectPropertyTypePanel = new SelectPropertyTypePanel(this);
		add(selectPropertyTypePanel, bagConstraints);
		
		selectPropertyPanel = new SelectPropertyPanel(this);
		bagConstraints.gridy++;
		add(selectPropertyPanel, bagConstraints);
		
		selectValuePanel = new SelectValuePanel(this);
		bagConstraints.gridy++;
		add(selectValuePanel, bagConstraints);
		
		buttonPanel = new PropertyButtonPanel(this);
		bagConstraints.anchor = GridBagConstraints.CENTER;
		bagConstraints.gridy++;
		add(buttonPanel, bagConstraints);
		
					
		setTitle("Select Property Type");
		setIconImage(ImgSrc.getImageIcon());
		setSize(500, 300);
		setLocationRelativeTo(null);
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
}