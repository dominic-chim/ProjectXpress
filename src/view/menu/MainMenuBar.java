package menu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class MainMenuBar {

	// ?
	// private String Path, savePath;

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
		JMenuBar Menu = new JMenuBar();
		Menu.setBackground(ColourB);
	}

	public JMenuBar MainMenuCreate() {

		// MenuListeners action = new MenuListeners(this);

		// settings for File Menu
		menuFile.setForeground(Color.white);

		// settings for New Menu
		miFileNew.setBackground(Color.white);
		miFileNew.setForeground(ColourB);
		// MenuNew.addActionListener(action);
		miFileNew.setMaximumSize(new Dimension(70, 40));
		menuFile.add(miFileNew);

		// setting for Open Menu
		// miFileOpen = new JButton("Open");
		miFileOpen.setBackground(Color.white);
		miFileOpen.setForeground(ColourB);
		miFileOpen.setMaximumSize(new Dimension(70, 40));
		// MenuOpen.addActionListener(action);
		menuFile.add(miFileOpen);

		// settings for Save Menu

		miFileSave.setBackground(Color.white);
		miFileSave.setForeground(ColourB);
		miFileSave.setMaximumSize(new Dimension(70, 40));

		// MenuSave.addActionListener(action);
		menuFile.add(miFileSave);

		// miFileClose = new JButton("Close");
		miFileClose.setBackground(Color.white);
		miFileClose.setForeground(ColourB);
		miFileClose.setMaximumSize(new Dimension(70, 40));
		menuFile.add(miFileClose);
		// MenuClose.addActionListener(action);

		Menu.add(menuFile);
		Menu.add(menuView);
		Menu.add(menuSettings);

		/*
		 * MenuView = new JMenu("View"); MenuView.setForeground(Color.white);
		 * JCheckBox inputpanel = new JCheckBox("Input Panel");
		 * inputpanel.setSelected(true); inputpanel.addItemListener(new
		 * enabledInput()); MenuView.add(inputpanel);
		 * 
		 * JCheckBox outputpanel = new JCheckBox("Output Panel");
		 * outputpanel.setSelected(true); outputpanel.addItemListener(new
		 * enableOutput()); MenuView.add(outputpanel);
		 * 
		 * JCheckBox picpanel = new JCheckBox("Map Panel");
		 * picpanel.setSelected(true); picpanel.addItemListener(new
		 * enablepic()); MenuView.add(picpanel);
		 * 
		 * Menu.add(MenuView);
		 */

		menuView.setForeground(Color.white);
		return Menu;
	}

}
