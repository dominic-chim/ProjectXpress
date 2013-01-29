package view.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class StaffList extends JPanel {

	private JPanel buttonPanel = new JPanel();

	private JButton btnDelete = new JButton("Delete Staff");
	private JButton btnModify = new JButton("Modify Staff");
	private JButton btnAdd = new JButton("Add Staff");

	private JTree tree;

	public StaffList() {

		setLayout(new BorderLayout());

		// button panel settings
		buttonPanel.setLayout(new GridLayout(0, 3));
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnModify);
		buttonPanel.add(btnAdd);

		add(buttonPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(300, 600));

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(
				"The Java Series");
		createNodes(top);
		tree = new JTree(top);
		JScrollPane treeView = new JScrollPane(tree);
		setVisible(true);
		
		add(tree, BorderLayout.CENTER);
		
	}

	private void createNodes(DefaultMutableTreeNode top) {

		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;

		category = new DefaultMutableTreeNode("Books for Java Programmers");
		top.add(category);

		// original Tutorial
		book = new DefaultMutableTreeNode(
				"The Java Tutorial: A Short Course on the Basics");
		category.add(book);
	}

	// Add a staff to Staff Summary
	public void addProject() {

	}

	// Add a task to a project
	public void addTaskToProject() {

	}

}
