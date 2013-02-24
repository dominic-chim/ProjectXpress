package data.dataObject;

import util.DateTime;

public class ResultDO {

    private int projectId;
    private int taskId;
    private int staffId;
    private DateTime startDateTime;
    private DateTime endDateTime;

    public ResultDO(int projectId, int taskId, int staffId, 
            DateTime startDateTime, DateTime endDateTime) {

        this.projectId = projectId;
        this.taskId = taskId;
        this.staffId = staffId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    // getters
    public int getProjectId() {
        return projectId;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getStaffId() {
        return staffId;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

}
