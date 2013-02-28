package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import view.MainFrame;
import data.Context;
import data.dataObject.StaffDO;

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

	Context context = new Context();
	HashMap<Integer, String> skillMap = context.getSkillMap();

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
		tree = new JTree(treeModel);
		JScrollPane treeView = new JScrollPane(tree);
		setVisible(true);

		add(tree, BorderLayout.CENTER);

		setVisible(true);

	}

	public void createDefaultStaff(ArrayList<StaffDO> staff) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		for (StaffDO staffObj : staff) {
			addNewStaffToList(staffObj);
		}
		// name = new DefaultMutableTreeNode("Dominic Chim");
		// staff.add(name);
		//
		// info = new DefaultMutableTreeNode("ID: 000001");
		// name.add(info);

	}

	public void addStaffDialog(ActionListener controller, StaffDO staff) {
		staffDialog = new StaffDialog(view, controller, staff);

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

	public String deleteStaff() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		String id = null;
		if (selectedNode.toString() != "Staff") {
			treeModel.removeNodeFromParent(selectedNode);

			id = treeModel.getChild(selectedNode, 0).toString();

			Pattern p = Pattern.compile("Id: (.*)");
			Matcher m = p.matcher(id);
			while (m.find()) {
				id = m.group(1);
			}

			return id;
		}

		return id;
	}

	public int getCurrentlySelectedStaffId() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		String id = null;
		String match = null;
		if (selectedNode.toString() != "Staff") {

			match = treeModel.getChild(selectedNode, 0).toString();

			Pattern p = Pattern.compile("Id: (.*)");
			Matcher m = p.matcher(match);
			while (m.find()) {
				id = m.group(1);
			}
		}

		return Integer.parseInt(id);

	}

	// Add a staff to Staff List
	public StaffDO addNewStaffToList(StaffDO staffInfo) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		name = new DefaultMutableTreeNode(staffInfo.getStaffName());
		treeModel.insertNodeInto(name, staff, 0);

		info = new DefaultMutableTreeNode("Id: " + staffInfo.getStaffId());
		name.add(info);

		info = new DefaultMutableTreeNode("Weekly Availability: "
				+ staffInfo.getStaffWeeklyAvailableTime());
		name.add(info);

//		if (staffInfo.getSkillLevels() != null) {
//			for (int i : staffInfo.getSkillLevels().keySet()) {
//
//				info = new DefaultMutableTreeNode(skillMap.get(i)
//						+ " - Level: " + staffInfo.getSkillLevels().get(i));
//				name.add(info);
//			}
//		}
		return staffInfo;

		// int j = 1;
		// for (String i : staffInfo.getSkills()) {
		// info = new DefaultMutableTreeNode("Skill " + j + ": " + i);
		// name.add(info);
		// j++;
		// }
		//
		// j = 1;
		// for (String i : staffInfo.getHolidays()) {
		// info = new DefaultMutableTreeNode("Holiday " + j + ": " + i);
		// name.add(info);
		// j++;
		// }

	}

	public StaffDO addModifiedStaffToList(StaffDO staffInfo) {

		// staffDao.modifyStaff(staffInfo);

		System.out.println("iD" + staffInfo.getStaffId() + "Name "
				+ staffInfo.getStaffName() + "Avail "
				+ staffInfo.getStaffWeeklyAvailableTime());

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		selectedNode.setUserObject(staffInfo.getStaffName());
		DefaultMutableTreeNode id = (DefaultMutableTreeNode) selectedNode
				.getChildAt(0);
		id.setUserObject("Id: " + staffInfo.getStaffId());

		DefaultMutableTreeNode weeklyAvailTime = (DefaultMutableTreeNode) selectedNode
				.getChildAt(1);
		weeklyAvailTime.setUserObject("Weekly Availability: "
				+ staffInfo.getStaffWeeklyAvailableTime());
		

		// DefaultMutableTreeNode skills = (DefaultMutableTreeNode) selectedNode
		// .getChildAt(2);
		// skills.setUserObject(staffInfo.getSkills()[0]);
		//
		// DefaultMutableTreeNode holidays = (DefaultMutableTreeNode)
		// selectedNode
		// .getChildAt(3);
		// holidays.setUserObject(staffInfo.getHolidays()[0]);

		tree.updateUI();

		return staffInfo;

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