package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.project.AddProjectDialog;
import view.project.ProjectPanel;

public class ProjectController {

    private ProjectPanel projectPanel = new ProjectPanel();

    public ProjectController(ProjectPanel projectPanel) {
        this.projectPanel = projectPanel;
        projectPanel.getProjectList().addController(new BtnAddProjectListener());
    }

    class BtnAddProjectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "delete":
                    break;
                case "modify":
                    break;
                case "add":
                    AddProjectDialog jdlogAdd = new AddProjectDialog();
                    jdlogAdd.setVisible(true);
                    break;
                default:
                    break;
            }
        }
        
    }



}
