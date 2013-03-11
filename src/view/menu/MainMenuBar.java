package view.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainMenuBar extends JMenuBar {

	// menus
	//JMenuBar Menu;
	private JMenu menuProject = new JMenu("Project");
	private JMenu menuView = new JMenu("View");
	private JMenu menuSettings = new JMenu("Settings");

	
	private JMenuItem miViewSkill = new JMenuItem("Skills");
    private JMenuItem miProjectSchedule = new JMenuItem("Scheduling");
	
	public MainMenuBar() {

		menuProject.add(miProjectSchedule);
		menuView.add(miViewSkill);

		this.add(menuProject);
		//this.add(menuView);
		//this.add(menuSettings);
	}
	
	public void addControllers(ActionListener controller) {
		miViewSkill.addActionListener(controller);
		miProjectSchedule.addActionListener(controller);
	}
}
