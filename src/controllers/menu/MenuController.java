package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import view.MainFrame;
import view.menu.MainMenuBar;

public class MenuController implements ActionListener {
	MainFrame view;
	SkillController skillController;

	public MenuController(MainFrame view, SkillController skillController) {

		this.skillController = skillController;
		this.view = view;
		view.getMainMenuBar().addControllers(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		final JFileChooser jfc = new JFileChooser();


		switch (cmd) {
		case "New":
			// reset everything -- does meant current data is deleted
			break;
		case "Open":
			// open file in w/e format we choose
			break;
		case "Save":
			// save current data to a format
			break;
		case "Close":
			System.exit(0);
			break;

		case "Skills":
			
			view.addSkillDialog(skillController);
			
			break;

		default:
			System.out.println("Invalid Option");
		}

	}

}
