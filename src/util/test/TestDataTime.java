package util.test;

import org.junit.Before;
import org.junit.Test;

import util.DateTime;
import static org.junit.Assert.*;

public class TestDataTime {

    private DateTime dt1;

    @Before
    public void setUp() {
        dt1 = new DateTime(2013, 10, 25, 10, 00, 00);
    }
    
    @Test
    public void testOutPrintDateTime(){
        assertEquals("2013-10-25 10:00:00",dt1.getDateTime());
        assertEquals(2013, dt1.getYear());
        assertEquals(10, dt1.getMonth());
        assertEquals(25, dt1.getDay());
        assertEquals(10, dt1.getHour());
        assertEquals(0, dt1.getMinute());
        assertEquals(0, dt1.getSecond());
    }

    @Test
    public void testHourLater() {
        assertEquals("2013-10-25 17:00:00",DateTime.hourLater(dt1, 7).getDateTime());
        assertEquals("2013-10-26 10:00:00",DateTime.hourLater(dt1, 8).getDateTime());
        assertEquals("2013-11-05 10:00:00",DateTime.hourLater(dt1, 88).getDateTime());
    }

    @Test
    public void testDuration() {
        assertEquals(88, DateTime.duration(dt1, new DateTime("2013-11-05 10:00:00")));
    }
}
