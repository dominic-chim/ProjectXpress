package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.PriorityQueue;

import util.DateTime;
import view.MainFrame;
import view.menu.SchedulingDialog;
import view.menu.UserManualDialog;
import algorithm.ScheduleAlgorithm;
import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;
import database.dataAccessObject.StaffDao;

public class MenuController implements ActionListener {

	private MainFrame view;
	private SkillController skillController;

	public MenuController(MainFrame view, SkillController skillController) {

		this.skillController = skillController;
		this.view = view;
		view.getMainMenuBar().addControllers(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		switch (cmd) {

		case "Skills":

			view.addSkillDialog(skillController);

			break;
		case "Scheduling":
			SchedulingDialog sdialog = view.addSchedulingDialog();
			sdialog.addControllers(new SchedulingController(sdialog));
			ProjectDao projectDB = new ProjectDao();
			StaffDao staffDB = new StaffDao();
			sdialog.initJcbxProjectsAndStaffs(projectDB.getAllStartedProject(),
					staffDB.getAllStaff());
			sdialog.setVisible(true);
			break;

		case "User Manual":

			UserManualDialog userManualDialog = view.addUserManualDialog();
			userManualDialog.addControllers(new UserManualController(userManualDialog));

			break;

		default:
			break;
		}

	}

	class UserManualController implements ActionListener {

		private UserManualDialog userManualDialog;

		public UserManualController(UserManualDialog userManualDialog) {

			this.userManualDialog = userManualDialog;

		}

		public void actionPerformed(ActionEvent e) {

			String cmd = e.getActionCommand();

			switch (cmd) {
			}

		}

	}

	class SchedulingController implements ActionListener {

		private SchedulingDialog sdialog;

		public SchedulingController(SchedulingDialog sdialog) {
			this.sdialog = sdialog;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			switch (cmd) {
			case "schedule":

				// if the duration is not enough double the duration and try
				// again
				boolean success = false;
				int totalHour = 0;
				while (!success) {
					ProjectDao projectDB = new ProjectDao();
					PriorityQueue<ProjectDO> projects = new PriorityQueue<ProjectDO>(
							5, new ProjectComparator());
					ArrayList<Integer> projectIds = sdialog
							.getSelectedProjectIds();
					for (int projectId : projectIds) {
						projects.add(projectDB.getProjectById(projectId));
					}
					ArrayList<StaffDO> staffs = new ArrayList<StaffDO>();
					for (int staffId : sdialog.getSelectedStaffIds()) {
						staffs.add((new StaffDao()).getStaffById(staffId));
					}
					DateTime startingDateTime = new DateTime(
							sdialog.getStartingDateTime());

					// estimate a duration of all project
					if (totalHour == 0) {
						for (ProjectDO project : projects) {
							for (TaskDO task : project.getTasks()) {
								totalHour += task.getTaskDuration();
							}
						}
						totalHour = totalHour / staffs.size() * 5;
					}

					try {
						ScheduleAlgorithm algorithm = new ScheduleAlgorithm(
								startingDateTime, DateTime.hourLater(
										startingDateTime, totalHour), staffs,
								projects);
						ResultDao resultDB = new ResultDao();
						resultDB.addResults(algorithm.runAlgoritm());
						success = true;
					} catch (ArrayIndexOutOfBoundsException exception) {
						success = false;
						totalHour *= 2;
					}
				}

				// refresh gui
				view.refresh();
				sdialog.dispose();

				break;
			case "cancel":
				sdialog.dispose();
				break;
			default:
				break;
			}

		}

	}

}
