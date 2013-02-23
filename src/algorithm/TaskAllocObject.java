package algorithm;

import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

public class TaskAllocObject {

    private TaskDO task; 
    private StaffDO staff; 

    private int startTime;
    private int endTime;

    public TaskAllocObject(TaskDO task, StaffDO staff, int startTime, int endTime) {
        this.task= task;
        this.staff= staff;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the taskId
     */
    public TaskDO getTask() {
        return task;
    }

    /**
     * @return the staffId
     */
    public StaffDO getStaff() {
        return staff;
    }

    /**
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public int getEndTime() {
        return endTime;
    }

}
