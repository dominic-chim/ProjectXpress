package view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class ProjectLeftPanel extends JPanel {
	
	
	private JPanel bottomPanel = new JPanel();
	
	private JButton btnDelete = new JButton("Delete Project");
	private JButton btnModify = new JButton("Modify Project");
	private JButton btnAdd = new JButton("Add project");
	
	
	public ProjectLeftPanel() {
		
		setLayout(new BorderLayout());
		
		// bottom panel settings
		bottomPanel.setLayout(new GridLayout(0, 3));
		bottomPanel.add(btnDelete);
		bottomPanel.add(btnModify);
		bottomPanel.add(btnAdd);
		
		add(bottomPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(300, 600));
	}
	
}
