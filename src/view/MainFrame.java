package view;

import java.awt.BorderLayout;

import javax.swing.*;

import view.project.*;
import view.staff.*;
import view.statistic.*;

public class MainFrame extends JFrame {
	
	private MainMenuBar menuBar = new MainMenuBar();
	
	private JTabbedPane mainTabbedPane = new  JTabbedPane();
	
	// component in mainTabbedPane
	private ProjectPanel projectPanel = new ProjectPanel();
	private StaffPanel staffPanel = new StaffPanel();
	private StatisticPanel statisticPanel = new StatisticPanel();
	
	public MainFrame() {
		
		setLayout(new BorderLayout());
		
		add(menuBar, BorderLayout.NORTH);
		
		// add tabs to tabbed pane
		mainTabbedPane.addTab("Project", projectPanel);
		mainTabbedPane.addTab("Staff", staffPanel);
		mainTabbedPane.addTab("Statistical Reports", statisticPanel);
		
		add(mainTabbedPane, BorderLayout.CENTER);
		
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
