package view.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.*;

import util.DateTime;

import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;

/**
 *
 * gui for start a Scheduling
 *
 * @author Ke CHEN
 */
public class SchedulingDialog extends JDialog {

    private JPanel jpnlTopContainer = new JPanel();
    private JPanel jpnlBottomContainer = new JPanel();


    private JPanel jpnlCenterLeft = new JPanel();
    private JPanel jpnlCenterRight = new JPanel();
    private JSplitPane jspCenterContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpnlCenterLeft, jpnlCenterRight);

    private JLabel jlblStaringDate = new JLabel("Starting Date");
    private JTextArea jtxtStartingDate = new JTextArea(DateTime.dataBaseDateFormat.format(new Date()));

    private JButton jbtnSchedule = new JButton("Schedule");
    private JButton jbtnCancel = new JButton("Cancel");

    private ArrayList<JCheckBox> jcbxProjects = new ArrayList<JCheckBox>();
    private ArrayList<JCheckBox> jcbxStaffs = new ArrayList<JCheckBox>();


    public SchedulingDialog(JFrame parent) {

        // make parent disable when this dialog appear
        super(parent, true);

        setLayout(new BorderLayout());

        // button settings
        jbtnSchedule.setActionCommand("schedule");
        jbtnCancel.setActionCommand("cancel");

        // add stuff to top panel
        jpnlTopContainer.setLayout(new GridLayout(1, 0));
        jpnlTopContainer.add(jlblStaringDate);
        jpnlTopContainer.add(jtxtStartingDate);

        // add botton to container
        jpnlBottomContainer.add(jbtnSchedule);
        jpnlBottomContainer.add(jbtnCancel);

        // container settings
        jpnlCenterLeft.setBorder(BorderFactory.createTitledBorder("Select projects"));
        jpnlCenterRight.setBorder(BorderFactory.createTitledBorder("Select staffs"));
        jspCenterContainer.setResizeWeight(0.5);

        add(jpnlTopContainer, BorderLayout.NORTH);
        add(new JScrollPane(jspCenterContainer), BorderLayout.CENTER);
        add(jpnlBottomContainer, BorderLayout.SOUTH);

        // dialog settings
        pack();
        setTitle("Scheduling projects");
        setLocationRelativeTo(parent);
    }


    public void initJcbxProjectsAndStaffs(ArrayList<ProjectDO> projects, ArrayList<StaffDO> staffs) {

        // init projects
        int projectNum = projects.size();
        if(projectNum != 0)
            jpnlCenterLeft.setLayout(new GridLayout(projectNum, 0));

        for(ProjectDO project : projects) {
            JCheckBox jcbxProject = new JCheckBox(project.getProjectName());
            jcbxProject.setActionCommand("" + project.getProjectId());
            jcbxProject.setSelected(true);
            jpnlCenterLeft.add(jcbxProject);
            jcbxProjects.add(jcbxProject);
        }

        // init staffs
        int staffNum = staffs.size();
        if(staffNum != 0) {
            jpnlCenterRight.setLayout(new GridLayout(staffNum, 0));
        }
        for(StaffDO staff : staffs) {
            JCheckBox jcbxStaff = new JCheckBox(staff.getStaffName());
            jcbxStaff.setActionCommand("" + staff.getStaffId());
            jcbxStaff.setSelected(true);
            jpnlCenterRight.add(jcbxStaff);
            jcbxStaffs.add(jcbxStaff);
        }


        this.pack();
        jpnlCenterLeft.updateUI();
        jpnlCenterRight.updateUI();
    }

    public void addControllers(ActionListener listener) {
        jbtnSchedule.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
    }

    /**
     * get selected projects
     */
    public ArrayList<Integer> getSelectedProjectIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for(JCheckBox jcbxProject : jcbxProjects) {
            if(jcbxProject.isSelected())
                ids.add(Integer.parseInt(jcbxProject.getActionCommand()));
        }

        return ids;
    }
    
    /**
     * get selected staffs
     */
    public ArrayList<Integer> getSelectedStaffIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for(JCheckBox jcbxStaff : jcbxStaffs) {
            if(jcbxStaff.isSelected())
                ids.add(Integer.parseInt(jcbxStaff.getActionCommand()));
        }

        return ids;
    }


    public String getStartingDateTime() {
        return jtxtStartingDate.getText();
    }



}
