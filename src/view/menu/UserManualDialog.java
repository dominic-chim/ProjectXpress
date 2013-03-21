package view.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import view.project.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * 
 * gui for user manual dialog 
 * 
 * @author Ross
 *
 */
public class UserManualDialog extends JDialog {

	//Containers
	private JPanel jpnlTopContainer = new JPanel();
	private JPanel jpnlCenterContainer = new JPanel();
	private JPanel jpnlBottomContainer = new JPanel();
	
	//Main Tab Panels
	private JPanel jpnlProject = new ProjectPanel();
	private JPanel jpnlStaff;
	private JPanel jpnlStatReports= new ProjectPanel();
	private JPanel jpnlScheduling = new ProjectPanel();
	private JPanel jpnlSkills = new ProjectPanel();

	//Buttons
    private JButton jbtnCancel = new JButton("Cancel");
    
    //Tab Pane
    private JTabbedPane jtbpMainTabPane = new JTabbedPane();
    
    
    //Constructor
	public UserManualDialog(JFrame parent) {
		
		super(parent, true);
		setLayout(new BorderLayout());
		
		//Create Panels for each Tab
		createStaffPanel();
		createProjectPanel();
		createStatReportsPanel();
		createSchedulingPanel();
		createSkillsPanel();

		//Add Panels to Tab Pane
		jtbpMainTabPane.addTab("Project", jpnlProject);
		jtbpMainTabPane.addTab("Staff", jpnlStaff);
		jtbpMainTabPane.addTab("Statisical Reports", jpnlStatReports);
		jtbpMainTabPane.addTab("Skills", jpnlSkills);
		jtbpMainTabPane.addTab("Schedule Projects", jpnlScheduling);

		//Add Tab Pane and Buttons to Containers
		jpnlTopContainer.add(jtbpMainTabPane);
		jpnlCenterContainer.add(jbtnCancel);

		// container settings
		// jpnlCenterContainer.setPreferredSize(new Dimension(300, 50));
		add(jpnlTopContainer, BorderLayout.NORTH);
		add(new JScrollPane(jpnlCenterContainer), BorderLayout.CENTER);
		add(jpnlBottomContainer, BorderLayout.SOUTH);
		
		// dialog settings
		pack();
		setTitle("User Manual");
		setLocationRelativeTo(parent);
		setVisible(true);

	}

	private void createSkillsPanel() {

		jpnlStaff = new JPanel(new BorderLayout());
		JPanel jpnlCreateStaff = new JPanel();
		JPanel jpnlModifyStaff = new JPanel();
		JPanel jpnlDeleteStaff = new JPanel();
		
		JTabbedPane jtbpStaff = new JTabbedPane();
				
		jtbpStaff.add("Create Staff", jpnlCreateStaff);
		jtbpStaff.add("Modify Staff", jpnlModifyStaff);
		jtbpStaff.add("Delete Staff", jpnlDeleteStaff);

		jpnlStaff.add(jtbpStaff, BorderLayout.NORTH);

		
	}

	private void createSchedulingPanel() {
		// TODO Auto-generated method stub
		
	}

	private void createStatReportsPanel() {
		// TODO Auto-generated method stub
		
	}

	private void createProjectPanel() {
		// TODO Auto-generated method stub
		
	}

	private void createStaffPanel() {
		// TODO Auto-generated method stub
		
	}

	public void addControllers(ActionListener controller) {

	}

}
