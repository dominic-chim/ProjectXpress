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

public class ProjectAllocation extends JPanel {

	JTable allocationTable;
	DefaultTableModel allocationModel;
	
	public ProjectAllocation() {
		
		setLayout(new BorderLayout());

		ListSelectionModel listSelectionModel;

		String[] columnNames = {"Projects", "Allocated Staff"};

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
		
		testData();
		
		setVisible(true);
		

	}
	
	public void addProjectsAllocatedStaff(HashMap<ProjectDO , ArrayList<ResultDO>> projectAllocStaffs)	{
		
		String currentProject= "";
		
		for(ProjectDO project: projectAllocStaffs.keySet()) {
			for(ResultDO staff: projectAllocStaffs.get(project)) {
				
				if(currentProject== project.getProjectName()) {
					allocationModel.addRow(new Object[] {"", staff.getStaffDO().getStaffName()});

				} else {
					allocationModel.addRow(new Object[] {project.getProjectName(), staff.getStaffDO().getStaffName()});

				}
				
				currentProject = project.getProjectName();
			}
		}
		
	}
	
	//Remove when finished Testing
	public void testData() {
		//// Testing \\\\
	    //Create StaffDO obj to Test
        HashMap<Integer, Double> skillLevels = new HashMap<Integer, Double>();
        skillLevels.put(1, 5.0);
        HashMap<DateTime, DateTime> holidayDates= new HashMap<DateTime, DateTime>();

		StaffDO staffOne = new StaffDO(1, "Ross", 40, skillLevels, holidayDates);
		StaffDO staffTwo = new StaffDO(2, "Bob", 40, skillLevels, holidayDates);

		//Create ArrayList of ResultDO to test
		
	
		DateTime dateTime = new DateTime(2013, 04, 05, 10, 30, 0);
		
		ArrayList<Integer> requiredTskIds = new ArrayList<Integer>();
		requiredTskIds.add(5);
		
		DateTime dateStart1= new DateTime(2013, 04, 05, 10, 30, 0);
		DateTime dateEnd1 = new DateTime(2013, 04, 05, 13, 30, 0);
		
		DateTime dateStart2= new DateTime(2013, 04, 06, 14, 30, 0);
		DateTime dateEnd2 = new DateTime(2013, 04, 06, 17, 30, 0);
		
		TaskDO taskOne = new TaskDO(1, 1, "Task One",  1, 3, "RiskLevel", dateTime, "Task Status", requiredTskIds);
		ResultDO resultOne = new ResultDO(5, taskOne, staffOne, dateStart1, dateEnd1);

		TaskDO taskTwo = new TaskDO(1, 2, "Task Two",  1, 3, "RiskLevel", dateTime, "Task Status", requiredTskIds);
		ResultDO resultTwo = new ResultDO(5, taskTwo, staffTwo, dateStart2, dateEnd2);

		
		ArrayList<ResultDO> listOfResultsTest1 = new ArrayList<ResultDO>();
		listOfResultsTest1.add(resultOne);
		listOfResultsTest1.add(resultTwo);
		
		ArrayList<ResultDO> listOfResultsTest2 = new ArrayList<ResultDO>();
		listOfResultsTest2.add(resultOne);
		listOfResultsTest2.add(resultTwo);
		
		HashMap<StaffDO , ArrayList<ResultDO>> test = new HashMap<StaffDO, ArrayList<ResultDO>>();
		test.put(staffOne, listOfResultsTest1);
		test.put(staffTwo, listOfResultsTest2);

//		nt projectId, String projectName, 
//        DateTime projectDueDate,
//        int projectPriority, String projectStatus,
//        ArrayList<TaskDO> tasks
		

		ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
		tasks.add(taskOne);
		tasks.add(taskTwo);
		
		
		
		ProjectDO projectOne = new ProjectDO(1, "Project One", dateEnd2, 1, "Status", tasks);
		ProjectDO projectTwo = new ProjectDO(2, "Project Two", dateEnd2, 1, "Status", tasks);

		
		HashMap<ProjectDO, ArrayList<ResultDO>>	projectTest = new HashMap<ProjectDO, ArrayList<ResultDO>>();
		projectTest.put(projectOne, listOfResultsTest1);
		projectTest.put(projectTwo, listOfResultsTest2);
		
				
		addProjectsAllocatedStaff(projectTest);	

	}
	
	
	public void addController(ActionListener controller) {

	}

}
