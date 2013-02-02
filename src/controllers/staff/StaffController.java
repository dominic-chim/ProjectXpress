package controllers.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.staff.*;

public class StaffController implements ActionListener {
	
	StaffView view;

	public StaffController(StaffView view) {
		
		this.view = view;
		view.addController(this);
		
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

			view.addStaff();
			
			break;

		default:

		}

	}

}
