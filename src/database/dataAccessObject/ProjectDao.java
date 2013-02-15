package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.DateTime;

import database.DatabaseRoot;
import data.dataObject.*;

public class ProjectDao extends DatabaseRoot {

    public ProjectDO getProjectById(int projectId) {
        String sql = "SELECT project_name, project_due_date,project_priority, " + 
                "project_status FROM project WHERE project_id=" + projectId;
        ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
        try {
            ResultSet result = db.executeQuery(sql);
            String projectName = null;
            DateTime projectDueDate = null;
            int projectPriority = 0;
            String projectStatus = null;
            GregorianCalendar projDueDate = null;
            if(result.next()) {
                projectName = result.getString("project_name");
//                projectDueDate = new DateTime(result.getString("project_due_date"));
                projectPriority = result.getInt("project_priority");
                projectStatus = result.getString("project_status");
                //projDueDate = dateTimeToCalendar(projectDueDate);
            } else {
                return null;
            }

            // get tasks of this project using TaskDao
            TaskDao taskDao = new TaskDao();
            tasks = taskDao.getTasksByProjectId(projectId);

            return new ProjectDO(projectId, projectName, 
                    projectDueDate, projectPriority, projectStatus, tasks);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //TODO change return value
        return null;
    }

    public void addProject(ProjectDO project) {


    }
}
