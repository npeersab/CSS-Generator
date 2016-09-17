package frames;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	JPanel buttonPanel; 
	JButton newfileButton, openfileButton;
	
	public MainFrame() {
		
		setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setAlignmentX(500);
		
		newfileButton = new JButton("New");
		newfileButton.setBounds(770, 520, 100, 30);
		buttonPanel.add(newfileButton);
		openfileButton = new JButton("Open");
		openfileButton.setBounds(890, 520, 100, 30);
		buttonPanel.add(openfileButton);
		
		add(buttonPanel);
		setTitle("CSS Generator");
		setSize(1020, 600);
		setLocation(150, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
