package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import database.DatabaseRoot;
import data.dataObject.*;

public class ProjectDao extends DatabaseRoot {

    public ProjectDO getProjectById(int projectId) {
        String sql = "SELECT project_name, project_due_date,project_priority, " + 
                "project_status FROM project WHERE project_id=" + projectId;
        ArrayList<TaskDO> tasks = new ArrayList();
        try {
            ResultSet result = db.executeQuery(sql);
            String projectName = null;
            String projectDueDate = null;
            int projectPriority = 0;
            String projectStatus = null;
            GregorianCalendar projDueDate = null;
            if(result.next()) {
                projectName = result.getString("project_name");
                projectDueDate = result.getString("project_due_date");
                projectPriority = result.getInt("project_priority");
                projectStatus = result.getString("project_status");
                projDueDate = dateTimeToCalendar(projectDueDate);
            } else {
                return null;
            }

            // get tasks of this project
            sql = "SELECT task_id, task_name, task_required_skill, task_duration, " +
                "task_risk_level, task_release_time, task_status from task WHERE project_id=" + projectId;
            result = db.executeQuery(sql);
            while(result.next()) {
                int taskId = result.getInt("task_id");
                String taskName = result.getString("task_name");
                String taskRequiredSkill = result.getString("task_required_skill");
                int taskDuration = result.getInt("task_duration");
                String taskRistLevel = result.getString("task_risk_level");
                String taskReleaseTime = result.getString("task_release_time");
                String taskStatus = result.getString("task_status");

                // get requiredTaskId of this task from table required_task_id
                ArrayList<Integer> requiredTaskIds = new ArrayList<Integer>();
                sql = "SELECT required_task_id from task_precedence WHERE project_id=" + 
                    projectId + "AND task_id=" + taskId;
                ResultSet innerResult = db.executeQuery(sql);
                while(innerResult.next()) {
                    int requiredTaskId = innerResult.getInt("required_task_id");
                    requiredTaskIds.add(new Integer(requiredTaskId));
                }
                GregorianCalendar releaseTime = dateTimeToCalendar(taskReleaseTime);
                tasks.add(new TaskDO(projectId, taskId, taskName, 
                            taskRequiredSkill, taskDuration, 
                            taskRistLevel, releaseTime, 
                            taskStatus, requiredTaskIds));
            }
            return new ProjectDO(projectId, projectName, 
                    projDueDate, projectPriority, projectStatus, tasks);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
