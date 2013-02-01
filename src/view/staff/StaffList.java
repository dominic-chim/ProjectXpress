import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class StaffList extends JPanel {

	private JPanel buttonPanel = new JPanel();

	private JButton btnDelete = new JButton("Delete Staff");
	private JButton btnModify = new JButton("Modify Staff");
	private JButton btnAdd = new JButton("Add Staff");

	private JTree tree;
	
	DefaultMutableTreeNode staff;

	public StaffList() {

		setLayout(new BorderLayout());

		// button panel settings
		buttonPanel.setLayout(new GridLayout(0, 3));
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnModify);
		buttonPanel.add(btnAdd);

		add(buttonPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(300, 600));

		staff = new DefaultMutableTreeNode(
				"Staff");
		createNodes();
		tree = new JTree(staff);
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

		name = new DefaultMutableTreeNode(
				"Ross Jarvis");
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
//		insertNodeInto
		staff.add(name);
				
		for(String staffDetails : staffInfo) {
			
			info = new DefaultMutableTreeNode(staffDetails);
			name.add(info);
			
		}
		
//        tree.scrollPathToVisible(new TreePath(s.getPath()));


		System.out.println(staff.getChildCount());
		
	}

	// Add a task to a project
	public void addStaffInfo() {

	}

}
