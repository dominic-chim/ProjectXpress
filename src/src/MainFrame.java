

import java.awt.BorderLayout;
import javax.swing.*;
import view.project.*;
import view.staff.*;
import view.statistic.*;
import view.menu.*;
import view.tab.*;

/**
 * 
 * main frame of the program contains a menu bar and a tabbedpane
 * 
 * the tabbed pane is used to hold tab: project, staff, statistical report
 * 
 * this class also holds references to tabs project, staff and statistical
 * report(maybe not useful)
 * 
 * @author Bob
 * 
 */

public class MainFrame  {

	// the container contains all tabs
	private JTabbedPane mainTabbedPane = new JTabbedPane();

	// component in mainTabbedPane
	private ProjectPanel projectPanel = new ProjectPanel();
	private StaffContainer staffPanel = new StaffContainer();
	private StatisticPanel statisticPanel = new StatisticPanel();

	public MainFrame() {
	}

	public JTabbedPane initial() {
		// add tabs to tabbed pane
		mainTabbedPane.addTab("Project", projectPanel);
		mainTabbedPane.addTab("Staff", staffPanel);
		mainTabbedPane.addTab("Statistical Reports", statisticPanel);
		mainTabbedPane.setUI(new MyTabbedPaneUI(mainTabbedPane));
		return mainTabbedPane;
	}

	//used if another class needs to interact with the tabbedpane in the future
	public JTabbedPane getTabs() {
		return mainTabbedPane;
	}

	private static void createAndShowGUI() {
		// the main menu bar
		MainMenuBar menuBar = new MainMenuBar();

		// Create and set up the window.

		MainFrame MF = new MainFrame();
		JFrame frame = new JFrame("PrjectExpress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		//add components to MainFrame
		frame.getContentPane().add(MF.initial(), BorderLayout.CENTER);
		frame.add(menuBar.MainMenuCreate(), BorderLayout.NORTH);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1024, 700);

	}
	
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
