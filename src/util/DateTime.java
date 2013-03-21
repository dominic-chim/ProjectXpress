package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.Days;

/**
 *
 * class to hold data time
 *
 * @author Ke CHEN
 */
public class DateTime {

    private int year, month, day, hour, minute, second;

    public static final SimpleDateFormat dataBaseDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public DateTime(String dateTime) {
    	
        year = Integer.parseInt(dateTime.substring(0, 4));
        month = Integer.parseInt(dateTime.substring(5, 7));
        day = Integer.parseInt(dateTime.substring(8, 10));
        hour = Integer.parseInt(dateTime.substring(11, 13));
        // we don't care them
        minute = 0;
        second = 0;
        
        
        try {
	        org.joda.time.DateTime dt = new org.joda.time.DateTime(
	                this.getYear(),
	                this.getMonth(),
	                this.getDay(),
	                this.getHour(),
	                this.getMinute(),
	                this.getSecond(),
	                0);
        } catch (Exception ex) {
        	throw new IllegalArgumentException("invalid date");
        }
        
    }

    public DateTime(int year, int month, int day, 
            int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        // we don't care them
        this.minute = 0;
        this.second = 0;
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

    public static DateTime hourLater(DateTime date, int hour) {

        org.joda.time.DateTime dt = new org.joda.time.DateTime(
                date.getYear(),
                date.getMonth(),
                date.getDay(),
                date.getHour(),
                date.getMinute(),
                date.getSecond(),
                0);

        int daysBetween = hour / 8;
        int hoursRemain = hour % 8;
        if(hoursRemain == 0 && hour != 0) {
            daysBetween--;
            hoursRemain = 8;
        }
        dt = dt.plusDays(daysBetween);

        if (dt.getHourOfDay() + hoursRemain > 17) {
            dt = dt.plusDays(1);
            return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 
                                dt.getHourOfDay() - 8 + hoursRemain, 0, 0);
        } else {
            return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 
                                dt.getHourOfDay() + hoursRemain, 0, 0);
        }
        

        

    }
    
    /**
     * returns nextDay at 9:00 am
     */
    public static DateTime nextDay(DateTime date) {
        org.joda.time.DateTime dt = new org.joda.time.DateTime(
                date.getYear(),
                date.getMonth(),
                date.getDay(),
                date.getHour(),
                date.getMinute(),
                date.getSecond(),
                0);
        dt = dt.plusDays(1);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 9, 0, 0);
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
