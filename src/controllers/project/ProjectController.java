package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import data.Context;
import data.dataObject.*;
import database.dataAccessObject.*;

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

    private ProjectList jpnlprojectList;

    // models 
    private ProjectDO projectModel = new ProjectDO();

    public ProjectController(MainFrame view) {
        this.view = view;
        this.projectPanel = view.getProjectPanel();
        jpnlprojectList = projectPanel.getProjectList();

        // add controller to buttons in project list
        jpnlprojectList.addController(new ProjectListBtnListener());

        // getAllProject from database and list them in the projectList
        ProjectDao projectDao = new ProjectDao();

        ArrayList<ProjectDO> projects = projectDao.getAllStartedProject();
        for(ProjectDO project : projects) {
            jpnlprojectList.addProjectNode(project);
        }
    }

    class ProjectListBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "delete":
                    Object delObj = jpnlprojectList.getSelectedObjectInTree();
                    if(delObj instanceof ProjectDO) {
                        ProjectDO project = (ProjectDO)delObj;
                        (new ProjectDao()).deleteProject(project);
                        updateProjectList();
                    }
                    break;
                case "modify":
                    Object obj = jpnlprojectList.getSelectedObjectInTree();
                    if(obj instanceof ProjectDO) {
                        ProjectManageDialog jdlogProjectManage = new ProjectManageDialog(view, (ProjectDO)obj);
                        jdlogProjectManage.addController(new ManageProjectDialogBtnListener((ProjectDO)obj, jdlogProjectManage));
                        jdlogProjectManage.setVisible(true);
                    } else if(obj instanceof TaskDO) {
                        TaskManageDialog jdlogTaskManage = new TaskManageDialog(view, (TaskDO)obj);
                        jdlogTaskManage.setSkillNames((new SkillDao()).getSkillNames());
                        jdlogTaskManage.setRiskLevels((new RiskDao()).getRiskNames());
                        jdlogTaskManage.addController(new ManageTaskDialogBtnListener((TaskDO)obj, jdlogTaskManage));
                        jdlogTaskManage.setVisible(true);
                    }
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

    /* listeners for manage dialogs */

    class ManageProjectDialogBtnListener implements ActionListener {

        private ProjectDO project;
        private ProjectManageDialog dialog; 

        public ManageProjectDialogBtnListener(ProjectDO project, ProjectManageDialog dialog) {
            this.project = project;
            this.dialog = dialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "update":

                    HashMap<String, String> valuesMap = dialog.getAllInputValue();
                    String projectName = valuesMap.get("project_name");
                    DateTime projectDueDate = new DateTime(valuesMap.get("due_date"));
                    int priority = Integer.parseInt(valuesMap.get("priority"));
                    String status = valuesMap.get("status");

                    project.setProjectName(projectName);
                    project.setProjectDueDate(projectDueDate);
                    project.setProjectPriority(priority);
                    project.setProjectStatus(status);

                    (new ProjectDao()).updateProjectInfo(project);

                    updateProjectList();
                    dialog.dispose();
                    break;
                case "cancel":
                    dialog.dispose();
                    break;
                default:
                    break;
            }

        }
    }


    class ManageTaskDialogBtnListener implements ActionListener {

        private TaskDO task;
        private TaskManageDialog dialog; 

        public ManageTaskDialogBtnListener(TaskDO task, TaskManageDialog dialog) {
            this.task = task;
            this.dialog = dialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "update":

                    HashMap<String, String> valuesMap = dialog.getAllInputValue();
                    setTaskInfo(valuesMap, task);

                    int remainingTime = Integer.parseInt(valuesMap.get("remaining_time"));

                    task.setTaskRemainingTime(remainingTime);

                    (new TaskDao()).updateTaskInfo(task);

                    updateProjectList();
                    dialog.dispose();
                    break;
                case "cancel":
                    dialog.dispose();
                    break;
                default:
                    break;
            }

        }
    }



    /** 
     * ActionListener for all buttons in AddProjectDialog
     */
    class AddProjectDialogBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add":
                    // construct the view of adding a task
                    jdlogAddTask = new AddTaskDialog(jdlogAddProject);

                    jdlogAddTask.addController(new AddTaskDialogBtnListener());
                    jdlogAddTask.setSkillNames((new SkillDao()).getSkillNames());
                    jdlogAddTask.setRiskLevels((new RiskDao()).getRiskNames());
                    jdlogAddTask.setVisible(true);
                    break;
                case "cancel":
                    jdlogAddProject.dispose();
                    break;
                case "finish":
                    try {
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

                        updateProjectList();

                        // close window
                        jdlogAddProject.dispose();
                    } catch (Exception excp) {
                        String ErrMsg = "Invalid input: " + excp.getMessage();
                        JOptionPane.showMessageDialog(jdlogAddProject, 
                                ErrMsg, "Invalid Input", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * ActionListeners for all buttons in AddTaskDialog
     */
    class AddTaskDialogBtnListener implements ActionListener {

        private TaskDO taskModel = new TaskDO();

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add": // add a Required task
                {
                    String taskName = jdlogAddTask.showAddReqiredTaskDialog(getTaskNames());
                    ArrayList<TaskDO> tasks = projectModel.getTasks();
                    int taskId = 0;
                    for(int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).getTaskName().equals(taskName)) 
                            taskId = i + 1;
                    }

                    if(taskId != 0)
                        taskModel.addReqiredTask(taskId);

                    ArrayList<Integer> requiredTaskIds = taskModel.getRequiredTaskIds();
                    String[] requiredTasks = new String[requiredTaskIds.size()];
                    for(int i = 0; i < requiredTaskIds.size(); i++ ) {
                        requiredTasks[i] = projectModel.getTasks().get(requiredTaskIds.get(i) - 1).getTaskName();
                    }
                    jdlogAddTask.reloadList(requiredTasks);
                }
                    break;
                case "cancel":
                    jdlogAddTask.dispose();
                    break;
                case "finish":
                    try {
                        HashMap<String, String> valuesMap = jdlogAddTask.getAllInputValue();
                        setTaskInfo(valuesMap, taskModel);

                        // add this task to projectDO
                        projectModel.addTask(taskModel);

                        // close window
                        jdlogAddTask.dispose();

                        // refresh add_project panel
                        jdlogAddProject.reloadList(getTaskNames());
                    } catch (Exception excp) {
                        String ErrMsg = "Invalid input: " + excp.getMessage();
                        JOptionPane.showMessageDialog(jdlogAddProject, 
                                ErrMsg, "Invalid Input", 
                                JOptionPane.ERROR_MESSAGE);
                    }
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


    private void updateProjectList() {

        ProjectDao projectDao = new ProjectDao();
        jpnlprojectList.clearTree();
        ArrayList<ProjectDO> projectsUpdate = projectDao.getAllStartedProject();
        for(ProjectDO project : projectsUpdate) {
            jpnlprojectList.addProjectNode(project);
        }
    }

    private void setProjectInfo(HashMap<String, String> valuesMap, ProjectDO project) {
    }

    private void setTaskInfo(HashMap<String, String> valuesMap, TaskDO task) {
        // get input
        String taskName = valuesMap.get("task_name");
        int requiredSkillId = Context.getSkillRevMap().get(valuesMap.get("required_skill"));
        int taskDuration = Integer.parseInt(valuesMap.get("duration"));
        String taskRiskLevel = valuesMap.get("risk_level");
        DateTime taskReleaseTime = new DateTime(valuesMap.get("release_time"));
        String status = valuesMap.get("status");

        // add task info into a taskDO
        task.setTaskName(taskName);
        task.setTaskRequiredSkill(requiredSkillId);
        task.setTaskDuration(taskDuration);
        task.setTaskRemainingTime(taskDuration);
        task.setTaskRiskLevel(taskRiskLevel);
        task.setTaskReleaseTime(taskReleaseTime);
        task.setTaskStatus(status);
    }
}
