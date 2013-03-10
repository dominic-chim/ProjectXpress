package view.statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
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

public class StatisticsProductivity extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3596282676177435508L;

	StatisticsDao stats = new StatisticsDao();

	GridBagLayout grid = new GridBagLayout();
	public JPanel panel = new JPanel();
	public JPanel panel2 = new JPanel(new GridLayout(2, 1));
	
	final CategoryDataset dataset = createDataset();
	final JFreeChart chart = projectChart(dataset);
	final ChartPanel chartPanel = new ChartPanel(chart);

	final PieDataset piedata = pieDataset();
	final JFreeChart piechart = createPieChart(piedata);
	final ChartPanel piePanel = new ChartPanel(piechart);

	final PieDataset projectdata = projectDataset();
	final JFreeChart projectchart = projectChart(projectdata);
	final ChartPanel projectPanel = new ChartPanel(projectchart);

	final PieDataset taskdata = taskDataset();
	final JFreeChart taskchart = taskChart(taskdata);
	final ChartPanel taskPanel = new ChartPanel(taskchart);

	final JTabbedPane status = new JTabbedPane();
	TitledBorder topBorder;

	public StatisticsProductivity(final String title) {
		super(title);
		topBorder = BorderFactory
				.createTitledBorder("Project Productivity In Hours");
		topBorder.setTitlePosition(TitledBorder.TOP);
		chartPanel.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("Risk Distribution");
		piePanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Project Status Distribution");
		projectPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Task Status Distribution");
		taskPanel.setBorder(topBorder);

		status.addTab("Project Status", projectPanel);
		status.addTab("Task Status", taskPanel);

		chartPanel.setPreferredSize(new Dimension(500, 560));
		piePanel.setPreferredSize(new Dimension(300, 250));
		projectPanel.setPreferredSize(new Dimension(350, 250));
		taskPanel.setPreferredSize(new Dimension(350, 250));

		panel2.add(piePanel);
		panel2.add(status);

		panel.setLayout(grid);
		panel.add(chartPanel);
		panel.add(panel2);
		add(panel);

	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();

		 result.addValue(13, "Hours Completed", "Database Creation");
		 result.addValue(25, "Hours Remaining", "Database Creation");
		 result.addValue(17, "Hours Completed", "Testing");
		 result.addValue(13, "Hours Remaining", "Testing");
		 result.addValue(27, "Hours Completed", "Express");
		 result.addValue(30, "Hours Remaining", "Express");
		 result.addValue(44, "Hours Completed", "Management");
		 result.addValue(40, "Hours Remaining", "Management");
		 result.addValue(44, "Hours Completed", "Management1");
		 result.addValue(40, "Hours Remaining", "Management1");
		 result.addValue(44, "Hours Completed", "Management2");
		 result.addValue(40, "Hours Remaining", "Management2");
		 result.addValue(44, "Hours Completed", "Management3");
		 result.addValue(40, "Hours Remaining", "Management3");
		 result.addValue(44, "Hours Completed", "Management4");
		 result.addValue(40, "Hours Remaining", "Management4");
		 result.addValue(44, "Hours Completed", "Management5");
		 result.addValue(40, "Hours Remaining", "Management5");
		 result.addValue(44, "Hours Completed", "Management6");
		 result.addValue(40, "Hours Remaining", "Management6");
		 result.addValue(44, "Hours Completed", "Management7");
		 result.addValue(40, "Hours Remaining", "Management7");
		 result.addValue(44, "Hours Completed", "Management8");
		 result.addValue(40, "Hours Remaining", "Management8");
		 result.addValue(44, "Hours Completed", "Management9");
		 result.addValue(40, "Hours Remaining", "Management9");
		 result.addValue(44, "Hours Completed", "Management11");
		 result.addValue(40, "Hours Remaining", "Management11");
		return result;
	}

	private PieDataset pieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Object> statsData = stats.riskStats();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.setValue(row.get(0).toString(), (Number) row.get(1));
		}

		// SELECT task_risk_level, COUNT(*)/(SELECT COUNT(*) FROM task)*100 AS
		// percentage FROM task GROUP BY task_risk_level;

		return dataset;
	}

	private PieDataset projectDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		ArrayList<Object> statsData = stats.projectStatusStats();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.setValue(row.get(0).toString(), (Number) row.get(1));
		}
		// SELECT project_status, COUNT(*)/(SELECT COUNT(*) FROM project)*100 AS
		// percentage FROM project GROUP BY project_status;
		return dataset;
	}

	private PieDataset taskDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Object> statsData = stats.taskStatusStats();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.setValue(row.get(0).toString(), (Number) row.get(1));
		}
		// SELECT task_status, COUNT(*)/(SELECT COUNT(*) FROM task)*100 AS
		// percentage FROM task GROUP BY task_status;
		return dataset;
	}

	private JFreeChart projectChart(final CategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createStackedBarChart(null,
				"Projects", "Number Of Hours", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot plot = chart.getCategoryPlot();
		StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		chart.getCategoryPlot().setRenderer(renderer);

		CategoryAxis domainAxis = ((CategoryPlot) plot).getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		plot.setNoDataMessage("No Data Available");
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(78, 130, 190));
		renderer.setSeriesPaint(1, new Color(189, 81, 78));
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
		plot.setSectionPaint("High", new Color(189, 81, 78));
		plot.setSectionPaint("Medium", new Color(252, 192, 6));
		plot.setSectionPaint("Low", new Color(78, 130, 190));
		return chart;

	}

	private static JFreeChart projectChart(PieDataset datasets) {

		JFreeChart chart = ChartFactory.createPieChart(null, datasets, true,
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		StandardPieSectionLabelGenerator labels = new StandardPieSectionLabelGenerator(
				"{2}");
		plot.setLabelGenerator(labels);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No Data Available");
		plot.setCircular(true);
		plot.setLabelGap(0.2);
		plot.setShadowXOffset(0);
		plot.setShadowYOffset(0);
		plot.setSimpleLabels(true);
		plot.setSectionPaint("Not Started", new Color(189, 81, 78));
		plot.setSectionPaint("In Progress", new Color(252, 192, 6));
		plot.setSectionPaint("Completed", new Color(78, 130, 190));
		return chart;

	}

	private static JFreeChart taskChart(PieDataset datasets) {

		JFreeChart chart = ChartFactory.createPieChart(null, datasets, true,
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		StandardPieSectionLabelGenerator labels = new StandardPieSectionLabelGenerator(
				"{2}");
		plot.setLabelGenerator(labels);
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No Data Available");
		plot.setCircular(true);
		plot.setLabelGap(0.2);
		plot.setShadowXOffset(0);
		plot.setShadowYOffset(0);
		plot.setSimpleLabels(true);
		plot.setSectionPaint("Not Started", new Color(189, 81, 78));
		plot.setSectionPaint("In Progress", new Color(252, 192, 6));
		plot.setSectionPaint("Completed", new Color(78, 130, 190));
		return chart;

	}

}
