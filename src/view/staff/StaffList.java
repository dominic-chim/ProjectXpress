package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import data.dataObject.*;

public class StaffList extends JPanel {

	private JPanel buttonPanel = new JPanel();

	//Bottom Buttons
	private JButton btnDelete = new JButton("Delete Staff");
	private JButton btnModify = new JButton("Modify Staff");
	private JButton btnAdd = new JButton("Add Staff");

	//JTree 
	private JTree tree;
	DefaultMutableTreeNode staff;
	DefaultTreeModel treeModel;
	
	//Text Fields for Adding Staff
	JTextField tfId;
	JTextField tfName;
	JTextField tfWeeklyAvail;
	JTextField tfSkillName;
	JTextField tfSkillLevel;
	JTextField tfTaskId;
	JTextField tfProjectId;
	JTextField tfPrefenceLevel;
	
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

		ArrayList<String> test = new ArrayList<String>();
		test.add("test");
		test.add("id");
		addStaff(test);
		add(tree, BorderLayout.CENTER);

		setVisible(true);

	}

	private void createNodes() {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		name = new DefaultMutableTreeNode("Dominic Chim");
		staff.add(name);

		info = new DefaultMutableTreeNode("ID: 000001");
		name.add(info);

		info = new DefaultMutableTreeNode("Availability");
		name.add(info);

		name = new DefaultMutableTreeNode("Ross Jarvis");
		staff.add(name);

		info = new DefaultMutableTreeNode("ID: 000002");
		name.add(info);

		info = new DefaultMutableTreeNode("Availability");
		name.add(info);
	}

	// Add a staff to Staff Summary
	public void addStaff(ArrayList<String> staffInfo) {

		DefaultMutableTreeNode name = null;
		DefaultMutableTreeNode info = null;

		name = new DefaultMutableTreeNode(staffInfo.get(0));
		treeModel.insertNodeInto(name, staff, 0);

		for (String staffDetails : staffInfo) {

			info = new DefaultMutableTreeNode(staffDetails);
			name.add(info);

		}

	}

	// Add a task to a project
	public void addStaffInfo() {

	}

	public void addStaff() {
		
		createStaffOptionPane();
		HashMap<String, String> staffInput = getStaffInput();
		
		StaffDO staffDo	 = new StaffDO(staffInput.get("ID"), staffInput.get("Name"),
				staffInput.get("WeeklyAvail"), staffInput.get("SkillName"), staffInput.get("SkillLevel"),
				staffInput.get("ProjectId"), staffInput.get("Task"), staffInput.get("PrefenceLevel"));
		
	
	
	public void modifyStaff() {
		
	}
	
	//Not sure if I should put this in addStaff, or if it will be used by modifyStaff aswell
	public void createStaffOptionPane() {
		
		//Panel and Layout - Panel will be put in OptionPane
		JPanel addStaffPanel = new JPanel();
		addStaffPanel.setLayout(new GridLayout(7,1));
		
		//Labels and TextFields for input
		JLabel lblId = new JLabel("ID");
		tfId= new JTextField();
		
		JLabel lblName = new JLabel("Name");
		tfName = new JTextField();
		
		JLabel lblWeeklyAvail= new JLabel("Weekly Available Time:");
		tfWeeklyAvail= new JTextField();
		
		JLabel lblSkillName= new JLabel("Skill Name:");
		tfSkillName= new JTextField();
		
		JLabel lblSkillLevel = new JLabel("Skill Level:");
		tfSkillLevel = new JTextField();
		
		JLabel lblProjectId = new JLabel("Project Id:");
		tfProjectId = new JTextField();
		
		JLabel lblTaskId = new JLabel("Task Id:");
		tfTaskId = new JTextField();

		JLabel lblPrefenceLevel = new JLabel("Prefence Level:");
		tfPrefenceLevel = new JTextField();

		
		//Add Labels and TextFields to Panel
		addStaffPanel.add(lblName);
		addStaffPanel.add(tfName);
		addStaffPanel.add(lblWeeklyAvail);
		addStaffPanel.add(tfWeeklyAvail);
		addStaffPanel.add(lblSkillName);
		addStaffPanel.add(tfSkillName);
		addStaffPanel.add(lblSkillLevel);
		addStaffPanel.add(tfSkillLevel);
		addStaffPanel.add(lblProjectId);
		addStaffPanel.add(tfProjectId);
		addStaffPanel.add(lblTaskId);
		addStaffPanel.add(tfTaskId);
		addStaffPanel.add(lblPrefenceLevel);
		addStaffPanel.add(tfPrefenceLevel);
		
		JOptionPane.showConfirmDialog(null, addStaffPanel,
				"Add Staff", JOptionPane.OK_CANCEL_OPTION);
		
	}
	
	public HashMap<String, String> getStaffInput() {
		
		HashMap<String, String> staffInput = new HashMap<String, String>();
		
		staffInput.put("Name", tfName.getText());
		staffInput.put("WeeklyAvail", tfWeeklyAvail.getText());
		staffInput.put("SkillLevel", tfSkillLevel.getText());
		staffInput.put("PrefenceLevel", tfPrefenceLevel.getText());
		
		return staffInput;
		
	}

	
	
	// Add ActionListener
	public void addController(ActionListener controller) {

		btnDelete.addActionListener(controller);
		btnModify.addActionListener(controller);
		btnAdd.addActionListener(controller);

	}

}
