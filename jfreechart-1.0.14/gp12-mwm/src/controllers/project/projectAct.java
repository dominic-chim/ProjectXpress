package controllers.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import view.project.*;

public class projectAct implements ActionListener {

	ProjectList current;
	

	public projectAct(ProjectList curProject) {
		this.current = curProject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		final JFileChooser jfc = new JFileChooser();

		switch (cmd) {
		case "Delete Project":
			// delete selected Project
			break;
		case "Modify Project":
			// edit selected Project
			break;
		case "Add project":
			// add a new project

			// stores the new project into a arraylist to be used later
			ProjectModifier createProject = new ProjectModifier();
			current.projectList.add(createProject);

			// make it so that it "pop up"
			JDialog dialog = new JDialog();
			dialog.setContentPane(createProject.createProject());
			dialog.setVisible(true);
			dialog.pack();

			break;
		default:
			System.out.println("Invalid Option");
		}

	}

}
