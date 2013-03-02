package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import view.MainFrame;

public class StaffView extends JPanel {

	// Left panel - Staff Summary
	StaffSummary staffSummary;
	StaffAllocation staffAllocation;
	StaffList staffList;

	public StaffView(MainFrame view) {

		staffList = new StaffList(view);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				staffList, staffTabPane());

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));

		add(splitPane, BorderLayout.CENTER);

	}

	public JPanel staffTabPane() {

		JPanel tabPanel = new JPanel();

		JTabbedPane mainTab = new JTabbedPane();
		staffSummary = new StaffSummary();
		staffAllocation = new StaffAllocation();

		JScrollPane staffSummaryScrollPane = new JScrollPane(staffSummary);

		
		tabPanel.setLayout(new BorderLayout());

		mainTab.addTab("Summary of Staff", staffSummaryScrollPane);
		mainTab.addTab("Staff Allocation", staffAllocation);
		mainTab.setPreferredSize(new Dimension(600, 400));

		tabPanel.add(mainTab, BorderLayout.CENTER);

		return tabPanel;

	}


	public String deleteStaff() {
		return staffList.deleteStaff();
	}

	public StaffList getStaffList() {
		return staffList;
	}

	public void addController(ActionListener controller) {

		staffList.addController(controller);
		staffSummary.addController(controller);
		staffAllocation.addController(controller);

	}

}