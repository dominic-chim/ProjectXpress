package view.menu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import controllers.menu.*;

public class MainMenuBar {

	

	// predefined colors
	Color ColourA = new Color(0xC3BEFA);
	Color ColourB = new Color(0x6052FF);

	// menus
	JMenuBar Menu;
	private JMenu menuFile = new JMenu("File");
	private JMenu menuView = new JMenu("View");
	private JMenu menuSettings = new JMenu("Settings");

	// menu items
	private JMenuItem miFileNew = new JMenuItem("New");
	private JMenuItem miFileOpen = new JMenuItem("Open");
	private JMenuItem miFileSave = new JMenuItem("Save");
	private JMenuItem miFileClose = new JMenuItem("Close");

	public MainMenuBar() {
		Menu = new JMenuBar();
		Menu.setBackground(ColourB);
		
	}

	public JMenuBar MainMenuCreate() {


		// settings for File Menu options
		menuFile.setForeground(Color.white);

		// settings for New Menu
		miFileNew.setBackground(Color.white);
		miFileNew.setForeground(ColourB);
		 miFileNew.addActionListener(new MenuListeners(this));
		miFileNew.setMaximumSize(new Dimension(70, 40));
		menuFile.add(miFileNew);

		// setting for Open Menu
		miFileOpen.setBackground(Color.white);
		miFileOpen.setForeground(ColourB);
		miFileOpen.setMaximumSize(new Dimension(70, 40));
		miFileOpen.addActionListener(new MenuListeners(this));
		menuFile.add(miFileOpen);

		// settings for Save Menu
		miFileSave.setBackground(Color.white);
		miFileSave.setForeground(ColourB);
		miFileSave.setMaximumSize(new Dimension(70, 40));
		miFileSave.addActionListener(new MenuListeners(this));
		menuFile.add(miFileSave);

		//settings for Close Menu
		miFileClose.setBackground(Color.white);
		miFileClose.setForeground(ColourB);
		miFileClose.setMaximumSize(new Dimension(70, 40));
		miFileClose.addActionListener(new MenuListeners(this));
		menuFile.add(miFileClose);
	

		Menu.add(menuFile);
		menuView.setForeground(Color.white);
		Menu.add(menuView);
		menuSettings.setForeground(Color.white);
		Menu.add(menuSettings);

		menuView.setForeground(Color.white);
		return Menu;
	}

}
