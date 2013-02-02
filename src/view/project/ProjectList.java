package view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import controllers.project.*;

public class ProjectList extends JPanel {
	
	/**
	 * 
	 */
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
		bottomPanel.add(btnDelete);
		bottomPanel.add(btnModify);
		bottomPanel.add(btnAdd);
		
		
		//make buttons interactive
		btnAdd.addActionListener(new projectAct(this));
		btnModify.addActionListener(new projectAct(this));
		btnDelete.addActionListener(new projectAct(this));
		
		
		add(bottomPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(300, 600));
        }
	
        // Add a staff to Staff Summary
		public void addProject() {

		}

		// Add a task to a project
		public void addTaskToProject() {

		}
	
}
