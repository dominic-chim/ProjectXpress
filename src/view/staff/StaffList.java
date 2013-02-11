package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import view.MainFrame;
import data.dataObject.StaffDO;
import database.dataAccessObject.StaffDao;

//import data.dataObject.*;

public class StaffList extends JPanel {

	private JPanel buttonPanel = new JPanel();

	// Bottom Buttons
	private JButton btnDelete = new JButton("Delete Staff");
	private JButton btnModify = new JButton("Modify Staff");
	private JButton btnAdd = new JButton("Add Staff");

	// JTree
	private JTree tree;
	DefaultMutableTreeNode staff;
	DefaultTreeModel treeModel;

	// Staff Dialog
	StaffDialog staffDialog;
	MainFrame view;

	public StaffList(MainFrame view) {

		this.view = view;
		setLayout(new BorderLayout());

		// button panel settings
		buttonPanel.setLayout(new GridLayout(0, 3));
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnModify);
		buttonPanel.add(btnAdd);

		add(buttonPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(300, 600));

		// Set Up Tree
		staff = new DefaultMutableTreeNode("Staff"); // Root Node

		treeModel = new DefaultTreeModel(staff);
		createNodes();
		tree = new JTree(treeModel);
		JScrollPane treeView = new JScrollPane(tree);
		setVisible(true);

		add(tree, BorderLayout.CENTER);

		setVisible(true);

	}

	private void createNodes() {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		// name = new DefaultMutableTreeNode("Dominic Chim");
		// staff.add(name);
		//
		// info = new DefaultMutableTreeNode("ID: 000001");
		// name.add(info);

	}

	public void addStaffInfo() {

	}

	public void addStaffDialog(ActionListener controller) {
		staffDialog = new StaffDialog(view, controller);

		// If Ok Button was pressed
		// if (result == 0) {
		// HashMap<String, String> staffInput = staffOptionPane.getStaffInput();
		// addStaffToList(staffInput);
		// }

		/*
		 * StaffDO staffDo = new StaffDO(staffInput.get("ID"),
		 * staffInput.get("Name"), staffInput.get("WeeklyAvail"),
		 * staffInput.get("SkillName"), staffInput.get("SkillLevel"),
		 * staffInput.get("ProjectId"), staffInput.get("Task"),
		 * staffInput.get("PrefenceLevel"));
		 */
	}

	public void addStaff() {
		StaffDO staffInput = staffDialog.getStaffInput();
		addStaffToList(staffInput);

		/*
		 * StaffDO staffDo = new StaffDO(staffInput.get("ID"),
		 * staffInput.get("Name"), staffInput.get("WeeklyAvail"),
		 * staffInput.get("SkillName"), staffInput.get("SkillLevel"),
		 * staffInput.get("ProjectId"), staffInput.get("Task"),
		 * staffInput.get("PrefenceLevel"));
		 */
	}

	public void modifyStaff() {

	}

	public void deleteStaff() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (selectedNode.toString() != "Staff") {
			treeModel.removeNodeFromParent(selectedNode);
		}

		StaffDao staffDao = new StaffDao();
		staffDao.deleteStaff(selectedNode.toString());

	}

	// Add a staff to Staff Summary
	public void addStaffToList(StaffDO staffInfo) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		name = new DefaultMutableTreeNode(staffInfo.getStaffName());
		treeModel.insertNodeInto(name, staff, 0);

		info = new DefaultMutableTreeNode("Id: " + staffInfo.getStaffId());
		name.add(info);

		info = new DefaultMutableTreeNode("Weekly Availability: "
				+ staffInfo.getStaffWeeklyAvailableTime());
		name.add(info);

		int j = 1;
		for (String i : staffInfo.getSkills()) {
			info = new DefaultMutableTreeNode("Skill " + j + ": " + i);
			name.add(info);
			j++;
		}

		j = 1;
		for (String i : staffInfo.getHolidays()) {
			info = new DefaultMutableTreeNode("Holiday " + j + ": " + i);
			name.add(info);
			j++;
		}

		// for (Map.Entry<String, String> entry : staffInfo.entrySet()) {
		//
		// if (entry.getKey() != "Name") {
		//
		// info = new DefaultMutableTreeNode(entry.getKey() + ": "
		// + entry.getValue());
		// name.add(info);
		// }
		//
		// }

	}

	public StaffDialog getStaffDialog() {
		return this.staffDialog;
	}

	// Add ActionListener
	public void addController(ActionListener controller) {

		btnDelete.addActionListener(controller);
		btnModify.addActionListener(controller);
		btnAdd.addActionListener(controller);
	}

}