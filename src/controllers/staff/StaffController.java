package controllers.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import database.dataAccessObject.StaffDao;

import view.MainFrame;
import view.staff.StaffDialog;

public class StaffController implements ActionListener {

	MainFrame view;
	StaffDialog staffDialog;
	StaffDao staffDao = new StaffDao();		

	public StaffController(MainFrame view) {

		this.view = view;
		view.getStaffView().addController(this);
		
		//Initialise staff tree with staff currently in the database
//		view.getStaffView().getStaffList().createDefaultStaff(staffDao.getAllStaff());
		
	}

	public void actionPerformed(ActionEvent ae) {

		String cmd = ae.getActionCommand();


		switch (cmd) {

		case "Delete Staff":

			String staffToDelete = view.getStaffView().deleteStaff();
			staffDao.deleteStaff(staffToDelete);

			break;

		case "Modify Staff":

			view.getStaffView().modifyStaff(this);
			
			break;

		case "Add Staff":

			view.getStaffView().addStaff(this);
			

			break;

		case "Add Skill":

			staffDialog = view.getStaffView().getStaffDialog();
			staffDialog.addSkill();

			break;

		case "Remove Skill":

			staffDialog = view.getStaffView().getStaffDialog();

			staffDialog.removeSkill();

			break;

		case "Add Holiday":
			
			staffDialog = view.getStaffView().getStaffDialog();
			staffDialog.addHoliday();
			
			break;

		case "Remove Holiday":
			
			staffDialog = view.getStaffView().getStaffDialog();
			staffDialog.removeHoliday();
			
			break;

		//Add Button In Staff Dialog
		case "Add":
			
			staffDialog = view.getStaffView().getStaffDialog();
			view.getStaffView().getStaffList().addNewStaffToList();
			staffDialog.dispose();

			break;
			
		case "Update":
			
			staffDialog = view.getStaffView().getStaffDialog();
			view.getStaffView().getStaffList().addModifiedStaffToList();
			staffDialog.dispose();
			
		case "Cancel":
			
			staffDialog = view.getStaffView().getStaffDialog();
			staffDialog.dispose();
			break;
			
		default:

		}

	}

}