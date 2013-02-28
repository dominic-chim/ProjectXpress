package view.staff;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import data.dataObject.ProjectDO;

public class StaffSummary extends JPanel {

	public StaffSummary() {

		createTable();

	}

	public void createTable() {

		ListSelectionModel listSelectionModel;

		String[] columnNames = { "Staff", "Allocated Project" };

		Object[][] data = {};

		JTable allocationTable;
		DefaultTableModel allocationModel;
		allocationModel = new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 1L;

			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		allocationTable = new JTable(allocationModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		listSelectionModel = allocationTable.getSelectionModel();
		allocationTable.setSelectionModel(listSelectionModel);

		HashMap<String, ArrayList<ProjectDO>> staffAllocProjects = new HashMap<String, ArrayList<String>>();
		
		for(String staff : staffAllocProjects.keySet()) {
			for(String projects: staffAllocProjects.get(staff)) {				
				allocationModel.addRow(new Object[] { staff, projects });
			}
		}
		
		this.add(allocationTable);

	}

	
	
	public void addController(ActionListener controller) {

	}
}
