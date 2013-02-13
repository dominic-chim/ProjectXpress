package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import data.Context;
import data.dataObject.*;
import database.dataAccessObject.ProjectDao;

import util.DateTime;
import view.MainFrame;
import view.project.*;

/**
 * controllers for project panel
 *
 */
public class ProjectController {

    private MainFrame view;
    private ProjectPanel projectPanel;
    private AddProjectDialog jdlogAddProject;
    private AddTaskDialog jdlogAddTask;

    // models 
    private ProjectDO projectModel = new ProjectDO();
    //private TaskDO taskModel = new TaskDO();

    public ProjectController(MainFrame view) {
        this.view = view;
        this.projectPanel = view.getProjectPanel();

        projectPanel.getProjectList().addController(new ProjectListBtnListener());
    }

    /** 
     * ActionListeners for all buttons in ProjectList
     */
    class ProjectListBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "delete":
                    break;
                case "modify":
                    break;
                case "add":
                    projectModel = new ProjectDO();
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


    /** 
     * ActionListener for all buttons in AddProjectDialogBtnListener
     */
    class AddProjectDialogBtnListener implements ActionListener {

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
                    HashMap<String, String> valuesMap = jdlogAddProject.getAllInputValue();
                    String projectName = valuesMap.get("project_name");
                    DateTime projectDueDate = new DateTime(valuesMap.get("due_date"));
                    int priority = Integer.parseInt(valuesMap.get("priority"));
                    String status = valuesMap.get("status");

                    projectModel.setProjectName(projectName);
                    projectModel.setProjectDueDate(projectDueDate);
                    projectModel.setProjectPriority(priority);
                    projectModel.setProjectStatus(status);

                    // adding projectModel to db;
                    ProjectDao projectDao = new ProjectDao();
                    projectDao.addProject(projectModel);

                    // close window
                    jdlogAddProject.dispose();
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * ActionListeners for all buttons in 
     */
    class AddTaskDialogBtnListener implements ActionListener {

        private TaskDO taskModel = new TaskDO();

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add": // add a Required task
                {
                    // TODO test it
                    String taskName = jdlogAddTask.showAddReqiredTaskDialog(getTaskNames());
                    ArrayList<TaskDO> tasks = projectModel.getTasks();
                    int taskId = 0;
                    for(int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).getTaskName().equals(taskName)) 
                            taskId = i + 1;
                    }

                    System.out.println(taskId);
                    taskModel.addReqiredTask(taskId);
                }
                    break;
                case "cancel":
                    jdlogAddTask.dispose();
                    break;
                case "finish":
                    // get task info from user input
                    HashMap<String, String> valuesMap = jdlogAddTask.getAllInputValue();
                    String taskName = valuesMap.get("task_name");
                    int requiredSkillId = Context.getSkillMap().get(valuesMap.get("required_skill"));
                    int taskDuration = Integer.parseInt(valuesMap.get("duration"));
                    String taskRistLevel = valuesMap.get("risk_level");
                    DateTime taskReleaseTime = new DateTime(valuesMap.get("release_time"));
                    String status = valuesMap.get("status");

                    // add task info into a taskDO
                    taskModel.setTaskName(taskName);
                    taskModel.setTaskRequiredSkill(requiredSkillId);
                    taskModel.setTaskDuration(taskDuration);
                    taskModel.setTaskRistLevel(taskRistLevel);
                    taskModel.setTaskReleaseTime(taskReleaseTime);
                    taskModel.setTaskStatus(status);

                    // add this task to projectDO
                    projectModel.addTask(taskModel);

                    // close window
                    jdlogAddTask.dispose();

                    // refresh add_project panel
                    jdlogAddProject.reloadList(getTaskNames());
                    break;

                default:
                    break;
            }

        }

    }

    /**
     * get all tasknames from current project
     *
     * @return list of string contains all tasknames
     */
    private String[] getTaskNames() {

        ArrayList<TaskDO> tasks = projectModel.getTasks();
        String[] names = new String[tasks.size()];
        for(int i = 0; i < tasks.size(); i++) {
            names[i] = tasks.get(i).getTaskName();
        }
        return names;
    }
}
