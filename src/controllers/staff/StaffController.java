package controllers.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import view.MainFrame;

public class StaffController implements ActionListener, TreeModelListener {
	
	MainFrame view;
	

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

			break;

		case "Add Staff":

			view.getStaffView().addStaff();
			
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