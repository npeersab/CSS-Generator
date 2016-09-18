package frames;

import css.CSSFile;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeNode;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JButton newfileButton, openfileButton;
	private CSSFile cssfile;
	private JTree csstree;
		
	public MainFrame() throws IOException {
		
		setLayout(null);
								
		newfileButton = new JButton("New");
		newfileButton.setBounds(770, 520, 100, 30);
		add(newfileButton);
		
		openfileButton = new JButton("Open");
		openfileButton.setBounds(890, 520, 100, 30);
		OpenFile open = new OpenFile();
		openfileButton.addActionListener(open);
		add(openfileButton);
				
		setTitle("CSS Generator");
		setBounds(150, 50, 1020, 600);
		setMaximizedBounds(getBounds());
		setMinimumSize(new Dimension(1020, 600));
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
				setTitle(filechooser.getSelectedFile().getName() + " - CSS Generator");
				cssfile = new CSSFile(filechooser.getSelectedFile());
				cssfile.ReadFile();
			}						
		}
	}
}
