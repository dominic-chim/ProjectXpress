package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		tree = new JTree(treeModel);
		JScrollPane treeView = new JScrollPane(tree);
		setVisible(true);

		add(tree, BorderLayout.CENTER);

		setVisible(true);

	}

	public void createDefaultStaff(ArrayList<StaffDO> staff) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

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

	public void addStaff(ActionListener controller) {
		
		addStaffDialog(controller, null);

//		StaffDO staffInput = staffDialog.getStaffInput();
//		addStaffToList(staffInput);

		/*
		 * StaffDO staffDo = new StaffDO(staffInput.get("ID"),
		 * staffInput.get("Name"), staffInput.get("WeeklyAvail"),
		 * staffInput.get("SkillName"), staffInput.get("SkillLevel"),
		 * staffInput.get("ProjectId"), staffInput.get("Task"),
		 * staffInput.get("PrefenceLevel"));
		 */
	}

	public void modifyStaff(ActionListener controller) {
		
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (selectedNode.toString() != "Staff") {

			String id = treeModel.getChild(selectedNode, 0).toString();
			
			Pattern p = Pattern.compile("Id: (.*)");
			Matcher m = p.matcher(id);
			while (m.find()) {
				id = m.group(1);
			}
	
			StaffDao staffDao = new StaffDao();
//			StaffDO staff = staffDao.getStaffById(Integer.parseInt(id));
			String[] skillTest = {"hello", "goodbye"};
			
			StaffDO staff = new StaffDO(5, "Ross", 5, skillTest, skillTest);
			
			addStaffDialog(controller, staff);
			
		}

//		staffDao.deleteStaff(selectedNode.toString());
		

	}

	public String deleteStaff() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (selectedNode.toString() != "Staff") {
			treeModel.removeNodeFromParent(selectedNode);
			
			return selectedNode.toString();
		}
		
		return null;
	}

	// Add a staff to Staff List
	public void addNewStaffToList() {

		StaffDO staffInfo = staffDialog.getStaffInput();
		StaffDao staffDao = new StaffDao();
		staffDao.createStaff(staffInfo);
		
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

	}

	public void addModifiedStaffToList() {
		
		StaffDO staffInfo = staffDialog.getStaffInput();
		StaffDao staffDao = new StaffDao();
//		staffDao.modifyStaff(staffInfo);
		
		System.out.println("iD" + staffInfo.getStaffId() + "Name "+ staffInfo.getStaffName() + "Avail "+ staffInfo.getStaffWeeklyAvailableTime());
		
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		
		selectedNode.setUserObject(staffInfo.getStaffName());
		DefaultMutableTreeNode id = (DefaultMutableTreeNode) selectedNode.getChildAt(0);
		id.setUserObject(staffInfo.getStaffId());
				
		DefaultMutableTreeNode weeklyAvailTime = (DefaultMutableTreeNode) selectedNode.getChildAt(1);
		weeklyAvailTime.setUserObject(staffInfo.getStaffWeeklyAvailableTime());
		
		DefaultMutableTreeNode skills = (DefaultMutableTreeNode) selectedNode.getChildAt(2);
		skills.setUserObject(staffInfo.getSkills()[0]);
		
		DefaultMutableTreeNode holidays = (DefaultMutableTreeNode) selectedNode.getChildAt(3);
		holidays.setUserObject(staffInfo.getHolidays()[0]);
		
		tree.updateUI();
		
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