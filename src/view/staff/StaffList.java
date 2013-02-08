package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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

	// Text Fields for Adding Staff
	JTextField tfId;
	JTextField tfName;
	JTextField tfWeeklyAvail;
	JTextField tfSkillName;
	JTextField tfSkillLevel;
	JTextField tfPrefenceLevel;

	// Holiday Input, start of holiday date until the end of holiday date
	JComboBox monthStart;
	JComboBox dayStart;
	JComboBox monthEnd;
	JComboBox dayEnd;

	public StaffList() {

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
		//
		// info = new DefaultMutableTreeNode("Availability");
		// name.add(info);

	}

	// Add a task to a project
	public void addStaffInfo() {

	}

	public void addStaff() {

		int result = createStaffOptionPane();

		// If Ok Button was pressed
		if (result == 0) {
			HashMap<String, String> staffInput = getStaffInput();
			addStaffToList(staffInput);
		}

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

	}

	// Not sure if I should put this in addStaff, or if it will be used by
	// modifyStaff aswell
	public int createStaffOptionPane() {

		// Panel and Layout - Panel will be put in OptionPane
		JPanel addStaffPanel = new JPanel();
		addStaffPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		// Labels and TextFields for input
		JLabel lblId = new JLabel("ID");
		tfId = new JTextField(20);

		JLabel lblName = new JLabel("Name");
		tfName = new JTextField(20);

		JLabel lblWeeklyAvail = new JLabel("Weekly Available Time:");
		tfWeeklyAvail = new JTextField(20);

		JLabel lblSkillName = new JLabel("Skill Name:");
		tfSkillName = new JTextField(20);

		JLabel lblSkillLevel = new JLabel("Skill Level:");
		tfSkillLevel = new JTextField(20);

		JLabel lblHolidayStart = new JLabel("Holiday Start");
		JLabel lblHolidayEnd = new JLabel("Holiday End");

		JLabel lblPrefenceLevel = new JLabel("Prefence Level:");
		tfPrefenceLevel = new JTextField(20);

		String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Decemeber"};

		monthStart = new JComboBox(months);
		monthEnd = new JComboBox(months);
		
		String days[] = new String[31];
		for(int i =1; i <= 31; i++) {
			days[i-1] = Integer.toString(i);
		}
		
		dayStart = new JComboBox(days);
		dayEnd = new JComboBox(days);
		
		JButton btnAddHoliday = new JButton("Add Holiday");
	
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("January 1 to January 5");
		listModel.addElement("February 22 to January 28");

		
		JLabel lblHolidayList = new JLabel("List of Holidays");
		JList holidayList = new JList(listModel);
		
		JButton removeHoliday = new JButton("Remove Holiday");

		
		// Add Labels and TextFields to Panel
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;

		addStaffPanel.add(lblId, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		addStaffPanel.add(tfId, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblName, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		addStaffPanel.add(tfName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblWeeklyAvail, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		addStaffPanel.add(tfWeeklyAvail, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblSkillName, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		addStaffPanel.add(tfSkillName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblSkillLevel, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		addStaffPanel.add(tfSkillLevel, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		addStaffPanel.add(lblPrefenceLevel, gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 2;
		addStaffPanel.add(tfPrefenceLevel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		addStaffPanel.add(lblHolidayStart, gbc);
		gbc.gridx = 1;
		addStaffPanel.add(monthStart, gbc);
		gbc.gridx = 2;
		addStaffPanel.add(dayStart, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		addStaffPanel.add(lblHolidayEnd, gbc);
		gbc.gridx = 1;
		addStaffPanel.add(monthEnd, gbc);
		gbc.gridx = 2;
		addStaffPanel.add(dayEnd, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		addStaffPanel.add(btnAddHoliday, gbc); 

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;

		addStaffPanel.add(lblHolidayList, gbc);
		gbc.gridwidth = 2;

		gbc.gridx = 1;
		addStaffPanel.add(holidayList, gbc);
		
		gbc.gridy = 10;
		addStaffPanel.add(removeHoliday, gbc);


		int result = JOptionPane.showConfirmDialog(null, addStaffPanel,
				"Add Staff", JOptionPane.OK_CANCEL_OPTION);

		return result;
	}

	public HashMap<String, String> getStaffInput() {

		HashMap<String, String> staffInput = new HashMap<String, String>();

		staffInput.put("Id", tfId.getText());
		staffInput.put("Name", tfName.getText());
		staffInput.put("WeeklyAvail", tfWeeklyAvail.getText());
		staffInput.put("SkillName", tfSkillName.getText());
		staffInput.put("SkillLevel", tfSkillLevel.getText());
		staffInput.put("PrefenceLevel", tfPrefenceLevel.getText());

		return staffInput;

	}

	// Add a staff to Staff Summary
	public void addStaffToList(HashMap<String, String> staffInfo) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		name = new DefaultMutableTreeNode(staffInfo.get("Name"));
		treeModel.insertNodeInto(name, staff, 0);

		for (Map.Entry<String, String> entry : staffInfo.entrySet()) {

			if (entry.getKey() != "Name") {

				info = new DefaultMutableTreeNode(entry.getKey() + ": "
						+ entry.getValue());
				name.add(info);
			}

		}

	}

	// Add ActionListener
	public void addController(ActionListener controller) {

		btnDelete.addActionListener(controller);
		btnModify.addActionListener(controller);
		btnAdd.addActionListener(controller);

	}

}