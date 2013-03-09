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

import util.DateTime;
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

		add(tree, BorderLayout.CENTER);

		setVisible(true);

	}

	public void createDefaultStaff(ArrayList<StaffDO> staff) {

		for (StaffDO staffObj : staff) {
			addNewStaffToList(staffObj);
		}

	}

	public void addStaffDialog(ActionListener controller, StaffDO staff) {

		staffDialog = new StaffDialog(view, controller, staff);

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

		System.out.println(id.toString());

		return Integer.parseInt(id);

	}

	// Add a staff to Staff List
	public StaffDO addNewStaffToList(StaffDO staffInfo) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;
		DefaultMutableTreeNode skills = new DefaultMutableTreeNode("Skills");
		DefaultMutableTreeNode holidays = new DefaultMutableTreeNode("Holidays");

		name = new DefaultMutableTreeNode(staffInfo.getStaffName());
		treeModel.insertNodeInto(name, staff, 0);

		info = new DefaultMutableTreeNode("Id: " + staffInfo.getStaffId());
		name.add(info);

		info = new DefaultMutableTreeNode("Weekly Availability: "
				+ staffInfo.getStaffWeeklyAvailableTime());
		name.add(info);

		name.add(skills);

		for (int i : staffInfo.getSkillLevels().keySet()) {

			info = new DefaultMutableTreeNode(skillMap.get(i) + " - Level: "
					+ staffInfo.getSkillLevels().get(i));
			skills.add(info);
		}

		name.add(holidays);

		for (DateTime date : staffInfo.getHolidays().keySet()) {

			info = new DefaultMutableTreeNode(date.getDateTime() + " to "
					+ staffInfo.getHolidays().get(date).getDateTime());
			holidays.add(info);
		}

		return staffInfo;
	}

	public StaffDO addModifiedStaffToList(StaffDO staffInfo) {

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

		DefaultMutableTreeNode skills = (DefaultMutableTreeNode) selectedNode
				.getChildAt(2);
		DefaultMutableTreeNode holidays = (DefaultMutableTreeNode) selectedNode
				.getChildAt(3);
					
		skills.removeAllChildren();
		
				
		for (int i : staffInfo.getSkillLevels().keySet()) {

			skills.add(new DefaultMutableTreeNode(skillMap.get(i)
					+ " - Level: " + staffInfo.getSkillLevels().get(i)));

		}

		holidays.removeAllChildren();
		
		for (DateTime date : staffInfo.getHolidays().keySet()) {
			System.out.println(date);
			holidays.add(new DefaultMutableTreeNode(date.getDateTime() + " to "
					+ staffInfo.getHolidays().get(date).getDateTime()));
		}

//		treeM
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