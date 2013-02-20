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
    //private HashMap<Integer, Integer> earliestAvailableTime = new HashMap<Integer, Integer>();
    //private HashMap<Integer, HashMap<Integer, Integer>> staffUnavailableTime = new HashMap<Integer, HashMap<Integer, Integer>>();
    
    private HashMap<Integer, boolean[]> staffAvailablity;
    
    // sets
    private PriorityQueue<DecisionSetObject> dicisionSet = 
        new PriorityQueue<DecisionSetObject>(5, new DecisionSetComparator());

    private ArrayList<TaskAllocObject> activeSet = new ArrayList<TaskAllocObject>();
    private ArrayList<TaskAllocObject> completeSet = new ArrayList<TaskAllocObject>();
    
    

    // list of projects
    private PriorityQueue<ProjectDO> projects = 
        new PriorityQueue<ProjectDO>(5, new ProjectComparator());

    public ScheduleAlgorithm(DateTime projectStartingDate, ArrayList<StaffDO> staffList, 
            PriorityQueue<ProjectDO> projects) {
    	
    	this.projectStartingDate = projectStartingDate;
        this.staffList = staffList;
        this.projects = projects;

        for(StaffDO staff : staffList) {

        	// TODO init staffAvailablity here
        	
            
            
        }
/*
        for(StaffDO staff : staffList) {
        	
        	int staffId = staff.getStaffId();
        	int minKey = 0;
        	for(int i : staffUnavailableTime.get(staffId).keySet()) {
        		if(minKey > i) {
        			minKey = i;
        		}
        	}
        	
        	if(minKey == 0){
        		earliestAvailableTime.put(staff.getStaffId(), 0);
        	}
        	
            
        }*/
        
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
                            int score = calcTaskScore(staff, task);
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

    private int calcTaskScore(StaffDO staff, TaskDO task) {

    	boolean[] availability = staffAvailablity.get(staff.getStaffId());
    	int availableTime = 0;
    	for(int i = 0; i < availability.length; i++) {
    		
    		if(availability[i]) {
    			availableTime = i;
    			break;
    		}
    		
    	}
    	
    	double skillLevel = staff.getSkillLevels().get(task.getTaskRequiredSkill());
    	
    	for(int i = max(availableTime,currentTime) ; ;) {
    		//TODO STOP HERE    availability
    	}
    	
    	int holiday;
    	
        return (int)ceil(max(availableTime,currentTime) + task.getTaskDuration() / skillLevel + holiday) ;

        //return 0;

    }
    
    private void removeIrreleventHoliday(HashMap<DateTime, Integer> holiday) {
    	
    }


}
