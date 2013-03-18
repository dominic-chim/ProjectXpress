package view.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import view.MainFrame;

public class AddProjectDialog extends JDialog {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1757481797240629572L;
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
    private JComboBox<String> jcbStatus = new JComboBox<String>(
            new String[]{"Started", "Not Started", "Completed"});

    // jlist to show tasks
    private DefaultListModel<String> taskListModel = new DefaultListModel<String>();
    private JList<String> taskList = new JList<String>(taskListModel);

    
    // buttons
    private JButton jbtnAddTask = new JButton("Add task");
    private JButton jbtnCancel = new JButton("Cancel");
    private JButton jbtnFinish = new JButton("Finish");

    public AddProjectDialog(MainFrame view) {

        super(view, true);

        setLayout(new BorderLayout());

        // add contents to top panel
        jpnlTop.setLayout(new GridLayout(3, 2));
        jpnlTop.add(jlbProjectName);
        jpnlTop.add(jtxtProjectName);
        //jpnlTop.add(jlbDueDate);
        //jpnlTop.add(jtxtDueDate);
        jpnlTop.add(jlbPriority);
        jpnlTop.add(jtxtPriority);
        jpnlTop.add(jlbStatus);
        jpnlTop.add(jcbStatus);

        // add task list to middle panel
        jpnlMiddle.setLayout(new BorderLayout());
        jpnlMiddle.add(taskList, BorderLayout.CENTER);

        // add contents to bottom panel
        jpnlBottom.setLayout(new GridLayout(0, 3));

        jbtnAddTask.setActionCommand("add");
        jbtnCancel.setActionCommand("cancel");
        jbtnFinish.setActionCommand("finish");

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

    public void addController(ActionListener listener) {
        jbtnAddTask.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
        jbtnFinish.addActionListener(listener);
    }

    public void reloadList(String[] taskNames) {
        taskListModel.removeAllElements();
        for(String taskName : taskNames) {
            taskListModel.addElement(taskName);
        }
    }

    public HashMap<String, String> getAllInputValue() {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("project_name", jtxtProjectName.getText());
        values.put("due_date", jtxtDueDate.getText());
        values.put("priority", jtxtPriority.getText());
        values.put("status", jcbStatus.getSelectedItem().toString());
        return values;
    }
}
