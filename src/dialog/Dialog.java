package dialog;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;

public class Dialog {
		// dialog
		protected JDialog dialog;
		
		// list of listeners
		protected List<ButtonEventListener> buttonEventListeners = 
				new ArrayList<ButtonEventListener>();
		
		public JDialog getDialog() {
			return dialog;
		}
		public void setDialog(JDialog dialog) {
			this.dialog = dialog;
		}
		
		// close the dialog box
	    public void close() {
	        if(dialog != null) {
	            dialog.dispose();
	        }
	    }
	    
	    // add new listener
	    public void addButtonEventListener(ButtonEventListener buttonEventListener) {
	        if(!buttonEventListeners.contains(buttonEventListener)) {
	            buttonEventListeners.add(buttonEventListener);
	        }
	    }

	    // remove the listener
	    public void removeButtonEventListener(ButtonEventListener buttonEventListener) {
	    	buttonEventListeners.remove(buttonEventListener);
	    }

	    // perform action when an event is occurred
	    public void dispatchButtonEvent(ButtonEvent evt) {
	        for(ButtonEventListener buttonEventListener: buttonEventListeners) {
	            buttonEventListener.handleButtonEvent(evt);
	        }
	    }
}
