package controllers.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.MainFrame;
import view.staff.StaffDialog;
import view.staff.StaffList;
import view.staff.StaffView;
import data.dataObject.StaffDO;
import database.dataAccessObject.StaffDao;

/**
 * 
 * controller for staff tab
 * 
 * @author Ross
 *
 */
public class StaffController implements ActionListener {

	MainFrame view;
	StaffView staffView;
	StaffList staffList;
	StaffDialog staffDialog;
	StaffDao staffDao = new StaffDao();
	StaffDO staff;

	public StaffController(MainFrame view) {

		this.view = view;
		staffView = view.getStaffView();
		staffView.addController(this);
		staffList = staffView.getStaffList();

		// Initialise staff tree with staff currently in the database
		staffList.createDefaultStaff(staffDao.getAllStaff());

	}

	public void actionPerformed(ActionEvent ae) {

		String cmd = ae.getActionCommand();

		switch (cmd) {

		case "Delete Staff":

			String staffToDelete = staffList.deleteStaff();
			staffDao.deleteStaff(staffToDelete);

			break;

		case "Modify Staff":

			int staffId = staffList.getCurrentlySelectedStaffId();
			if (staffId != 0) {

				StaffDO staff = staffDao.getStaffById(staffId);
				staffList.addStaffDialog(this, staff);
			}
			break;

		case "Add Staff":

			staffList.addStaffDialog(this, null);

			break;

		case "Add Skill":

			staffDialog = staffList.getStaffDialog();
			staffDialog.addSkill();

			break;

		case "Remove Skill":

			staffDialog = staffList.getStaffDialog();

			staffDialog.removeSkill();

			break;

		case "Add Holiday":

			staffDialog = staffList.getStaffDialog();
			staffDialog.addHoliday();

			break;

		case "Remove Holiday":

			staffDialog = staffList.getStaffDialog();
			staffDialog.removeHoliday();

			break;

		// Add Button In Staff Dialog
		case "Add":

			staffDialog = staffList.getStaffDialog();

			if (staffDialog.checkStaffInput()) {

				StaffDO input = staffDialog.getStaffInput();

				if (staffDao.getStaffById(input.getStaffId()) != null) {
					staffDialog.inputError("Id Already Exists");
					break;
				}

				staff = staffList.addNewStaffToList(input);

				staffDialog.dispose();

				staffDao.createStaff(staff);
			}
			break;

		case "Update":

			staffDialog = staffList.getStaffDialog();
			staff = staffDialog.getStaffInput();
			ArrayList<String> queries = staffDialog.getQueries();

			staffDialog.dispose();

			staffDao.modifyStaff(staffList.getCurrentlySelectedStaffId(),
					staff, queries);
			staff = staffList.addModifiedStaffToList(staff);

		case "Cancel":

			staffDialog = staffList.getStaffDialog();
			staffDialog.dispose();
			break;

		default:

		}

	}
}