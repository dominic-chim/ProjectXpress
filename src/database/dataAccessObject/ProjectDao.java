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
            ResultSet result = connection.createStatement().executeQuery(sql);
            String projectName = null;
            DateTime projectDueDate = null;
            int projectPriority = 0;
            String projectStatus = null;
            if(result.next()) {
                projectName = result.getString("project_name");
                projectDueDate = new DateTime(result.getString("project_due_date"));
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

    public ArrayList<ProjectDO> getAllProject() {
        ArrayList<ProjectDO> projects = new ArrayList<ProjectDO>();

        String sql = "SELECT DISTINCT project_id FROM project";

        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                int projectId = result.getInt("project_id");
                projects.add(getProjectById(projectId));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return projects;
    }

    public void addProject(ProjectDO project) {

        // insert project info
        String sql = String.format("INSERT INTO project (project_name, project_due_date, "
                                    + "project_priority, project_status) VALUES ('%s', '%s', %d, '%s')", 
                                    project.getProjectName(), 
                                    project.getProjectDueDate().getDateTime(),
                                    project.getProjectPriority(),
                                    project.getProjectStatus());
        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<TaskDO> tasks = project.getTasks();
        TaskDao taskDao = new TaskDao();

        // TODO check error
        int projectId = getMaxProjectId();

        for(int i = 1; i <= tasks.size(); i++) {
            taskDao.addTask(projectId, i, tasks.get(i - 1));
        }

    }

    public int getMaxProjectId() {
        ResultSet result;
        try {
            result = connection.createStatement().executeQuery("SELECT max(project_id) AS max_id FROM project");
            if(result.next())
                return result.getInt("max_id");
            else
                return 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }


    public DateTime getLastDueDate() {

        String sql = "SELECT max(project_due_date) AS max_due_date FROM project";
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            if(result.next()) {
                return new DateTime(result.getString("max_due_date"));
            }
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
