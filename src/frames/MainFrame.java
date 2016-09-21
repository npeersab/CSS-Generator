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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JButton NewFileButton, OpenFileButton, AddButton, RemoveButton; 
	private CSSFile cssfile;
	private JTree csstree;
	private DefaultMutableTreeNode root;
	private JMenuBar menubar;
		
	public MainFrame() throws IOException {
		
		setLayout(null);
	
		root = new DefaultMutableTreeNode("No File Selected");
		createTree(root);
		createButtons();
		createMenuBar();
		
		add(csstree);
		add(NewFileButton);
		add(OpenFileButton);
		add(AddButton);
		add(RemoveButton);
		setJMenuBar(menubar);
		setTitle("CSS Generator");
		setBounds(150, 50, 1020, 620);
		setMaximizedBounds(getBounds());
		setMinimumSize(new Dimension(1020, 620));
		setIconImage(ImageIO.read(new File("res/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} // Constructor
	
	public class OpenFile implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
						
			openFile();						
		}
	}
		
	public class NewFile implements ActionListener {
		
			@Override
		public void actionPerformed(ActionEvent e) {
			
			MainFrame.this.remove(csstree);
				
			setTitle("new file* - CSS Generator");
			root = new DefaultMutableTreeNode("new file*");
			
			createTree(root);
			MainFrame.this.add(csstree);
			MainFrame.this.repaint();	
		}
		
	}

	private void createMenuBar() {
		
		JMenuItem newfile = new JMenuItem("New");
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		newfile.setMnemonic(KeyEvent.VK_N);
		newfile.addActionListener(new NewFile());
		
		JMenuItem openfile = new JMenuItem("Open File...");
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		openfile.setMnemonic(KeyEvent.VK_O);
		openfile.addActionListener(new OpenFile());
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		save.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem saveAs  = new JMenuItem("Save As...");
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		saveAs.setMnemonic(KeyEvent.VK_S);
		
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
		file.addSeparator();
		file.add(save);
		file.add(saveAs);
		file.addSeparator();
		file.add(exit);
		
		menubar = new JMenuBar();
		menubar.add(file);
	} // CreateMenuBar
	
	public void openFile() {
		
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
			
			root = cssfile.getTree();
			remove(csstree);
			createTree(root);
			add(csstree);
			repaint();
	
		}
	}
	
	public void createTree(DefaultMutableTreeNode root) {
		
		csstree = new JTree(root);
		csstree.setBounds(5, 5, 300, 450);
		csstree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				TreePath path = e.getPath();
				if(path.toString().equals("[No File Selected]")) {
					openFile();
					return;
				}
				
				switch(path.getPathCount()) {
				
				case 1 :
					AddButton.setText("Add Selector");
					break;
					
				case 2 :
					AddButton.setText("Add Property");
					RemoveButton.setText("Remove Selector");
					RemoveButton.setVisible(true);
					break;
					
				case 3 :
					AddButton.setText("Edit Propery");
					RemoveButton.setText("Remove Property");
					RemoveButton.setVisible(true);
					break;
				}
				AddButton.setVisible(true);
			}
		});
	}
	
	public void createButtons() {
	
		NewFileButton = new JButton("New");
		NewFileButton.setBounds(730, 520, 100, 30);
		NewFileButton.addActionListener(new NewFile());
		NewFileButton.setMnemonic(KeyEvent.VK_N);
			
		OpenFileButton = new JButton("Open File...");
		OpenFileButton.setBounds(860, 520, 120, 30);
		OpenFileButton.addActionListener(new OpenFile());
		OpenFileButton.setMnemonic(KeyEvent.VK_O);
		
		AddButton = new JButton("");
		AddButton.setBounds(20, 520, 130, 30);
		AddButton.setVisible(false);
		
		RemoveButton = new JButton();
		RemoveButton.setBounds(170, 520, 170, 30);
		RemoveButton.setMnemonic(KeyEvent.VK_DELETE);
		RemoveButton.setVisible(false);
	}
}
