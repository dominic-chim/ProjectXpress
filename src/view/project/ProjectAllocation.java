package view.project;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import util.DateTime;
import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;

public class ProjectAllocation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable allocationTable;
	DefaultTableModel allocationModel;

	public ProjectAllocation() {

		setLayout(new BorderLayout());

		ListSelectionModel listSelectionModel;

		String[] columnNames = { "Projects", "Allocated Staff" };

		Object[][] data = {};

		allocationModel = new DefaultTableModel(data, columnNames) {

			private static final long serialVersionUID = 1L;

			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		allocationTable = new JTable(allocationModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		listSelectionModel = allocationTable.getSelectionModel();
		allocationTable.setSelectionModel(listSelectionModel);

		JScrollPane scrollPane = new JScrollPane(allocationTable);
		add(scrollPane, BorderLayout.CENTER);

		// read data from db
		HashMap<ProjectDO, ArrayList<ResultDO>> dataToShow = new HashMap<ProjectDO, ArrayList<ResultDO>>();
		ResultDao resultDB = new ResultDao();
		ProjectDao projectDB = new ProjectDao();

		ArrayList<Integer> projectIds = resultDB
				.getAllProjectInCurrentVersion();

		for (int projectId : projectIds) {
			dataToShow.put(projectDB.getProjectById(projectId),
					resultDB.getResultByProject(projectId));
		}

		addProjectsAllocatedStaff(dataToShow);

		setVisible(true);

	}

	public void addProjectsAllocatedStaff(
			HashMap<ProjectDO, ArrayList<ResultDO>> projectAllocStaffs) {

		String currentProject = "";

		if (projectAllocStaffs.size() > 0) {

			for (ProjectDO project : projectAllocStaffs.keySet()) {
				for (ResultDO staff : projectAllocStaffs.get(project)) {

					if (currentProject == project.getProjectName()) {
						allocationModel.addRow(new Object[] { "",
								staff.getStaffDO().getStaffName() });

					} else {
						allocationModel.addRow(new Object[] {
								project.getProjectName(),
								staff.getStaffDO().getStaffName() });

					}

					currentProject = project.getProjectName();
				}
			}
		}

	}

}


