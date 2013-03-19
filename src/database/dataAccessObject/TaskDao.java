package database.dataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.DateTime;


import data.dataObject.TaskDO;
import database.DatabaseRoot;

public class TaskDao extends DatabaseRoot {

    public ArrayList<TaskDO> getTasksByProjectId(int projectId) {
        ArrayList<TaskDO> tasks = new ArrayList<TaskDO>();
        String sql = "SELECT task_id FROM task WHERE project_id=" + projectId + " ORDER BY task_id";
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                tasks.add(getTaskById(projectId, result.getInt("task_id")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tasks;
    }

    public TaskDO getTaskById(int projectId, int taskId) {
        TaskDO task = null;

        String sql = "SELECT task_name, task_required_skill, task_duration, task_risk_level, task_remaining_time, "
            + "task_release_time, task_status FROM task WHERE project_id="
            + projectId + " AND task_id=" + taskId;

        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            if(result.next()) {
                // get task info
                String taskName = result.getString("task_name");
                int taskRequiredSkill = result.getInt("task_required_skill");
                int taskDuration = result.getInt("task_duration");
                int taskRemainingTime = result.getInt("task_remaining_time");
                String taskRiskLevel = result.getString("task_risk_level");
                DateTime taskReleaseTime = new DateTime(result.getString("task_release_time"));
                String taskStatus = result.getString("task_status");

                // get requiredTaskId of this task from table required_task_id
                ArrayList<Integer> requiredTaskIds = new ArrayList<Integer>();
                sql = "SELECT required_task_id from task_precedence WHERE project_id=" + 
                    projectId + " AND task_id=" + taskId;
                ResultSet innerResult = db.executeQuery(sql);
                while(innerResult.next()) {
                    int requiredTaskId = innerResult.getInt("required_task_id");
                    requiredTaskIds.add(new Integer(requiredTaskId));
                }
                return new TaskDO(projectId, taskId, taskName, 
                            taskRequiredSkill, taskDuration, taskRemainingTime,
                            taskRiskLevel, taskReleaseTime, 
                            taskStatus, requiredTaskIds);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return task;
    }
    
    public void addTask(int projectId, int taskId, TaskDO task) {
        String sql = String.format("INSERT INTO task (project_id, task_id, task_name, task_required_skill, "
                                    + "task_duration, task_risk_level, task_release_time, task_status, task_remaining_time) "
                                    + "VALUES (%d, %d, '%s', %d, %d, '%s', '%s', '%s', %d)", 
                                    projectId, taskId, task.getTaskName(),
                                    task.getTaskRequiredSkill(),
                                    task.getTaskOriginalDuration(),
                                    task.getTaskRiskLevel(),
                                    task.getTaskReleaseTime().getDateTime(),
                                    task.getTaskStatus(),
                                    task.getTaskDuration()
                                    );

        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Integer> requiredTaskIds = task.getRequiredTaskIds();
        for(int requiredTaskId : requiredTaskIds) {
            addTaskDependency(projectId, taskId, requiredTaskId);
        }
    }

    public void addTaskDependency(int projectId, int taskId, int requiredTaskId) {

        String sql = String.format("INSERT INTO task_precedence (project_id, task_id, required_task_id) " +
                                    "VALUES (%d, %d, %d)",
                                    projectId, taskId, requiredTaskId);
        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateTaskInfo(TaskDO task) {
        String sql = String.format("UPDATE task SET task_name='%s', task_required_skill=%d, " +
                "task_duration=%d, task_risk_level='%s', task_release_time='%s', task_status='%s', "+
                "task_remaining_time=%d WHERE task_id=%d AND project_id=%d",
                task.getTaskName(),
                task.getTaskRequiredSkill(),
                task.getTaskOriginalDuration(),
                task.getTaskRiskLevel(),
                task.getTaskReleaseTime().getDateTime(),
                task.getTaskStatus(),
                task.getTaskDuration(),
                task.getTaskId(),
                task.getProjectId());
        try {
            db.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
