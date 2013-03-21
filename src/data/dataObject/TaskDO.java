package data.dataObject;

import java.util.ArrayList;

import data.Context;



import util.DateTime;
/**
 * 
 * data object for task
 * 
 * @author Ke CHEN
 *
 */
public class TaskDO {

    private int projectId;
    private int taskId;
    private String taskName;
    private int taskRequiredSkill;
    private int taskDuration;
    private int taskRemainingTime;
    private String taskRiskLevel;
    private DateTime taskReleaseTime;
    private String taskStatus;
    private ArrayList<Integer> requiredTaskIds = new ArrayList<Integer>();

    public TaskDO() {
    }

    public TaskDO(int projectId, int taskId, String taskName, 
            int taskRequiredSkill, int taskDuration, int taskRemainingTime,
            String taskRiskLevel, DateTime taskReleaseTime,
            String taskStatus, ArrayList<Integer> requiredTaskIds) {
        
        setProjectId(projectId);
        setTaskId(taskId);
        setTaskName(taskName);
        setTaskRequiredSkill(taskRequiredSkill);
        setTaskDuration(taskDuration);
        setTaskRemainingTime(taskRemainingTime);
        setTaskRiskLevel(taskRiskLevel);
        setTaskReleaseTime(taskReleaseTime);
        setTaskStatus(taskStatus);
        setRequiredTaskIds(requiredTaskIds);
    }


    // getters
    public int getProjectId() {
        return projectId;
    }

    public int getTaskId() {
        return taskId;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public int getTaskRequiredSkill() {
        return taskRequiredSkill;
    }
    
    
    /**
     * this method return the original task duration
     */
    public int getTaskOriginalDuration() {
        return taskDuration;
    }

    /**
     * this method return the real time needed for the task
     */
    public int getTaskDuration() {
        int risk = Context.getRiskLevel().get(getTaskRiskLevel());
        return (int)Math.ceil(taskRemainingTime * risk * 0.01);
    }

    public int getTaskRemainingTime() {
    	return taskRemainingTime;
    }


    public String getTaskRiskLevel() {
        return taskRiskLevel;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
    
    public DateTime getTaskReleaseTime() {
        return taskReleaseTime;
    }

    
    public ArrayList<Integer> getRequiredTaskIds() {
        return requiredTaskIds;
    }
    
    
    
    // setters
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
 
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    public void setTaskRequiredSkill(int taskRequiredSkill) {
        this.taskRequiredSkill = taskRequiredSkill;
    }

    
    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }


    public void setTaskRiskLevel(String taskRiskLevel) {
        this.taskRiskLevel = taskRiskLevel;
    }


    public void setTaskReleaseTime(DateTime taskReleaseTime) {
        this.taskReleaseTime = taskReleaseTime;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskRemainingTime(int taskRemainingTime) {
        this.taskRemainingTime = taskRemainingTime;
    }


    public void setRequiredTaskIds(ArrayList<Integer> requiredTaskIds) {
        this.requiredTaskIds = requiredTaskIds;
    }

    public void addReqiredTask(int taskId) {
        requiredTaskIds.add(taskId);
    }

    public String toString() {
        String output = "Task name: " + taskName + "\n";
        output += "Task Required Skill: " + taskRequiredSkill + "\n";
        output += "Task Duration: " + taskDuration + "\n";
        output += "Task risk level: " + taskRiskLevel + "\n";
        output += "Task Release time: " + taskReleaseTime.getDateTime() + "\n";
        output += "Task Status: " + taskStatus + "\n";
        output += "Task Remaining time: " + taskRemainingTime + "\n";
        output += "requiredTaskIds: ";
        for(Integer requiredTaskId : requiredTaskIds) {
            output += " " + requiredTaskId + " ";
        }
        output += "\n";

        return output;
    }
}
