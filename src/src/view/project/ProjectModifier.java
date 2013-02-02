package view.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.MainFrame;

@SuppressWarnings("serial")
public class ProjectModifier extends JPanel implements ActionListener {
	public JButton add, clear;
	public String ProjectStatus;
	Color ColourA = new Color(0xC3BEFA);
	Color ColourB = new Color(0x6052FF);

	public ProjectModifier() {
		
	}

	public JPanel createProject() {
		JPanel project = new JPanel();
		
		JLabel name, dueDate, priority, status;
		JTextArea Name, Date,Priority,Status;

		//sets up the Project Panel
		project.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		project.setBackground(ColourA);
		
		
		//layout of the Project panel
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 0, 20);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(name = new JLabel("Project Name"), gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(Name = new JTextArea(""), gbc);
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 0, 20);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(dueDate = new JLabel("Due Date"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(Date = new JTextArea(""), gbc);
		

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 0, 20);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(priority = new JLabel("Priority"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(Priority = new JTextArea(""), gbc);
		
		
		
		
		
		String[] stat = {"Started","Not Started","Completed"};
		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox<String> statuslist = new JComboBox<String>(stat);
		statuslist.setSelectedIndex(1);
		statuslist.addActionListener(this);
		
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 10, 20);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(status = new JLabel("Status"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		project.add(statuslist, gbc);
		
		//defines a minimum size
		project.setPreferredSize(new Dimension(500, 500));
		return project;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> cb = (JComboBox<String>)e.getSource();
        ProjectStatus = (String) cb.getSelectedItem();
		
	}

}
