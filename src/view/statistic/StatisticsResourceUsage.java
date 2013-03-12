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
	public JPanel panel = new JPanel(new GridLayout(1, 2));
	public JPanel panel2 = new JPanel(new GridLayout(2, 1));
	public JPanel panel3 = new JPanel();

	JTable table;
	final StatisticsDao stats = new StatisticsDao();


	final CategoryDataset useddata = usedDataset();
	final JFreeChart usedchart = usedBarChart(useddata);
	final ChartPanel usedPanel = new ChartPanel(usedchart);

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
		Object columns[] = { "Staff ID", "Staff Name", "No. of Project Allocations",
				"No. of Skills" };
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
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setMaxWidth(160);
		table.getColumnModel().getColumn(3).setMaxWidth(120);
		table.setRowSorter(sorter);

		topBorder = BorderFactory
				.createTitledBorder("Staff Usage");
		topBorder.setTitlePosition(TitledBorder.TOP);

		spTable.setBorder(topBorder);
		topBorder = BorderFactory.createTitledBorder("Task Count By Staff");
		taskPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Staff Distribution By Project");
		piePanel.setBorder(topBorder);


		usage.addTab("Tasks Allocated By" +
				" Staff", taskPanel);
		usage.addTab("Time Usage For Tasks By Staff", usedPanel);


		spTable.setPreferredSize(new Dimension(10, 10));
		piePanel.setPreferredSize(new Dimension(250, 250));
		usage.setPreferredSize(new Dimension(400, 560));

		panel3.add(usage);
		panel2.add(piePanel);
		panel2.add(spTable);


		panel.setPreferredSize(new Dimension(100, 100));
		panel.add(panel2);
		panel.add(panel3);
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
	
	private CategoryDataset usedDataset() {

		String staffName = "Staff Name";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Object> statsData = stats.usedData();
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
	
	
	private static JFreeChart usedBarChart(CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart(null,

		"Staff Name", "Number of Hours Used", dataset, PlotOrientation.VERTICAL,
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
		renderer.setSeriesPaint(0, new Color(252, 192, 6));
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setShadowVisible(false);
		chart.getCategoryPlot().setRenderer(renderer);

		return chart;

	}

}
