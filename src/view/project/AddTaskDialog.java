package view.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class AddTaskDialog extends JDialog {


    // container panels
    private JPanel jpnlTop = new JPanel();
    private JPanel jpnlMiddle = new JPanel();
    private JPanel jpnlBottom = new JPanel();

    // labels
    private JLabel jlbTaskName = new JLabel("Task name");
    private JLabel jlbRequiredSkill = new JLabel("Required skill");
    private JLabel jlbDuration = new JLabel("Duration");
    private JLabel jlbRiskLevel = new JLabel("Risk level");
    private JLabel jlbReleaseTime = new JLabel("Release time");
    private JLabel jlbStatus = new JLabel("Status");

    // texts
    private JTextArea jtxtTaskName = new JTextArea();
    private JTextArea jtxtRequiredSkill = new JTextArea();
    private JTextArea jtxtDuration = new JTextArea();
    private JTextArea jtxtReleaseTime = new JTextArea("0000-00-00 00:00:00");

    // TODO change it to the ones in database
    private JComboBox jcbRiskLevel = new JComboBox(
            new String[]{"Low", "Middle", "High"});
    private JComboBox jcbStatus = new JComboBox(
            new String[]{"Started", "Not Started", "Completed"});

    // jlist in center
    // TODO add real data from model
    private JList requiredTaskList = new JList();

    // buttons
    private JButton jbtnAddRequirement = new JButton("Add Required Task");
    private JButton jbtnCancel = new JButton("Cancel");
    private JButton jbtnFinish = new JButton("Finish");


    public AddTaskDialog(AddProjectDialog parentDialog) {

        super(parentDialog, true);

        setLayout(new BorderLayout());

        // adding things to top panel
        jpnlTop.setLayout(new GridLayout(6, 2));
        jpnlTop.add(jlbTaskName);
        jpnlTop.add(jtxtTaskName);
        jpnlTop.add(jlbRequiredSkill);
        jpnlTop.add(jtxtRequiredSkill);
        jpnlTop.add(jlbDuration);
        jpnlTop.add(jtxtDuration);
        jpnlTop.add(jlbRiskLevel);
        jpnlTop.add(jcbRiskLevel);
        jpnlTop.add(jlbReleaseTime);
        jpnlTop.add(jtxtReleaseTime);
        jpnlTop.add(jlbStatus);
        jpnlTop.add(jcbStatus);

        // add a jlist in CENTER
        add(requiredTaskList, BorderLayout.CENTER);
        
        // add buttons to bottom
        jbtnAddRequirement.setActionCommand("add");
        jbtnCancel.setActionCommand("cancel");
        jbtnFinish.setActionCommand("finish");

        jpnlBottom.setLayout(new GridLayout(0, 3));
        jpnlBottom.add(jbtnAddRequirement);
        jpnlBottom.add(jbtnCancel);
        jpnlBottom.add(jbtnFinish);


        // add panels to the dialog window
        add(jpnlTop, BorderLayout.NORTH);
        add(jpnlMiddle, BorderLayout.CENTER);
        add(jpnlBottom, BorderLayout.SOUTH);

        // dialog settings
        setTitle("Add task");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
