package view.staff;

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
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;
import database.dataAccessObject.ResultDao;
import database.dataAccessObject.StaffDao;

/**
 * gui for staff allocation 
 * 
 * @author Ross, Dominic
 *
 */
public class StaffAllocation extends JPanel {


	private static final long serialVersionUID = 1L;
	JTable allocationTable;
    DefaultTableModel allocationModel;
    
    public StaffAllocation() {
        
        setLayout(new BorderLayout());

        ListSelectionModel listSelectionModel;

        String[] columnNames = {"Staff", "Allocated Tasks"};

        Object[][] data = {};

        allocationModel = new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
			public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        allocationTable = new JTable(allocationModel) {
  
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Disallow the editing of any cell
            }
        };

        listSelectionModel = allocationTable.getSelectionModel();
        allocationTable.setSelectionModel(listSelectionModel);
        
        JScrollPane scrollPane = new JScrollPane(allocationTable);
        add(scrollPane, BorderLayout.CENTER);
        
        HashMap<StaffDO , ArrayList<ResultDO>> dataToShow = new HashMap<StaffDO, ArrayList<ResultDO>>();
		ResultDao resultDB = new ResultDao();
		StaffDao staffDB = new StaffDao();

        ArrayList<Integer> staffIds = resultDB.getAllStaffInCurrentVersion();
        for(int staffId : staffIds) {
            dataToShow.put(staffDB.getStaffById(staffId), resultDB.getResultByStaff(staffId));
        }
        addStaffAllocatedProjects(dataToShow);    
        
        setVisible(true);
        

    }
    
    
    public void addStaffAllocatedProjects(HashMap<StaffDO , ArrayList<ResultDO>> staffAllocProjects)    {
        
        String currentStaff = "";
        
        for(StaffDO staff : staffAllocProjects.keySet()) {
            for(ResultDO tasks: staffAllocProjects.get(staff)) {
                
                if(currentStaff == staff.getStaffName()) {
                    allocationModel.addRow(new Object[] {"", tasks.getTaskDO().getTaskName()});

                } else {
                    allocationModel.addRow(new Object[] {staff.getStaffName(), tasks.getTaskDO().getTaskName()});

                }
                
                currentStaff = staff.getStaffName();
            }
        }
        
    }
    
    


}
