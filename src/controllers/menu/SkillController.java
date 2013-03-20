package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import view.menu.SkillDialog;
import data.Context;
import data.dataObject.SkillDO;
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
			
			String skillName = skillDialog.addNewSkillToList();
			System.out.println(skillName);
			skillDao.addSkill(skillName);
			skillDialog.getAddSkillDialog().dispose();
			Context.updateSkills();

			break;

		// Cancel from add skill dialog
		case "Cancel Add":

			skillDialog.getAddSkillDialog().dispose();

			break;

		// Save Modifed Skill from modify skill dialog
		case "Save changes":

			SkillDO skill = skillDialog.addModifiedSkillToList();
			skillDao.modifySkill(skill);
			skillDialog.getModifySkillDialog().dispose();
			Context.updateSkills();

			break;

		// Cancel modifying a skill from modify skill dialog
		case "Cancel Modify":

			skillDialog.getModifySkillDialog().dispose();

			break;
		}

	}

}