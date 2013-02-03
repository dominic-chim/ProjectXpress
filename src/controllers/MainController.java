package controllers;

import controllers.staff.StaffController;
import controllers.project.ProjectController;
import view.MainFrame;

public class MainController {

    private MainFrame view;

    public MainController(MainFrame view) {

        this.view = view;

        // start other controllers
        ProjectController projectController = new ProjectController(view);
    }


}
