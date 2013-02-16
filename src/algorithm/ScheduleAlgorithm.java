package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import util.DateTime;

import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

import static java.lang.Math.*;

public class ScheduleAlgorithm {


    private DateTime projectStartingDate;

    private ArrayList<StaffDO> staffList = new ArrayList<StaffDO>();
    private int currentTime = 0;
    private HashMap<Integer, Integer> availableTime = new HashMap<Integer, Integer>();

    // sets
    private PriorityQueue<DecisionSetObject> dicisionSet = 
        new PriorityQueue<DecisionSetObject>(5, new DecisionSetComparator());

    private ArrayList<TaskAllocObject> activeSet = new ArrayList<TaskAllocObject>();
    private ArrayList<TaskAllocObject> completeSet = new ArrayList<TaskAllocObject>();

    // list of projects
    PriorityQueue<ProjectDO> projects = 
        new PriorityQueue<ProjectDO>(5, new ProjectComparator());

    public ScheduleAlgorithm(ArrayList<StaffDO> staffList, 
            PriorityQueue<ProjectDO> projects) {

        this.staffList = staffList;
        this.projects = projects;

        for(StaffDO staff : staffList) {

            availableTime.put(staff.getStaffId(), 0);
        }

    }


    public void runAlgoritm() {

        while(projects.size() != 0) {
            ProjectDO currentProject = projects.poll();
            ArrayList<TaskDO> tasks = currentProject.getTasks();
            for(TaskDO task : tasks) {
                if(canStart(task)) {
                    // TODO remove it from tasks

                    for(StaffDO staff : staffList) {
                        if(staff.hasSkill(task.getTaskRequiredSkill())) {
                            int score = getScore(staff.getStaffId(), task.getTaskId());
                            DecisionSetObject canStartTask = new DecisionSetObject();
                        }
                    }
                }
            }
        }
        

    }

    private boolean canStart(TaskDO task) {

        ArrayList<Integer> requiredId = task.getRequiredTaskIds();

        if(requiredId.size() == 0) {
            return true;
        }

        boolean canStart = true;
        for(int taskId : requiredId) {

            boolean eq = false;
            for(TaskAllocObject taskAlloc : completeSet) {
                if(taskAlloc.getTaskId() == taskId) {
                    eq = true;
                }
            }
            canStart = canStart && eq;
        }

        return canStart;
    }

    private int calcTaskScore(int staffId, int taskId) {

        max(,2);

        return 0;

    }


}
