package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import frames.PropertyFrame;

public class PropertyButtonPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public PropertyButtonPanel(PropertyFrame parent) {
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		parent.setAddButton(addButton);
		add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		parent.setCancelButton(cancelButton);
		add(cancelButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
