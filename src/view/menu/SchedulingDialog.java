package view.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import data.dataObject.ProjectDO;

public class SchedulingDialog extends JDialog {

    private JPanel jpnlTopContainer = new JPanel();
    private JPanel jpnlBottomContainer = new JPanel();

    private JButton jbtnSchedule = new JButton("Schedule");
    private JButton jbtnCancel = new JButton("Cancel");

    public SchedulingDialog(JFrame parent) {
        super(parent, true);

        setLayout(new BorderLayout());

        jbtnSchedule.setActionCommand("schedule");
        jbtnCancel.setActionCommand("cancel");

        jpnlBottomContainer.add(jbtnSchedule);
        jpnlBottomContainer.add(jbtnCancel);

        add(jpnlTopContainer, BorderLayout.CENTER);
        add(jpnlBottomContainer, BorderLayout.SOUTH);

        pack();
        setTitle("Scheduling projects");
        setLocationRelativeTo(parent);
    }


    public void initJcbxProjects(ArrayList<ProjectDO> projects) {
        for(ProjectDO project : projects) {
            JCheckBox jcbxProject = new JCheckBox(project.getProjectName());
            jcbxProject.setActionCommand("" + project.getProjectId());
            add(jcbxProject);

        }
    }

    public void addControllers(ActionListener listener) {
        jbtnSchedule.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
    }

    // TODO remove this test code
    public void tt() {
        jpnlTopContainer.add(new JCheckBox("hello"));
        jpnlTopContainer.updateUI();
    }



}
