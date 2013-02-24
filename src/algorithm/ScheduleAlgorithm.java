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
    
    // result chart
    private HashMap<Integer, boolean[]> staffAvailablity = new HashMap<Integer, boolean[]>();
    
    // sets
    private PriorityQueue<DecisionSetObject> decisionSet = 
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


        // get the greatest project due date
        DateTime greatestProjectDueDate = null;
        for(ProjectDO project : projects) {
            if(greatestProjectDueDate == null) {
                greatestProjectDueDate = project.getProjectDueDate();
            } else {
                if(greatestProjectDueDate.before(project.getProjectDueDate())) {
                    greatestProjectDueDate = project.getProjectDueDate();
                }
            }
        }

        // convert DateTime to ...
        for(StaffDO staff : staffList) {
            
            HashMap<DateTime, DateTime> holidays = staff.getHolidays();
            for(DateTime holidayStartTime : holidays.keySet()) {
                //int duration = holidays.get(holidayStartTime);
                //DateTime holidayEndTime = holidayStartTime.addWorkingHour(duration);
                DateTime holidayEndTime = holidays.get(holidayStartTime);
                if(holidayEndTime.before(projectStartingDate)) {
                    holidays.remove(holidayStartTime);
                }
            }

            // TODO calculate the length of boolean array 
            // TODO convert holiday to working days
            // TODO store and empty complete set !
            // STOP HERE


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
            // get one project according priority
            ProjectDO currentProject = projects.poll();
            // get all its tasks
            ArrayList<TaskDO> tasks = currentProject.getTasks();

            while(!(tasks.size() == 0 && activeSet.size() == 0)) {

                for(TaskDO task : tasks) {
                    if(canStart(task)) {

                        for(StaffDO staff : staffList) {
                            if(staff.hasSkill(task.getTaskRequiredSkill())) {

                                int score = calcTaskScore(staff, task);
                                DecisionSetObject canStartTask = new DecisionSetObject(staff, task, score);
                                decisionSet.add(canStartTask);
                            }
                        }
                        tasks.remove(task);
                    }
                }

                while(decisionSet.size() != 0) {

                    // get the one in decisionSet which have the lowest score
                    // TODO check if poll() get the right score
                    DecisionSetObject toActive = decisionSet.poll();

                    // get info from toActive to construct a TaskAllocObject
                    StaffDO staff = toActive.getStaff();
                    TaskDO task = toActive.getTask();
                    int startTime = getEarilestStartTime(staff.getStaffId());

                    // get availability of the staff to get holiday infomation
                    boolean[] availability = staffAvailablity.get(staff.getStaffId());
                    // calculate the endTime according to holiday and duration
                    int endTime = task.getTaskDuration() 
                        + startTime 
                        + getHoliday(startTime, task.getTaskDuration(), availability);

                    TaskAllocObject activatedTask = new TaskAllocObject(task, staff, startTime, endTime);
                    activeSet.add(activatedTask);

                    // remove the object in decisionSet with the same task
                    for(DecisionSetObject toRemove : decisionSet) {
                        if(toRemove.getTask().getTaskId() == task.getTaskId()) {
                            decisionSet.remove(toRemove);
                        }
                    }

                    // update availableTime
                    for(int i = startTime; i <= endTime; i++) {
                        availability[i] = false;
                    }

                    // update decisionSet according to new availableTime
                    for(DecisionSetObject toUpdate : decisionSet) {
                        double score = calcTaskScore(toUpdate.getStaff(), toUpdate.getTask());
                        toUpdate.setScore(score);
                    }

                }

                // add the task in activeSet which will complete first to the completeSet
                TaskAllocObject firstFinishedTask = null;
                for(TaskAllocObject taskAlloc : activeSet) {
                    if(firstFinishedTask == null) {
                        firstFinishedTask = taskAlloc;
                    } else if(firstFinishedTask.getEndTime() > taskAlloc.getEndTime()) {
                        firstFinishedTask = taskAlloc;
                    }
                }
                // move it from activeSet to completeSet
                activeSet.remove(firstFinishedTask);
                completeSet.add(firstFinishedTask);
                // reset currentTime
                currentTime = firstFinishedTask.getEndTime();
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
                if(taskAlloc.getTask().getTaskId() == taskId) {
                    eq = true;
                }
            }
            canStart = canStart && eq;
        }

        return canStart;
    }

    private int calcTaskScore(StaffDO staff, TaskDO task) {

    	boolean[] availability = staffAvailablity.get(staff.getStaffId());

        // get earliest time for the task can be start for this staff
        int earliestStartTime = getEarilestStartTime(staff.getStaffId());
    	
        // get skill level of the task required skill of the task
    	double skillLevel = staff.getSkillLevels().get(task.getTaskRequiredSkill());

    	
        // get holiday if relevant 
        int holiday = getHoliday(earliestStartTime, task.getTaskDuration(), availability);
    	
        // calculate the score according to the formula
        return (int)ceil(earliestStartTime + task.getTaskDuration() / skillLevel + holiday);

    }

    private int getHoliday(int startTime, int duration, boolean[] availability) {
        int holidayCounter = 0;
    	for(int i = startTime; i < (startTime + duration); i++) {
            if(!availability[i]) {
                holidayCounter++;
                i--;
            }
    	}
        return holidayCounter;
    }

    private int getEarilestStartTime(int staffId) {
    	boolean[] availability = staffAvailablity.get(staffId);

        // get earliestavailableTime for a staff
    	int availableTime = 0;
    	for(int i = 0; i < availability.length; i++) {
    		
    		if(availability[i]) {
    			availableTime = i;
    			break;
    		}
    		
    	}
        // get earliest time for the task can be start for this staff
        int earliestStartTime = max(availableTime, currentTime);
        return earliestStartTime;
    }

    
    private void removeIrreleventHoliday(HashMap<DateTime, Integer> holiday) {
    	
    }


}
