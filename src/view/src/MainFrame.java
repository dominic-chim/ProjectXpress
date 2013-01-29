import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import menu.MainMenuBar;
import project.ProjectPanel;
import staff.StaffPanel;
import statistic.StatisticPanel;

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
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1913422545964126349L;

	// the main menu bar
	private MainMenuBar menuBar = new MainMenuBar();

	// the container contains all tabs
	private JTabbedPane mainTabbedPane = new JTabbedPane();

	// component in mainTabbedPane
	private ProjectPanel projectPanel = new ProjectPanel();
	private StaffPanel staffPanel = new StaffPanel();
	private StatisticPanel statisticPanel = new StatisticPanel();

	public MainFrame() {

		setLayout(new BorderLayout());

		add(menuBar.MainMenuCreate(), BorderLayout.NORTH);

		// add tabs to tabbed pane
		mainTabbedPane.addTab("Project", projectPanel);
		mainTabbedPane.addTab("Staff", staffPanel);
		mainTabbedPane.addTab("Statistical Reports", statisticPanel);

		add(mainTabbedPane, BorderLayout.CENTER);

		// setSize(600, 600);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
