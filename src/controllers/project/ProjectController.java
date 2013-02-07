package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.dataObject.*;

import view.MainFrame;
import view.project.*;

//import static view.project.AddTaskDialog.*;

public class ProjectController {

    private MainFrame view;
    private ProjectPanel projectPanel;

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
                    AddProjectDialog jdlogAddProject = new AddProjectDialog(view);

                    jdlogAddProject.addController(new AddProjectDialogBtnListener(jdlogAddProject));
                    jdlogAddProject.setVisible(true);

                    break;
                default:
                    break;
            }
        }
        
    }


    // ActionListener for all buttons in AddProjectDialog
    class AddProjectDialogBtnListener implements ActionListener {

        private AddProjectDialog parentDialog; 

        public AddProjectDialogBtnListener(AddProjectDialog parentDialog) {
            this.parentDialog = parentDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add":
                    // construct the view of adding a task
                    AddTaskDialog jdlogAddTask = new AddTaskDialog(parentDialog);

                    jdlogAddTask.addController(new AddTaskDialogBtnListener(jdlogAddTask));
                    jdlogAddTask.setVisible(true);
                    break;
                case "cancel":
                    parentDialog.dispose();
                    break;
                case "finish":
                    break;
                default:
                    break;
            }

        }
    }

    // TODO check this and finish it 
    // ActionListeners for all buttons in 
    class AddTaskDialogBtnListener implements ActionListener {

        private AddTaskDialog parentDialog;

        public AddTaskDialogBtnListener(AddTaskDialog parentDialog) {
            this.parentDialog = parentDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add": // add a Required task
                    parentDialog.showAddReqiredTaskDialog();
                    break;
                case "cancel":
                    parentDialog.dispose();
                    break;
                case "finish":
                    TaskDO taskModel = new TaskDO();
                    taskModel.setTaskName(parentDialog.getInputValue(AddTaskDialog.TASK_NAME));
                    //taskModel.setTaskRequiredSkill(parentDialog.getInputValue(AddTaskDialog.REQUIRED_SKILL));
                    projectModel.addTask(taskModel);
                    break;
                default:
                    break;
            }

        }

    }

}
