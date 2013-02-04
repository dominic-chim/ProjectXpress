package view.statistic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class StatisticsResourceUsage extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064688526261690032L;
	JPanel panel = new JPanel();

	public StatisticsResourceUsage(final String title) {

		super(title);
		panel.setLayout(new java.awt.BorderLayout());

		final IntervalCategoryDataset dataset = createSampleDataset();

		final JFreeChart chart = ChartFactory.createGanttChart(
				"Resource Usage Statstics", "Task", "Date", dataset, true,
				true, false);
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();

		final CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.blue);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.validate();

	}

	private IntervalCategoryDataset createSampleDataset() {

		final TaskSeries s1 = new TaskSeries("Scheduled");

		final Task t1 = new Task("Project 1", date(1, Calendar.APRIL, 2013),
				date(5, Calendar.JULY, 2013));
		t1.setPercentComplete(0.60);
		s1.add(t1);

		final Task t2 = new Task("Project 2", date(9, Calendar.APRIL, 2013),
				date(20, Calendar.APRIL, 2013));
		t2.setPercentComplete(1.00);
		s1.add(t2);

		final Task t3 = new Task("Project 3", date(6, Calendar.MAY, 2013),
				date(30, Calendar.MAY, 2013));
		t3.setPercentComplete(0.30);
		s1.add(t3);

		final Task t4 = new Task("Project 4", date(2, Calendar.JUNE, 2013),
				date(12, Calendar.JUNE, 2013));
		t4.setPercentComplete(0.80);
		s1.add(t4);

		final Task t5 = new Task("Project 5", date(3, Calendar.JUNE, 2013),
				date(31, Calendar.JULY, 2013));
		t5.setPercentComplete(0.60);

		s1.add(t5);

		final Task t6 = new Task("Project 6", date(1, Calendar.AUGUST, 2013),
				date(24, Calendar.OCTOBER, 2013));
		t6.setPercentComplete(0.80);
		s1.add(t6);

		final Task t7 = new Task("Project 7",
				date(28, Calendar.SEPTEMBER, 2013), date(30, Calendar.DECEMBER,
						2013));
		t7.setPercentComplete(0.50);
		s1.add(t7);

		final TaskSeriesCollection collection = new TaskSeriesCollection();
		collection.add(s1);

		return collection;
	}

	private static Date date(final int day, final int month, final int year) {

		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}

}
