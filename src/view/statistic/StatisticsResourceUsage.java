package view.statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import database.dataAccessObject.StatisticsDao;

public class StatisticsResourceUsage extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064688526261690032L;
	public JPanel panel = new JPanel(new GridLayout(2, 1));
	public JPanel panel2 = new JPanel(new GridLayout(1, 2));
	public JPanel panel3 = new JPanel();

	JTable table;
	final StatisticsDao stats = new StatisticsDao();

	final CategoryDataset usageData = totalUsageDataset();
	final JFreeChart usageChart = totalUsageChart(usageData);
	final ChartPanel chartPanel = new ChartPanel(usageChart);

	final CategoryDataset mUsageData = multipleUsageDataset();
	final JFreeChart mUsageChart = multipleUsageChart(mUsageData);
	final ChartPanel mUsagePanel = new ChartPanel(mUsageChart);

	final CategoryDataset dataset = createDataset();
	final JFreeChart chart = taskBarChart(dataset);
	final ChartPanel taskPanel = new ChartPanel(chart);

	final PieDataset piedata = pieDataset();
	final JFreeChart piechart = createPieChart(piedata);
	final ChartPanel piePanel = new ChartPanel(piechart);

	final JTabbedPane usage = new JTabbedPane();
	TitledBorder topBorder;

	public StatisticsResourceUsage(final String title) {
		super(title);

		Object rows[][] = stats.allStats();
		Object columns[] = { "Staff ID", "Staff Name", "No. of Task Allocations",
				"No. of Project Allocations", "Weekly Available Time" };
		DefaultTableModel model = new DefaultTableModel(rows, columns);
		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		JScrollPane spTable = new JScrollPane(table);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		table.setRowSorter(sorter);

		topBorder = BorderFactory
				.createTitledBorder("Total Resource Usage Per Day");
		topBorder.setTitlePosition(TitledBorder.TOP);
		chartPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Total Resource Usage Per Day (Per Staff)");
		mUsagePanel.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("Staff Usage");
		spTable.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("Task Count By Staff");
		taskPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Staff Distribution By Project");
		piePanel.setBorder(topBorder);

		usage.addTab("Total Usage", chartPanel);
		usage.addTab("Multiple Staff Usage", mUsagePanel);
		usage.addTab("Staff Usage", spTable);

		chartPanel.setPreferredSize(new Dimension(850, 200));
		mUsagePanel.setPreferredSize(new Dimension(850, 200));
		spTable.setPreferredSize(new Dimension(850, 200));

		panel3.add(usage);
		panel2.add(piePanel);
		panel2.add(taskPanel);

		chartPanel.setPreferredSize(new Dimension(800, 250));
		mUsagePanel.setPreferredSize(new Dimension(100, 100));

		panel.setPreferredSize(new Dimension(100, 100));
		panel.add(panel3);
		panel.add(panel2);
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

	private CategoryDataset createDataset() {

		String staffName = "Staff Name";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Object> statsData = stats.taskCountStaff();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.addValue((Number) row.get(1), staffName, row.get(0)
					.toString());
		}

		// SELECT skill_name, COUNT(*) AS Total FROM staff_skill_level NATURAL
		// JOIN skill GROUP BY skill_name;
		return dataset;
	}

	private PieDataset pieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Object> statsData = stats.staffCountProject();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.setValue(row.get(0).toString(), (Number) row.get(1));
		}

		// SELECT project_name, COUNT(DISTINCT(staff_id)) FROM project NATURAL
		// JOIN scheduling_result GROUP BY project_name;

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

	private static JFreeChart createPieChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createRingChart(null, dataset, true,
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		StandardPieSectionLabelGenerator labels = new StandardPieSectionLabelGenerator(
				"{2}");

		plot.setLabelGenerator(labels);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No Data Available");
		plot.setCircular(true);
		plot.setLabelGap(0.02);
		plot.setShadowXOffset(0);
		plot.setShadowYOffset(0);
		plot.setSimpleLabels(true);
		return chart;

	}

	private static JFreeChart taskBarChart(CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart(null,

		"Staff Name", "Number of Tasks", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.white);
		plot.setNoDataMessage("No Data Available");

		CategoryAxis domainAxis = ((CategoryPlot) plot).getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

		StackedBarRenderer renderer = new StackedBarRenderer(false);
		renderer.setDrawBarOutline(false);
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(78, 130, 190));
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setShadowVisible(false);
		chart.getCategoryPlot().setRenderer(renderer);

		return chart;

	}

}
