package view.staff;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;

public class StaffAllocation extends JPanel {

	JTable allocationTable;
	DefaultTableModel allocationModel;
	
	public StaffAllocation() {
		
		setLayout(new BorderLayout());

		ListSelectionModel listSelectionModel;

		String[] columnNames = { "Staff", "Allocated Project" };

		Object[][] data = {};

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

	
		
		
		add(allocationTable, BorderLayout.CENTER);
		setVisible(true);
		

	}
	
	public void addStaffAllocatedProjects(HashMap<StaffDO , ArrayList<ProjectDO>> staffAllocProjects)	{
		
		for(StaffDO staff : staffAllocProjects.keySet()) {
			for(ProjectDO projects: staffAllocProjects.get(staff)) {				
				allocationModel.addRow(new Object[] {staff, projects.getProjectName()});
			}
		}
		
	}
	
	
	public void addController(ActionListener controller) {

	}

}
