package view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import controllers.project.*;

/**
 * left part of project tab in MainFrame
 */
public class ProjectList extends JPanel {
    
    private static final long serialVersionUID = -7858214750393514974L;

    private JPanel bottomPanel = new JPanel();
    private JButton btnDelete = new JButton("Delete Project");
    private JButton btnModify = new JButton("Modify Project");
    private JButton btnAdd = new JButton("Add project");

    public ArrayList<ProjectModifier> projectList = new ArrayList<ProjectModifier>();
    
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
        
        add(bottomPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(300, 600));
    }

    public void addController(ActionListener listener) {
        btnDelete.addActionListener(listener);
        btnModify.addActionListener(listener);
        btnAdd.addActionListener(listener);
    }
}
