package data.dataObject;

import util.DateTime;

/**
 * 
 * data object to represent one result
 * 
 * @author Ke CHEN & Ross
 *
 */
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

    public TaskDO getTaskDO() {
        return taskDO;
    }

    public StaffDO getStaffDO() {
        return staffDO;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public String toString() {
        return String.format("project:%d, staff:%d, start:%s, end%s\n", 
                taskDO.getTaskId(),
                staffDO.getStaffId(),
                startDateTime.getDateTime(),
                endDateTime.getDateTime());
    }

}
