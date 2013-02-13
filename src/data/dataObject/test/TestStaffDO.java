package data.dataObject.test;

import data.dataObject.*;
import test.GroupTestCase;

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
    	sdo.setStaffName(null);
    	assertEquals(null,sdo.getStaffName());
    }
    public void testStaffWeeklyAvailableTime(){
    	sdo.setStaffWeeklyAvailableTime(0);
    	assertEquals(0,sdo.getStaffWeeklyAvailableTime());
    }
    
    public void testSetStaffSkills(){
    	sdo.setSkills(null);
    	assertEquals(null,sdo.getSkills());
    }
    
    public void testSetStaffHolidays(){
    	sdo.setHolidays(null);
    	assertEquals(null,sdo.getHolidays());
    }
}
