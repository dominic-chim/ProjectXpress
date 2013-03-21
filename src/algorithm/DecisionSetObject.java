package algorithm;

import data.dataObject.StaffDO;
import data.dataObject.TaskDO;


/**
 * 
 * object place in the decision set in the scheduling algorithm
 * 
 * @author Ke CHEN & Ross
 *
 */
public class DecisionSetObject {
    private StaffDO staff;
    private TaskDO task;
    private double score;

    public DecisionSetObject(StaffDO staff, TaskDO task, double score) {
        this.staff= staff;
        this.task= task;
        this.score = score;
    }


    // getters
    public StaffDO getStaff() {
        return staff;
    }

    public TaskDO getTask() {
        return task;
    }

 
    public double getScore() {
        return score;
    }

    // setters
    public void setScore(double score) {
        this.score = score;
    }
}
