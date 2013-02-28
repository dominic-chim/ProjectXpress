package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.dataAccessObject.SkillDao;

import view.MainFrame;
import view.menu.SkillDialog;

public class SkillController implements ActionListener {
	
	MainFrame view;
	SkillDialog skillDialog;
	SkillDao skillDao;
	
	public SkillController(MainFrame view) {
		
		this.view = view;
		
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
				
		skillDialog = view.getSkillDialog();

		switch(cmd){
		
		case "Add Skill":
		
			skillDialog.addSkillDialog();
			
			break;
			
		case "Modify Skill":
			
			skillDialog.modifySkillDialog();
			
			break;
			
		case "Remove Skill":
			
			skillDialog.removeSkillDialog();
			
			break;
		
		case "Save Skill":
			
			skillDialog.addNewSkillToList();
			skillDialog.getAddSkillDialog().dispose();
			
			
			break;
		
		case "Cancel Add":
			
			skillDialog.getAddSkillDialog().dispose();
			
			break;
			
		case "Save changes":
			
			skillDialog.addModifiedSkillToList();
			skillDialog.getModifySkillDialog().dispose();

			
			break;
			
		case "Cancel Modify":
			
			skillDialog.getModifySkillDialog().dispose();
			
			break;
		}
		
	}
	
}