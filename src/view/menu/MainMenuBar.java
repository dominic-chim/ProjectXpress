package view.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * menu class
 * 
 * @author Dominic, Ross, Ke CHEN
 *
 */
public class MainMenuBar extends JMenuBar {

	// menus
	private JMenu menuProject = new JMenu("Project");
	private JMenu menuSettings = new JMenu("Settings");
	private JMenu menuHelp = new JMenu("Help");
	private JMenu menuAbout= new JMenu("About");
	private JMenuItem miSettingsSkill = new JMenuItem("Skills");
	private JMenuItem miSettingsRisk = new JMenuItem("Risk");
    private JMenuItem miProjectSchedule = new JMenuItem("Scheduling");
    private JMenuItem miUserManual = new JMenuItem("User Manual");
	private JMenuItem miAbout = new JMenuItem("Project Info");
    
	
	public MainMenuBar() {

		menuProject.add(miProjectSchedule);
		menuSettings.add(miSettingsSkill);
		menuSettings.add(miSettingsRisk);
		menuHelp.add(miUserManual);
		menuAbout.add(miAbout);

		this.add(menuProject);
		this.add(menuSettings);
		this.add(menuAbout);
	}
	
	public JMenuItem getSchedulingButton() {
		return this.miProjectSchedule;
		
	}
	
	public void addControllers(ActionListener controller) {
		miSettingsSkill.addActionListener(controller);
		miSettingsRisk.addActionListener(controller);
		miProjectSchedule.addActionListener(controller);
		miUserManual.addActionListener(controller);
		miAbout.addActionListener(controller);
	}
}
