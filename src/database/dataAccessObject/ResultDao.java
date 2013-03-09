package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DateTime;

import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

import database.DatabaseRoot;

public class ResultDao extends DatabaseRoot {

    public ArrayList<ResultDO> getResultByProject(int projectId) {

        ArrayList<ResultDO> results = new ArrayList<ResultDO>();

        String sql = String.format("SELECT staff_id, start_datetime, end_datetime, project_id, task_id FROM scheduling_result WHERE project_id=%d AND version=%d ORDER BY start_datetime", projectId, getCurrentResultVersion());
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                int staffId = result.getInt("staff_id");
                DateTime startDateTime = new DateTime(result.getString("start_datetime"));
                DateTime endDateTime = new DateTime(result.getString("end_datetime"));
                int taskId = result.getInt("task_id");

                TaskDO task = (new TaskDao()).getTaskById(projectId, taskId);
                StaffDO staff = (new StaffDao()).getStaffById(staffId);

                results.add(new ResultDO(projectId, task, staff, startDateTime, endDateTime));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return results;
    }

    public ArrayList<ResultDO> getResultByStaff(int staffId) {

        ArrayList<ResultDO> results = new ArrayList<ResultDO>();

        String sql = String.format("SELECT staff_id, start_datetime, end_datetime, project_id, task_id FROM scheduling_result WHERE staff_id=%d AND version=%d ORDER BY start_datetime", staffId, getCurrentResultVersion());
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                int projectId = result.getInt("project_id");
                DateTime startDateTime = new DateTime(result.getString("start_datetime"));
                DateTime endDateTime = new DateTime(result.getString("end_datetime"));
                int taskId = result.getInt("task_id");

                TaskDO task = (new TaskDao()).getTaskById(projectId, taskId);
                StaffDO staff = (new StaffDao()).getStaffById(staffId);

                results.add(new ResultDO(projectId, task, staff, startDateTime, endDateTime));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }

    public void addResults(ArrayList<ResultDO> results) {

        int version = getCurrentResultVersion() + 1;
        for(ResultDO result : results) {
            String sql = String.format("INSERT INTO scheduling_result (staff_id, start_datetime, "
                + "end_datetime, project_id, task_id, version) VALUES (%d, '%s', '%s', %d, %d, %d)", 
                    result.getStaffDO().getStaffId(),
                    result.getStartDateTime().getDateTime(),
                    result.getEndDateTime().getDateTime(),
                    result.getTaskDO().getProjectId(),
                    result.getTaskDO().getTaskId(),
                    version);
            try {
                db.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private int getCurrentResultVersion() {
        String sql = "SELECT max(version) AS current_version FROM scheduling_result";
        try {
            ResultSet rset = connection.createStatement().executeQuery(sql);
            if(rset.next()) {
                return rset.getInt("current_version");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    


    

}
