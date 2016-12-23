package panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import css.CSSFile;
import css.Selector;

public class CodePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private HashMap<Selector, SelectorCodePanel> selectorHashMap;

	public CodePanel() {
		// set layout and create constraints
		setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.insets = new Insets(5, 5, 0, 0);
		
		JLabel label = new JLabel("No file selected");
		label.setForeground(Color.WHITE);
		add(label, bagConstraints);
		label = new JLabel("Create or open existing file from file menu");
		label.setForeground(Color.WHITE);
		bagConstraints.gridy++;
		add(label, bagConstraints);
		setBackground(Color.GRAY);
		
		bagConstraints.weighty = 50;
		add(new JLabel(), bagConstraints);
	}
	
	public void updatePanel(CSSFile cssFile) {
		// remove all existing components
		removeAll();
		
		// get list of selectors
		LinkedList<Selector> selectorsList = cssFile.getSelectorsList();
		Iterator<Selector> iterator = selectorsList.iterator();
		
		// create hash map to store selector and corresponding code panel
		selectorHashMap = new HashMap<Selector, SelectorCodePanel>();
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = 0;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.anchor = GridBagConstraints.NORTHWEST;
		
		while (iterator.hasNext()) {
			Selector selector = iterator.next();
			SelectorCodePanel selectorCodePanel = new SelectorCodePanel(selector);
			bagConstraints.gridy++;
			add(selectorCodePanel, bagConstraints);
			selectorHashMap.put(selector, selectorCodePanel);
		}
		
		bagConstraints.gridy++;
	//	bagConstraints.fill = GridBagConstraints.VERTICAL;
		bagConstraints.weighty = 50;
		add(new JLabel(), bagConstraints);
	}
}
