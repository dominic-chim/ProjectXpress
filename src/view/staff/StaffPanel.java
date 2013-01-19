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
		
		setLayout(new BorderLayout());
		add(mainContainer, BorderLayout.CENTER);
	}
	
}
