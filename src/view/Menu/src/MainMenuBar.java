
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

	private JMenuBar Menu;
	private String Path, savePath;
	private Color ColourA = new Color(0xC3BEFA);
	private Color ColourB = new Color(0x6052FF);

	private JMenu MenuFile = new JMenu("File");
	private JButton MenuNew = new JButton("New");
	private JButton MenuOpen = new JButton("Open");
	private JButton MenuSave = new JButton("Save");
	private JButton MenuClose = new JButton("Close");
	private JMenu MenuView = new JMenu("View");
	private JMenu MenuSettings = new JMenu("Settings");
	private JMenu MenuContainer = new JMenu();

	// TODO create menu items here

	public MainMenuBar() {
		MenuListeners action = new MenuListeners(this);
		// TODO add menu items to corresponding menu

		// settings for File Menu
		MenuFile.setForeground(Color.white);

		// settings for New Menu
		MenuNew.setBackground(Color.white);
		MenuNew.setForeground(ColourB);
		MenuNew.addActionListener(action);
		MenuNew.setMaximumSize(new Dimension(70, 40));
		MenuFile.add(MenuNew);

		// setting for Open Menu
		MenuOpen = new JButton("Open");
		MenuOpen.setBackground(Color.white);
		MenuOpen.setForeground(ColourB);
		MenuOpen.setMaximumSize(new Dimension(70, 40));
		MenuOpen.addActionListener(action);
		MenuFile.add(MenuOpen);

		// settings for Save Menu

		MenuSave.setBackground(Color.white);
		MenuSave.setForeground(ColourB);
		MenuSave.setMaximumSize(new Dimension(70, 40));
		
		MenuSave.addActionListener(action);
		MenuFile.add(MenuSave);

		MenuClose = new JButton("Close");
		MenuClose.setBackground(Color.white);
		MenuClose.setForeground(ColourB);
		MenuClose.setMaximumSize(new Dimension(70, 40));
		MenuFile.add(MenuClose);
		MenuClose.addActionListener(action);
		Menu.add(MenuFile);

		/*MenuView = new JMenu("View");
		MenuView.setForeground(Color.white);
		JCheckBox inputpanel = new JCheckBox("Input Panel");
		inputpanel.setSelected(true);
		inputpanel.addItemListener(new enabledInput());
		MenuView.add(inputpanel);

		JCheckBox outputpanel = new JCheckBox("Output Panel");
		outputpanel.setSelected(true);
		outputpanel.addItemListener(new enableOutput());
		MenuView.add(outputpanel);

		JCheckBox picpanel = new JCheckBox("Map Panel");
		picpanel.setSelected(true);
		picpanel.addItemListener(new enablepic());
		MenuView.add(picpanel);

		Menu.add(MenuView);*/

		MenuView.setForeground(Color.white);

	}

}
