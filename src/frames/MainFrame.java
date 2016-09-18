package frames;

import css.CSSFile;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel; 
	private JButton newfileButton, openfileButton;
	private CSSFile cssfile;
	private JTextArea text;
	
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
		OpenFile open = new OpenFile();
		openfileButton.addActionListener(open);
		
		text = new JTextArea(100, 100);
		add(text);
		
		add(buttonPanel);
		setTitle("CSS Generator");
		setSize(1020, 600);
		setLocation(150, 100);
		setIconImage(ImageIO.read(new File("res/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public class OpenFile implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String home = System.getProperty("user.home");
			File cssdir = new File(home, "css_generator");
			
			JFileChooser filechooser = new JFileChooser(cssdir);
			filechooser.setDialogTitle("Select File");
			filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			filechooser.setFileFilter(new FileNameExtensionFilter("CSS Files","css"));
			
	
			if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				cssfile = new CSSFile(filechooser.getSelectedFile());
				cssfile.ReadFile();
			}						
		}
	}
}
