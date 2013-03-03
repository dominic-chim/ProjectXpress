package algorithm;

import static java.lang.Math.ceil;
import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

//import org.joda.time.DateMidnight;
//import org.joda.time.Days;

import util.DateTime;
import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.ResultDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;

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
    private PriorityQueue<ProjectDO> projects = new PriorityQueue<ProjectDO>(5,
            new ProjectComparator());

    private ArrayList<ResultDO> scheduleResult = new ArrayList<ResultDO>();

    public ScheduleAlgorithm(DateTime projectStartingDate,
            DateTime projectsDueDate, ArrayList<StaffDO> staffList,
            PriorityQueue<ProjectDO> projects) {

        this.projectStartingDate = projectStartingDate;
        this.staffList = staffList;
        this.projects = projects;

        // get the greatest project due date
        DateTime greatestProjectDueDate = null;
        for (ProjectDO project : projects) {
            if (greatestProjectDueDate == null) {
                greatestProjectDueDate = project.getProjectDueDate();
            } else {
                if (greatestProjectDueDate.before(project.getProjectDueDate())) {
                    greatestProjectDueDate = project.getProjectDueDate();
                }
            }
        }

        // convert DateTime to 123..
        for (StaffDO staff : staffList) {

            HashMap<DateTime, DateTime> holidays = staff.getHolidays();
            for (DateTime holidayStartTime : holidays.keySet()) {
                //int duration = holidays.get(holidayStartTime);
                //DateTime holidayEndTime = holidayStartTime.addWorkingHour(duration);
                DateTime holidayEndTime = holidays.get(holidayStartTime);
                if (holidayEndTime.before(projectStartingDate)) {
                    holidays.remove(holidayStartTime);
                }
            }

            // TODO calculate the length of boolean array
            // TODO convert holiday to working days
            // TODO store and empty complete set !
            boolean[] availability = new boolean[DateTime.duration(projectStartingDate, projectsDueDate)];
            Arrays.fill(availability, Boolean.TRUE);
            for (DateTime holidayStartTime : holidays.keySet()) {
                
                DateTime holidayEndTime = holidays.get(holidayStartTime);
                int holidayDuration = DateTime.duration(holidayStartTime, holidayEndTime);
                int holidayStartTimeInAlg = DateTime.duration(projectStartingDate,
                        holidayStartTime);
                for (int i = holidayStartTimeInAlg; i < holidayStartTimeInAlg
                        + holidayDuration; i++) {
                    availability[i] = false;
                }
            }
            staffAvailablity.put(staff.getStaffId(), availability);

        }

    }

    public void runAlgoritm() {

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

                        //tasks.remove(task);
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
                scheduleResult.add(
                        new ResultDO(currentProject.getProjectId(),
                                    taskAlloc.getTask(),
                                    taskAlloc.getStaff(),
                                    DateTime.hourLater(projectStartingDate, taskAlloc.getStartTime()),
                                    DateTime.hourLater(projectStartingDate, taskAlloc.getEndTime())
                                    ));
            }

            //TODO del
            for(TaskAllocObject to : completeSet) {
                System.out.println(to);
            }

            // empty completeSet for next project
            completeSet.clear();
            // reset current time
            currentTime = 0;
        }
        

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

    /*
    private void removeIrreleventHoliday(HashMap<DateTime, Integer> holiday) {

    }
     */



    /*
    public static int duration(DateTime start, DateTime end) {

        String strStart = start.getDateTime();
        String strEnd = end.getDateTime();

        DateMidnight sd = new DateMidnight(strStart.substring(0, 10));
        DateMidnight ed = new DateMidnight(strEnd.substring(0, 10));

        int days = Days.daysBetween(sd, ed).getDays();
        
        int startHour = Integer.parseInt(strStart.substring(11,13));
        int endHour = Integer.parseInt(strEnd.substring(11,13));

        return (endHour - startHour) + 8 * days;

        
    }  
    */


}
