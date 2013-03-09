package view.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import view.MainFrame;

import data.dataObject.ProjectDO;

public class ProjectManageDialog extends JDialog{

    // container panels
    private JPanel jpnlTop = new JPanel();
    private JPanel jpnlBottom = new JPanel();

    // labels 
    private JLabel jlbProjectName = new JLabel("Project name");
    private JLabel jlbDueDate = new JLabel("Due date");
    private JLabel jlbPriority = new JLabel("Priority");
    private JLabel jlbStatus = new JLabel("Status");

    // text areas
    private JTextArea jtxtProjectName = new JTextArea();
    private JTextArea jtxtDueDate = new JTextArea();
    private JTextArea jtxtPriority = new JTextArea();

    // combobox for status
    private JComboBox<String> jcbStatus = new JComboBox<String>(
            new String[]{"Started", "Not Started", "Completed"});

    
    // buttons
    private JButton jbtnUpdate = new JButton("Update");
    private JButton jbtnCancel = new JButton("Cancel");

    public ProjectManageDialog(MainFrame view, ProjectDO project) {

        super(view, true);

        setLayout(new BorderLayout());

        // set values
        jtxtProjectName.setText(project.getProjectName());
        jtxtDueDate.setText(project.getProjectDueDate().getDateTime());
        jtxtPriority.setText(project.getProjectPriority() + "");
        jcbStatus.setSelectedItem(project.getProjectStatus());

        // add contents to top panel
        jpnlTop.setLayout(new GridLayout(4, 2));
        jpnlTop.add(jlbProjectName);
        jpnlTop.add(jtxtProjectName);
        jpnlTop.add(jlbDueDate);
        jpnlTop.add(jtxtDueDate);
        jpnlTop.add(jlbPriority);
        jpnlTop.add(jtxtPriority);
        jpnlTop.add(jlbStatus);
        jpnlTop.add(jcbStatus);

        // add contents to bottom panel
        jbtnUpdate.setActionCommand("update");
        jbtnCancel.setActionCommand("cancel");

        jpnlBottom.add(jbtnUpdate);
        jpnlBottom.add(jbtnCancel);
        
        // add panels to dialog window
        add(jpnlTop, BorderLayout.NORTH);
        add(jpnlBottom, BorderLayout.SOUTH);

        // dialog settings
        setTitle("Manage project");
        //setSize(400, 600);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
