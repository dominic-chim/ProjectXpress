package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseRoot;
import data.dataObject.ProjectDO;

public class ProjectDao extends DatabaseRoot {

    public ProjectDO getProjectById(int projectId) {
        String sql = "SELECT project_name, project_due_date,project_priority, project_status FROM project WHERE project_id=" + projectId;
        try {
            ResultSet result = db.executeQuery(sql);
            while(result.next()) {
                String projectName = result.getString("project_name");
                String projectDueDate = result.getString("project_due_date");
                int projectPriority = result.getInt("project_priority");
                String projectStatus = result.getString("project_status");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
