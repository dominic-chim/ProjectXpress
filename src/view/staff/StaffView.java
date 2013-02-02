package view.staff;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class StaffView extends JPanel {

	// Left panel - Staff Summary
	StaffList staffList = new StaffList();
	StaffSummary staffSummary;
	StaffAllocation staffAllocation;
		
	public StaffView() {
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, staffList, staffTabPane());
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));

		add(splitPane, BorderLayout.CENTER);
		
	}
	
	public JPanel staffTabPane() {
		
		JPanel tabPanel= new JPanel();
		
		JTabbedPane mainTab = new JTabbedPane();
		staffSummary = new StaffSummary();
		staffAllocation = new StaffAllocation();
		
		tabPanel.setLayout(new BorderLayout());
		
		mainTab.addTab("Summary of Staff", staffSummary);
		mainTab.addTab("Staff Allocation", staffAllocation);
		mainTab.setPreferredSize(new Dimension(600, 400));
		
		tabPanel.add(mainTab, BorderLayout.CENTER);
		
		return tabPanel;
		
	}
	
	public void addStaff() {
		staffList.addStaff();
	}
	
	public void modifyStaff() {
		
	}
	
	public void addController(ActionListener controller) {

		staffList.addController(controller);
		staffSummary.addController(controller);
		staffAllocation.addController(controller);

	}
}
