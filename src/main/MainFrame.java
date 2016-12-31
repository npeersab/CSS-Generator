package main;

import css.CSSFile;
import css.Property;
import css.Selector;
import dialog.ButtonEvent;
import dialog.ButtonEventListener;
import dialog.ExitConfirmation;
import main.listener.AddButtonListener;
import main.listener.RemoveButtonListener;
import res.Directory;
import res.ImgSrc;
import theme.ThemeColor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import theme.ThemedJFrame;
import main.codePanel.CodePanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends ThemedJFrame {
	private static final long serialVersionUID = 1L;
	
	// components
	private JButton addButton, removeButton; 
	private CSSFile cssFile;
	private JTree cssTree;
	private DefaultMutableTreeNode root;
	private MenuBar menuBar;
	JMenuItem save, saveAs;
	
	// Listeners
	private AddButtonListener addButtonListener;
	private RemoveButtonListener removeButtonListener;
	
	// Panels
	private TreePanel treePanel;
	private LeftPanel leftPanel;
	private CodePanel codePanel;
	private ButtonPanel buttonPanel;
		
	// windowAdapter
	private customWindowAdapter windowAdapter;
	
	// default title for the application
	private final String TITLE = "CSS Generator";
	
	// keep track on file
	private boolean saved = true;
	
	public MainFrame() {
		// set layout to GridBagLayout
		setLayout(new GridBagLayout());
		
		// create GridbagConstraints
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = bagConstraints.gridy = 0;
		bagConstraints.fill = GridBagConstraints.BOTH;
		
		// set theme
		themeColor = ThemeColor.white;
		
		// create treePanel
		treePanel = new TreePanel(this);
				
		// create default tree
		root = new DefaultMutableTreeNode("No File Selected");
		createTree(root);
		treePanel.updateTree();
		
		// create leftPanel
		leftPanel = new LeftPanel(this);

		// add treePanel and detailsPanel in splitPane
		JSplitPane splitPane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, treePanel, leftPanel);
		splitPane.setDividerLocation(0.2);
		splitPane.setResizeWeight(0.2);
		bagConstraints.weightx = 1;
		bagConstraints.weighty = 4;
		add(splitPane, bagConstraints);

		// create menu bar
		menuBar = new MenuBar(this);
		
		// set frame properties
		setJMenuBar(menuBar);
		setSize(900, 600);
		setTitle(TITLE);
		setIconImage(ImgSrc.getImageIcon());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		windowAdapter = new customWindowAdapter();
		addWindowListener(windowAdapter);
	}
	
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
					removeButton.setEnabled(false);
					break;
					
				case 2 :
					addButton.setText("Add Property");
					addButton.setToolTipText("add new Property to selected Selector");
					
					removeButton.setText("Remove Selector");
					removeButton.setToolTipText("remove selected Selector");
					removeButton.setEnabled(true);
					break;
					
				case 3 :
					addButton.setText("Edit Propery");
					addButton.setToolTipText("edit selected Property");
					
					removeButton.setText("Remove Property");
					removeButton.setToolTipText("remove selected Property");
					removeButton.setEnabled(true);
					break;
				}
				
				addButtonListener.setPath(path);
				removeButtonListener.setPath(path);
				addButton.setEnabled(true);
				
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
			leftPanel.setHeader(cssFile.getName());
			codePanel.updatePanel(cssFile);
			
			buttonPanel.setVisible(true);
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
			leftPanel.setHeader(cssFile.getName());
			codePanel.updatePanel(cssFile);
			addButton.setVisible(true);
			removeButton.setVisible(true);
			buttonPanel.setVisible(true);
		}
	}

	// add new Selector
	public void addSelector(Selector selector) {
		TreePath path = cssTree.getSelectionPath();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		node.add(new DefaultMutableTreeNode(selector));
		cssTree.updateUI();
		cssFile.addSelector(selector);
		codePanel.addSelector(selector);
		fileEdited();
	}
	
	// add new Property
	public void addProperty(Selector selector, Property property) {
		TreePath path = cssTree.getSelectionPath();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		node.add(new DefaultMutableTreeNode(property));
		cssTree.updateUI();
		codePanel.updateSelectorPanel(selector);
		fileEdited();
	}
	
	// make changes after editing a property
	public void editProperty(Selector selector) {
		codePanel.updateSelectorPanel(selector);
		fileEdited();
	}
	
	// make changes when file is edited
	public void fileEdited() {
		updateTitle("*" + cssFile.getName());
		save.setEnabled(true);
		saveAs.setEnabled(true);
		saved(false);
	}
	
	// make changes when file is saved
	public void fileSaved() {
		save.setEnabled(false);
		saveAs.setEnabled(false);
		saved(true);
	}
	
	// update title of frame
	public void updateTitle(String filename) {
		setTitle(filename + " - " + TITLE);
	}
	
	class customWindowAdapter extends WindowAdapter {
	    @Override
	    public void windowClosing(WindowEvent windowEvent) {
	    	exit();
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
	
	// save as new file
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
	
	// exit
	public void exit() {
		if (isSaved()) {
    		System.exit(NORMAL);
    	}
    	else {
    		ExitConfirmation confirmation = new ExitConfirmation(MainFrame.this);
    		confirmation.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					switch (event.getId()) {
					case ButtonEvent.CANCEL:
						break;
					case ButtonEvent.NO:
						dispose();
						break;
					case ButtonEvent.YES:
						save();
						dispose();
					}
					confirmation.close();
				}
			});
    		confirmation.ShowExitConfirmation();
    	}
	}
	
	// enable or disable MainFrame
	public void enableWindow(boolean b) {
		setFocusableWindowState(b);
		setEnabled(b);
		setAlwaysOnTop(b);
		removeWindowListener(windowAdapter);
		if (b)
			addWindowListener(windowAdapter);
		revalidate();	
	}
	
	// apply theme
	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		treePanel.applyTheme(themeColor);
		leftPanel.applyTheme(themeColor);
		codePanel.applyTheme(themeColor);		
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
	
	// getter and setter for saved;
	public boolean isSaved() {
		return saved;
	}
	public void saved(boolean saved) {
		this.saved = saved;
	}
	
	// getter and setter for codePanel
	public CodePanel getCodePanel() {
		return codePanel;
	}
	public void setCodePanel(CodePanel codePanel) {
		this.codePanel = codePanel;
	}

	// getter and setter for button panel
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}
	public void setButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
}
