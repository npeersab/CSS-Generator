package frames;

import css.CSSFile;
import css.Property;
import css.Selector;
import listeners.AddButtonListener;
import listeners.RemoveButtonListener;
import res.Directory;
import res.ImgSrc;
import panels.MainFrameButtonPanel;
import panels.MainFrameDetailsPanel;
import panels.TreePanel;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
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
import javax.swing.tree.TreePath;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// components
	private JButton addButton, removeButton; 
	private CSSFile cssFile;
	private JTree cssTree;
	private DefaultMutableTreeNode root;
	private JMenuBar menubar;
	private JMenuItem save, saveAs;
	
	// Listeners
	private AddButtonListener addButtonListener;
	private RemoveButtonListener removeButtonListener;
	
	// Panels
	private TreePanel treePanel;
	private MainFrameDetailsPanel detailsPanel;
	private MainFrameButtonPanel buttonPanel;
	
	private customWindowAdapter windowAdapter;
	
	// default title for the application
	private final String TITLE = "CSS Generator";
	
	public MainFrame() {
		// set layout to GridBagLayout and create GridbagConstraints
		setLayout(new GridBagLayout());			
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.fill = GridBagConstraints.BOTH;
		bagConstraints.insets = new Insets(4, 4, 4, 4);
		
		// create and add treePanel
		treePanel = new TreePanel(this);
		bagConstraints.weightx = 1;
		bagConstraints.weighty = 3;
		add(treePanel, bagConstraints);
		
		// create default tree
		root = new DefaultMutableTreeNode("No File Selected");
		createTree(root);
		treePanel.updateTree();
		
		// create and add details panel
		detailsPanel = new MainFrameDetailsPanel(this);
		bagConstraints.weightx = 4;
		bagConstraints.weighty = 3;
		bagConstraints.gridx++;
		add(detailsPanel, bagConstraints);
		
		// create and add buttons panel
		buttonPanel = new MainFrameButtonPanel(this);
		bagConstraints.gridwidth = 2;
		bagConstraints.weightx = 4;
		bagConstraints.weighty = 2;
		bagConstraints.gridx = 0;		
		bagConstraints.gridy = 1;
		add(buttonPanel, bagConstraints);
		
		createMenuBar();
		
		// set frame properties
		setJMenuBar(menubar);
		setSize(900, 600);
		setTitle(TITLE);
		setIconImage(ImgSrc.getImageIcon());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		windowAdapter = new customWindowAdapter();
		addWindowListener(windowAdapter);
	} // Constructor
	
	// create new menuBar
	private void createMenuBar() {
		JMenuItem newfile = new JMenuItem("New");
		newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		newfile.setMnemonic(KeyEvent.VK_N);
		newfile.addActionListener(
				e -> newFile());
		
		JMenuItem openfile = new JMenuItem("Open File...");
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		openfile.setMnemonic(KeyEvent.VK_O);
		openfile.addActionListener(
				e -> openFile());
		
		save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		save.setMnemonic(KeyEvent.VK_S);
		save.setEnabled(false);
		save.addActionListener(e -> save());
		
		saveAs  = new JMenuItem("Save As...");
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		saveAs.setEnabled(false);
		saveAs.addActionListener(e -> saveAs());
		saveAs.setMnemonic(KeyEvent.VK_S);
				
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(
				e -> System.exit(0));
		
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
	
	// create cssTree
	private void createTree(DefaultMutableTreeNode root) {
		cssTree = new JTree(root);
		cssTree.setSize(200, 300);
		cssTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();
				
				if (path.toString().equals("[No File Selected]"))
					return;
				
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
				
				addButtonListener.setPath(path);
				removeButtonListener.setPath(path);
				addButton.setVisible(true);
				
			}
		});
	}
	
	// open existing file
	public void openFile() {
		File cssdir = Directory.getCSSDirectory();
		JFileChooser filechooser = new JFileChooser(cssdir);
		filechooser.setDialogTitle("Select File");
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filechooser.setFileFilter(new FileNameExtensionFilter("CSS Files","css"));
				
		if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
			cssFile = new CSSFile(file);
			cssFile.ReadFile();
			
			root = cssFile.getTree();
			createTree(root);
			treePanel.updateTree();
			
			updateTitle(cssFile.getName());
		}
	}
	
	// create new cssFile
	public void newFile() {
		File cssdir = Directory.getCSSDirectory();
		JFileChooser chooser = new JFileChooser(cssdir);
		chooser.setDialogTitle("Enter File Name");
		chooser.setFileFilter(new FileNameExtensionFilter("CSS Files","css"));
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			cssFile = new CSSFile(chooser.getSelectedFile());
			cssFile.saveFile();
			root = new DefaultMutableTreeNode(cssFile.getName());
			createTree(root);
			treePanel.updateTree();
			fileEdited();
			updateTitle("*" + cssFile.getName());
		}
	}

	// add new Selector
	public void addSelector(Selector selector) {
		TreePath path = cssTree.getSelectionPath();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		node.add(new DefaultMutableTreeNode(selector));
		cssTree.updateUI();
		cssFile.addSelector(selector);
		
		fileEdited();
	}
	
	// add new Property
	public void addProperty(Property property) {
		TreePath path = cssTree.getSelectionPath();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		node.add(new DefaultMutableTreeNode(property));
		cssTree.updateUI();
		
		fileEdited();
	}
	
	// make changes when file is edited
	public void fileEdited() {
		save.setEnabled(true);
		saveAs.setEnabled(true);
	}
	
	// make changes when file is saved
	public void fileSaved() {
		save.setEnabled(false);
		saveAs.setEnabled(false);
	}
	
	public void updateTitle(String filename) {
		setTitle(filename + " - " + TITLE);
	}
	
	class customWindowAdapter extends WindowAdapter {
	    @Override
	    public void windowClosing(WindowEvent windowEvent) {
	        if (JOptionPane.showConfirmDialog(MainFrame.this, 
	            "Are you sure to close this window?", "Really Closing?", 
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	            System.exit(0);
	        }
	    }
	}    

	// save file
	public void save() {
		if(cssFile.getFile().exists()) {
			cssFile.saveFile();
			fileSaved();
			updateTitle(cssFile.getName());
		}
		else
			saveAs();
	}
	
	// save new file
	public void saveAs() {
		File cssdir = Directory.getCSSDirectory();
		JFileChooser chooser = new JFileChooser(cssdir);
		chooser.setDialogTitle("Save As...");
		chooser.setFileFilter(new FileNameExtensionFilter("CSS Files","css"));
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			cssFile.setFile(chooser.getSelectedFile());
		else
			return;
		updateTitle(cssFile.getName());
		cssFile.saveFile();
		fileSaved();
	}
	
	// getter and setter for windowApdater
	public customWindowAdapter getWindowAdapter() {
		return windowAdapter;
	}
	public void setWindowAdapter(customWindowAdapter windowAdapter) {
		this.windowAdapter = windowAdapter;
	}
	
	// getter and setter for cssTree
	public JTree getCssTree() {
		return cssTree;
	}
	public void setCssTree(JTree cssTree) {
		this.cssTree = cssTree;
	}
	
	// getter and setter for addButtonListener
	public AddButtonListener getAddNButtonListener() {
		return addButtonListener;
	}
	public void setAddButtonListener(AddButtonListener addButtonListener) {
		this.addButtonListener = addButtonListener;
	}
	
	// getter and setter for removeButtonListener
	public RemoveButtonListener getRemoveButtonListener() {
		return removeButtonListener;
	}
	public void setRemoveButtonListener(RemoveButtonListener removeButtonListener) {
		this.removeButtonListener = removeButtonListener;
	}
	
	// getter and setter for addButton
	public JButton getAddButton() {
		return addButton;
	}
	public void setAddButton(JButton button) {
		this.addButton = button;
	}
	
	// getter and setter for removeButton
	public JButton getRemoveButton() {
		return removeButton;
	}
	public void setRemoveButton(JButton button) {
		this.removeButton = button;
	}
	
	// getter and setter for cssFile
	public CSSFile getCssFile() {
		return cssFile;
	}
	public void setCssFile(CSSFile cssFile) {
		this.cssFile = cssFile;
	}
}
