package view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * left part of project tab in MainFrame
 */
public class ProjectList extends JPanel {

	private static final long serialVersionUID = -7858214750393514974L;

	private JPanel bottomPanel = new JPanel();
	private JButton btnDelete = new JButton("Delete Project");
	private JButton btnModify = new JButton("Modify Project");
	private JButton btnAdd = new JButton("Add project");

	public JTree projectTree;

	public ProjectList() {
        
        setLayout(new BorderLayout());
        
        // bottom panel settings
        bottomPanel.setLayout(new GridLayout(0, 3));

        btnDelete.setActionCommand("delete");
        btnModify.setActionCommand("modify");
        btnAdd.setActionCommand("add");

        bottomPanel.add(btnDelete);
        bottomPanel.add(btnModify);
        bottomPanel.add(btnAdd);
        
        // set up the tree
        // TODO finish this
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("Project");
        DefaultMutableTreeNode taskSample1 = new DefaultMutableTreeNode("task1");
        DefaultMutableTreeNode taskSample2 = new DefaultMutableTreeNode("task2");
        DefaultMutableTreeNode taskSample3 = new DefaultMutableTreeNode("task3");
        topNode.add(taskSample1);
        topNode.add(taskSample2);
        topNode.add(taskSample3);
        projectTree = new JTree(topNode);
        add(projectTree, BorderLayout.CENTER);
        projectTree.putClientProperty("JTree.lineStyle", "Horizontal");
        projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        // 
        add(bottomPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(300, 600));
        
        //Project containers
        DefaultListModel<AddProjectDialog> ProjectListModel = new DefaultListModel<AddProjectDialog>();
    }

	public void addController(ActionListener listener) {
		btnDelete.addActionListener(listener);
		btnModify.addActionListener(listener);
		btnAdd.addActionListener(listener);
	}

	public JTree getTree() {

		return projectTree;
	}
}
