package algorithm;

import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

/**
 *
 * object in active set and complete set in the algorithm
 *
 * @author Ke CHEN & Ross
 */
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

    // getters
    public TaskDO getTask() {
        return task;
    }

    public StaffDO getStaff() {
        return staff;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String toString() {
        return String.format("task: %d, staff: %d, from: %d, to: %d", 
                task.getTaskId(), staff.getStaffId(), startTime, endTime);
    }
         

}
