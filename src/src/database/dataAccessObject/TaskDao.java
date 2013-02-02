package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import data.dataObject.TaskDO;
import database.DatabaseRoot;

public class TaskDao extends DatabaseRoot {

    //TODO test getTasksByProjectId
    public ArrayList<TaskDO> getTasksByProjectId(int projectId) {
        ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
        String sql = "SELECT task_id FROM task WHERE project_id=" + projectId;
        try {
            ResultSet result = db.executeQuery(sql);
            while(result.next()) {
                tasks.add(getTaskById(projectId, result.getInt("task_id")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tasks;
    }

    //TODO test getTaskById
    public TaskDO getTaskById(int projectId, int taskId) {
        TaskDO task = null;

        String sql = "SELECT task_name, task_required_skill, task_duration, task_risk_level, "
            + "task_release_time, task_status from task WHERE project_id="
            + projectId + "AND task_id=" + taskId;
        try {
            ResultSet result = db.executeQuery(sql);
            if(result.next()) {
                // get task info
                String taskName = result.getString("task_name");
                int taskRequiredSkill = result.getInt("task_required_skill");
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
                return new TaskDO(projectId, taskId, taskName, 
                            taskRequiredSkill, taskDuration, 
                            taskRistLevel, releaseTime, 
                            taskStatus, requiredTaskIds);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return task;
    }
    
}
