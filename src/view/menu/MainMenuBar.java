package view.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainMenuBar extends JMenuBar {

	// menus
	//JMenuBar Menu;
	private JMenu menuProject = new JMenu("Project");
	//private JMenu menuView = new JMenu("View");
	private JMenu menuSettings = new JMenu("Settings");
	private JMenu menuHelp = new JMenu("Help");
	
	private JMenuItem miSettingsSkill = new JMenuItem("Skills");
	private JMenuItem miSettingsRisk = new JMenuItem("Risk");
    private JMenuItem miProjectSchedule = new JMenuItem("Scheduling");
    private JMenuItem miUserManual = new JMenuItem("User Manual");
    
	
	public MainMenuBar() {

		menuProject.add(miProjectSchedule);
		menuSettings.add(miSettingsSkill);
		menuSettings.add(miSettingsRisk);
		menuHelp.add(miUserManual);

		this.add(menuProject);
		//this.add(menuView);
		this.add(menuSettings);
		this.add(menuHelp);
	}
	
	public JMenuItem getSchedulingButton() {
		return this.miProjectSchedule;
		
	}
	
	public void addControllers(ActionListener controller) {
		miSettingsSkill.addActionListener(controller);
		miSettingsRisk.addActionListener(controller);
		miProjectSchedule.addActionListener(controller);
		miUserManual.addActionListener(controller);
	}
}
