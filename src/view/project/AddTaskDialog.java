package view.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class AddTaskDialog extends JDialog {

    // constant represent a component
    /*
    public static final int TASK_NAME = 0;
    public static final int REQUIRED_SKILL = 1;
    public static final int DURATION = 2;
    public static final int RISK_LEVEL = 3;
    public static final int RELEASE_TIME = 4;
    public static final int STATUS = 5;
    */


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
    //private JTextArea jtxtRequiredSkill = new JTextArea();
    private JTextArea jtxtDuration = new JTextArea();
    private JTextArea jtxtReleaseTime = new JTextArea("0000-00-00 00:00:00");

    // TODO change it to the ones in database
    private JComboBox<String> jcbRiskLevel = new JComboBox<String>(
            new String[]{"Low", "Middle", "High"});
    private JComboBox<String> jcbStatus = new JComboBox<String>(
            new String[]{"Started", "Not Started", "Completed"});
    private JComboBox<String> jcbRequiredSkill = new JComboBox<String>(new String[]{"skill1", "skill2", "skill3"});

    // jlist in center
    // TODO add real data from model
    private JList<String> requiredTaskList = new JList<String>(new String[]{"Test1", "Test2"});

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
        jpnlTop.add(jcbRequiredSkill);
        jpnlTop.add(jlbDuration);
        jpnlTop.add(jtxtDuration);
        jpnlTop.add(jlbRiskLevel);
        jpnlTop.add(jcbRiskLevel);
        jpnlTop.add(jlbReleaseTime);
        jpnlTop.add(jtxtReleaseTime);
        jpnlTop.add(jlbStatus);
        jpnlTop.add(jcbStatus);

        // add a jlist in CENTER
        jpnlMiddle.setLayout(new BorderLayout());
        jpnlMiddle.add(requiredTaskList, BorderLayout.CENTER);
        
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

    public void addController(ActionListener listener) {
        jbtnAddRequirement.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
        jbtnFinish.addActionListener(listener);
    }

    public String showAddReqiredTaskDialog(Object[] tasks) {
        String result = (String)JOptionPane.showInputDialog(
                            this,
                            "Select Task:",
                            "Required Task",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            tasks,
                            "ham");
        return result;
    }


    /*
    public String getInputValue(final int componentId) {
        switch(componentId) {
            case TASK_NAME:
                return jtxtTaskName.getText();
            case REQUIRED_SKILL:
                return jcbRequiredSkill.getSelectedItem().toString();
            case DURATION:
                return jtxtDuration.getText();
            case RISK_LEVEL:
                return jcbRiskLevel.getSelectedItem().toString();
            case RELEASE_TIME:
                return jtxtReleaseTime.getText();
            case STATUS:
                return jcbStatus.getSelectedItem().toString();
            default: 
                return "";
        }

    }
    */

    public HashMap<String, String> getAllInputValue() {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("task_name", jtxtTaskName.getText());
        values.put("required_skill", jcbRequiredSkill.getSelectedItem().toString());
        values.put("duration", jtxtDuration.getText());
        values.put("risk_level", jcbRiskLevel.getSelectedItem().toString());
        values.put("release_time", jtxtReleaseTime.getText());
        values.put("status", jcbStatus.getSelectedItem().toString());
        return values;
    }

}
