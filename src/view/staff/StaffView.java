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
    //private StaffSummary staffSummary;
    //private StaffAllocation staffAllocation;
    private StaffList staffList;

    private JTabbedPane mainTab = new JTabbedPane();
    MainFrame view;
    private StaffSummary staffSummary = new StaffSummary();

    public StaffView(MainFrame view) {
    	this.view = view;
        staffList = new StaffList(view);
        

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                staffList, staffTabPane());

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600));

        add(splitPane, BorderLayout.CENTER);

    }

    public JPanel staffTabPane() {

        JPanel tabPanel = new JPanel();

       
        StaffAllocation staffAllocation = new StaffAllocation();

        JScrollPane staffSummaryScrollPane = new JScrollPane(this.staffSummary);

        
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
    
    public StaffSummary getStaffSummary(){
    	
		return staffSummary;
    }


    /**
     * @return the mainTab
     */
    public JTabbedPane getMainTab() {
        return mainTab;
    }

    public void addController(ActionListener controller) {

        staffList.addController(controller);
        //staffSummary.addController(controller);
        //staffAllocation.addController(controller);

    }

}
