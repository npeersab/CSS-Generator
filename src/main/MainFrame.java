package main;

import css.CSSFile;
import css.Property;
import css.Selector;
import dialog.ButtonEvent;
import dialog.ButtonEventListener;
import dialog.DialogBox;
import editProperty.EditProperty;
import main.listener.AddElementListener;
import main.listener.RemoveElementListener;
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
import addProperty.AddProperty;
import addSelector.AddSelector;
import theme.ThemedJFrame;
import main.codePanel.CodePanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MainFrame extends ThemedJFrame {
	private static final long serialVersionUID = 1L;

	// components 
	private CSSFile cssFile;
	private JTree cssTree;
	private DefaultMutableTreeNode root;
	private MenuBar menuBar;
	private JMenuItem saveItem, saveAsItem;
	private JButton saveButton, saveAsButton;

	// Listeners
	private AddElementListener addElementListener;
	private RemoveElementListener removeElementListener;

	// Panels
	private TreePanel treePanel;
	private RightPanel rightPanel;
	private CodePanel codePanel;
	private ToolBar toolBar;

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
		
		// create listeners
		addElementListener = new AddElementListener(this);
		removeElementListener = new RemoveElementListener(this);

		// set theme
		themeColor = ThemeColor.white;
		
		// create tool bar
		toolBar = new ToolBar(this);
		bagConstraints.weightx = 1;
		bagConstraints.weighty = 0.5;
		add(toolBar, bagConstraints);

		// create treePanel
		treePanel = new TreePanel(this);

		// create default tree
		root = new DefaultMutableTreeNode("No File Selected");
		createTree(root);
		treePanel.updateTree();

		// create leftPanel
		rightPanel = new RightPanel(this);
	
		// add treePanel and detailsPanel in splitPane
		JSplitPane splitPane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, treePanel, rightPanel);
		splitPane.setDividerLocation(0.2);
		splitPane.setResizeWeight(0.2);
		bagConstraints.weightx = 1;
		bagConstraints.weighty = 4;
		bagConstraints.gridy++;
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setVisible(true);
	}

	// create cssTree
	private void createTree(DefaultMutableTreeNode root) {
		cssTree = new JTree(root);
		cssTree.setSize(200, 300);

		cssTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();
				// when no file is selected
				if (path.toString().equals("[No File Selected]"))
					return;
				addElementListener.setPath(path);
				removeElementListener.setPath(path);
			}
		});
	}

	// open existing file
	public void openFile() {
		// check whether file is saved or not
		if (!saved) {
			// create dialog box to ask that file is to be saved or not
			DialogBox dialogBox = new DialogBox(this, cssFile.getName(), DialogBox.SAVE_MODIFIED);
			dialogBox.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					dialogBox.close();
					switch (event.getId()) {
					case ButtonEvent.YES:
						save();
						showOpenFileChooser();
						break;
					case ButtonEvent.NO:
						showOpenFileChooser();
						break;
					}	
				}
			});
			dialogBox.showDialogBox();
		}
		// if file is saved show file chooser
		else 
			showOpenFileChooser();
	}

	public void showOpenFileChooser() {
		// get CSS directory 
		File cssdir = Directory.getCSSDirectory();

		// create file chooser
		JFileChooser filechooser = new JFileChooser(cssdir);
		filechooser.setDialogTitle("Select File");
		filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filechooser.setFileFilter(new FileNameExtensionFilter("CSS Files","css"));

		if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
			cssFile = new CSSFile(this, file);
			readFile(file);
			fileSaved();
		}
	}

	// read the file
	public void readFile(File file) {
		cssFile.readFile();
		root = cssFile.getTree();
		createTree(root);
		treePanel.updateTree();

		updateTitle(cssFile.getName());
		rightPanel.setHeader(cssFile.getName());
		codePanel.updatePanel(cssFile);

		saveAsItem.setEnabled(true);
		saveAsButton.setEnabled(true);
	}

	// create new cssFile
	public void newFile() {
		// if file is saved show file chooser
		if (isSaved()) 
			showNewFileChooser();
		else {
			// create dialog box
			DialogBox dialogBox = new DialogBox(this, cssFile.getName(), DialogBox.SAVE_MODIFIED);
			dialogBox.addButtonEventListener(new ButtonEventListener() { 
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					dialogBox.close();
					switch (event.getId()) {
					case ButtonEvent.YES:
						save();
						showNewFileChooser();
						break;
					case ButtonEvent.NO:
						showNewFileChooser();
						break;
					}
				}
			});
			dialogBox.showDialogBox();
		}
	}

	public void showNewFileChooser() {
		File cssdir = Directory.getCSSDirectory();
		JFileChooser chooser = new JFileChooser(cssdir);
		chooser.setDialogTitle("Enter File Name");
		chooser.setFileFilter(new FileNameExtensionFilter("CSS Files","css"));
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (!file.getName().endsWith(".css"))
				file = new File(file.getAbsolutePath() + ".css");
			cssFile = new CSSFile(this, file);
			cssFile.saveFile();
			root = new DefaultMutableTreeNode(cssFile);
			cssFile.setSelectorsNode(root);
			createTree(root);
			treePanel.updateTree();
			fileEdited();
			rightPanel.setHeader(cssFile.getName());
			codePanel.updatePanel(cssFile);
		}
	}

	// add new Selector
	public void addSelector(Selector selector) {
		/// add selector in cssTree
		DefaultMutableTreeNode selectorsNode = cssFile.getSelectorsNode();
		DefaultMutableTreeNode selectorNode = new DefaultMutableTreeNode(selector);
		selectorsNode.add(selectorNode);
		cssTree.updateUI();

		// add selector in file
		cssFile.addSelector(selector, selectorNode);

		// add selector in codePanel
		codePanel.addSelector(selector);

		// set file as edited
		fileEdited();

		// expand cssTree and set selection
		cssTree.expandRow(0);
		cssTree.setSelectionRow(0);

		// re apply theme to tree
		treePanel.updateTreeTheme(themeColor);
	}

	// remove selector
	public void removeSelector(Selector selector) {
		cssFile.removeSelector(selector);
		codePanel.removeSelector(selector);
	}

	// add new Property
	public void addProperty(Selector selector, Property property, DefaultMutableTreeNode node) {

		// add property in CSS tree
		DefaultMutableTreeNode propertyNode = new DefaultMutableTreeNode(property);
		node.add(propertyNode);
		cssTree.updateUI();

		// add property in selector
		selector.addProperty(property, propertyNode);

		// update code panel
		codePanel.updateSelectorPanel(selector);

		// set file status
		fileEdited();

		// re apply theme to tree
		treePanel.updateTreeTheme(themeColor);
	}

	// remove property
	public void removeProperty(Selector selector, Property property) {
		selector.removeProperty(this, property);
		codePanel.updateSelectorPanel(selector);
	}

	// make changes after editing a property
	public void editProperty(Selector selector) {
		codePanel.updateSelectorPanel(selector);
		fileEdited();
	}

	// make changes when file is edited
	public void fileEdited() {
		updateTitle("*" + cssFile.getName());
		saveItem.setEnabled(true);
		saveAsItem.setEnabled(true);
		saveButton.setEnabled(true);
		saveAsButton.setEnabled(true);
		saved(false);
	}

	// make changes when file is saved
	public void fileSaved() {
		saveItem.setEnabled(false);
		saveButton.setEnabled(false);
		saved(true);
	}

	// update title of frame
	public void updateTitle(String filename) {
		setTitle(filename + " - " + TITLE);
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
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			cssFile.setFile(file);
			cssFile.saveFile();
			cssTree.updateUI();
			String name = cssFile.getName();
			updateTitle(name);
			rightPanel.setHeader(name);
			fileSaved();
		}
		else
			return;
	}

	// exit
	public void exit() {
		if (isSaved()) {
			System.exit(NORMAL);
		}
		else {
			DialogBox dialogBox = new DialogBox(this, cssFile.getName(), DialogBox.SAVE_MODIFIED);
			dialogBox.addButtonEventListener(new ButtonEventListener() {
				@Override
				public void handleButtonEvent(ButtonEvent event) {
					switch (event.getId()) {
					case ButtonEvent.NO:
						dispose();
						break;
					case ButtonEvent.YES:
						save();
						dispose();
						break;
					}
					dialogBox.close();
				}
			});
			dialogBox.showDialogBox();
		}
	}

	// display dialog box to add new selector
	public void showAddSelectorDialog() {
		// create new add selector dialog
		AddSelector addSelector = new AddSelector(this);

		// add listener to dialog
		addSelector.addButtonEventListener(new ButtonEventListener() {
			@Override
			public void handleButtonEvent(ButtonEvent event) {
				// if add button is present
				if (event.getId() == ButtonEvent.YES) {
					Selector selector = addSelector.getSelector();

					if (selector != null) {
						// if the selector is already present
						if (cssFile.contains(selector)) {

							// create overwrite confirmation window
							DialogBox dialogBox = new DialogBox(
									addSelector.getDialog(), selector.toString(), DialogBox.OVERWRITE);

							// add listener to dialog
							dialogBox.addButtonEventListener(new ButtonEventListener() {
								@Override
								public void handleButtonEvent(ButtonEvent event) {

									// if yes button is pressed
									if (event.getId() == ButtonEvent.YES) {

										// remove all existing selectors with same name
										cssFile.removeAllSelector(selector);

										// add new selector
										addSelector(selector);
										addSelector.close();
									}
									// close confirmation if any button pressed
									dialogBox.close();
								}
							});
							dialogBox.showDialogBox();
						}
						else {
							addSelector(selector);
							addSelector.close();
						}
					}
					else
						return;
				}
			}
		});
		addSelector.showAddSelector();		
	}

	// display dialog box to add new property
	public void showAddPropertyDialog(TreePath path) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getPathComponent(1);
		Selector selector = (Selector) node.getUserObject();

		// create new add property dialog
		AddProperty addProperty = new AddProperty(this);

		// add listener to dialog box
		addProperty.addButtonEventListener(new ButtonEventListener() {
			@Override
			public void handleButtonEvent(ButtonEvent event) {

				// when add button is pressed
				if (event.getId() == ButtonEvent.YES) {
					Property property = addProperty.getProperty();
					if (property != null) {

						// if the property is already present in selector
						if (selector.contains(property)) {

							// create new overwrite confirmation dialog
							DialogBox dialogBox = new DialogBox(
									addProperty.getDialog(), property.getName(), DialogBox.OVERWRITE);								

							// add listener to dialog
							dialogBox.addButtonEventListener(new ButtonEventListener() {
								@Override
								public void handleButtonEvent(ButtonEvent event) {

									// if yes button is pressed
									if (event.getId() == ButtonEvent.YES) {

										// remove all properties with same name from  selector
										selector.removeAllProperties(MainFrame.this, property);

										// add new property
										addProperty(selector, property, node);

										// expand tree
										cssTree.expandPath(path);
										addProperty.close();
									}
									// close confirmation if any button pressed
									dialogBox.close();
								}
							});
							dialogBox.showDialogBox();
						}
						else  {
							addProperty(selector, property, node);
							cssTree.expandPath(path);
							addProperty.close();
						}
					}
					else return;
				}
			}
		});
		addProperty.showAddProperty();
	} 
	
	// display dialog box to edit property
	public void showEditPropertyDialog(TreePath path) {
		// edit the property
		Selector selector = (Selector) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();
		EditProperty editProperty = new EditProperty(
				this, selector, 
				(Property) ((DefaultMutableTreeNode) path.getPathComponent(2)).getUserObject());
		editProperty.showEditProperty();
	}

	// apply theme
	@Override
	public void applyTheme(ThemeColor themeColor) {
		setThemeColor(themeColor);
		treePanel.applyTheme(themeColor);
		rightPanel.applyTheme(themeColor);
		codePanel.applyTheme(themeColor);
		toolBar.applyTheme(themeColor);
	}

	// getter and setter for cssTree
	public JTree getCssTree() {
		return cssTree;
	}
	public void setCssTree(JTree cssTree) {
		this.cssTree = cssTree;
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

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getSaveAsButton() {
		return saveAsButton;
	}

	public void setSaveAsButton(JButton saveAsButton) {
		this.saveAsButton = saveAsButton;
	}

	public JMenuItem getSaveItem() {
		return saveItem;
	}

	public void setSaveItem(JMenuItem saveItem) {
		this.saveItem = saveItem;
	}

	public JMenuItem getSaveAsItem() {
		return saveAsItem;
	}

	public void setSaveAsItem(JMenuItem saveAsItem) {
		this.saveAsItem = saveAsItem;
	}

	public AddElementListener getAddElementListener() {
		return addElementListener;
	}

	public void setAddElementListener(AddElementListener addElementListener) {
		this.addElementListener = addElementListener;
	}

	public RemoveElementListener getRemoveElementListener() {
		return removeElementListener;
	}

	public void setRemoveElementListener(RemoveElementListener removeElementListener) {
		this.removeElementListener = removeElementListener;
	}
}
