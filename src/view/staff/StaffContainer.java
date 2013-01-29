package view.staff;



import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class StaffContainer extends JPanel {

	// Left panel - Staff Summary
	StaffList staffList= new StaffList();
	StaffTabPane staffTabPane = new StaffTabPane();
		
	public StaffContainer() {
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, staffList, staffTabPane);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));

		add(splitPane, BorderLayout.CENTER);
	}
	
}
