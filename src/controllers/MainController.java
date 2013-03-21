package controllers;

import view.MainFrame;
import controllers.menu.MenuController;
import controllers.menu.SkillController;
import controllers.project.ProjectController;
import controllers.staff.StaffController;

/**
 * 
 * main controller used to start other controllers
 * 
 * @author all group members
 * 
 */
public class MainController {

	private MainFrame view;

	public MainController(MainFrame view) {

		this.view = view;

		// start other controllers
		new ProjectController(view);
		new StaffController(view);
		new MenuController(view, new SkillController(view));

	}

}
