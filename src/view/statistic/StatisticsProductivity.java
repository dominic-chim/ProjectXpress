package view.statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
	public JPanel panel3 = new JPanel(new GridLayout(2, 1));
	public JPanel panel4 = new JPanel();

	JTable table, table2, table3, table4;

	final CategoryDataset dataset = createDataset();
	final JFreeChart chart = projectChart(dataset);
	final ChartPanel chartPanel = new ChartPanel(chart);

	final PieDataset piedata = pieDataset();
	final JFreeChart piechart = createPieChart(piedata);
	final ChartPanel piePanel = new ChartPanel(piechart);

	final PieDataset projectdata = projectDataset();
	final JFreeChart projectchart = projectChart(projectdata);
	final ChartPanel projectPanel = new ChartPanel(projectchart);

	final JTabbedPane status = new JTabbedPane();
	final JTabbedPane tables = new JTabbedPane();
	TitledBorder topBorder;

	public StatisticsProductivity(final String title) {
		super(title);

		Object rows[][] = stats.scheduledProjects();
		Object columns[] = { "Project ID", "Project Name", "Priority",
				"No. of Tasks", "Start Date", "End Date" };
		DefaultTableModel model = new DefaultTableModel(rows, columns);
		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		Object rows2[][] = stats.projectsList();
		Object columns2[] = { "Project ID", "Project Name", "Priority",
				"Project Status" };
		DefaultTableModel model2 = new DefaultTableModel(rows2, columns2);
		table2 = new JTable(model2) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		Object rows3[][] = stats.scheduledTasks();
		Object columns3[] = { "Task ID", "Task Name", "Staff Allocation",
				"Start Date", "End Date", "Risk Level" };
		DefaultTableModel model3 = new DefaultTableModel(rows3, columns3);
		table3 = new JTable(model3) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		Object rows4[][] = stats.tasksList();
		Object columns4[] = { "Task ID", "Task Name", "Project",
				"Required Skill", "Duration (hrs)", "Task Status" };
		DefaultTableModel model4 = new DefaultTableModel(rows4, columns4);
		table4 = new JTable(model4) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		JScrollPane spTable = new JScrollPane(table);
		JScrollPane spTable2 = new JScrollPane(table2);
		JScrollPane spTable3 = new JScrollPane(table3);
		JScrollPane spTable4 = new JScrollPane(table4);

		topBorder = BorderFactory
				.createTitledBorder("Project Productivity by Task Status");
		topBorder.setTitlePosition(TitledBorder.TOP);
		chartPanel.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("Risk Distribution");
		piePanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Project Status Distribution");
		projectPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("List of Scheduled Projects");
		spTable.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("List of Projects");
		spTable2.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("List of Scheduled Tasks");
		spTable3.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("List of Tasks");
		spTable4.setBorder(topBorder);

		tables.addTab("Scheduled Projects", spTable);
		tables.addTab("All Projects", spTable2);
		tables.addTab("Scheduled Tasks", spTable3);
		tables.addTab("All Tasks", spTable4);

		panel3.setPreferredSize(new Dimension(500, 560));
		spTable.setPreferredSize(new Dimension(500, 260));
		piePanel.setPreferredSize(new Dimension(350, 290));
		projectPanel.setPreferredSize(new Dimension(350, 290));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(75);

		table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table4.getColumnModel().getColumn(0).setPreferredWidth(55);
		table4.getColumnModel().getColumn(1).setPreferredWidth(80);
		table4.getColumnModel().getColumn(2).setPreferredWidth(70);
		table4.getColumnModel().getColumn(3).setPreferredWidth(90);
		table4.getColumnModel().getColumn(4).setPreferredWidth(87);
		table4.getColumnModel().getColumn(5).setPreferredWidth(75);

		panel3.add(chartPanel);
		panel3.add(tables);
		panel2.add(piePanel);
		panel2.add(projectPanel);

		panel.setLayout(grid);
		panel.add(panel3);
		panel.add(panel2);
		add(panel);

	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// DefaultCategoryDataset result = new DefaultCategoryDataset();

		// String staffName = "Staff Name";
		ArrayList<Object> statsData = stats.projectProductivity();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.addValue((Number) row.get(0), row.get(1).toString(), row
					.get(2).toString());
		}


		return dataset;
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
				"Projects", "Tasks", dataset,
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


}
