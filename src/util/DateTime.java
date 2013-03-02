package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.Days;

public class DateTime {

    private int year, month, day, hour, minute, second;

    //private String dateTime = "0000-00-00 00:00:00";
    private final SimpleDateFormat dataBaseDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public DateTime(String dateTime) {
        year = Integer.parseInt(dateTime.substring(0, 4));
        month = Integer.parseInt(dateTime.substring(5, 7));
        day = Integer.parseInt(dateTime.substring(8, 10));
        hour = Integer.parseInt(dateTime.substring(11, 13));
        minute = Integer.parseInt(dateTime.substring(14, 16));
        second = Integer.parseInt(dateTime.substring(17, 19));
    }

    public DateTime(int year, int month, int day, 
            int hour, int minute, int second) {
        // TODO throw some exception when these values are invalid
        //
        //dateTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month,
        //        day, hour, minute, second);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public DateTime(Date date) {
    }
    
    public DateTime() {
    	
    }

    public String getDateTime() {
        String dateTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", 
                year, month, day, hour, minute, second);
        return dateTime;
    }


    public boolean before(DateTime toCompare) {
        try {
            String dateTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", 
                                        year, month, day, hour, minute, second);
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

        String strStart = start.getDateTime();
        String strEnd = end.getDateTime();

        DateMidnight sd = new DateMidnight(strStart.substring(0, 10));
        DateMidnight ed = new DateMidnight(strEnd.substring(0, 10));

        int days = Days.daysBetween(sd, ed).getDays();
        
        int startHour = Integer.parseInt(strStart.substring(11,13));
        int endHour = Integer.parseInt(strEnd.substring(11,13));

        return (endHour - startHour) + 8 * days;
    }

    // getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
