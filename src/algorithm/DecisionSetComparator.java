package algorithm;

import java.util.Comparator;

public class DecisionSetComparator implements Comparator<DecisionSetObject> {

    @Override
    public int compare(DecisionSetObject task1, DecisionSetObject task2) {

        if(task1.getScore() < task2.getScore()) {
            return -1;
        }

        if(task1.getScore() > task2.getScore()) {
            return 1;
        }

        if(task1.getScore() == task2.getScore()) {
            return 0;
        }

        return 0;
    }

}
