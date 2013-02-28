package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DateTime;

import data.dataObject.ResultDO;
import database.DatabaseRoot;

public class ResultDao extends DatabaseRoot {

    public ArrayList<ResultDO> getResultByProject(int projectId) {

        ArrayList<ResultDO> results = new ArrayList<ResultDO>();

        String sql = "SELECT staff_id, start_datetime, end_datetime, project_id, task_id FROM scheduling_result WHERE project_id="
                + projectId;
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                int staffId = result.getInt("staff_id");
                DateTime startDateTime = new DateTime(result.getString("start_datetime"));
                DateTime endDateTime = new DateTime(result.getString("end_datetime"));
                int taskId = result.getInt("task_id");
                
                results.add(new ResultDO(projectId, taskId, staffId, startDateTime, endDateTime));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return results;
    }
    
    

}
