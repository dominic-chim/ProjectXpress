package view.statistic;
import java.awt.*;

import javax.swing.*;

public class StatisticPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel topPanel = new JPanel();

	private StatisticsAvailableResources statAvailableR = new StatisticsAvailableResources();
	private StatisticsProductivity statProductivity = new StatisticsProductivity("");
	private StatisticsResourceUsage statReseourceU = new StatisticsResourceUsage("");

	public StatisticPanel() {

		topPanel.setLayout(new BorderLayout());

		tabbedPane.addTab("Available Resources", statAvailableR.panel);
		tabbedPane.addTab("Productivity", statProductivity.panel);
		tabbedPane.addTab("Resource Usage", statReseourceU.panel);

		topPanel.add(tabbedPane, BorderLayout.CENTER);
		add(topPanel);
	}

}