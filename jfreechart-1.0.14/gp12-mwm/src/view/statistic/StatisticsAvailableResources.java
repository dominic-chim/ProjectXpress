package view.statistic;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StatisticsAvailableResources {

	public JPanel panel;

	public StatisticsAvailableResources() {
		Object rows[][] = { { "Dominic Chim", "Developer", "2", "12" },
				{ "Ross Jarvis", "Developer", "2", "8" },
				{ "Bob Chen", "Database", "1", "13" },
				{ "Samy Driss", "Documentation", "5", "9" },
				{ "David Lin", "Tester", "1", "5" },
				{ "Dominic Chim", "Developer", "5", "12" },
				{ "Ross Jarvis", "Developer", "3", "8" },
				{ "Bob Chen", "Database", "4", "13" },
				{ "Samy Driss", "Documentation", "2", "9" },
				{ "David Lin", "Tester", "3", "5" } };
		Object columns[] = { "Employee Name", "Specialization",
				"Project Allocation",
				"Number of Remaining Working Hours (week)" };
		JTable table = new JTable(rows, columns);
		JScrollPane spTable = new JScrollPane(table);
		panel = new JPanel();
		panel.add(spTable);
	}

}
