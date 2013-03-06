package view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.*;

import util.DateTime;

import data.dataObject.ProjectDO;

public class SchedulingDialog extends JDialog {

    private JPanel jpnlTopContainer = new JPanel();
    private JPanel jpnlCenterContainer = new JPanel();
    private JPanel jpnlBottomContainer = new JPanel();

    private JLabel jlblStaringDate = new JLabel("Starting Date");
    //private JTextArea jtxtStartingDate = new JTextArea(DateTime.dataBaseDateFormat.format(new Date()));
    private JTextArea jtxtStartingDate = new JTextArea("2013-02-01 09:00:00");

    private JButton jbtnSchedule = new JButton("Schedule");
    private JButton jbtnCancel = new JButton("Cancel");

    private ArrayList<JCheckBox> jcbxProjects = new ArrayList<JCheckBox>();


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
        //jpnlCenterContainer.setPreferredSize(new Dimension(300, 300));

        add(jpnlTopContainer, BorderLayout.NORTH);
        add(jpnlCenterContainer, BorderLayout.CENTER);
        add(jpnlBottomContainer, BorderLayout.SOUTH);

        // dialog settings
        pack();
        setTitle("Scheduling projects");
        setLocationRelativeTo(parent);
    }


    public void initJcbxProjects(ArrayList<ProjectDO> projects) {

        // set size and layout 
        int projectNum = projects.size();
        int width = 300;
        int height = projectNum * 20;
        if(projectNum != 0)
        	jpnlCenterContainer.setLayout(new GridLayout(projectNum, 0));
        this.setSize(width, height + 50);

        for(ProjectDO project : projects) {
            JCheckBox jcbxProject = new JCheckBox(project.getProjectName());
            jcbxProject.setActionCommand("" + project.getProjectId());
            jcbxProject.setSelected(true);
            jpnlCenterContainer.add(jcbxProject);
            jcbxProjects.add(jcbxProject);
        }
        jpnlCenterContainer.updateUI();
    }

    public void addControllers(ActionListener listener) {
        jbtnSchedule.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
    }

    public ArrayList<Integer> getSelectedProjectIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for(JCheckBox jcbxProject : jcbxProjects) {
            if(jcbxProject.isSelected())
                ids.add(Integer.parseInt(jcbxProject.getActionCommand()));
        }

        return ids;
    }


    public String getStartingDateTime() {
        return jtxtStartingDate.getText();
    }



}
