package frames;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel buttonPanel; 
	JButton newfileButton, openfileButton;
	
	public MainFrame() throws IOException {
		
		setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
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
		setIconImage(ImageIO.read(new File("res/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
