package view.statistic;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class StatisticsProductivity extends ApplicationFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3596282676177435508L;
	JPanel panel = new JPanel();

	public StatisticsProductivity(final String title) {
		super(title);
		panel.setLayout(new java.awt.BorderLayout());
		final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.validate();
	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();

		result.addValue(13, "Hours Completed", "Project 1");
		result.addValue(25, "Hours Remaining", "Project 1");
		result.addValue(17, "Hours Completed", "Project 2");
		result.addValue(13, "Hours Remaining", "Project 2");
		result.addValue(27, "Hours Completed", "Project 3");
		result.addValue(30, "Hours Remaining", "Project 3");
		result.addValue(44, "Hours Completed", "Project 4");
		result.addValue(40, "Hours Remaining", "Project 4");
		result.addValue(25, "Hours Completed", "Project 5");
		result.addValue(13, "Hours Remaining", "Project 5");
		return result;
	}

	private JFreeChart createChart(final CategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createStackedBarChart(
				"Staff Productivity", "Projects", "No. Of Hours", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.getRenderer().setSeriesPaint(0, new Color(0, 0, 255));
		plot.getRenderer().setSeriesPaint(1, new Color(255, 0, 0));
		return chart;
	}

}
