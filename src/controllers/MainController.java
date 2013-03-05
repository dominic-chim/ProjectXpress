package controllers;

import view.MainFrame;
import controllers.menu.MenuController;
import controllers.menu.SchedulingController;
import controllers.menu.SkillController;
import controllers.project.ProjectController;
import controllers.staff.StaffController;

public class MainController {

    private MainFrame view;

    public MainController(MainFrame view) {

        this.view = view;

        // start other controllers
        ProjectController projectController = new ProjectController(view);
        StaffController staffController = new StaffController(view);
        SkillController skillController = new SkillController(view);
        MenuController menuController = new MenuController(view, skillController);
        
    }


}
