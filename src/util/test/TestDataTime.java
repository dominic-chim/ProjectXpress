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
    }
}
