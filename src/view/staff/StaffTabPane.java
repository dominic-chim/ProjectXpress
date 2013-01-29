package view.staff;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class StaffTabPane extends JPanel {

	JTabbedPane mainTab = new JTabbedPane();
	StaffSummary staffSummary = new StaffSummary();
	StaffAllocation staffAllocation = new StaffAllocation();
	
	public StaffTabPane() {
				
		setLayout(new BorderLayout());
		
		mainTab.addTab("Summary of Staff", staffSummary);
		mainTab.addTab("Staff Allocation", staffAllocation);
		mainTab.setPreferredSize(new Dimension(600, 400));
		
		add(mainTab, BorderLayout.CENTER);
		
	}

}
