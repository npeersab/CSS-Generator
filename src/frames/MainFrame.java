package frames;

import css.CSSFile;
import css.CSSSelector;
import frames.ImgSrc;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JButton addButton, removeButton; 
	private CSSFile cssfile;
	private JTree csstree;
	private DefaultMutableTreeNode root;
	private JMenuBar menubar;
	private AddNode addNodeActionListener;
	private DeleteNode removeNodeActionListener;
	private JPanel buttonPanel, detailsPanel;
	private JScrollPane treePane;

	public MainFrame() {
		
		setLayout(new GridBagLayout());
		
		root = new DefaultMutableTreeNode("No File Selected");
		createTree(root);
		
		addTreePane();
		addButtons();
		addDetailsPanel();
		
		createMenuBar();
		setJMenuBar(menubar);
		setSize(900, 600);
		setTitle("CSS Generator");
					
		setIconImage(ImgSrc.getImageIcon());
		setLocationRelativeTo(null);
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
		save.addActionListener(e ->
				
				cssfile.SaveFile()
		);
		
		JMenuItem saveAs  = new JMenuItem("Save As...");
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		saveAs.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(e ->
			
				System.exit(0)
		);
		
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
	
	private void createTree(DefaultMutableTreeNode root) {
		
		csstree = new JTree(root);
						
		csstree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				TreePath path = e.getPath();
				
				if (path.toString().equals("[No File Selected]")) {
					return;
				}
				
				switch (path.getPathCount()) {
				
				case 1 :
					addButton.setText("Add Selector");
					addButton.setToolTipText("add new Selector in the File");
					removeButton.setVisible(false);
					break;
					
				case 2 :
					addButton.setText("Add Property");
					addButton.setToolTipText("add new Property to selected Selector");
					
					removeButton.setText("Remove Selector");
					removeButton.setToolTipText("remove selected Selector");
					removeButton.setVisible(true);
					
					break;
					
				case 3 :
					addButton.setText("Edit Propery");
					addButton.setToolTipText("edit selected Property");
					
					removeButton.setText("Remove Property");
					removeButton.setToolTipText("remove selected Property");
					removeButton.setVisible(true);
					break;
				}
				
				addNodeActionListener.setPath(path);
				removeNodeActionListener.setPath(path);
				addButton.setVisible(true);
				
			}
		});

	}
	
	private void addButtons() {
	
		addButton = new JButton();
		addNodeActionListener = new AddNode();
		addButton.addActionListener(addNodeActionListener);
		addButton.setVisible(false);
		
		removeButton = new JButton();
		removeButton.setMnemonic(KeyEvent.VK_DELETE);
		removeNodeActionListener = new DeleteNode();
		removeButton.addActionListener(removeNodeActionListener);
		removeButton.setVisible(false);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.insets = new Insets(10, 10, 10, 10);
		buttonPanel.add(addButton, bagConstraints);
		buttonPanel.add(removeButton, bagConstraints);
	//	buttonPanel.setBackground(new Color(50, 50, 40));
		
		bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		bagConstraints.gridheight = 1;
		bagConstraints.gridwidth = 4;
		bagConstraints.gridy = 3;
		add(buttonPanel, bagConstraints);
	}

	private void addTreePane() {
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.weightx = bagConstraints.weighty = 1;
		if (treePane != null) 
			remove(treePane);

		treePane = new JScrollPane(csstree,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bagConstraints.gridheight = 2;
		bagConstraints.gridwidth = 1;
		add(treePane, bagConstraints);
		revalidate();
	}
	
	private void addDetailsPanel() {
		
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.weightx = bagConstraints.weighty = 1;
	
		
		detailsPanel = new JPanel();
	//	detailsPanel.setBackground(new Color(100, 20, 100));
		bagConstraints.gridwidth = 3;
		bagConstraints.gridheight = 2;
		bagConstraints.weightx = 5;
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 0;
		add(detailsPanel, bagConstraints);
			
	}
	
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
			createTree(root);
			addTreePane();
		}
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
			cssfile = new CSSFile(new File("new file"));
			root = new DefaultMutableTreeNode("new file*");
			createTree(root);
			addTreePane();
			MainFrame.this.repaint();	
		}
		
	}

	public class DeleteNode implements ActionListener {

		private TreePath path;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (path.getPathCount() == 2) {
				cssfile.removeSelector(path.getLastPathComponent().toString());

			}
			
			if (path.getPathCount() == 3) {
				
				cssfile.removeProperty(path.getPathComponent(2).toString(),
						path.getLastPathComponent().toString());
			}
			
			DefaultTreeModel model = (DefaultTreeModel) csstree.getModel();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (node.getParent() != null)
				model.removeNodeFromParent(node);
			
			removeButton.setVisible(false);
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
			
			if (path.getPathCount() == 1) {
				
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
