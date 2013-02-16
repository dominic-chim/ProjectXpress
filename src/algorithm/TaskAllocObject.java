package algorithm;

public class TaskAllocObject {

    private int taskId; 
    private int staffId; 

    private int startTime;
    private int endTime;

    public TaskAllocObject(int taskId, int staffId, int startTime, int endTime) {
        this.taskId = taskId;
        this.staffId = staffId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @return the staffId
     */
    public int getStaffId() {
        return staffId;
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
