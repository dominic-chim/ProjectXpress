package view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import data.dataObject.ProjectDO;
import data.dataObject.TaskDO;

/**
 * left part of project tab in MainFrame
 */
public class ProjectList extends JPanel {
    
    private JPanel bottomPanel = new JPanel();
    private JButton btnDelete = new JButton("Delete Project");
    private JButton btnModify = new JButton("Modify Project");
    private JButton btnAdd = new JButton("Add project");

    private DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("projects");
    
    // override convertValueToText() method in jtree
    private JTree projectTree = new JTree(topNode){

        @Override
        public String convertValueToText(Object value, boolean selected, boolean expanded, 
                boolean leaf, int row, boolean hasFocus) {
            
            if(value instanceof DefaultMutableTreeNode) {
                Object obj = ((DefaultMutableTreeNode)value).getUserObject();
                if(obj instanceof ProjectDO) {
                    return ((ProjectDO)obj).getProjectName();
                } else if(obj instanceof TaskDO) {
                    return ((TaskDO)obj).getTaskName();
                }
            }
            return value.toString();
        }
    };
    
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
        add(projectTree, BorderLayout.CENTER);
        
        // 
        add(bottomPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(300, 600));
    }

    public void addController(ActionListener listener) {
        btnDelete.addActionListener(listener);
        btnModify.addActionListener(listener);
        btnAdd.addActionListener(listener);
    }

    public void addProjectNode(ProjectDO project) {

        DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode();
        projectNode.setUserObject(project);

        ArrayList<TaskDO> tasks = project.getTasks();
        for(TaskDO task : tasks) {
            DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode();
            taskNode.setUserObject(task);
            projectNode.add(taskNode);
        }

        topNode.add(projectNode);
    }

    // get selected project/task in project list
    public Object getSelectedObjectInTree() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)projectTree.getLastSelectedPathComponent();
        return selectedNode.getUserObject();
    }

}
