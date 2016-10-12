package frames;

import css.CSSFile;
import css.CSSSelector;
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
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JButton NewFileButton, OpenFileButton, AddButton, RemoveButton; 
	private CSSFile cssfile;
	private JTree csstree;
	private DefaultMutableTreeNode root;
	private JMenuBar menubar;
	private AddNode addNodeActionListener;
	private DeleteNode removeNodeActionListener;

	public MainFrame() {
		
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
		try {
			setIconImage(ImageIO.read(new File("/usr/local/CSS-Generator/res/icon.png")));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(getParent(), "some files are missing please re-install CSS-Generator", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(ERROR);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} // Constructor
	
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
		save.addActionListener( e ->
				
				cssfile.SaveFile()
		);
		
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
		csstree.setBounds(20, 20, 300, 450);
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(new ImageIcon("res/property.png"));
		csstree.setCellRenderer(renderer);
		
		csstree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				TreePath path = e.getPath();
				
				if(path.toString().equals("[No File Selected]")) {
					return;
				}
				
				switch(path.getPathCount()) {
				
				case 1 :
					AddButton.setText("Add Selector");
					AddButton.setToolTipText("add new Selector in the File");
					break;
					
				case 2 :
					AddButton.setText("Add Property");
					AddButton.setToolTipText("add new Property to selected Selector");
					
					RemoveButton.setText("Remove Selector");
					RemoveButton.setToolTipText("remove selected Selector");
					RemoveButton.setVisible(true);
					
					break;
					
				case 3 :
					AddButton.setText("Edit Propery");
					AddButton.setToolTipText("edit selected Property");
					
					RemoveButton.setText("Remove Property");
					RemoveButton.setToolTipText("remove selected Property");
					RemoveButton.setVisible(true);
					break;
				}
				
				addNodeActionListener.setPath(path);
				removeNodeActionListener.setPath(path);
				AddButton.setVisible(true);
				
			}
		});

	}
	
	public void createButtons() {
	
		NewFileButton = new JButton("New");
		NewFileButton.setBounds(730, 520, 100, 30);
		NewFileButton.addActionListener(new NewFile());
		NewFileButton.setMnemonic(KeyEvent.VK_N);
		NewFileButton.setToolTipText("create new File");
			
		OpenFileButton = new JButton("Open File...");
		OpenFileButton.setBounds(860, 520, 120, 30);
		OpenFileButton.addActionListener(new OpenFile());
		OpenFileButton.setMnemonic(KeyEvent.VK_O);
		OpenFileButton.setToolTipText("open existing File");
		
		AddButton = new JButton("");
		AddButton.setBounds(20, 520, 130, 30);
		addNodeActionListener = new AddNode();
		AddButton.addActionListener(addNodeActionListener);
		AddButton.setVisible(false);
		
		RemoveButton = new JButton();
		RemoveButton.setBounds(170, 520, 170, 30);
		RemoveButton.setMnemonic(KeyEvent.VK_DELETE);
		removeNodeActionListener = new DeleteNode();
		RemoveButton.addActionListener(removeNodeActionListener);
		RemoveButton.setVisible(false);
		
	}

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

	public class DeleteNode implements ActionListener {

		private TreePath path;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(path.getPathCount() == 2) {
				cssfile.removeSelector(path.getLastPathComponent().toString());

			}
			
			if(path.getPathCount() == 3) {
				
				cssfile.removeProperty(path.getPathComponent(2).toString(), path.getLastPathComponent().toString());
			}
			
			DefaultTreeModel model = (DefaultTreeModel) csstree.getModel();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if(node.getParent() != null)
				model.removeNodeFromParent(node);
			
			RemoveButton.setVisible(false);
		}
		
		public void setPath(TreePath path) {
			
			this.path = path;
		}
		
	}

	public class AddNode implements ActionListener {

		private TreePath path;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String name = null;
			
			if(path.getPathCount() == 1) {
				
				name = JOptionPane.showInputDialog("Enter Selector name");
				CSSSelector selector = new CSSSelector(name, "Element Type Selector");
				cssfile.addSelector(selector);
			}
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			node.add(new DefaultMutableTreeNode(name));
			csstree.updateUI();
		}
		
		public void setPath(TreePath path) {
			
			this.path = path;
		}
		
	}

}
