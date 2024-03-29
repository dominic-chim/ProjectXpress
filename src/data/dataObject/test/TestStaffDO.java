package data.dataObject.test;

import java.util.HashMap;
import util.*;
import data.dataObject.*;
import test.GroupTestCase;

/**
 * 
 * test for StaffDO
 * 
 * @author Hongyang LIN
 *
 */
public class TestStaffDO extends GroupTestCase{
    
    private StaffDO sdo;
    
    public TestStaffDO(String name) {
        super(name);
    }
    
    protected void setUp() {
        sdo = new StaffDO(0,null,0,null,null);
    }
    
    public void testSetGetStaffId() {
        sdo.setStaffId(1);
        assertEquals(1, sdo.getStaffId());
    }
    
    public void testSetStaffName(){
    	sdo.setStaffName("bob");
    	assertEquals("bob",sdo.getStaffName());
    }
    public void testStaffWeeklyAvailableTime(){
    	sdo.setStaffWeeklyAvailableTime(45);
    	assertEquals(45,sdo.getStaffWeeklyAvailableTime());
    }
    
    public void testSetStaffSkillLevels(){
    	HashMap<Integer, Double> tempSkill = new HashMap<Integer, Double>();
    	tempSkill.put(1, 0.8);
    	sdo.setSkillLevels(tempSkill);
    	assertEquals(0.8,sdo.getSkillLevels().get(1), 2e-10);
    }
    
    public void testSetStaffHolidays(){
    	HashMap<DateTime, DateTime> tempHoliday = new HashMap<DateTime, DateTime>();
    	DateTime start = new DateTime(2010,3,2,9,00,00);
    	DateTime end = new DateTime(2010,3,3,9,00,00);
    	tempHoliday.put(start, end);
    	sdo.setHolidayHour(tempHoliday);
  
    	assertEquals(new DateTime("2010-03-03 09:00:00").getYear(),sdo.getHolidays().get(start).getYear());
    	assertEquals(new DateTime("2010-03-03 09:00:00").getMonth(),sdo.getHolidays().get(start).getMonth());
    	assertEquals(new DateTime("2010-03-03 09:00:00").getDay(),sdo.getHolidays().get(start).getDay());
    	assertEquals(new DateTime("2010-03-03 09:00:00").getHour(),sdo.getHolidays().get(start).getHour());
    	assertEquals(new DateTime("2010-03-03 09:00:00").getMinute(),sdo.getHolidays().get(start).getMinute());
    	assertEquals(new DateTime("2010-03-03 09:00:00").getSecond(),sdo.getHolidays().get(start).getSecond());
    }
}
