package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import view.menu.SkillDialog;
import database.dataAccessObject.SkillDao;

public class SkillController implements ActionListener {

	MainFrame view;
	SkillDialog skillDialog;
	SkillDao skillDao = new SkillDao();

	public SkillController(MainFrame view) {

		this.view = view;

	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		skillDialog = view.getSkillDialog();

		switch (cmd) {

		// Add Skill Dialog
		case "Add Skill":

			skillDialog.addSkillDialog();

			break;

		// Modify Skill Dialog
		case "Modify Skill":

			skillDialog.modifySkillDialog();

			break;

		// Remove Skill Dialog
		case "Remove Skill":

			skillDialog.removeSkillDialog();

			break;

		// Save skill from add skill dialog
		case "Save Skill":
			
			skillDao.addSkill();

			skillDialog.addNewSkillToList();
			skillDialog.getAddSkillDialog().dispose();

			break;

		// Cancel from add skill dialog
		case "Cancel Add":

			skillDialog.getAddSkillDialog().dispose();

			break;

		// Save Modifed Skill from modify skill dialog
		case "Save changes":

			skillDialog.addModifiedSkillToList();
			skillDialog.getModifySkillDialog().dispose();

			break;

		// Cancel modifying a skill from modify skill dialog
		case "Cancel Modify":

			skillDialog.getModifySkillDialog().dispose();

			break;
		}

	}

}