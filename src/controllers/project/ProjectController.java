package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import view.project.*;

public class ProjectController {

    private MainFrame view;
    private ProjectPanel projectPanel;

    public ProjectController(MainFrame view) {
        this.view = view;
        this.projectPanel = view.getProjectPanel();

        projectPanel.getProjectList().addController(new ProjectListBtnListener());
    }

    class ProjectListBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "delete":
                    break;
                case "modify":
                    break;
                case "add":
                    AddProjectDialog jdlogAdd = new AddProjectDialog(view);
                    jdlogAdd.addController(new AddProjectDialogBtnListener(jdlogAdd));
                    jdlogAdd.setVisible(true);
                    break;
                default:
                    break;
            }
        }
        
    }


    class AddProjectDialogBtnListener implements ActionListener {

        AddProjectDialog parentDialog;

        public AddProjectDialogBtnListener(AddProjectDialog jdlogAddProject) {
            parentDialog = jdlogAddProject;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "add":
                    AddTaskDialog jdlogAddTask = new AddTaskDialog(parentDialog);
                    jdlogAddTask.setVisible(true);
                    break;
                case "cancel":
                    break;
                case "finish":
                    break;
                default:
                    break;
            }

        }
    }

}
