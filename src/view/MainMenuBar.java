package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class MainMenuBar extends JMenuBar {

	private JMenu menuFile = new JMenu("File");
	private JMenu menuView = new JMenu("View");
	private JMenu menuSettings = new JMenu("Settings");
	
	// TODO create menu items here
	
	public MainMenuBar() {
		
		// TODO add menu items to corresponding menu 
		
		// add menus to menu bar
		add(menuFile);
		add(menuView);
		add(menuSettings);
	}
	
}