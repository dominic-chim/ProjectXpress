package controllers.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class StaffController implements ActionListener {
	
	MainFrame view;

	public StaffController(MainFrame view) {
		
		this.view = view;
		view.getStaffView().addController(this);
		
	}

	public void actionPerformed(ActionEvent ae) {

		String cmd = ae.getActionCommand();
		
		switch (cmd) {

		case "Delete Staff":
			
			System.out.println("Delete Button");
			
			break;

		case "Modify Staff":
			System.out.println("Modify Button");

			break;

		case "Add Staff":
			System.out.println("Add Button");

			view.getStaffView().addStaff();
			
			break;

		default:

		}

	}

}
