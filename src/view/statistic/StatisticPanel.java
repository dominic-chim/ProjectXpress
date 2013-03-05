package view.statistic;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class StatisticPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel topPanel = new JPanel();

	private StatisticsAvailableResources statAvailableR = new StatisticsAvailableResources("");
	private StatisticsProductivity statProductivity = new StatisticsProductivity("");
	private StatisticsResourceUsage statReseourceU = new StatisticsResourceUsage("");

	public StatisticPanel() {

		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		topPanel.setLayout(new BorderLayout());

		tabbedPane.addTab("Available Resources", statAvailableR.panel);
		tabbedPane.addTab("Productivity", statProductivity.panel);
		tabbedPane.addTab("Resource Usage", statReseourceU.panel);

		topPanel.add(tabbedPane, BorderLayout.WEST);
		//topPanel.setPreferredSize(new Dimension(900, 700));
		add(topPanel);
	}

}