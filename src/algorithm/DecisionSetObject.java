package algorithm;

import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

public class DecisionSetObject {
    private StaffDO staff;
    private TaskDO task;
    private double score;

    public DecisionSetObject(StaffDO staff, TaskDO task, double score) {
        this.staff= staff;
        this.task= task;
        this.score = score;
    }

    /**
     * @return the staffId
     */
    public StaffDO getStaff() {
        return staff;
    }

    /**
     * @return the taskId
     */
    public TaskDO getTask() {
        return task;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
