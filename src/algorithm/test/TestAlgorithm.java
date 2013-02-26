package algorithm.test;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;

public class TestAlgorithm {

    //public ScheduleAlgorithm(DateTime projectStartingDate, ArrayList<StaffDO> staffList, 
    //        PriorityQueue<ProjectDO> projects) {


	public void testDuration() {
		DateTime start = new DateTime(2013, 03, 01, 5,0,0);
		DateTime end = new DateTime(2013, 04, 01, 5,0,0);

		
		
		 ReadableInstant startRI = (ReadableInstant) start;
	     ReadableInstant endRI = (ReadableInstant) end;
	     
	     Days d = Days.daysBetween(startRI, endRI);
	     int days = d.getDays();
	     
	     System.out.println(days);
	    
		
	}

}
