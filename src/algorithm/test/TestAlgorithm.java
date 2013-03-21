package algorithm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import algorithm.ScheduleAlgorithm;

import data.dataObject.ProjectComparator;
import data.dataObject.ProjectDO;
import data.dataObject.StaffDO;
import data.dataObject.TaskDO;
import static org.junit.Assert.*;
import util.DateTime;

/**
 * 
 * Class to Test the algorithm
 * 
 * @author Ke CHEN
 *
 */
public class TestAlgorithm {


    private PriorityQueue<ProjectDO> pqProj;
    private ArrayList<StaffDO> staffs;
    
    
    @Before
    public void init() {
        /*
         * projects
         */

        pqProj = new PriorityQueue<ProjectDO>(5, new ProjectComparator());

        // a random date
        util.DateTime td = new util.DateTime("1800-10-10 10:00:00");

        //------------project 1-----------


        // tasks for project 1
        ArrayList<Integer> taskreq1 = new ArrayList<Integer>();
        TaskDO a1 = new TaskDO(1, 1, "A1", 1, 3, 3, "", td, "", taskreq1);

        ArrayList<Integer> taskreq2 = new ArrayList<Integer>();
        taskreq2.add(1);
        TaskDO a2 = new TaskDO(1, 2, "A2", 3, 1, 1, "", td, "", taskreq2);

        ArrayList<Integer> taskreq3 = new ArrayList<Integer>();
        TaskDO a3 = new TaskDO(1, 3, "A3", 3, 1, 1, "", td, "", taskreq3);

        ArrayList<Integer> taskreq4 = new ArrayList<Integer>();
        taskreq4.add(2);
        taskreq4.add(3);
        TaskDO a4 = new TaskDO(1, 4, "A4", 2, 4, 4, "", td, "", taskreq4);
        
        ArrayList<Integer> taskreq5 = new ArrayList<Integer>();
        taskreq5.add(2);
        taskreq5.add(3);
        TaskDO a5 = new TaskDO(1, 5, "A5", 2, 2, 2, "", td, "", taskreq5);

        ArrayList<Integer> taskreq6 = new ArrayList<Integer>();
        taskreq6.add(5);
        TaskDO a6 = new TaskDO(1, 6, "A6", 1, 3, 3, "", td, "", taskreq6);

        //add tasks to a ArrayList
        ArrayList<TaskDO> tasksForP1 = new ArrayList<TaskDO>();
        tasksForP1.add(a1);
        tasksForP1.add(a2);
        tasksForP1.add(a3);
        tasksForP1.add(a4);
        tasksForP1.add(a5);
        tasksForP1.add(a6);


        // create ProjectDO for project 1
        ProjectDO project1 = new ProjectDO(1, "A", td, 1, "", tasksForP1);

        pqProj.add(project1);

        //------------project 2-----------

        ArrayList<Integer> taskreqb1 = new ArrayList<Integer>();
        TaskDO b1 = new TaskDO(2, 1, "B1", 2, 4, 4, "", td, "", taskreqb1);

        ArrayList<Integer> taskreqb2 = new ArrayList<Integer>();
        taskreqb2.add(1);
        TaskDO b2 = new TaskDO(2, 2, "B2", 3, 2, 2, "", td, "", taskreqb2);

        ArrayList<Integer> taskreqb3 = new ArrayList<Integer>();
        TaskDO b3 = new TaskDO(2, 3, "B3", 1, 5, 5, "", td, "", taskreqb3);

        ArrayList<Integer> taskreqb4 = new ArrayList<Integer>();
        TaskDO b4 = new TaskDO(2, 4, "B4", 3, 1, 1, "", td, "", taskreqb4);
        
        ArrayList<Integer> taskreqb5 = new ArrayList<Integer>();
        taskreqb5.add(2);
        taskreqb5.add(3);
        TaskDO b5 = new TaskDO(2, 5, "B5", 1, 2, 2, "", td, "", taskreqb5);

        //add tasks to a ArrayList
        ArrayList<TaskDO> tasksForP2 = new ArrayList<TaskDO>();
        tasksForP2.add(b1);
        tasksForP2.add(b2);
        tasksForP2.add(b3);
        tasksForP2.add(b4);
        tasksForP2.add(b5);


        // create ProjectDO for project 2
        ProjectDO project2 = new ProjectDO(2, "B", td, 2, "", tasksForP2);

        pqProj.add(project2);


        /*
         * staffs
         */
        HashMap<Integer, Double> skillLevels1 = new HashMap<Integer, Double>();
        skillLevels1.put(1, 1.0);
        skillLevels1.put(2, 0.5);
        HashMap<util.DateTime, util.DateTime> holidays1 = new HashMap<util.DateTime, util.DateTime>();
        holidays1.put(new util.DateTime("2013-02-03 10:00:00"), new util.DateTime("2013-02-03 11:00:00"));
        StaffDO r1 = new StaffDO(1, "Resource1", 0, skillLevels1, holidays1);


        HashMap<Integer, Double> skillLevels2 = new HashMap<Integer, Double>();
        skillLevels2.put(2, 1.0);
        skillLevels2.put(3, 1.0);
        HashMap<util.DateTime, util.DateTime> holidays2 = new HashMap<util.DateTime, util.DateTime>();
        holidays2.put(new util.DateTime("2013-02-01 16:00:00"), new util.DateTime("2013-02-02 10:00:00"));
        StaffDO r2 = new StaffDO(2, "Resource2", 0, skillLevels2, holidays2);



        /////-----

        staffs = new ArrayList<StaffDO>();
        staffs.add(r1);
        staffs.add(r2);
    }
    
    
    @Test
    public void testDuration() {
        DateTime start = new DateTime(2013, 4, 1, 16,0,0);
        DateTime end = new DateTime(2013, 4, 2, 10,0,0);
        
        assertEquals(2, DateTime.duration(start, end));
        
    }
    
    
    @Test
    public void testAlgorithmWithHoliday(){


        ScheduleAlgorithm algorithm = new ScheduleAlgorithm(new DateTime("2013-02-01 09:00:00"), 
                                                            new DateTime("2013-02-06 09:00:00"),
                                                            staffs, pqProj);
        algorithm.runAlgoritm();
        System.out.println("--------------");
        
    }
    
    @Ignore
    public void testAlgorithmWithoutHoliday(){


        ScheduleAlgorithm algorithm = new ScheduleAlgorithm(new DateTime("2013-03-01 09:00:00"), 
                                                            new DateTime("2013-03-06 09:00:00"),
                                                            staffs, pqProj);
        algorithm.runAlgoritm();
        System.out.println("--------------");
    }

}
