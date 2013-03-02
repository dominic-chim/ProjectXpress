package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.Days;

public class DateTime {

	private int year;
    private String dateTime = "0000-00-00 00:00:00";
    private final SimpleDateFormat dataBaseDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime(int year, int month, int day, int hour, int minute,
            int second) {
        // TODO throw some exception when these values are invalid
        //
        dateTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month,
                day, hour, minute, second);
    }

    public DateTime(Date date) {
    }

    public String getDateTime() {
        return this.dateTime;
    }


    public boolean before(DateTime toCompare) {
        try {
            Date thisDate = dataBaseDateFormat.parse(dateTime);
            Date dateToCompare = dataBaseDateFormat.parse(toCompare.getDateTime());
            return thisDate.before(dateToCompare);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static int duration(DateTime start, DateTime end) {

        /*
        ReadableInstant startRI = (ReadableInstant) start;
        ReadableInstant endRI = (ReadableInstant) end;

        Days d = Days.daysBetween(startRI, endRI);
        int days = d.getDays();
         */

        String strStart = start.getDateTime();
        String strEnd = end.getDateTime();

        //int startDay = Integer.parseInt(strStart.substring(8,10));
        //int endDay = Integer.parseInt(strEnd.substring(8,10));
        DateMidnight sd = new DateMidnight(strStart.substring(0, 10));
        DateMidnight ed = new DateMidnight(strEnd.substring(0, 10));

        int days = Days.daysBetween(sd, ed).getDays();
        
        int startHour = Integer.parseInt(strStart.substring(11,13));
        int endHour = Integer.parseInt(strEnd.substring(11,13));

        return (endHour - startHour) + 8 * days;
    }  


}
