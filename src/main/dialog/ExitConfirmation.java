package main.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import main.MainFrame;

public class ExitConfirmation {
	private List<ConfimationEventListener> confimationEventListeners = 
			new ArrayList<ConfimationEventListener>();
	
	// components
	private JDialog dialog;
	private JButton yesButton, noButton, cancelButton;
	
	// reference to parent frame
	private MainFrame parent;
	
	public ExitConfirmation(MainFrame parent) {
		this.parent = parent;
	}
	
	public void ShowExitConfirmation() {
		// create new dialog box
		dialog = new JDialog(parent, "Exit Confirmation", true);
		
		// set layout 
		dialog.setLayout(new GridBagLayout());
		
		// create constraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.anchor = GridBagConstraints.EAST;
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.insets = new Insets(10, 0, 10, 0);
		
		// set confirmation message
		JLabel label = new JLabel("\'" + parent.getCssFile().getName() + "\' has been"
				+ " modified. Save changes ?");
		dialog.add(label, bagConstraints);
		
		// create action listener for buttons
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id;
				JButton button = (JButton) e.getSource();
				
				if (button.equals(yesButton))
					id = ConfirmationEvent.YES;
				else if (button.equals(noButton))
					id = ConfirmationEvent.NO;
				else 
					id = ConfirmationEvent.CANCEL;
				
				dispatchConfirmationEvent(new ConfirmationEvent(dialog, id));
			}
		};
		
		// add no button
		bagConstraints.insets = new Insets(10, 0, 10, 10);
		noButton = new JButton("No");
		noButton.addActionListener(actionListener);
		bagConstraints.gridy++;
		dialog.add(noButton, bagConstraints);
		
		// add cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		bagConstraints.gridx++;
		dialog.add(cancelButton, bagConstraints);
		
		// add yes button
		yesButton = new JButton("Yes");
		yesButton.addActionListener(actionListener);
		bagConstraints.gridx++;
		dialog.add(yesButton, bagConstraints);
		
		// set dialog properties
		dialog.setSize(600, 160);
		dialog.setTitle("Exit Confirmation");
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	// close the dialog box
    public void close() {
        if(dialog != null) {
            dialog.dispose();
        }
    }
    
    // add new listener
    public void addConfirmationEventListener(ConfimationEventListener confimationEventListener) {
        if(!confimationEventListeners.contains(confimationEventListener)) {
            confimationEventListeners.add(confimationEventListener);
        }
    }

    // remove the listener
    public void removeConfirmationEventListener(ConfimationEventListener confimationEventListener) {
    	confimationEventListeners.remove(confimationEventListener);
    }

    // perform action when an event is occurred
    public void dispatchConfirmationEvent(ConfirmationEvent evt) {
        for(ConfimationEventListener confimationEventListener: confimationEventListeners) {
            confimationEventListener.handleConfirmationEvent(evt);
        }
    }
}
