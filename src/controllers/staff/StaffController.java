package controllers.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import view.MainFrame;
import view.staff.StaffDialog;

public class StaffController implements ActionListener, TreeModelListener {

	MainFrame view;
	StaffDialog staffDialog;

	public StaffController(MainFrame view) {

		this.view = view;
		view.getStaffView().addController(this);

	}

	public void actionPerformed(ActionEvent ae) {

		String cmd = ae.getActionCommand();


		switch (cmd) {

		case "Delete Staff":

			view.getStaffView().deleteStaff();

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

		case "Add":
			
//			staffDialog = view.getStaffView().getStaffDialog();
//			view.getStaffView().getStaffList().addStaff();
//			staffDialog.dispose();

			break;
			
		case "Update":
			
//			staffDialog = view.getStaffView().getStaffDialog();
//			view.getStaffView().getStaffList().modifyStaff();
			staffDialog.dispose();
			
		case "Cancel":
			
			staffDialog = view.getStaffView().getStaffDialog();
			staffDialog.dispose();
			break;
			
		default:

		}

	}

	@Override
	public void treeNodesChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void treeNodesInserted(TreeModelEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void treeNodesRemoved(TreeModelEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void treeStructureChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub

	}

}