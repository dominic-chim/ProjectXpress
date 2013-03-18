package view.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import data.Context;
import data.dataObject.TaskDO;

import view.MainFrame;

public class TaskManageDialog extends JDialog {

    // container panels
    private JPanel jpnlTop = new JPanel();
    private JPanel jpnlBottom = new JPanel();

    // labels
    private JLabel jlbTaskName = new JLabel("Task name");
    private JLabel jlbRequiredSkill = new JLabel("Required skill");
    private JLabel jlbDuration = new JLabel("Duration");
    private JLabel jlbRiskLevel = new JLabel("Risk level");
    private JLabel jlbReleaseTime = new JLabel("Release time");
    private JLabel jlbStatus = new JLabel("Status");
    private JLabel jlbRemainingTime = new JLabel("Remaining Time");

    // texts
    private JTextArea jtxtTaskName = new JTextArea();
    private JTextArea jtxtDuration = new JTextArea();
    private JTextArea jtxtReleaseTime = new JTextArea();
    private JTextArea jtxtRemainingTime = new JTextArea();

    private JComboBox<String> jcbRiskLevel = new JComboBox<String>();
    private JComboBox<String> jcbStatus = new JComboBox<String>(
            new String[]{"Started", "Not Started", "Completed"});
    private JComboBox<String> jcbRequiredSkill = new JComboBox<String>();


    // buttons
    private JButton jbtnUpdate = new JButton("Update");
    private JButton jbtnCancel = new JButton("Cancel");


    // model
    private TaskDO task;


    public TaskManageDialog(MainFrame view, TaskDO task) {

        super(view, true);

        setLayout(new BorderLayout());
        this.task = task;

        // set values
        jtxtTaskName.setText(task.getTaskName());
        jtxtDuration.setText(task.getTaskOriginalDuration() + "");
        jtxtReleaseTime.setText(task.getTaskReleaseTime().getDateTime());
        jtxtRemainingTime.setText(task.getTaskRemainingTime() + "");
        jcbStatus.setSelectedItem(task.getTaskStatus());

        //jcbRiskLevel.setSelectedItem(task.getTaskRistLevel());
        //jcbRequiredSkill.setSelectedItem(Context.getSkillMap().get(task.getTaskRequiredSkill()));

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
        //jpnlTop.add(jlbReleaseTime);
        //jpnlTop.add(jtxtReleaseTime);
        jpnlTop.add(jlbStatus);
        jpnlTop.add(jcbStatus);
        jpnlTop.add(jlbRemainingTime);
        jpnlTop.add(jtxtRemainingTime);

        
        // add buttons to bottom
        jbtnUpdate.setActionCommand("update");
        jbtnCancel.setActionCommand("cancel");

        jpnlBottom.add(jbtnUpdate);
        jpnlBottom.add(jbtnCancel);


        // add panels to the dialog window
        add(jpnlTop, BorderLayout.NORTH);
        add(jpnlBottom, BorderLayout.SOUTH);

        // dialog settings
        setTitle("Manage Task");
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void setSkillNames(String[] skillNames) {
        jcbRequiredSkill.removeAllItems();
        for(String skillName : skillNames) {
            jcbRequiredSkill.addItem(skillName);
        }
        jcbRequiredSkill.setSelectedItem(Context.getSkillMap().get(task.getTaskRequiredSkill()));
    }

    public void setRiskLevels(String[] riskLevels) {
        jcbRiskLevel.removeAllItems();
        for(String riskLevel : riskLevels) {
            jcbRiskLevel.addItem(riskLevel);
        }
        jcbRiskLevel.setSelectedItem(task.getTaskRiskLevel());

    }

    public void addController(ActionListener listener) {

        jbtnUpdate.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
    }

    public HashMap<String, String> getAllInputValue() {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("task_name", jtxtTaskName.getText());
        values.put("required_skill", jcbRequiredSkill.getSelectedItem().toString());
        values.put("duration", jtxtDuration.getText());
        values.put("risk_level", jcbRiskLevel.getSelectedItem().toString());
        values.put("release_time", jtxtReleaseTime.getText());
        values.put("status", jcbStatus.getSelectedItem().toString());
        values.put("remaining_time", jtxtRemainingTime.getText());
        return values;
    }

         
}
