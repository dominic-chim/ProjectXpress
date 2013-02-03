package view.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class AddProjectDialog extends JDialog {


    // container panels
    private JPanel jpnlTop = new JPanel();
    private JPanel jpnlMiddle = new JPanel();
    private JPanel jpnlBottom = new JPanel();

    // labels 
    private JLabel jlbProjectName = new JLabel("Project name");
    private JLabel jlbDueDate = new JLabel("Due date");
    private JLabel jlbPriority = new JLabel("Priority");
    private JLabel jlbStatus = new JLabel("Status");

    // text areas
    private JTextArea jtxtProjectName = new JTextArea();
    private JTextArea jtxtDueDate = new JTextArea("2013-02-02 00:00:00");
    private JTextArea jtxtPriority = new JTextArea();

    // combobox for status
    private JComboBox jcbStatus = new JComboBox(
            new String[]{"Started", "Not Started", "Completed"});


    // buttons
    private JButton jbtnAddTask = new JButton("Add task");
    private JButton jbtnCancel = new JButton("Cancel");
    private JButton jbtnFinish = new JButton("Finish");

    public AddProjectDialog() {

        setLayout(new BorderLayout());

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

        // TODO add contents to middle panel


        // add contents to bottom panel
        jpnlBottom.setLayout(new GridLayout(0, 3));
        jpnlBottom.add(jbtnAddTask);
        jpnlBottom.add(jbtnCancel);
        jpnlBottom.add(jbtnFinish);
        
        // add panels to dialog window
        add(jpnlTop, BorderLayout.NORTH);
        add(jpnlMiddle, BorderLayout.CENTER);
        add(jpnlBottom, BorderLayout.SOUTH);

        // dialog settings
        setTitle("Add project");
        setSize(400, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
