package util.test;

import test.GroupTestCase;
import util.DateTime;
import static org.junit.Assert.*;

public class TestDataTime extends GroupTestCase{
	
	private DateTime time;
	
    public TestDataTime(String name) {
        super(name);
    }
    
    protected void setUp() {
        time = new DateTime(1000, 00, 00, 00, 00, 00);
    }
    
    public void testOutPrintDateTime(){
    	assertEquals("1000-00-00 00:00:00",time.getDateTime());
    	assertEquals(new DateTime("1000-00-00 00:00:00").getYear(),time.getYear());
    	assertEquals(new DateTime("1000-00-00 00:00:00").getMonth(),time.getMonth());
    	assertEquals(new DateTime("1000-00-00 00:00:00").getDay(),time.getDay());
    	assertEquals(new DateTime("1000-00-00 00:00:00").getHour(),time.getHour());
    	assertEquals(new DateTime("1000-00-00 00:00:00").getMinute(),time.getMinute());
    	assertEquals(new DateTime("1000-00-00 00:00:00").getSecond(),time.getSecond());
    }
}
