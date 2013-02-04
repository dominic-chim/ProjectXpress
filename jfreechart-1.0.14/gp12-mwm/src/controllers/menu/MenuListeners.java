package controllers.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import view.menu.MainMenuBar;


public class MenuListeners implements ActionListener {
	MainMenuBar menu;
	
	public MenuListeners(MainMenuBar mainMenuBar)  {
		menu = mainMenuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		final JFileChooser jfc = new JFileChooser();

		switch(cmd){
		case "New" :
			//reset everything -- does meant current data is deleted
			break;
		case "Open":
			//open file in w/e format we choose
			break;
		case "Save":
			//save current data to a format
			break;
		case "Close":
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Option");
		}
		
	}

}
