package algorithm;

public class DecisionSetObject {
    private int staffId;
    private int taskId;
    private double score;

    public DecisionSetObject(int staffId, int taskId, double score) {
        this.staffId = staffId;
        this.taskId = taskId;
        this.score = score;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }
}
