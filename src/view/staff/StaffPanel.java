package view.staff;

import javax.swing.JPanel;

public class StaffPanel extends JPanel {

	// Left panel - Staff Summary
	private StaffSummary staffSummary = new StaffSummary();
	
	// Right tabbed pane
	private JTabbedPane rightTabPane = new JTabbedPane();
	
	// main split pane
	private JSplitPane mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, staffSummary, rightTabPane);
	
	public StaffPanel() {
		
		rightTabs.addTab("Summary of Projects", new JPanel());
		rightTabs.addTab("Project Allocation", new JPanel());
		rightTabs.setPreferredSize(new Dimension(600, 600));
		
		setLayout(new BorderLayout());
		add(mainContainer, BorderLayout.CENTER);
	}
	
}
