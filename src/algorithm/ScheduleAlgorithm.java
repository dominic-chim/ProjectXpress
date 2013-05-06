package algorithm;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import util.DateTime;
import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

/**
 *
 * Schedule algorithm
 * 
 * @author Ke CHEN & Ross
 */
public class ScheduleAlgorithm {

    private DateTime projectStartingDate;

    private ArrayList<StaffDO> staffList = new ArrayList<StaffDO>();
    private int currentTime = 0;

    // result chart
    private HashMap<Integer, boolean[]> staffAvailablity = new HashMap<Integer, boolean[]>();

    // sets
    private PriorityQueue<DecisionSetObject> decisionSet = new PriorityQueue<DecisionSetObject>(
            5, new DecisionSetComparator());

    private ArrayList<TaskAllocObject> activeSet = new ArrayList<TaskAllocObject>();
    private ArrayList<TaskAllocObject> completeSet = new ArrayList<TaskAllocObject>();

    // list of projects
    private PriorityQueue<ProjectDO> projects = new PriorityQueue<ProjectDO>(5, new ProjectComparator());

    private ArrayList<ResultDO> scheduleResult = new ArrayList<ResultDO>();

    public ScheduleAlgorithm(DateTime projectStartingDate,
            DateTime projectsDueDate, ArrayList<StaffDO> staffList,
            PriorityQueue<ProjectDO> projects) {

        this.projectStartingDate = projectStartingDate;
        this.staffList = staffList;
        this.projects = projects;



        // convert DateTime to 123..
        for (StaffDO staff : staffList) {

            HashMap<DateTime, DateTime> holidays = staff.getHolidays();
            ArrayList<DateTime> toDeleteDate = new ArrayList<DateTime>();
            for (DateTime holidayStartTime : holidays.keySet()) {
                DateTime holidayEndTime = holidays.get(holidayStartTime);
                if (holidayEndTime.before(projectStartingDate)) {
                	toDeleteDate.add(holidayStartTime);
                }
            }
            
            for(DateTime del : toDeleteDate) {
            	holidays.remove(del);
            }
            

            boolean[] availability = new boolean[DateTime.duration(projectStartingDate, projectsDueDate)];
            Arrays.fill(availability, Boolean.TRUE);
            for (DateTime holidayStartTime : holidays.keySet()) {
                
                DateTime holidayEndTime = holidays.get(holidayStartTime);
                int holidayDuration = DateTime.duration(holidayStartTime, holidayEndTime);
                int holidayStartTimeInAlg = DateTime.duration(projectStartingDate,
                        holidayStartTime);
                int holidayStartTimeInAlgReal = holidayStartTimeInAlg;
                if(holidayStartTimeInAlg < 0) {
                	holidayStartTimeInAlgReal = 0;
                }
                for (int i = holidayStartTimeInAlgReal; i < holidayStartTimeInAlg
                        + holidayDuration; i++) {
                    availability[i] = false;
                }
            }
            staffAvailablity.put(staff.getStaffId(), availability);

        }

    }

    public ArrayList<ResultDO> runAlgoritm() {

        while (projects.size() != 0) {
            // get one project according priority
            ProjectDO currentProject = projects.poll();
            // get all its tasks
            ArrayList<TaskDO> tasks = currentProject.getTasks();

            while (!(tasks.size() == 0 && activeSet.size() == 0)) {

                

                ArrayList<TaskDO> toDeleteTasks = new ArrayList<TaskDO>();
                for (TaskDO task : tasks) {
                    if (canStart(task)) {

                        for (StaffDO staff : staffList) {
                            if (staff.hasSkill(task.getTaskRequiredSkill())) {

                                int score = calcTaskScore(staff, task);
                                DecisionSetObject canStartTask = new DecisionSetObject(
                                        staff, task, score);
                                decisionSet.add(canStartTask);
                            }
                        }

                        toDeleteTasks.add(task);
                    }

                }
                
              

                for(TaskDO toDeleteTask : toDeleteTasks) {
                    tasks.remove(toDeleteTask);
                }

                
                // move from decision set to active set
                while (decisionSet.size() != 0) {

                    // get the one in decisionSet which have the lowest score
                    DecisionSetObject toActive = decisionSet.poll();

                    // get info from toActive to construct a TaskAllocObject
                    StaffDO staff = toActive.getStaff();
                    TaskDO task = toActive.getTask();
                    int startTime = getEarilestStartTime(staff.getStaffId());

                    // get availability of the staff to get holiday information
                    boolean[] availability = staffAvailablity.get(staff.getStaffId());
                    // calculate the endTime according to holiday and duration
                    
                    double skillLevel = staff.getSkillLevels().get(task.getTaskRequiredSkill());
                    int endTime = (int)ceil(task.getTaskDuration() / skillLevel)
                            + startTime
                            + getHoliday(startTime, (int)ceil(task.getTaskDuration() / skillLevel), availability);

                    TaskAllocObject activatedTask = new TaskAllocObject(task,
                            staff, startTime, endTime);
                    activeSet.add(activatedTask);

                    // remove the object in decisionSet with the same task
                    ArrayList<DecisionSetObject> toRemoveDecisionSetObjs = new ArrayList<DecisionSetObject>();
                    for (DecisionSetObject toRemove : decisionSet) {
                        if (toRemove.getTask().getTaskId() == task.getTaskId()) {
                            toRemoveDecisionSetObjs.add(toRemove);
                            //decisionSet.remove(toRemove);
                        }
                    }
                    for (DecisionSetObject remove : toRemoveDecisionSetObjs) {
                        decisionSet.remove(remove);
                    }

                    // update availableTime
                    for (int i = startTime; i < endTime; i++) {
                        availability[i] = false;
                    }

                    // update decisionSet according to new availableTime
                    for (DecisionSetObject toUpdate : decisionSet) {
                        double score = calcTaskScore(toUpdate.getStaff(), toUpdate.getTask());
                        toUpdate.setScore(score);
                    }
                    
                    // reconstruct decisionSet to get the right priority queue
                    ArrayList<DecisionSetObject> dcos = new ArrayList<DecisionSetObject>();
                    for (DecisionSetObject toUpdate : decisionSet) {
                        dcos.add(toUpdate);
                    }
                    decisionSet.clear();
                    for (DecisionSetObject dco : dcos) {
                        decisionSet.add(dco);
                    }

                }
                
              

                // add the task in activeSet which will complete first to the completeSet
                TaskAllocObject firstFinishedTask = null;
                for (TaskAllocObject taskAlloc : activeSet) {
                    if (firstFinishedTask == null) {
                        firstFinishedTask = taskAlloc;
                    } else if (firstFinishedTask.getEndTime() > taskAlloc.getEndTime()) {
                        firstFinishedTask = taskAlloc;
                    }
                }
                // move it from activeSet to completeSet
                activeSet.remove(firstFinishedTask);
                completeSet.add(firstFinishedTask);
                // reset currentTime
                currentTime = firstFinishedTask.getEndTime();
                
                

            }

            // add result to result set
            for(TaskAllocObject taskAlloc : completeSet) {
                if(taskAlloc.getStartTime() != taskAlloc.getEndTime()) {
                    scheduleResult.add(
                            new ResultDO(currentProject.getProjectId(),
                                        taskAlloc.getTask(),
                                        taskAlloc.getStaff(),
                                        DateTime.hourLater(projectStartingDate, taskAlloc.getStartTime()),
                                        DateTime.hourLater(projectStartingDate, taskAlloc.getEndTime())
                                        ));
                }
            }

            // empty completeSet for next project
            completeSet.clear();
            // reset current time
            currentTime = 0;
        }
        
        
        return scheduleResult;
    }

    private boolean canStart(TaskDO task) {

        ArrayList<Integer> requiredId = task.getRequiredTaskIds();

        if (requiredId.size() == 0) {
            return true;
        }

        boolean canStart = true;
        for (int taskId : requiredId) {

            boolean eq = false;
            for (TaskAllocObject taskAlloc : completeSet) {
                if (taskAlloc.getTask().getTaskId() == taskId) {
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
        double skillLevel = staff.getSkillLevels().get(
                task.getTaskRequiredSkill());

        // get holiday if relevant
        int holiday = getHoliday(earliestStartTime, (int)ceil(task.getTaskDuration() / skillLevel),
                availability);

        // calculate the score according to the formula
        return (int) ceil(earliestStartTime + task.getTaskDuration()
                / skillLevel + holiday);

    }

    private int getHoliday(int startTime, int duration, boolean[] availability) {
        int holidayCounter = 0;
        for (int i = startTime; i < (startTime + duration); i++) {
            if (!availability[i + holidayCounter]) {
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
        for (int i = currentTime; i < availability.length; i++) {

            if (availability[i]) {
                availableTime = i;
                break;
            }

        }
        // get earliest time for the task can be start for this staff
        int earliestStartTime = max(availableTime, currentTime);
        return earliestStartTime;
    }




}
