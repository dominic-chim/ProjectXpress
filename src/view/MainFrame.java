 package view;

import java.awt.BorderLayout;

import javax.swing.*;

import controllers.menu.SkillController;

import view.project.*;
import view.staff.*;
import view.statistic.*;
import view.menu.*;


/**
 * 
 * main frame of the program
 * contains a menu bar and a tabbedpane 
 * 
 * the tabbed pane is used to hold tab: project, staff, statistical report
 * 
 * this class also holds references to tabs project, staff and statistical report(maybe not useful)
 * 
 * 
 */
public class MainFrame extends JFrame {
	
	// the main menu bar
	private MainMenuBar menuBar = new MainMenuBar();
	
	// the container contains all tabs
	private JTabbedPane mainTabbedPane = new  JTabbedPane();
	
	// component in mainTabbedPane
	private ProjectPanel projectPanel = new ProjectPanel();
	private StaffView staffPanel = new StaffView(this);
	private StatisticPanel statisticPanel = new StatisticPanel();
	
	SkillDialog skillDialog;
	
	public MainFrame() {
		
		setLayout(new BorderLayout());
		
		add(menuBar, BorderLayout.NORTH);
		
		// add tabs to tabbed pane
		mainTabbedPane.addTab("Project", projectPanel);
		mainTabbedPane.addTab("Staff", staffPanel);
		mainTabbedPane.addTab("Statistical Reports", statisticPanel);
		
		add(mainTabbedPane, BorderLayout.CENTER);
		
		setSize(600, 600);
        setTitle("Project Express");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void addSkillDialog(SkillController skillController) {
		
		this.skillDialog = new SkillDialog(this, skillController);
		skillDialog.createSkillDialog();

	}
	
	public SchedulingDialog addSchedulingDialog() {
		return  new SchedulingDialog(this); 
	}
	
	public UserManualDialog addUserManualDialog() {
		return  new UserManualDialog(this); 
	}
	
	public SkillDialog getSkillDialog() {
		
		return this.skillDialog;
	}

	public StaffView getStaffView() {
		return staffPanel;
	}
	
    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }
    
    public MainMenuBar getMainMenuBar() {
    	return menuBar;
    }


    public JTabbedPane getTabbedPane() {
        return mainTabbedPane;
    }

    public void refresh() {
        // refresh statistic panel
//        mainTabbedPane.remove(statisticPanel);
//        statisticPanel = new StatisticPanel();
//		mainTabbedPane.addTab("Statistical Reports", statisticPanel);

        // refresh project panel
        JTabbedPane projectTabs = projectPanel.getRightTabs();
        projectTabs.removeAll();
        projectTabs.addTab("Summary of Projects", new ProjectSummary());
        projectTabs.addTab("Project Allocation", new ProjectAllocation());
        
        // refresh staff panel
        JTabbedPane staffTabs = staffPanel.getMainTab();
        staffTabs.removeAll();
        StaffSummary staffSummary = new StaffSummary();
        StaffAllocation staffAllocation = new StaffAllocation();
        JScrollPane staffSummaryScrollPane = new JScrollPane(staffSummary);
        staffTabs.addTab("Summary of Staff", staffSummaryScrollPane);
        staffTabs.addTab("Staff Allocation", staffAllocation);
    }

}
