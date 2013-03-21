package view.statistic;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.ui.ApplicationFrame;
import database.dataAccessObject.StatisticsDao;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
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

/**
 * 
 * gui for statistic availability resources tab
 * 
 * @author Samy
 *
 */
public class StatisticsAvailableResources extends ApplicationFrame {


	private static final long serialVersionUID = 1L;
	public JPanel panel = new JPanel(new GridLayout(1, 2));
	public JPanel panel2 = new JPanel(new GridLayout(2, 1));
	public JPanel panel3 = new JPanel();
	public JPanel panel4 = new JPanel();
	JTable table;
	TitledBorder topBorder;
	final StatisticsDao stats = new StatisticsDao();

	final CategoryDataset dataset = createDataset();
	final JFreeChart chart = createChart(dataset);
	final ChartPanel chartPanel = new ChartPanel(chart);

	final PieDataset piedata = pieDataset();
	final JFreeChart piechart = createChart(piedata);
	final ChartPanel piePanel = new ChartPanel(piechart);

	public StatisticsAvailableResources(final String title) {
		super(title);
		Object rows[][] = stats.availableStats();
		Object columns[] = { "Staff ID", "Staff Name", "Skill Lvl",
				"Skill Name"};
		DefaultTableModel model = new DefaultTableModel(rows, columns);
		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		JScrollPane spTable = new JScrollPane(table);
		topBorder = BorderFactory
				.createTitledBorder("Staff Skills");
		topBorder.setTitlePosition(TitledBorder.TOP);
		spTable.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Weekly Available Time By Staff");
		chartPanel.setBorder(topBorder);
		topBorder = BorderFactory
				.createTitledBorder("Staff Distribution By Skill");
		piePanel.setBorder(topBorder);

		spTable.setPreferredSize(new Dimension(10, 10));
		piePanel.setPreferredSize(new Dimension(250, 250));
		chartPanel.setPreferredSize(new Dimension(400, 560));

		panel3.add(chartPanel);
		panel2.add(spTable);
		panel2.add(piePanel);
		panel.add(panel2);
		panel.add(panel3);

		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(60);
		table.setRowSorter(sorter);

	};

	private CategoryDataset createDataset() {

		String staffName = "Staff Name";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Object> statsData = stats.timeAvailablitlyStaff();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.addValue((Number) row.get(1), staffName, row.get(0)
					.toString());
		}


		return dataset;
	}

	private PieDataset pieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Object> statsData = stats.skillStaffCount();
		for (int i = 0; i < statsData.size(); i++) {
			ArrayList<Object> row = (ArrayList<Object>) statsData.get(i);
			dataset.setValue(row.get(0).toString(), (Number) row.get(1));
		}


		return dataset;
	}

	private static JFreeChart createChart(CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart(null,

		"Staff Name", "Number of Hours", dataset, PlotOrientation.VERTICAL,
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

	private static JFreeChart createChart(PieDataset piedataset) {
		JFreeChart jfreechart = ChartFactory.createPieChart(null, piedataset,
				true, true, false);
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		pieplot.setNoDataMessage("No Data Available");
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} {2}"));
		pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
		pieplot.setSimpleLabels(true);
		pieplot.setInteriorGap(0.0D);
		pieplot.setShadowXOffset(0);
		pieplot.setShadowYOffset(0);
		return jfreechart;
	}

}
