package view.staff;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class StaffContainer extends JPanel {

	// Left panel - Staff Summary
	StaffList staffList= new StaffList();
		
	public StaffContainer() {
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, staffList, staffTabPane());
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));

		add(splitPane, BorderLayout.CENTER);
	}
	
	public JPanel staffTabPane() {
		
		JPanel tabPanel= new JPanel();
		
		JTabbedPane mainTab = new JTabbedPane();
		StaffSummary staffSummary = new StaffSummary();
		StaffAllocation staffAllocation = new StaffAllocation();
		
		tabPanel.setLayout(new BorderLayout());
		
		mainTab.addTab("Summary of Staff", staffSummary);
		mainTab.addTab("Staff Allocation", staffAllocation);
		mainTab.setPreferredSize(new Dimension(600, 400));
		
		tabPanel.add(mainTab, BorderLayout.CENTER);
		
		return tabPanel;
		
	}
}
