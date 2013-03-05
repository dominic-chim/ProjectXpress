package view.statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class StatisticsResourceUsage extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064688526261690032L;
	public JPanel panel = new JPanel(new GridLayout(2, 1));
	public JPanel panel2 = new JPanel();
	public JPanel panel3 = new JPanel();

	final CategoryDataset usageData = totalUsageDataset();
	final JFreeChart usageChart = totalUsageChart(usageData);
	final ChartPanel chartPanel = new ChartPanel(usageChart);

	final CategoryDataset mUsageData = multipleUsageDataset();
	final JFreeChart mUsageChart = multipleUsageChart(mUsageData);
	final ChartPanel mUsagePanel = new ChartPanel(mUsageChart);

	final JTabbedPane usage = new JTabbedPane();
	TitledBorder topBorder;

	public StatisticsResourceUsage(final String title) {
		super(title);

		topBorder = BorderFactory
				.createTitledBorder("Total Resource Usage Per Day");
		topBorder.setTitlePosition(TitledBorder.TOP);
		chartPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Total Resource Usage Per Day (Per Staff)");
		mUsagePanel.setBorder(topBorder);

		usage.addTab("Total Usage", chartPanel);
		usage.addTab("Multiple Staff Usage", mUsagePanel);

		panel3.add(usage);

		chartPanel.setPreferredSize(new Dimension(800, 250));
		mUsagePanel.setPreferredSize(new Dimension(100, 100));

		panel.setPreferredSize(new Dimension(100, 100));
		panel.add(panel2);
		panel.add(panel3);
	}

	private CategoryDataset totalUsageDataset() {

		final String Staff = "Samy Driss";

		final String Day1 = "Day 1";
		final String Day2 = "Day 2";
		final String Day3 = "Day 3";
		final String Day4 = "Day 4";
		final String Day5 = "Day 5";
		final String Day6 = "Day 6";
		final String Day7 = "Day 7";
		final String Day8 = "Day 8";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(1.0, Staff, Day1);
		dataset.addValue(4.0, Staff, Day2);
		dataset.addValue(3.0, Staff, Day3);
		dataset.addValue(5.0, Staff, Day4);
		dataset.addValue(5.0, Staff, Day5);
		dataset.addValue(7.0, Staff, Day6);
		dataset.addValue(7.0, Staff, Day7);
		dataset.addValue(8.0, Staff, Day8);

		return dataset;

	}

	private CategoryDataset multipleUsageDataset() {

		final String Staff = "Samy Driss";
		final String Staff2 = "Bob Chen";
		final String Staff3 = "Ross Jarvis";

		final String Day1 = "Day 1";
		final String Day2 = "Day 2";
		final String Day3 = "Day 3";
		final String Day4 = "Day 4";
		final String Day5 = "Day 5";
		final String Day6 = "Day 6";
		final String Day7 = "Day 7";
		final String Day8 = "Day 8";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(1.0, Staff, Day1);
		dataset.addValue(4.0, Staff, Day2);
		dataset.addValue(3.0, Staff, Day3);
		dataset.addValue(5.0, Staff, Day4);
		dataset.addValue(5.0, Staff, Day5);
		dataset.addValue(7.0, Staff, Day6);
		dataset.addValue(7.0, Staff, Day7);
		dataset.addValue(8.0, Staff, Day8);

		dataset.addValue(5.0, Staff2, Day1);
		dataset.addValue(7.0, Staff2, Day2);
		dataset.addValue(6.0, Staff2, Day3);
		dataset.addValue(8.0, Staff2, Day4);
		dataset.addValue(4.0, Staff2, Day5);
		dataset.addValue(4.0, Staff2, Day6);
		dataset.addValue(2.0, Staff2, Day7);
		dataset.addValue(1.0, Staff2, Day8);

		dataset.addValue(4.0, Staff3, Day1);
		dataset.addValue(3.0, Staff3, Day2);
		dataset.addValue(2.0, Staff3, Day3);
		dataset.addValue(3.0, Staff3, Day4);
		dataset.addValue(6.0, Staff3, Day5);
		dataset.addValue(3.0, Staff3, Day6);
		dataset.addValue(4.0, Staff3, Day7);
		dataset.addValue(3.0, Staff3, Day8);

		return dataset;

	}

	private JFreeChart totalUsageChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createLineChart(null, "Day",
				"Staff", dataset, PlotOrientation.VERTICAL, true, true, false);

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		plot.setNoDataMessage("No Data Available");

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);

		return chart;
	}

	private JFreeChart multipleUsageChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createLineChart(null, "Day",
				"Staff", dataset, PlotOrientation.VERTICAL, true, true, false);

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		plot.setNoDataMessage("No Data Available");

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);

		return chart;
	}

}
