package frames;

import css.CSSFile;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JButton newfileButton, openfileButton;
	private CSSFile cssfile;
	private JTree csstree;
	private DefaultMutableTreeNode root;
	private JMenuBar menubar;
		
	public MainFrame() throws IOException {
		
		setLayout(null);
								
		newfileButton = new JButton("New");
		newfileButton.setBounds(770, 520, 100, 30);
			
		openfileButton = new JButton("Open");
		openfileButton.setBounds(890, 520, 100, 30);
		OpenFile open = new OpenFile();
		openfileButton.addActionListener(open);
		openfileButton.setMnemonic(KeyEvent.VK_O);
		
		root = new DefaultMutableTreeNode("New File");
		csstree = new JTree(root);
		csstree.setBounds(0, 0, 300, 620);
		//csstree.setRootVisible(false);
		add(csstree);
		
		
		add(newfileButton);
		add(openfileButton);
		createMenuBar();
		setJMenuBar(menubar);
		setTitle("CSS Generator");
		setBounds(150, 50, 1020, 620);
		setMaximizedBounds(getBounds());
		setMinimumSize(new Dimension(1020, 620));
		setIconImage(ImageIO.read(new File("res/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public class OpenFile implements ActionListener {
		
		@Override
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
				root.removeAllChildren();
				root.add(cssfile.getTree());
				csstree.updateUI();
			}						
		}
	}
	
	private void createMenuBar() {
		
		JMenuItem newfile = new JMenuItem("New");
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		newfile.setMnemonic(KeyEvent.VK_N);
		
		JMenuItem openfile = new JMenuItem("Open");
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		openfile.setMnemonic(KeyEvent.VK_O);
		openfile.addActionListener(new OpenFile());
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.add(newfile);
		file.add(openfile);
		file.add(exit);
		
		
		menubar = new JMenuBar();
		menubar.add(file);
	}
}
