package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import data.Skills;
import data.dataObject.*;

import view.MainFrame;
import view.project.*;

//import static view.project.AddTaskDialog.*;

public class ProjectController {

    private MainFrame view;
    private ProjectPanel projectPanel;
    private AddProjectDialog jdlogAddProject;
    private AddTaskDialog jdlogAddTask;

    // models 
    private ProjectDO projectModel = new ProjectDO();
    private TaskDO taskModel = new TaskDO();

    public ProjectController(MainFrame view) {
        this.view = view;
        this.projectPanel = view.getProjectPanel();

        projectPanel.getProjectList().addController(new ProjectListBtnListener());
    }

    // ActionListeners for all buttons in ProjectList
    class ProjectListBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "delete":
                    break;
                case "modify":
                    break;
                case "add":
                    // construct view of adding a project
                    jdlogAddProject = new AddProjectDialog(view);

                    jdlogAddProject.addController(new AddProjectDialogBtnListener());
                    jdlogAddProject.setVisible(true);

                    break;
                default:
                    break;
            }
        }
        
    }


    // ActionListener for all buttons in AddProjectDialog
    class AddProjectDialogBtnListener implements ActionListener {

        /*
        private AddProjectDialog parentDialog; 

        public AddProjectDialogBtnListener(AddProjectDialog parentDialog) {
            this.parentDialog = parentDialog;
        }
        */

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add":
                    // construct the view of adding a task
                    jdlogAddTask = new AddTaskDialog(jdlogAddProject);

                    jdlogAddTask.addController(new AddTaskDialogBtnListener());
                    jdlogAddTask.setVisible(true);
                    break;
                case "cancel":
                    jdlogAddProject.dispose();
                    break;
                case "finish":
                    jdlogAddProject.dispose();
                    break;
                default:
                    break;
            }

        }
    }

    // TODO check this and finish it 
    // ActionListeners for all buttons in 
    class AddTaskDialogBtnListener implements ActionListener {

        /*
        private AddTaskDialog parentDialog;

        public AddTaskDialogBtnListener(AddTaskDialog parentDialog) {
            this.parentDialog = parentDialog;
        }
        */

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add": // add a Required task
                    //TODO solve problem when adding new TaskDO
                    jdlogAddTask.showAddReqiredTaskDialog(getTaskNames());
                    break;
                case "cancel":
                    jdlogAddTask.dispose();
                    break;
                case "finish":
                    taskModel = new TaskDO();

                    HashMap<String, String> valuesMap = jdlogAddTask.getAllInputValue();
                    String taskName = valuesMap.get("task_name");
                    int requiredSkillId = Skills.SKILLS.get(valuesMap.get("required_skill"));
                    int taskDuration = Integer.parseInt(valuesMap.get("duration"));
                    String taskRistLevel = valuesMap.get("risk_level");
                    String status = valuesMap.get("status");




                    taskModel.setTaskName(taskName);
                    taskModel.setTaskRequiredSkill(requiredSkillId);
                    taskModel.setTaskDuration(taskDuration);
                    taskModel.setTaskRistLevel(taskRistLevel);
                    //TODO add time
                    taskModel.setTaskStatus(status);


                    projectModel.addTask(taskModel);
                    jdlogAddTask.dispose();

                    jdlogAddProject.reloadList(getTaskNames());
                    break;
                default:
                    break;
            }

        }

    }

    private String[] getTaskNames() {

        ArrayList<TaskDO> tasks = projectModel.getTasks();
        String[] names = new String[tasks.size()];
        for(int i = 0; i < tasks.size(); i++) {
            names[i] = tasks.get(i).getTaskName();
        }

        return names;

    }

}
