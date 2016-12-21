package frames;

import css.Property;
import css.PropertyDetails;
import css.PropertyGroup;
import css.Selector;
import panels.PropertyButtonPanel;
import panels.PropertyDescriptionPanel;
import panels.SelectPropertyGroupPanel;
import panels.SelectPropertyPanel;
import panels.SelectValuePanel;
import res.ImgSrc;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class AddProperty extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// panels
	private SelectPropertyPanel selectPropertyPanel;
	private SelectValuePanel selectValuePanel;
	private SelectPropertyGroupPanel selectPropertyGroupPanel;
	private PropertyButtonPanel buttonPanel;
	private PropertyDescriptionPanel descriptionPanel;
	
	// components
	private JComboBox<PropertyGroup> propertyGroupComboBox;
	private JComboBox<PropertyDetails> propertyComboBox;
	private JButton addButton, cancelButton;
	private JTextArea description;
	
	// to keep reference of selector in which property is going to be added
	private Selector selector;
	
	// default frame size;
	public final Dimension FRAME_SIZE = new Dimension(550, 400);
	
	// reference to parent
	private MainFrame parent;

	// Constructor
	public AddProperty(MainFrame parent, Selector selector) {
		// store the references
		this.selector = selector;
		this.parent = parent;
		
		// disable parent
		enableParent(false);
		
		// set layout to GridBaglayout and create GridBagConstraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.insets = new Insets(10, 10, 10, 10);
					
		// add propertyTypePanel
		selectPropertyGroupPanel = new SelectPropertyGroupPanel(this);
		add(selectPropertyGroupPanel, bagConstraints);
		
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
		bagConstraints.anchor = GridBagConstraints.EAST;
		bagConstraints.gridy++;
		add(buttonPanel, bagConstraints);
		
		// action listener for cancel button
		cancelButton.addActionListener(e -> {
			enableParent(true);
			dispose();
		});
					
		setTitle("Add new Property");
		setIconImage(ImgSrc.getImageIcon());
		setSize(FRAME_SIZE);
		setLocationRelativeTo(parent);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				enableParent(true);
				dispose();
			}
		});
		setVisible(true);
	}

	// action after pressing addButton
	@Override
	public void actionPerformed(ActionEvent e) {
		String value = selectValuePanel.getValue();
		PropertyDetails propertyDetails = (PropertyDetails) propertyComboBox.getSelectedItem();
		Property property = new Property(
				propertyDetails.toString(), value, propertyDetails.getDescription()); 
		selector.addProperty(property);	
		parent.addProperty(property);
		enableParent(true);
		dispose();
	}
	
	// disable/enable parent window
	private void enableParent(boolean b) {
		parent.enableWindow(b);
		setAlwaysOnTop(!b);
	}

	// getter and setter for property description
	public JTextArea getDescription() {
		return description;
	}
	public void setDescription(JTextArea description) {
		this.description = description;
	}
	
	//getter and setter for cancelButton
	public JButton getCancelButton() {
		return cancelButton;
	}
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	// getter and setter for propertyGroupComboBox
	public JComboBox<PropertyGroup> getPropertyGroupComboBox() {
		return propertyGroupComboBox;
	}
	public void setPropertyGroupComboBox(JComboBox<PropertyGroup> propertyTypeComboBox) {
		this.propertyGroupComboBox = propertyTypeComboBox;
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
}