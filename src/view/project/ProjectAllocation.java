package view.project;

import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import database.dataAccessObject.ProjectDao;
import database.dataAccessObject.ResultDao;

/**
 * 
 * gui for project allocation panel
 * 
 * @author Ross Dominic
 *
 */
public class ProjectAllocation extends JPanel {

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
			
			HashMap<ProjectDO, HashSet<String>> projectAllocSet = new HashMap<ProjectDO, HashSet<String>>();
			for (ProjectDO project : projectAllocStaffs.keySet()) {
				HashSet<String> set = new HashSet<String>();
				for (ResultDO staff : projectAllocStaffs.get(project)) {
					set.add(staff.getStaffDO().getStaffName());
				}
				projectAllocSet.put(project, set);
			}
			
			
			for (ProjectDO project : projectAllocSet.keySet()) {
				for (String staff : projectAllocSet.get(project)) {

					if (currentProject == project.getProjectName()) {
						allocationModel.addRow(new Object[] { "",
								staff});

					} else {
						allocationModel.addRow(new Object[] {
								project.getProjectName(),
								staff});

					}

					currentProject = project.getProjectName();
				}
			}
		}
		
	}

}


