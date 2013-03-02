package data.dataObject;

import util.DateTime;

public class ResultDO {

    private int projectId;
    private TaskDO taskDO;
    private StaffDO staffDO;
    private DateTime startDateTime;
    private DateTime endDateTime;

    public ResultDO(int projectId, TaskDO taskDO, StaffDO staffDO, 
            DateTime startDateTime, DateTime endDateTime) {

        this.projectId = projectId;
        this.taskDO = taskDO;
        this.staffDO = staffDO;
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
