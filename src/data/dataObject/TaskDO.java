package data.dataObject;

import java.util.ArrayList;

import util.DateTime;

public class TaskDO {

    private int projectId;
    private int taskId;
    private String taskName;
    private int taskRequiredSkill;
    private int taskDuration;
    private String taskRistLevel;
    private DateTime taskReleaseTime;
    private String taskStatus;
    private ArrayList<Integer> requiredTaskIds;

    public TaskDO() {
    }

    public TaskDO(int projectId, int taskId, String taskName, 
            int taskRequiredSkill, int taskDuration,
            String taskRistLevel, DateTime taskReleaseTime,
            String taskStatus, ArrayList<Integer> requiredTaskIds) {
        
        setProjectId(projectId);
        setTaskId(taskId);
        setTaskName(taskName);
        setTaskRequiredSkill(taskRequiredSkill);
        setTaskDuration(taskDuration);
        setTaskRistLevel(taskRistLevel);
        setTaskReleaseTime(taskReleaseTime);
        setTaskStatus(taskStatus);
        setRequiredTaskIds(requiredTaskIds);
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the taskRequiredSkill
     */
    public int getTaskRequiredSkill() {
        return taskRequiredSkill;
    }

    /**
     * @param taskRequiredSkill the taskRequiredSkill to set
     */
    public void setTaskRequiredSkill(int taskRequiredSkill) {
        this.taskRequiredSkill = taskRequiredSkill;
    }

    /**
     * @return the taskDuration
     */
    public int getTaskDuration() {
        return taskDuration;
    }

    /**
     * @param taskDuration the taskDuration to set
     */
    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }

    /**
     * @return the taskRistLevel
     */
    public String getTaskRistLevel() {
        return taskRistLevel;
    }

    /**
     * @param taskRistLevel the taskRistLevel to set
     */
    public void setTaskRistLevel(String taskRistLevel) {
        this.taskRistLevel = taskRistLevel;
    }

    /**
     * @return the taskReleaseTime
     */
    public DateTime getTaskReleaseTime() {
        return taskReleaseTime;
    }

    /**
     * @param taskReleaseTime the taskReleaseTime to set
     */
    public void setTaskReleaseTime(DateTime taskReleaseTime) {
        this.taskReleaseTime = taskReleaseTime;
    }

    /**
     * @return the taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus the taskStatus to set
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * @return the requiredTaskId
     */
    public ArrayList<Integer> getRequiredTaskIds() {
        return requiredTaskIds;
    }

    /**
     * @param requiredTaskId the requiredTaskId to set
     */
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
        output += "Task risk level: " + taskRistLevel + "\n";
        output += "Task Release time: " + taskReleaseTime.getDateTime() + "\n";
        output += "Task Status: " + taskStatus + "\n";
        output += "requiredTaskIds: ";
        for(Integer requiredTaskId : requiredTaskIds) {
            output += " " + requiredTaskId + " ";
        }
        output += "\n";

        return output;
    }


}
