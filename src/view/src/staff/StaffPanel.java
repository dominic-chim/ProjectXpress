package staff;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class StaffPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1252765657849296634L;

	// left panel 
	StaffList staffList= new StaffList();
	
	// right tabbed pane
	private JTabbedPane rightTabs = new JTabbedPane();
	
	// main split pane
	private JSplitPane mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, staffList, rightTabs);
	
	public StaffPanel() {
		
		// TODO change these tabs to customized ones
		rightTabs.addTab("Summary of Projects", new JPanel());
		rightTabs.addTab("Project Allocation", new JPanel());
		rightTabs.setPreferredSize(new Dimension(600, 600));
		
		setLayout(new BorderLayout());
		add(mainContainer, BorderLayout.CENTER);
	}
}
